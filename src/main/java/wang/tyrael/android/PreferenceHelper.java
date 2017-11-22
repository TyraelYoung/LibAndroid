package wang.tyrael.android;

import android.content.Context;
import android.content.SharedPreferences;

import wang.tyrael.android.application.ApplicationHolder;

/**
 * Created by wangchao on 2016/6/8.
 */
public class PreferenceHelper {
    Context context =  ApplicationHolder.getApplication();

    private String name = "default";

    /**
     * WARN  小心key重复
     * @param key
     * @param value
     */
    public void putString(String key, String value){
        SharedPreferences sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.apply();
    }

    /**
     * WARN  小心key重复
     * @param key
     * @param value
     */
    public void putLong(String key, long value){
        SharedPreferences sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    /**
     * WARN  小心key重复
     * @param key
     * @param value
     */
    public void putBoolean(String key, boolean value){
        SharedPreferences sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public String getString(String key, String defaultValue){
        SharedPreferences sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        return sp.getString(key, defaultValue);
    }

    public long getLong(String key){
        SharedPreferences sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        return sp.getLong(key, 0);
    }

    public boolean getBoolean(String key){
        SharedPreferences sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        return sp.getBoolean(key, false);
    }
}
