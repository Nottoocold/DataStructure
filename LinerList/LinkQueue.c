#include "LinkQueue.h"

void InitQueue(LinkQueue* Q)
{
	Q->front = Q->rear = (LinkNode*)malloc(sizeof(LinkNode));
	if (Q->front && Q->rear)
	{
		Q->front->next = NULL;
		Q->front->data = 0;
	}
}

bool IsEmpty(LinkQueue Q)
{
	if (Q.front == Q.rear)
		return true;
	else
		return false;
}

void EnQueue(LinkQueue* Q, ElemType x)
{
	LinkNode* s = (LinkNode*)malloc(sizeof(LinkNode));
	if (s)
	{
		s->data = x; 
		s->next = NULL;
	}
	Q->rear->next = s;
	Q->rear = s;
	Q->front->data++;
}

bool DeQueue(LinkQueue* Q, ElemType* x)
{
	if (Q->front == Q->rear) return false;
	LinkNode* p = Q->front->next;
	*x = p->data;
	Q->front->next = p->next;
	if (Q->rear == p)
		Q->rear = Q->front;
	free(p);
	p = NULL;
	Q->front->data--;
	return true;
}