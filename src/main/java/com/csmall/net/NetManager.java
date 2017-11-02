package com.csmall.net;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by wangchao on 2015/12/21.
 */
public class NetManager {
    public static final NetManager sManager = new NetManager();

    public static NetManager getInstance(){
        return sManager;
    }

    //@Gaurdedby this
    private List<WeakReference<NetListener>> mList = new ArrayList<WeakReference<NetListener>>();

    public synchronized void onNetChange(){
//        Context context = MyApplication.getApplication();
//            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//            NetworkInfo mobileInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
//            NetworkInfo wifiInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
//            NetworkInfo activeInfo = manager.getActiveNetworkInfo();
//            Toast.makeText(context, "mobile:"+mobileInfo.isConnected()+"\n"+"wifi:"+wifiInfo.isConnected()
//                    +"\n"+"active:"+activeInfo.getTypeName(), 1).show();
        if(NetHelper.isConnected()){
            //TODO 考虑网络从移动网，变为wifi的情况。可以不通知了。
            Iterator<WeakReference<NetListener>> it = mList.iterator();
            while (it.hasNext()){
                WeakReference<NetListener> wr = it.next();
                NetListener l = wr.get();
                if(l == null){
                    it.remove();
                }else{
                    l.onConnect();
                }
            }
        }
    }

    public synchronized void registerListener(WeakReference<NetListener> wr){
        mList.add(wr);
    }

}
