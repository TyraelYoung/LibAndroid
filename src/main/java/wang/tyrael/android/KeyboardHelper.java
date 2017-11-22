package wang.tyrael.android;

import android.app.Activity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by wangchao on 2016/1/7.
 */
public class KeyboardHelper {
    //http://stackoverflow.com/questions/1109022/close-hide-the-android-soft-keyboard

    /**
     * 隐藏键盘
     * 不适用fragment.getactivity()
     * @param activity
     */
    public static void hide(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if(view == null) {
            view = new View(activity);
        }
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
