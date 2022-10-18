package com.zyc.datastructure.node;

/**
 * @author zyc
 * @date 2022/10/17
 */
public class HuffmanNode extends Node<Integer> implements Cloneable {
    protected HuffmanNode parent;

    protected HuffmanNode left;

    protected HuffmanNode right;

    public HuffmanNode(Integer weight) {
        this(weight, null, null, null);
    }

    public HuffmanNode(Integer weight, HuffmanNode parent, HuffmanNode left, HuffmanNode right) {
        super(weight);
        this.parent = parent;
        this.left = left;
        this.right = right;
    }

    public void setParent(HuffmanNode parent) {
        this.parent = parent;
    }

    public void setLeft(HuffmanNode left) {
        this.left = left;
    }

    public void setRight(HuffmanNode right) {
        this.right = right;
    }

    @Override
    public HuffmanNode clone() {
        try {
            return (HuffmanNode) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

}
