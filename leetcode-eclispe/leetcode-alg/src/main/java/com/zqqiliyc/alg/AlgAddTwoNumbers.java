package com.zqqiliyc.alg;

import com.zqqiliyc.datastruct.node.ListNode;

/**
 * Add Two Numbers, link: <a href="https://leetcode.cn/problems/add-two-numbers/description/">LeetCode 2</a><br>
 * <p>
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字
 * <p>
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * <p>
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p>
 * 示例：
 * <p>
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 * <p>
 * 说明：
 * <p>
 * 输入的链表长度范围为 [1, 100]
 * 0 <= Node.val <= 9
 * 题目数据保证列表表示的数字不含前导零
 * 将两个数相加，并以相同形式返回一个表示和的链表。
 *
 * @author zqqiliyc
 * @since 2024-09-04
 */
public class AlgAddTwoNumbers {

    public ListNode<Integer> addTwoNumbers(ListNode<Integer> l1, ListNode<Integer> l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        // 辅助头结点
        ListNode<Integer> dummy = new ListNode<>(0);
        ListNode<Integer> curr = dummy;
        // 进位
        int carry = 0;
        // 遍历两个链表，相加，并记录进位
        while (l1 != null || l2 != null) {
            int val1 = l1 == null ? 0 : l1.getVal();
            int val2 = l2 == null ? 0 : l2.getVal();
            int sum = val1 + val2 + carry;
            carry = sum / 10;
            curr.setNext(new ListNode<>(sum % 10));
            curr = curr.getNext();
            if (l1 != null) l1 = l1.getNext();
            if (l2 != null) l2 = l2.getNext();
        }

        if (carry > 0) {
            curr.setNext(new ListNode<>(carry));
        }

        return dummy.getNext();
    }
}
