package blibli.one;

import java.util.Stack;

/**
 * @author AqqJe
 * @description
 */
public class Solution7_3 {

    public static boolean IsPopOrder(int[] pushA, int[] popA) {
        Stack<Integer> stack = new Stack<>();
        stack.push(pushA[0]);
        int j = 0;
        for (int i = 1; i < pushA.length; i++) {
            if (stack.peek() != popA[j]) {
                stack.push(pushA[i]);
            } else {
                stack.pop();
                j++;
                i--;
            }
        }
        while (!stack.isEmpty()&&stack.peek() == popA[j]) {
            stack.pop();
            j++;
        }
        return stack.isEmpty();
    }
}
