package wang.tyrael.android;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.util.Log;

import wang.tyrael.android.application.ApplicationHolder;

/**
 * Created by wangchao on 2015/9/9.
 */
public class DebugHelper {
    private static final String TAG = "DebugHelper";

    public static boolean isDebuggable() {
        try {
            Context context = ApplicationHolder.getApplication();
            ApplicationInfo info = context.getApplicationInfo();
            if (info == null) {
                return false;
            }
            boolean result =  (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
            Log.i(TAG, "isDebuggable:" + result);
            return result;
        } catch (Exception e) {
            Log.e(TAG, "", e);
        }
        return false;
    }
}
