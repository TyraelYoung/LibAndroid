package com.csmall.library.statistics.individual;

import android.os.Environment;
import android.util.Log;

import com.csmall.android.PreferenceHelper;
import com.csmall.json.individual.User;


import java.io.File;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 起一个线程，用一个队列来写。
 * 某个时间点。事件可能很多。也可能很少。
 *
 * 每次启动用一个新文件。
 * 文件超过1m，重新用一个新文件。
 *
 * 先写个简单实现，以后再考虑复杂情况。
 *
 * //TODO
 * 添加删除旧日志
 *
 * Created by wangchao on 2016/6/7.
 */
public class IndividualLog {
    private static final String TAG = "IndividualLog";
    private static final String PREF_KEY_DIR_CURRENT = TAG + "PREF_KEY_DIR_CURRENT";
    private static IndividualLog sLog = new IndividualLog();

    //需要持久化
    private String dirCurrent;

    private BlockingDeque<Logable> mEventQueue = new LinkedBlockingDeque<Logable>();
    private LogWriter mWriter;
    private boolean isLooping = false;

    public static IndividualLog getInstance(){
        return sLog;
    }

    private IndividualLog(){
        dirCurrent = new PreferenceHelper().getString(PREF_KEY_DIR_CURRENT, null);
        if(dirCurrent == null){
            dirCurrent = Dir.dirLog();
        }
        mWriter = new LogWriter(dirCurrent);
    }

    public void onUserChange(User user){
        onNewEvent(user);
    }

    public void onNewEvent(Logable event){
        if(!ReportConfig.getInstance().isLog()){
            //如果
            return;
        }
        try {
            mEventQueue.put(event);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        startWrite();
    }

    public String prepareUpload(){
        String dirOld = dirCurrent;

        //换一个目录读写
        dirCurrent = Dir.dirLog();
        new PreferenceHelper().putString(PREF_KEY_DIR_CURRENT, dirCurrent);
        mWriter = new LogWriter(dirCurrent);

        //旧目录交给上传器处理
        return dirOld;

    }

    public void startWrite(){
        Log.i(TAG, "startWrite:isLooping:" + isLooping);
        if(isLooping){
            return;
        }
        isLooping = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        Logable event = mEventQueue.take();
                        mWriter.write(event);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
