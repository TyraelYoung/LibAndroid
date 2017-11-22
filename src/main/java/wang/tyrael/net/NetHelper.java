package wang.tyrael.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import wang.tyrael.android.application.ApplicationHolder;
import wang.tyrael.log.LogHelper;

/**
 * Created by wangchao on 2015/9/15.
 */
public class NetHelper {
    private static final String TAG = "NetHelper";

    public static boolean isConnected() {
        Context context = ApplicationHolder.getApplication();
        // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
        try {
            ConnectivityManager connectivity = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity != null) {
                // 获取网络连接管理的对象
                NetworkInfo info = connectivity.getActiveNetworkInfo();
                if (info != null&& info.isConnected()) {
                    // 判断当前网络是否已经连接
                    if (info.getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            LogHelper.e(TAG, "error", e);
        }
        return false;
    }
}
