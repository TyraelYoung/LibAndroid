package com.csmall.net;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.csmall.thread.ThreadPoolManager;

/**
 * Created by wangchao on 2015/12/21.
 */
public class ConnectChangeReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            //Toast.makeText(context, intent.getAction(), 1).show();
//            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//            NetworkInfo mobileInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
//            NetworkInfo wifiInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
//            NetworkInfo activeInfo = manager.getActiveNetworkInfo();
//            Toast.makeText(context, "mobile:"+mobileInfo.isConnected()+"\n"+"wifi:"+wifiInfo.isConnected()
//                    +"\n"+"active:"+activeInfo.getTypeName(), 1).show();
            ThreadPoolManager.getInstance().getNormal().submit(new Runnable() {
                @Override
                public void run() {
                    NetManager.getInstance().onNetChange();
                }
            });

        }  //如果无网络连接activeInfo为null


}
