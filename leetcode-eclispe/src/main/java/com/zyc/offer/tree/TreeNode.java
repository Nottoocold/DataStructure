package com.zyc.offer.tree;

public class TreeNode<E> {
    E val;
    TreeNode<E> left;
    TreeNode<E> right;

    public TreeNode() {

    }

    public TreeNode(E val) {
        this.val = val;
    }

    public TreeNode(E val, TreeNode<E> left, TreeNode<E> right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
