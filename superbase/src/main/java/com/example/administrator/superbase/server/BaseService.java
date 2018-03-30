package com.example.administrator.superbase.server;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.administrator.superbase.imInterface.IMListener;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by v_chicunxiang on 2018/3/30.
 *
 * @史上最帅无敌创建者 ccx
 * @创建时间 2018/3/30 11:23
 */

public class BaseService extends Service {

    private static WebSocketClient mWebSocketClient;
    private String data = "";
    private static BaseService sBaseService;
    private static boolean isStart = false;

    @Override
    public void onCreate() {
        super.onCreate();
        sBaseService = this;
        isStart = true;
    }

    /**
     * 使用bindService的回调
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        onBindIntent(intent);
        return new BaseBinder();
    }

    public static WebSocketClient getWebSocketClient() {
        return mWebSocketClient;
    }

    public static BaseService getInstance() {
        if (isStart) {
            return sBaseService;
        } else {
            return null;
        }
    }

    ;

    /**
     * 使用StartService的回调
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        onBindIntent(intent);
        return super.onStartCommand(intent, flags, startId);
    }

    private void onBindIntent(Intent intent) {
        try {
            data = intent.getStringExtra("url");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            System.setProperty("java.net.preferIPv6Addresses", "false");
            System.setProperty("java.net.preferIPv4Stack", "true");
            mWebSocketClient = new WebSocketClient(new URI(data), new Draft_6455(), null, 3000) {
                @Override
                public void onOpen(ServerHandshake serverHandshake) {
                    // 链接成功的回调
                    if (mOnSocketStateListener != null) {
                        mOnSocketStateListener.onConnect(getURI());
                    }
                }

                @Override
                public void onMessage(String s) {
                    // 收到消息的回调
                    if (mOnSocketStateListener != null) {
                        mOnSocketStateListener.onMessage(getURI(), s);
                    }
                }

                @Override
                public void onClose(int i, String s, boolean b) {
                    // 关闭的回调
                    if (mOnSocketStateListener != null) {
                        mOnSocketStateListener.onClose(getURI(), i, s);
                    }
                }

                @Override
                public void onError(Exception e) {
                    // 出错的回调
                    if (mOnSocketStateListener != null) {
                        mOnSocketStateListener.onError(e);
                    }
                }
            };

            mWebSocketClient.connect();

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private OnSocketStateListener mOnSocketStateListener;

    public void setOnSocketStateListener(OnSocketStateListener onSocketStateListener) {
        mOnSocketStateListener = onSocketStateListener;
    }

    public interface OnSocketStateListener extends IMListener {
        /**
         * @param uri 地址
         */
        void onConnect(URI uri);

        /**
         * @param uri 地址
         * @param msg 收到消息
         */
        void onMessage(URI uri, String msg);

        /**
         * @param uri  地址
         * @param code 状态码
         * @param msg  原因
         */
        void onClose(URI uri, int code, String msg);

        /**
         * @param e 异常原因
         */
        void onError(Exception e);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mWebSocketClient.isConnecting()) {
            mWebSocketClient.close();
            mWebSocketClient = null;
        }
    }


    public class BaseBinder extends Binder {

        public BaseService getService() {
            return BaseService.this;
        }
    }
}
