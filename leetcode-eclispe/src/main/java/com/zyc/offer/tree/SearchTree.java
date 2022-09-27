package com.zyc.offer.tree;

import java.util.*;

public class SearchTree {
    int level = 0;

    /**
     * 从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。
     *
     * @param root 树根
     * @return 层序遍历序列数组
     */
    public <T> List<T> levelOrder(TreeNode<T> root) {
        if (root == null) return null;
        Deque<TreeNode<T>> queue = new LinkedList<>();
        queue.offerLast(root);
        List<T> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode<T> t = queue.pollFirst();
            list.add(t.getVal());
            if (t.left != null)
                queue.offerLast(t.left);
            if (t.right != null)
                queue.offerLast(t.right);
        }
        return list;
    }

    /**
     * 从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。
     *
     * @param root 树根
     * @return 层序遍历序列集合
     */
    public <T> List<List<T>> levelOrderByRow(TreeNode<T> root) {
        if (root == null) return new ArrayList<>();
        Deque<TreeNode<T>> queue = new LinkedList<>();
        List<List<T>> ret = new ArrayList<>();
        queue.offerLast(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<T> list = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                TreeNode<T> T = queue.pollFirst();
                assert T != null;
                list.add(T.getVal());
                if (T.left != null)
                    queue.offerLast(T.left);
                if (T.right != null)
                    queue.offerLast(T.right);
            }
            ret.add(list);
        }
        return ret;
    }

    /**
     * 请实现一个函数按照之字形顺序打印二叉树，<br>
     * 即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。
     *
     * @param root 树根
     * @return 结果集合
     */
    public List<List<Integer>> levelOrderBy(TreeNode<Integer> root) {
        if (root == null) return new ArrayList<>();
        Deque<TreeNode<Integer>> queue = new LinkedList<>();
        List<List<Integer>> ret = new ArrayList<>();
        queue.offerLast(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>(size);
            level++;
            if (((level & 1) == 1)) {//奇数行
                for (int i = 0; i < size; i++) {
                    TreeNode<Integer> T = queue.pollFirst();
                    assert T != null;
                    list.add(T.getVal());
                    if (T.left != null)
                        queue.offerLast(T.left);
                    if (T.right != null)
                        queue.offerLast(T.right);
                }
                ret.add(list);
            } else {
                Deque<TreeNode<Integer>> stack = new ArrayDeque<>(size << 1);
                for (int i = 0; i < size; i++) {
                    TreeNode<Integer> T = queue.pollLast();
                    assert T != null;
                    list.add(T.getVal());
                    if (T.right != null)
                        stack.push(T.right);
                    if (T.left != null)
                        stack.push(T.left);
                }
                while (!stack.isEmpty()) {
                    queue.offerLast(stack.pop());
                }
                ret.add(list);
            }
        }
        return ret;
    }

    public <T> TreeNode<T> buildTreeByLevel(TreeNode<T> root, T[] arr) {
        Deque<TreeNode<T>> queue = new LinkedList<>();
        for (T t : arr) {
            TreeNode<T> node = new TreeNode<>(t);
            if (root == null) {
                root = node;
                queue.offerLast(node);
            } else {
                TreeNode<T> cur = queue.getFirst();
                if (cur.left == null) {
                    cur.left = node;
                    queue.offerLast(node);
                } else if (cur.right == null) {
                    cur.right = node;
                    queue.pollFirst();
                    queue.offerLast(node);
                }
            }
        }
        return root;
    }

    public <T> int maxDepth(TreeNode<T> root) {
        if (root != null) {
            int left = maxDepth(root.left);
            int right = maxDepth(root.right);
            return Math.max(left, right) + 1;
        }
        return 0;
    }

    // 先序遍历实现
    public <E> boolean isBalanced(TreeNode<E> root) {
        if (root == null) {
            return true;
        }
        // 当前节点的左右子树高度差的绝对值小于等于1 且 左右子树也都平衡
        return Math.abs(maxDepth(root.left) - maxDepth(root.right)) <= 1
                && isBalanced(root.left) && isBalanced(root.right);
    }

    // 后序遍历实现
    public <E> boolean isBalanced0(TreeNode<E> root) {
        return order(root) != -1;
    }

    private <E> int order(TreeNode<E> root) {
        if (root == null) {
            return 0;
        }
        int left = order(root.left);
        if (left == -1) return -1;
        int right = order(root.right);
        if (right == -1) return -1;
        return Math.abs(left - right) <= 1 ? Math.max(left, right) + 1 : -1;
    }

}
