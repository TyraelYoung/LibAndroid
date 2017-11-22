package wang.tyrael.android.application;

import android.app.Application;

import wang.tyrael.log.LogHelper;


public abstract class CSApplication extends Application {
    private static final String TAG = "CSApplication";

    /**
     * 初始化工作委托给
     */
    private InitManager initManager;

    // 2f5494e0f44e
    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationHolder.setApplication(this);
        initManager = getInitManager();

        initManager.initOnAppCreate();

        LogHelper.d(TAG, "app onCreate end");
    }

    /**
     * 初始化工作委托给InitManager
     */
    public abstract InitManager getInitManager();

}
