package com.zyc.offer.tree;

import com.zyc.datastructure.node.Node;

public class TreeNode<E> extends Node<E> {

    TreeNode<E> left;

    TreeNode<E> right;

    public TreeNode() {

    }

    public TreeNode(E val) {
        this(val, null, null);
    }

    public TreeNode(E val, TreeNode<E> left, TreeNode<E> right) {
        super(val);
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("val = ").append(getVal());
        if (this.left != null)
            builder.append(",").append("left val =").append(left.getVal());
        if (this.right != null)
            builder.append(",").append("right val =").append(right.getVal());
        return builder.toString();
    }
}
