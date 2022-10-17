package com.zyc.datastructure.tree;

import java.util.*;

/**
 * 平衡二叉树 它的查找效率与它的高度密切相关
 * 对于有N个节点的二叉树 它的高度最高是 logN 所以平均查找长度是O(logN)
 *
 * @param <E>
 */
public class AVLTree<E extends Comparable<E>> {

    private AVLTreeNode<E> root;

    private Comparator<? super E> comparator;

    public AVLTree() {

    }

    public AVLTree(Comparator<? super E> comparator) {
        this.comparator = comparator;
    }

    public int height() {
        return getHeight(this.root);
    }

    public void add(E val) {
        this.root = insert(this.root, val);
    }

    public boolean containKey(E key) {
        return find(key) != null;
    }

    public Collection<E> collections() {
        List<E> values = new ArrayList<>();
        InOrder(values, root);
        return Collections.unmodifiableList(values);
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public E find(E key) {
        AVLTreeNode<E> node = find0(key);
        return node != null ? (node.getVal() == key ? node.getVal() : null) : null;
    }

    public boolean delete(E key) {
        if (this.root == null) {
            System.out.println("Tree is null..");
            return false;
        }
        AVLTreeNode<E> parent = null;
        AVLTreeNode<E> delNode = this.root;
        Deque<AVLTreeNode<E>> stack = new ArrayDeque<>();
        // 迭代寻找待删除的节点的引用和它的双亲节点 并将迭代路径压栈
        while (delNode != null && comparator.compare(delNode.getVal(), key) != 0) {
            if (comparator.compare(delNode.getVal(), key) > 0) {
                parent = delNode;
                stack.push(delNode);
                delNode = delNode.left;
            } else {
                parent = delNode;
                stack.push(delNode);
                delNode = delNode.right;
            }
        }
        if (delNode == null) {
            System.out.println("Tree is not containKey " + key);
            return false;
        }
        delete0(delNode, parent, stack);
        return true;
    }

    /**
     * @param node   待删节点
     * @param parent 待删节点的父节点
     * @param stack  根节点到parent的路径
     */
    private void delete0(AVLTreeNode<E> node, AVLTreeNode<E> parent, Deque<AVLTreeNode<E>> stack) {
        // 左子树为空 用右子树替代
        if (node.left == null) {
            if (parent.left == node) {
                parent.left = node.right;
            } else {
                parent.right = node.right;
            }
            reBalance(stack);
        } else if (node.right == null) { // 右子树为空 用左子树替代
            if (parent.left == node) {
                parent.left = node.left;
            } else {
                parent.right = node.left;
            }
            reBalance(stack);
        } else {
            // 左右子树都不为空 用直接前驱替代 或直接后继替代
            // 这里用直接前驱
            AVLTreeNode<E> pre = node;
            AVLTreeNode<E> cur = node.left;
            while (cur.right != null) {
                pre = cur;
                cur = cur.right;
            }
            node.setVal(cur.getVal());
            if (pre != node) { // node节点有前驱
                pre.right = cur.left;
            } else { // node节点没有前驱 因为node节点的左孩子没有右孩子
                pre.left = cur.left;
            }
            // 当前子树的高度可能会改变
            stack.push(node);
            reBalance(stack);
        }

    }

    // 如果没找到返回父节点的引用
    private AVLTreeNode<E> find0(E key) {
        AVLTreeNode<E> cur = this.root;
        while (cur != null) {
            if (comparator.compare(cur.getVal(), key) > 0) {
                if (cur.left == null) {
                    return cur;
                }
                cur = cur.left;
            } else if (comparator.compare(cur.getVal(), key) < 0) {
                if (cur.right == null) {
                    return cur;
                }
                cur = cur.right;
            } else {
                return cur;
            }
        }
        return null;
    }

    private AVLTreeNode<E> insert(AVLTreeNode<E> root, E val) {
        if (root == null) {
            root = new AVLTreeNode<>(val, 1);
        } else if (comparator.compare(root.getVal(), val) > 0) {
            root.left = insert(root.left, val);
            // 判断是否不平衡
            if (getHeight(root.left) - getHeight(root.right) > 1) {
                if (comparator.compare(root.left.getVal(), val) > 0) {
                    // LL
                    root = RightRotate(root);
                } else {
                    // LR
                    root = leftRotateThenRight(root);
                }
            }
        } else if (comparator.compare(root.getVal(), val) < 0) {
            root.right = insert(root.right, val);
            // 判断是否不平衡
            if (getHeight(root.left) - getHeight(root.right) < -1) {
                if (comparator.compare(root.right.getVal(), val) < 0) {
                    // RR
                    root = LeftRotate(root);
                } else {
                    // RL
                    root = rightRotateThenLeft(root);
                }
            }
        }
        // 更新高度
        updateHeight(root);
        return root;
    }


    /**
     * 右旋
     * LL型(左孩子的左子树中插入新节点)导致不平衡
     *
     * @param node 最小不平衡子树的根节点
     * @return 调整后新的根节点
     */
    private AVLTreeNode<E> RightRotate(AVLTreeNode<E> node) {
        AVLTreeNode<E> newRoot = node.left;
        node.left = newRoot.right;
        newRoot.right = node;
        updateHeight(node);
        updateHeight(newRoot);
        return newRoot;
    }

    /**
     * 左旋
     * RR型(右孩子的右子树中插入新节点)导致不平衡
     *
     * @param node 最小不平衡子树的根节点
     * @return 调整后新的根节点
     */
    private AVLTreeNode<E> LeftRotate(AVLTreeNode<E> node) {
        AVLTreeNode<E> newRoot = node.right;
        node.right = newRoot.left;
        newRoot.left = node;
        updateHeight(node);
        updateHeight(newRoot);
        return newRoot;
    }

    /**
     * 先左旋再右旋
     * LR型(左孩子的右子树中插入新节点)导致不平衡
     *
     * @param node 最小不平衡子树的根节点
     * @return 调整后新的根节点
     */
    private AVLTreeNode<E> leftRotateThenRight(AVLTreeNode<E> node) {
        node.left = LeftRotate(node.left);
        return RightRotate(node);
    }

    /**
     * 先右旋再左旋
     * RL型(右孩子的左子树中插入新节点)导致不平衡
     *
     * @param node 最小不平衡子树的根节点
     * @return 调整后新的根节点
     */
    private AVLTreeNode<E> rightRotateThenLeft(AVLTreeNode<E> node) {
        node.right = RightRotate(node.right);
        return LeftRotate(node);
    }

    /**
     * 迭代依次判断从根节点到delNode节点的路径上的节点是否平衡
     *
     * @param stack 遍历路径
     */
    private void reBalance(Deque<AVLTreeNode<E>> stack) {
        while (!stack.isEmpty()) {
            AVLTreeNode<E> Z = stack.pop();
            updateHeight(Z);
            int lh = getHeight(Z.left);
            int rh = getHeight(Z.right);
            // 最小不平衡子树根节点 (Z)
            if (Math.abs(lh - rh) > 1) {
                // 找出当前根节点(Z)的最高的孩子节点 (Y)
                AVLTreeNode<E> Y = lh > rh ? Z.left : Z.right;
                // 找出(Y)的最高的孩子节点 (X)
                AVLTreeNode<E> X =
                        getHeight(Y.left) > getHeight(Y.right) ? Y.left : Y.right;

                AVLTreeNode<E> peekNode = stack.peek();

                if (Z.left == Y) {
                    if (Y.left == X) {
                        // Y是Z的左孩子 X是Y的左孩子 LL
                        if (peekNode != null) {
                            peekNode.left = RightRotate(Z);
                        } else {
                            this.root = RightRotate(Z);
                        }
                    } else { // Y.right == X
                        // Y是Z的左孩子 X是Y的右孩子 LR
                        if (peekNode != null) {
                            peekNode.left = leftRotateThenRight(Z);
                        } else {
                            this.root = leftRotateThenRight(Z);
                        }
                    }

                } else { // Z.right == Y

                    if (Y.right == X) {
                        // Y是Z的右孩子 X是Y的右孩子 RR
                        if (peekNode != null) {
                            peekNode.right = LeftRotate(Z);
                        } else {
                            this.root = LeftRotate(Z);
                        }
                    } else { // Y.left == X
                        // Y是Z的右孩子 X是Y的左孩子 RL
                        if (peekNode != null) {
                            peekNode.right = rightRotateThenLeft(Z);
                        } else {
                            this.root = rightRotateThenLeft(Z);
                        }
                    }

                }

            }
        }
    }

    private void InOrder(Collection<E> values, AVLTreeNode<E> node) {
        if (node != null) {
            InOrder(values, node.left);
            values.add(node.getVal());
            InOrder(values, node.right);
        }
    }

    /**
     * 更新当前节点树高
     *
     * @param node 待更新节点
     */
    private void updateHeight(AVLTreeNode<E> node) {
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
    }

    private int getHeight(AVLTreeNode<E> node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

}
