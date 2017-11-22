package wang.tyrael.android.application;

import android.util.Log;

import wang.tyrael.android.ProcessHelper;

/**
 * Created by 王超 on 2017/10/11.
 */

public abstract class InitManager {

    private static final String TAG = "InitManager";

    public void initOnAppCreate() {
        Log.i(TAG, "initOnAppCreate");
        switch (ProcessHelper.getNameCurrent()){
            default:
                //默认进程
                initOnAppCreateDefaultProcess();
                break;
        }
//        initOnAppCreateDefaultProcess();
        new Thread(new Runnable() {
            @Override
            public void run() {
                initOnBackground();
                initOnIdle();
            }
        }).start();
    }

    public void initOnBackground(){}

    public void initOnIdle(){}

    /**
     * 主进程创建，一般很多组件初始化，写在这里
     * 应用有多进程的时候，副进程不需要很多组件。而安卓框架调用的是同样的入口。
     */
    public abstract void initOnAppCreateDefaultProcess();

    public void initOnMainActivityCreate(){}

    public void initOnLogin(){}
}
