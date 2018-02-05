package com.example.administrator.viewutils;

import android.widget.TextView;

import com.example.administrator.superbase.view.NetworkStateView;

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


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
