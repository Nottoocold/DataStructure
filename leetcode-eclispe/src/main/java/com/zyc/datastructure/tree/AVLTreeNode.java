package com.zyc.datastructure.tree;

import com.zyc.datastructure.node.Node;

public class AVLTreeNode<E> extends Node<E>  {

    // 记录当前子树高度
    int height;

    AVLTreeNode<E> left, right;

    public AVLTreeNode() {
        super();
    }

    public AVLTreeNode(E val, int height) {
        super(val);
        this.height = height;
    }

    public AVLTreeNode(E val, AVLTreeNode<E> left, AVLTreeNode<E> right) {
        super(val);
        this.left = left;
        this.right = right;
        this.height = 1;
    }

    @Override
    public String toString() {
        return "Node{" +
                "val=" + super.getVal() +
                ", height=" + height +
                '}';
    }
}
