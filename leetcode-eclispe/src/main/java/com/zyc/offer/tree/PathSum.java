package com.zyc.offer.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PathSum {

    /**
     * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
     *
     * @param root   树根节点
     * @param target 目标和
     * @return 所有可能的路径组合
     */
    public List<List<Integer>> pathSum(TreeNode<Integer> root, int target) {
        if (root==null || root.val==target) {
            return new ArrayList<>();
        }
        LinkedList<List<Integer>> listList = new LinkedList<>();
        LinkedList<Integer> list = new LinkedList<>();
        DFS(root, target, listList, list);
        return listList;
    }

    private void DFS(TreeNode<Integer> node, int target, LinkedList<List<Integer>> list, LinkedList<Integer> work) {
        if (node==null) {
            return;
        }
        work.add(node.val);
        target -= node.val;
        // 没有剩余且node是根节点
        if (target==0 && node.left==null && node.right==null) {
            list.add(new LinkedList<>(work));
        }
        DFS(node.left, target, list, work);
        DFS(node.right, target, list, work);
        work.removeLast();
    }
}
