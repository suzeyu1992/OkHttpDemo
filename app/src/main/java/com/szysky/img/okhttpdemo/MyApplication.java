/*
 * MyApplication 2017-02-16
 * Copyright (c) 2017 suzeyu Co.Ltd. All right reserved
 */
package com.szysky.img.okhttpdemo;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Author :  suzeyu
 * Time   :  2017-02-16  下午6:19
 * Blog   :  http://szysky.com
 * GitHub :  https://github.com/suzeyu1992
 * ClassDescription :
 */
public class MyApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
