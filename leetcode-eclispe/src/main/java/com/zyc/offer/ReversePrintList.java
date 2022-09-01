package com.zyc.offer;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个链表头节点，从尾到头用数组返回其中的元素
 */
public class ReversePrintList {

    public int[] reversePrint(ListNode head) {
        List<Integer> list = new ArrayList<>();
        dfs(head, list);
        int[] ret = new int[list.size()];
        int i = 0;
        for (Integer integer : list) {
            ret[i++] = integer;
        }
        return ret;
    }

    // 2,4,1,3,5,6
    private void dfs(ListNode head, List<Integer> list) {
        if (head != null) {
            dfs(head.next, list);
            list.add(head.val);
        }
    }

    // 链表中节点值互不相同
    public ListNode deleteNode(ListNode head, int val) {
        if (head == null) return null;
        ListNode cur = head, pre = null;
        while (cur != null) {
            if (cur.val == val) {
                if (pre == null) {
                    pre = cur.next;
                } else {
                    pre.next = cur.next;
                }
                break;
            } else {
                pre = cur;
                cur = cur.next;

            }
        }
        return head.val == val ? pre : head;
    }

    // 给定一个数字k代表链表倒数第k个节点，规定最后一个节点就是倒数第一个节点
    // 返回从第k个节点的引用
    public ListNode getKthFromEnd(ListNode head, int k) {
        List<ListNode> list = new ArrayList<>();
        list.add(null);
        int count = 0;
        ListNode p = head;
        while (p != null) {
            count++;
            list.add(p);
            p = p.next;
        }
        return list.get(count - k + 1);
    }

    // 相交链表 返回第一个相交的节点
    ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode p = headA, q = headB;
        while (p != q) {
            if (p == null) p = headB;
            else p = p.next;
            if (q == null) q = headA;
            else q = q.next;
        }
        return p;
    }
}

class ListNode {
    int val;
    ListNode next;

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}