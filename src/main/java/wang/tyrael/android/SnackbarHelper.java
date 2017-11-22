package wang.tyrael.android;

import android.support.design.widget.Snackbar;
import android.view.View;

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
