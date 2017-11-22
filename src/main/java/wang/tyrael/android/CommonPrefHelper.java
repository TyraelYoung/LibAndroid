package wang.tyrael.android;

import android.content.Context;
import android.content.SharedPreferences;

import wang.tyrael.android.application.ApplicationHolder;

/**
 * 应用范围通用的
 * Created by wangchao on 2015/9/11.
 */
public class CommonPrefHelper {
    private static final String PREF_NAME = "CommonPrefHelper";
    /**
     * 后台环境：正式环境、测试环境
     */
    private static final String KEY_SERVER = "KEY_SERVER";
    public static final int VALUE_SERVER_NORMAL = 0;
    public static final int VALUE_SERVER_TEST = 1;

    private static CommonPrefHelper sCommonPrefHelper;

    public static  synchronized CommonPrefHelper getInstance(){
        if(sCommonPrefHelper == null){
            sCommonPrefHelper = new CommonPrefHelper();
        }
        return sCommonPrefHelper;
    }

    SharedPreferences mPref;

    private CommonPrefHelper(){
        Context context = ApplicationHolder.getApplication();
        mPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    /**
     *
     * @return 上次设置的模式
     */
    public int getServer(){
        return mPref.getInt(KEY_SERVER, VALUE_SERVER_NORMAL);
    }

    public void setServer(int mode){
        SharedPreferences.Editor editor = mPref.edit();
        editor.putInt(KEY_SERVER, mode);
        editor.apply();
    }
}
