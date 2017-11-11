package com.csmall.net.okhttp;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import wang.tyrael.library.http.okhttpapi.CookieJarSupport;

/**
 * Created by 王超 on 2017/11/11.
 */

public class ClientFactory {
    private static final long PING_INTERVAL_MS = (long) (50 * 1000);

    private static OkHttpClient clientWithCookie = new OkHttpClient().newBuilder()
            .cookieJar(new CookieJarSupport())
            .pingInterval(PING_INTERVAL_MS, TimeUnit.MILLISECONDS)
            .addNetworkInterceptor(new StethoInterceptor())
            .build();

    public static OkHttpClient getClientWithCookie() {
        return clientWithCookie;
    }
}
