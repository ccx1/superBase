package com.example.administrator.viewutils;

import com.example.administrator.viewutilslist.SuperBaseActivity;
import com.example.administrator.viewutilslist.utils.LogUtils;
import com.example.administrator.viewutilslist.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by v_chicunxiang on 2018/2/2.
 *
 * @史上最帅无敌创建者 ccx
 * @创建时间 2018/2/2 16:48
 */

public class BaseActivity extends SuperBaseActivity {
    @Override
    protected int BindLayout() {
        return 0;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initData() {

    }

    protected void showToast(String s){
        ToastUtils.ToastShow(s);
    }


    protected void showLongToast(String s){
        ToastUtils.ToastLongTimeShow(s);
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtils.d("onStart");
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtils.d("onResume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.d("onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtils.d("onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtils.d("onStop");
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtils.d("onRestart");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(Object event) {
        onMessageEvent(event);
    }

    protected void onMessageEvent(Object event) {

    }

}
