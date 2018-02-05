package com.example.administrator.viewutils;

import android.widget.TextView;

import com.example.administrator.superbase.view.NetworkStateView;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class MainActivity extends BaseActivity {


    private NetworkStateView mNetworkStateView;
    private Emitter.Listener connect = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            System.out.println("链接成功");
        }
    };
    private Socket mSocket;

    @Override
    protected int BindLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        super.initView();
        mNetworkStateView = (NetworkStateView) $(R.id.nwsv);
        registerNetWorkStateView(mNetworkStateView);
        TextView view = (TextView) $(R.id.textview);
    }

    @Override
    protected void initData() {
        String CHAT_SERVER_URL = "http://localhost:8080/";
        try {
            mSocket = IO.socket(CHAT_SERVER_URL);
            mSocket.on(Socket.EVENT_CONNECT,connect);
            mSocket.connect();
            mSocket.emit("one push","aaaaa");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSocket.disconnect();
        mSocket.off(Socket.EVENT_CONNECT, connect);
    }
}
