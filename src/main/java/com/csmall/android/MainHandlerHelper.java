package com.csmall.android;

import android.os.Handler;
import android.os.Looper;

/**
 * 主线程的handler
 * Created by wangchao on 2015/12/23.
 */
public class MainHandlerHelper {
    private static final MainHandlerHelper sMainHandler = new MainHandlerHelper();

    public static MainHandlerHelper getInstance() {
        return sMainHandler;
    }

    private Handler uiHandler;

    private MainHandlerHelper() {
        uiHandler = new Handler(Looper.getMainLooper());
    }

    public Handler getUIHandler() {
        return uiHandler;
    }
}
