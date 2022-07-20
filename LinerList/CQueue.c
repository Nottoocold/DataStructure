#include "CQueue.h"

void C_InitQueue(CQueue* Q)
{
	QElemType* p = (QElemType*)malloc(sizeof(QElemType) * MAXSIZE);
	assert(p);
	Q->base = p;
	Q->front = Q->rear = 0;
}

int C_QueueLength(CQueue Q)
{
	return (Q.rear - Q.front + MAXSIZE) % MAXSIZE;
}

int C_EnQueue(CQueue* Q, QElemType e)
{
	if ((Q->rear+1) % MAXSIZE == Q->front)
	{
		return 0;
	}
	Q->base[Q->rear] = e;
	Q->rear = (Q->rear + 1) % MAXSIZE;
	return 1;
}

int C_DeQueue(CQueue* Q, QElemType* e)
{
	if (Q->rear == Q->front)
	{
		return 0;
	}
	*e = Q->base[Q->front];
	Q->front = (Q->front + 1) % MAXSIZE;
	return 1;
}
