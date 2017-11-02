package com.csmall.net.upload;

/**
 * Created by wangchao on 2017/2/28.
 */

public interface UpLoadListener {
    void onError(int code, String message);
    void onSuccess(String responseBody);
    void onProgress(long current, long total);
}
