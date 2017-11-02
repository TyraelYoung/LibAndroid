package com.csmall.android.tts;

import android.annotation.TargetApi;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.preference.Preference;
import android.util.AttributeSet;

import com.csmall.android.ToastUtil;
import com.csmall.android.application.ApplicationHolder;

/**
 * Created by 王超 on 2017/11/2.
 */

public class SystemTTSPreference extends Preference {
    Context context = ApplicationHolder.getApplication();

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SystemTTSPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public SystemTTSPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public SystemTTSPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SystemTTSPreference(Context context) {
        super(context);
    }

    @Override
    protected void onClick() {
        Intent intent = new Intent("/");
        ComponentName cm = new ComponentName("com.android.settings","com.android.settings.TextToSpeechSettings");
        intent.setComponent(cm);
        intent.setAction("android.intent.action.VIEW");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try{
            context.startActivity(intent);
        }catch (ActivityNotFoundException e){
            ToastUtil.show("请在系统设置中使用Google文字转语音");
        }

    }
}
