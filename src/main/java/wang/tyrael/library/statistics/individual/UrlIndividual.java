package wang.tyrael.statistics.individual;

/**
 * Created by wangchao on 2016/6/12.
 */
public class UrlIndividual {
    public static final String URL_BASE_LOCAL = "http://192.168.123.1:8088/CsmallStat";
//    public static final String URL_BASE = "http://csmallstat.applinzi.com";
    public static final String URL_ALI = "http://120.25.99.98";

    private static String base = URL_ALI;

    /**
     * 方便设置测试和正式环境
     * @param h
     */
    public static void setBase(String h){
        base = h;
    }

    public static String getConfig(){
        return String.format("%s/ConfigServlet", base);
    }

    public static String getUplaod(){
        return String.format("%s/FileReceiveServlet", base);
    }

}
