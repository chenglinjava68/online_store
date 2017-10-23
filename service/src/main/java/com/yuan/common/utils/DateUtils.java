package com.yuan.common.utils;

import org.apache.log4j.Logger;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtils {
    private static Logger logger = Logger.getLogger(DateUtils.class);

    public static String dateStr(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("MM月dd日 hh:mm");
        String str = format.format(date);
        return str;
    }

    public static String dateStr(Date date, String f) {
        SimpleDateFormat format = new SimpleDateFormat(f);
        String str = format.format(date);
        return str;
    }

    public static String dateStr2(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String str = format.format(date);
        return str;
    }

    public static String newdateStr2(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String str = format.format(date);
        return str;
    }

    public static String newdateStr6(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        String str = format.format(date);
        return str;
    }

    public static String newdateStr3(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyMMdd");
        String str = format.format(date);
        return str;
    }

    public static String dateStr3(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String str = format.format(date);
        return str;
    }

    public static String dateStr4(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = format.format(date);
        return str;
    }

    public static String dateStr5(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        String str = format.format(date);
        return str;

    }

    public static String dateStr6(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String str = format.format(date);
        return str;

    }

    public static Date getDate(String time, String formatStr) {
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        try {
            return format.parse(time);
        } catch (ParseException e) {
            logger.error(e);
        }
        return null;
    }

    /**
     * 将秒转换成时间
     *
     * @param times
     * @return
     */
    public static Date getDate(String times) {
        long time = Long.parseLong(times);
        return new Date(time * 1000);
    }

    public static String dateStr(String times) {
        return dateStr(getDate(times));
    }

    public static String dateStr2(String times) {
        return dateStr2(getDate(times));
    }

    public static String dateStr3(String times) {
        return dateStr3(getDate(times));
    }

    public static String dateStr4(String times) {
        return dateStr4(getDate(times));
    }

    public static long getTime(Date date) {
        return date.getTime() / 1000;
    }

    public static int getDay(Date d) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    public static int getMonth(Date d) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        return cal.get(Calendar.MONTH);
    }

    public static int getYear(Date d) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        return cal.get(Calendar.YEAR);
    }

    /**
     * s - 表示 "yyyy-mm-dd" 形式的日期的 String 对象
     *
     * @param
     * @return
     */
    public static Date valueOf(String s) {
        final int YEAR_LENGTH = 4;
        final int MONTH_LENGTH = 2;
        final int DAY_LENGTH = 2;
        final int MAX_MONTH = 12;
        final int MAX_DAY = 31;
        int firstDash;
        int secondDash;
        Date d = null;

        if (s == null) {
            throw new IllegalArgumentException();
        }

        firstDash = s.indexOf('-');
        secondDash = s.indexOf('-', firstDash + 1);
        if ((firstDash > 0) && (secondDash > 0) && (secondDash < s.length() - 1)) {
            String yyyy = s.substring(0, firstDash);
            String mm = s.substring(firstDash + 1, secondDash);
            String dd = s.substring(secondDash + 1);
            if (yyyy.length() == YEAR_LENGTH && mm.length() == MONTH_LENGTH &&
                    dd.length() == DAY_LENGTH) {
                int year = Integer.parseInt(yyyy);
                int month = Integer.parseInt(mm);
                int day = Integer.parseInt(dd);
                if (month >= 1 && month <= MAX_MONTH) {
                    int maxDays = MAX_DAY;
                    switch (month) {
                        // February determine if a leap year or not
                        case 2:
                            if ((year % 4 == 0 && !(year % 100 == 0)) || (year % 400 == 0)) {
                                maxDays = MAX_DAY - 2; // leap year so 29 days in February
                            } else {
                                maxDays = MAX_DAY - 3; //  not a leap year so 28 days in February
                            }
                            break;
                        // April, June, Sept, Nov 30 day months
                        case 4:
                        case 6:
                        case 9:
                        case 11:
                            maxDays = MAX_DAY - 1;
                            break;
                    }
                    if (day >= 1 && day <= maxDays) {
                        Calendar cal = Calendar.getInstance();
                        cal.set(year, month - 1, day, 0, 0, 0);
                        cal.set(Calendar.MILLISECOND, 0);
                        d = cal.getTime();
                    }
                }
            }
        }
        if (d == null) {
            throw new IllegalArgumentException();
        }
        return d;
    }

    public static Date rollDay(Date d, int day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.DAY_OF_MONTH, day);
        return cal.getTime();
    }

    /**
     * 获得今天的0点
     */
    public static Date getIntegralTime() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获得今天的12点59分59秒
     */
    public static Date getLastIntegralTime() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获得周一的00:00:00
     */
    public static Date getFirstDayInThisWeek() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return cal.getTime();
    }

    /**
     * 获得周日的12:59:59
     */
    public static Date getLastDayInThisWeek() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getFirstDayInThisWeek());
        cal.add(Calendar.DAY_OF_WEEK, 7);
        return cal.getTime();
    }

    /**
     * 获得这个月的第一天
     */
    public static Date getFirstDayInThisMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    /**
     * 获得这个月的最后一天
     */
    public static Date getLastDayInThisMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, 24);
        return cal.getTime();
    }


    public static long getTime(String format) {
        long t = 0;
        if (StringUtils.isBlank(format)) return t;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date;
        try {
            date = sdf.parse(format);
            t = date.getTime() / 1000;
        } catch (ParseException e) {
            logger.error(e);
        }
        return t;
    }

    /**
     * 将2017-08-12这种结束时间变为 2017-08-12 23:59:59
     */
    public static String toEndDayTime(String endTime) {
        return endTime + " 23:59:59";
    }

    public static Date getDate2(String str) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            logger.error(e);
        }
        return date;

    }

    public static Date getDate3(String str) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            logger.error(e);
        }
        return date;

    }

    public static Time getTime1(String str) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        Time time = null;
        try {
            Date date = format.parse(str);
            time = new Time(date.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }

    public static Date getDateYYYYMMdd(String str) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            logger.error(e);
        }
        return date;
    }

    public static Date getDateHHmmss(String str) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            logger.error(e);
        }
        return date;
    }

    /**
     * 获取下一天 默认 格式 yyyy-MM-dd 00:00:00
     *
     * @param date
     * @return
     */
    public static Date getNextDayYYYYMMdd(Date date) {
        date = rollDay(date, 1);
        return getDateYYYYMMdd(dateStr2(date));
    }
    //wsl 2014-09-04 start

    /**
     * 获取当天 默认 格式 yyyy-MM-dd 00:00:00
     *
     * @param date
     * @return
     */
    public static long getDayYYYYMMdd(Date date) {
        return getDateYYYYMMdd(dateStr2(date)).getTime() / 1000;
    }

    //wsl 2014-09-04 end
    public static String getYYYYMMddHHmmss(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (date == null) {
            return null;
        }
        try {
            return sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            return null;
        }
    }

    //v1.8.0.4_u3 TGPROJECT-333 qinjun 2014-06-10 start
    public static boolean valid(String str) {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = formatter.parse(str);
            return str.equals(formatter.format(date));
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 获得两个时间相差的分钟数
     *
     * @param nowTime
     * @param time
     * @return
     */
    public static long getSubMinutes(Long nowTime, Long time) {
        long subMillions = nowTime - time;
        long subMinutes = (subMillions % (1000 * 60 * 60)) / (1000 * 60);
        System.out.println("--------------相差的分钟数为" + subMinutes + "---------------------");
        return subMinutes;
    }

    /**
     * 获得列如 21:30 - 23:30间隔的毫秒数
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static long getSubMill(Time startTime, Time endTime) {
        int startTimeTotalMin = startTime.getHours() * 60 + startTime.getMinutes();
        int endTimeTotalMin = endTime.getHours() * 60 + endTime.getMinutes();
        return endTimeTotalMin * 60 * 1000 - startTimeTotalMin * 60 * 1000;
    }

    /**
     * 获得日期是星期几
     *
     * @param date
     * @return
     */
    public static Integer getDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int intWeek;
        if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
            intWeek = 7;
        } else {
            intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        }
        return intWeek;
    }

    /**
     * 指定小时，分钟，在指定范围内生成日期集合
     * 例如，2017-06-01 到 2017-07-01范围中，获得每周三 12:30 的日期
     *
     * @return
     */
    public static List<Date> getDays(Date startDate, Date endDate, Time time, Integer dayInWeek) {
        List<Date> dateList = new ArrayList<Date>();
        Integer startDateDayInWeek = getDayOfWeek(startDate);
        Integer dayDifference;
        Integer daySpan;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.set(Calendar.HOUR, time.getHours());
        calendar.set(Calendar.MINUTE, time.getMinutes());
        calendar.set(Calendar.SECOND, 0);
        if (startDateDayInWeek <= dayInWeek) {
            daySpan = 0;
            dayDifference = dayInWeek - startDateDayInWeek;
            calendar.add(Calendar.DATE, dayDifference);
        } else {
            daySpan = 0;
            dayDifference = startDateDayInWeek - dayInWeek;
            calendar.add(Calendar.DATE, 7 - dayDifference);
        }
        while (true) {
            calendar.add(Calendar.DAY_OF_MONTH, daySpan);
            if (calendar.getTime().getTime() > (endDate.getTime() + 86400000)) {
                break;
            } else {
                dateList.add(calendar.getTime());
                System.out.println(dateStr4(calendar.getTime()));
            }
            if (daySpan == 0) {
                daySpan += 7;
            }
        }
        return dateList;
    }

    /**
     * 判断两个时间段是否有交集
     */
    public static boolean judgeIsIntersect(Date startTime1, Date endTime1, Date startTime2, Date endTime2) {
        long startTime1Mill = startTime1.getTime();
        long endTime1Mill = endTime1.getTime();
        long startTime2Mill = startTime2.getTime();
        long endTime2Mill = endTime2.getTime();
        if (startTime2Mill < startTime1Mill && endTime2Mill < startTime1Mill) {
            return false;
        } else if (startTime2Mill > endTime1Mill) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 转成 12:30这种格式
     */
    public static String getTime2(Date date) {
        Integer hour = date.getHours();
        Integer minutes = date.getMinutes();
        StringBuffer stringBuffer = new StringBuffer("");
        stringBuffer.append(hour + ":");
        if (minutes < 10) {
            stringBuffer.append("0" + minutes);
        } else {
            stringBuffer.append(minutes);
        }
        return stringBuffer.toString();
    }

    /**
     * 转成 12:30这种格式
     */
    public static String getTime3(Time time) {
        Integer hour = time.getHours();
        Integer minutes = time.getMinutes();
        StringBuffer stringBuffer = new StringBuffer("");
        stringBuffer.append(hour + ":");
        if (minutes < 10) {
            stringBuffer.append("0" + minutes);
        } else {
            stringBuffer.append(minutes);
        }
        return stringBuffer.toString();
    }

    /**
     * 由出生日期获得年龄
     *
     * @param birthDay
     * @return 年龄
     */
    public static int getAge(Date birthDay){
        Calendar cal = Calendar.getInstance();
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(birthDay);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
        int age = yearNow - yearBirth;
        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) age--;
            } else {
                age--;
            }
        }
        return age;
    }

}
