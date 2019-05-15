package blibli.one;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author AqqJe
 * @description 猛兽侠中精灵鼠在利剑飞船的追逐下逃到一个n*n的建筑群中，精灵鼠从（0,0）的位置进入建筑群，建筑群的出口位置为（n-1,n-1），建筑群的每个位置都有阻碍，每个位置上都会相当于给了精灵鼠一个固定值减速，因为精灵鼠正在逃命所以不能回头只能向前或者向下逃跑，现在问精灵鼠最少在减速多少的情况下逃出迷宫？
 */
public class Solution7_1 {

    public static void main(String[] args) throws Exception {
        //BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("test.txt")));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        while (in.ready()) {
            int n = Integer.parseInt(in.readLine());
            int[][] nums = new int[n][n];
            for (int i = 0; i < n; i++) {
                String[] strings = in.readLine().split(",");
                for (int j = 0; j < n; j++) {
                    nums[i][j] = Integer.parseInt(strings[j]);
                }
            }
            for (int i = 1; i < n; i++) {
                nums[i][0] += nums[i - 1][0];
            }
            for (int i = 1; i < n; i++) {
                nums[0][i] += nums[0][i - 1];
            }
            for (int i = 1; i < n; i++) {
                for (int j = 1; j < n; j++) {
                    nums[i][j] += Math.min(nums[i - 1][j], nums[i][j - 1]);
                }
            }
            System.out.println(nums[n - 1][n - 1]);
        }
        in.close();
    }
}
