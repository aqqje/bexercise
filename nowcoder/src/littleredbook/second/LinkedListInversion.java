package littleredbook.second;

import swordmeansoffer.base.ListNode;

/**
 * @author AqqJe
 * @description
 * 给出一个链表，每 k 个节点一组进行翻转，并返回翻转后的链表。k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么将最后剩余节点保持原有顺序。
 *
 * 说明：
 * 1. 你需要自行定义链表结构，将输入的数据保存到你的链表中；
 * 2. 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换；
 * 3. 你的算法只能使用常数的额外空间。
 */

public class LinkedListInversion {

    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null) return head;
        //获取长度
        int len = getLength(head);
        //t表示可以逆转几组
        int t = len/k;
        //定义虚拟头结点
        ListNode dummy = new ListNode(-1);
        //定义sign标记结点
        ListNode sign = dummy;
        dummy.next = head;
        ListNode f = head;
        ListNode s = f.next;
        while(t>0){
            int m = k;
            //三指针逆转
            while(m>1&&f!=null){
                f.next = s.next;
                s.next = sign.next;
                sign.next = s;
                s = f.next;
                m--;
            }
            //让sign指向当前f
            sign = f;
            //f指向当前s
            f = s;
            //判断f是否为空，为空直接返回
            if(f == null) break;
            //s指向s.next
            s = s.next;
            //逆转次数减一
            t--;
        }
        return dummy.next;
    }

    public static int getLength(ListNode head){
        ListNode cur = head;
        int count = 0;
        while(cur!=null){
            cur = cur.next;
            count++;
        }
        return count;
    }
}
