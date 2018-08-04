package com.jiangxufa.net_lib;


import com.jiangxufa.net_lib.converter.FastJsonConverterFactory;
import com.jiangxufa.net_lib.websocket.HttpHelper;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/*
 * 创建时间：2018/1/6
 * 编写人：lenovo
 * 功能描述：
 */

public class Network {

    private static HashMap<Class, Object> apiMap = new HashMap<>();
    private static Converter.Factory gsonConverterFactory = FastJsonConverterFactory.create();
    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJava2CallAdapterFactory.create();
    private static OkHttpClient okHttpClient;
    private static OkHttpClient.Builder builder;
    public final static int CONNECT_TIMEOUT =60;
    public final static int READ_TIMEOUT=100;
    public final static int WRITE_TIMEOUT=60;

    public static void init(Interceptor... interceptors) {
        HttpHelper.SSLParams sslParams = HttpHelper.getSslSocketFactory(HttpHelper.UnSafeTrustManager);
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder = new OkHttpClient.Builder()
                    .readTimeout(READ_TIMEOUT,TimeUnit.SECONDS)//设置读取超时时间
                    .writeTimeout(WRITE_TIMEOUT,TimeUnit.SECONDS)//设置写的超时时间
                    .connectTimeout(CONNECT_TIMEOUT,TimeUnit.SECONDS)//设置连接超时时间
                    .sslSocketFactory(sslParams.sSLSocketFactory,sslParams.trustManager);
            if (interceptors.length > 0) {
                for (Interceptor interceptor : interceptors) {
                    builder.addInterceptor(interceptor);
                }
            }
            builder.addInterceptor(httpLoggingInterceptor);
            okHttpClient = builder.build();
        } else {
            builder = new OkHttpClient.Builder();
            if (interceptors.length > 0) {
                for (Interceptor interceptor : interceptors) {
                    builder.addInterceptor(interceptor);
                }
            }
            okHttpClient = builder
                    .readTimeout(READ_TIMEOUT,TimeUnit.SECONDS)//设置读取超时时间
                    .writeTimeout(WRITE_TIMEOUT,TimeUnit.SECONDS)//设置写的超时时间
                    .connectTimeout(CONNECT_TIMEOUT,TimeUnit.SECONDS)//设置连接超时时间
                    .sslSocketFactory(sslParams.sSLSocketFactory,sslParams.trustManager)
                    .build();
        }

    }

//    public static void init() {
//        HttpHelper.SSLParams sslParams = HttpHelper.getSslSocketFactory(HttpHelper.UnSafeTrustManager);
//        if (BuildConfig.DEBUG) {
//            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
//            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//            okHttpClient = new OkHttpClient.Builder()
//                    .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)//设置读取超时时间
//                    .writeTimeout(WRITE_TIMEOUT,TimeUnit.SECONDS)//设置写的超时时间
//                    .connectTimeout(CONNECT_TIMEOUT,TimeUnit.SECONDS)//设置连接超时时间
//                    .addInterceptor(new ParamsInterceptor())
//                    .addInterceptor(httpLoggingInterceptor)
//                    .sslSocketFactory(sslParams.sSLSocketFactory,sslParams.trustManager)
//                    .build();
//        } else {
//            okHttpClient = new OkHttpClient.Builder()
//                    .readTimeout(READ_TIMEOUT,TimeUnit.SECONDS)//设置读取超时时间
//                    .writeTimeout(WRITE_TIMEOUT,TimeUnit.SECONDS)//设置写的超时时间
//                    .connectTimeout(CONNECT_TIMEOUT,TimeUnit.SECONDS)//设置连接超时时间
//                    .addInterceptor(new ParamsInterceptor())
//                    .sslSocketFactory(sslParams.sSLSocketFactory,sslParams.trustManager)
//                    .build();
//        }
//    }

    public static <T> T getService(Class<T> clazz) {
        if (apiMap.containsKey(clazz)) {
            return (T) apiMap.get(clazz);
        } else {
            throw new RuntimeException("请先初始化相应的api service");
        }
    }

    /**
     * 初始化retrofit客户端
     *
     * @param baseUrl baseUrl
     * @param clazz   apiService
     * @param <T>     apiService
     * @return apiService
     */
    public static <T> T initService(String baseUrl, Class<T> clazz) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .client(okHttpClient)
                .build();
        T t = retrofit.create(clazz);
        apiMap.put(clazz, t);
        return t;
    }


}
