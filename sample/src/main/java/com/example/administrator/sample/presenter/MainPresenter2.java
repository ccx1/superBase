package com.example.administrator.sample.presenter;

import com.example.administrator.sample.MainActivity;
import com.example.administrator.superbase.utils.common.LogUtil;

/**
 * Created by v_chicunxiang on 2018/2/7.
 *
 * @史上最帅无敌创建者 ccx
 * @创建时间 2018/2/7 15:31
 */

public class MainPresenter2 {
    private final MainActivity main;

    public MainPresenter2(MainActivity main) {
        this.main = main;
    }

    public void aaa(){
        LogUtil.d("我呗调用了");
    }
}
