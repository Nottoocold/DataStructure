package com.zyc.datastructure.list.linklist;

import com.zyc.datastructure.node.Node;

import java.util.*;
import java.util.function.Consumer;

/**
 * @author zyc
 * @date 2022/10/19
 */
public class LinkList<E> {
    private LinkNode<E> head;
    private LinkNode<E> tail;
    private int size;

    public int size() {
        return size;
    }

    public E remove(E val) {
        LinkNode<E> node = find(val);
        if (node != null) {
            if (node == head) {
                return removeFirst();
            } else if (node == tail) {
                return removeLast();
            } else {
                LinkNode<E> pre = precursor(val);
                pre.next = node.next;
                --size;
                return node.getVal();
            }
        }
        return null;
    }

    public boolean contain(E val) {
        return find(val) != null;
    }

    private LinkNode<E> find(E val) {
        if (!isEmpty()) {
            LinkNode<E> p = head;
            while (p != null) {
                if (equals(p, val))
                    return p;
                p = p.next;
            }
        }
        return null;
    }

    private LinkNode<E> nodeAt(int index) {
        checkIndex(index);
        LinkNode<E> p = head;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("size is " + size + " but index" + index + " is out of range");
    }

    private boolean equals(LinkNode<E> p, E v2) {
        return p.getVal().equals(v2);
    }

    public Set<E> values() {
        if (!isEmpty()) {
            Set<E> values = new LinkedHashSet<>();
            LinkNode<E> p = head;
            while (p != null) {
                values.add(p.getVal());
                p = p.next;
            }
            return Collections.unmodifiableSet(values);
        }
        return Collections.emptySet();
    }

    public void addAll(List<E> values) {
        values.forEach(this::addLast);
    }

    public E removeLast() {
        E value = null;
        if (!isEmpty()) {
            value = tail.getVal();
            LinkNode<E> pre = precursor(value);
            if (pre == head && pre == tail) {
                head = tail = null;
            } else {
                pre.next = null;
                tail = pre;
            }
            --size;
        }
        return value;
    }

    public E removeFirst() {
        E value = null;
        if (!isEmpty()) {
            value = head.getVal();
            if (head.next == null)
                head = tail = null;
            else
                head = head.next;
            --size;
        }
        return value;
    }

    public void addLastIfAbsent(E val) {
        if (find(val) == null)
            addLast(val);
    }

    public void addFirstAbsent(E val) {
        if (find(val) == null)
            addFirst(val);
    }

    public void addLast(E val) {
        checkVal(val);
        LinkNode<E> node = createNode(val);
        if (isEmpty()) {
            head = tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        ++size;
    }

    public void addFirst(E val) {
        checkVal(val);
        LinkNode<E> node = createNode(val);
        if (isEmpty()) {
            head = tail = node;
        } else {
            node.next = head;
            head = node;
        }
        ++size;
    }

    private LinkNode<E> precursor(E val) {
        LinkNode<E> p = head;
        if (equals(p, val))
            return p;
        while (p != null && p.next != null) {
            if (equals(p.next, val)) {
                break;
            }
            p = p.next;
        }
        return p;
    }

    private void checkVal(E val) {
        if (val == null) {
            throw new IllegalArgumentException("val must not be null");
        }
    }

    Iterator<E> iterator(int index) {
        return new singleItr(index);
    }

    private LinkNode<E> createNode(E val) {
        return new LinkNode<>(val);
    }

    public boolean isEmpty() {
        return head == null && tail == null;
    }

    @Override
    public String toString() {
        return "size=" + size;
    }

    public LinkList() {
        size = 0;
    }

    public E getFirst() {
        return head == null ? null : head.getVal();
    }

    public E nextVal(E val) {
        LinkNode<E> node = nodeAt(indexOf(val));
        return node == null ? null : (node.next == null) ? null : node.next.getVal();
    }

    public int indexOf(E val) {
        LinkNode<E> p = head;
        int index = 0;
        for (int i = 0; i < size; i++) {
            if (equals(p, val)) {
                index = i;
                break;
            }
            p = p.next;
        }
        return index;
    }

    static class LinkNode<E> extends Node<E> {
        private LinkNode<E> next;

        public LinkNode(E val) {
            this(val, null);
        }

        public LinkNode(E val, LinkNode<E> next) {
            super(val);
            this.next = next;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof LinkNode<?>)) return false;
            return super.equals(o);
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }
    }

    private class singleItr implements Iterator<E> {
        private LinkNode<E> cur;
        private LinkNode<E> lastReturn;
        private int nextIdx;

        singleItr() {
        }

        singleItr(int index) {
            cur = (index == size) ? null : nodeAt(index);
            nextIdx = index;
        }

        @Override
        public boolean hasNext() {
            return nextIdx < size;
        }

        @Override
        public E next() {
            if (!hasNext())
                throw new NoSuchElementException();
            lastReturn = cur;
            cur = cur.next;
            nextIdx++;
            return lastReturn.getVal();
        }

        @Override
        public void remove() {
            Iterator.super.remove();
        }

        @Override
        public void forEachRemaining(Consumer<? super E> action) {
            Iterator.super.forEachRemaining(action);
        }
    }
}
