package com.example.administrator.viewutilslist.manager;

import android.app.Activity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/9/22.
 *
 * @史上最帅无敌创建者 ccx
 * @创建时间 2017/9/22 11:29
 */

public class SuperActivityManager {


    private static final SuperActivityManager instance = new SuperActivityManager();
    private ArrayList<Activity> activityStack;

    public static SuperActivityManager getInstance() {
        return instance;
    }

    private SuperActivityManager() {
    }


    //传入一个activity添加到集合中
    public void pushOneActivity(Activity actvity) {
        if (activityStack == null) {
            activityStack = new ArrayList<Activity>();
        }
        activityStack.add(actvity);

    }



    //退出所有activity
    public void finishAllActivity() {
        if (activityStack != null) {
            while (activityStack.size() > 0) {
                Activity activity = getLastActivity();
                if (activity == null) {
                    break;
                }
                popOneActivity(activity);
            }
        }
    }

    //获取栈顶的activity，先进后出原则
    public Activity getLastActivity() {
        if (activityStack.size() == 0) {
            return null;
        }

        return activityStack.get(activityStack.size()-1);
    }
    //移除一个activity
    public void popOneActivity(Activity activity) {
        if (activityStack != null && activityStack.size() > 0) {
            if (activity != null) {
                activity.finish();
                activityStack.remove(activity);
                activity = null;
            }

        }
    }



}
