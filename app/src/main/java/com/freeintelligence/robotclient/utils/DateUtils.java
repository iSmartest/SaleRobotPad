package com.freeintelligence.robotclient.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateUtils {
    /**
     * 获取系统时间戳
     *
     * @return
     */
    public long getCurTimeLong() {
        long time = System.currentTimeMillis();
        return time;
    }

    /**
     * 获取当前时间
     *
     * @param pattern
     * @return
     */
    public static String getCurDate(String pattern) {
        SimpleDateFormat sDateFormat = new SimpleDateFormat(pattern);
        return sDateFormat.format(new java.util.Date());
    }

    /**
     * 时间戳转换成字符窜
     *
     * @param milSecond
     * @param pattern
     * @return
     */
    public static String getDateToString(long milSecond, String pattern) {
        Date date = new Date(milSecond);
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    /**
     * 将字符串转为时间戳
     *
     * @param dateString
     * @param pattern
     * @return
     */
    public static long getStringToDate(String dateString, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        Date date = new Date();
        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date.getTime();
    }

    // date类型转换为String类型
    // formatType格式为yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
    // data Date类型的时间
    public static String dateToString(Date data, String formatType) {
        return new SimpleDateFormat(formatType).format(data);

    }
}