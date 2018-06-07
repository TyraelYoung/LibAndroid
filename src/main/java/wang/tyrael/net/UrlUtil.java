package wang.tyrael.net;

import java.net.MalformedURLException;

import wang.tyrael.http.UrlParser;

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
        UrlParser parser = parser = new UrlParser(url);
        parser.parse();
        return parser.encode();
    }
}
