package wang.tyrael.crash;

import wang.tyrael.android.application.ApplicationHolder;
import wang.tyrael.android.manifest.ManifestHelper;
import wang.tyrael.log.LogHelper;

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
        String name = ManifestHelper.getMetaData("BUGLY_APPID");
        Bugly.init(ApplicationHolder.getApplication(), name, false);
//        Bugly.init(ApplicationHolder.getApplication());
//        CrashReport.initCrashReport(ApplicationHolder.getApplication());
    }
}
