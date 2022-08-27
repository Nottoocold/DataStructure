package com.zyc.offer.tree;

import java.util.LinkedList;
import java.util.Queue;

public class SearchTreePlus {

    /**
     * 输入一个二叉树，该函数输出它的镜像。
     *
     * @param root 树根
     * @param <T>  数中元素类型
     * @return 镜像二叉树的根
     */
    public <T> TreeNode<T> mirrorTree(TreeNode<T> root) {
        if (root != null) {
            DFS_mirrorTree(root);
        }
        return root;
    }

    /**
     * 实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。
     *
     * @param root 树根
     * @param <T>  树元素类型
     * @return 若是对称二叉树则返回true, 否则false
     */
    public <T> boolean isSymmetric(TreeNode<T> root) {
        return DFS_isSymmetric(root, root);
    }

    /**
     * 输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
     * <br>
     * B是A的子结构， 即 A中有出现和B相同的结构和节点值。
     *
     * @param A   树A
     * @param B   树B
     * @param <T> 树中元素类型
     * @return 如果是B是A的子树返回true
     */
    public <T> boolean isSubStructure(TreeNode<T> A, TreeNode<T> B) {
        if (B == null)
            return false;
        return Inorder(A, B);
    }

    /*
     * 先序遍历树A,依次寻找A中与B的根元素相等的节点
     */
    private <T> boolean Inorder(TreeNode<T> A, TreeNode<T> B) {
        if (A != null) {
            if (A.val == B.val) { // 树A和树B的根节点相等
                if (compare(A, B)) // 比较其结构
                    return true;
            }
            return Inorder(A.left, B) || Inorder(A.right, B);
        }
        return false;
    }

    // 标记两树
    private <T> boolean compare(TreeNode<T> A, TreeNode<T> B) {
        if (B == null) {
            return true;
        }
        if (A == null) {
            return false;
        }
        return A.val == B.val && compare(A.left, B.left) && compare(A.right, B.right);
    }

    private <T> boolean DFS_isSymmetric(TreeNode<T> p, TreeNode<T> q) {
        if (p == null && q == null)
            return true;
        if (p == null || q == null)
            return false;
        return p.val == q.val && DFS_isSymmetric(p.left, q.right) && DFS_isSymmetric(p.right, q.left);
    }

    private <T> boolean BFS_isSymmetric(TreeNode<T> p, TreeNode<T> q) {
        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.offer(p);
        queue.offer(q);
        while (!queue.isEmpty()) {
            p = queue.poll();
            q = queue.poll();
            if (p == null && q == null)
                return true;
            if ((p == null || q == null) || p.val != q.val)
                return false;
            queue.offer(p.left);
            queue.offer(q.right);
            queue.offer(p.right);
            queue.offer(q.left);
        }
        return true;
    }

    //DFS,后根遍历实现二叉树的镜像
    private <T> void DFS_mirrorTree(TreeNode<T> root) {
        if (root != null) {
            DFS_mirrorTree(root.left);
            DFS_mirrorTree(root.right);
            TreeNode<T> p = root.left;
            root.left = root.right;
            root.right = p;
        }
    }
}
