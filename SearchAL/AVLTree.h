//
// Created by zyc on 2022/7/28.
//

#ifndef AVLTREE_AVLTREE_H
#define AVLTREE_AVLTREE_H
#include <stdio.h>
#include <stdlib.h>
//宏定义平衡因子
#define LH +1
#define EH 0
#define RH -1
typedef struct AVLNode AVLNode;
typedef struct AVLNode *AVLTree;
typedef int ElemType;
typedef enum boolean{
    false,
    true
} bool;

struct AVLNode {
    ElemType data;  //数据域
    int bf;         //平衡因子
    AVLNode *lchild, *rchild;
};

void R_Rotate(AVLTree *p);//对以 p 为根结点的二叉树做右旋处理，令 p 指针指向新的树根结点
void L_Rotate(AVLTree *p);//对以 p 为根结点的二叉树做左旋处理，令 p 指针指向新的树根结点

void LeftBalance(AVLTree *T);//对以指针 T 所指向结点为根结点的二叉树作左子树的平衡处理，令指针 T 指向新的根结点
void RightBalance(AVLTree *T);//右子树的平衡处理同左子树的平衡处理完全类似
int InsertAVL(AVLTree *T, ElemType e, bool *taller);//构建AVL树

bool AVL_Search(AVLTree root, ElemType e, AVLNode **ret);
void InOrderAVL(AVLTree root);
#endif //AVLTREE_AVLTREE_H
