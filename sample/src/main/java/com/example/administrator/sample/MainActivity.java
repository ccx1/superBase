package com.example.administrator.sample;

import android.widget.TextView;

import com.example.administrator.sample.component.DaggerMainComponent;
import com.example.administrator.sample.module.HomeModule;
import com.example.administrator.sample.module.MainModule;
import com.example.administrator.sample.module.MainModule2;
import com.example.administrator.sample.presenter.HomePresenter;
import com.example.administrator.sample.presenter.MainPresenter;
import com.example.administrator.sample.presenter.MainPresenter2;
import com.example.administrator.superbase.view.NetworkStateView;

import javax.inject.Inject;

public class MainActivity extends BaseActivity {

    private TextView mView;
    private NetworkStateView mNetworkStateView;

    @Inject
    MainPresenter mMainPresenter;

    @Inject
    MainPresenter2 mMainPresenter2;

    @Inject
    HomePresenter mHomePresenter;

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
        DaggerMainComponent.builder()
                .homeModule(new HomeModule(this))
                .mainModule(new MainModule(MainActivity.this))
                .mainModule2(new MainModule2(this))
                .build().inject(this);
    }
}
