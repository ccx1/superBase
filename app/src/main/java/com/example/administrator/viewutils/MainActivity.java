package com.example.administrator.viewutils;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.viewutilslist.utils.LogUtils;
import com.example.administrator.viewutilslist.utils.PermissionRequesUtls;

import java.util.List;

public class MainActivity extends AppCompatActivity implements PermissionRequesUtls.OnRequestPermissionsResultCallbacks {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionRequesUtls.onRequestPermissionsResult(requestCode, permissions, grantResults,this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms, boolean isAllGranted) {
        LogUtils.e("同意:" + perms.size() + "个权限,isAllGranted=" + isAllGranted);
        for (String perm : perms) {
            LogUtils.e("同意:" + perm);
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms, boolean isAllDenied) {
        LogUtils.e("拒绝:" + perms.size() + "个权限,isAllDenied=" + isAllDenied);
        for (String perm : perms) {
            LogUtils.e("拒绝:" + perm);
        }
    }
}
