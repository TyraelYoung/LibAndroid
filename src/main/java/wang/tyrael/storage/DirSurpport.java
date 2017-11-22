package wang.tyrael.storage;

import android.content.Context;
import android.os.Environment;

import wang.tyrael.android.application.ApplicationHolder;
import wang.tyrael.log.LogHelper;

import java.io.File;

/**
 * support 对系统api的封装
 * Created by 王超 on 2017/9/7.
 */

public class DirSurpport {
    private static final Context context = ApplicationHolder.getApplication();
    private static final String TAG = "DirSurpport";

    /* Checks if external storage is available for read and write */
    public static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /**
     * 外部存储目录，卸载时会被系统删除
     * 与应用对应。
     *
     * @return
     */
    public static File getCacheDir(){
        if(isExternalStorageWritable()){
            return context.getExternalCacheDir();
        }else{
            LogHelper.w(TAG, "外部缓存不可用");
            return null;
        }
    }

}
