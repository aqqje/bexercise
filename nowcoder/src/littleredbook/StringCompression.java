package littleredbook;

import java.util.Scanner;

/**
 * @author AqqJe
 * @description 输入一串字符，请编写一个字符串压缩程序，将字符串中连续出现的重复字母进行压缩，并输出压缩后的字符串。
 * 例如：
 * aac 压缩为 1ac
 * xxxxyyyyyyzbbb 压缩为 3x5yz2b
 */
public class StringCompression {

    // 测试
    public static void main(String[] args) {
        System.out.println(stringCompression("When you need someone to listen, I'll be there. When you need a hug, I'll be there. When you need someone to hold your hand, I'll be there. When you need someone to wipe your tears, guess what? I'll be there. William Shakespeare"));
    }


    public static String stringCompression(String str){
        // 用于存储压缩字符串
        StringBuilder builder = new StringBuilder();
        // 当前字符相同记录数
        int count = 0;
        char[] chars = str.toCharArray();
        // 当前字符
        char current = chars[0];
        int len = chars.length;
        for (int i = 0; i < len; i++) {
            if(current == chars[i]){
                count++;
            }
            if(current != chars[i]){
                if(count > 1){
                    builder.append(count-1);
                    builder.append(current);
                }else{
                    builder.append(current);
                }
                // 重新初始化
                current = chars[i];
                count = 1;
            }
            // 尾部处理
            if(i == len -1){
                if(count > 1){
                    builder.append(count-1);
                    builder.append(current);
                }else{
                    builder.append(current);
                }
            }
        }
        return builder.toString();

    }
}
