package date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author AqqJe
 * @description 计算出一个人已经出生了多少天。
 */
public class Solution1 {

    public static long getBrithday(String dateStr) throws ParseException {
        if(dateStr == null && dateStr == "") return 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date brith = sdf.parse(dateStr);
        long brithTime = brith.getTime();
        long nowTime = new Date().getTime();

        return (nowTime - brithTime) / (1000 * 60 * 60 * 24);
    }

    public static void main(String[] args) throws ParseException {
        System.out.println(getBrithday("2019-5-17"));
    }
}
