#include "MyQueue.h"

int main()
{
	MyQueue Q;
	Init(&Q);
	Push(&Q, 1);
	Push(&Q, 2);
	Push(&Q, 3);
	Push(&Q, 4);
	Push(&Q, 5);
	printf("%d\n", Peek(&Q));//1
	printf("%d\n", Pop(&Q));//1
	printf("%d\n", Pop(&Q));//2
	printf("%d\n", Peek(&Q));//3
	printf("%d\n", IsEmpty(Q));//0
	return 0;
}