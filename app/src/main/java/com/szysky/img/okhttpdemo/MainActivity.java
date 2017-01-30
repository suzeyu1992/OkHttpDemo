package com.szysky.img.okhttpdemo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
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

        String url = "http://gc.ditu.aliyun.com/geocoding?a=苏州";

        OkHttpClient okHttpClient = new OkHttpClient()
                .newBuilder()
                .connectTimeout(1000, TimeUnit.MILLISECONDS)
                .readTimeout(1000, TimeUnit.MILLISECONDS)
                .writeTimeout(1000, TimeUnit.MILLISECONDS)
                .addNetworkInterceptor(new StethoInterceptor())
                .addInterceptor(new UserAgentIntercept("android 8.8  version/111"))     // 设置请求头的User-Agent字段
                .build();




        // 创建请求头
        Request request = new Request.Builder()
                .url(url)
                .tag(url)
                .build();



        // 新增一个调用请求对象
        Call call = okHttpClient.newCall(request);

        // 同步调用 使用execute发起
        // call.execute();  // 代码阻塞, 直到响应返回一个Response对象

        // 异步调用 请求对象添加到队列
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

        // 取消网络请求
        //cancelCallWithTag(url, okHttpClient);
    }


    /**
     * 发送post请求
     */
    public void send_post(View tv_post){
         String url = "http://www.roundsapp.com/post" ;

        // post请求的 请求体内容
        String json = "{'winCondition':'HIGH_SCORE',"
                + "'name':'Bowling',"
                + "'round':4,"
                + "'lastSaved':1367702411696,"
                + "'dateStarted':1367702378785,"
                + "'players':["
                + "{'name':'" + "Jesse" + "','history':[10,8,6,7,8],'color':-13388315,'total':39},"
                + "{'name':'" + "Jake" + "','history':[6,10,5,10,10],'color':-48060,'total':41}"
                + "]}";

         // 设置请求体内容
        // 可以指定的编码字符集有字节数组, 文件, 字符串等类型
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);

        OkHttpClient okHttpClient = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i(TAG, "post请求失败:  "+e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i(TAG, "post请求成功: "+ response.toString());
                Log.i(TAG, "post请求成功: "+ response.body().string());
                response.close();

            }
        });

    }


    /**
     * 取消Request请求
     */
    public void cancelCallWithTag(String tag, OkHttpClient httpClient){

        // 遍历请求队列中待处理的 网络请求
        for (Call call : httpClient.dispatcher().queuedCalls()) {
            if (call.request().tag().equals(tag)){
                call.cancel();
                Log.i(TAG, "请求队列中的网络请求已经取消: tag= "+tag);
            }
        }

        // 遍历正在请求的对象
        for (Call call : httpClient.dispatcher().runningCalls()) {
            if (call.request().tag().equals(tag)) {
                call.cancel();
                Log.i(TAG, "正在发起的网络请求已经取消: tag= " + tag);
            }
        }

    }


}
