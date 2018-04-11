/*
 * MobileAppSDK android.
 *
 * Copyright (c) 2014 Giant Interactive Group, Inc. All rights reserved.
 */

package com.example.administrator.superbase.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.format.Formatter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.List;

/**
 * 获取设备的信息
 *
 * @author caiyuhao 2013年11月21日
 */
public final class ZTDeviceUtil {


    /**
     * 获取地理位置信息
     *
     * @param ctx Context
     * @return 位置信息
     */
    private static Location getLocation(Context ctx) {
        try {
            Location localLocation;
            LocationManager lm = (LocationManager) ctx
                    .getSystemService(Context.LOCATION_SERVICE);
            if (checkPermission(ctx, "android.permission.ACCESS_FINE_LOCATION")) {
                localLocation = lm
                        .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                if (localLocation != null) {
                    return localLocation;
                }
            }
            if (checkPermission(ctx,
                    "android.permission.ACCESS_COARSE_LOCATION")) {
                localLocation = lm
                        .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                if (localLocation != null) {
                    return localLocation;
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取本地IP地址
     *
     * @return 本地IP地址
     */
    public static String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface
                    .getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface inferf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = inferf
                        .getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()
                            && (inetAddress instanceof Inet4Address)) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 检查网络是否可用
     *
     * @param ctx Context
     * @return 网络是否可用
     */
    public static boolean netIsAvailable(Context ctx) {
        ConnectivityManager cm = (ConnectivityManager) ctx
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info == null) {
            return false;
        }
        NetworkInfo.State state = info.getState();
        return state == NetworkInfo.State.CONNECTED;
    }

    /**
     * 检查权限
     *
     * @param ctx      Context
     * @param permName 待检查的权限名称
     * @return 返回是否具有该权限
     */
    public static boolean checkPermission(Context ctx, String permName) {
        PackageManager packageManager = ctx.getPackageManager();
        boolean        havePermission = false;
        if (null != packageManager) {
            try {
                havePermission = packageManager.checkPermission(permName,
                        ctx.getPackageName()) == PackageManager.PERMISSION_GRANTED ? true
                        : false;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return havePermission;
    }

    /**
     * 判断应用是否在前台运行
     *
     * @param ctx Context
     * @return true表示在前台运行，false表示在后台运行
     */
    public static boolean isAppOnForeground(Context ctx) {
        Context         applicationContext = ctx.getApplicationContext();
        ActivityManager activityManager    = null;
        String          packageName        = null;
        if (applicationContext != null) {
            activityManager = (ActivityManager) applicationContext
                    .getSystemService(Context.ACTIVITY_SERVICE);
            packageName = applicationContext.getPackageName();
        }
        if (null != activityManager) {
            List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager
                    .getRunningAppProcesses();
            if (null != appProcesses) {
                for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
                    if (appProcess.processName.equals(packageName)
                            && appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 获取总内存的大小
     *
     * @param ctx
     * @return
     */
    public static String getTotalMemory(Context ctx) {

        String   str1          = "/proc/meminfo";
        String   str2;
        String[] arrayofString;
        long     inital_memory = 0;
        try {
            FileReader localFileReader = new FileReader(str1);
            BufferedReader localBufferReader = new BufferedReader(
                    localFileReader, 8192);
            str2 = localBufferReader.readLine();
            arrayofString = str2.split("\\s+");
            for (String num : arrayofString) {

            }
            inital_memory = Integer.valueOf(arrayofString[1]).intValue() * 1024;
            localBufferReader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return Formatter.formatFileSize(ctx, inital_memory);
    }

}
