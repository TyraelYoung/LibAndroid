package com.csmall.library.statistics.individual;

import android.os.Environment;
import android.util.Log;

import java.io.File;

/**
 * Created by wangchao on 2016/6/11.
 */
public class Dir {
    public static final String LOG = Environment.getExternalStorageDirectory() + File.separator + "individual" + File.separator + "log";
    public static final String ZIP = Environment.getExternalStorageDirectory() + File.separator + "individual" + File.separator + "zip";
    private static final String TAG = "Dir";

    /**
     * 日志有一个二级目录
     * @return
     */
    public static String dirLog(){

        String path = LOG + File.separator + System.currentTimeMillis();
        File file = new File(path);
        file.mkdirs();
        Log.i(TAG, "dirLog:" + path);
        return path;
    }

    public static String dirZip(){
        String path = ZIP;
        File file = new File(path);
        file.mkdirs();
        return path;
    }

    public static String fileLog(String dir){
        return dir + File.separator + System.currentTimeMillis() + ".txt";
    }

    public static String fileZip() {
        return dirZip() + File.separator + System.currentTimeMillis() + ".zip";
    }
}
