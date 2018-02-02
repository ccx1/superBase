package com.example.administrator.viewutilslist.utils;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * Created by Administrator on 2017-06-02.
 *
 * @创建者 admin
 * @描述
 * @创建时间 2017-06-02 9:03
 * @更新描述
 */

public class SharedPreferencesUtils {

    private static SharedPreferences sp;
    private static Context mContext;
    private static String[] mNames;


    public static void init(Context context,String... names) {
        mContext = context;
        mNames = names;
    }

    /**
     * reset Names
     */
    public static void setmNames(String... mNames) {
        SharedPreferencesUtils.mNames = mNames;
    }

    public static void saveBoolean(int namesKey, String key, boolean value) {

        if (sp == null) {
            sp = mContext.getSharedPreferences(mNames[namesKey], Context.MODE_PRIVATE);
        }
        sp.edit().putBoolean(key, value).apply();

    }


    public static boolean getBoolean(int namesKey,String key, boolean defValue) {


        if (sp == null) {
            sp = mContext.getSharedPreferences(mNames[namesKey], Context.MODE_PRIVATE);
        }


        return sp.getBoolean(key, defValue);

    }


    public static void saveString(int namesKey,String key, String value) {


        if (sp == null) {
            sp = mContext.getSharedPreferences(mNames[namesKey], Context.MODE_PRIVATE);
        }
        sp.edit().putString(key, value).apply();

    }


    public static String getString(int namesKey,String key, String defValue) {

        if (sp == null) {
            sp = mContext.getSharedPreferences(mNames[namesKey], Context.MODE_PRIVATE);
        }

        return sp.getString(key, defValue);

    }
}