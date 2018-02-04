package com.example.administrator.viewutilslist.utils.common;

import com.example.administrator.viewutilslist.utils.Utils;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class ThreadUtil {

    private static Executor sExecutors = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors() + 1, 2 * Runtime.getRuntime().availableProcessors() + 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());

    public static void runOnSubThread(Runnable runnable) {
        sExecutors.execute(runnable);
    }

    public static void runOnUiThread(Runnable runnable) {

        Utils.getInstance().HandlerManager().post(runnable);

    }

}
