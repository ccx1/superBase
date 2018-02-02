package com.example.administrator.viewutilslist.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Administrator on 2017/9/1.
 *
 * @史上最帅无敌创建者 ccx
 * @创建时间 2017/9/1 18:21
 */

public class GetIpUtils {

    private static String ip;

    public static String getIPAddress(Context context) {
        NetworkInfo info = ((ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
            if (info.getType() == ConnectivityManager.TYPE_MOBILE) {//当前使用2G/3G/4G网络
                try {
                    //Enumeration<NetworkInterface> en=NetworkInterface.getNetworkInterfaces();
                    for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                        NetworkInterface intf = en.nextElement();
                        for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                            InetAddress inetAddress = enumIpAddr.nextElement();
                            if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                                return inetAddress.getHostAddress();
                            }
                        }
                    }
                } catch (SocketException e) {
                    e.printStackTrace();
                }

            } else if (info.getType() == ConnectivityManager.TYPE_WIFI) {//当前使用无线网络
                WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
                WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                String ipAddress = intIP2StringIP(wifiInfo.getIpAddress());//得到IPV4地址
                return ipAddress;
            }
        } else {
            //当前无网络连接,请在设置中打开网络
        }
        return null;
    }

    /**
     * 获取外网ip,基本上用这个
     *
     * @return
     */
    public static String GetNetIp() {
        URL infoUrl = null;
        final InputStream[] inStream = {null};
        try {
            infoUrl = new URL("https://cmyip.com/");
            URLConnection connection = infoUrl.openConnection();
            final HttpsURLConnection httpConnection = (HttpsURLConnection) connection;
            httpConnection.setRequestMethod("GET");
            httpConnection.setConnectTimeout(1000);
            final int[] responseCode = new int[1];
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        responseCode[0] = httpConnection.getResponseCode();
                        if (responseCode[0] == HttpURLConnection.HTTP_OK) {
                            inStream[0] = httpConnection.getInputStream();
                            BufferedReader reader = new BufferedReader(new InputStreamReader(inStream[0], "utf-8"));
                            StringBuilder strber = new StringBuilder();
                            String line = null;
                            while ((line = reader.readLine()) != null)
                                strber.append(line + "\n");
                            inStream[0].close();
                            String find_ip_tag = "My IP Address is ";
                            int start = strber.indexOf(find_ip_tag);
                            if (start > 0) {
                                int end = strber.indexOf(" ", start + find_ip_tag.length());
                                ip = strber.substring(start + find_ip_tag.length(), end);
                            }
                            Log.e("print-", ip);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ip;
    }

    /**
     * 将得到的int类型的IP转换为String类型
     *
     * @param ip
     * @return
     */
    public static String intIP2StringIP(int ip) {
        return (ip & 0xFF) + "." +
                ((ip >> 8) & 0xFF) + "." +
                ((ip >> 16) & 0xFF) + "." +
                (ip >> 24 & 0xFF);
    }

}
