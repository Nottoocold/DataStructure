package com.zyc.datastructure.node;

import java.util.Objects;

public abstract class Node<E> {
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
        return val.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node<?> node = (Node<?>) o;

        return Objects.equals(val, node.val);
    }

    @Override
    public int hashCode() {
        return val != null ? val.hashCode() : 0;
    }
}
