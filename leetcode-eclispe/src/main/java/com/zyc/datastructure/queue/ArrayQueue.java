package com.zyc.datastructure.queue;

import java.util.Arrays;

public class ArrayQueue<E> {

    private static final int DEFAULT = 1 << 4;

    private static final int MAXSIZE = 1 << 10;

    private Object[] queue;

    /*
        指向队头元素
     */
    private int head;

    /*
        指向队尾元素的下一个位置，即将要插入的位置
     */
    private int tail;

    public ArrayQueue() {
        this(DEFAULT);
    }

    public ArrayQueue(int size) {
        queue = new Object[size < 1 ? 1 : Math.min(size, MAXSIZE)];
        this.head = 0;
        this.tail = 0;
    }

    private void grow() {
        int old = queue.length;
        if (old >= MAXSIZE) {
            throw new IllegalStateException("Sorry, deque too big");
        }
        int newCap = queue.length << 1;
        final Object[] es = queue = Arrays.copyOf(queue, newCap);
        if (head == tail) {
            System.arraycopy(es, head, es, old, old - head);

            System.arraycopy(es, 0, es, old * 2 - head, head);

            head = old;
            tail = 0;
        }
    }

    private static int increase(int index, int len) {
        if (++index >= len) index = 0;
        return index;
    }

    public void offer(E val) {
        queue[tail] = val;
        tail = increase(tail, queue.length);
        if (head == tail)
            grow();
    }

    public E poll() {
        if (isEmpty()) {
            throw new UnsupportedOperationException("队列为空");
        }
        @SuppressWarnings("unchecked")
        E val = (E) queue[head];
        head = increase(head, queue.length);
        return val;
    }

    public boolean isEmpty() {
        return head == tail;
    }

    public int size() {
        int len = queue.length;
        int i = head;
        int j = tail;
        if ((j -= i) <= 0) {
            j += len;
        }
        return j;
    }

    public void print() {
        int x = head;
        while (x != tail) {
            System.out.print(queue[x] + "\t");
            x = increase(x, queue.length);
        }
        System.out.println();
    }

}
