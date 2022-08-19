package com.zyc.doublecursor;

public class Solution1 {

    static class ListNode {
        int val;
        ListNode next;

        public ListNode() {
        }

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

    }

    public static ListNode build(ListNode head, Object... objects) {
        ListNode p = head;
        for (int i = 0; i < objects.length; ++i) {
            ListNode node = new ListNode((int) objects[i]);
            p.next = node;
            p = node;
        }
        return head;
    }

    public static void print(ListNode head) {
        ListNode qNode = head;
        while (qNode != null) {
            System.out.print(qNode.val);
            qNode = qNode.next;
        }
    }

    /**
     * 删除有序链表中的重复节点
     *
     * @param head 表头节点
     * @return 1 2 3 3 4 4 5
     */
    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode x_head = new ListNode(-1, head);
        ListNode cur = x_head;
        while (cur.next != null && cur.next.next != null) {
            if (cur.next.val == cur.next.next.val) {
                int rep = cur.next.val;
                while (cur.next != null && cur.next.val == rep) {
                    cur.next = cur.next.next;
                }
            } else
                cur = cur.next;
        }
        return x_head.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1, null);
        head = Solution1.build(head, 2, 3, 3, 4, 4, 5);
        head = deleteDuplicates(head);
        print(head);

    }


}
