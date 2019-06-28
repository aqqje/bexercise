package date;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;

/**
 * @author AqqJe
 * @description Calender类面试相关
 */
public class CalenderDome {

    public static void main(String[] args) {
        // 1.如何获取年月日小时分钟秒
        Calendar cal = Calendar.getInstance();
        // 年
        // System.out.println(cal.get(Calendar.YEAR));
        // 月
        // System.out.println(cal.get(Calendar.MONTH));
        // 日
        // System.out.println(cal.get(Calendar.DATE));
        // 小时
        // System.out.println(cal.get(Calendar.HOUR_OF_DAY));
        // 分钟
        // System.out.println(cal.get(Calendar.MINUTE));
        // 秒
        // System.out.println(cal.get(Calendar.SECOND));

        // 2.如何获从得1970-1-1 0:0:0 到现在时间的毫秒数
        // 2.1 Calendar
        // System.out.println(Calendar.getInstance().getTimeInMillis());
        // 2.2 System
        // System.out.println(System.currentTimeMillis());
        // 2.3 Java 8
        // System.out.println(Clock.systemDefaultZone().millis());


        // 3 获取当前月份的第一天
        Calendar c = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        c.set(Calendar.DAY_OF_MONTH,1);
        System.out.println(format.format(c.getTime()));
        // 3.1 java8
        LocalDate today = LocalDate.now();
        LocalDate first = LocalDate.of(today.getYear(), today.getMonth(), 1);
        LocalDate last = today.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println(first);

        // 4 获取当前月份的最后-天
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH,ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        System.out.println(format.format(ca.getTime()));
        // 4.1 java8
        System.out.println(last);

        // 5.打印昨天当前时刻
        Calendar cale = Calendar.getInstance();
        cale.add(Calendar.DATE,-1);
        System.out.println(cale.getTime());
    }
}
