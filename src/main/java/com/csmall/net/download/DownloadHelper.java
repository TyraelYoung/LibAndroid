package com.csmall.net.download;

/**
 * Created by wangchao on 2017/2/28.
 */

public class DownloadHelper {
    /**
     *
     * @param url 下载地址
     * @param path 存储目的地
     * @param listener 下载监听
     * @return
     */
    public static AbstractDownloadTask download(String url, String path, DownLoadListener listener){
        AbstractDownloadTask task = new OkHttpDownloadTask(url, path, listener);
        task.run();
        return task;
    }
}
