#ifndef __BITREE_H__
#define __BITREE_H__
#include <stdio.h>
#include <stdlib.h>
#include <assert.h>

typedef struct Element
{
	char ch;
} ElementType;

typedef struct BiTreeNode
{
	ElementType data;
	struct	BiTreeNode *lchild, *rchild;
} BiTreeNode, *BiTree;

void PreOrder(BiTree T);
void InOrder(BiTree T);
void PostOrder(BiTree T);
void LevelOrder(BiTree T);
void visit(BiTree T);
int TreeDeep(BiTree T);
#endif // !__BITREE_H__
