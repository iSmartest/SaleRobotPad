package com.freeintelligence.robotclient.net;


import android.util.Log;


import com.freeintelligence.robotclient.app.App;
import com.freeintelligence.robotclient.net.callback.MyNetWorkCallback;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 基于OKhttp发送网络请求
 * Created by xingge on 2017/9/27.
 */

public class OkHttpUtils implements IHttp {
    public final static int CONNECT_TIMEOUT =20;
    public final static int READ_TIMEOUT=20;
    public final static int WRITE_TIMEOUT=20;
    public final static int MAX=10 * 1024 * 1024;
    private static final String TAG = "OkHttpUtils";
    private OkHttpClient okHttpClient;
    private static final File file =new File(App.getContext().getExternalCacheDir(),
            "okhttpcache");
    Cache cache =new Cache(file,MAX);
    //构造函数私有化
    private OkHttpUtils(){
        okHttpClient = new OkHttpClient.Builder().
         cache(cache)
                .readTimeout(READ_TIMEOUT,TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(WRITE_TIMEOUT,TimeUnit.SECONDS)//设置写的超时时间
                .connectTimeout(CONNECT_TIMEOUT,TimeUnit.SECONDS)//设置连接超时时间
                .build();


    }

    private static OkHttpUtils okHttpUtils;

    //提供一个公共的、静态的、返回值类型是当前本类的对象
    public static OkHttpUtils getInstance(){
        if(okHttpUtils == null){
            synchronized (OkHttpUtils.class){
                if(okHttpUtils == null)
                    okHttpUtils = new OkHttpUtils();
            }
        }
        return okHttpUtils;
    }

    /**
     * 发送get请求
     * @param url 请求地址
     * @param params 请求参数
     * @param callback 回调
     * @param <T> 请求回来的数据对应的JavaBean
     */
    @Override
    public <T> void get(String url, Map<String, String> params, final MyNetWorkCallback<T> callback) {

       final StringBuffer sb = new StringBuffer(url);
        if(params != null && params.size() > 0){
            sb.append("?");
            Set<String> keys = params.keySet();
            for (String key : keys) {
                String value = params.get(key);
                sb.append(key).append("=").append(value).append("&");
            }
            url = sb.deleteCharAt(sb.length()-1).toString();


        }
//        //设置缓存时间为60秒
//        CacheControl cacheControl = new CacheControl.Builder()
//                .maxAge(99999, TimeUnit.SECONDS)
//                .build();

        final Request request = new Request.Builder()
                .url(url).build();

        okHttpClient.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, final IOException e) {

                App.activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //执行在主线程

                        callback.onError(404,e.getMessage().toString());
                    }
                });

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String jsonData = response.body().string();

                //执行在子线程中
                App.activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //执行在主线程
                        Log.e(TAG, "onResponse: "+jsonData);
                        callback.onSuccess(getGeneric(jsonData,callback));
                    }
                });

            }
        });

    }

    @Override
    public <T> void post(String url, Map<String, String> params, final MyNetWorkCallback<T> callback) {

        FormBody.Builder builder = new FormBody.Builder();
        if(params !=null && params.size() > 0){
            Set<String> keys = params.keySet();
            for (String key : keys) {
                String value = params.get(key);
                builder.add(key,value);
                Log.e(TAG, "post: "+key+"--------------------"+value);
            }
        }

        Request request = new Request.Builder().url(url).post(builder.build()).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                if (App.activity==null){
                    return;
                }
                App.activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //执行在主线程
                        callback.onError(404,e.getMessage().toString());
                    }
                });

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String jsonData = response.body().string();
                if (App.activity==null){
                    return;
                }
                final T generic = getGeneric(jsonData, callback);

                //执行在子线程中
                App.activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //执行在主线程
                        callback.onSuccess(generic);
                    }
                });

            }
        });
    }

    @Override
    public void upload() {

    }

    @Override
    public void download() {

    }

    @Override
    public void loadImage() {

    }

    /**
     * 自动解析json至回调中的JavaBean
     * @param jsonData
     * @param callBack
     * @param <T>
     * @return
     */
    private <T> T getGeneric(String jsonData,MyNetWorkCallback<T> callBack) {
        Gson gson = new Gson();

        //通过反射获取泛型的实例
        Log.e(TAG, "getGeneric: " + jsonData);
        Type[] types = callBack.getClass().getGenericInterfaces();
        Type[] actualTypeArguments = ((ParameterizedType) types[0]).getActualTypeArguments();
        Type type = actualTypeArguments[0];
        try {
            T t = gson.fromJson(jsonData, type);
            return t;
        } catch (Exception e) {
            e.printStackTrace();
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw, true));
            String str = sw.toString();
            Log.i("", str);
            return null;
        }
    }
}
