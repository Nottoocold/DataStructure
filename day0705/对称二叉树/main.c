#include <stdlib.h>
#include <assert.h>
#include "f.h"
#define bool int
#define true 1
#define false 0

void InOrder(struct TreeNode* T, int* res, int* size)
{
    if (T) {
        InOrder(T->left, res, size);
        res[(*size)++] = T->val;
        InOrder(T->right, res, size);
    }
}

int* inorderTraversal(struct TreeNode* root, int* returnSize) {
    if (!root) {
        *returnSize = 0;
        return NULL;
    }
    *returnSize = 0;
    int* result = (int*)calloc(100, sizeof(int));
    InOrder(root, result, returnSize);
    return result;
}

bool isSymmetric(struct TreeNode* root) {
    int* returnSize = (int*)calloc(1, sizeof(int));
    assert(returnSize);
    int* front = inorderTraversal(root, returnSize);
    //if ((*returnSize + 1) % 2 == 0) return false;
    int* rear = front + *returnSize-1;
    return *front == *rear;
}

int main1()
{
	struct TreeNode* root = NULL;//树根
	struct TreeNode* T;//树节点
	SqQueue Q;
	int ch[] = { 1,2,2,2,'-',2};
	int* p = ch;
	_InitQueue(&Q);
	while (p <= ch+5)//层次建树
	{
		T = (struct TreeNode*)malloc(sizeof(struct TreeNode));
		T->val = *p;
		T->left = NULL;
		T->right = NULL;
		if (!root)//空树
		{
			_EnQueue(&Q, T);
			root = T;
			p++;
			continue;
		}
		else
		{
			_EnQueue(&Q, T);
		}
		if (Q.base[Q.cur]->left == NULL)
		{
			Q.base[Q.cur]->left = T;
		}
		else if (Q.base[Q.cur]->right == NULL)
		{
			Q.base[Q.cur]->right = T;
			Q.cur++;
		}
		p++;
	}
	isSymmetric(root);
    return 0;
}