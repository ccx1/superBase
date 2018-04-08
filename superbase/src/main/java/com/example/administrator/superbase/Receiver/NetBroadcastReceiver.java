package com.example.administrator.superbase.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import com.example.administrator.superbase.BaseConstant;
import com.example.administrator.superbase.utils.NetUtils;

/**
 * Created by Administrator on 2018/2/2.
 *
 * @史上最帅无敌创建者 ccx
 * @创建时间 2018/2/2 22:59
 */

public class NetBroadcastReceiver extends BroadcastReceiver {

    private onNetWorkStateEvent mOnNetWorkStateEvent;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION) || intent.getAction().equals(BaseConstant.ACTION_ONE_SEND)) {
            int netWorkState = NetUtils.getNetWorkState(context);
            // 接口回调传过去状态的类型
            mOnNetWorkStateEvent.onNetStateEvent(netWorkState);
        }

    }


    public void setOnNetWorkStateEvent(onNetWorkStateEvent onNetWorkStateEvent) {
        mOnNetWorkStateEvent = onNetWorkStateEvent;
    }

    public interface onNetWorkStateEvent{
        void onNetStateEvent(int netWorkState);
    }
}
