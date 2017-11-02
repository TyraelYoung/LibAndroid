package com.csmall.net.upload;

import com.csmall.net.download.DownLoadListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangchao on 2017/3/1.
 */

public abstract class AbstractUploadTask {
    public final String uploadApiUrl;

    protected Map<String, String> files = new HashMap<>();
    protected Map<String, String> otherParams = new HashMap<>();

    /**
     * 上传以后的资源地址
     */
//    public String resUrl;
    public String body;
    public final UpLoadListener listener;

    protected AbstractUploadTask(String uploadApiUrl, UpLoadListener listener,  Map<String, String> files, Map<String, String> params) {
        this.uploadApiUrl = uploadApiUrl;
        this.listener = listener;
        this.files = files;
        this.otherParams = params;
    }

    /**
     * 文件字段
     * 目前测试过上传一个文件，要是有多个文件，需要再调试下。
     * @param paramKey
     * @param filePath
     */
    public void putFile(String paramKey, String filePath){
        files.put(paramKey, filePath);
    }

    /**
     * 其他参数字段
     * @param key
     * @param value
     */
    public void putParam(String key, String value){
        otherParams.put(key, value);
    }

    public abstract void run();
    public String getResult(){
        return body;
    }
}
