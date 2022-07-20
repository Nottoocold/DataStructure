#include "List.h"
// impl

void InitList(SqList* L)
{
    L->data = (ElementType*)malloc(sizeof(ElementType) * INITSIZE);
    L->listsize = INITSIZE;
    L->length = 0;
}

int DestoryList(SqList* L)
{
    if (L != NULL)
    {
        free(L->data);
        L->data = NULL;
        free(L);
        L = NULL;
        return 1;
    }
    return 0;
}

void ClearList(SqList* L)
{
    if (L != NULL && L->length != 0)
    {
        L->length = 0;
    }
}


int isEmpty(SqList L)
{   
    return L.length == 0 ? 1 : 0;
}

int Length(SqList L)
{
    return L.length;
}

void printList(SqList L)
{
    for (int i = 0; i < L.length; i++)
    {
        printf("%d ", L.data[i]);
    }
}
//返回表中元素e的位序，没有返回-1
int LocateElem(SqList L, ElementType e,int(*compare)(ElementType,ElementType))
{
    if (isEmpty(L))
    {
        return -1;
    }
    for (int i = 0; i < L.length; i++)
    {
        if (!compare(L.data[i],e))
        {
            return i + 1;
        }
    }
    return -1;
}
//获取第i个位置上的元素
void getElem(SqList L, int i, ElementType* e)
{
    if(isEmpty(L) || i < 1 || i > L.length)
    {
        printf("args is wrongful.\n");
        return;
    }
    *e = L.data[i - 1];
}

int ListInsert(SqList* L, int i, ElementType e)
{
    if (L == NULL || i < 1 || i >(L->length + 1))
    {
        printf("args is wrongful.\n");
        return 0;
    }
    if (L->length >= L->listsize)//扩容
    {
        ElementType* ps = realloc(L->data, sizeof(ElementType) * (L->listsize + LISTINCREMENT));
        assert(ps);
        L->data = ps;
        L->listsize += LISTINCREMENT;
    }
    ElementType* q = &(L->data[i - 1]);
    ElementType* p = &(L->data[L->length - 1]);
    while (p >= q)//移动元素
    {
        *(p + 1) = *p;
        --p;
    }
    *q = e;
    L->length++;
    return 1;
}
//删除第i个位置的元素，并用e返回
void ListDelete(SqList* L, int i, ElementType* e)
{
    if (L == NULL || i < 1 || i > L->length)
    {
        printf("args is wrongful.\n");
        return;
    }
    *e = L->data[i - 1];
    ElementType* last = &(L->data[L->length - 1]);
    ElementType* cur = &(L->data[i - 1]);
    while (cur < last)
    {
        *cur = *(cur + 1);
        cur++;
    }
    L->length--;
}


//合并两个线性表，得到新表
void MergeList(SqList La, SqList Lb, SqList* Lc)
{
    ElementType* pa = La.data;
    ElementType* pb = Lb.data;
    Lc->listsize = La.length + Lb.length;//新表表长
    Lc->length = 0;
    ElementType* pc = Lc->data = (ElementType*)malloc(sizeof(ElementType) * Lc->listsize);//
    ElementType* pa_last = La.data + La.length - 1;
    ElementType* pb_last = Lb.data + Lb.length - 1;
    while (pa <= pa_last && pb <= pb_last)
    {
        if (*pa < *pb)
        {
            *pc = *pa;
            pa++;
            pc++;
            Lc->length++;
        }
        else if(*pa == *pb)//若两者相等则只保留一个
        {
            *pc = *pa;
            pa++;
            pb++;
            pc++;
            Lc->length++;
        }
        else
        {
            *pc = *pb;
            pb++;
            pc++;
            Lc->length++;
        }
    }
    while (pa <= pa_last)
    {
        *pc = *pa;
        pa++;
        pc++;
        Lc->length++;
    }
    while (pb <= pb_last)
    {
        *pc = *pb;
        pb++;
        pc++;
        Lc->length++;
    }
}

int compare(ElementType e1, ElementType e2)
{
    return e1 - e2;
}
