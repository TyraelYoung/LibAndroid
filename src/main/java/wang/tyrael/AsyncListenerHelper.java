package wang.tyrael;

import java.lang.ref.WeakReference;

/**
 * Created by wangchao on 2015/10/7.
 */
public class AsyncListenerHelper {
    public static void returnSuccess(WeakReference<AsyncListener> wrlistener) {
        if (wrlistener == null) {
            return;
        }
        AsyncListener listener = wrlistener.get();
        if (listener == null) {
            return;
        }
        listener.onSucceed();
    }

    public static void returnFailure(WeakReference<AsyncListener> wrlistener, int code, String msg) {
        if (wrlistener == null) {
            return;
        }
        AsyncListener listener = wrlistener.get();
        if (listener == null) {
            return;
        }
        listener.onFail(code, msg);
    }
}
