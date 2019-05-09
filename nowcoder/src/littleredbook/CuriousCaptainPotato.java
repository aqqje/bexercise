package littleredbook;

/**
 * @author 琉璃201901090019116
 * @description 给定二叉树T（树深度不超过H<=10，深度从1开始，节点个数N<1024，节点编号1~N）的层序和中序遍历，
 * 输出T从左向右叶子节点以及树先序和后序遍历序列
 */
public class CuriousCaptainPotato {

    // 测试
    public static void main(String[] args) {
        /*System.out.println(countOne(13));*/
        int n = 100;

        for (long i=1;i<=n;i *= 10) {
            //System.out.println(i);
            long a = n / i;    //分割点前面的数
            long b = a % 10;   //当前位数字
            long c = n % i;    //分割点后面数字
            System.out.println("分割点前面的数"+a);
            System.out.println("当前位数字"+b);
            System.out.println("分割点后面数字"+c);
        }

    }
    public static long countOne(long n){
        if(n < 0) return 0;
        // 总记录数
        long count = 0;
        // 当前记录数
        int index = 1;
        while(n >= index){
            String indexStr = String.valueOf(index);
            char[] chars = indexStr.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                if(chars[i] == '1'){
                    count++;
                }
            }
            index++;
        }
        return count;
    }
}
