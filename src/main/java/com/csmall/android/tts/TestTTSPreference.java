package com.csmall.android.tts;

import android.annotation.TargetApi;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.preference.Preference;
import android.provider.Settings;
import android.util.AttributeSet;

import com.csmall.android.ToastUtil;
import com.csmall.android.application.ApplicationHolder;
import com.csmall.log.LogHelper;

/**
 * Created by 王超 on 2017/11/2.
 */

public class TestTTSPreference extends Preference {
    private static final String TAG = "SystemTTSPreference";
    Context context = ApplicationHolder.getApplication();

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TestTTSPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public TestTTSPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TestTTSPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TestTTSPreference(Context context) {
        super(context);
    }

    @Override
    protected void onClick() {
        SpeekSupport.getSpeekSupport().speek("语音已成功设置");
        ToastUtil.show("正在播放声音：“语音已成功设置”");
    }
}
