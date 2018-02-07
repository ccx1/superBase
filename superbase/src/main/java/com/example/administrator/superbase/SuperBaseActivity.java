package com.example.administrator.superbase;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.administrator.superbase.Receiver.NetBroadcastReceiver;
import com.example.administrator.superbase.utils.common.ToastUtil;
import com.example.administrator.superbase.utils.NetUtils;
import com.example.administrator.superbase.utils.PermissionRequesUtls;
import com.example.administrator.superbase.utils.common.LogUtil;
import com.example.administrator.superbase.view.NetworkStateView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * Created by v_chicunxiang on 2018/2/2.
 *
 * @史上最帅无敌创建者 ccx
 * @创建时间 2018/2/2 16:28
 */

public abstract class SuperBaseActivity extends AppCompatActivity implements PermissionRequesUtls.OnRequestPermissionsResultCallbacks, NetBroadcastReceiver.onNetWorkStateEvent {
    private SparseArray<View> mSparseArray = new SparseArray<>();
    private long      mTimeMillis;
    private Toolbar   toolbar;
    public  ActionBar mActionBar;
    private NetBroadcastReceiver mNetBroadcastReceiver = new NetBroadcastReceiver();
    private NetworkStateView mNetworkStateView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获取全局的对象
        //获取activitymanager管理器,进行统一管理

        setContentView(BindLayout());

        //绑定黄油刀

        if (isNeedTranslucentStatus()) {

            setTranslucentStatus();

        } else {
            if (toolbar != null) {
                toolbar.setPadding(0, 0, 0, 0);
            }

        }

        //初始化布局
        initView();

        //初始化数据
        initData();

        //初始化注册
        initRegister();

        Intent intent = new Intent();
        // 进入页面之后的判断网络情况
        intent.setAction(BaseConstant.ACTION_ONE_SEND);
        sendBroadcast(intent);
    }

    private void initRegister() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        filter.addAction(BaseConstant.ACTION_ONE_SEND);
        registerReceiver(mNetBroadcastReceiver, filter);
        mNetBroadcastReceiver.setOnNetWorkStateEvent(this);
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    public void setToolbar(Toolbar toolbar) {
        this.toolbar = toolbar;
        setSupportActionBar(toolbar);
    }

    /**
     * 设置toolbar的标题
     *
     * @param title
     */
    public void setToolbarTitle(String title) {
        if (mActionBar == null) {
            mActionBar = getSupportActionBar();
        }
        mActionBar.setTitle(title);
    }

    /**
     * [绑定控件]
     *
     * @param resId
     * @return
     */
    protected <T extends View> T $(@IdRes int resId) {
        return (T) super.findViewById(resId);
    }


    /**
     * 设置是否需要沉浸式
     *
     * @return
     */
    public boolean isNeedTranslucentStatus() {
        return false;
    }

    /**
     * 设置沉浸式
     */
    private void setTranslucentStatus() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // 这个属性4.4算是全透明（有的机子是过渡形式的透明），5.0就是半透明了 我的模拟器、真机都是半透明，

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {// 4.4 全透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {// 5.0 全透明实现
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);// calculateStatusColor(Color.WHITE, (int) alphaValue)

        }

    }

    /**
     * 双击退出
     */
    @Override
    public void onBackPressed() {
        if (isDoubleExit()) {
            if (System.currentTimeMillis() - mTimeMillis > 2000) {
                ToastUtil.ToastShow("在按一次退出");
                mTimeMillis = System.currentTimeMillis();
            } else {
                finish();
            }
        } else {
            finish();
        }
    }

    /**
     * 并且释放资源
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mNetBroadcastReceiver);

    }

    /**
     * 获取控件id,类似于findviewbyid
     *
     * @param id 传入一个控件的id,自动寻找
     * @return 返回一个控件
     */
    protected View getViewId(int id) {
        View view = mSparseArray.get(id);
        if (view == null) {
            view = this.findViewById(id);
            mSparseArray.put(id, view);
        }
        return view;
    }


    /*
     * 绑定一个布局id
     */
    @LayoutRes
    protected abstract int BindLayout();

    /**
     * 查找id,或者初始化toolbar的标题
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * [页面跳转] *
     */
    public void startActivity(Class<?> clz) {
        startActivity(new Intent(SuperBaseActivity.this, clz));
    }

    /**
     * [携带数据的页面跳转] *
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }


    /**
     * [含有Bundle通过Class打开编辑界面] *
     */
    public void startActivityForResult(Class<?> cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    public boolean isDoubleExit() {
        return false;
    }

    /**
     * 权限处理
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionRequesUtls.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms, boolean isAllGranted) {
        LogUtil.e("同意:" + perms.size() + "个权限,isAllGranted=" + isAllGranted);
        for (String perm : perms) {
            LogUtil.e("同意:" + perm);
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms, boolean isAllDenied) {
        LogUtil.e("拒绝:" + perms.size() + "个权限,isAllDenied=" + isAllDenied);
        for (String perm : perms) {
            LogUtil.e("拒绝:" + perm);
        }
    }

    /**
     * eventbus处理
     */
    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }


    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(Object event) {
        onMessageEvent(event);
    }

    protected void onMessageEvent(Object event) {

    }

    /**
     * @param netWorkState 状态值
     */
    @Override
    public void onNetStateEvent(int netWorkState) {
        switch (netWorkState) {
            case NetUtils.NETWORK_NONE:
                onNetWorkNone();
                break;
            case NetUtils.NETWORK_NONET:
                onNetWorkNoNet();
                break;
            case NetUtils.NETWORK_MOBILE:
                onNetWorkMobile();
                break;
            case NetUtils.NETWORK_WIFI:
                onNetWorkWifi();
                break;
            case NetUtils.NETWORK_EMPTY:
                onNetWorkEmpty();
                break;
            default:
                break;
        }
    }

    public void onNetWorkNoNet() {
        LogUtil.i("onNetWorkNoNet");
        if (mNetworkStateView != null) {
            mNetworkStateView.showNoNet();
        }
    }

    public void onNetWorkWifi() {
        LogUtil.i("onNetWorkWifi");
        if (mNetworkStateView != null) {
            mNetworkStateView.showSuccess();
        }
    }

    public void onNetWorkMobile() {
        LogUtil.i("onNetWorkMobile");
        if (mNetworkStateView != null) {
            mNetworkStateView.showSuccess();
        }
    }

    public void onNetWorkEmpty() {
        LogUtil.i("onNetWorkEmpty");
        if (mNetworkStateView != null) {
            mNetworkStateView.showEmpty();
        }
    }

    public void onNetWorkNone() {
        LogUtil.i("onNetWorkNone");
        if (mNetworkStateView != null) {
            mNetworkStateView.showError();
        }
    }


    public void registerNetWorkStateView(NetworkStateView networkStateView) {
        this.mNetworkStateView = networkStateView;
        if (mNetworkStateView != null) {
            mNetworkStateView.showLoading();
        }
    }

    protected void showShortToast(String s) {
        ToastUtil.ToastShow(s);
    }


    protected void showLongToast(String s) {
        ToastUtil.ToastLongTimeShow(s);
    }

}
