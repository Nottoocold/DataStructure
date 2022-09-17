package com.zyc.datastructure.tree;

import com.zyc.datastructure.node.Node;

/**
 * 红黑树节点定义
 *
 * @param <K>
 * @param <V>
 */
public class RBNode<K, V> {

    /**
     * 数据域
     */
    Entry<K, V> entry;

    RBNode<K, V> parent;

    RBNode<K, V> left;

    RBNode<K, V> right;

    Color color;

    /**
     * 黑高 从该节点出发(不包含该节点本身)到达任一空叶节点的路径上黑节点的数目
     */
    //int bh;
    public RBNode(K key, V value) {
        this(key, value, Color.red);
    }

    public RBNode(K key, V val, Color color) {
        this(key, val, null, null, null, color);
    }

    public RBNode(Entry<K, V> entry, RBNode<K, V> parent, Color color) {
        this(entry, parent, null, null, color);
    }

    public RBNode(K key, V value, RBNode<K, V> parent, RBNode<K, V> left, RBNode<K, V> right, Color color) {
        this(new Entry<>(key, value), parent, left, right, color);
    }

    public RBNode(Entry<K, V> entry, RBNode<K, V> parent, RBNode<K, V> left, RBNode<K, V> right, Color color) {
        this.entry = entry;
        this.parent = parent;
        this.left = left;
        this.right = right;
        this.color = color;
    }

    @Override
    public String toString() {
        return entry.toString() + ", color = " + (this.color==Color.red ? "红色":"黑色");
    }

    static class Entry<K, V> extends Node<V> {
        private final K key;

        public Entry(K key) {
            this.key = key;
        }

        public Entry(final K key, V value) {
            super(value);
            this.key = key;
        }

        public K getKey() {
            return key;
        }

        @Override
        public String toString() {
            return "key=" + key;
        }
    }

    enum Color {
        red, black;
    }

}
