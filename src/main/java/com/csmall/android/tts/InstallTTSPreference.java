package com.csmall.android.tts;

import android.annotation.TargetApi;
import android.content.ActivityNotFoundException;
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

public class InstallTTSPreference extends Preference {
    Context context = ApplicationHolder.getApplication();

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public InstallTTSPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public InstallTTSPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public InstallTTSPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public InstallTTSPreference(Context context) {
        super(context);
    }

    @Override
    protected void onClick() {
        Uri uri = Uri.parse("http://app.qq.com/#id=detail&appid=1101109599");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try{
            context.startActivity(intent);
        }catch (ActivityNotFoundException a){
            ToastUtil.show("你的手机没有浏览器，请先安装浏览器");
        }

    }
}
