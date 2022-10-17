package com.zyc.datastructure.tree;

import java.util.*;

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
        insert(this, key, val);
    }

    public boolean remove(K key) {
        RBNode<K, V> target = find(key);
        if (target == null) {
            return false;
        }
        delete(target);
        return true;
    }

    public boolean containKey(K key) {
        return find(key) != null;
    }

    public V get(K key) {
        RBNode<K, V> node = find(key);
        if (node == null) {
            return null;
        }
        return node.entry.getVal();
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public Collection<RBNode.Entry<K, V>> collections() {
        List<RBNode.Entry<K, V>> entries = new ArrayList<>();
        InOrder(entries, root);
        return Collections.unmodifiableList(entries);
    }

    private void insert(RBTree<K, V> tree, K k, V v) {
        insert0(tree.root, new RBNode.Entry<>(k, v));
    }

    //
    private void insert0(RBNode<K, V> root, RBNode.Entry<K, V> entry) {
        RBNode<K, V> p = root, parent = null;
        int cmp = 0;
        while (p != null) {
            cmp = comparator.compare(p.entry.getKey(), entry.getKey());
            if (cmp > 0) {
                parent = p;
                p = p.left;
            } else if (cmp <= 0) {
                parent = p;
                p = p.right;
            }
        }
        p = new RBNode<>(entry, parent, RBNode.Color.red);
        if (p.parent == null) {
            // 新节点是根节点 染黑色直接返回
            p.color = RBNode.Color.black;
            this.root = p;
        } else {
            if (cmp > 0) {
                parent.left = p;
            } else {
                parent.right = p;
            }
            if (p.parent.color == RBNode.Color.black) {
                // 满足红黑树的定义(不红红) 返回
                return;
            }
            // h > 2
            insertionBlance(p, parent);
        }
    }

    /**
     * @param x 待删节点
     */
    private void delete(RBNode<K, V> x) {
        // x无子节点
        if (x.left == null && x.right == null) {
            RBNode<K, V> xp = x.parent;
            // x是红色 直接删除
            if (x.color == RBNode.Color.red) {
                if (xp.left == x) {
                    xp.left = null;
                } else {
                    xp.right = null;
                }
            } else {
                // x是黑色 需要调整
                if (root == x) {
                    // x是根节点
                    root = null;
                } else {
                    // 对x进行平衡调整
                    deletionBlance(x);
                    // 调整完之后进行真正的删除
                    if (x == x.parent.left) {
                        x.parent.left = null;
                    } else {
                        x.parent.right = null;
                    }
                }
            }
            x.parent = x.left = x.right = null;
        } else if (x.left == null) {
            // x一定是黑色,右孩子染黑色代替
            RBNode<K, V> xr = x.right;
            xr.parent = x.parent;
            xr.color = RBNode.Color.black;
            if (root == x) {
                root = xr;
            } else {
                if (x.parent.left == x) {
                    x.parent.left = xr;
                } else {
                    x.parent.right = xr;
                }
            }
            x.parent = x.right = null;
        } else if (x.right == null) {
            // x一定是黑色，左孩子染黑色代替
            RBNode<K, V> xl = x.left;
            xl.parent = x.parent;
            xl.color = RBNode.Color.black;
            if (root == x) {
                root = xl;
            } else {
                if (x.parent.left == x) {
                    x.parent.left = xl;
                } else {
                    x.parent.right = xl;
                }
            }
            x.parent = x.left = null;
        } else {
            // x有两个子节点
            RBNode<K, V> xr = x.right;
            while (xr.left != null) {
                xr = xr.left;
            }
            x.entry.setKey(xr.entry.getKey());
            x.entry.setVal(xr.entry.getVal());
            delete(xr);
        }
    }

    private void deletionBlance(RBNode<K, V> x) {
        while (x != root && x.color == RBNode.Color.black) {
            RBNode<K, V> brother = findBrother(x);
            // x是其父节点的左孩子 case1
            if (x == x.parent.left) {
                // 兄弟为红色 case1.1
                if (brother.color == RBNode.Color.red) {
                    // 兄弟变成黑色，父节点变成红色
                    brother.color = RBNode.Color.black;
                    x.parent.color = RBNode.Color.red;
                    // 父节点左旋，恢复左子树的黑色高度 -> case1.2
                    RBNode<K, V> g = x.parent;
                    RBNode<K, V> after = leftRotate(x.parent);
                    delupdate(g, after);
                    // 更新兄弟
                    brother = x.parent.right;
                }

                // 兄弟为黑色 左右侄子也都是黑色 case1.2
                if ((brother.left == null || brother.left.color == RBNode.Color.black)
                        && (brother.right == null || brother.right.color == RBNode.Color.black)) {
                    // 兄弟变红色
                    brother.color = RBNode.Color.red;
                    // 向上回溯调整
                    x = x.parent;
                } else {
                    // 兄弟是黑色 左侄子为红色 右侄子为黑色 case1.3
                    if (brother.right == null || brother.right.color == RBNode.Color.black) {
                        // 左侄子变黑色 兄弟变红色 RL
                        brother.left.color = RBNode.Color.black;
                        brother.color = RBNode.Color.red;
                        // 兄弟右旋 恢复右子树黑高
                        RBNode<K, V> after = rightRotate(brother); // RL -> RR
                        delupdate(brother, after);
                        // 左侄子成为新兄弟
                        brother = x.parent.right;
                    }
                    // 兄弟是黑色 右侄子是红色 左侄子任意色 RR case1.4
                    // 兄弟变为父节点颜色
                    brother.color = x.parent.color;
                    // 父节点和右侄子都变黑色
                    x.parent.color = RBNode.Color.black;
                    brother.right.color = RBNode.Color.black;
                    RBNode<K, V> gf = x.parent;
                    // 以父节点进行左单旋 RR
                    RBNode<K, V> after = leftRotate(x.parent);
                    delupdate(gf, after);
                    x = root;
                }
            } else {
                // x是其父节点的右孩子 case2

                // 兄弟为红色 case2.1
                if (brother.color == RBNode.Color.red) {
                    // 兄弟变黑色，父亲变红色
                    brother.color = RBNode.Color.black;
                    x.parent.color = RBNode.Color.red;
                    RBNode<K, V> gf = x.parent;
                    // 父亲右旋，恢复红黑色高度 -> case2.2
                    RBNode<K, V> after = rightRotate(x.parent);
                    delupdate(gf, after);
                    // 更新兄弟为右侄子
                    brother = x.parent.left;
                }

                // 兄弟为黑色 左右侄子都为黑色 case2.2
                if ((brother.left == null || brother.left.color == RBNode.Color.black)
                        && (brother.right == null || brother.right.color == RBNode.Color.black)) {
                    // 兄弟变为红色
                    brother.color = RBNode.Color.red;
                    // x指向父节点，继续进行调整
                    x = x.parent;
                } else {
                    // 兄弟是黑色 左侄子为黑色 右侄子为红色 case2.1
                    if (brother.left == null || brother.left.color == RBNode.Color.black) {
                        // 右侄子变黑色，兄弟变红色
                        brother.right.color = RBNode.Color.black;
                        brother.color = RBNode.Color.red;
                        // 对兄弟左旋
                        RBNode<K, V> after = leftRotate(brother); // LR -> LL
                        delupdate(brother, after);
                        // 右侄子成为新的兄弟
                        brother = x.parent.left;
                    }

                    // 兄弟是黑色 右侄子为黑色，左侄子为红色 case2.2
                    // 兄弟改为父节点颜色
                    brother.color = x.parent.color;
                    // 父节点和左侄子变成黑色
                    x.parent.color = RBNode.Color.black;
                    brother.left.color = RBNode.Color.black;

                    RBNode<K, V> gf = x.parent;
                    // 右旋父节点 LL
                    RBNode<K, V> after = rightRotate(x.parent);
                    delupdate(gf, after);
                    // x指向根节点
                    x = root;
                }
            }
        }
        x.color = RBNode.Color.black;
    }

    private void insertionBlance(RBNode<K, V> newNode, RBNode<K, V> parent) {
        // 新节点满足红黑树定义 此判断是针对第105行递归判断 防止空指针异常
        if (newNode.parent.color == RBNode.Color.black) return;
        RBNode<K, V> uncle = findUncle(newNode);
        // 黑叔
        if (uncle == null || uncle.color == RBNode.Color.black) {
            RBNode<K, V> gf = uncle == null ? parent.parent : uncle.parent;
            if (gf.left == parent) {
                if (parent.left == newNode) {
                    // LL
                    RBNode<K, V> after = rightRotate(gf);
                    updateRelation(gf, after);
                    updateColor(parent, gf);

                } else {
                    // LR
                    RBNode<K, V> after = leftThenRight(gf);
                    updateRelation(gf, after);
                    updateColor(newNode, gf);
                }

            } else {
                if (parent.right == newNode) {
                    // RR
                    RBNode<K, V> after = leftRotate(gf);
                    updateRelation(gf, after);
                    updateColor(parent, gf);
                } else {
                    // RL
                    RBNode<K, V> after = RightThenLeft(gf);
                    updateRelation(gf, after);
                    updateColor(newNode, gf);
                }
            }

        } else {
            // 红叔 叔父爷颜色取反
            updateColor(uncle, parent, parent.parent);
            if (parent.parent.parent == null) {
                // 若爷是根节点
                parent.parent.color = RBNode.Color.black;
                return;
            }
            // 否则将爷节点当作新节点
            insertionBlance(parent.parent, parent.parent.parent);
        }

    }

    private void delupdate(RBNode<K, V> g, RBNode<K, V> node) {
        RBNode<K, V> gf = node.parent;
        if (gf.left == g) {
            gf.left = node;
        } else {
            gf.right = node;
        }
    }

    // gf 原先的爷节点 after当前的爷节点 更新关系
    private void updateRelation(RBNode<K, V> gf, RBNode<K, V> after) {
        if (root == gf) {
            root = after;
        } else {
            RBNode<K, V> gff = after.parent;
            if (gff.left == gf) {
                gff.left = after;
            } else {
                gff.right = after;
            }
        }
    }

    private RBNode<K, V> findBrother(RBNode<K, V> node) {
        RBNode<K, V> p = node.parent;
        if (p.left == node) {
            return p.right;
        }
        return p.left;
    }

    // 查找返回node的叔叔节点 若叔叔节点为空则返回NULL
    private RBNode<K, V> findUncle(RBNode<K, V> node) {
        // 是根节点
        if (node.parent == null) return null;
        RBNode<K, V> f = node.parent; // 父节点
        if (f.parent == null) return null;
        RBNode<K, V> gf = f.parent; // 爷节点
        if (gf.left == f) {
            return gf.right;
        }
        return gf.left;
    }

    private RBNode<K, V> find(K key) {
        RBNode<K, V> p = root;
        int cmp;
        while (p != null) {
            cmp = comparator.compare(p.entry.getKey(), key);
            if (cmp > 0) {
                p = p.left;
            } else if (cmp < 0) {
                p = p.right;
            } else {
                return p;
            }
        }
        return null;
    }

    private RBNode<K, V> leftRotate(RBNode<K, V> node) {
        RBNode<K, V> parent = node.parent;
        RBNode<K, V> newroot = node.right;
        RBNode<K, V> rl = newroot.left;
        newroot.left = node;
        node.right = rl;
        if (rl != null) {
            rl.parent = node;
        }
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
        if (lr != null) {
            lr.parent = node;
        }
        node.parent = newroot;
        newroot.parent = parent;
        return newroot;
    }

    private RBNode<K, V> leftThenRight(RBNode<K, V> node) {
        node.left = leftRotate(node.left);
        return rightRotate(node);
    }

    private RBNode<K, V> RightThenLeft(RBNode<K, V> node) {
        node.right = rightRotate(node.right);
        return leftRotate(node);
    }

    @SafeVarargs
    private void updateColor(RBNode<K, V>... node) {
        for (RBNode<K, V> no : node) {
            if (no == null) {
                continue;
            }
            RBNode.Color c = no.color;
            no.color = (c == RBNode.Color.red ? RBNode.Color.black : RBNode.Color.red);
        }
    }

    private void InOrder(Collection<RBNode.Entry<K, V>> entries, RBNode<K, V> node) {
        if (node != null) {
            InOrder(entries, node.left);
            entries.add(node.entry);
            InOrder(entries, node.right);
        }
    }

    private RBNode<K, V> root;

    private Comparator<? super K> comparator;

    public RBTree() {

    }

    public RBTree(Comparator<K> comparator) {
        this.comparator = comparator;
    }

}
