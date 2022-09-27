package com.zyc.offer.tree;

import java.util.HashMap;
import java.util.Map;

public class BuildTree {

    private int[] preorder;

    private final Map<Integer, Integer> map = new HashMap<>();

    /**
     * 根据二叉树的前序和中序遍历序列重建二叉树
     *
     * @param preorder 前序遍历序列
     * @param inorder  中序遍历序列
     * @return 返回重建之后的二叉树的层序遍历序列
     */
    public TreeNode<Integer> buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return dfs(0, 0, inorder.length - 1);
    }

    private TreeNode<Integer> dfs(int root, int left, int right) {
        if (left > right)
            return null;
        // 当前子树根节点
        TreeNode<Integer> node = new TreeNode<>(preorder[root]);
        // 获取当前根节点在中序遍历的下标
        int index = map.get(preorder[root]);
        // 构建左子树
        node.left = dfs(root + 1, left, index - 1);
        // 构建右子树
        node.right = dfs(root + index - left + 1, index + 1, right);
        // 返回当前子树根节点
        return node;
    }

}
