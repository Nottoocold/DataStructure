#include "BiTree.h"

int main()
{
	BiTree root = NULL;
	char ch[] = "zhao";
	BiTreeNode* Queue[4];
	int front = 0, rear = 0;
	int cur = 0;
	char* p = ch;
	while(*p)
	{
		BiTreeNode* t = (BiTreeNode*)calloc(1,sizeof(BiTreeNode));
		t->data.ch = *p;
		if(!root)
		{
			root = t;
			Queue[rear++] = t;		
		}
		else
		{
			if(Queue[cur]->lchild == NULL)
			{
				Queue[cur]->lchild = t;
				Queue[rear++] = t;
			}
			else if(Queue[cur]->rchild == NULL)
			{
				Queue[cur++]->rchild = t;
				Queue[rear++] = t;		
			}
		}					
		p++;
	}
	printf("PreOrder: ");
	PreOrder(root);
	printf("\nInOeder: ");
	InOrder(root);
	printf("\nPostOeder: ");
	PostOrder(root);
	printf("\nLevelOrder: ");
	LevelOrder(root);
	printf("\nTreeDepth is %d\n",TreeDeep(root));
	return 0;
}

