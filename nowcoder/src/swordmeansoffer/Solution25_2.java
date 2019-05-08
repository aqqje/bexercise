package swordmeansoffer;

import swordmeansoffer.base.TreeNode;

import java.util.ArrayList;

/**
 * @author AqqJe
 * @description 题目描述
 * 输入一颗二叉树的跟节点和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
 * (注意: 在返回值的list中，数组长度大的数组靠前)
 */
public class Solution25_2 {
    // 结果路径
    private ArrayList<ArrayList<Integer>> resultList = new ArrayList<ArrayList<Integer>>();
    private ArrayList<Integer> pathList = new ArrayList<Integer>();
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        if(root == null) return resultList;
        // 如果当前只有一个父节点,无左右子节点
        pathList.add(root.val);
        if(target == root.val && root.left == null && root.right == null){
            resultList.add(new ArrayList<Integer>(pathList));
        }
        // 如果存在左节点
        if(root.val < target && root.left != null){
            FindPath(root.left, target - root.val);
        }
        // 如果存在右节点
        if(root.val < target && root.right != null){
            FindPath(root.right, target - root.val);
        }
        // 回退到父节点
        pathList.remove(pathList.size() - 1);
        return resultList;
    }


}
