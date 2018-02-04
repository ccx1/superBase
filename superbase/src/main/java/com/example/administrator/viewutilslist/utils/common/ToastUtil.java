package com.example.administrator.viewutilslist.utils.common;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;


public class ToastUtil {

    private static Toast   toast;
    @SuppressLint("StaticFieldLeak")
    private static Context mContext;

    private ToastUtil(){

    }

    public static void init(Context context){
        mContext = context;
    }

    @SuppressLint("ShowToast")
    public static void ToastShow(String msg) {
        if (toast == null) {
            toast = Toast.makeText(mContext, msg, Toast.LENGTH_SHORT);
        }
        toast.setText(msg);
        toast.show();

    }

    @SuppressLint("ShowToast")
    public static void ToastLongTimeShow(String msg) {
        if (toast == null) {
            toast = Toast.makeText(mContext, msg, Toast.LENGTH_LONG);
        }
        toast.setText(msg);
        toast.show();
    }
}