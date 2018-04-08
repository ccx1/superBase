package com.example.administrator.superbase.utils;

import android.content.BroadcastReceiver;

import com.example.administrator.superbase.Receiver.NetBroadcastReceiver;

/**
 * Created by v_chicunxiang on 2018/4/8.
 */

public class ReceiverF {
    private ReceiverF() {
    }
    private static NetBroadcastReceiver mNetBroadcastReceiver ;

    public static BroadcastReceiver getReceiver(){
        if (mNetBroadcastReceiver == null) {
            mNetBroadcastReceiver = new NetBroadcastReceiver();
        }
        return mNetBroadcastReceiver;

    }

    public static void onDestroy(){
        mNetBroadcastReceiver = null;
    }
}
