package wang.tyrael;

import android.content.Context;
import android.util.Log;

import wang.tyrael.android.application.ApplicationHolder;
import wang.tyrael.crash.BuglyApi;
import wang.tyrael.log.LogHelper;
import wang.tyrael.net.okhttp.ClientFactory;
import com.facebook.stetho.Stetho;

import wang.tyrael.http.okhttp.async.HttpWithCookieStatic;

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
