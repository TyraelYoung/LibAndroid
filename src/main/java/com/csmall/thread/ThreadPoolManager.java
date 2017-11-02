package com.csmall.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * 各种线程池的管理
 * Created by wangchao on 2015/12/18.
 */
public class ThreadPoolManager {
    private static ThreadPoolManager sManager = new ThreadPoolManager();

    //TODO 根据cpu核数制定线程池大小
    private ScheduledExecutorService mScheduledExecutorService = Executors.newScheduledThreadPool(2);
    private ExecutorService mNormalExecutor;
    private ExecutorService mVipExecutor = Executors.newCachedThreadPool();

    public static ThreadPoolManager getInstance(){
        return sManager;
    }

    private ThreadPoolManager(){
        int noCpu = Runtime.getRuntime().availableProcessors();
        mNormalExecutor = Executors.newFixedThreadPool(noCpu + 2);
    }

    /**
     * 定时执行器
     * @return
     */
    public ScheduledExecutorService getSchedule(){
        return mScheduledExecutorService;
    }

    /**
     * 常规短任务
     * @return
     */
    public ExecutorService getNormal(){
        return mNormalExecutor;
    }

    /**
     * 高优先级任务
     * @return
     */
    public ExecutorService getVip(){
        return mVipExecutor;
    }
}
