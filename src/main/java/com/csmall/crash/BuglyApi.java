package com.csmall.crash;

import com.csmall.android.application.ApplicationHolder;
import com.csmall.log.LogHelper;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.crashreport.CrashReport;

/**
 * Created by wangchao on 2017/3/29.
 */

public class BuglyApi {
    private static final String TAG = "BuglyApi";

    public static void initOnAppCreate(){
        LogHelper.i(TAG, "initOnAppCreate");
        //默认去manifest中取值
//        Bugly.init(ApplicationHolder.getApplication(), "bc4cb057ab", false);
//        Bugly.init(ApplicationHolder.getApplication());
        CrashReport.initCrashReport(ApplicationHolder.getApplication());
    }
}
