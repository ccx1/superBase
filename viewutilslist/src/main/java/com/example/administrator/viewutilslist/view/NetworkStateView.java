package com.example.administrator.viewutilslist.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by v_chicunxiang on 2018/2/2.
 *  网络判断自定义View
 * @史上最帅无敌创建者 ccx
 * @创建时间 2018/2/2 18:32
 */

public class NetworkStateView extends FrameLayout {
    public NetworkStateView(@NonNull Context context) {
        this(context,null);
    }

    public NetworkStateView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public NetworkStateView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {



    }
}
