//
// Created by zyc on 2022/7/28.
//
#include "AVLTree.h"

#define NULL nullptr

int max(ElemType a, ElemType b) {
    return a > b ? a : b;
}

//获取当前结点的深度
int get_balance(AVLNode *node) {
    if (node == NULL) {
        return 0;
    }
    return node->depth;
}

//返回当前平衡因子
int is_balance(AVLNode *node) {
    if (node == NULL) {
        return 0;
    } else {
        return get_balance(node->left) - get_balance(node->right);//平衡因子 = 左子树深度 - 右子树深度
    }
}

//更新当前深度
void update_depth(AVLNode *node) {
    if (node == NULL) {
        return;
    } else {
        int depth_l = get_balance(node->left); //左子树深度
        int depth_r = get_balance(node->right); //右子树深度
        node->depth = max(depth_l, depth_r) + 1;
    }
}

/**
 * 左旋函数，返回新的父节点
 * @param node
 * @return
 */
AVLNode *L_Rotate(AVLNode *node) {
    //node为离操作结点最近的失衡的结点
    AVLNode *parent = NULL, *son;
    //获取失衡结点的父节点
    parent = node->parent;
    //获取失衡结点的右孩子
    son = node->right;
    //设置son结点左孩子的父指针
    if (son->left != NULL) {
        son->left->parent = node;
    }
    //失衡结点的右孩子变更为son的左孩子
    node->right = son->left;
    //更新失衡结点的高度信息
    update_depth(node);
    //失衡结点变成son的左孩子
    son->left = node;
    //设置son的父结点为原失衡结点的父结点
    son->parent = parent;
    //如果失衡结点不是根结点，则开始更新父节点
    if (parent != NULL) {
        //如果父节点的左孩子是失衡结点，指向现在更新后的新孩子son
        if (parent->left == node) {
            parent->left = son;
        } else {
            //父节点的右孩子是失衡结点
            parent->right = son;
        }
    }
    //设置失衡结点的父亲
    node->parent = son;
    //更新son结点的高度信息
    update_depth(son);
    return son;
}

/**
 * 右旋函数，返回新的父节点
 * @param node
 * @return
 */
AVLNode *R_Rotate(AVLNode *node) {
    //node为最小不平衡子树的根节点
    AVLNode *parent, *son;
    //获取失衡结点的父节点
    parent = node->parent;
    //获取失衡结点的左孩子
    son = node->left;
    //设置son结点右孩子的父指针
    if (son->right != NULL) son->right->parent = node;
    //失衡结点的左孩子变更为son的右孩子
    node->left = son->right;
    //更新失衡结点的高度信息
    update_depth(node);
    //失衡结点变成son的右孩子
    son->right = node;
    //设置son的父结点为原失衡结点的父结点
    son->parent = parent;
    //如果失衡结点不是根结点，则开始更新父节点
    if (parent != NULL) {
        //如果父节点的左孩子是失衡结点，指向现在更新后的新孩子son
        if (parent->left == node) {
            parent->left = son;
        } else {
            parent->right = son;
        }
    }
    //设置失衡结点的父亲
    node->parent = son;
    //更新son结点的高度信息
    update_depth(son);
    return son;
}
