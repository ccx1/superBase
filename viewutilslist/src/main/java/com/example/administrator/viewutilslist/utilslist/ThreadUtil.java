package com.example.administrator.viewutilslist.utilslist;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by taojin on 2017/06/11.
 */

public class ThreadUtil {


    private static Executor sExecutors = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors() + 1, 2 * Runtime.getRuntime().availableProcessors() + 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());

    public static void runOnSubThread(Runnable runnable) {
        sExecutors.execute(runnable);
    }

    private static Handler sHandler = new Handler(Looper.getMainLooper());

    public static void runOnUiThread(Runnable runnable) {

        sHandler.post(runnable);

    }


}
