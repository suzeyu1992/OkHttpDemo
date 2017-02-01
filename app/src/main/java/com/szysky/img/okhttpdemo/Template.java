/*
 * Template 2017-02-16
 * Copyright (c) 2017 suzeyu Co.Ltd. All right reserved
 */
package com.szysky.img.okhttpdemo;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Author :  suzeyu
 * Time   :  2017-02-16  下午8:29
 * Blog   :  http://szysky.com
 * GitHub :  https://github.com/suzeyu1992
 * ClassDescription : 模板代码
 */
public class Template {


    /**
     * 构建OkHttpClient模板
     */
    public OkHttpClient createClient(){

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(2000, TimeUnit.MILLISECONDS)        // 设置连接超时
                .readTimeout(2000, TimeUnit.MILLISECONDS)           // 设置读超时
                .writeTimeout(2000, TimeUnit.MILLISECONDS)          // 设置写超时
                .retryOnConnectionFailure(true)                     // 失败是否自动重连
                .addNetworkInterceptor(new StethoInterceptor())
                .addInterceptor(new UserAgentIntercept("android 8.8  version/111"))     // 设置请求头的User-Agent字段
                .build();

        return okHttpClient;
    }

    public Request createRequest(){
        Request request = new Request.Builder()
                .url("https://api.github.com/repos/square/okhttp/issues")                          //设置访问url
                .get()                                                                             //类似的有post、delete、patch、head、put等方法，对应不同的网络请求方法
                .header("User-Agent", "OkHttp Headers.java")                                       //设置header
                .addHeader("Accept", "application/json; q=0.5")                                    //添加header
                .removeHeader("User-Agent")                                                        //移除header
                .headers(new Headers.Builder().add("User-Agent", "OkHttp Headers.java").build())   //移除原有所有header，并设置新header
                .addHeader("Accept", "application/vnd.github.v3+json")
                .build();                                                                          //构建request

        return request;
    }

    /**
     * 当内存不足. 可以通过代码手动释放网络缓存立即关闭连接池并清除等动作
     */
    public void clearCache(OkHttpClient client) throws IOException {
        client.dispatcher().executorService().shutdown();   //清除并关闭线程池
        client.connectionPool().evictAll();                 //清除并关闭连接池
        client.cache().close();
    }
}
