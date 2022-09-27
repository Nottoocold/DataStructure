package com.zyc.offer.tree;

/**
 * @author ZYC
 */
public class KthLargest {

    private int ret = 0;
    private int count = 0;

    /**
     * 二叉搜索树第k个大的节点
     *
     * @param root 树根
     * @param k    倒数第k个节点
     * @return 返回倒数第k个结果
     */
    public int kthLargest(TreeNode<Integer> root, int k) {
        inOrderTree(root, k);
        return ret;
    }

    private void inOrderTree(TreeNode<Integer> treeNode, int k) {
        if (treeNode == null) {
            return;
        }

        inOrderTree(treeNode.right, k);

        count++;
        if (count == k) {
            ret = treeNode.getVal();
            return;
        }

        inOrderTree(treeNode.left, k);
    }

    public TreeNode<Integer> binInsert(TreeNode<Integer> treeNode, int val) {
        if (treeNode == null) {
            return new TreeNode<>(val);
        } else if (treeNode.getVal() == val) {
            return treeNode;
        } else if (treeNode.getVal() > val) {
            treeNode.left = binInsert(treeNode.left, val);
            return treeNode;
        } else {
            treeNode.right = binInsert(treeNode.right, val);
            return treeNode;
        }
    }

}
