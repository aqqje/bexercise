package swordmeansoffer;

import swordmeansoffer.base.TreeNode;

import java.util.ArrayList;

/**
 * @author AqqJe
 * @description 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否可能为该栈的弹出顺序。
 * 假设压入栈的所有数字均不相等。例如序列1,2,3,4,5是某栈的压入顺序，序列4,5,3,2,1是该压栈序列对应的一个弹出序列，
 * 但4,3,5,1,2就不可能是该压栈序列的弹出序列。（注意：这两个序列的长度是相等的
 */
public class Solution22 {
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        // 判断二叉树是否存在
        if(root == null) return null;
        // 记录根
        list.add(root.val);
        // 判断左边是否存在
        if(root.left != null){
            PrintFromTopToBottom(root.left);
        }
        // 判断右边是否存在
        if(root.right != null){
            PrintFromTopToBottom(root.right);
        }
        return list;
    }
}
