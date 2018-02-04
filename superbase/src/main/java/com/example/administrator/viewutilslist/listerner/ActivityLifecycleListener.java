package com.example.administrator.viewutilslist.listerner;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/**
 * Created by Administrator on 2017/10/9.
 *  切换的监听
 * @史上最帅无敌创建者 ccx
 * @创建时间 2017/10/9 10:31
 */

public abstract class ActivityLifecycleListener implements Application.ActivityLifecycleCallbacks {
    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {
        onActivityStartedListerner(activity);
    }

    public abstract void onActivityStartedListerner(Activity activity);

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        onActivityDestroyedListerner(activity);
    }

    protected abstract void onActivityDestroyedListerner(Activity activity);
}
