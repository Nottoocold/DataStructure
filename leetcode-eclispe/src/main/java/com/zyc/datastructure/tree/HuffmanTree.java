package com.zyc.datastructure.tree;

import com.zyc.datastructure.node.HuffmanNode;

/**
 * @author zyc
 * @date 2022/10/17
 */
public class HuffmanTree {
    private final HuffmanNode root;
    private static int[] w;

    public static HuffmanTree create(int[] weight) {
        HuffmanNode p = null;
        MinHeap<HuffmanNode> heap = new MinHeap<>(weight);
        for (int i = 0; i < weight.length - 1; i++) {
            HuffmanNode left = heap.poll();
            HuffmanNode right = heap.poll();
            p = new HuffmanNode(left.getVal() + right.getVal(), null, left, right);
            left.setParent(p);
            right.setParent(p);
            heap.add(p);
        }
        heap.destroy();
        return new HuffmanTree(p);
    }

    private HuffmanTree(HuffmanNode root) {
        this.root = root;
    }

    public HuffmanNode getRoot() {
        return root;
    }
}
