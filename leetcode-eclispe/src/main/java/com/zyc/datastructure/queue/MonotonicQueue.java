package com.zyc.datastructure.queue;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.Queue;

public class MonotonicQueue<E> {
    private final Queue<E> queue;
    private final Deque<E> deque;
    private final Comparator<? super E> cmp;

    public MonotonicQueue(Comparator<E> cmp) {
        queue = new ArrayDeque<>();
        deque = new ArrayDeque<>();
        this.cmp = cmp;
    }

    public void push_back(E value) {
        queue.offer(value);
        while (!deque.isEmpty() && cmp.compare(deque.peekLast(), value) < 0) {
            deque.pollLast();
        }
        deque.offerLast(value);
    }

    public E pop_front() {
        if (queue.isEmpty())
            return null;
        if (queue.peek().equals(deque.peekFirst()))
            deque.pollFirst();
        return queue.poll();
    }

    public E maxValue() {
        return deque.isEmpty() ? null : deque.peekFirst();
    }
}
