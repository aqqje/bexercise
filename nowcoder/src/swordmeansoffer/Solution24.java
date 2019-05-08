package swordmeansoffer;

/**
 * @author AqqJe
 * @description 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。
 * 如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同。
 */
public class Solution24 {
    public boolean IsTreeBST(int[] sequence, int start, int end){
        // 如果只有一个数,直接返回
        if(start >= end) return true;
        int i = start;
        // 获取左右子树分隔点
        for(; i < end; i++){
            if(sequence[i] > sequence[end]) break;
        }
        // 判定右子树是否合理
        for(int j = i; j < end; j++){
            // 不合理直接返回
            if(sequence[j] < sequence[end]) return false;
        }
        return IsTreeBST(sequence, start, i - 1) && IsTreeBST(sequence, i, end - 1);

    }

    public boolean VerifySquenceOfBST(int [] sequence) {
        if(sequence == null || sequence.length == 0) return false;
        return IsTreeBST(sequence, 0, sequence.length - 1);
    }
}
