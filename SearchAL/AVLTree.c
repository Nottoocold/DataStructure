//
// Created by zyc on 2022/7/28.
//
#include "AVLTree.h"

/**
 * 以p为根节点的二叉树作右旋处理
 * @param p 用p返回新的根节点
 */
void R_Rotate(AVLTree *p) {
    AVLNode *pl = (*p)->lchild;
    (*p)->lchild = pl->rchild;
    pl->rchild = *p;
    *p = pl;//新的根节点
}

/**
 * 以p为根节点的二叉树作左旋处理
 * @param p 用p返回新的根节点
 */
void L_Rotate(AVLTree *p) {
    AVLNode *pr = (*p)->rchild;
    (*p)->rchild = pr->lchild;
    pr->lchild = *p;
    *p = pr;
}

/**
 * 以T指向的节点为根节点的二叉树作左子树的平衡处理，令指针 T 指向新的根结点
 * @param T
 */
void LeftBalance(AVLTree *T) {
    AVLTree lc, rd;
    lc = (*T)->lchild;
    /**
        查看以 T 的左子树为根结点的子树，失去平衡的原因，如果 bf 值为 1 ，则说明添加在左子树为根结点的左子树中(LL)，需要对其进行右旋处理；
        反之，如果 bf 值为 -1(LR)，说明添加在以左子树为根结点的右子树中，需要进行双向先左旋后右旋的处理
                T
              /
             lc
            / \
         LCL  LCR
     */
    switch (lc->bf) {
        case LH:    //LL
            (*T)->bf = lc->bf = EH;
            R_Rotate(T);
            break;
        case RH:
            rd = lc->rchild;
            switch (rd->bf) {
                case LH:
                    (*T)->bf = RH;
                    lc->bf = EH;
                    break;
                case EH:
                    (*T)->bf = lc->bf = EH;
                    break;
                case RH:
                    (*T)->bf = EH;
                    lc->bf = LH;
                    break;
            }
            rd->bf = EH;
            L_Rotate(&(*T)->lchild);
            R_Rotate(T);
            break;
    }
}

/**
 * 右子树的平衡处理同左子树的平衡处理完全类似
 * @param T 最小不平衡子树的根节点
 */
void RightBalance(AVLTree *T) {
    AVLTree lc, rd;
    lc = (*T)->rchild;
    /**
     *      T
     *       \
     *        lc
     *       /  \
     *     LCL  LCR
     */
    switch (lc->bf) {
        case RH:
            (*T)->bf = lc->bf = EH;//说明在右孩子的右子树中插入了节点
            L_Rotate(T);
            break;
        case LH:
            rd = lc->lchild;
            switch (rd->bf) {
                case LH:
                    (*T)->bf = EH;
                    lc->bf = RH;
                    break;
                case EH:
                    (*T)->bf = lc->bf = EH;
                    break;
                case RH:
                    (*T)->bf = EH;
                    lc->bf = LH;
                    break;
            }
            rd->bf = EH;
            R_Rotate(&(*T)->rchild);
            L_Rotate(T);
            break;
    }
}

/**
 * 构建AVL树
 * @param T
 * @param e
 * @param taller 判断树是否由于添加元素而变高
 * @return 插入成功返回true
 */
int InsertAVL(AVLTree *T, ElemType e, bool *taller) {
    //如果本身为空树，则直接添加 e 为根节点
    if ((*T) == NULL) {
        (*T) = (AVLNode *) calloc(1, sizeof(AVLNode));
        (*T)->bf = EH;  //0
        (*T)->data = e;
        *taller = true;
    } else if (e == (*T)->data) {//如果已经存在 e ,则不允许插入
        *taller = false;
        return false;
    } else if (e < (*T)->data) {//如果 e 小于结点 T 的数据域，则插入到 T 的左子树中
        //如果插入过程，不会影响树本身的平衡，则直接结束
        if (!InsertAVL(&(*T)->lchild, e, taller))
            return false;
        //判断插入过程是否会导致整棵树的深度 +1
        if (*taller) {
            //判断根结点 T 的平衡因子是多少，由于是在其左子树添加新结点的过程中导致失去平衡，所以当 T 结点的平衡因子本身为 1 时，需要进行左子树的平衡处理，否则更新树中各结点的平衡因子数
            switch ((*T)->bf) {
                case LH:    //原先左高
                    LeftBalance(T); //此时在此节点的左子树插入节点，导致bf变为2
                    *taller = false;
                    break;
                case EH:    //原先等高
                    (*T)->bf = LH;
                    *taller = true;
                    break;
                case RH:    //原先是右高
                    (*T)->bf = EH;
                    *taller = false;
                    break;
            }
        }
    } else {//同样，当 e>T->data 时，需要插入到以 T 为根结点的树的右子树中，同样需要做和以上同样的操作
        if (!InsertAVL(&(*T)->rchild, e, taller))
            return 0;
        if (*taller) {
            switch ((*T)->bf) {
                case LH:
                    (*T)->bf = EH;
                    *taller = false;
                    break;
                case EH:
                    (*T)->bf = RH;
                    *taller = true;
                    break;
                case RH:
                    RightBalance(T);
                    *taller = false;
                    break;
            }
        }
    }
    return true;
}

/**
 * AVL的查找
 * @param root 树根
 * @param e 关键字
 * @param pos 查找成功返回指定的节点指针
 * @return 成功返回true
 */
bool AVL_Search(AVLTree root, ElemType e, AVLNode **ret) {
    AVLNode *p = root;
    *ret = NULL;
    while (p) {
        if (p->data == e) {
            *ret = p;
            return true;
        } else if (p->data > e)
            p = p->lchild;
        else
            p = p->rchild;
    }
    return false;
}

/**
 * 中序遍历
 * @param root
 */
void InOrderAVL(AVLTree root) {
    if (root) {
        InOrderAVL(root->lchild);
        printf("CurNode = { data: %d, bf: %d, left: %p, right: %p }\n", root->data, root->bf, root->lchild,
               root->rchild);
        InOrderAVL(root->rchild);
    }
}

int main() {
    AVLTree root = NULL;
    bool flag = false;
    int arr[] = {2,4,1,6,7,3,3,9};
    for (size_t i = 0; i < sizeof(arr)/sizeof(arr[0]); i++)
    {
        InsertAVL(&root,arr[i],&flag);
    }
    InOrderAVL(root);
    return 0;
}