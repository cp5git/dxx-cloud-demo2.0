package com.dxx.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * 时间处理工具类
 * @author cp5
 * @date 2020年 07月30日 11:50:39
 */
public class DateUtil {

    public static final String DEFAULT_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";//默认时间格式

    public static final int SECOND = Calendar.SECOND;
    public static final int MINUTE = Calendar.MINUTE;
    public static final int HOUR = Calendar.HOUR;
    public static final int DATE = Calendar.DATE;
    public static final int MONTH = Calendar.MONTH;
    public static final int YEAR = Calendar.YEAR;
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String TIME_FORMAT = "HH:mm:ss";
    public static final String DATE_TIME_CLING_FORMAT = "yyyyMMddHHmmss";
    public static final String DATE_TIME_FORMAT_WITH_OUT_SECOND = "yyyy-MM-dd HH:mm";
    public static final String DATE_FORMAT_FILE = "yyyyMMdd";

    /**
     * 时间转字符串
     * @param date 时间
     * @param dateFormat 时间格式
     * @return 时间字符串
     */
    public static String format(Date date, String dateFormat) {
        if (date == null) {
            return null;
        }
        DateFormat df = new SimpleDateFormat(dateFormat);
        return df.format(date);
    }

    /**
     * 时间转字符串,使用默认时间格式
     * @param date 时间
     * @return 时间字符串
     */
    public static String format(Date date) {
        return format(date,DEFAULT_TIME_FORMAT);
    }

    /**
     * 字符串转时间
     * @param date 时间字符串
     * @param dateFormat 时间格式
     * @return 时间
     * @throws ParseException 时间格式错误
     */
    public static Date format(String date, String dateFormat) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);

        return sdf.parse(date);

    }

    /**
     * 获取默认格式的时间字符串
     * @return
     */
    public static String getCurrentTime(){
        return format(new Date(),DEFAULT_TIME_FORMAT);
    }

    /**
     * 计算时间间隔,以秒为单位
     * @param from 开始时间
     * @param to 结束时间
     * @return 时间间隔
     */
    public static long timeDiff(Date from,Date to) {
        return timeDiff(SECOND,from,to);
    }

    /**
     * 计算时间间隔
     * @param timeInterval 单位
     * @param from 开始时间
     * @param to 结束时间
     * @return 时间间隔
     */
    public static long timeDiff(int timeInterval, Date from, Date to) {
        Calendar calendar = Calendar.getInstance();
        if (timeInterval==YEAR) {
            calendar.setTime(to);
            int time = calendar.get(Calendar.YEAR);
            calendar.setTime(from);
            return time - calendar.get(Calendar.YEAR);
        }

        if (timeInterval==MONTH) {
            calendar.setTime(to);
            int time = calendar.get(Calendar.YEAR) * 12;
            calendar.setTime(from);
            time -= calendar.get(Calendar.YEAR) * 12;
            calendar.setTime(to);
            time += calendar.get(Calendar.MONTH);
            calendar.setTime(from);
            return time - calendar.get(Calendar.MONTH);
        }

        if (timeInterval==DATE) {
            calendar.setTime(to);
            int time = calendar.get(Calendar.DAY_OF_YEAR)
                    + calendar.get(Calendar.YEAR) * 365;
            calendar.setTime(from);
            return time
                    - (calendar.get(Calendar.DAY_OF_YEAR) + calendar
                    .get(Calendar.YEAR) * 365);
        }

        if (timeInterval==HOUR) {
            long time = to.getTime() / 1000 / 60 / 60;
            return time - from.getTime() / 1000 / 60 / 60;
        }

        if (timeInterval==MINUTE) {
            long time = to.getTime() / 1000 / 60;
            return time - from.getTime() / 1000 / 60;
        }

        if (timeInterval==SECOND) {
            long time = to.getTime() / 1000;
            return time - from.getTime() / 1000;
        }

        return to.getTime() - from.getTime();
    }

    /**
     * 增加时间
     * @param field 时间单位
     * @param date 原始时间
     * @param value 增加的时间量
     * @return 增加后的时间
     * cp5
     * 20200726
     */
    public static Date add(int field, Date date, int value) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int fieldNewValue = (c.get(field) + value);
        c.set(field, fieldNewValue);
        return c.getTime();
    }

    public static Date add(Date date, int value) {
        return add(SECOND,date,value);
    }


}
