package com.jiangxufa.net_lib.intercepter;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/*
 * 创建时间：2018/1/6
 * 编写人：lenovo
 * 功能描述：添加固定参数
 */
public class ParamsInterceptor implements Interceptor {


    private HttpUrl.Builder getParams;
    private Request newRequest;

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request oldRequest = chain.request();
        String method = oldRequest.method();
//        doGet(oldRequest);
//        if (method.equals("GET")) {
//            doGet(oldRequest);
//        } else if (method.equals("POST")){
//            doPost(oldRequest);
//        } else if (method.equals("PUT")) {
//            doPut(oldRequest);
//        }
        return chain.proceed(newRequest);
    }


//    private Request doGet(Request oldRequest) {
//        getParams = getGetParams(oldRequest);
//        if (!StringUtils.isEmpty(ApiConfig.getToken())) {
//            newRequest = oldRequest.newBuilder()
//                    .method(oldRequest.method(), oldRequest.body())
//                    //添加到请求里
//                    .url(getParams.build())
//                    .addHeader("token", ApiConfig.getToken())
//                    .build();
//        } else {
//            newRequest = oldRequest.newBuilder()
//                    .method(oldRequest.method(), oldRequest.body())
//                    //添加到请求里
//                    .url(getParams.build())
//                    .build();
//        }
//        return newRequest;
//    }
//
//    private Request doPost(Request oldRequest) {
//        if (!StringUtils.isEmpty(ApiConfig.getToken())) {
//            newRequest = oldRequest.newBuilder()
//                    .method(oldRequest.method(), oldRequest.body())
//                    //添加到请求里
//                    .url(getParams.build())
//                    .addHeader("token", ApiConfig.getToken())
//                    .post(RequestBody.create(MediaType.parse("application/x-www-form-urlencoded"),
//                            getPostParmas(oldRequest)))
//                    .build();
//        } else {
//            newRequest = oldRequest.newBuilder()
//                    .method(oldRequest.method(), oldRequest.body())
//                    //添加到请求里
//                    .url(getParams.build())
//                    .post(RequestBody.create(MediaType.parse("application/x-www-form-urlencoded"),
//                            getPostParmas(oldRequest)))
//                    .build();
//        }
//        return newRequest;
//    }
//
//    private Request doPut(Request oldRequest) {
//        if (!StringUtils.isEmpty(ApiConfig.getToken())) {
//            newRequest = oldRequest.newBuilder()
//                    .method(oldRequest.method(), oldRequest.body())
//                    //添加到请求里
//                    .url(getParams.build())
//                    .addHeader("token", ApiConfig.getToken())
//                    .put(RequestBody.create(MediaType.parse("application/x-www-form-urlencoded"),
//                            getPostParmas(oldRequest)))
//                    .build();
//        } else {
//            newRequest = oldRequest.newBuilder()
//                    .method(oldRequest.method(), oldRequest.body())
//                    //添加到请求里
//                    .url(getParams.build())
//                    .put(RequestBody.create(MediaType.parse("application/x-www-form-urlencoded"),
//                            getPostParmas(oldRequest)))
//                    .build();
//        }
//        return newRequest;
//    }
//
//
//    private HttpUrl.Builder getGetParams(Request request) {
//        if (!StringUtils.isEmpty(ApiConfig.getToken())) {
//            //获取到请求地址api
//            getParams = request.url().newBuilder()
//                    .addQueryParameter("versionCode", ApiConfig.getVersionCode())
//                    .addQueryParameter("terminalType", ApiConfig.getTerminalType());
//        } else {
//            getParams = request.url().newBuilder()
//                    .addQueryParameter("versionCode", ApiConfig.getVersionCode())
//                    .addQueryParameter("userType", ApiConfig.getUserType())
//                    .addQueryParameter("terminalType", ApiConfig.getTerminalType())
//                    .addQueryParameter("uniqid", ApiConfig.getUniqid())
//                    .addQueryParameter("appType", ApiConfig.getAppType());
//        }
//        return getParams;
//    }
//
//    private String getPostParmas(Request original) {
//        RequestBody formBody;
//        if (!StringUtils.isEmpty(ApiConfig.getToken())) {
//            formBody = new FormBody.Builder()//form表单
//                    .add("versionCode", ApiConfig.getVersionCode())
//                    .add("userType", ApiConfig.getUserType())
//                    .add("terminalType", ApiConfig.getTerminalType())
//                    .add("uniqid", ApiConfig.getUniqid())
//                    .add("appType", ApiConfig.getAppType())
//                    .build();
//        } else {
//            formBody = new FormBody.Builder()//form表单
//                    .add("versionCode", ApiConfig.getVersionCode())
//                    .add("terminalType", ApiConfig.getTerminalType())
//                    .build();
//        }
//        //默认添加formBody后不能添加新的form表单，需要先将RequestBody转成string去拼接
//        String postBodyString = bodyToString(original.body());
//        postBodyString += ((postBodyString.length() > 0) ? "&" : "") + bodyToString(formBody);
//        return postBodyString;
//    }

    private static String bodyToString(final RequestBody request) {
        try {
            final RequestBody copy = request;
            final Buffer buffer = new Buffer();
            if (copy != null) copy.writeTo(buffer);
            else return "";
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        }
    }

}
