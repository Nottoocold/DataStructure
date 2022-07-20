#ifndef __STACK_H_
#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#define STACK_INIT_SIZE 10
#define STACK_INCREAMENT 5

typedef int SElemType;

typedef struct Stack
{
	SElemType* base;
	SElemType* top;
	int stacksize;
}SqStack;

void InitStack(SqStack* S);

void ClearStack(SqStack* S);

void DestoryStack(SqStack* S);

int StackEmpty(SqStack S);

int StackLength(SqStack S);

int GetTopElem(SqStack S, SElemType* e);

int Push(SqStack* S,SElemType e);

int Pop(SqStack* S, SElemType* e);

#endif // !__STACK_H_
