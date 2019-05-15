package blibli.one;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

/**
 * @author AqqJe
 * @description
 */
public class Solution10_2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        int N = 0;
        String[] strs = str.split(" ");
        String[] t = strs[strs.length-1].split(",");

        int[] res = new int[strs.length+1];
        int cnt = 0;
        for (int i = 0; i < strs.length-1; i++) {

            res[cnt++] = Integer.parseInt(strs[i]);

        }
        res[cnt++] = Integer.parseInt(t[0]);
        N = Integer.parseInt(t[1]);
        Arrays.sort(res);
        boolean flag = false;
        cnt = res.length;

        for (int i = 0; i < cnt - 2; i++) {
            int target = N-res[i];
            HashSet<Integer> set = new HashSet<>();
            for (int j = i+1; j < cnt; j++) {
                if (set.contains(target - res[j])) {
                    flag=true;
                    break;
                } else {
                    set.add(res[j]);
                }
            }
        }
        if (flag) {
            System.out.println("True");
        } else {
            System.out.println("False");
        }
    }
}
