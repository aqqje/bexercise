package Interviewquestions;

import java.util.*;

/**
 * 用java实现一个算法，给定一个自然数，求出所有累加得到他的表达式。
 * 比如给出自然数5，那么表达式有：1+1+1+1+1,1+1+1+2,1+1+3，1+2+2，1+4,2+3
 */
public class QAOne {
    public static void main(String[] args) {
        Set<List> a = a(new HashSet<List>(), new ArrayList<List>(), 5);
        for (List list : a) {
            System.out.print(Arrays.toString(list.toArray()).replace("[", "").replace("]", "")
                    .replaceAll(" ", "").replaceAll(",", "+"));
        }

    }

    public static Set<List> a(Set<List> set, List list, int num){
        for(int i = 1; i <= num; i++){
            List thisContext = new ArrayList<List>(list);
            thisContext.add(i);
            int nextNum = num - i;
            if(nextNum == 0 && thisContext.size() > 1){
                Collections.sort(thisContext);
                set.add(thisContext);
                continue;
            }
            a(set, thisContext, nextNum);
        }
        return set;
    }
}
