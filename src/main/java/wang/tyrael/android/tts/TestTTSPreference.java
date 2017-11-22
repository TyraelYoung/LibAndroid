package wang.tyrael.android.tts;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.preference.Preference;
import android.util.AttributeSet;

import wang.tyrael.android.ToastUtil;
import wang.tyrael.android.application.ApplicationHolder;

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
