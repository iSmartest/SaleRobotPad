package com.freeintelligence.robotclient.net;

import com.freeintelligence.robotclient.net.callback.MyNetWorkCallback;
import com.squareup.okhttp.OkHttpClient;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;


/**
 *
 */


//暂搁置
public class RetrofitUtils implements IHttp {

    private static final int READ_TIMEOUT = 60;//读取超时时间,单位  秒
    private static final int CONN_TIMEOUT = 12;//连接超时时间,单位  秒

    private static volatile Retrofit mRetrofit;

    private RetrofitUtils() {

    }

    public static Retrofit newInstence(String url) {
        OkHttpClient client = new OkHttpClient();//初始化一个client,不然retrofit会自己默认添加一个
        client.setReadTimeout(READ_TIMEOUT, TimeUnit.MINUTES);//设置读取时间为一分钟
        client.setConnectTimeout(CONN_TIMEOUT, TimeUnit.SECONDS);//设置连接时间为12s
        if (mRetrofit == null) {
            //同步代码块
            synchronized (RetrofitUtils.class) {
                if (mRetrofit == null) {
                    mRetrofit = new Retrofit.Builder()
                            .client(client)//添加一个client,不然retrofit会自己默认添加一个
                            .baseUrl(url)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .build();
                }
            }

        }

        return mRetrofit;
    }

    @Override
    public <T> void get(String url, Map<String, String> params, MyNetWorkCallback<T> callback) {

    }

    @Override
    public <T> void post(String url, Map<String, String> params, MyNetWorkCallback<T> callback) {

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
}
