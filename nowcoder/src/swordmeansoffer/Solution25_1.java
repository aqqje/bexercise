package swordmeansoffer;

import swordmeansoffer.base.TreeNode;

import java.util.ArrayList;

/**
 * @author AqqJe
 * @description 题目描述
 * 输入一颗二叉树的跟节点和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
 * (注意: 在返回值的list中，数组长度大的数组靠前)
 */
public class Solution25_1 {
    // 结果路径
    private ArrayList<ArrayList<Integer>> resultList = new ArrayList<ArrayList<Integer>>();
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        if(root == null) return resultList;
        int[] path = new int[1000];
        this.isTargetPath(root, target, 0, path, 0);
        return this.resultList;
    }

    /**
     *
     * @param curNode 当前节点
     * @param target 目标和
     * @param curSun 当前和
     * @param path 当前路径
     * @param index 当前的几个节点
     */
    public void isTargetPath(TreeNode curNode, int target, int curSun,int[] path, int index){
        if(curNode == null) return;
        // 添加当前节点值
        curSun += curNode.val;
        path[index]= curNode.val;
        index ++;
        // 如果当前节点没有子节点
        if(curSun == target && curNode.left == null && curNode.right == null){
            // 储存当前路径
            ArrayList<Integer> pathList = new ArrayList<>();
            for(int i = 0; i < index; i++){
                pathList.add(path[i]);
            }
            // 添加到时结果路径中
            resultList.add(pathList);
            return;
        }
        // 如果存在左节点
        if(curSun < target && curNode.left != null){
            this.isTargetPath(curNode.left, target, curSun, path, index);
        }
        // 如果存在右节点
        if(curSun < target && curNode.right != null){
            this.isTargetPath(curNode.right, target, curSun, path, index);
        }
    }
}
