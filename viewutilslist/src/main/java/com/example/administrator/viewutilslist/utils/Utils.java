package com.example.administrator.viewutilslist.utils;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;

import com.example.administrator.viewutilslist.BaseConstant;
import com.example.administrator.viewutilslist.utils.common.LogUtils;
import com.example.administrator.viewutilslist.utils.common.SharedPreferencesUtils;
import com.example.administrator.viewutilslist.utils.common.ToastUtils;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by v_chicunxiang on 2018/2/2.
 *
 * @史上最帅无敌创建者 ccx
 * @创建时间 2018/2/2 14:00
 */

public class Utils {

    private static Context mContext;
    private static  Handler sHandler;
    private static Utils sInstance;


    private Utils() {

    }

    public void init(Context context) {
        init(context, false);
    }

    public void init(Context context, boolean isDebug) {
        mContext = context;
        initSharePreferences(mContext);
        initStaticToast(mContext);
        LogUtils.setIsDebug(true);
        PathUtil.getInstance().initDirs(context.getFilesDir().getAbsolutePath(),null,context);
    }

    public Handler HandlerManager() {
        if (sHandler == null) {
            sHandler = new Handler(Looper.getMainLooper());
        }
        return sHandler;
    }

    private void initStaticToast(Context mContext) {
        ToastUtils.init(mContext);
    }

    private void initSharePreferences(Context context) {
        SharedPreferencesUtils.init(context, BaseConstant.DEFAULT_KEY);
    }

    public static void writeFile(byte[] bytes, File file, boolean append) {
        FileOutputStream mFileOutputStream = null;
        try {
            mFileOutputStream = new FileOutputStream(file, append);
            mFileOutputStream.write(bytes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeStream(mFileOutputStream);
        }
    }

    public static String readFileToString(String file) {
        FileInputStream fileInputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        String result = null;
        try {
            fileInputStream = new FileInputStream(file);
            byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = fileInputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, len);
            }
            byte[] data = byteArrayOutputStream.toByteArray(); // 取内存中保存的数据
            result = new String(data, "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeStream(fileInputStream, byteArrayOutputStream);
        }
        return result;
    }

    private static void closeStream(Closeable... c) {
        for (int i = 0; i < c.length; i++) {
            Closeable closeable = c[i];
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    closeable = null;
                }
            }
        }
    }

    public static boolean isSdk14() {
        return Build.VERSION.SDK_INT >= 14;
    }

    public static boolean isSdk21() {
        return Build.VERSION.SDK_INT >= 21;
    }

    public static Utils getInstance() {
        if (sInstance == null) {
            sInstance = new Utils();
        }
        return sInstance;
    }
}
