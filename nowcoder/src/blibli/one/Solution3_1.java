package blibli.one;

import java.util.Scanner;
import java.util.Stack;

/**
 * @author AqqJe
 * @description 给定一个合法的表达式字符串，其中只包含非负整数、加法、减法以及乘法符号（不会有括号），例如7+3*4*5+2+4-3-1，请写程序计算该表达式的结果并输出；
 */
public class Solution3_1 {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        while (input.hasNext()) {
            String str=input.nextLine();
            if (!str.equals("END")) {
                //char[] str = s.toCharArray();
                value(str);

            }else
                break;
        }
        input.close();

    }

    private static void value(String str) {
        Stack<Integer> num = new Stack<>();
        Stack<Character> symbol = new Stack<>();
        int index = 0;
        int temp = index;
        while (index<str.length() && str.charAt(index) >= '0' && str.charAt(index) <= '9') { //存数
            index++;
        }
        num.push(Integer.parseInt(str.substring(temp, index)));
        while (index < str.length()) {
            symbol.push(str.charAt(index++));
            //System.out.println(index);
            temp = index;

            while (index<str.length() && str.charAt(index) >= '0' && str.charAt(index) <= '9') {
                index++;
            }

            num.push(Integer.parseInt(str.substring(temp, index)));
            if (symbol.peek() == '*') {     //如果操作数为乘直接计算
                int a = num.pop();
                int b = num.pop();
                symbol.pop();
                int c = b*a;
                num.push(c);
            }else {
                continue;

            }
        }
        Stack<Integer> num_temp = new Stack<>();
        Stack<Character> symbol_temp = new Stack<>();
        while (!symbol.empty()) {
            symbol_temp.push(symbol.pop());
        }
        while (!num.empty()) {
            num_temp.push(num.pop());
        }
        while (!symbol_temp.empty()) {
            if (symbol_temp.peek() == '+') {
                int a = num_temp.pop();
                int b = num_temp.pop();
                symbol_temp.pop();
                int c = a+b;
                num_temp.push(c);
            }else {
                int a = num_temp.pop();
                int b = num_temp.pop();
                symbol_temp.pop();
                int c = a-b;
                num_temp.push(c);
            }
        }

        System.out.println(num_temp.pop());
    }
}
