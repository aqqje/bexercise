package littleredbook.second;

import java.util.Scanner;

/**
 * @author
 * @description
 */
public class CuriousCaptainPotato {

    // 测试
    public static void main(String[] args) {
        /*System.out.println(countOne(13));*/
        Scanner sc = new Scanner(System.in);
        long l = sc.nextLong();
        long result = countOne1(l);
        System.out.println(result);

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
    public static long countOne1(long n){
        if(n <= 0 || n > 2147483647) return 0;
        String buffer = "";
        long count = 0;
        for(int i = 1; i <= n; i++){
            buffer += i;
        }
        String oldStr = buffer;
        String newStr = oldStr.replaceAll("1", "");
        return oldStr.length() - newStr.length();
    }

    // 牛客329198682号
    public static long numberOf1Between1AndN(int n, int x){
        if(n < 0 || x < 1 || x > 9){
            return 0;
        }
        int curr, low, temp, high = n, i = 1;
        long total = 0;
        while(high!=0){
            high = n / (int)Math.pow(10, i); //获取第i位的高位
            temp = n % (int)Math.pow(10, i); //
            curr = temp / (int)Math.pow(10, i-1); //获取第i位
            low = temp%(int)Math.pow(10, i-1); //获取第i位的低位
            if(curr == x){ //等于x
                total += high*(int)Math.pow(10, i-1)+ low + 1;
            }else if(curr < x){ //小于x
                total += high*(int) Math.pow(10, i-1);
            }else{ //大于x
                total += (high + 1) * (int)Math.pow(10, i-1);
            }
            i++;
        }
        return total;
    }
}
