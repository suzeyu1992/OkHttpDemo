/*
 * CusRequestBody 2017-02-18
 * Copyright (c) 2017 suzeyu Co.Ltd. All right reserved
 */
package com.szysky.img.okhttpdemo;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;

/**
 * Author :  suzeyu
 * Time   :  2017-02-18  下午5:53
 * Blog   :  http://szysky.com
 * GitHub :  https://github.com/suzeyu1992
 * ClassDescription : 自定义RequestBody类, 可以通过流的方式来提交RequestBody
 */
public class CusRequestBody extends RequestBody {
    @Override
    public MediaType contentType() {
        return MediaType.parse("text/x-markdown; charset=utf-8");
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        for (int i = 0; i < 10; i++) {
            sink.writeUtf8(String.format("* %d",i));
        }
    }
}
