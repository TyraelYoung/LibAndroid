package com.csmall.net.download;

import com.csmall.log.LogHelper;
import com.csmall.net.okhttpapi.ClientApi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * TODO 测试下载
 * TODO 下载中，把文件命名为临时文件，防止下载中断。下载完成了，再重命名。
 * TODO 断点续传
 * Created by wangchao on 2017/2/28.
 */

public class OkHttpDownloadTask extends AbstractDownloadTask {
    private static final String TAG = "OkHttpDownloadTask";
    private OkHttpClient client;

    protected OkHttpDownloadTask(String srcUrl, String destPath, DownLoadListener listener) {
        super(srcUrl, destPath, listener);
        //设置OkHttpClient
        client = ClientApi.getClientNoConfig();
    }

    @Override
    public void run() {
        final File file = new File(destPath);
        file.delete();
//        try {
//            //没有文件的话，后面无法输出
//            file.createNewFile();
//        } catch (IOException e) {
//            LogHelper.e(TAG, e);
//        }
        final Request request = new Request.Builder().url(srcUrl).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                notifyError(0, "下载失败");

                // if (e.getCause().equals(SocketTimeoutException.class)) {
                // sendFailCallback(callBack, new Exception("连接超时，请稍后重试."));
                // } else {
                // sendFailCallback(callBack, e);
                // }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream is = null;
                byte[] buf = new byte[2048];
                int len = 0;
                FileOutputStream fos = null;
                try {
                    long total = response.body().contentLength();
                    //LogHelper.d(TAG, "total------>" + total);
                    long current = 0;
                    is = response.body().byteStream();
                    fos = new FileOutputStream(file);
                    while ((len = is.read(buf)) != -1) {

                        fos.write(buf, 0, len);
                        long preProgress = current * 100 / total;
                        current += len;
                        long progress = current * 100 / total;
                        //LogHelper.d(TAG, "current------>" + readedSize + "|" + currentProgress);

                         if (progress - preProgress >= 1){
                             if(listener != null){
                                 listener.onProgress(current, total);
                             }
                         }
                    }
                    fos.flush();
                    notifySuccess();
                } catch (IOException e) {
                    LogHelper.w(TAG, "",e);
                    notifyError(0, "读取网络输入流错误");
                } finally {
                    try {
                        if (is != null) {
                            is.close();
                        }
                        if (fos != null) {
                            fos.close();
                        }
                    } catch (IOException e) {
                        LogHelper.e(TAG, e.toString());
                    }
                }
            }
        });
    }

    private void notifyError(int code, String msg){
        LogHelper.w(TAG, String.format("下载失败：code:%d:msg:%s", code, msg));
        if(listener != null){
            listener.onError(0, "下载失败");
        }
    }

    private void notifySuccess(){
        if(listener != null){
            listener.onSuccess();
        }
    }
}
