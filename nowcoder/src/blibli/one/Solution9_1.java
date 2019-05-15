package blibli.one;

import java.util.Scanner;

/**
 * @author AqqJe
 * @description 从业 666 年的 BILIBILI 网络安全工程师 KindMo 最近很困惑，公司有一个业务总是受到 SSRF 攻击。请帮他写一个程序，判断输入的字符串是否属于内网IP，用于防御该漏洞。
 * 我们知道常见的内网IP有，127.0.0.1，192.168.0.1 等。
 */
public class Solution9_1 {

    public static void main(String []args){
        Scanner sc=new Scanner(System.in);
        while(sc.hasNext()){
            String []strings=sc.nextLine().split("\\.");
            if(Integer.parseInt(strings[0])==127||Integer.parseInt(strings[0])==10||Integer.parseInt(strings[0])==172){
                System.out.println(1);
            }
            else if(Integer.parseInt(strings[0])==192&&Integer.parseInt(strings[1])==168){
                System.out.println(1);
            }
            else System.out.println(0);
        }
    }

}
