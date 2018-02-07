package com.example.administrator.sample.module;

import com.example.administrator.sample.MainActivity;
import com.example.administrator.sample.presenter.HomePresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by v_chicunxiang on 2018/2/7.
 *
 * @史上最帅无敌创建者 ccx
 * @创建时间 2018/2/7 17:22
 */
@Module
public class HomeModule {

    private final MainActivity main;

    public HomeModule(MainActivity mainActivity) {
        this.main = mainActivity;
    }

    @Provides
    HomePresenter provideHomePresenter(){
        return new HomePresenter(main);
    }
}
