package littleredbook;

import littleredbook.base.TreeNode;

import java.util.*;

/**
 * @author 琉璃201901090019116
 * @description 给定二叉树T（树深度不超过H<=10，深度从1开始，节点个数N<1024，节点编号1~N）的层序和中序遍历，输出T从左向右叶子节点以及树先序和后序遍历序列
 */
public class TwoForkTree {
    public static StringBuffer sb1=new StringBuffer();
    public static StringBuffer sb2=new StringBuffer();
    public static StringBuffer sb3=new StringBuffer();
    public static void main(String[] args){
        Scanner scan=new Scanner(System.in);
        String str1=scan.nextLine();
        String str2=scan.nextLine();
        String[] s1=str1.split(" ");
        TreeNode root=xun(s1,str2);
        preOrder(root);
        postOrder(root);
        System.out.println(sb1.toString().trim());
        System.out.println(sb2.toString().trim());
        System.out.println(sb3.toString().trim());
    }
    public static TreeNode xun(String[] a,String b){
        if(b.length()==0) return null;
        int index=0;
        String[] s2=b.split(" ");
        int len=s2.length;
        TreeNode temp=new TreeNode(a[0]);
        for(;index<len;index++){
            if(a[0].equals(s2[index])) break;
        }
        ArrayList<String> list1=new ArrayList(Arrays.asList(a));
        ArrayList<String> list2=new ArrayList(Arrays.asList(a));
        for(int i=0;i<=index;i++)
            list1.remove(s2[i]);
        for(int i=index;i<len;i++)
            list2.remove(s2[i]);
        temp.left=xun(list2.toArray(new String[list1.size()]),b.substring(0,b.indexOf(s2[index])));
        if(index==len-1)
            temp.right=null;
        else
            temp.right=xun(list1.toArray(new String[list2.size()]),b.substring(b.indexOf(s2[index+1])));
        if(temp.left==null&&temp.right==null) sb1.append(temp.val+" ");
        return temp;
    }
    public static void preOrder(TreeNode root){
        if(root!=null){
            sb2.append(root.val+" ");
            preOrder(root.left);
            preOrder(root.right);
        }
    }
    public static void postOrder(TreeNode root){
        if(root!=null){
            postOrder(root.left);
            postOrder(root.right);
            sb3.append(root.val+" ");
        }
    }
}