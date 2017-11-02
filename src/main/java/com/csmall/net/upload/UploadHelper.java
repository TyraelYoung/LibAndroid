package com.csmall.net.upload;


import java.util.Map;

/**
 * Created by wangchao on 2017/3/2.
 */

public class UploadHelper {

    public static AbstractUploadTask upload(String uploadApiUrl,UpLoadListener listener, Map<String, String> files, Map<String, String> params){
        AbstractUploadTask task = new OkHttpUploadTask(uploadApiUrl, listener, files, params);
        task.run();
        return task;
    }
}
