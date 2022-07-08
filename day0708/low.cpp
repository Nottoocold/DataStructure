#include "function.h"
/*找出给定节点p和q的最近公告祖先*/
/*所有节点都是唯一的不会重复,且不是NULL*/
//从p出发能否找到q
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

BiTNode* FindParaent(BiTree root, BiTree p)
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

BiTNode* lowestCommonAncestor(BiTree root,BiTree p,BiTree q)
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
