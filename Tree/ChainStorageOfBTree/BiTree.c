#include "BiTree.h"

void PreOrder(BiTree T)
{
	if (T != NULL)
	{
		visit(T);
		PreOrder(T->lchild);
		PreOrder(T->rchild);
	}
}

void InOrder(BiTree T)
{
	if (T != NULL)
	{
		InOrder(T->lchild);
		visit(T);
		InOrder(T->rchild);
	}
}

void PostOrder(BiTree T)
{
	if (T != NULL)
	{
		PostOrder(T->lchild);
		PostOrder(T->rchild);
		visit(T);
	}
}

void LevelOrder(BiTree T)
{
	BiTreeNode* Q[100];
	int front = 0, rear = 0;
	assert(T);
	Q[rear++] = T;
	while(front != rear)
	{
		BiTreeNode* p = Q[front++];
		visit(p);
		if(p->lchild)
			Q[rear++] = p->lchild;
		if(p->rchild)
			Q[rear++] = p->rchild;	
	}
}

void visit(BiTree T)
{
	putchar(T->data.ch);
}

int TreeDeep(BiTree T)
{
	if (T == NULL)
	{
		return 0;
	}
	int l = TreeDeep(T->lchild);
	int r = TreeDeep(T->rchild);
	return l > r ? l+1 : r+1;
}
