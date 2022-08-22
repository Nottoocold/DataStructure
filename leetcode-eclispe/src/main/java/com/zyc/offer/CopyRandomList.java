package com.zyc.offer;

import java.util.HashMap;
import java.util.Map;

public class CopyRandomList {

    public Node copyRandomListByMap(Node head) {
        if (head == null)
            return null;
        Map<Node, Node> map = new HashMap<>();// <原节点,拷贝节点>
        Node cur;
        //1.拷贝接节点
        for (cur = head; cur != null; cur = cur.next) {
            map.put(cur, new Node(cur.val));
        }
        //2.装配拷贝节点的next,random指针
        for (cur = head; cur != null; cur = cur.next) {
            Node copyNode = map.get(cur);
            copyNode.next = map.get(cur.next);
            copyNode.random = map.get(cur.random);
        }
        return map.get(head);
    }

    public Node copyRandomListByLoop(Node head) {
        if (head == null)
            return null;
        Node cur;
        //1.复制节点放在原节点身后
        for (cur = head; cur != null; cur = cur.next.next) {
            Node copyNode = new Node(cur.val);
            copyNode.next = cur.next;
            cur.next = copyNode;
        }
        //2.装配复制节点的随即指针
        for (cur = head; cur != null; cur = cur.next.next) {
            if (cur.random != null) {
                cur.next.random = cur.random.next;
            }
        }
        //3.拆分链表
        Node temp = null;
        Node ret = head.next;
        for (cur = head; cur.next != null; ) {
            temp = cur.next;
            cur.next = temp.next;
            cur = temp;
        }
        return ret;
    }
}

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
