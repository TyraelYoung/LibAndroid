package wang.tyrael.android.tts;

import android.content.Context;
import android.os.Build;
import android.speech.tts.TextToSpeech;

import wang.tyrael.android.ToastUtil;
import wang.tyrael.android.application.ApplicationHolder;
import wang.tyrael.log.LogHelper;

import java.util.Locale;
import java.util.UUID;

/**
 * Created by 王超 on 2017/11/2.
 */

public class SpeekSupport {
    private static final String TAG = "SpeekSupport";
    private static final Context context = ApplicationHolder.getApplication();

    private static SpeekSupport speekSupport = new SpeekSupport();

    public static SpeekSupport getSpeekSupport() {
        return speekSupport;
    }

    public TextToSpeech textToSpeech = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
        @Override
        public void onInit(int status) {
            switch (status){
                case TextToSpeech.SUCCESS:
                    int result = textToSpeech.setLanguage(Locale.CHINA);
                    //华为MHA-AL00会设置失败fail bound to com.google.android.tts
                    //TextToSpeech: Failed to bind to com.google.android.tts
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED)
                    {
                        ToastUtil.show("您的手机系统文字转语音引擎不支持中文，请各大应用市场下载 Google文字转语音");
                    }
                    LogHelper.i(TAG, "onInit SUCCESS");
                    break;
                case TextToSpeech.ERROR:
                    LogHelper.i(TAG, "onInit ERROR");
                    break;
            }
        }
    }, "com.google.android.tts");

    public void speek(String s){
        LogHelper.i(TAG, "speek:" + s);
        UUID uuid = UUID.randomUUID();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            textToSpeech.speak(s, TextToSpeech.QUEUE_ADD, null, uuid.toString());
        }else{
            textToSpeech.speak(s, TextToSpeech.QUEUE_ADD, null);
        }
    }
}
