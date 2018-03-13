package com.example.administrator.superbase.utils.common;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;


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
    private static Context           mContext;
    private static String[]          mNames;


    public static void init(Context context, String... names) {
        mContext = context;
        mNames = names;
    }
    /**
     * reset Names
     */
    public static void setmNames(String... mNames) {
        SharedPreferencesUtils.mNames = mNames;
    }

    public static void put(int namesKey, String key, Object obj) {

        sp = mContext.getSharedPreferences(mNames[namesKey], Context.MODE_PRIVATE);

        SharedPreferences.Editor edit = sp.edit();

        if (obj instanceof String) {
            edit.putString(key, (String) obj);
        } else if (obj instanceof Integer) {
            edit.putInt(key, (Integer) obj);
        } else if (obj instanceof Float) {
            edit.putFloat(key, (Float) obj);
        } else if (obj instanceof Long) {
            edit.putLong(key, (Long) obj);
        } else if (obj instanceof Boolean) {
            edit.putBoolean(key, (Boolean) obj);
        } else {
            edit.putString(key, obj.toString());
        }

        edit.apply();

    }

    public static String get(int namesKey, String key, String defValue) {

        sp = mContext.getSharedPreferences(mNames[namesKey], Context.MODE_PRIVATE);

        return sp.getString(key, defValue);
    }

    public static int get(int namesKey, String key, int defValue) {

        sp = mContext.getSharedPreferences(mNames[namesKey], Context.MODE_PRIVATE);

        return sp.getInt(key, defValue);
    }

    public static boolean get(int namesKey, String key, boolean defValue) {

        sp = mContext.getSharedPreferences(mNames[namesKey], Context.MODE_PRIVATE);

        return sp.getBoolean(key, defValue);
    }

    public static float get(int namesKey, String key, float defValue) {

        sp = mContext.getSharedPreferences(mNames[namesKey], Context.MODE_PRIVATE);

        return sp.getFloat(key, defValue);
    }

    public static long get(int namesKey, String key, long defValue) {

        sp = mContext.getSharedPreferences(mNames[namesKey], Context.MODE_PRIVATE);

        return sp.getLong(key, defValue);
    }

    public static Set<String> get(int namesKey, String key, Set<String> defValue) {

        sp = mContext.getSharedPreferences(mNames[namesKey], Context.MODE_PRIVATE);
        return sp.getStringSet(key, defValue);
    }


    public static void clear(int namesKey) {
        sp = mContext.getSharedPreferences(mNames[namesKey], Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.clear();
        edit.apply();
    }


    public static void clearAll() {
        for (String name : mNames) {
            sp = mContext.getSharedPreferences(name, Context.MODE_PRIVATE);
            SharedPreferences.Editor edit = sp.edit();
            edit.clear();
            edit.apply();
        }
    }


}