package com.csmall;

import android.content.Context;
import android.graphics.Bitmap;

import com.csmall.android.DebugHelper;
import com.csmall.android.application.ApplicationHolder;
import com.csmall.crash.BuglyApi;
import com.csmall.log.LogHelper;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by wangchao on 2017/3/29.
 */

public class LibInitManager {

    public static void initOnAppCreate(LibInitData data) {
        LogHelper.init();
//        ApplicationHolder.setApplication(data.app);
        BuglyApi.initOnAppCreate();
        //MailSender.setMailConfig(data.mailConfig);
        initImageLoader(ApplicationHolder.getApplication());
    }


    public static void initImageLoader(Context context) {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        ImageLoaderConfiguration.Builder builder = new ImageLoaderConfiguration.Builder(
                context)
                .defaultDisplayImageOptions(options);
        if(DebugHelper.isDebuggable()){
            builder.writeDebugLogs();
        }
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(builder.build());

    }
}
