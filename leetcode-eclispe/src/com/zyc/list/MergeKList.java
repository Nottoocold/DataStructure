package com.zyc.list;

public class MergeKList {
	
	class ListNode {
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
	
	public ListNode mergeTwoList(ListNode l1,ListNode l2) {
		if (l1 == null || l2 == null) {
			return l1 == null ? l2 : l1;
		}
		ListNode head = new ListNode();
		ListNode rear = head , p1 = l1, p2 = l2;
		while(p1 != null && p2 != null) {
			if (p1.val < p2.val) {
				rear.next = p1;
				p1 = p1.next;
			}else {
				rear.next = p2;
				p2 = p2.next;
			}
			rear = rear.next;
		}
		if(p1 != null)
			rear.next = p1;
		if(p2 != null)
			rear.next = p2;
		return head.next;
	}
	
	// 0 1 2 3
	// 3 4 5 6
	public ListNode merge(ListNode[] list,int left,int right) {
		if(left == right) // 递归结束条件
			return list[left];
		if(left > right)
			return null;
		int mid = (left + right) / 2;
		ListNode left_l = merge(list, left, mid);
		ListNode right_l = merge(list, mid+1, right);
		return mergeTwoList(left_l, right_l);
	}
	
	/**
	 * K个链表中的元素升序排列
	 * @param lists
	 * @return 返回归并后的链表头
	 */
	public ListNode mergeKLists(ListNode[] lists) {
		ListNode head = null;
		for(int i = 0; i < lists.length; ++i) {
			head = mergeTwoList(head, lists[i]);
		}
		merge(lists, 0, lists.length - 1);
		return head;
    }
	
}
