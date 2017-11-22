package wang.tyrael.android;

import android.content.Context;
import android.os.Environment;

import wang.tyrael.util.FileUtil;

/**
 * 数据的清理有时候是跟业务相关的。
 * 这里是一些安卓的通用功能
 * Created by wangchao on 2016/9/3.
 */
public class DataCleanHelper {
    public static long getCacheSize(Context context) throws Exception {
        long cacheSize = FileUtil.getDirSize(context.getCacheDir());
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            cacheSize += FileUtil.getDirSize(context.getExternalCacheDir());
        }
        return cacheSize;
    }

    public  static void cleanCache(Context context){
        FileUtil.delFileByDir(context.getCacheDir());
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            FileUtil.delFileByDir(context.getExternalCacheDir());
        }
    }



}
