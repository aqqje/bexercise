package swordmeansoffer;

import swordmeansoffer.base.TreeNode;

import java.util.ArrayList;

/**
 * @author AqqJe
 * @description 从上往下打印出二叉树的每个节点，同层节点从左至右打印。
 */
public class Solution23 {
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        // 使用一个辅助队列
        ArrayList<TreeNode> queue = new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<Integer>();
        // 判断二叉树是否存在
        if(root == null) return list;
        // 初始化队列
        queue.add(root);
        // 判断左边是否存在
        while(queue.size() != 0){
            TreeNode current = queue.remove(0);
            list.add(current.val);
            if(current.left != null){
                queue.add(current.left);

            }
            // 判断右边是否存在
            if(current.right != null){
                queue.add(current.right);
            }
        }
        return list;
    }
}
