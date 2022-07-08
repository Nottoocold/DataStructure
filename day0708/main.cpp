#include "function.h"
#include <iostream>
extern BiTNode *lowestCommonAncestor(BiTree root, BiTree p, BiTree q);
BiTree p = nullptr, q = nullptr;

void LevelOrder(BiTree T)
{
	LinkQueue Q;
	InitQueue(Q);
	BiTree p;
	EnQueue(Q, T);
	while (!IsEmpty(Q))
	{
		DeQueue(Q, p);
		putchar(p->data);
		if (p->lchild != NULL)
			EnQueue(Q, p->lchild);
		if (p->rchild != NULL)
			EnQueue(Q, p->rchild);
	}
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

int main()
{
	BiTree root = nullptr;
	int data;
	while ((scanf("%d", &data) != EOF))
	{
		if (data == 999)
			break;
		root = insertIntoBST(root, data);
	}
	BiTNode *ret = lowestCommonAncestor(root, p, q);
	std::cout << ret->data << std::endl;
	return 0;
}