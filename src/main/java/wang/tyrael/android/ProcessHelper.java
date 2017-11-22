package wang.tyrael.android;

import android.content.Context;
import android.app.ActivityManager;
import android.util.Log;


import wang.tyrael.android.application.ApplicationHolder;

import java.util.List;

/**
 * Created by wangchao on 2015/12/22.
 */
public class ProcessHelper {
    public static final String PROCESS_DEFAULT = ApplicationHolder.getApplication().getPackageName();
    public static final String PROCESS_BAIDU = PROCESS_DEFAULT + ":bdservice_v1";
    private static final java.lang.String TAG = "ProcessHelper";

    /**
     * @return null may be returned if the specified process not found
     */
    public static String getProcessName(Context cxt, int pid) {
        ActivityManager am = (ActivityManager) cxt.getSystemService(Context.ACTIVITY_SERVICE);
        List<android.app.ActivityManager.RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
        if (runningApps == null) {
            return null;
        }
        for (android.app.ActivityManager.RunningAppProcessInfo procInfo : runningApps) {
            if (procInfo.pid == pid) {
                return procInfo.processName;
            }
        }
        return null;
    }

    /**
     * 获取当前进程的名字
     * @return
     */
    public static String getNameCurrent(){
        int pid = android.os.Process.myPid();
        String name = getProcessName(ApplicationHolder.getApplication(), pid);
        Log.i(TAG, "name:" + name);
        return name;
    }


}
