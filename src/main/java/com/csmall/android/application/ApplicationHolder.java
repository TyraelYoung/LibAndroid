package com.csmall.android.application;

import android.app.Application;

import com.csmall.log.LogHelper;

/**
 * Created by wangchao on 2016/6/6.
 */
public class ApplicationHolder {
    private static final String TAG = "ApplicationHolder";
    private static Application sApplication;

    public static Application getApplication(){
        if(sApplication == null){
            LogHelper.e(TAG, "sApplication == null");
        }
        return sApplication;
    }

    public static void setApplication(Application application) {
        sApplication = application;
    }
}
