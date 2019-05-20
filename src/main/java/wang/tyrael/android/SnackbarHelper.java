package wang.tyrael.android;

import android.view.View;

import com.google.android.material.snackbar.Snackbar;

/**
 * Created by wangchao on 2016/9/3.
 */
public class SnackbarHelper {
    public static void show(String info, View view) {
        try {
            Snackbar.make(view,
                    info, Snackbar.LENGTH_SHORT).setAction("retry", null).show();
        } catch (Exception e) {
            return;
        }
    }
}
