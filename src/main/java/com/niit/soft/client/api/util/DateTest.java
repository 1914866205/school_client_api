package com.niit.soft.client.api.util;

import javax.xml.crypto.Data;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * @author
 * @version 1.0
 * @ClassName DateUtil
 * @Description TODO
 * @date 2020-06-08 21:02
 **/
public class DateTest {
    public static void main(String[] args) {
//        //首先获取开学当前时间 分辨出当前时间为周几
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date date = null;
//        try {
//            date = dateFormat.parse("2020-06-04");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        int[] weekDays = {7,1,2,3,4,5,6};
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(date);
//        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
//        if (w < 0) {
//            w = 0;
//        }
//        System.out.println(weekDays[w]);
//
//        //获取系统当前时间与某个时间判断之间的天数
//        LocalDate start = LocalDate.of(2020, 6, 4);
//        LocalDate now = LocalDate.now();
//        int gapTime = Math.toIntExact(now.toEpochDay() - start.toEpochDay());
//        System.out.println("两个时间之间的天数是：" + gapTime + " 天");
//
//        //计算这是第几周
//        int week=0;
//        if (gapTime%7!=0){
//            week = gapTime/7+2;
//        }else {
//            week = gapTime/7+1;
//        }
//        System.out.println("当前为第"+week+"周");
//
//
//        String sem = getSemester();
//        System.out.println(sem);

        System.out.println(getWeek("2020-02-24 08:11:18"));
//        System.out.println(getSemester());
//        System.out.println(getWeekDay("2020-06-08 00:01:19"));
//        System.out.println(getCurrentWeek());
    }

    /**
     * 获取学期名
     *
     * @return
     */
    public static String getSemester() {
        Calendar cal1 = Calendar.getInstance();
        int year = cal1.get(Calendar.YEAR);
        int month = cal1.get(Calendar.MONTH) + 1;
        if (month <= 6) {
            return (year - 1) + "-" + year + "学年第二学期";
        } else {
            return year + "-" + (year + 1) + "学年第一学期";
        }
    }

    //获取今天是开学后的第几周次
    public static int getWeek(String time) {
        //获取年
        String year = time.substring(0, 4);
        int year1 = Integer.parseInt(year);
        //获取月
        String month = time.substring(5, 7);
        int month1 = Integer.parseInt(month);
        //获取日
        String day = time.substring(8, 10);
        int day1 = Integer.parseInt(day);
        //获取系统当前时间与某个时间判断之间的天数
        LocalDate start = LocalDate.of(2020, 5, 1);
        LocalDate now = LocalDate.now();
        int gapTime = Math.toIntExact(now.toEpochDay() - start.toEpochDay());
//        System.out.println("两个时间之间的天数是：" + gapTime + " 天");

        //计算这是第几周
        int week = 0;
        if (gapTime % 7 != 0) {
            week = gapTime / 7 + 2;
        } else {
            week = gapTime / 7 + 1;
        }
//        System.out.println("当前为第"+week+"周");
        return week;
    }


    //获取系统当前周次
    public static int getCurrentWeek() {
        int[] weekDays = {7, 1, 2, 3, 4, 5, 6};
        Calendar cal = Calendar.getInstance();
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return weekDays[w];
    }

    /**
     * 获取从开学日期开始当前是第几周
     *
     * @param beginSchoolTime
     * @return
     */
    public static int getWeekDay(String beginSchoolTime) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = dateFormat.parse(beginSchoolTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int[] weekDays = {7, 1, 2, 3, 4, 5, 6};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return weekDays[w];
    }

    /**
     * string串边长localdatetime
     *
     * @param time
     * @return
     */
    public static Timestamp getLocalDateTime(String time) {
        Timestamp ts = Timestamp.valueOf(time);

        return ts;
    }

}
