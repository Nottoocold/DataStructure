#include "f.h"

int _InitQueue(SqQueue* Q)
{
	QElemType* p = (QElemType*)malloc(sizeof(QElemType) * MAXSIZE);
	if (!p)
	{
		return 0;
	}
	Q->base = p;
	Q->front = Q->rear = 0;
	Q->cur = 0;//
	return 1;
}

int _QueueLength(SqQueue Q)
{
	return (Q.rear - Q.front + MAXSIZE) % MAXSIZE;
}

int _EnQueue(SqQueue* Q, QElemType e)
{
	if ((Q->rear + 1) % MAXSIZE == Q->front)
	{
		return 0;
	}
	Q->base[Q->rear] = e;
	Q->rear = (Q->rear + 1) % MAXSIZE;
	return 1;
}

int _DeQueue(SqQueue* Q, QElemType* e)
{
	if (Q->rear == Q->front)
	{
		return 0;
	}
	*e = Q->base[Q->front];
	Q->front = (Q->front + 1) % MAXSIZE;
	return 1;
}
