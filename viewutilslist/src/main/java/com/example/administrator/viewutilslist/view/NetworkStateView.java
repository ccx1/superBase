package com.example.administrator.viewutilslist.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.example.administrator.viewutilslist.R;

/**
 * Created by v_chicunxiang on 2018/2/2.
 *  网络判断自定义View
 * @史上最帅无敌创建者 ccx
 * @创建时间 2018/2/2 18:32
 */

public class NetworkStateView extends FrameLayout {

    //当前的加载状态
    private int mCurrentState;
    private static final int STATE_SUCCESS = 0;
    private static final int STATE_LOADING = 1;
    private static final int STATE_NETWORK_ERROR = 2;
    private static final int STATE_NO_NETWORK = 3;
    private static final int STATE_EMPTY = 4;

    public NetworkStateView(@NonNull Context context) {
        this(context,null);
    }

    public NetworkStateView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NetworkStateView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.NetworkStateView);
        
        init();
    }

    private void init() {



    }
}
