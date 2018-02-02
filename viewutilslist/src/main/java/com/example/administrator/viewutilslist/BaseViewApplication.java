package com.example.administrator.viewutilslist;

import android.app.Activity;
import android.app.Application;

import com.example.administrator.viewutilslist.listerner.ActivityLifecycleListener;
import com.example.administrator.viewutilslist.manager.SuperActivityManager;
import com.example.administrator.viewutilslist.utils.Utils;

/**
 * Created by Administrator on 2017/10/11.
 *
 * @史上最帅无敌创建者 ccx
 * @创建时间 2017/10/11 19:22
 */

public class BaseViewApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        /**
         * 对activity的监听,做好统一管理,直接继承或者重写这个方法即可,这个方法只在api14以上才有
         */
        registerActivityLifecycleCallbacks(new ActivityLifecycleListener() {
            @Override
            public void onActivityStartedListerner(Activity activity) {
                SuperActivityManager.getInstance().pushOneActivity(activity);
            }

            @Override
            protected void onActivityDestroyedListerner(Activity activity) {
                SuperActivityManager.getInstance().popOneActivity(activity);
            }
        });

        Utils.getInstance().init(this);

    }
}
