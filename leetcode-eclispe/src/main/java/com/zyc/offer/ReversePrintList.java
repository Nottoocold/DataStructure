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

}

class ListNode {
    int val;
    ListNode next;

    public ListNode(int val,ListNode next) {
        this.val = val;
        this.next = next;
    }
}