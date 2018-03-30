package com.example.administrator.superbase.imManager;

import com.example.administrator.superbase.imInterface.IMListener;
import com.example.administrator.superbase.server.BaseIMClient;
import com.example.administrator.superbase.server.BaseService;

import org.java_websocket.client.WebSocketClient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by v_chicunxiang on 2018/3/30.
 *
 * @史上最帅无敌创建者 ccx
 * @创建时间 2018/3/30 13:06
 */

public class ChatManager {

    private static ChatManager     sChatManager;
    private        BaseIMClient    mIMClient;
    private        WebSocketClient mWebSocketClient;
    private List<IMListener> messageListeners = new ArrayList<>();

    private ChatManager(BaseIMClient baseIMClient, WebSocketClient webSocketClient) {
        this.mIMClient = baseIMClient;
        this.mWebSocketClient = webSocketClient;
    }

    public static ChatManager getInstance(BaseIMClient baseIMClient, WebSocketClient webSocketClient) {
        if (sChatManager == null) {
            sChatManager = new ChatManager(baseIMClient, webSocketClient);
        }
        return sChatManager;
    }

    public void addMessageListener(IMListener listener) {
        if (!messageListeners.contains(listener)) {
            messageListeners.add(listener);
            BaseService instance = BaseService.getInstance();
            if (instance != null) {
                instance.setOnSocketStateListener((BaseService.OnSocketStateListener) messageListeners);
            }

        }
    }

    public void removeMessageListener(IMListener listener) {
        if (messageListeners.contains(listener)) {
            messageListeners.remove(listener);
        }
    }

    private boolean judgeWebSocket() {
        return mWebSocketClient != null && mWebSocketClient.isConnecting();
    }

    public void sendMessage(final IMMessage imMessage) {
        this.mIMClient.executeOnSendQueue(new Runnable() {
            @Override
            public void run() {
                if (judgeWebSocket() && imMessage.getType().equals(ChatManager.Type.TEXT)) {
                    mWebSocketClient.send(imMessage.getMessage());
                }
            }
        });

    }

    public void sendImage(final IMMessage imMessage) {
        this.mIMClient.executeOnSendQueue(new Runnable() {
            @Override
            public void run() {
                if (judgeWebSocket() && imMessage.getType().equals(ChatManager.Type.IMAGE)) {
                    mWebSocketClient.send(imMessage.getMessage());
                }
            }
        });

    }


    public void sendFile(final IMMessage imMessage) {
        this.mIMClient.executeOnSendQueue(new Runnable() {
            @Override
            public void run() {
                if (judgeWebSocket() && imMessage.getType().equals(Type.FILE)) {
                    mWebSocketClient.send(imMessage.getMessage());
                }
            }
        });
    }

    public void sendVoiced(final IMMessage imMessage) {
        this.mIMClient.executeOnSendQueue(new Runnable() {
            @Override
            public void run() {
                if (judgeWebSocket() && imMessage.getType().equals(Type.VOICE)) {
                    mWebSocketClient.send(imMessage.getMessage());
                }
            }
        });
    }

    static class Type {
        static final Object TEXT  = "text";
        static final String IMAGE = "image";
        static final String FILE  = "file";
        static final String VOICE = "voice";
    }
}
