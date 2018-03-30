package com.example.administrator.sample;

import android.widget.TextView;

import com.example.administrator.superbase.view.NetworkStateView;

public class MainActivity extends BaseActivity {

    private TextView mView;
    private NetworkStateView mNetworkStateView;


    @Override
    protected int BindLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        super.initView();
        mNetworkStateView = (NetworkStateView) $(R.id.nwsv);
        super.registerNetWorkStateView(mNetworkStateView);
        mView = (TextView) $(R.id.textview);


    }
}
