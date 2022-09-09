//
// Created by zyc on 2022/7/20.
//
#include "List.h"
#include "Stack.h"
#include "LinkQueue.h"
#include "CQueue.h"

int main(){
  printf("Stack test..\n");
  SqStack S;
  InitStack(&S);
  int i ;
  for( i = 1; i < 10; ++i)
  {
    printf("Push..%d ",i);
    Push(&S,i);
  }
  printf("\n");
  while (!StackEmpty(S))
  {
    printf("GetTopElem..%d ",GetTopElem(S,&i));
    printf("Pop..%d \n",Pop(&S,&i));
  }
  return 0;
}
