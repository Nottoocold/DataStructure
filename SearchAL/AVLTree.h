//
// Created by zyc on 2022/7/28.
//

#ifndef AVLTREE_AVLTREE_H
#define AVLTREE_AVLTREE_H

typedef struct AVLNode AVLNode;
typedef struct AVLNode *AVLTree;
typedef int ElemType;

struct AVLNode {
    ElemType data;  //数据域
    int depth;  //当前节点所在高度
    AVLNode *parent;    //当前节点的双亲节点
    AVLNode *right;
    AVLNode *left;

    AVLNode(ElemType data) {
        this->data = data;
        depth = 0;
        left = right = nullptr;
        parent = nullptr;
    }
};

//左旋函数
AVLNode *L_Rotate(AVLNode *node);

//右旋函数
AVLNode *R_Rotate(AVLNode *node);

#endif //AVLTREE_AVLTREE_H
