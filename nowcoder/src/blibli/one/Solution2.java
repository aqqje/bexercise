package blibli.one;

import java.util.Scanner;

/**
 * @author AqqJe
 * @description av394281 中，充满威严的蕾米莉亚大小姐因为触犯某条禁忌，被隙间妖怪八云紫（紫m……èi）按住头在键盘上滚动。
 * 同样在弹幕里乱刷梗被紫姐姐做成罪袋的你被指派找到大小姐脸滚键盘打出的一行字中的第 `k` 个仅出现一次的字。
 * (为简化问题，大小姐没有滚出 ascii 字符集以外的字)
 */
public class Solution2 {

    public static String fun(int k, String string) {
        int[] counts = new int[255];
        for (int i = 0; i < string.length(); i++) {
            counts[string.charAt(i)]++;
        }

        for (int i = 0; i < string.length(); i++) {
            if(counts[string.charAt(i)] == 1) {
                k--;
                if(k == 0) return "["+string.charAt(i)+"]";
            }
        }

        return "Myon~";
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int k = scanner.nextInt();
            String string = scanner.nextLine();

            string = string.replaceFirst(" ", "");
            System.out.println(fun(k, string));
        }
    }
}
