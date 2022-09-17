package com.zyc.datastructure;

import com.zyc.datastructure.list.linklist.DoubleLinkList;
import com.zyc.datastructure.queue.ArrayQueue;
import com.zyc.datastructure.tree.AVLTree;
import com.zyc.datastructure.tree.RBTree;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;

public class TestStruct {
    private static final TestStruct TEST = new TestStruct();

    public static void main(String[] args) throws InterruptedException {
        TEST.RBTreeTest();
    }

    public void LinkTest() {
        DoubleLinkList<Integer> linkList = new DoubleLinkList<>();
        for (int i = 0; i < 4095; i++) {
            linkList.addLast(i + 1);
        }
    }

    public void QueueTest() {
        ArrayQueue<Integer> queue = new ArrayQueue<>();
        for (int i = 0; i < 4095; i++) {
            queue.offer(i + 1);
        }
    }

    public void RBTreeTest() {
        RBTree<Integer, String> tree = new RBTree<>(Integer::compareTo);
        tree.put(20,"zyc1");
        tree.put(10,"zyc2");
        tree.put(5,"zyc3");
        tree.put(30,"zyc4");
        tree.put(40,"zyc5");
        tree.put(57,"zyc6");
        tree.put(3,"zyc7");
        tree.put(2,"zyc8");
        tree.put(4,"zyc8");
        tree.put(35,"zyc8");
        tree.put(25,"zyc8");
        tree.put(18,"zyc8");
        tree.put(22,"zyc8");
        tree.put(23,"zyc8");
        tree.put(24,"zyc8");
        tree.put(19,"zyc8");
    }

    public void AVLTreeTest(int count) {
        Random r = new Random();
        Queue<Integer> queue = new ArrayDeque<>();
        AVLTree<Integer> tree = new AVLTree<>(Integer::compareTo);
        for (int i = 0; i < count; i++) {
            int n = r.nextInt(count << 1);
            queue.offer(n);
            tree.add(n);
        }
        while (!queue.isEmpty()) {
            tree.containKey(queue.poll());
        }

        System.out.println("树高:" + tree.height());
    }

}
