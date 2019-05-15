package blibli.one;

import java.util.Scanner;

/**
 * @author AqqJe
 * @description
 */
public class Solution7_2 {

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        while(in.hasNextInt()){
            int n = Integer.parseInt(in.nextLine());
            int[][] arr = new int[n][n];
            for(int i=0; i<n; i++){
                String[] s = in.nextLine().split(",");
                for(int j=0; j<n; j++){
                    arr[i][j] = Integer.parseInt(s[j]);
                }
            }
            //重写最后一行，最后一列；
            for(int i=n-2; i>=0; i--){
                arr[i][n-1] += arr[i+1][n-1];
                arr[n-1][i] += arr[n-1][i+1];
            }
            //补全其他行，列；
            for(int i=n-2; i>=0; i--){
                for(int j=n-2; j>=0; j--){
                    arr[i][j] += Math.min(arr[i+1][j], arr[i][j+1]);
                }
            }
            System.out.println(arr[0][0]);
        }
        in.close();
    }
}
