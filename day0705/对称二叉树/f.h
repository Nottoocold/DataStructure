#ifndef __QUEUE_H_
#define __QUEUE_H_
#include <stdio.h>
#include <stdlib.h>
struct TreeNode {
	int val;
	struct TreeNode* left;
	struct TreeNode* right;
};

typedef struct TreeNode* QElemType;

#define MAXSIZE 20

typedef struct QNode
{
	QElemType* base;
	int front;
	int rear;
	int cur;
}SqQueue;

int _InitQueue(SqQueue* Q);

int _QueueLength(SqQueue Q);

int _EnQueue(SqQueue* Q, QElemType e);

int _DeQueue(SqQueue* Q, QElemType* e);

#endif // !__QUEUE_H_