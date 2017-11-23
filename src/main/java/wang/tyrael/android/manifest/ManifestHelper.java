package wang.tyrael.android.manifest;

import android.content.Context;
import android.content.pm.PackageManager;

import wang.tyrael.android.application.ApplicationHolder;

/**
 * Created by 王超 on 2017/11/23.
 */

public class ManifestHelper {
    private static final Context context = ApplicationHolder.getApplication();

    public static String getMetaData(String name){
        String channel = null;
        try {
            channel = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA)
                    .metaData.getString(name);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return channel;
    }
}
