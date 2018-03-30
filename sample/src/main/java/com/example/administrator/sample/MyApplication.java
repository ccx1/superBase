package com.example.administrator.sample;

import com.example.administrator.superbase.SuperBaseApplication;
import com.example.administrator.superbase.server.BaseIMClient;
import com.example.administrator.superbase.server.BaseService;

import java.net.URI;

/**
 * Created by Administrator on 2017/9/22.
 *
 * @史上最帅无敌创建者 ccx
 * @创建时间 2017/9/22 10:00
 */

public class MyApplication extends SuperBaseApplication {

    private static MyApplication appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
        BaseIMClient.getInstance().init(this,"");
        BaseIMClient.getInstance().chatManager().addMessageListener(new BaseService.OnSocketStateListener() {
            @Override
            public void onConnect(URI uri) {

            }

            @Override
            public void onMessage(URI uri, String msg) {

            }

            @Override
            public void onClose(URI uri, int code, String msg) {

            }

            @Override
            public void onError(Exception e) {

            }
        });
    }


    public static MyApplication getInstance() {
        return appContext;
    }
}
    