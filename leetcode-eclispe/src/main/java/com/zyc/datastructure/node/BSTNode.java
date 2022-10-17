package com.zyc.datastructure.node;

/**
 * @author zyc
 * @date 2022/10/16
 */
public class BSTNode<E extends Comparable<E>> extends Node<E> {

    private BSTNode<E> left;

    private BSTNode<E> right;

    private BSTNode<E> parent;

    public BSTNode() {
        super();
    }

    public BSTNode(E val) {
        this(val, null);
    }

    public BSTNode(E val, BSTNode<E> parent) {
        this(val, parent, null, null);
    }

    public BSTNode(E val, BSTNode<E> left, BSTNode<E> right) {
        this(val, null, left, right);
    }

    public BSTNode(E val, BSTNode<E> parent, BSTNode<E> left, BSTNode<E> right) {
        super(val);
        this.parent = parent;
        this.left = left;
        this.right = right;
    }

    public BSTNode<E> getLeft() {
        return left;
    }

    public void setLeft(BSTNode<E> left) {
        this.left = left;
    }

    public BSTNode<E> getRight() {
        return right;
    }

    public void setRight(BSTNode<E> right) {
        this.right = right;
    }

    public BSTNode<E> getParent() {
        return parent;
    }

    public void setParent(BSTNode<E> parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "" + getVal();
    }

}
