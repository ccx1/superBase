package com.example.administrator.viewutilslist.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.administrator.viewutilslist.R;

/**
 * Created by v_chicunxiang on 2018/2/2.
 * 网络判断自定义View
 *
 * @史上最帅无敌创建者 ccx
 * @创建时间 2018/2/2 18:32
 */

public class NetworkStateView extends LinearLayout {

    private final Context context;
    //当前的加载状态
    private       CurrentState     mCurrentState = CurrentState.STATE_EMPTY;
    private int mLoadingViewId;
    private int mErrorViewId;
    private int mNoNetworkViewId;
    private int mEmptyViewId;
    private LayoutInflater mInflater;
    private ViewGroup.LayoutParams params;
    private View mErrorView;
    private View mNoNetworkView;
    private View mLoadingView;
    private View mEmptyView;


    public NetworkStateView(@NonNull Context context) {
        this(context, null);
    }

    public NetworkStateView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NetworkStateView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.NetworkStateView,defStyleAttr,0);

        mLoadingViewId = typedArray.getResourceId(R.styleable.NetworkStateView_loadingView, R.layout.view_loading);
        mErrorViewId = typedArray.getResourceId(R.styleable.NetworkStateView_errorView, R.layout.view_network_error);
        mNoNetworkViewId = typedArray.getResourceId(R.styleable.NetworkStateView_noNetworkView, R.layout.view_no_network);
        mEmptyViewId = typedArray.getResourceId(R.styleable.NetworkStateView_emptyView, R.layout.view_empty);

        typedArray.recycle();

        mInflater = LayoutInflater.from(context);

        params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        setBackgroundColor(Color.WHITE);

    }

    public void showError() {
        mCurrentState = CurrentState.STATE_NETWORK_ERROR;
        if (null == mErrorView) {
            mErrorView = mInflater.inflate(mErrorViewId, null);
            addView(mErrorView, 0, params);
        }
        showViewByState(mCurrentState);
    }

    public void showNoNet() {
        mCurrentState = CurrentState.STATE_NO_NETWORK;
        if (null == mNoNetworkView) {
            mNoNetworkView = mInflater.inflate(mNoNetworkViewId, null);
            addView(mNoNetworkView, 0, params);
        }
        showViewByState(mCurrentState);
    }

    public void showLoading() {
        mCurrentState = CurrentState.STATE_LOADING;
        if (null == mLoadingView) {
            mLoadingView = mInflater.inflate(mLoadingViewId, null);
            addView(mLoadingView, 0, params);
        }
        showViewByState(mCurrentState);
    }

    public void showEmpty() {
        mCurrentState = CurrentState.STATE_EMPTY;
        if (null == mEmptyView) {
            mEmptyView = mInflater.inflate(mEmptyViewId, null);
            addView(mEmptyView, 0, params);
        }
        showViewByState(mCurrentState);
    }


    public void showSuccess() {
        mCurrentState = CurrentState.STATE_NETWORK_ERROR;
        showViewByState(mCurrentState);
    }

    private void showViewByState(CurrentState state) {

        //如果当前状态为加载成功，隐藏此View，反之显示
        this.setVisibility(state == CurrentState.STATE_SUCCESS ? View.GONE : View.VISIBLE);

        if (null != mLoadingView) {
            mLoadingView.setVisibility(state == CurrentState.STATE_LOADING ? View.VISIBLE : View.GONE);
        }

        if (null != mErrorView) {
            mErrorView.setVisibility(state == CurrentState.STATE_NETWORK_ERROR ? View.VISIBLE : View.GONE);
        }

        if (null != mNoNetworkView) {
            mNoNetworkView.setVisibility(state == CurrentState.STATE_NO_NETWORK ? View.VISIBLE : View.GONE);
        }

        if (null != mEmptyView) {
            mEmptyView.setVisibility(state == CurrentState.STATE_EMPTY ? View.VISIBLE : View.GONE);
        }
    }



    private enum CurrentState {
        STATE_SUCCESS,//成功
        STATE_LOADING,//加载中
        STATE_NETWORK_ERROR,//网络错误
        STATE_NO_NETWORK,//没有网络
        STATE_EMPTY //空闲状态
    }
}
