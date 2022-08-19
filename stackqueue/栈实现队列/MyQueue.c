#include "MyQueue.h"

void Init(MyQueue* Q)
{
	int* p1 = (int*)calloc(QUEUE_SIZE, sizeof(int));
	int* p2 = (int*)calloc(QUEUE_SIZE, sizeof(int));
	assert(p1 && p2);
	Q->s1.queue = p1;
	Q->s2.queue = p2;
	Q->s1.top = -1;
	Q->s2.top = -1;
}

Bool IsEmpty(MyQueue Q)
{
	if (Q.s1.top == -1 && Q.s2.top == -1)
	{
		return TRUE;
	}
	return FALSE;
}

void Push(MyQueue* Q, int value)
{
	if (Q->s1.top == QUEUE_SIZE - 1)//full
	{
		return;
	}
	Q->s1.queue[++Q->s1.top] = value;
}

int Pop(MyQueue* Q)
{
	if (Q->s2.top != -1)
	{
		return Q->s2.queue[Q->s2.top--];
	}
	if (Q->s2.top == -1 && Q->s1.top != -1)
	{
		while (Q->s1.top != -1)
		{
			Q->s2.queue[++Q->s2.top] = Q->s1.queue[Q->s1.top--];
		}
		return Q->s2.queue[Q->s2.top--];
	}
	return 10;//faile
}

int Peek(MyQueue* Q)
{
	if (Q->s2.top != -1)
	{
		return Q->s2.queue[Q->s2.top];
	}
	if (Q->s2.top == -1 && Q->s1.top != -1)
	{
		while (Q->s1.top != -1)
		{
			Q->s2.queue[++Q->s2.top] = Q->s1.queue[Q->s1.top--];
		}
		return Q->s2.queue[Q->s2.top];
	}
	return 10;
}

void Free(MyQueue* Q)
{
	if (Q)
	{
		free(Q->s1.queue);
		free(Q->s2.queue);
		Q->s1.queue = NULL;
		Q->s2.queue = NULL;
		free(Q);
		Q = NULL;
	}
}
