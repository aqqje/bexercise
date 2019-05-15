package blibli.one;

import java.util.Scanner;

/**
 * @author AqqJe
 * @description
 */
public class Solution13_2 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] val = getArray(in.nextLine().split(","));
        int[] value = new int[val.length];
        for (int p = 0, l = val.length - 1, f = 0; p <= l;f++){
            if (f % 2 == 0){
                value[f] = val[p++];
            }else{
                value[f] = val[l--];
            }
        }
        int ind = 0;
        for (int i : value) {
            if(ind++ == val.length-1){
                System.out.print(i);
            }else{
                System.out.print(i + ",");
            }
        }
    }

    public static int[] getArray(String[] val) {
        int[] arr = new int[val.length];
        for (int i = 0; i < val.length; i++) {
            arr[i] = Integer.parseInt(val[i]);
        }
        return arr;
    }
}
