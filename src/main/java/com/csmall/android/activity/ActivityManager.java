package com.csmall.android.activity;

import android.app.Activity;

import com.csmall.AsyncListener;
import com.csmall.report.ReportManager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * fore有可能为空
 * Created by wangchao on 2015/11/20.
 */
public class ActivityManager {
    private static final String TAG = "ActivityManager";
    /**
     * 当前处于前台的activity
     */
    private static volatile Activity fore;
    private static List<AsyncListener> mResumeListeners = new ArrayList<AsyncListener>();


    //TODO 考虑多线程问题。
    public static void onResumeEnd(Activity activity){
        Activity before = fore;
        fore = activity;
        if(before == null){
            notifyListener();
        }
    }

    public static void onPauseBegin(Activity activity){
        if(activity == fore){
            fore = null;
        }else{
            //理论上这个分支不应该走
            //TODO 有上报
            ReportManager.getInstance().reportFail(TAG, "activity != fore:" + activity + ":" + fore);
        }

    }

    /**
     * 获得前台的activity
     * @return
     */
    public static Activity getFore(){
        return fore;
    }

    public static synchronized void addListener(AsyncListener asyncListener){
        mResumeListeners.add(asyncListener);
    }

    public static synchronized void removeListener(AsyncListener asyncListener){
        mResumeListeners.remove(asyncListener);
    }

    private static void notifyListener(){
        //新启动一个线程执行
        new Thread(new Runnable() {
            @Override
            public void run() {
                Iterator<AsyncListener> iterator = mResumeListeners.iterator();
                while(iterator.hasNext()){
                    AsyncListener asyncListener = iterator.next();
                    asyncListener.onSucceed();
                }
            }
        }).start();
    }

}

//可能用户退出了当前app，按了home
//06-21 10:57:07 5313 5313 I VolleyHelper: volleylog:false
//        06-21 10:57:07 5313 5313 I BaseActivity: onCreate:com.example.csmall.Activity.Splash.SplashActivity@16cf3db8
//        06-21 10:57:08 5313 5313 I BaseActivity: onResume:com.example.csmall.Activity.Splash.SplashActivity@16cf3db8
//        06-21 10:57:08 5313 5556 I AddressLib: list.size():31
//        06-21 10:57:09 5313 5313 I BaseActivity: onPause:com.example.csmall.Activity.Splash.SplashActivity@16cf3db8
//        06-21 10:57:09 5313 5313 I BaseActivity: onCreate:com.example.csmall.MainActivity@212ceba7
//        06-21 10:57:10 5313 5313 I HomeFragment: onCreateView
//        06-21 10:57:10 5313 5313 I MallDataHelper: getBanner
//        06-21 10:57:10 5313 5313 I BaseActivity: onResume:com.example.csmall.MainActivity@212ceba7
//        06-21 10:57:10 5313 5313 I BaseActivity: onPause:com.example.csmall.MainActivity@212ceba7
//        06-21 10:57:10 5313 5313 I BaseActivity: onStop:com.example.csmall.Activity.Splash.SplashActivity@16cf3db8
//        06-21 10:57:10 5313 5313 I BaseActivity: onDestroy:com.example.csmall.Activity.Splash.SplashActivity@16cf3db8
