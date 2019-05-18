import java.util.Date;

/**
 * Date 类型测试
 */
public class Test1 {
    public static void main(String[] args) {
        // 创建日期对象，把当前的时间
        System.out.println(new Date());
        // 创建日期对象，把当前的毫秒值转成日期对象
        System.out.println(new Date(0L));
    }
}
