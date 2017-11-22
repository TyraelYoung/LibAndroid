package wang.tyrael.version;

import android.content.Context;
import android.content.pm.PackageManager;

import wang.tyrael.android.application.ApplicationHolder;
import wang.tyrael.log.LogHelper;

/**
 * Created by wangchao on 2017/3/29.
 */

public class VersionManager {
    private static final String TAG = "VersionManager";

    /**
     * 获取软件版本号
     *
     *
     * @return
     */
    public static int getVersionCode() {
        int versionCode = 0;
        try {
            Context context = ApplicationHolder.getApplication();
            // 获取软件版本号，对应AndroidManifest.xml下android:versionCode
            String pkName = context.getPackageName();
            versionCode = context.getPackageManager().getPackageInfo(
                    pkName, 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            LogHelper.e(TAG, "", e);
        }
        return versionCode;
    }

    public static String getVersionName() {
        try {
            Context context = ApplicationHolder.getApplication();
            String pkName = context.getPackageName();
            String versionName = context.getPackageManager().getPackageInfo(
                    pkName, 0).versionName;
            return versionName;
        } catch (Exception e) {
            LogHelper.e(TAG, "", e);
        }
        return null;
    }
}
