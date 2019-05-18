import java.util.Calendar;
import java.util.Date;

/**
 * @author AqqJe
 * @description Calendar 类测试
 */
public class Test2 {
    public static void main(String[] args) {
        // 获取一个日历对象
        Calendar calendar = Calendar.getInstance();
        // 年
        int year = calendar.get(Calendar.YEAR);
        // 月
        int month = calendar.get(Calendar.MONTH)+1;
        // 日
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println(year+"-"+month+"-"+day);


        // 设置指定数据
        calendar.set(Calendar.YEAR, 2020);
        year = calendar.get(Calendar.YEAR);
        System.out.println(year+"-"+month+"-"+day);


        // 当前年 -3 年
        calendar.add(Calendar.YEAR, -3);
        //当前月份 +2 月
        calendar.add(Calendar.MONTH, 2);
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH)+1;
        System.out.println(year+"-"+month+"-"+day);

        // 获取当前 calendar 对象的 Date 对象
        Date date = calendar.getTime();
        System.out.println(date);
    }
}
