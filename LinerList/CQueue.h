/*
	循环队列
*/
#ifndef __QUEUE_H_
#define __QUEUE_H_
#include <stdio.h>
#include <stdlib.h>
#include <assert.h>

typedef int QElemType;

#define MAXSIZE 20

typedef struct QNode
{
	QElemType* base;
	int front;
	int rear;
}CQueue;

void C_InitQueue(CQueue* Q);

int C_QueueLength(CQueue Q);

int C_EnQueue(CQueue* Q, QElemType e);

int C_DeQueue(CQueue* Q, QElemType* e);

#endif // !__QUEUE_H_
