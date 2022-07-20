#ifndef __LINKQUEUE_H__
#define __LINKQUEUE_H__
#include <stdlib.h>

typedef int bool;
#define true 1
#define false 0

typedef char ElemType;

typedef struct LinkNode {
	ElemType data;
	struct LinkNode* next;
}LinkNode;

typedef struct LinkQueue{
	LinkNode* front, * rear;
}LinkQueue;

void InitQueue(LinkQueue* Q);
bool IsEmpty(LinkQueue Q);
void EnQueue(LinkQueue* Q, ElemType x);
bool DeQueue(LinkQueue* Q, ElemType* x);

#endif // !__LINKQUEUE_H__
