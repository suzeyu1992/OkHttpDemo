package com.szysky.img.okhttpdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "suzeyu";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 发送get请求
     */
    public void send_get(View tv_get){

        OkHttpClient okHttpClient = new OkHttpClient();

        // 创建请求头
        Request request = new Request.Builder()
                .url("http://gc.ditu.aliyun.com/geocoding?a=苏州")
                .build();

        // 新增一个调用请求对象
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i(TAG, "get请求失败:  "+e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i(TAG, "get请求成功: "+ response.toString());
                Log.i(TAG, "get请求成功: "+ response.body().string());
                response.close();
            }
        });
    }
}
