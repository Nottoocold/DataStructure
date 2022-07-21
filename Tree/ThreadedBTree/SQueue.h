#ifndef __QUEUE_H__
#define __QUEUE_H__
#include "ThreadTree.h"

typedef int bool;
#define true 1
#define false 0

typedef ThreadNode* ElemType;

typedef struct LinkNode {
	ElemType data;
	struct LinkNode* next;
}LinkNode;

typedef struct {
	LinkNode* front, * rear;
}LinkQueue;

void InitQueue(LinkQueue* Q);
bool IsEmpty(LinkQueue Q);
void EnQueue(LinkQueue* Q, ElemType x);
bool DeQueue(LinkQueue* Q, ElemType* x);

#endif // !__QUEUE_H__
