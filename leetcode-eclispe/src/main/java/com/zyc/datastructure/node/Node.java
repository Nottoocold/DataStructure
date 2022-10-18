package com.zyc.datastructure.node;

public class Node<E> {
    protected E val;

    public Node() {
    }

    public Node(E val) {
        this.val = val;
    }

    public E getVal() {
        return val;
    }

    public void setVal(E val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "val=" + val;
    }
}
