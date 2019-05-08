package swordmeansoffer;

import java.util.Iterator;
import java.util.Stack;


/**
 * @author AqqJe
 * @description 定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数（时间复杂度应为O（1））。
 */
public class Solution20 {
    Stack<Integer> stack = new Stack<>();
    public void push(int node) {
        stack.push(node);
    }

    public void pop() {
        stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int min() {
        int min = stack.peek();
        Iterator<Integer> iterator = stack.iterator();
        int temp = 0;
        while(iterator.hasNext()){
            temp = iterator.next();
            if(min > temp){
                min = temp;
            }
        }
        return min;
    }
}
