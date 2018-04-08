package com.example.administrator.superbase.manager;

import com.example.administrator.superbase.SuperBaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/22.
 *
 * @史上最帅无敌创建者 ccx
 * @创建时间 2017/9/22 11:29
 */

public class SuperActivityManager {


    private static SuperActivityManager    instance      = new SuperActivityManager();
    private        List<SuperBaseActivity> activityStack = new ArrayList<>();;

    public static SuperActivityManager getInstance() {
        return instance;
    }

    private SuperActivityManager() {
    }

    public void openActivity(SuperBaseActivity activity){
        activityStack.add(activity);
    }

    public void removeActivity(SuperBaseActivity activity){
        if (activity != null && activityStack.contains(activity)) {
            activityStack.remove(activity);
            activity = null;
        }
    }

    public void removeAllActivity(){
        if (activityStack.size() > 0) {
            for (int i = activityStack.size()-1; i >= 0; i--) {
                SuperBaseActivity superBaseActivity = activityStack.get(i);
                activityStack.remove(superBaseActivity);
                if (superBaseActivity != null) {
                    superBaseActivity = null;
                }
            }
        }
    }

}
