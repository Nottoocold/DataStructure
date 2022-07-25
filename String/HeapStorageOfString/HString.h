#ifndef __HSTRING_H_
#define __HSTRING_H_
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>
typedef struct HString
{
	char* ch;
	int length;
}HString;

//串赋值
int StrAssign(HString* T,char* chars);

//串拷贝S->T
int StrCopy(HString* T, HString S);

//
int StrLength(HString T);

int StrCompare(HString T, HString S);

//串长置0
int ClearStr(HString* T);

//释放串空间
int DestoryStr(HString* T);

int StrEmpty(HString T);

//串链接 S1 + S2 = T
int Concat(HString* T, HString S1, HString S2);

//从S的pos位置起len长度的子串
HString* SubString(HString S, int pos, int len);

//子串在主串中的索引，没有则返回-1
int indexStr(HString main_T, HString sub_S);

void printStr(HString T);

#endif // !__HSTRING_H_
