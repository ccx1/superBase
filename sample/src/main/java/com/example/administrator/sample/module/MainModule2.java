package com.example.administrator.sample.module;

import com.example.administrator.sample.MainActivity;
import com.example.administrator.sample.presenter.MainPresenter2;

import dagger.Module;
import dagger.Provides;

/**
 * Created by v_chicunxiang on 2018/2/7.
 *
 * @史上最帅无敌创建者 ccx
 * @创建时间 2018/2/7 14:58
 */
@Module
public class MainModule2 {

    private final MainActivity mMain;

    public MainModule2(MainActivity mainActivity) {
        this.mMain = mainActivity;
    }

    @Provides
    MainPresenter2 provideMainPresenter() {
        return new MainPresenter2(mMain);
    }

}
