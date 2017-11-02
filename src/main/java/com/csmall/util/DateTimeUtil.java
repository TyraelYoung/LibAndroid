package com.csmall.util;


import android.text.TextUtils;

import com.csmall.util.TimeInfo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DateTimeUtil {

    private static final long INTERVAL_IN_MILLISECONDS = 30000L;

    /**
     *
     * @return 00:00
     */
    public static String formatMinSec(int ms){
        int secTotal = ms / 1000;
        int sec = secTotal % 60;
        int minTotal = secTotal / 60;
        if(minTotal > 9){
            return String.valueOf(minTotal) + ":" + String.format("%02d", sec);
        }else{
            return String.format("%02d", minTotal) + ":" + String.format("%02d", sec);
        }
    }

    public static String getForamtTimes(String data) {

        SimpleDateFormat mat = new SimpleDateFormat("yyyy.MM.dd");
        String d = mat.format(new Date(Long.valueOf(data) * 1000));
        return d;
    }

    public static String getTimes(String data) {

        SimpleDateFormat mat = new SimpleDateFormat("yyyy-MM-dd");
        String d = mat.format(new Date(Long.valueOf(data) * 1000));
        return d;
    }

    public static String getDateAndTimes(String data) {
        String t1 = "";
        if (!data.equals("")) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date d1 = new Date(Long.valueOf(data) * 1000);
            t1 = format.format(d1);
        }
        return t1;
    }

    public static String getSpaceTime(long oldtime, long newtime) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String value = "";
        try {
            long diff = newtime - oldtime;//这样得到的差值是微秒级别
            long days = diff / (60 * 60 * 24);
            long hours = (diff - days * (60 * 60 * 24)) / (60 * 60);
            long minutes = (diff - days * (60 * 60 * 24) - hours * (60 * 60)) / (60);
            long second = (diff - days * (60 * 60 * 24) - hours * (60 * 60) - minutes * (60));
            value = String.format("%02d", days) + "天" + String.format("%02d", hours) + "小时" + String.format("%02d", minutes) + "分" + String.format
                    ("%02d", second) + "秒";
        } catch (Exception e) {
        }
        return value;
    }

    public static long getTwoDay(long oldtime, long newtime) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            long diff = newtime - oldtime;//这样得到的差值是微秒级别
            long days = diff / (60 * 60 * 24);
            return days;
        } catch (Exception e) {
        }
        return 0;
    }

    public static String diffTime(String time) {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        long d2 = date.getTime();
        long d1 = Long.valueOf(time) * 1000;
        long diff = (d2 - d1);
        long days = diff / (1000 * 60 * 60 * 24);
        long hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        long minutes = (diff - days * (1000 * 60 * 60 * 24) - hours
                * (1000 * 60 * 60))
                / (1000 * 60);

        String timeString;
        if (days > 0) {
            timeString = days + "天前";
        } else if (hours > 0) {
            timeString = hours + "小时前";
        } else {
            timeString = minutes + "分钟前";
        }

        return timeString;

    }

    public static Date stringToDate(String dateString) {
        ParsePosition position = new ParsePosition(0);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateValue = simpleDateFormat.parse(dateString, position);
        return dateValue;
    }

    /**
     * 调用此方法输入所要转换的时间戳输入例如（1402733340）输出（"16:09"）
     */
    public static String timetHour(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("HH:mm");
        @SuppressWarnings("unused")
        long lcc = Long.valueOf(time);
        int i = Integer.parseInt(time);
        String times = sdr.format(new Date(i * 1000L));
        return times;

    }

    /**
     * 获取现在时间
     *
     * @return返回短时间格式 yyyyMMdd
     */
    public static String getStringToday() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 调用此方法输入所要转换的时间戳输入例如（1402733340）输出（"20140614"）
     */
    public static String timetDay(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyyMMdd");
        @SuppressWarnings("unused")
        long lcc = Long.valueOf(time);
        int i = Integer.parseInt(time);
        String times = sdr.format(new Date(i * 1000L));
        return times;

    }

    /**
     * 得到二个日期间的间隔天数
     */
    public static String getTwoDay(String sj1, String sj2) {
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyyMMdd");
        long day = 0;
        try {
            java.util.Date date = myFormatter.parse(sj1);
            java.util.Date mydate = myFormatter.parse(sj2);
            day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
        } catch (Exception e) {
            return "";
        }
        return day + "";
    }

    public static String getTimestampString(Date var0) {
        String var1 = null;
        long var2 = var0.getTime();
        if (isSameDay(var2)) {
            Calendar var4 = GregorianCalendar.getInstance();
            var4.setTime(var0);
            int var5 = var4.get(Calendar.HOUR_OF_DAY);
            if (var5 > 17) {
                var1 = "晚上 hh:mm";
            } else if (var5 >= 0 && var5 <= 6) {
                var1 = "凌晨 hh:mm";
            } else if (var5 > 11 && var5 <= 17) {
                var1 = "下午 hh:mm";
            } else {
                var1 = "上午 hh:mm";
            }
        } else if (isYesterday(var2)) {
            var1 = "昨天 HH:mm";
        } else {
            var1 = "M月d日 HH:mm";
        }

        return (new SimpleDateFormat(var1, Locale.CHINA)).format(var0);
    }

    public static boolean isCloseEnough(long var0, long var2) {
        long var4 = var0 - var2;
        if (var4 < 0L) {
            var4 = -var4;
        }

        return var4 < 30000L;
    }

    private static boolean isSameDay(long var0) {
        TimeInfo var2 = getTodayStartAndEndTime();
        return var0 > var2.getStartTime() && var0 < var2.getEndTime();
    }

    private static boolean isYesterday(long var0) {
        TimeInfo var2 = getYesterdayStartAndEndTime();
        return var0 > var2.getStartTime() && var0 < var2.getEndTime();
    }

    public static Date StringToDate(String var0, String var1) {
        SimpleDateFormat var2 = new SimpleDateFormat(var1);
        Date var3 = null;

        try {
            var3 = var2.parse(var0);
        } catch (ParseException var5) {
            var5.printStackTrace();
        }

        return var3;
    }

    public static String toTime(int var0) {
        var0 /= 1000;
        int var1 = var0 / 60;
        boolean var2 = false;
        if (var1 >= 60) {
            int var4 = var1 / 60;
            var1 %= 60;
        }

        int var3 = var0 % 60;
        return String.format("%02d:%02d", Integer.valueOf(var1), Integer.valueOf(var3));
    }

    public static String toTimeBySecond(int var0) {
        int var1 = var0 / 60;
        boolean var2 = false;
        if (var1 >= 60) {
            int var4 = var1 / 60;
            var1 %= 60;
        }

        int var3 = var0 % 60;
        return String.format("%02d:%02d", Integer.valueOf(var1), Integer.valueOf(var3));
    }

    public static TimeInfo getYesterdayStartAndEndTime() {
        Calendar var0 = Calendar.getInstance();
        var0.add(Calendar.DAY_OF_MONTH, -1);
        var0.set(Calendar.HOUR_OF_DAY, 0);
        var0.set(Calendar.MINUTE, 0);
        var0.set(Calendar.SECOND, 0);
        var0.set(Calendar.MILLISECOND, 0);
        Date var1 = var0.getTime();
        long var2 = var1.getTime();
        Calendar var4 = Calendar.getInstance();
        var4.add(Calendar.DAY_OF_MONTH, -1);
        var4.set(Calendar.HOUR_OF_DAY, 23);
        var4.set(Calendar.MINUTE, 59);
        var4.set(Calendar.SECOND, 59);
        var4.set(Calendar.MILLISECOND, 999);
        Date var5 = var4.getTime();
        long var6 = var5.getTime();
        TimeInfo var8 = new TimeInfo();
        var8.setStartTime(var2);
        var8.setEndTime(var6);
        return var8;
    }

    public static TimeInfo getTodayStartAndEndTime() {
        Calendar var0 = Calendar.getInstance();
        var0.set(Calendar.HOUR_OF_DAY, 0);
        var0.set(Calendar.MINUTE, 0);
        var0.set(Calendar.SECOND, 0);
        var0.set(Calendar.MILLISECOND, 0);
        Date var1 = var0.getTime();
        long var2 = var1.getTime();
        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss S");
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        Date var6 = todayEnd.getTime();
        long var7 = var6.getTime();
        TimeInfo var9 = new TimeInfo();
        var9.setStartTime(var2);
        var9.setEndTime(var7);
        return var9;
    }

    /**
     * 计算两个时间戳的时间间隔，返回的String[]数组为时间间隔的{时,分,秒}
     * @param fT 较早的时间
     * @param sT 较晚的时间
     * @return
     */
    public static String[] getTimeInterval(String fT,String sT){
        if (TextUtils.isEmpty(fT) || TextUtils.isEmpty(sT)){
            return new String[]{"00","00","00"};
        }
        long diff = Long.parseLong(sT) - Long.parseLong(fT);
        if (diff <= 0){
            return new String[]{"00","00","00"};
        }
        long hour = diff / (60 * 60);
        long min = diff / 60 - hour * 60;
        long sec = diff - hour * 60 * 60 - min * 60;
        return new String[]{String.format("%02d",hour),String.format("%02d",min),String.format("%02d",sec)};
    }
}

