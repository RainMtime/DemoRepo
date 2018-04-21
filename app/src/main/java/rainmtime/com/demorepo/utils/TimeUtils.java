package rainmtime.com.demorepo.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by chunyu on 2018/4/21 上午10:22.
 * Email: 746431278@qq.com
 */

public class TimeUtils {

    public static long calcTimeCost(long st) {
        return System.currentTimeMillis() - st;
    }


    public static final SimpleDateFormat DEFAULT_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat FEED_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    public static final SimpleDateFormat SERVER_DATE_FORMAT = new SimpleDateFormat("yyyyMMddHHmm");
    public static final SimpleDateFormat EXIF_DATE_FORMAT = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");

//    public static String formatFeedDate(Date date) {
//        return FEED_DATE_FORMAT.format(date);
//    }

    private static StringBuilder sStringBuilder = new StringBuilder(32);

    //* 今天+时间（24小时制），如“今天07:55”
    //* 昨天/前天+时间，如“昨天07:30”
    //* 日期+时间，如“06-16 04:23”
    public static String formatMessageDateTime2(long dateTimeMillis) {
        final long currentMillis = System.currentTimeMillis();
        //以目标时间的天数和当前时间的天数差为衡量标准
        //需要格式化的日历
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(dateTimeMillis);
        int d = c.get(Calendar.DAY_OF_MONTH);
        int h = c.get(Calendar.HOUR_OF_DAY);
        int m = c.get(Calendar.MINUTE);
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int day = c.get(Calendar.DAY_OF_YEAR);

        //当前日历
        Calendar c2 = Calendar.getInstance();
        c2.setTimeInMillis(currentMillis);
        int year2 = c2.get(Calendar.YEAR);
        int day2 = c2.get(Calendar.DAY_OF_YEAR);

        int diffDay;
        //计算天数差
        if (year2 != year) { //不同年份
            int timeDistance = 0;
            for (int i = year; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {  //闰年
                    timeDistance += 366;
                } else {  //不是闰年
                    timeDistance += 365;
                }
            }
            diffDay = timeDistance + (day2 - day);
        } else { //同一年
            diffDay = day2 - day;
        }

        sStringBuilder.delete(0, sStringBuilder.length());
        if (diffDay == 0) {
            return sStringBuilder.append("今天").append((h < 10 ? "0" + h : h)).append(":").append((m < 10 ? "0" + m : m)).toString();
        } else if (diffDay == 1) {//显示昨天
            return sStringBuilder.append("昨天").append((h < 10 ? "0" + h : h)).append(":").append((m < 10 ? "0" + m : m)).toString();
        } else if (diffDay == 2) {//显示前天
            return sStringBuilder.append("前天").append((h < 10 ? "0" + h : h)).append(":").append((m < 10 ? "0" + m : m)).toString();
        } else {//显示日期
            if (year2 != year) {//不同年
                return sStringBuilder.append(year).append("-").append((month < 10 ? "0" + month : month)).append("-").append((d < 10 ? "0" + d : d)).
                        append(" ").append((h < 10 ? "0" + h : h)).append(":").append((m < 10 ? "0" + m : m)).toString();
            } else {//同年
                return sStringBuilder.append((month < 10 ? "0" + month : month)).append("-").append((d < 10 ? "0" + d : d)).
                        append(" ").append((h < 10 ? "0" + h : h)).append(":").append((m < 10 ? "0" + m : m)).toString();
            }
        }
    }

}
