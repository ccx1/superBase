package com.example.administrator.viewutils;

import com.example.administrator.viewutilslist.SuperBaseApplication;

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


    }


    public static MyApplication getInstance() {
        return appContext;
    }
}
    