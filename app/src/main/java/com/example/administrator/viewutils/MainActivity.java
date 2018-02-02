package com.example.administrator.viewutils;

import android.widget.TextView;

import com.example.administrator.viewutilslist.view.NetworkStateView;

public class MainActivity extends BaseActivity {


    private NetworkStateView mMNetworkStateView;

    @Override
    protected int BindLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        super.initView();


        mMNetworkStateView = (NetworkStateView) $(R.id.nwsv);
        mMNetworkStateView.showLoading();
        TextView view = (TextView) $(R.id.textview);
    }


    @Override
    public void onNetWorkWifi() {
        super.onNetWorkWifi();
        mMNetworkStateView.showSuccess();
    }

    @Override
    public void onNetWorkMobile() {
        super.onNetWorkMobile();
        mMNetworkStateView.showSuccess();
    }

    @Override
    public void onNetWorkEmpty() {
        super.onNetWorkEmpty();
        mMNetworkStateView.showEmpty();
    }

    @Override
    public void onNetWorkNone() {
        super.onNetWorkNone();
        mMNetworkStateView.showNoNet();
    }


    @Override
    public void onNetWorkNoNet() {
        super.onNetWorkNoNet();
        mMNetworkStateView.showSuccess();
    }


}
