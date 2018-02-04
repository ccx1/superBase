package com.example.administrator.viewutils;

import com.example.administrator.viewutilslist.SuperBaseActivity;
import com.example.administrator.viewutilslist.utils.common.LogUtil;

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


    @Override
    protected void onStart() {
        super.onStart();
        LogUtil.d("onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.d("onResume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.d("onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtil.d("onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtil.d("onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtil.d("onRestart");
    }


}
