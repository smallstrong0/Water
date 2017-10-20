package com.smallstrong.water.http;


import com.smallstrong.ss_common.SSLog;
import com.smallstrong.water.app.AppContext;
import com.smallstrong.water.app.AppEnvEnum;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author smallstrong
 *         created at 2017/6/18 下午1:24
 */

public class Http {

    private static OkHttpClient client;
    private static WaterApi waterApi;
    private static volatile Retrofit retrofit;
    private static String BASE_URL_ONLINE = "http://smallstrong.wang";
    private static String BASE_URL_DEBUG = "http://smallstrong.wang";


    /**
     * @return retrofit的底层利用反射的方式, 获取所有的api接口的类
     */
    public static WaterApi getWaterApi() {
        if (waterApi == null) {
            waterApi = getRetrofit().create(WaterApi.class);
        }
        return waterApi;
    }


    /**
     * 设置公共参数
     */
    private static Interceptor addQueryParameterInterceptor() {
        Interceptor addQueryParameterInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                Request request;
                HttpUrl modifiedUrl = originalRequest.url().newBuilder()
                        // Provide your custom parameter here
                        .addQueryParameter("phoneSystem", "")
                        .addQueryParameter("phoneModel", "")
                        .build();
                request = originalRequest.newBuilder().url(modifiedUrl).build();
                return chain.proceed(request);
            }
        };
        return addQueryParameterInterceptor;
    }

    /**
     * 设置头
     */
    private static Interceptor addHeaderInterceptor() {
        Interceptor headerInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                Request.Builder requestBuilder = originalRequest.newBuilder()
                        // Provide your custom header here
                        .header("token", "test")
                        .method(originalRequest.method(), originalRequest.body());
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        };
        return headerInterceptor;
    }

    private static Interceptor addLoggerInterceptor() {
        //添加一个log拦截器,打印所有的log
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {

            @Override
            public void log(String message) {
                SSLog.json(message);
            }
        });
        //可以设置请求过滤的水平,body,basic,headers
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }

    private static String getBaseUrl() {
        return AppEnvEnum.appEnvEnum == AppEnvEnum.ONLINE ? BASE_URL_ONLINE : BASE_URL_DEBUG;
    }


    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            synchronized (Http.class) {
                if (retrofit == null) {
                    //设置 请求的缓存的大小跟位置
                    File cacheFile = new File(AppContext.getAppContext().getCacheDir(), "cache");
                    Cache cache = new Cache(cacheFile, 1024 * 1024 * 50); //50Mb 缓存的大小

                    OkHttpClient.Builder build = new OkHttpClient.Builder();
//                            .connectionSpecs(Collections.singletonList(ConnectionSpec.MODERN_TLS))
//                            .certificatePinner(new CertificatePinner.Builder()
//                                    .add("www.smallstrong.wang", "test")
//                                    .build());

                    if (AppEnvEnum.appEnvEnum == AppEnvEnum.DEBUG) {
                        build.addInterceptor(addLoggerInterceptor());
                    }
                    client = build
                            .addInterceptor(addQueryParameterInterceptor())  //参数添加
                            .addInterceptor(addHeaderInterceptor()) // token过滤
                            .cache(cache)  //添加缓存
                            .connectTimeout(60l, TimeUnit.SECONDS)
                            .readTimeout(60l, TimeUnit.SECONDS)
                            .writeTimeout(60l, TimeUnit.SECONDS)
                            .build();

                    // 获取retrofit的实例
                    retrofit = new Retrofit
                            .Builder()
                            .baseUrl(getBaseUrl())
                            .client(client)
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                            .addConverterFactory(GsonConverterFactory.create()) //这里是用的fastjson的
                            .build();
                }
            }
        }
        return retrofit;
    }

}
