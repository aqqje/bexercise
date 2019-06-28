package date;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;

/**
 * @author AqqJe
 * @description
 */
public class Date2string {

    private static SimpleDateFormat sdf = null;
    private static Date date = new Date();

    // 获取本年第一天的日期
    public static String getCurrentYearFirst(){
        sdf = new SimpleDateFormat("yyyy");
        String year = sdf.format(date);
        return year+"-1-1";
    }
    // 获取本年最后一天的日期
    public static String getCurrentYearLast(){
        sdf = new SimpleDateFormat("yyyy");
        String year = sdf.format(date);
        return year+"-12-31";
    }

    // 获取当天日期
    public static String getNowTime(){
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return sdf.format(date);
    }

    // 获取本周一日期
    public static String getCurrentWeekFirst(){
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0)
            day_of_week = 7;
        c.add(Calendar.DATE, -day_of_week + 1);
        return sdf.format(c.getTime());
    }

    // 获取本周日日期
    public static String getCurrentWeekLast(){
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        int day_of_month = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if(day_of_month == 0)
            day_of_month = 7;
        calendar.add(Calendar.DATE,  -day_of_month + 7);
        return sdf.format(calendar.getTime());
    }

    // 获取上周一日期
    public static String getPreWeekFirst(){
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -Calendar.DAY_OF_WEEK + 7);
        return sdf.format(calendar.getTime());
    }

    // 获取上周日日期
    public static String getPreWeekLast(){
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, +(Calendar.DAY_OF_MONTH - Calendar.DAY_OF_WEEK) - 7);
        return sdf.format(calendar.getTime());
    }

    // 获取本月第一天
    public static String getCurrentMonthFirst(){
        LocalDate today = LocalDate.now();
        return LocalDate.of(today.getYear(), today.getMonth(), 1).toString();

    }

    // 获取本月最后一天
    public static String getCurrentMonthLast(){
        LocalDate today = LocalDate.now();
        return today.with(TemporalAdjusters.lastDayOfMonth()).toString();
    }

    // 获取上月第一天
    public static String getPreMonthFirst(){
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return sdf.format(calendar.getTime());
    }

    // 获取上月最后一天
    public static String getPreMonthLast(){
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return sdf.format(calendar.getTime());
    }

    // 获取下月第一天
    public static String getNextMonthFirst(){
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, +1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return sdf.format(calendar.getTime());
    }

    // 获取下月最后一天
    public static String getNextMonthLast(){
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, +1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return sdf.format(calendar.getTime());
    }

    // 测试
    public static void main(String[] args) {
        // 获取本年第一天的日期
        System.out.println("获取本年第一天的日期:"+getCurrentYearFirst());
        System.out.println("获取本年最后一天的日期:"+getCurrentYearLast());

        System.out.println("获取当天日期:"+getNowTime());

        System.out.println("获取本周一日期:"+getCurrentWeekFirst());
        System.out.println("获取本周日日期:"+getCurrentWeekLast());

        System.out.println("获取上周一日期:"+getPreWeekFirst());
        System.out.println("获取上周日日期:"+getPreWeekLast());

        System.out.println("获取本月第一天:"+getCurrentMonthFirst());
        System.out.println("获取本月最后天:"+getCurrentMonthLast());

        System.out.println("获取上月第一天:"+getPreMonthFirst());
        System.out.println("获取上月最后天:"+getPreMonthLast());

        System.out.println("获取下月第一天:"+getNextMonthFirst());
        System.out.println("获取下月最后天:"+getNextMonthLast());
    }
}
