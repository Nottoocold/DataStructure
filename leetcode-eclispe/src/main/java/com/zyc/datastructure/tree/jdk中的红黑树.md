## jdk1.8中的hashmap中的红黑树
```java
/*
 // root为根节点  p为爷节点
static <K,V> TreeNode<K,V> rotateLeft(TreeNode<K,V> root, TreeNode<K,V> p) {
            TreeNode<K,V> r, pp, rl; // pp 代表p的父节点parent
            // 只有当p不是空且有右孩子时才进行左旋
            if (p != null && (r = p.right) != null) {
                //      p
                //       \
                //        r
                //       / \
                //      rl  rr
                if ((rl = p.right = r.left) != null) //先更新p的右指针 再判断rl是不是空指针
                    rl.parent = p; // 更新rl的父节点
                    
                if ((pp = r.parent = p.parent) == null) // 更新r的父指针 并判断p是不是根节点
                    (root = r).red = false; // 根节点一定是黑色
                    
                else if (pp.left == p) // 否则正常处理
                    pp.left = r;
                else
                    pp.right = r;
                    
                // 处理父节点和叶节点的指针
                r.left = p;
                p.parent = r; // 当前子树新的根节点是r
            }
            return root;
        }

// 右旋操作类似
static <K,V> TreeNode<K,V> rotateRight(TreeNode<K,V> root, TreeNode<K,V> p) {
            TreeNode<K,V> l, pp, lr;
            if (p != null && (l = p.left) != null) {
                //       p
                //      /
                //     l
                //    / \
                //   ll  lr
                if ((lr = p.left = l.right) != null)
                    lr.parent = p;
                    
                if ((pp = l.parent = p.parent) == null)
                    (root = l).red = false;
                    
                else if (pp.right == p)
                    pp.right = l;
                else
                    pp.left = l;
                    
                l.right = p;
                p.parent = l;
            }
            return root;
        }

// 插入操作后的平衡调整(主要判断有没有破坏不红红的特性)   root-> 根节点  x-> 已插入的新节点
static <K,V> TreeNode<K,V> balanceInsertion(TreeNode<K,V> root, TreeNode<K,V> x) {
            x.red = true; // 新节点默认都染成红色
            
            // xp x的父节点； xpp x的爷节点；xppl xppr x的叔节点 
            for (TreeNode<K,V> xp, xpp, xppl, xppr;;) {
            
                // 新节点就是根节点 说明原先是空树 则根节点一定是黑色后直接返回
                if ((xp = x.parent) == null) {
                    x.red = false;
                    return x;
                }
                
                // x的父节点是黑色(没有破坏不红红的特性) 或 x的爷节点是空(说明了当前的树一定最多只有两层)
                else if (!xp.red || (xpp = xp.parent) == null)
                    return root;
                
                // 父节点是爷节点的左孩子
                if (xp == (xppl = xpp.left)) {
                
                    // 此时 x的叔节点一定是爷的右孩子 且叔节点不是NULL且是红颜色的叔叔
                    if ((xppr = xpp.right) != null && xppr.red) {
                        // 叔父爷颜色取反 并让爷节点xpp称为新的节点 然后进入下轮循环
                        xppr.red = false;
                        xp.red = false;
                        xpp.red = true;
                        x = xpp;
                    }
                    else { // 进入到else的话说明有两种情况 1.叔节点是NULL节点(空叶节点看成黑色) 2.叔节点是黑色的
                    
                        // x节点是父节点的右孩子
                        if (x == xp.right) {
                        
                            // LR型 旋转之前先让x引用xp 方便接下来操作                            
                            root = rotateLeft(root, x = xp);  // 先左旋
                            // 此时的x是原来的xp 所以x.parent 就是原来的x
                            // 这里判断x的父指针是不是NULL的原因是 经过旋转处理后可能x成为了根节点
                            xpp = (xp = x.parent) == null ? null : xp.parent;
                        }
                        
                        // 上面的if成立的话经过左旋之后就变成了LL型 若上面的if不成立的话说明原本就是LL型
                        if (xp != null) { // 父和爷染色
                            xp.red = false;
                            if (xpp != null) {
                                xpp.red = true;
                                root = rotateRight(root, xpp); // 再右旋
                            }
                        }
                    }
                }
                
                else { // 说明父节点xp 是 爷节点xpp的右孩子 处理与上述类似
                
                    if (xppl != null && xppl.red) { // 红色叔叔xppl
                        xppl.red = false;
                        xp.red = false;
                        xpp.red = true;
                        x = xpp;
                    }
                    else { // 黑色叔叔
                    
                        if (x == xp.left) {
                            // RL型
                            root = rotateRight(root, x = xp);
                            xpp = (xp = x.parent) == null ? null : xp.parent;
                        }
                        // RR型
                        if (xp != null) {
                            xp.red = false;
                            if (xpp != null) {
                                xpp.red = true;
                                root = rotateLeft(root, xpp);
                            }
                        }
                    }
                }
            }
        }
        
*/

```