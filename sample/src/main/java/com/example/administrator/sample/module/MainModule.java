package com.example.administrator.sample.module;

import com.example.administrator.sample.MainActivity;
import com.example.administrator.sample.presenter.MainPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by v_chicunxiang on 2018/2/7.
 *
 * @史上最帅无敌创建者 ccx
 * @创建时间 2018/2/7 14:58
 */
@Module
public class MainModule {

    private final MainActivity mMain;

    public MainModule(MainActivity mainActivity) {
        this.mMain = mainActivity;
    }

    @Provides
    MainPresenter provideMainPresenter() {
        return new MainPresenter(mMain);
    }

}
