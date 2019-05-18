package date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author AqqJe
 * @description 时间与字符串转换
 */
public class Solution2 {

    private static SimpleDateFormat sdf;

    /** 时间转字符串
     *
     * @param formart 指定格式
     * @param date 需要转化时间
     * @return
     */
    public static String date2Str(String formart, Date date){
        if((formart == null && formart == "") && date == null) return null;
        sdf = new SimpleDateFormat(formart);
        return sdf.format(date);
    }

    /** 字符串转时间
     *
     * @param formart 指定格式
     * @param dateStr 时间字符串
     * @return
     * @throws ParseException
     */
    public static Date str2Date(String formart, String dateStr) throws ParseException {
        if((formart == null && formart == "") && dateStr == null) return null;
        sdf = new SimpleDateFormat(formart);
        return sdf.parse(dateStr);
    }

    public static void main(String[] args) throws ParseException {
        System.out.println(date2Str("yyyy-MM-dd", new Date()));
        System.out.println(str2Date("yyyy-MM-dd", "2019-05-16"));
    }
}
