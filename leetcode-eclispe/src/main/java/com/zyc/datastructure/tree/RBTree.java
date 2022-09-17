package com.zyc.datastructure.tree;

import java.util.Comparator;

/**
 * 一种自平衡的二叉排序树
 * 红黑树的定义:
 * <p>左根右: 左 < 根 < 右</p>
 * <p>根叶黑: 根节点一定是黑色 叶节点(NULL节点 外部节点 失败节点)也一定是黑色</p>
 * <P>不红红: 一定不存在两个相邻(父子路径上)的红节点</P>
 * <P>黑路同: 从数的任意一个节点出发 则该节点到任意叶节点的简单路径上黑节点数都相同</P>
 * 当插入和删除都比较频繁时 可以使用红黑树
 *
 * @param <K> 以K来进行比较
 * @param <V> value的值
 */
public class RBTree<K, V> {

    public void put(K key, V val) {
        insert(root, key, val);
    }

    private void insert(RBNode<K, V> curNode, K k, V v) {
        insert0(curNode, new RBNode.Entry<>(k, v));
    }

    //
    private void insert0(RBNode<K, V> node, RBNode.Entry<K, V> entry) {
        RBNode<K, V> p = node, parent = null;
        int cmp = 0;
        while (p!=null) {
            cmp = comparator.compare(p.entry.getKey(), entry.getKey());
            if (cmp > 0) {
                parent = p;
                p = p.left;
            } else if (cmp < 0) {
                parent = p;
                p = p.right;
            } else {
                throw new IllegalArgumentException("重复的Key:" + entry.getKey());
            }
        }
        p = new RBNode<>(entry, parent, RBNode.Color.red);
        if (p.parent==null) {
            // 新节点是根节点 染黑色直接返回
            p.color = RBNode.Color.black;
            root = p;
            return;
        } else {
            if (cmp > 0) {
                parent.left = p;
            } else {
                parent.right = p;
            }
            if (p.parent.color==RBNode.Color.black) {
                // 满足红黑树的定义(不红红) 返回
                return;
            }
            // h > 2
            insertionBlance(p, parent);
        }
    }

    private void insertionBlance(RBNode<K, V> newNode, RBNode<K, V> parent) {
        // 新节点满足红黑树定义 此判断是针对第105行递归判断 防止空指针异常
        if (newNode.parent.color==RBNode.Color.black) return;
        RBNode<K, V> uncle = findUncle(newNode);
        // 黑叔
        if (uncle==null || uncle.color==RBNode.Color.black) {
            RBNode<K, V> gf = uncle==null ? parent.parent:uncle.parent;
            if (gf.left==parent) {
                if (parent.left==newNode) {
                    // LL
                    RBNode<K, V> after = rightRotate(gf);
                    updateRelation(gf, after);
                    updateColor(parent,gf);

                } else {
                    // LR
                    RBNode<K, V> after = leftThenRight(gf);
                    updateRelation(gf, after);
                    updateColor(newNode,gf);
                }

            } else {
                if (parent.right==newNode) {
                    // RR
                    RBNode<K, V> after = leftRotate(gf);
                    updateRelation(gf, after);
                    updateColor(parent,gf);
                } else {
                    // RL
                    RBNode<K, V> after = RightThenLeft(gf);
                    updateRelation(gf, after);
                    updateColor(newNode,gf);
                }
            }

        } else {
            // 红叔 叔父爷颜色取反
            updateColor(uncle, parent, parent.parent);
            if (parent.parent.parent==null) {
                // 若爷是根节点
                parent.parent.color = RBNode.Color.black;
                return;
            }
            // 否则将爷节点当作新节点
            insertionBlance(parent.parent, parent.parent.parent);
        }

    }

    // gf 原先的爷节点 after当前的爷节点 更新关系
    private void updateRelation(RBNode<K, V> gf, RBNode<K, V> after) {
        if (root==gf) {
            root = after;
        } else {
            RBNode<K, V> gff = after.parent;
            if (gff.left==gf) {
                gff.left = after;
            } else {
                gff.right = after;
            }
        }
    }

    // 能执行到此方法 说明此红黑树的高度一定大于2
    private RBNode<K, V> findUncle(RBNode<K, V> node) {
        // 是根节点
        if (node.parent==null) return null;
        RBNode<K, V> f = node.parent; // 父节点
        if (f.parent==null) return null;
        RBNode<K, V> gf = f.parent; // 爷节点
        if (gf.left==f) {
            return gf.right;
        }
        return gf.left;
    }

    private RBNode<K, V> leftRotate(RBNode<K, V> node) {
        RBNode<K, V> parent = node.parent;
        RBNode<K, V> newroot = node.right;
        RBNode<K, V> rl = newroot.left;
        newroot.left = node;
        node.right = rl;
        node.parent = newroot;
        newroot.parent = parent;
        return newroot;
    }

    private RBNode<K, V> rightRotate(RBNode<K, V> node) {
        RBNode<K, V> parent = node.parent;
        RBNode<K, V> newroot = node.left;
        RBNode<K, V> lr = newroot.right;
        newroot.right = node;
        node.left = lr;
        node.parent = newroot;
        newroot.parent = parent;
        return newroot;
    }

    private RBNode<K, V> leftThenRight(RBNode<K, V> node) {
        node.left = leftRotate(node.left);
        return rightRotate(node);
    }

    private RBNode<K, V> RightThenLeft(RBNode<K, V> node) {
        node.right = rightRotate(node.left);
        return leftRotate(node);
    }

    private void updateColor(RBNode<K, V>... node) {
        for (RBNode<K, V> no : node) {
            if (no==null) {
                continue;
            }
            RBNode.Color c = no.color;
            no.color = (c==RBNode.Color.red ? RBNode.Color.black:RBNode.Color.red);
        }
    }

    private RBNode<K, V> root;

    private Comparator<? super K> comparator = null;

    public RBTree() {

    }

    public RBTree(Comparator<K> comparator) {
        this.comparator = comparator;
    }

}
