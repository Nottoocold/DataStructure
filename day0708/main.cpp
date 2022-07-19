#include "function.h"
#include <iostream>
extern BiTNode *lowestCommonAncestor(BiTree root, BiTree p, BiTree q);
extern BiTNode *insertIntoBST(BiTNode *&root, int val);
extern BiTNode *p, *q;
//层次遍历
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