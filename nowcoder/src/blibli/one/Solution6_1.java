package blibli.one;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author AqqJe
 * @description 如果version1 > version2 返回1，如果 version1 < version2 返回-1，不然返回0.
 *
 * 输入的version字符串非空，只包含数字和字符.。.字符不代表通常意义上的小数点，只是用来区分数字序列。例如字符串2.5并不代表二点五，只是代表版本是第一级版本号是2，第二级版本号是5.
 */
public class Solution6_1 {

    public static void main(String[] args) throws Exception {
        //BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("test.txt")));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        while (in.ready()) {
            String[] strings = in.readLine().split(" ");
            String[] str1 = strings[0].split("\\.");
            String[] str2 = strings[1].split("\\.");
            System.out.println(compare(str1, str2, 0));
        }
        in.close();
    }

    private static int compare(String[] str1, String[] str2, int i) {
        if (i == str1.length&&i==str2.length)
            return 0;
        else if (i == str2.length)
            return 1;
        else if (i == str1.length)
            return -1;
        if (Integer.parseInt(str1[i]) - Integer.parseInt(str2[i]) > 0) {
            return 1;
        } else if (Integer.parseInt(str1[i]) - Integer.parseInt(str2[i]) < 0) {
            return -1;
        }
        return compare(str1, str2, i + 1);
    }
}
