package com.example.administrator.viewutils;

import android.widget.TextView;

import com.example.administrator.viewutilslist.view.NetworkStateView;

public class MainActivity extends BaseActivity {


    private NetworkStateView mNetworkStateView;

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
}
