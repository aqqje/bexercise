package swordmeansoffer;

import java.util.ArrayList;

/**
 * @author AqqJe
 * @description 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，
 * 例如，如果输入如下4 X 4矩阵： 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16
 * 则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
 */
public class Solution19 {
    public static ArrayList<Integer> printMetria(int[][] metria){
        int row = metria.length;
        int col = metria[0].length;
        if(row == 0 && col == 0) return null;
        int left = 0; int top = 0; int right = row - 1;int bottom = col - 1;
        ArrayList<Integer> list = new ArrayList<>();
        while(right >= left && bottom >= top){
            // 左--->右
            for(int i = left; i <=right; i++){
                list.add(metria[top][i]);
            }
            // 上--->下
            for(int j = top + 1; j <= bottom; j++){
                list.add(metria[j][right]);
            }
            // 右--->左
            if(left != right){
                for(int k = right - 1; k >= left; k--){
                    list.add(metria[bottom][k]);
                }
            }
            if(top != bottom){
                for(int l = bottom - 1; l > top; l--){
                    list.add(metria[l][left]);
                }
            }
            // 下--->上
            top++;left++;bottom--;right--;
        }
        return list;

    }

    // 测试
    public static void main(String[] args) {
        int[][] array = new int[][]{
                {1,2,3,4},
                {5,6,7,8},
                {9,10,11,12},
                {13,14,15,16},
        };
        ArrayList<Integer> list = printMetria(array);
        System.out.println(list);
    }
}
