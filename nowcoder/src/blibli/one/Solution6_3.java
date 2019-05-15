package blibli.one;

import java.util.Scanner;

/**
 * @author AqqJe
 * @description
 */
public class Solution6_3 {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String str1=sc.next();
        String str2=sc.next();
        String[]a=str1.split("\\.");
        String[]b=str2.split("\\.");
        Solution6_3 bilibili_version=new Solution6_3();
        int res=bilibili_version.compareto(a, b);
        if(res==0){
            if(a.length>b.length){
                res=1;
            }
            if(a.length<b.length){
                res=-1;
            }

            if(a.length==b.length){
                res=0;
            }

        }
        System.out.println(res);
    }

    public int compareto(String[]a,String[]b){
        int len=Integer.min(a.length,b.length);
        for(int i=0;i<len;i++){
            if(Integer.parseInt(a[i])>Integer.parseInt(b[i])){
                return 1;
            }

            if(Integer.parseInt(a[i])<Integer.parseInt(b[i])){
                return -1;
            }

            if(Integer.parseInt(a[i])==Integer.parseInt(b[i])){
                if(i==len-1)
                    return 0;
            }
        }
        return 0;
    }
}
