package com.freeintelligence.robotclient.net.callback;

import android.os.Handler;
import android.os.Looper;

import java.io.File;
import java.io.IOException;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class OkHttpManager {
    public static final String TAG = "OkHttpManger";
    private static OkHttpClient okHttpClient;
    public static OkHttpManager manager;
    private Handler mDelivery;


    private OkHttpManager() {
       // okHttpClient = new OkHttpClient();
         okHttpClient = new OkHttpClient.Builder()
        .connectTimeout(20, TimeUnit.SECONDS)
        .writeTimeout(20, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .build();
        
        mDelivery = new Handler(Looper.getMainLooper());
    }

    public static OkHttpManager getInstance() {
        if (manager == null) {
            synchronized (OkHttpManager.class) {
                if (manager == null) {
                    manager = new OkHttpManager();
                }
            }
        }
        return manager;
    }
    
    

    private void _getAsyn(String url, final ResultCallback callback) {
        final Request request = new Request.Builder()
                .url(url)
                .build();
        deliveryResult(callback, request);
    }

    private void _postFileAsyn(String url, File[] files, final ResultCallback callback, Map<String, Object> map) {
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
//添加文件
        if (files.length != 0) {
            for (int i = 0; i < files.length; i++) {
                RequestBody fileBody = RequestBody.create(
                        MediaType.parse(getMediaType(files[i].getName())), files[i]);
                builder.addFormDataPart("imageUpload", files[i].getName(), fileBody);
            }
        }
//添加参数
        if (map != null) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                builder.addFormDataPart(entry.getKey(), (String) entry.getValue());
            }
        }
        Request request = new Request.Builder().url(url).post(builder.build()).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(final Call call, final IOException e) {
                mDelivery.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onError( e);
                    }
                });
            }

            @Override
            public void onResponse(final Call call,  Response response) throws IOException {
                final String string=response.body().string();
                mDelivery.post(new Runnable() {
                    @Override
                    public void run() {
                            callback.onResponse(string);
                    }
                });

            }
        });
    }

    private void _postAsyn(String url, final ResultCallback callback, Map<String, String> map) {
        FormBody.Builder build = new FormBody.Builder();
        if (map != null) {
            //增强for循环遍历
            for (Map.Entry<String, String> entry : map.entrySet()) {
                build.add(entry.getKey(), entry.getValue());
            }
        }
        FormBody formBody = build.build();
        Request request = buildPostRequest(url, formBody);
        deliveryResult(callback, request);
    }

    private Request buildPostRequest(String url, FormBody formBody) {
        return new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
    }

    /**
     * 根据文件的名称判断文件的Mine值
     */
    private String getMediaType(String fileName) {
        FileNameMap map = URLConnection.getFileNameMap();
        String contentTypeFor = map.getContentTypeFor(fileName);
        if (contentTypeFor == null) {
            contentTypeFor = "application/octet-stream";
        }
        return contentTypeFor;
    }

    private void deliveryResult(final ResultCallback callback, Request request) {
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(final Call call, final IOException e) {
                if(e!=null){
                    mDelivery.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onError(e);
                        }
                    });
                }

            }

            @Override
            public void onResponse(final Call call,  Response response) throws IOException {
                final String str=response.body().string();
                mDelivery.post(new Runnable() {
                    @Override
                    public void run() {
                            callback.onResponse(str);
                    }
                });
            }
        });
    }

    public static void getAsyn(String url, ResultCallback callback) {
        getInstance()._getAsyn(url, callback);
    }

    public static void postAsyn(String url, ResultCallback callback, Map<String, String> params) {
        getInstance()._postAsyn(url, callback, params);
    }

    public static void postFileAsyn(String url, File[] files, ResultCallback callback, Map<String, Object> params) {
        getInstance()._postFileAsyn(url, files, callback, params);
    }

    public static abstract class ResultCallback {
        public abstract void onError(Exception e);

        public abstract void onResponse(String string);
    }


    public void asynUpLoadPost(String url, HashMap<String, String> params,Map<String,File> map, ResultCallback resultCallback) {
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        addParams(builder, params);
        if (map != null) {
             RequestBody fileBody = null;
            File file = null;
            for(Map.Entry<String,File> entry:map.entrySet()){
                file = entry.getValue();
                if (file == null) {
                    continue;

                }
                String fileName = file.getName();
                fileBody = RequestBody.create(MediaType.parse(guessMimeType(fileName)), file);
                //TODO 根据文件名设置contentType
//                builder.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + fileKeys[i] + "\"; filename=\"" + fileName + "\""), fileBody);
                builder.addFormDataPart(entry.getKey(), fileName, fileBody);
            }
        }

        Request.Builder requestBuilder = new Request.Builder()
                .url(url)
                .post(builder.build())
                .tag(url);
        deliveryResult( resultCallback,requestBuilder.build());
    }
    private static void addParams(MultipartBody.Builder builder, HashMap<String, String> params) {
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                builder.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + key + "\""), RequestBody.create(null, params.get(key)));
            }
        }
    }
    private static String guessMimeType(String path) {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String contentTypeFor = fileNameMap.getContentTypeFor(path);
        if (contentTypeFor == null) {
            contentTypeFor = "application/octet-stream";
        }
        return contentTypeFor;
    }
}
