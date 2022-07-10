#include "function.h"
BiTNode *p = nullptr, *q = nullptr;
/*找出给定节点p和q的最近公告祖先*/
/*所有节点都是唯一的不会重复,且不是NULL*/
//判断从p出发能否找到q
int Find(BiTree p, BiTree q)
{
    if (!p)
        return 0;
    if (p->data == q->data)
        return 1;
    else if (q->data < p->data)
    {
        return Find(p->lchild, q);
    }
    else
    {
        return Find(p->rchild, q);
    }
}
//返回p的双亲节点
BiTNode *FindParaent(BiTree root, BiTree p)
{
    if ((root->lchild != nullptr && root->lchild->data == p->data) || (root->rchild != nullptr && root->rchild->data == p->data))
        return root;
    if (p->data < root->data)
    {
        return FindParaent(root->lchild, p);
    }
    else
    {
        return FindParaent(root->rchild, p);
    }
}
//求以root为根，p和q的最近公共祖先
BiTNode *lowestCommonAncestor(BiTree root, BiTree p, BiTree q)
{
    if (Find(p, q))
    {
        return p;
    }
    if (Find(q, p))
    {
        return q;
    }
    // p q 分别在不同的子树中
    //分别找p q 的双亲节点
    BiTree p_pa = FindParaent(root, p);
    BiTree q_pa = FindParaent(root, q);
    while (p_pa != root && q_pa != root)
    {
        if (Find(p_pa, q))
        {
            return p_pa;
        }
        if (Find(q_pa, p))
        {
            return q_pa;
        }
        p_pa = FindParaent(root, p_pa);
        q_pa = FindParaent(root, q_pa);
    }
    return root;
}

BiTNode *createNode(int val)
{
    BiTNode *pp = (BiTNode *)calloc(1, sizeof(BiTNode));
    pp->data = val;
    if (val == 2)
        p = pp;
    if (val == 4)
        q = pp;
    return pp;
}
//构建二叉搜索树
BiTNode *insertIntoBST(BiTNode *&root, int val)
{
    if (!root)
    { // kongshu
        return createNode(val);
    }
    if (val < root->data)
    {
        if (root->lchild == NULL)
        {
            root->lchild = createNode(val);
        }
        else
        {
            insertIntoBST(root->lchild, val);
        }
    }
    else if (val > root->data)
    {
        if (root->rchild == NULL)
        {
            root->rchild = createNode(val);
        }
        else
        {
            insertIntoBST(root->rchild, val);
        }
    }
    return root;
}
