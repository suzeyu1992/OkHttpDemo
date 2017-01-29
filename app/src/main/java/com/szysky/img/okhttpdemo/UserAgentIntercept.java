/*
 * UserAgentIntercept 2017-02-16
 * Copyright (c) 2017 suzeyu Co.Ltd. All right reserved
 */
package com.szysky.img.okhttpdemo;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Author :  suzeyu
 * Time   :  2017-02-16  下午7:27
 * Blog   :  http://szysky.com
 * GitHub :  https://github.com/suzeyu1992
 * ClassDescription : 自定义User-Agent 拦截器
 */
public class UserAgentIntercept implements Interceptor {

    private static final String USER_AGENT = "User-Agent";
    private  String cusHeaderValue;

    public UserAgentIntercept(String userAgentValue){
        cusHeaderValue = userAgentValue;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request originalRequest = chain.request();
        Request newRequest = originalRequest.newBuilder()
                .removeHeader(USER_AGENT)
                .addHeader(USER_AGENT, cusHeaderValue)
                .build();

        return chain.proceed(newRequest);
    }
}
