package com.csmall;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.csmall.android.DebugHelper;
import com.csmall.android.application.ApplicationHolder;
import com.csmall.crash.BuglyApi;
import com.csmall.log.LogHelper;
import com.csmall.net.okhttp.ClientFactory;
import com.facebook.stetho.Stetho;

import wang.tyrael.library.http.okhttp.async.HttpWithCookieStatic;

/**
 * Created by wangchao on 2017/3/29.
 */

public class LibInitManager {

    private static final String TAG = "LibInitManager";

    public static void initOnAppCreate(LibInitData data) {
        Log.i(TAG, "initOnAppCreate");
        Context  context= ApplicationHolder.getApplication();
        LogHelper.init();
//        ApplicationHolder.setApplication(data.app);
        BuglyApi.initOnAppCreate();
        //MailSender.setMailConfig(data.mailConfig);
        Stetho.initializeWithDefaults(context);

        HttpWithCookieStatic.init(ClientFactory.getClientWithCookie());
    }


}
