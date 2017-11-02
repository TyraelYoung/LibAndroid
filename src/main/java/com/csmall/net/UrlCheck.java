package com.csmall.net;

/**
 * Created by wangchao on 2017/3/2.
 */

public class UrlCheck {
    /**
     * 有些url不完整
     * @param url
     * @return
     */
    public static String check(String url){
        if(url.startsWith("//")){
            return String.format("https:%s", url);
        }
        return url;
    }
}
