package com.csmall.net;

/**
 * Created by csmallTech on 2017/3/6.
 */

public class UrlUtil {
    /**
     * url编码
     * @param url
     * @return
     */
    public static String encode(String url){
        UrlParser parser = new UrlParser(url);
        parser.parse();
        return parser.encode();
    }
}
