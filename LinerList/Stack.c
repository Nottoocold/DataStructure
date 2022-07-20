#include "Stack.h"

void InitStack(SqStack* S)
{
	S->base = (SElemType*)malloc(sizeof(SElemType) * STACK_INIT_SIZE);
	assert(S->base);
	S->top = S->base;
	S->stacksize = STACK_INIT_SIZE;
}

void ClearStack(SqStack* S)
{
	S->top = S->base;
}

void DestoryStack(SqStack* S)
{
	assert(S);
	S->top = NULL;
	free(S->base);
	S->base = NULL;
	free(S);
	S = NULL;
}

int StackEmpty(SqStack S)
{
	return S.base == S.top;
}

int StackLength(SqStack S)
{
	return (S.top - S.base) / sizeof(SElemType);

}

int GetTopElem(SqStack S, SElemType* e)
{
	if (S.base == S.top)
	{
		return 0;
	}
	*e = *(S.top - 1);
	return *e;
}

int Push(SqStack* S, SElemType e)
{
	if (S->top - S->base >= S->stacksize)//扩容栈
	{
		S->base = (SElemType*)realloc(S->base, sizeof(SElemType) * (S->stacksize + STACK_INCREAMENT));
		assert(S->base);
		S->top = S->base + S->stacksize;
		S->stacksize += STACK_INCREAMENT;
	}
	*(S->top) = e;
	S->top++;
	return 1;
}

int Pop(SqStack* S, SElemType* e)
{
	if (S->base == S->top)
	{
		return 0;
	}
	S->top--;
	*e = *(S->top);
	return *e;
}
