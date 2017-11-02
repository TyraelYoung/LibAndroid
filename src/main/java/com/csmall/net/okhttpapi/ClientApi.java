package com.csmall.net.okhttpapi;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by wangchao on 2017/2/28.
 */

public class ClientApi {
    private static OkHttpClient clientNoConfig = new OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build();

    public static OkHttpClient getClientNoConfig() {
        return clientNoConfig;
    }
}
