/**
 *
 */
package wang.tyrael.android;

import android.content.Context;
import android.os.Looper;
import android.widget.Toast;

import wang.tyrael.android.application.ApplicationHolder;

public class ToastUtil {

    public static void show(Context context, final String info) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            MainHandlerHelper.getInstance().getUIHandler().post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(ApplicationHolder.getApplication(), info, Toast.LENGTH_LONG).show();
                }
            });
        } else {
            Toast.makeText(ApplicationHolder.getApplication(), info, Toast.LENGTH_LONG).show();
        }
    }

    public static void show(final Context context, final int info) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            MainHandlerHelper.getInstance().getUIHandler().post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(ApplicationHolder.getApplication(), info, Toast.LENGTH_LONG).show();
                }
            });


        } else {
            Toast.makeText(context, info, Toast.LENGTH_LONG).show();
        }
    }

    public static void show(String info) {
        show(info, Toast.LENGTH_LONG);
    }

    public static void show(final String info, int duration) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            MainHandlerHelper.getInstance().getUIHandler().post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(ApplicationHolder.getApplication(), info, Toast.LENGTH_LONG).show();
                }
            });
        } else {
            Toast.makeText(ApplicationHolder.getApplication(), info, duration).show();
        }

    }
}
