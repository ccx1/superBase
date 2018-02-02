package com.example.administrator.viewutils;

import android.widget.TextView;

public class MainActivity extends BaseActivity  {

    @Override
    protected int BindLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        super.initView();
        TextView view = (TextView) $(R.id.textview);
        view.setText("aaa");

    }
}
