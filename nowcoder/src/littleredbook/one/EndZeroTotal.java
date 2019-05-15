package littleredbook.one;

import java.util.Scanner;

/**
 * @author AqqJe
 * @description
 * 输入一个自然数n，求表达式 f(n) = 1!2!3!.....n! 的结果末尾有几个连续的0？
 * 求表达式 f(n)结果末尾0的个数
 */
public class EndZeroTotal {
    public static long getEndZeroTotal1(long n){
        long total = 0;
        for(int i = 1; i <= n; i++){
            int t = i;
            while(t > 0){
                total += t / 5;
                t /= 5;
            }
        }
        return total;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long endZeroTotal1 = getEndZeroTotal1(n);
        System.out.println(endZeroTotal1);
    }
}
