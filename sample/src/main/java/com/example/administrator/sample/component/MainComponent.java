package com.example.administrator.sample.component;

import com.example.administrator.sample.MainActivity;
import com.example.administrator.sample.module.HomeModule;
import com.example.administrator.sample.module.MainModule;
import com.example.administrator.sample.module.MainModule2;

import dagger.Component;

/**
 * Created by v_chicunxiang on 2018/2/7.
 *
 * @史上最帅无敌创建者 ccx
 * @创建时间 2018/2/7 14:58
 */
@Component(modules = {MainModule.class, MainModule2.class, HomeModule.class})
public interface MainComponent {
    void inject(MainActivity mainActivity);
}
