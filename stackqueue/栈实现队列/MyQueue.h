#ifndef __MYQUEUE_H_
#define __MYQUEUE_H_
#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#define QUEUE_SIZE 100
#define Bool int
#define TRUE 1
#define FALSE 0
typedef struct Stack
{
	int* queue;
	int top;
}Stack;

typedef struct Queue
{
	Stack s1, s2;
}MyQueue;

void Init(MyQueue* Q);
Bool IsEmpty(MyQueue Q);
void Push(MyQueue* Q,int value);
int Pop(MyQueue* Q);
int Peek(MyQueue* Q);
void Free(MyQueue* Q);
#endif // !__MYQUEUE_H_