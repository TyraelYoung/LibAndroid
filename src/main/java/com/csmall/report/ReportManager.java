package com.csmall.report;

import android.content.Context;

import com.csmall.android.application.ApplicationHolder;
import com.csmall.android.DebugHelper;
import com.csmall.log.LogHelper;
//import com.tencent.stat.StatService;

import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * 网络接口添加上报步骤：
 * 1. http://mta.qq.com/mta/custom/ctr_event_conf?app_id=3101980559
 * 到该网址，“新增事件”添加一个id
 * 2. 该类中添加一个常量ID，值就是上面的事件id
 * 3. 调用ReportSuccess，把常量ID输进来啊。
 * <p/>
 * Created by wangchao on 2015/10/28.
 */
public class ReportManager {

    public static final int ERROR_CODE_DEFAULT = 1000;

    private static final ReportManager sReportManager = new ReportManager();
    private static final Context sContext = ApplicationHolder.getApplication();
    private static final String TAG = "ReportManager";

    public static void initOnAppCreate(){
//        MtaApi.getInstance();
    }

    public static ReportManager getInstance() {
        return sReportManager;
    }

    private ReportManager() {

    }

    public void reportEvent(EventData ed){
        LogHelper.d(TAG, "reportEvent:" + ed.id);
        Properties prop = new Properties();
        Iterator<Map.Entry<String, String>> iterator = ed.props.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, String> entry = iterator.next();
            prop.setProperty(entry.getKey(), entry.getValue()); // 活动页面
        }
//        StatService.trackCustomKVEvent(sContext, ed.id, prop);
    }

    public void reportClick(String id){
        //mta
        Properties prop = new Properties();
        prop.setProperty("name", "ok"); // 活动页面
//        StatService.trackCustomKVEvent(sContext, id, prop);

    }

    /**
     * @param id 后台定义的时间id
     */
    public void reportSuccess(String id) {
        Properties prop = new Properties();
        prop.setProperty("ErrorCode", "0"); // 活动页面
//        StatService.trackCustomKVEvent(sContext, id, prop);
    }

    public void reportException(String msg, Throwable e) {
        LogHelper.w(TAG, msg, e);
//        StatService.reportError(sContext, msg);
//        StatService.reportException(sContext, e);
    }

    /**
     * @param id
     * @param errorCode 必须大于0
     */
    public void reportFail(String id, int errorCode) {
        //上报错误，可以告警
//        StatService.reportError(sContext, id);

        //上报错误吗，方便查看百分比
        if (errorCode < 0) {
            if (DebugHelper.isDebuggable()) {
                throw new RuntimeException("errorCode < 0");
            } else {
//                LogHelper.w(TAG, "errorCode < 0");
            }
        }
        Properties prop = new Properties();
        prop.setProperty("ErrorCode", String.valueOf(errorCode)); // 活动页面
//        StatService.trackCustomKVEvent(sContext, id, prop);
    }

    public void reportFail(String tag, String msg) {
        //上报错误，可以告警
//        StatService.reportError(sContext, tag + ": " + msg);
        LogHelper.w(tag, msg);
    }

    /**
     * 上报与服务器的接口的成功率
     */
    public void reportServer(ServerFaceInfo info) {
//// 新建监控接口对象
//        StatAppMonitor monitor = new StatAppMonitor(info.mId);
//// 设置接口耗时
//        monitor.setMillisecondsConsume(info.mDuration);
//        int retCode = info.mCode;
//// 设置接口返回码
//        monitor.setReturnCode(info.mCode);
//// 设置请求包大小，若有的话
////            monitor.setReqSize(1000);
//// 设置响应包大小，若有的话
////            monitor.setRespSize(2000);
//// 设置抽样率
//        // 默认为1，表示100%。
//        // 如果是50%，则填2(100/50)，如果是25%，则填4(100/25)，以此类推。
////            monitor.setSampling(2);
//        if (retCode == 0) {
//// 标记为成功
//            monitor.setResultType(StatAppMonitor.SUCCESS_RESULT_TYPE);
//        } else if (retCode < 0) {
//            //逻辑失败，可能是业务数据有问题。
//// 标记为逻辑失败，可能由网络未连接等原因引起的， 但对于业务来说不是致命的，是可容忍的
//            monitor.setResultType(StatAppMonitor.LOGIC_FAILURE_RESULT_TYPE);
//        } else {
//            //网络失败
//// 接口调用出现异常，致命的，标识为失败
//            monitor.setResultType(StatAppMonitor.FAILURE_RESULT_TYPE);
//        }
//// 上报接口监控
//        StatService.reportAppMonitorStat(MyApplication.getApplication(), monitor);
    }

    /**
     * 不记录日志，只上报
     * @param msg
     * @param e
     */
    public void reportExceptionOnly(String msg, Throwable e) {
//        StatService.reportException(sContext, e);
    }
}


