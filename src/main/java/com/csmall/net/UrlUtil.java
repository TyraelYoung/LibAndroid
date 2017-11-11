package com.csmall.net;

import java.net.MalformedURLException;

import wang.tyrael.library.http.UrlParser;

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
        UrlParser parser = null;
        try {
            parser = new UrlParser(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        parser.parse();
        return parser.encode();
    }
}
