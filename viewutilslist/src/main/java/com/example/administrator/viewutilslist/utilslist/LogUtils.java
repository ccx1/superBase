package com.example.administrator.viewutilslist.utilslist;

import android.util.Log;

/**
 * Created by Administrator on 2017/9/4.
 *  日志管理类
 * @史上最帅无敌创建者 ccx
 * @创建时间 2017/9/4 14:25
 */

public class LogUtils {

    private LogUtils() {
    }

    public static        boolean isDebug = true;
    private static final String  TAG     = "tag";

    public static void i(String msg){
        if (isDebug) {
            Log.i(TAG, msg);
        }
    }
    public static void d(String msg){
        if (isDebug) {
            Log.d(TAG, msg);
        }
    }
    public static void e(String msg){
        if (isDebug) {
            Log.e(TAG, msg);
        }
    }
    public static void v(String msg){
        if (isDebug) {
            Log.v(TAG, msg);
        }
    }

    public static void i(String tag, String msg){
        if (isDebug) {
            Log.i(tag, msg);
        }
    }
    public static void d(String tag, String msg){
        if (isDebug) {
            Log.d(tag, msg);
        }
    }
    public static void e(String tag, String msg){
        if (isDebug) {
            Log.i(tag, msg);
        }
    }
    public static void v(String tag, String msg){
        if (isDebug) {
            Log.v(tag, msg);
        }
    }




}
