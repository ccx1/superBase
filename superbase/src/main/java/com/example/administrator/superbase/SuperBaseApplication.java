package com.example.administrator.superbase;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.example.administrator.superbase.listerner.ActivityLifecycleListener;
import com.example.administrator.superbase.manager.SuperActivityManager;
import com.example.administrator.superbase.utils.Utils;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2017/10/11.
 *
 * @史上最帅无敌创建者 ccx
 * @创建时间 2017/10/11 19:22
 */

public class SuperBaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // 对activity的监听,做好统一管理,直接继承或者重写这个方法即可,这个方法只在api14以上才有
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
        removal();


    }

    private void removal(){
        int pid = android.os.Process.myPid();
        String processAppName = getAppName(pid);
        if (processAppName == null ||!processAppName.equalsIgnoreCase(this.getPackageName())) {
            Log.e(SuperBaseApplication.class.getName(), "enter the service process!");
            // 则此application::onCreate 是被service 调用的，直接返回
            return;
        }
    }

    private String getAppName(int pID) {
        String processName = null;
        ActivityManager am = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
        List l = am.getRunningAppProcesses();
        Iterator i = l.iterator();
        PackageManager pm = this.getPackageManager();
        while (i.hasNext()) {
            ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) (i.next());
            try {
                if (info.pid == pID) {
                    processName = info.processName;
                    return processName;
                }
            } catch (Exception e) {
            }
        }
        return processName;
    }

    /**
     * 分包工具,防止64K加载
     * @param base
     */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
