package com.zyc.datastructure.tree;

public class AVLTreeNode<E> {
    E val;

    // 记录当前子树高度
    int height;

    AVLTreeNode<E> left, right;

    public AVLTreeNode() {
    }

    public AVLTreeNode(E val, int height) {
        this.val = val;
        this.height = height;
    }

    public AVLTreeNode(E val, AVLTreeNode<E> left, AVLTreeNode<E> right) {
        this.val = val;
        this.left = left;
        this.right = right;
        this.height = 1;
    }

    @Override
    public String toString() {
        return "Node{" +
                "val=" + val +
                ", height=" + height +
                '}';
    }
}
