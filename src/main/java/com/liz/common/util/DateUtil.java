package com.liz.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * DateFormatter tool
 *
 * Created by IntelliJ IDEA.
 * User: pengchangguo
 * Date: 12-2-24
 * Time: P.M 12:00
 */
public class DateUtil {

    /**
     * format the date to string
     * <pre>
     *     String s = DateUtil.format(new TimeStamp(System.currentTimeMillis()), "yyyyMMddhhmmsssss");
     *     //s is the string of currentTime
     * </pre>
     * @param date java.util.Date
     * @param pattern the pattern of Date
     * @return formatted string
     */
    public static String format(Date date, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }

    /**
     * parse the string to date
     * @param date string of date
     * @param pattern the pattern of Date
     * @return java.util.Date
     */
    public static Date parse(String date, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        try {
            return simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * 将时间戳转为字符串
     * @param cc_time   时间戳
     * @param pattern   格式化的字符串   yyyy年MM月dd日HH时mm分ss秒
     * @return
     */
    public static String getStrTime(String cc_time, String pattern) {
        String re_StrTime ;
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        long lcc_time = Long.valueOf(cc_time);
        re_StrTime = sdf.format(new Date(lcc_time * 1000L));
        return re_StrTime;
    }

    /**
     * 获取现在时间
     *
     * @return 返回时间类型 yyyy-MM-dd HH:mm:ss
     */
    public static String getNowDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 是否工作日
     * @param holidays 节假日数组
     * @param specsDays 特殊日数组
     * @return 是否是工作日
     */
    public static boolean isWorkDay(String[] holidays, String[] specsDays) {
//        todo 先判断是否节假日，再判断是否特殊日， 最后判断是否周末
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(new Date());
        String pattern="yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat(pattern);

        return false;
    }

}
