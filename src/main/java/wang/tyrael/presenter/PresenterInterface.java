package wang.tyrael.presenter;

import android.content.Intent;
import android.os.Bundle;

/**
 * Created by 王超 on 2017/9/2.
 */

public interface PresenterInterface {
    void handle(Event event);

    void onCreate();
    void onCreate(Bundle bundle);
    void onCreate(Intent intent);
    void onStart();
    void onStop();
    void onDestroy();
}
