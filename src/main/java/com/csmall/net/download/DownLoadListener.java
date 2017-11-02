package com.csmall.net.download;

/**
 * Created by wangchao on 2017/2/28.
 */

public interface DownLoadListener {
    void onError(int code, String message);
    void onSuccess();
    void onProgress(long current, long total);
}
