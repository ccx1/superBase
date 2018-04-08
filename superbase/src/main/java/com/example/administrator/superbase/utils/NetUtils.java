package com.example.administrator.superbase.utils;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;

import com.example.administrator.superbase.utils.common.LogUtil;


/**
 * Created by v_chicunxiang on 2018/2/2.
 *
 * @史上最帅无敌创建者 ccx
 * @创建时间 2018/2/2 15:05
 */

public class NetUtils {

    private static final String TAG = "net";
    private static final int LOW_SPEED_UPLOAD_BUF_SIZE = 1024;
    private static final int HIGH_SPEED_UPLOAD_BUF_SIZE = 10240;
    private static final int MAX_SPEED_UPLOAD_BUF_SIZE = 102400;
    private static final int LOW_SPEED_DOWNLOAD_BUF_SIZE = 2024;
    private static final int HIGH_SPEED_DOWNLOAD_BUF_SIZE = 30720;
    private static final int MAX_SPEED_DOWNLOAD_BUF_SIZE = 102400;


    /**
     * 没有连接网络
     */
    public static final int NETWORK_NONE   = -1;
    /**
     * 有移动网络,但是没话费的情况
     */
    public static final int NETWORK_NONET  = -2;
    /**
     * 无法获知网络状态
     */
    public static final int NETWORK_EMPTY  = -3;

    /**
     * 移动网络
     */
    public static final int NETWORK_MOBILE = 0;
    /**
     * 无线网络
     */
    public static final int NETWORK_WIFI   = 1;
    private static NetUtils sInstance;
    private static Context  mContext;


    private NetUtils() {
    }

    public static boolean hasNetwork() {
        if(mContext != null) {
            @SuppressLint("WrongConstant") ConnectivityManager var1 = (ConnectivityManager)mContext.getSystemService("connectivity");
            NetworkInfo var2 = var1.getActiveNetworkInfo();
            return var2 != null?var2.isAvailable():false;
        } else {
            return false;
        }
    }

    @TargetApi(13)
    public static boolean hasDataConnection() {
        try {
            @SuppressLint("WrongConstant") ConnectivityManager var1 = (ConnectivityManager)mContext.getSystemService("connectivity");
            NetworkInfo var2 = var1.getNetworkInfo(1);
            if(var2 != null && var2.isAvailable() && var2.isConnected()) {
                LogUtil.d("net", "has wifi connection");
                return true;
            } else {
                var2 = var1.getNetworkInfo(0);
                if(var2 != null && var2.isAvailable() && var2.isConnected()) {
                    LogUtil.d("net", "has mobile connection");
                    return true;
                } else {
                    if(Build.VERSION.SDK_INT >= 13) {
                        var2 = var1.getNetworkInfo(9);
                        if(var2 != null && var2.isAvailable() && var2.isConnected()) {
                            LogUtil.d("net", "has ethernet connection");
                            return true;
                        }
                    }

                    LogUtil.d("net", "no data connection");
                    return false;
                }
            }
        } catch (Exception var3) {
            return false;
        }
    }

    @Deprecated
    public static boolean isWifiConnection() {
        return isWifiConnected();
    }

    public static boolean isWifiConnected() {
        @SuppressLint("WrongConstant") ConnectivityManager var1 = (ConnectivityManager)mContext.getSystemService("connectivity");
        NetworkInfo var2 = var1.getNetworkInfo(1);
        if(var2 != null && var2.isAvailable() && var2.isConnected()) {
            LogUtil.d("net", "wifi is connected");
            return true;
        } else {
            return false;
        }
    }

    @Deprecated
    public static boolean isMobileConnection() {
        return isMobileConnected();
    }

    public static boolean isMobileConnected() {
        @SuppressLint("WrongConstant") ConnectivityManager var1 = (ConnectivityManager)mContext.getSystemService("connectivity");
        NetworkInfo var2 = var1.getNetworkInfo(0);
        if(var2 != null && var2.isAvailable() && var2.isConnected()) {
            LogUtil.d("net", "mobile is connected");
            return true;
        } else {
            return false;
        }
    }

    @Deprecated
    public static boolean isEthernetConnection() {
        return isEthernetConnected();
    }

    public static boolean isEthernetConnected() {
        if(Build.VERSION.SDK_INT >= 13) {
            @SuppressLint("WrongConstant") ConnectivityManager var1 = (ConnectivityManager)mContext.getSystemService("connectivity");
            NetworkInfo var2 = var1.getNetworkInfo(9);
            if(var2 != null && var2.isAvailable() && var2.isConnected()) {
                LogUtil.d("net", "ethernet is connected");
                return true;
            }
        }

        return false;
    }

    public static String getWiFiSSID() {
        @SuppressLint("WrongConstant") WifiManager var1 = (WifiManager)mContext.getSystemService("wifi");
        String var2 = "";

        try {
            WifiInfo var3 = var1.getConnectionInfo();
            var2 = var3.getSSID();
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        return var2;
    }

    public static int getUploadBufSize() {
        @SuppressLint("WrongConstant") ConnectivityManager var1 = (ConnectivityManager)mContext.getSystemService("connectivity");
        NetworkInfo var2 = var1.getActiveNetworkInfo();
        return var2 != null && var2.getType() == 1?102400:(Build.VERSION.SDK_INT >= 13 && var2 != null && var2.getType() == 9?102400:(var2 == null && isConnectionFast(var2.getType(), var2.getSubtype())?10240:1024));
    }

    public static int getDownloadBufSize() {
        @SuppressLint("WrongConstant") ConnectivityManager var1 = (ConnectivityManager)mContext.getSystemService("connectivity");
        NetworkInfo var2 = var1.getActiveNetworkInfo();
        return var2 != null && var2.getType() == 1?102400:(Build.VERSION.SDK_INT >= 13 && var2 != null && var2.getType() == 9?102400:(var2 == null && isConnectionFast(var2.getType(), var2.getSubtype())?30720:2024));
    }

    private static boolean isConnectionFast(int var0, int var1) {
        if(var0 == 1) {
            return true;
        } else if(Build.VERSION.SDK_INT >= 13 && var0 == 9) {
            return true;
        } else {
            if(var0 == 0) {
                switch(var1) {
                    case 1:
                        return false;
                    case 2:
                        return false;
                    case 3:
                        return true;
                    case 4:
                        return false;
                    case 5:
                        return true;
                    case 6:
                        return true;
                    case 7:
                        return false;
                    case 8:
                        return true;
                    case 9:
                        return true;
                    case 10:
                        return true;
                    default:
                        if(Build.VERSION.SDK_INT >= 11 && (var1 == 14 || var1 == 13)) {
                            return true;
                        }

                        if(Build.VERSION.SDK_INT >= 9 && var1 == 12) {
                            return true;
                        }

                        if(Build.VERSION.SDK_INT >= 8 && var1 == 11) {
                            return false;
                        }
                }
            }

            return false;
        }
    }

    public static String getNetworkType() {
        @SuppressLint("WrongConstant") ConnectivityManager var1 = (ConnectivityManager)mContext.getSystemService("connectivity");
        NetworkInfo var2 = var1.getActiveNetworkInfo();
        if(var2 != null && var2.isAvailable()) {
            int var3 = var2.getType();
            if(Build.VERSION.SDK_INT >= 13 && var3 == 9) {
                return "ETHERNET";
            } else if(var3 == 1) {
                return "WIFI";
            } else {
                @SuppressLint("WrongConstant") TelephonyManager var4 = (TelephonyManager)mContext.getSystemService("phone");
                switch(var4.getNetworkType()) {
                    case 1:
                    case 2:
                    case 4:
                    case 7:
                    case 11:
                        return "2G";
                    case 3:
                    case 5:
                    case 6:
                    case 8:
                    case 9:
                    case 10:
                    case 12:
                    case 14:
                    case 15:
                        return "3G";
                    case 13:
                        return "4G";
                    default:
                        return "unkonw network";
                }
            }
        } else {
            return "no network";
        }
    }

    public static int getNetWorkState(Context context) {
        // 得到连接管理器对象
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetworkInfo = connectivityManager
                .getActiveNetworkInfo();

        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            if (activeNetworkInfo.getType() == (ConnectivityManager.TYPE_WIFI)) {

                return NETWORK_WIFI;
            } else if (activeNetworkInfo.getType() == (ConnectivityManager.TYPE_MOBILE)) {

                return NETWORK_MOBILE;
            }else {
                return NETWORK_EMPTY;
            }

        } else {
            return NETWORK_NONE;
        }
    }

    public void init(Context context) {
        mContext = context;
    }

    public static NetUtils getInstance() {
        if (sInstance == null) {
            sInstance = new NetUtils();
        }
        return sInstance;
    }
}
