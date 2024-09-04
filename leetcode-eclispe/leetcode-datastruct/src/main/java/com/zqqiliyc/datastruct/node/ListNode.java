package com.zqqiliyc.datastruct.node;

import java.util.Arrays;

/**
 * Definition for singly-linked list.
 * <p>
 * struct ListNode<V> {
 * V val;
 * ListNode<V>* next;
 * ListNode(V x) : val(x), next(NULL) {}
 * };
 *
 * @author zqqiliyc
 * @since 2024-09-04
 */
public class ListNode<V> extends Node<V> {
    private ListNode<V> next;

    public ListNode(V value) {
        this(value, null);
    }

    public ListNode(V value, ListNode<V> next) {
        super(value);
        this.next = next;
    }

    public ListNode<V> getNext() {
        return this.next;
    }

    public void setNext(ListNode<V> next) {
        this.next = next;
    }

    public boolean hasNext() {
        return this.next != null;
    }

    public V[] toArray() {
        // 链表转数组
        int size = 0;
        ListNode<V> node = this;
        while (node != null) {
            size++;
            node = node.getNext();
        }
        @SuppressWarnings("unchecked")
        V[] arr = (V[]) new Object[size];
        node = this;
        for (int i = 0; i < size; i++) {
            arr[i] = node.getVal();
            node = node.getNext();
        }
        return arr;
    }

    public static <V> ListNode<V> fromArray(V[] arr) {
        // 数组转链表
        if (arr == null || arr.length == 0) {
            return null;
        }
        ListNode<V> head = new ListNode<>(arr[0]);
        ListNode<V> node = head;
        for (int i = 1; i < arr.length; i++) {
            node.setNext(new ListNode<>(arr[i]));
            node = node.getNext();
        }
        return head;
    }

    @Override
    public String toString() {
        V[] array = toArray();
        return Arrays.toString(array);
    }
}
