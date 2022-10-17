package com.zyc.datastructure.tree;

import com.zyc.datastructure.node.BSTNode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author zyc
 * @date 2022/10/16
 */
public class BSTree<E extends Comparable<E>> {

    public boolean contain(E val) {
        return get(val) != null;
    }

    public E get(E val) {
        BSTNode<E> search = search(root, val);
        return search != null ? valOf(search) : null;
    }

    public void add(E val) {
        BSTNode<E> newNode = new BSTNode<>(val);
        insert(this, newNode);
    }

    public void remove(E val) {
        BSTNode<E> node;
        if ((node = search(root, val)) != null)
            delete(node);
    }

    private void delete(BSTNode<E> node) {
        if (isLeaf(node)) {
            delete(node, IS_LEFT);
        } else if (onlyLeft(node)) {
            delete(node, HAS_LEFT);
        } else if (onlyRight(node)) {
            delete(node, HAS_RIGHT);
        } else {
            BSTNode<E> successor = successor(node);
            node.setVal(valOf(successor));
            delete(successor);
        }
    }

    private void delete(BSTNode<E> node, int mode) {
        switch (mode) {
            case (IS_LEFT) -> {
                BSTNode<E> p = parentOf(node);
                if (node == leftOf(p)) {
                    p.setLeft(null);
                } else {
                    p.setRight(null);
                }
                node.setParent(null);
            }
            case (HAS_LEFT) -> {
                BSTNode<E> p = parentOf(node);
                if (node == leftOf(p)) {
                    p.setLeft(leftOf(node));
                } else {
                    p.setRight(leftOf(node));
                }
                leftOf(node).setParent(p);
                node.setParent(null);
                node.setLeft(null);
            }
            default -> {
                BSTNode<E> p = parentOf(node);
                if (node == leftOf(p)) {
                    p.setLeft(rightOf(node));
                } else {
                    p.setRight(rightOf(node));
                }
                rightOf(node).setParent(p);
                node.setParent(null);
                node.setRight(null);
            }
        }
    }

    /**
     * @param tree 树
     * @param node 新节点
     */
    private void insert(BSTree<E> tree, BSTNode<E> node) {
        BSTNode<E> x = tree.root;
        BSTNode<E> y = x;
        int cmp;
        while (x != null) {
            cmp = valOf(node).compareTo(valOf(x));
            y = x;
            if (cmp < 0)
                x = leftOf(x);
            else
                x = rightOf(x);
        }
        node.setParent(y);
        if (y == null)
            tree.root = node;
        else {
            cmp = valOf(node).compareTo(valOf(y));
            if (cmp < 0)
                y.setLeft(node);
            else
                y.setRight(node);
        }
    }

    private BSTNode<E> search(BSTNode<E> x, E val) {
        if (x == null)
            return null;
        int cmp = valOf(x).compareTo(val);
        if (cmp < 0)
            return search(rightOf(x), val);
        else if (cmp > 0)
            return search(leftOf(x), val);
        else
            return x;
    }

    private BSTNode<E> root = null;

    public BSTree() {
    }

    private static final int IS_LEFT = 0;
    private static final int HAS_LEFT = -1;
    private static final int HAS_RIGHT = 1;

    public Collection<E> preOrder() {
        if (root == null)
            return null;
        List<E> values = new ArrayList<>();
        preOrder(root, values);
        return Collections.unmodifiableList(values);
    }

    public Collection<E> inOrder() {
        if (root == null)
            return null;
        List<E> values = new ArrayList<>();
        inOrder(root, values);
        return Collections.unmodifiableList(values);
    }

    public Collection<E> postOrder() {
        if (root == null)
            return null;
        List<E> values = new ArrayList<>();
        postOrder(root, values);
        return Collections.unmodifiableList(values);
    }

    private void preOrder(BSTNode<E> root, Collection<E> values) {
        if (root != null) {
            values.add(valOf(root));
            preOrder(leftOf(root), values);
            preOrder(rightOf(root), values);
        }
    }

    private void inOrder(BSTNode<E> root, Collection<E> values) {
        if (root != null) {
            inOrder(leftOf(root), values);
            values.add(valOf(root));
            inOrder(rightOf(root), values);
        }
    }

    private void postOrder(BSTNode<E> root, Collection<E> values) {
        if (root != null) {
            postOrder(leftOf(root), values);
            postOrder(rightOf(root), values);
            values.add(valOf(root));
        }
    }

    public E minimum() {
        BSTNode<E> tree = root;
        BSTNode<E> node = minimum(tree);
        return node != null ? valOf(node) : null;
    }

    public E maximum() {
        BSTNode<E> tree = root;
        BSTNode<E> node = maximum(tree);
        return node != null ? valOf(node) : null;
    }

    private BSTNode<E> maximum(BSTNode<E> tree) {
        if (tree == null)
            return null;
        while (rightOf(tree) != null)
            tree = rightOf(tree);
        return tree;
    }

    private BSTNode<E> minimum(BSTNode<E> tree) {
        if (tree == null)
            return null;
        while (leftOf(tree) != null)
            tree = leftOf(tree);
        return tree;
    }

    /**
     * 1. t有右孩子，则返回以右孩子为根的子树的最小值<br>
     * 2. t没有右孩子<br>
     * 2.1 t是其父节点的左孩子，则后继就是其父节点<br>
     * 2.2 t是其父节点的右孩子，则向上查询最低的父节点返回<br>
     *
     * @param t 节点非null
     * @return t的后继节点
     */
    private BSTNode<E> successor(BSTNode<E> t) {
        BSTNode<E> r = rightOf(t);
        if (r != null)
            return minimum(r);
        BSTNode<E> p = parentOf(t);
        while (p != null && t == rightOf(p)) {
            t = p;
            p = parentOf(p);
        }
        return p;
    }

    private BSTNode<E> predecessor(BSTNode<E> t) {
        BSTNode<E> l = leftOf(t);
        if (l != null)
            return maximum(l);
        BSTNode<E> p = parentOf(t);
        while (p != null && t == leftOf(p)) {
            t = p;
            p = parentOf(p);
        }
        return p;
    }

    private E valOf(BSTNode<E> node) {
        return node.getVal();
    }

    private BSTNode<E> leftOf(BSTNode<E> node) {
        return node.getLeft();
    }

    private BSTNode<E> rightOf(BSTNode<E> node) {
        return node.getRight();
    }

    private BSTNode<E> parentOf(BSTNode<E> node) {
        return node.getParent();
    }

    private boolean isLeaf(BSTNode<E> node) {
        return leftOf(node) == null && rightOf(node) == null;
    }

    private boolean onlyLeft(BSTNode<E> node) {
        return rightOf(node) == null;
    }

    private boolean onlyRight(BSTNode<E> node) {
        return leftOf(node) == null;
    }

}
