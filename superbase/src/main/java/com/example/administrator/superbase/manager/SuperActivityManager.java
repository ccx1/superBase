package com.example.administrator.superbase.manager;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/22.
 *
 * @史上最帅无敌创建者 ccx
 * @创建时间 2017/9/22 11:29
 */

public class SuperActivityManager {


    private static SuperActivityManager instance      = new SuperActivityManager();
    private        List<Activity>       activityStack = new ArrayList<>();
    ;

    public static SuperActivityManager getInstance() {
        return instance;
    }

    private SuperActivityManager() {
    }

    public void pushOneActivity(Activity activity) {
        activityStack.add(activity);
    }

    public void removeActivity(Activity activity) {
        if (activity != null && activityStack.contains(activity)) {
            activityStack.remove(activity);
            activity = null;
        }
    }

    Activity getLastActivty() {
        if (activityStack.size() == 0) {
            return null;
        }
        return activityStack.get(activityStack.size() - 1);
    }

    public void removeAllActivity() {
        while (activityStack.size() > 0) {
            Activity lastActivty = getLastActivty();
            if (lastActivty != null) {
                removeActivity(lastActivty);
            }
        }

    }

}
