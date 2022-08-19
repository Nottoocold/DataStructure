package com.zyc.utils;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Compare {
    class T implements Comparable<T> {
        int val;
        T next;

        public T() {
        }

        public T(int val) {
            this.val = val;
        }

        public T(int val, T next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public int compareTo(T o) {
            return this.val - o.val;
        }

        @Override
        public String toString() {
            return "val = " + val + ",next = " + next;
        }
    }

    public static void main(String[] args) {
        T t1 = new Compare().new T(1);
        T t2 = new Compare().new T(4);
        T t3 = new Compare().new T(8);
//		System.out.println(t3);
//		System.out.println(t2);
//		System.out.println(t1);

        PriorityQueue<T> queue = new PriorityQueue<>(new Comparator<T>() {

            @Override
            public int compare(T o1, T o2) {
                return o1.val - o2.val;
            }
        });
        queue.offer(t2);
        queue.offer(t3);
        queue.offer(t1);
        while (!queue.isEmpty()) {
            System.out.println(queue.peek());
            queue.poll();
        }
    }
}
