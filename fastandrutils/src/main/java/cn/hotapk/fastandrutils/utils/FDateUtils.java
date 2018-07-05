package cn.hotapk.fastandrutils.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * @author laijian
 * @version 2017/9/12
 * @Copyright (C)下午3:47 , www.hotapk.cn
 * 时间操作类
 */
public final class FDateUtils {
    // 格式：年－月－日 时：分：秒
    public static String FORMAT_YMDHMS = "yyyy-MM-dd HH:mm:ss";
    // 格式：年－月－日
    public static String FORMAT_YMD = "yyyy-MM-dd";
    // 格式：年－月－日 时:分
    public static String FORMAT_YMDHM = "yyyy-MM-dd HH:mm";
    // 格式：年/月/日 时：分：秒
    public static String FORMAT2_YMDHMS = "yyyy/MM/dd HH:mm:ss";
    // 格式：年/月/日
    public static String FORMAT2_YMD = "yyyy/MM/dd";
    // 格式：年/月/日 时:分
    public static String FORMAT2_YMDHM = "yyyy/MM/dd HH:mm";

    private FDateUtils() {
    }

    /**
     * 字符串转日期类型
     *
     * @param dateStr 日期字符串
     * @param format  转换类型
     */
    public static Date stringToDate(String dateStr, String format) {

        SimpleDateFormat formater = new SimpleDateFormat(format, Locale.US);
        try {
            formater.setLenient(false);
            return formater.parse(dateStr);
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * 字符串转日期类型
     *
     * @param dateStr 日期字符串
     *                默认时间类型 yyyy-MM-dd HH:mm:ss
     */
    public static Date stringToDate(String dateStr) {
        return stringToDate(dateStr, FORMAT_YMDHMS);
    }


    /**
     * 日期转字符串
     *
     * @param date
     * @param format
     * @return
     */
    public static String dateToString(Date date, String format) {
        SimpleDateFormat formater = new SimpleDateFormat(format, Locale.US);
        try {
            return formater.format(date);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 日期转字符串
     *
     * @param date 默认时间类型 yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String dateToString(Date date) {
        return dateToString(date, FORMAT_YMDHMS);
    }


    /**
     * 获取某年某月的天数
     *
     * @param year  *          int
     *              *
     * @param month *          int 月份[1-12]
     *              *
     * @return int
     */
    public static int getDaysOfMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获得当前日期
     *
     * @return int
     */
    public static int getToday() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DATE);
    }

    /**
     * 获得当前月份
     *
     * @return int
     */
    public static int getToMonth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 获得当前年份
     *
     * @return int
     */
    public static int getToYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 返回日期的天
     *
     * @param date *          Date
     *             *
     * @return int
     */
    public static int getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DATE);
    }

    /**
     * 返回日期的年
     *
     * @param date *          Date
     *             *
     * @return int
     */
    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 返回日期的月份，1-12
     *
     * @param date *          Date
     *             *
     * @return int
     */
    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 计算两个日期相差的天数，如果date2 > date1 返回正数，否则返回负数
     *
     * @param date1 *          Date
     *              *
     * @param date2 *          Date
     *              *
     * @return long
     */
    public static long dayDiff(Date date1, Date date2) {
        return (date2.getTime() - date1.getTime()) / 86400000;
    }

    /**
     * 比较两个日期的年差
     *
     * @param befor *
     * @param after *
     * @return
     */
    public static int yearDiff(String befor, String after)

    {
        Date beforeDay = stringToDate(befor, FORMAT_YMD);
        Date afterDay = stringToDate(after, FORMAT_YMD);
        if (beforeDay != null && afterDay != null) {
            return (getYear(afterDay) - getYear(beforeDay));
        } else {
            return -1;
        }
    }

    /**
     * 获取一天的开始时间
     *
     * @param setTime 默认格式时间 yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getFristDayTime(String setTime) {
        return getFristDayTime(FORMAT_YMDHMS, setTime);
    }

    /**
     * 获取一天的开始时间
     */
    public static String getFristDayTime(String dateFormat, String setTime)

    {
        SimpleDateFormat format = new SimpleDateFormat(dateFormat, Locale.US);
        GregorianCalendar c1 = new GregorianCalendar();
        try {
            c1.setTime(format.parse(setTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c1.set(Calendar.HOUR_OF_DAY, 0);
        c1.set(Calendar.MINUTE, 0);
        c1.set(Calendar.SECOND, 0);
        return format.format(c1.getTime());
    }

    /**
     * 获取一天的结束时间
     *
     * @param setTime 默认格式时间 yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getLastDayTime(String setTime) {
        return getLastDayTime(FORMAT_YMDHMS, setTime);
    }

    /**
     * 获取一天的结束时间
     */
    public static String getLastDayTime(String dateFormat, String setTime) {
        SimpleDateFormat format = new SimpleDateFormat(dateFormat, Locale.US);
        GregorianCalendar c1 = new GregorianCalendar();
        try {
            c1.setTime(format.parse(setTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c1.set(Calendar.HOUR_OF_DAY, 23);
        c1.set(Calendar.MINUTE, 59);
        c1.set(Calendar.SECOND, 59);
        return format.format(c1.getTime());
    }

    /**
     * 比较指定日期与当前日期的差
     *
     * @param after *
     * @return
     */
    public static int yearDiffCurr(String after) {
        Date beforeDay = new Date();
        Date afterDay = stringToDate(after, FORMAT_YMD);
        if (afterDay != null) {
            return (getYear(beforeDay) - getYear(afterDay));
        } else {
            return -1;
        }
    }


    /**
     * 获取指定日期的时间差
     *
     * @param after
     * @param before
     * @return
     */
    public static int yearDiffCurr(String after, String before)

    {
        Date beforeDay = stringToDate(before, FORMAT_YMD);
        Date afterDay = stringToDate(after, FORMAT_YMD);
        if (afterDay != null && beforeDay != null) {
            return (getYear(beforeDay) - getYear(afterDay));
        } else {
            return -1;
        }
    }


    /**
     * 获取每月的第一周天数
     *
     * @param year  *
     * @param month *
     * @return *
     * @author chenyz
     */
    public static int getFirstWeekdayOfMonth(int year, int month) {
        Calendar c = Calendar.getInstance();
        c.setFirstDayOfWeek(Calendar.SATURDAY);// 星期天为第一天
        c.set(year, month - 1, 1);
        return c.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 获取每月的最后一周天数
     *
     * @param year  *
     * @param month *
     * @return *
     * @author chenyz
     */
    public static int getLastWeekdayOfMonth(int year, int month)

    {
        Calendar c = Calendar.getInstance();
        c.setFirstDayOfWeek(Calendar.SATURDAY);// 星期天为第一天
        c.set(year, month - 1, getDaysOfMonth(year, month));
        return c.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 获取本月第一天
     *
     * @param format *
     * @return
     */
    public static String getFirstDayOfMonth(String format) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DATE, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        String firstDayOfMonth = dateToString(cal.getTime(), format);
        return firstDayOfMonth != null ? firstDayOfMonth : "";
    }

    /**
     * 获取本月最后一天
     *
     * @param format *
     * @return
     */
    public static String getLastDayOfMonth(String format) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DATE, 1);
        cal.add(Calendar.MONTH, 1);
        cal.add(Calendar.DATE, -1);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        String lastDayOfMonth = dateToString(cal.getTime(), format);
        return lastDayOfMonth != null ? lastDayOfMonth : "";
    }

    // 获得本周一0点时间
    public static Date getTimesWeekmorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return cal.getTime();
    }

    // 获得本周日24点时间
    public static Date getTimesWeeknight() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getTimesWeekmorning());
        cal.add(Calendar.DAY_OF_WEEK, 7);
        return cal.getTime();
    }


    // 获得昨天0点时间
    public static Date getYesterdaymorning(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(stringToDate(getFristDayTime(FORMAT_YMDHMS, dateToString(date))).getTime() - 3600 * 24 * 1000);
        return cal.getTime();
    }

    // 获得距当天前n天时间
    public static Date getBefore(Date date, int day) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(stringToDate(getFristDayTime(FORMAT_YMDHMS, dateToString(date))).getTime() - 3600 * 24 * 1000 * day);
        return cal.getTime();
    }

    // 获得距当天前7天时间
    public static Date getBefore(Date date) {
        return getBefore(date, 7);
    }

    //获取明天
    public static Date getTomorrowMorning(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(stringToDate(getFristDayTime(FORMAT_YMDHMS, dateToString(date))).getTime() + 3600 * 24 * 1000);
        return cal.getTime();
    }

    // 获得距当天后n天时间
    public static Date getAfterDate(Date date, int day) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(stringToDate(getFristDayTime(FORMAT_YMDHMS, dateToString(date))).getTime() + 3600 * 24 * 1000 * day);
        return cal.getTime();
    }

    // 获得距当天后7天时间
    public static Date getAfterDate(Date date) {
        return getAfterDate(date, 7);
    }

    /**
     * 判断日期是否有效,包括闰年的情况
     *
     * @param date *          YYYY-mm-dd
     *             *
     * @return
     */
    public static boolean isDate(String date) {
        StringBuffer reg = new StringBuffer(
                "^((\\d{2}(([02468][048])|([13579][26]))-?((((0?");
        reg.append("[13578])|(1[02]))-?((0?[1-9])|([1-2][0-9])|(3[01])))");
        reg.append("|(((0?[469])|(11))-?((0?[1-9])|([1-2][0-9])|(30)))|");
        reg.append("(0?2-?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][12");
        reg.append("35679])|([13579][01345789]))-?((((0?[13578])|(1[02]))");
        reg.append("-?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))");
        reg.append("-?((0?[1-9])|([1-2][0-9])|(30)))|(0?2-?((0?[");
        reg.append("1-9])|(1[0-9])|(2[0-8]))))))");
        Pattern p = Pattern.compile(reg.toString());
        return p.matcher(date).matches();
    }

    /**
     * 根据生日获取星座
     *
     * @param birth *          YYYY-mm-dd
     *              *
     * @return
     */
    public static String getAstro(String birth) {
        if (!isDate(birth)) {
            birth = "2000" + birth;
        }
        if (!isDate(birth)) {
            return "";
        }
        int month = Integer.parseInt(birth.substring(birth.indexOf("-") + 1,
                birth.lastIndexOf("-")));
        int day = Integer.parseInt(birth.substring(birth.lastIndexOf("-") + 1));
        String s = "魔羯水瓶双鱼牡羊金牛双子巨蟹狮子处女天秤天蝎射手魔羯";
        int[] arr = new int[]{20, 19, 21, 21, 21, 22, 23, 23, 23, 23, 22, 22};
        int start = month * 2 - ((day < arr[month - 1] ? 2 : 0));
        return s.substring(start, start + 2) + "座";
    }


}
