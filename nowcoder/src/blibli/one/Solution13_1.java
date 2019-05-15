package blibli.one;

import java.util.Scanner;

/**
 * @author AqqJe
 * @description 对于一个链表 L: L0→L1→…→Ln-1→Ln,
 * 将其翻转成 L0→Ln→L1→Ln-1→L2→Ln-2→…
 *
 * 输入是一串数字，请将其转换成单链表格式之后，再进行操作
 */
public class Solution13_1 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] input = in.nextLine().split(",");
        String[] output = new String[input.length];
        for (int i = 0;i < (input.length + 1) / 2;i++) {
            output[i * 2] = input[i];
            if (i * 2 + 1 < input.length) {
                output[i * 2 + 1] = input[input.length - 1 - i];
            }
        }
        String res = String.join(",",output);
        System.out.println(res);
    }
}
