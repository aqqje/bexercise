package titlebully;

/**
 * 题霸: 回路
 *
 * 题目描述
 * 题意
 * 牛牛在一个迷宫中，迷宫有 n 个格子，有 m 条通道，每条通道连接两个格子 u, v，编号为 u 的格子与编号为 v 的格子可互相到达，每人每条通道只能走一次。
 * 牛牛想知道，他是否能从 1号格子出发回到 1 号格子。
 * 输入
 * 第一行给定两个整数 n , m 。
 * 接下来 m 行，每行有两个整数 u，v 。
 *1≤n≤100,0000≤m≤100,0000≤L≤m
 * m对 u, v 互不相同
 *
 * 输出
 * 若能回到 11 号格子则返回Yes，否则返回No。
 *
 *
 * 示例1
 * 输入
 *      [4, 4],[(1,2), (2, 3), (3,4),(4,1)]
 * 输出
 *      "Yes"
 * @Author AqqJe
 * @Date 2020/8/3
 * @Version 1.0
 **/
public class ReturnCircuit {
    /**
     * 能回到1号点返回 Yes，否则返回 No
     * @param param int整型一维数组 param[0] 为 n，param[1] 为 m
     * @param edge Point类一维数组 Point.x , Point.y 分别为一条边的两个点
     * @return string字符串
     */
    public String solve (int[] param, Point[] edge) {
        // write code here
        return "";
    }
}
class Point {
    int x;
    int y;
}
