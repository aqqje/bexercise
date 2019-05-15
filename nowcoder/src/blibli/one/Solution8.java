package blibli.one;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @author AqqJe
 * @description 给定一个数字矩阵，请设计一个算法从左上角开始顺时针打印矩阵元素
 */
public class Solution8 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int M = in.nextInt();
            int N = in.nextInt();
            if (M == -1 || N == -1) {
                return;
            }
            int[][] matrix = new int[M][N];
            for (int i = 0;i < M;i++) {
                for (int j = 0;j < N;j++) {
                    matrix[i][j] = in.nextInt();
                }
            }
            int start = 0;
            ArrayList<Integer> list = new ArrayList<>();
            while(M > start * 2 && N > start * 2){
                printMatrixInCircle(list, matrix, M, N, start);
                start++;
            }
            ArrayList<String> another = new ArrayList<>();
            for (Iterator<Integer> l = list.iterator(); l.hasNext();) {
                another.add(String.valueOf(l.next()));
            }
            String res = String.join(",", another);
            System.out.print(res);
            System.out.println();
        }
    }
    private static void printMatrixInCircle(ArrayList<Integer> list, int[][] nums,
                                            int  rows, int cols, int start) {
        int endX = cols - 1 - start;
        int endY = rows - 1 - start;

        //从左到右打印一行
        for (int i = start;i <= endX;i++) {
            int number = nums[start][i];
            list.add(number);
        }
        //从上到下打印一列
        if(start < endY){
            for (int i = start + 1;i <= endY;i++) {
                int number = nums[i][endX];
                list.add(number);
            }
        }
        //从右向左打印一行
        if(start < endX && start < endY){
            for (int i = endX - 1;i >= start;i--) {
                int number = nums[endY][i];
                list.add(number);
            }
        }
        //从下向上打印一列
        if(start < endX && start < endY - 1){
            for (int i = endY - 1;i >= start + 1;i--) {
                int number = nums[i][start];
                list.add(number);
            }
        }
    }
}
