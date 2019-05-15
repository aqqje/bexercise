package blibli.one;

import java.util.Scanner;

/**
 * @author AqqJe
 * @description 22娘和33娘接到了小电视君的扭蛋任务：
 * 一共有两台扭蛋机，编号分别为扭蛋机2号和扭蛋机3号，22娘使用扭蛋机2号，33娘使用扭蛋机3号。
 * 扭蛋机都不需要投币，但有一项特殊能力：
 * 扭蛋机2号：如果塞x（x范围为>=0正整数）个扭蛋进去，然后就可以扭到2x+1个
 * 扭蛋机3号：如果塞x（x范围为>=0正整数）个扭蛋进去，然后就可以扭到2x+2个
 * 22娘和33娘手中没有扭蛋，需要你帮她们设计一个方案，两人“轮流扭”（谁先开始不限，扭到的蛋可以交给对方使用），用“最少”的次数，使她们能够最后恰好扭到N个交给小电视君。
 *
 * 思路分析:
 *  反向向下加到 N, 优先考虑 3 号机, N 减少的最快
 *  10 = 2 * 4 + 2 ------ 3
 *  4 = 2 * 1 + 2 ------ 3
 *  1 = 2 * 0 + 1 ------ 2
 *
 *  11 = 2 * 5 + 1 ------ 3
 *  5 = 2 * 2 + 1 ------ 2
 *  2 = 2 * 0 + 2 ------ 3
 */
public class Solution1 {
    private static StringBuilder sb = new StringBuilder();

    public static String getEggOrder(int cnt){

        if(cnt <= 0) return sb.reverse().toString();
        if(cnt % 2 == 0){
            sb.append("3");
            getEggOrder((cnt - 2) / 2);
        }else{
            sb.append("2");
            getEggOrder((cnt - 1) / 1 );
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(Solution1.getEggOrder(n));

    }
}
