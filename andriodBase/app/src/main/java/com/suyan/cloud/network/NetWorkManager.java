package com.suyan.cloud.network;

import com.suyan.cloud.network.converter.CustomGsonConverterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class NetWorkManager {
    private static NetWorkManager s_Instance;
    private static int TIME_OUT = 10;
    private OkHttpClient mOkHttpClient;
//    private static Retrofit retrofit;

    private NetWorkManager() {

    }


    public static NetWorkManager getInstance() {
        if (s_Instance == null) {
            synchronized (NetWorkManager.class) {
                if (s_Instance == null) {
                    s_Instance = new NetWorkManager();
                }
            }
        }
        return s_Instance;
    }

    /**
     * 初始化必要对象和参数
     */
    public void init() {
        // 初始化okhttp
        BaseLoggingInterceptor loggingInterceptor = new BaseLoggingInterceptor();
        loggingInterceptor.setLevel(BaseLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        mOkHttpClient = builder
//                .addInterceptor(mRewriteCacheControlInterceptor)
                .addInterceptor(loggingInterceptor)
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
                .sslSocketFactory(SSLSocketClient.getSSLSocketFactory())
                .hostnameVerifier(SSLSocketClient.getHostnameVerifier())
                .build();
    }

    public <T> T create(String baseUrl, Class<T> service) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(mOkHttpClient)
                .addConverterFactory(CustomGsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(service);
    }

}
