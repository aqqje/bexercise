package blibli.one;

import java.util.Scanner;

/**
 * @author AqqJe
 * @description
 */
public class Solution6_2 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] input = in.nextLine().split(" ");
        String[] str1 = input[0].split("\\.");
        String[] str2 = input[1].split("\\.");
        for (int i = 0;i < str1.length || i <str2.length;i++) {
            int n1 = i < str1.length ? Integer.parseInt(str1[i]) : 0;
            int n2 = i < str2.length ? Integer.parseInt(str2[i]) : 0;
            if (n1 > n2) {
                System.out.print(1);
                return;
            } else if(n1 < n2) {
                System.out.print(-1);
                return;
            }
        }
        System.out.print(0);
    }
}
