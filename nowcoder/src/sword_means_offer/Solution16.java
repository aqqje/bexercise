package sword_means_offer;

import sword_means_offer.base.ListNode;

/**
 * @description 牛客剑指offer16
 * @author AqqJe
 */
public class Solution16 {
    /**
     * 输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
     * 递归法
     * @param list1
     * @param list2
     * @return
     */
    public static ListNode Merge(ListNode list1, ListNode list2) {
        if(list1 == null) return list2;
        if(list2 == null) return list1;
        // 声明新链表
        ListNode MergeHead = null;
        if(list1.val > list2.val){
            MergeHead = list2;
            MergeHead.next = Merge(list1, list2.next);
        }else{
            MergeHead = list1;
            MergeHead.next = Merge(list1.next, list2);
        }
        return MergeHead;
    }


    /**
     * 普通法
     * @param list1
     * @param list2
     * @return
     */
    public ListNode Merge2(ListNode list1, ListNode list2){
        if(list1 == null){
            return list2;
        }
        if(list2 == null){
            return list1;
        }
        ListNode mergeHead = null;
        ListNode current = null;
        while(list1!=null && list2!=null){
            if(list1.val <= list2.val){
                if(mergeHead == null){
                    mergeHead = current = list1;
                }else{
                    current.next = list1;
                    current = current.next;
                }
                list1 = list1.next;
            }else{
                if(mergeHead == null){
                    mergeHead = current = list2;
                }else{
                    current.next = list2;
                    current = current.next;
                }
                list2 = list2.next;
            }
        }
        if(list1 == null){
            current.next = list2;
        }else{
            current.next = list1;
        }
        return mergeHead;
    }

    // 测试
    public static void main(String[] args) {
        ListNode li1 = new ListNode(3);
        ListNode li2 = new ListNode(5);
        ListNode list1 = new ListNode(1);
        list1.next = li1;
        li1.next = li2;
        ListNode l1 = new ListNode(6);
        ListNode l2 = new ListNode(4);
        ListNode list2 = new ListNode(2);
        list2.next = l2;
        l2.next = l1;

        System.out.println( Merge(list1, list2));
    }
}
