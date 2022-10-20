package com.zyc.datastructure.list.linklist;

import com.zyc.datastructure.node.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * 最好用于首尾添加删除的操作
 *
 * @param <E>
 */
public class DoubleLinkList<E> {

    private ListNode<E> first;

    private ListNode<E> last;

    private int size;

    public int size() {
        return size;
    }

    public void addLast(E val) {
        linkLast(val);
    }

    public void addFirst(E val) {
        linkFirst(val);
    }

    public E getLast() {
        if (last == null) {
            throw new NoSuchElementException("Last Pointer is null");
        }
        return last.getVal();
    }

    public E getFirst() {
        if (first == null) {
            throw new NoSuchElementException("First Pointer is null");
        }
        return first.getVal();
    }

    public void push(E val) {
        addLast(val);
    }

    public E pop() {
        return removeLast();
    }

    public E peek() {
        return getLast();
    }

    /**
     * 在第pos个位置之前添加
     *
     * @param pos 位序 从1开始
     * @param val 元素关键字
     */
    @Deprecated
    public void add(int pos, E val) {
        checkAdd(pos);
        if (pos == 1) {
            linkFirst(val);
        } else {
            linkBefore(node(pos - 1), val);
        }
    }

    @Deprecated
    public E remove(int pos) {
        checkDel(pos);
        E e;
        if (pos == 1) {
            e = first.getVal();
            unlinkFirst(first);
        } else if (pos == size) {
            e = last.getVal();
            unlinkLast(last);
        } else {
            e = unlink(node(pos - 1));
        }
        return e;
    }

    public E removeLast() {
        final ListNode<E> l = this.last;
        if (l == null) {
            throw new NoSuchElementException("Last Pointer is null");
        }
        return unlinkLast(l);
    }

    public E removeFirst() {
        final ListNode<E> f = this.first;
        if (f == null) {
            throw new NoSuchElementException("First Pointer is null");
        }
        return unlinkFirst(f);
    }

    public DoubleLinkList() {
        size = 0;
    }

    private void checkAdd(int pos) {
        if (size == 0) return;
        if (pos <= 0 || pos > size) {
            throw new IllegalArgumentException("合法范围是" + 1 + "到" + size);
        }
    }

    private void checkDel(int pos) {
        if (size == 0 || pos <= 0 || pos > size) {
            throw new IllegalArgumentException("pos 不合法");
        }
    }

    private ListNode<E> node(int index) {
        ListNode<E> p;
        if (index < (size >> 1)) {
            p = first;
            for (int i = 0; i < index; ++i) {
                p = p.next;
            }
        } else {
            p = last;
            for (int i = size - 1; i > index; --i) {
                p = p.prev;
            }
        }
        return p;
    }

    private E unlink(ListNode<E> cur) {
        E e = cur.getVal();
        ListNode<E> pre = cur.prev;
        ListNode<E> ne = cur.next;
        if (pre == null) {
            first = ne;
        } else {
            pre.next = ne;
            cur.prev = null;
        }
        if (ne == null) {
            last = pre;
        } else {
            ne.prev = pre;
            cur.next = null;
        }
        cur.setVal(null);
        size--;
        return e;
    }

    private E unlinkFirst(ListNode<E> he) {
        E e = he.getVal();
        ListNode<E> ne = he.next;
        first = ne;
        if (ne == null) {
            last = null;
        } else {
            ne.prev = null;
        }
        size--;
        return e;
    }

    private E unlinkLast(ListNode<E> lp) {
        E e = lp.getVal();
        ListNode<E> pre = lp.prev;
        last = pre;
        if (pre == null) {
            first = null;
        } else {
            pre.next = null;
        }
        size--;
        return e;
    }

    private void linkBefore(ListNode<E> cur, E val) {
        ListNode<E> pre = cur.prev;
        ListNode<E> listNode = new ListNode<>(pre, val, cur);
        if (pre == null) {
            first = listNode;
        } else {
            pre.next = listNode;
        }
        cur.prev = listNode;
        size++;
    }

    private void linkFirst(E val) {
        final ListNode<E> front = this.first;
        final ListNode<E> listNode = new ListNode<>(null, val, front);
        first = listNode;
        if (front == null) {
            last = listNode;
        } else {
            front.prev = listNode;
        }
        size++;
    }

    private void linkLast(E val) {
        ListNode<E> rear = this.last;
        ListNode<E> listNode = new ListNode<>(rear, val, null);
        last = listNode;
        if (rear == null) {
            first = listNode;
        } else {
            rear.next = listNode;
        }
        size++;
    }

    public List<E> Collections() {
        List<E> list = new ArrayList<>();
        ListNode<E> p = first;
        while (p != null) {
            list.add(p.getVal());
            p = p.next;
        }
        return Collections.unmodifiableList(list);
    }

    static class ListNode<E> extends Node<E> {
        ListNode<E> prev;
        ListNode<E> next;

        public ListNode() {
        }

        public ListNode(ListNode<E> prev, E val, ListNode<E> next) {
            super(val);
            this.prev = prev;
            this.next = next;
        }

        @Override
        public String toString() {
            return "val=" + super.getVal();
        }
    }
}
