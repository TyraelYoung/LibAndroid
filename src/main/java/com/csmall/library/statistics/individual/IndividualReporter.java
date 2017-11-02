package com.csmall.library.statistics.individual;

import android.util.Log;

import com.csmall.util.FileUtil;
import com.csmall.util.ZipUtil;

//import org.xutils.common.Callback;
//import org.xutils.http.HttpMethod;
//import org.xutils.http.RequestParams;

/**
 * 负责一次上传任务。
 * 个体的行为报告
 * 1. 行为写入文件
 * 2. 定期上报
 *
 * @Deprecated
 * 暂时没有使用
 *
 * Created by wangchao on 2016/6/7.
 */
public class IndividualReporter {

    private static final String TAG = "IndividualReporter";

    // 先简单处理
    public void report(){
        Log.i(TAG, "IndividualReporter:" + ReportConfig.getInstance().isReport());
        if(!ReportConfig.getInstance().isReport()){
            return;
        }
        String dir = IndividualLog.getInstance().prepareUpload();
        String des = zip(dir);
        FileUtil.delDir(dir);
        if(des == null){
            return;
        }
        upload(des);
    }

    //打个包
    private String zip(String dir) {
        String des = Dir.fileZip();
        String zip = ZipUtil.zipDir(dir, des);
        if (zip == null) {
            Log.i(TAG, "zip == null");
            return null;
        }
        return des;
    }

    public void upload(final String path){
        //Log.d(TAG, "path = " + path);
        if(path == null){
            return;
        }

//        RequestParams params = new RequestParams(UrlIndividual.getUplaod());
//        params.addParameter("param", new Gson().toJson(DeviceHelper.getDevice()));
//        params.addBodyParameter("file", new File(path), null);
//            //改组件需添加字符信息才能传文件
//        params.addBodyParameter("test", "test");
//        UploadManager.send(HttpMethod.POST, params, new Callback.CommonCallback<String>(){
//            @Override
//            public void onSuccess(String result) {
//                Log.d(TAG, "onSuccess");
//                 //成功则删除本地压缩包
//                new File(path).delete();
//            }
//
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//                Log.d(TAG, "onError", ex);
//                //删除本地压缩包
//                new File(path).delete();
//            }
//
//            @Override
//            public void onCancelled(CancelledException cex) {
//
//            }
//
//            @Override
//            public void onFinished() {
//
//            }
//        });
    }

}
