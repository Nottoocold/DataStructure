package com.zyc.datastructure;

import com.zyc.datastructure.list.linklist.DoubleLinkList;
import com.zyc.datastructure.queue.ArrayQueue;

public class TestStruct {
    private static final TestStruct TEST = new TestStruct();

    public static void main(String[] args) {
        TEST.LinkTest();
        TEST.QueueTest();
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

}
