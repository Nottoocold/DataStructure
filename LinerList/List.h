#ifndef _LIST_H_

#include <assert.h>
#include <stdio.h>
#include <stdlib.h>

typedef int ElementType;

#define INITSIZE 10 
#define LISTINCREMENT 5 

typedef struct List
{
    int listsize;//表长
    int length;//表中元素个数
    ElementType *data;
}SqList;


void InitList(SqList* L);


int DestoryList(SqList* L);


void ClearList(SqList* L);


int isEmpty(SqList L);


int Length(SqList L);


void printList(SqList L);


int LocateElem(SqList L, ElementType e, int(*compare)(ElementType, ElementType));


void getElem(SqList L, int i,ElementType* e);


int ListInsert(SqList* L, int i, ElementType e);


void ListDelete(SqList* L, int i,ElementType* e);


void MergeList(SqList La, SqList Lb, SqList* Lc);

int compare(ElementType, ElementType);

#endif // _LIST_H_