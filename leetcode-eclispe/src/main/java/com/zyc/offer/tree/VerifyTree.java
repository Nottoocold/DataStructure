package com.zyc.offer.tree;

/**
 * @author zyc
 * @date 2022/10/2
 */
public class VerifyTree {

    /**
     * 验证给定的后根遍历序列是不是某二叉搜索树的遍历结果
     *
     * @param postOrder 后根遍历序列
     * @return 如果时返回 {@code true},否则返回 {@code false}
     */
    public boolean verifyPostOrder(int[] postOrder) {
        return dfs(postOrder, 0, postOrder.length - 1);
    }

    private boolean dfs(int[] post, int i, int j) {
        if (i >= j)
            return true;
        int p = i;
        // 寻找第一个大于根节点的元素(右子树的底部节点)
        while (post[p] < post[j])
            ++p;
        // 记录该节点索引
        int m = p;
        // 若序列不合法 则p不会指向根节点
        while (post[p] > post[j])
            ++p;
        return p == j // 当是合法序列时 p = j
                && dfs(post, i, m - 1) // 左子树
                && dfs(post, m, j - 1); // 右子树
    }
}
