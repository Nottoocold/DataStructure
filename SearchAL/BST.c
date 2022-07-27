#include <stdio.h>
#include <stdlib.h>
typedef int ElemType;
typedef struct BSTNode{
    ElemType key;
    struct BSTNode* left, *right;
}BSTNode,*BSTree;

/*查找,时间复杂度O(logN),*/

//1.非递归
BSTNode* BST_Search(BSTree T, ElemType key){
    while(T != NULL && T->key != key){//不是空指针且没找到key
        if(T->key > key)
            T = T->left;//左子树
        else
            T = T->right;//
    }
    return T;
}

//2.递归实现
BSTNode* BST_Search_R(BSTree T, ElemType key){
    if(T == NULL)
        return T;//NULL
    if(T->key == key)
        return T;//查找成功
    else if(T->key > key)
        return BST_Search_R(T->left,key);//递归左子树
    else
        return BST_Search_R(T->right,key);
}

//插入节点
int BST_Insert(BSTree* T, ElemType key){
    if(*T == NULL){
        *T = (BSTNode*)calloc(1,sizeof(BSTNode));
        (*T)->key = key;
        return 1;
    }
    else if((*T)->key == key)
        return 0;//重复值
    else if((*T)->key > key)
        return BST_Insert(&((*T)->left),key);
    else
        return BST_Insert(&((*T)->right),key);
}

/* 删除节点,若被删除节点存在左右子树,两种方案,设需要删除的节点为X,
 * 1.使用X节点的直接前驱替代X的位置,即X节点的左子树的中序序列下最后一个被访问的节点,然后删除前驱节点
 * 2.使用X节点的直接后继替代X的位置,即X节点的右子树的中序序列下第一个被访问的节点,然后删除后继节点
 * 其余情况,
 * 1.X只有左子树，左子树替代
 * 2.X只有右子树，右子树替代
 * 3.X时叶子节点，直接删除
 */

