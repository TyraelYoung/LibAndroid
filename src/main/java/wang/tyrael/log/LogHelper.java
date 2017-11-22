package wang.tyrael.log;

import android.text.TextUtils;
import android.util.Log;

import wang.tyrael.android.DebugHelper;
import com.tencent.bugly.crashreport.BuglyLog;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by wangchao on 2015/8/29.
 */
public class LogHelper {
    private static final String FORMAT_TAG = "TAG:%s, %s";

    private static boolean isDebuggable = false;

    public static void init() {
        isDebuggable = DebugHelper.isDebuggable();
    }

    public static void d(String s) {
        d("csmall", s);
    }

    public static void d(String tag, Map map) {
        if (map == null) {
            d(tag, "map == null");
            return;
        }
        Set<Map.Entry> s = map.entrySet();
        Iterator<Map.Entry> iterator = s.iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = iterator.next();
            d(tag, "" + entry.getKey() + ": " + entry.getValue());
        }
    }

    public static void d(String tag, List list) {
        if (list == null) {
            d(tag, "list == null");
            return;
        }
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            Object o = iterator.next();
            d(tag, o.toString());
        }
    }

    public static void d(String tag, String s) {
        if (!isDebuggable) {
            return;
        }
        if(s == null){
//            w(tag, "有些机型不支持打印null");
            s = "null";
        }
        BuglyLog.d(tag, s);
        Log.d(tag, s);
    }

    public static void i(String tag, String s) {
        if (!TextUtils.isEmpty(s)) {
            BuglyLog.i(tag, s);
            Log.i(tag, s);
        }
    }

    public static void w(String tag, String s) {
        BuglyLog.w(tag, s);
        Log.w(tag, s);
    }

    public static void w(String tag, String s, Throwable e) {
        BuglyLog.w(tag, s);
        Log.w(tag, s, e);
    }

    public static void w(String tag, Throwable e) {
        BuglyLog.w(tag, "");
        Log.w(tag, "", e);
    }

    public static void e(String tag, String s) {
        BuglyLog.e(tag, s);
        if (s == null) {
            //某些机型
//            java.lang.NullPointerException
//            println needs a message
            return;
        }
        Log.e(tag, s);
        if(DebugHelper.isDebuggable()){
            throw new RuntimeException(s);
        }
    }

    public static void e(String tag, String s, Throwable e) {
//        java.lang.RuntimeException: Unable to start activity ComponentInfo{com.example.csmall/com.example.csmall.MainActivity}: java.lang
// .NullPointerException: Attempt to invoke virtual method 'void darks.log.Logger.error(java.lang.Object, java.lang.Throwable)' on a null object
// reference
//        at android.app.ActivityThread.performLaunchActivity(ActivityThread.java:2379)
//        at android.app.ActivityThread.handleLaunchActivity(ActivityThread.java:2441)
//        at android.app.ActivityThread.access$800(ActivityThread.java:154)
//        at android.app.ActivityThread$H.handleMessage(ActivityThread.java:1323)
//        at android.os.Handler.dispatchMessage(Handler.java:102)
//        at android.os.Looper.loop(Looper.java:135)
//        at android.app.ActivityThread.main(ActivityThread.java:5336)
//        at java.lang.reflect.Method.invoke(Native Method)
//        at java.lang.reflect.Method.invoke(Method.java:372)
//        at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:904)
//        at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:699)
//        Caused by: java.lang.NullPointerException: Attempt to invoke virtual method 'void darks.log.Logger.error(java.lang.Object, java.lang
// .Throwable)' on a null object reference
//        at com.example.csmall.LogHelper.w(LogHelper.java:43)
//        at com.example.csmall.Util.NetHelper.isConnected(NetHelper.java:33)
//        at com.example.csmall.business.network.HttpHelper.send(HttpHelper.java:47)
//        at com.example.csmall.business.network.HttpHelper.send(HttpHelper.java:86)
//        at com.example.csmall.business.network.HttpHelper.send(HttpHelper.java:123)
//        at com.example.csmall.business.VersionManager.check8Update(VersionManager.java:130)
//        at com.example.csmall.InitManager.initOnMainActivityCreate(InitManager.java:40)
//        at com.example.csmall.MainActivity.onCreate(MainActivity.java:79)
//        at android.app.Activity.performCreate(Activity.java:5976)
//        at android.app.Instrumentation.callActivityOnCreate(Instrumentation.java:1105)
//        at android.app.ActivityThread.performLaunchActivity(ActivityThread.java:2332)
//        ... 10 more
        BuglyLog.e(tag, s, e);
        Log.e(tag, s, e);
        if(DebugHelper.isDebuggable()){
            throw new RuntimeException(e);
        }
    }

    public static void e(String tag, Throwable e) {
        BuglyLog.e(tag, "", e);
        Log.e(tag, "", e);
        if(DebugHelper.isDebuggable()){
            throw new RuntimeException(e);
        }
    }
}
