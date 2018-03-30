package com.example.administrator.superbase.server;

import android.content.Context;

import com.example.administrator.superbase.imManager.ChatManager;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by v_chicunxiang on 2018/3/30.
 *
 * @史上最帅无敌创建者 ccx
 * @创建时间 2018/3/30 12:57
 */

public class BaseIMClient {

    private static BaseIMClient sBaseIMClient;
    private Context mContext;
    private String mServerLocation;
    private boolean mIsDebug;
    private ChatManager mChatManager;
    private ExecutorService mainQueue = Executors.newSingleThreadExecutor();
    private ExecutorService sendQueue = Executors.newSingleThreadExecutor();


    private BaseIMClient() {
    }

    public void init(Context context,String serverLocation){
        init(context,serverLocation,false);
    }

    public void init(Context context,String serverLocation,boolean isDebug){
        this.mContext = context;
        this.mServerLocation = serverLocation;
        this.mIsDebug = isDebug;
        mChatManager = ChatManager.getInstance(this,BaseService.getWebSocketClient());

    }

    public static BaseIMClient getInstance() {
        if (sBaseIMClient == null) {
            sBaseIMClient = new BaseIMClient();
        }
        return sBaseIMClient;
    }


    public void executeOnMainQueue(Runnable var1) {
        this.mainQueue.submit(var1);
    }

    public void executeOnSendQueue(Runnable var1) {
        this.sendQueue.submit(var1);
    }


    public ChatManager chatManager(){
        return mChatManager;
    }



}
