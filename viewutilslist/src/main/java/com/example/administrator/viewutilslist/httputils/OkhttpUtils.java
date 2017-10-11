package com.example.administrator.viewutilslist.httputils;

import android.os.Environment;

import com.example.administrator.viewutilslist.utilslist.GsonTools;
import com.example.administrator.viewutilslist.utilslist.RequestCallBack;
import com.example.administrator.viewutilslist.utilslist.ThreadUtil;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.os.Environment.MEDIA_BAD_REMOVAL;
import static android.os.Environment.MEDIA_CHECKING;
import static android.os.Environment.MEDIA_REMOVED;
import static android.os.Environment.MEDIA_SHARED;
import static android.os.Environment.MEDIA_UNMOUNTED;

/**
 * Created by Administrator on 2017/8/31.
 *
 * @史上最帅无敌创建者 ccx
 * @创建时间 2017/8/31 14:14
 */

public class OkhttpUtils {
    private static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("application/json; charset=utf-8");
    private static final OkHttpClient mOkHttpClient;

    /*
        初始化
     */
    static {
        File file = null;
        String externalStorageState = Environment.getExternalStorageState();
        if (externalStorageState.equals(MEDIA_REMOVED) || externalStorageState.equals(MEDIA_UNMOUNTED) || externalStorageState.equals(MEDIA_BAD_REMOVAL) || externalStorageState.equals(MEDIA_CHECKING) || externalStorageState.equals(MEDIA_SHARED)) {
            file = new File(File.separator + "shcache");

        } else {
            file = new File(Environment.getExternalStorageDirectory() + File.separator + "shcache");

        }
        if (!file.exists()) {
            file.mkdirs();
        }

        mOkHttpClient = new OkHttpClient
                .Builder()
                .cache(new Cache(file, 10 * 1024 * 1024))
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .retryOnConnectionFailure(false)
                .build();
    }


    /**
     * post的异步请求
     *
     * @param json 传入一个json
     * @param url  传入一个url地址
     * @return
     */
    public static void PostEnqueueToString(String json, String url, final RequestCallBack requestCallBack) {
        Request request = PostJson(json, url);

        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                requestCallBack.onError(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                ThreadUtil.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        requestCallBack.onSuccess(string);

                    }
                });
            }
        });
    }

    private static Request PostJson(String json, String url) {
        RequestBody body = FormBody.create(MEDIA_TYPE_MARKDOWN, json);
        return new Request.Builder()
                .post(body)
                .url(url)
                .build();
    }

    /**
     * post的同步直接转换成bean
     *
     * @param json
     * @param url
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T PostExecuteToBean(final String json, final String url, final Class<T> clazz) {

        try {

            Request request = PostJson(json, url);

            Response response = mOkHttpClient.newCall(request).execute();

            if (response.isSuccessful()) {
                T t = GsonTools.changeGsonToBean(response.body().string(), clazz);
                return t;
            } else {

                return null;
            }

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }


    }

    /**
     * post的同步请求
     *
     * @param json
     * @param url
     * @return
     */
    public static String PostExecuteToString(final String json, final String url) {

        try {

            Request request = PostJson(json, url);

            Response response = mOkHttpClient.newCall(request).execute();

            if (response.isSuccessful()) {
                String string = response.body().string();
                return string;
            } else {
                return null;
            }

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }


    }


    /**
     * 同步请求get
     *
     * @param url 传入一个url
     * @return
     */
    public static String GetExecuteToString(final String url) {
        try {

            Request request = getRequest(url);

            Response response = mOkHttpClient.newCall(request).execute();

            if (response.isSuccessful()) {
                String string = response.body().string();
                return string;
            } else {
                return null;
            }

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }


    }

    private static Request getRequest(String url) {
        return new Request.Builder()
                .url(url)
                .build();
    }

    /**
     * 异步请求的get
     *
     * @param url             需要一个网址
     * @param requestCallBack 一个回调的接口
     */
    public static void GetEnqueueToString(String url, final RequestCallBack requestCallBack) {

        Request request = getRequest(url);
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                requestCallBack.onError(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                ThreadUtil.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        requestCallBack.onSuccess(string);

                    }
                });


            }
        });

    }


    /**
     * 同步直接转换成bean
     *
     * @param url
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T GetExecuteToBean(String url, final Class<T> clazz) {

        try {
            Request request = getRequest(url);

            Response response = mOkHttpClient.newCall(request).execute();

            if (response.isSuccessful()) {
                T t = GsonTools.changeGsonToBean(response.body().string(), clazz);
                return t;
            } else {

                return null;
            }

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}


