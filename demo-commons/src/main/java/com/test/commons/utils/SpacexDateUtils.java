package com.test.commons.utils;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Calendar;
import java.util.Date;

/**
 * 空间站日期工具
 * @author zhangyabo
 * @date 2018/11/29
 */
public class SpacexDateUtils {

    private final static String[] WEEK_DAYS = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
    /**
     * 时间格式化
     */
    private final static String TIME_FORMAT_HH_MM = "HH:mm";
    /**
     * 中文日期
     */
    private final static String[] ZH_DATE = {"今天","明天"};

    /**
     * 上门预约时间  ：今天 09:00-11:00
     * @param startTime
     * @param endTime
     * @return
     */
    public static String getReserveDateTime(Date startTime, Date endTime){
        return getZHDate(startTime)+" "+ DateFormatUtils.format(startTime,TIME_FORMAT_HH_MM) +"-"+DateFormatUtils.format(endTime,TIME_FORMAT_HH_MM);
    }

    /**
     * 上门预约时间  ：今天(周一) 09:00-11:00
     * @param startTime
     * @param endTime
     * @return
     */
    public static String getReserveWeekDateTime(Date startTime, Date endTime){
        if (startTime == null || endTime == null){
            return null;
        }
        return getZHDate(startTime)+"("+SpacexDateUtils.getWeekName(startTime)+")"+" "+DateFormatUtils.format(startTime,TIME_FORMAT_HH_MM) +"-"+DateFormatUtils.format(endTime,TIME_FORMAT_HH_MM);
    }


    /**
     * 返回中文时间  如 今天，明天，后天，日期（大后天）
     * @param dateTime
     * @return
     */
    public static String getZHDate(Date dateTime){

        Calendar nowTimeCalendar = Calendar.getInstance();
        int nowDayOfYear = nowTimeCalendar.get(Calendar.DAY_OF_YEAR);

        return getZHDate(dateTime, nowDayOfYear);
    }

    /**
     * 获取空间站预约日志显示名称
     * @param dateTime
     * @param nowDayOfYear
     * @return
     */
    public static String getZHDate(Date dateTime, int nowDayOfYear){
        Calendar dateTimeCalendar = Calendar.getInstance();
        dateTimeCalendar.setTime(dateTime);
        int dateTimeDayOfYear = dateTimeCalendar.get(Calendar.DAY_OF_YEAR);
        int compare = dateTimeDayOfYear - nowDayOfYear;
        if (compare < 0 || compare >= ZH_DATE.length){
            int month = dateTimeCalendar.get(Calendar.MONTH);
            int day = dateTimeCalendar.get(Calendar.DAY_OF_MONTH);
            return (month+1)+"月"+day+"日";
        } else {
            return ZH_DATE[compare];
        }
    }

    /**
     * 获取周几
     * @param time
     * @return
     */
    public static String getWeekName(Date time){
        String name = "";
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0 || w > WEEK_DAYS.length){
            w = 0;
        }
        name = WEEK_DAYS[w];
        return name;
    }

}
