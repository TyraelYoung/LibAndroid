package com.csmall.net.upload;

import com.csmall.log.LogHelper;
import com.csmall.net.download.DownLoadListener;
import com.csmall.net.okhttpapi.ClientApi;
import com.csmall.util.FileUtil;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * https://github.com/square/okhttp/blob/master/samples/guide/src/main/java/okhttp3/recipes/PostMultipart.java
 * Created by wangchao on 2017/3/1.
 */

public class OkHttpUploadTask extends AbstractUploadTask {
    private static final java.lang.String TAG = "OkHttpUploadTask";
    OkHttpClient client = ClientApi.getClientNoConfig();

    protected OkHttpUploadTask(String uploadApiUrl,UpLoadListener listener,  Map<String, String> files, Map<String, String> params) {
        super(uploadApiUrl, listener, files, params);
    }

    @Override
    public void run() {
        LogHelper.d(TAG, "uploadApiUrl:" + uploadApiUrl);
        MediaType mt = getMediaType();
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);

        Iterator<Map.Entry<String, String>> iterator = files.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, String> item = iterator.next();
            String path = item.getValue();
            String name = FileUtil.parseName(path);
            builder.addFormDataPart(item.getKey(), name, RequestBody.create(mt, new File(path)));
            LogHelper.d(TAG, "upload:" + path);
        }
        Iterator<Map.Entry<String, String>> iterator2 = otherParams.entrySet().iterator();
        while(iterator2.hasNext()){
            Map.Entry<String, String> item = iterator2.next();
            builder.addFormDataPart(item.getKey(), item.getValue());
        }
        RequestBody requestBody = builder.build();

        Request request = new Request.Builder()
                .url(this.uploadApiUrl)
                .post(requestBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                LogHelper.d(TAG, "upload:fail");
                notifyError(0, "上传失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                LogHelper.d(TAG, "upload:sucess");
                body = response.body().string();
                LogHelper.d(TAG, "upload:sucess:" + body);
                notifySuccess(body);
            }
        });

    }

    /**
     *
     * @return
     */
    private MediaType getMediaType(){
        return MediaType.parse("application/octet-stream");
    }

    private void notifyError(int code, String msg){
        LogHelper.w(TAG, String.format("下载失败：code:%d:msg:%s", code, msg));
        if(listener != null){
            listener.onError(0, "下载失败");
        }
    }

    private void notifySuccess(String responseBody){
        if(listener != null){
            listener.onSuccess(responseBody);
        }
    }
}
