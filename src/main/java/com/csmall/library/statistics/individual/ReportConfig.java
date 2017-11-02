package com.csmall.library.statistics.individual;

import android.util.Log;

import com.csmall.android.DeviceHelper;
import com.csmall.android.PreferenceHelper;
import com.csmall.json.individual.User;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/**
 *  什么时间，需不需要上报
 *  跟后台通信
 * Created by wangchao on 2016/6/8.
 */
public class ReportConfig {
    public static final String TAG = "ReportConfig";
    public static final String PREF_KEY_LAST_TIME = TAG + "PREF_KEY_LAST_TIME";
    public static final String PREF_KEY_INTERVAL = TAG + "PREF_KEY_INTERVAL";
    public static final String PREF_KEY_IS_REPORT = TAG + "PREF_KEY_IS_REPORT";

    private static ReportConfig sConfig = new ReportConfig();

    private PreferenceHelper mPrefHelper = new PreferenceHelper();

    public static ReportConfig getInstance(){
        return sConfig;
    }

    /**
     * 上一次与后台沟通的时间
     */
    private long mLastTimeCommunicate;
    /**
     * 一个月
     * 0表示再也不沟通
     */
    private long mIntervalCommunicate = 7 * 24 *60 * 60 * 1000;

    /**
     * 是否上报
     */
    private boolean isReport = false;

    private ReportConfig() {
        readConfig();
    }

    public boolean isReport(){
        return isReport;
    }

    public boolean isLog(){
        return true;
    }

    /**
     * 是否需要更新配置
     */
    public void ifGetServerConfig(User user){
        Log.i(TAG, "ifGetServerConfig:");
        if(mLastTimeCommunicate >0 && System.currentTimeMillis() - mLastTimeCommunicate < mIntervalCommunicate){
            Log.i(TAG, "ifGetServerConfig:条件不满足");
            return;
        }
        getServerConfig(user);
    }

    private void getServerConfig(User user){
        Log.i(TAG, "ifGetServerConfig:");
        Gson gson = new Gson();
        Map<String, String> param  = new HashMap<>();
        param.put("user", gson.toJson(user));
        param.put("device", gson.toJson(DeviceHelper.getDevice()));
//        StringRequest request = new StringRequestWithHeaderParam(Request.Method.POST, UrlIndividual.getConfig(), new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Log.i(TAG, "getServerConfig:" + response);
//                try{
//                    Config config = new Gson().fromJson(response, Config.class);
//                    //新的配置
//                    mLastTimeCommunicate = System.currentTimeMillis();
//                    isReport = config.isReport;
//                    setInterval(config.intervalCommunicateMinute * 60 * 1000);
//                    writeConfig();
//                }catch (JsonSyntaxException e){
//                    Log.e(TAG, "", e);
//                }
//             }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.i(TAG, "getServerConfig:onErrorResponse" + error);
//            }
//        }, param, null);
//        VolleyHelper.getInstance().send(request);

    }

    private void setInterval(long interval){
        if(interval < 0){
            mIntervalCommunicate = 7 * 24 *60 * 60 * 1000;
            return;
        }
        mIntervalCommunicate = interval;
    }

    private void readConfig(){
        mLastTimeCommunicate = mPrefHelper.getLong(PREF_KEY_LAST_TIME);
        mIntervalCommunicate = mPrefHelper.getLong(PREF_KEY_INTERVAL);
        isReport = mPrefHelper.getBoolean(PREF_KEY_IS_REPORT);
    }

    private void writeConfig(){
        mPrefHelper.putLong(PREF_KEY_LAST_TIME, mLastTimeCommunicate);
        mPrefHelper.putLong(PREF_KEY_INTERVAL, mIntervalCommunicate);
        mPrefHelper.putBoolean(PREF_KEY_IS_REPORT, isReport);
    }

}
