package com.csmall.net;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangchao on 2015/12/23.
 */
public class UrlParser {
    private static final String TAG = "UrlParser";
    public final String original;
    private String path;
    private Map<String, String> params;

    public UrlParser(String original) {
        this.original = original;
    }

    public Map<String, String> getParams(){
        if(params == null){
            parse();
        }
        //仍然为空，说明没有参数
        if(params == null){
            params = new HashMap<String, String>();
        }
        return Collections.unmodifiableMap(params);
    }



    /**
     * 解析出参数
     */
    public void parse(){
        String strURL = original;
        String[] arrSplit = null;
//        strURL = strURL.trim().toLowerCase();
        arrSplit = strURL.split("[?]");
        if (strURL.length() > 0) {
            if (arrSplit[0] != null) {
                path = arrSplit[0];
            }
            if (arrSplit.length > 1) {
                if (arrSplit[1] != null) {
                    params =  parseParam(arrSplit[1]);
                }
            }
        }
    }

    /**
     * 仅对参数进行url编码
     * @return
     */
    public String encode(){
        StringBuilder sb = new StringBuilder(path);
        if(params ==null || params.isEmpty()){
            return sb.toString();
        }
        sb.append("?");
        for(Map.Entry<String, String> entry : params.entrySet()){
            sb.append(entry.getKey());
            sb.append("=");
            String value = null;
            try {
                value = URLEncoder.encode(entry.getValue(), "utf-8");
            } catch (UnsupportedEncodingException e) {
//                ReportManager.getInstance().reportException(TAG, e);
                value = entry.getValue();
            }
            sb.append(value);
            sb.append("&");
        }
        //删除最后一个“&”
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }


    /**
     * 解析出url请求的路径，包括页面
     *
     * @param strURL url地址
     * @return url路径
     */
    public static String UrlPage(String strURL) {
        String strPage = null;
        String[] arrSplit = null;
        strURL = strURL.trim().toLowerCase();
        arrSplit = strURL.split("[?]");
        if (strURL.length() > 0) {
            if (arrSplit.length > 1) {
                if (arrSplit[0] != null) {
                    strPage = arrSplit[0];
                }
            }
        }
        return strPage;
    }

    /**
     * 去掉url中的路径，留下请求参数部分
     *
     * @param strURL url地址
     * @return url请求参数部分
     */
    private static String TruncateUrlPage(String strURL) {
        String strAllParam = null;
        String[] arrSplit = null;
        strURL = strURL.trim().toLowerCase();
        arrSplit = strURL.split("[?]");
        if (strURL.length() > 1) {
            if (arrSplit.length > 1) {
                if (arrSplit[1] != null) {
                    strAllParam = arrSplit[1];
                }
            }
        }
        return strAllParam;
    }

    /**
     * 解析出url参数中的键值对
     * 如 "index.jsp?Action=del&id=123"，解析出Action:del,id:123存入map中
     *
     * @param URL url地址
     * @return url请求参数部分
     */
    public static Map<String, String> URLRequest(String URL) {
        Map<String, String> mapRequest = new HashMap<String, String>();
        String[] arrSplit = null;
        String strUrlParam = TruncateUrlPage(URL);
        if (strUrlParam == null) {
            return mapRequest;
        }
//每个键值为一组
        arrSplit = strUrlParam.split("[&]");
        for (String strSplit : arrSplit) {
            String[] arrSplitEqual = null;
            arrSplitEqual = strSplit.split("[=]");
//解析出键值
            if (arrSplitEqual.length > 1) {
//正确解析
                mapRequest.put(arrSplitEqual[0], arrSplitEqual[1]);
            } else {
                if (arrSplitEqual[0] != "") {
//只有参数没有值，不加入
                    mapRequest.put(arrSplitEqual[0], "");
                }
            }
        }
        return mapRequest;
    }

    /**
     * 解析出url参数中的键值对
     * 如 "index.jsp?Action=del&id=123"，解析出Action:del,id:123存入map中
     *
     */
    public static Map<String, String> parseParam(String strUrlParam) {
        Map<String, String> mapRequest = new HashMap<String, String>();
        String[] arrSplit = null;
        if (strUrlParam == null) {
            return mapRequest;
        }
//每个键值为一组
        arrSplit = strUrlParam.split("[&]");
        for (String strSplit : arrSplit) {
            String[] arrSplitEqual = null;
            arrSplitEqual = strSplit.split("[=]");
//解析出键值
            if (arrSplitEqual.length > 1) {
//正确解析
                mapRequest.put(arrSplitEqual[0], arrSplitEqual[1]);
            } else {
                if (arrSplitEqual[0] != "") {
//只有参数没有值，不加入
                    mapRequest.put(arrSplitEqual[0], "");
                }
            }
        }
        return mapRequest;
    }
}
