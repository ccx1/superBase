package com.example.administrator.viewutils;

import android.widget.TextView;

import com.example.administrator.viewutilslist.utils.common.FileUtils;

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

        String pdfContent = FileUtils.readPdfContent2String(this, "aa.pdf");

    }


}
