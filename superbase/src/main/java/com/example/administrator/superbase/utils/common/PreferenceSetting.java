package com.example.administrator.superbase.utils.common;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.administrator.superbase.utils.encode.MD5;

/**
 * Created by v_chicunxiang on 2018/6/1.
 */

public class PreferenceSetting {
    private static final String FILE_NAME = "bdvrsetting";
    private static final String VTLN_SECRET_KEY = "BDVRVtln*!Secret";
    private static final String VTLN_KEY = "vtln";
    private static final int VTLN_LIMIT = 255;
    private static final String FILE_NAME_MD5 = MD5.md5("bdvrsetting");

    public static String getString(Context var0, String var1, String var2) {
        SharedPreferences var3 = var0.getSharedPreferences(FILE_NAME_MD5, 0);
        return var3.getString(var1, var2);
    }

    public static void setString(Context var0, String var1, String var2) {
        SharedPreferences var3 = var0.getSharedPreferences(FILE_NAME_MD5, 0);
        var3.edit().putString(var1, var2).commit();
    }

    public static boolean getBoolean(Context var0, String var1, boolean var2) {
        SharedPreferences var3 = var0.getSharedPreferences(FILE_NAME_MD5, 0);
        return var3.getBoolean(var1, var2);
    }

    public static void setBoolean(Context var0, String var1, boolean var2) {
        SharedPreferences var3 = var0.getSharedPreferences(FILE_NAME_MD5, 0);
        var3.edit().putBoolean(var1, var2).commit();
    }

    public static void removeString(Context var0, String var1) {
        SharedPreferences var2 = var0.getSharedPreferences(FILE_NAME_MD5, 0);
        var2.edit().remove(var1).commit();
    }

    public static boolean setVtlnWithCheckSum(Context var0, int var1) {
        if(var1 >= 0 && var1 <= 255) {
            String var2 = MD5.md5((var1 + "BDVRVtln*!Secret"));
            setString(var0, "vtln", var1 + "||" + var2);
            return true;
        } else {
            return false;
        }
    }

    public static int getVtlnWithCheckSum(Context var0) {
        String var1 = getString(var0, "vtln", "");
        int var2 = -1;
        if(var1.indexOf("||") != -1) {
            String[] var3 = var1.split("\\|\\|");
            if(var3.length >= 2) {
                String var4 = var3[1];
                String var5 = var3[0];
                String var6 = MD5.md5((var5 + "BDVRVtln*!Secret"));
                if(var6.equals(var4)) {
                    var2 = Integer.parseInt(var5);
                }
            }
        }

        return var2;
    }

    private PreferenceSetting() {
    }
}
