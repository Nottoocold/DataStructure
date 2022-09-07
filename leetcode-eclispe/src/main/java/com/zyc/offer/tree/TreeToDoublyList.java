package com.zyc.offer.tree;

public class TreeToDoublyList {
    private Node pre = null;

    /**
     * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。
     * 中序线索化二叉树
     *
     * @param root 树根
     * @return 返回的链表头
     */
    public Node treeToDoublyList(Node root) {
        return CreateInThread(root);
    }

    //中序线索二叉树
    private Node CreateInThread(Node root) {
        Node p = null;
        if (root!=null) {
            p = In_getFirstNode(root);
            InThread(root);
            pre.right = p;
            p.left = pre;
        }
        return p;
    }

    private void InThread(Node node) {
        if (node!=null) {
            InThread(node.left);
            Thread_Visit(node);
            InThread(node.right);
        }
    }

    private void Thread_Visit(Node cur) {
        if (pre!=null) {
            pre.right = cur;
        }
        cur.left = pre;
        pre = cur;
    }

    public Node In_getFirstNode(Node root) {
        while (root.left!=null) {
            root = root.left;
        }
        return root;
    }

    public Node In_getLastNode(Node root) {
        while (root.right!=null) {
            root = root.right;
        }
        return root;
    }

    public void In_ThreadOrder(Node p) {
        pre = p;
        while (p!=null) {
            System.out.print(p.val + ", ");
            p = p.right;
            if (pre==p) {
                break;
            }
        }
    }

    public void In_ThreadOrder_Re(Node p) {
        pre = p;
        while (p!=null) {
            System.out.print(p.val + ", ");
            p = p.left;
            if (pre==p) {
                break;
            }
        }
    }


    static class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }
}
