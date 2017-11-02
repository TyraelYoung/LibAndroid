package com.csmall.image;

import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by 王超 on 2017/10/12.
 */

public class ImageHelper {
    private static final ImageLoader sfImageLoader = ImageLoader.getInstance();

    public static void display(ImageView imageView, String url){
        sfImageLoader.displayImage(url, imageView);
    }
}
