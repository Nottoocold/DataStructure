#include "HString.h"

int StrAssign(HString* T, char* chars)
{
	char* c = chars;
	int len = 0;
	while (*c)
	{
		len++;
		c++;
	}
	if (!len)//chars长度为0
	{
		T->ch = NULL;
		T->length = len;
	}
	else
	{
		if (!(T->ch=(char*)malloc(sizeof(char)*(len + 1))))
		{
			return 0;
		}
		memcpy(T->ch, chars, len+1);
		//T->ch[len] = '\0';
		T->length = len;
	}
	return 1;
}

int StrCopy(HString* T, HString S)
{
	assert(S.ch);
	return StrAssign(T, S.ch);
}

int StrEmpty(HString T)
{
	return T.length == 0;
}

int StrLength(HString T)
{
	return T.length;
}

int StrCompare(HString T, HString S)
{
	int i = 0;
	for ( i = 0; i < T.length && S.length; i++)
	{
		if (T.ch[i] != S.ch[i])
		{
			return T.ch[i] - S.ch[i];//对应位置不相等则返回
		}
	}
	return T.length - S.length;//对应位置相等返回两串长度相减
}

int ClearStr(HString* T)
{
	if (!T->ch)//串空
	{
		return 0;
	}
	T->length = 0;
	return 1;
}

int DestoryStr(HString* T)
{
	if (T->ch)
	{
		free(T->ch);
		T->ch = NULL;
		free(T);
		return 1;
	}
	return 0;
}

int Concat(HString* T, HString S1, HString S2)
{
	int len = S1.length + S2.length;
	char* cptr = (char*)malloc(sizeof(char) * (S1.length + S2.length + 1));
	assert(cptr);
	T->ch = cptr;
	memcpy(cptr, S1.ch, S1.length);
	memcpy((cptr + S1.length), S2.ch, S2.length + 1);
	T->length = len;
	return 1;
}
//包括pos，len包括pos
HString* SubString(HString S, int pos, int len)
{
	if (pos < 1 || pos > S.length || len < 0 || len > S.length - pos + 1)
	{
		printf("args is wrongful.\n");
		return NULL;//参数不合法
	}
	if (!len)
	{
		return NULL;
	}
	HString* Sub = (HString*)malloc(sizeof(HString));
	if (Sub)
	{
		Sub->ch = (char*)malloc(sizeof(char) * (len + 1));
		if (Sub->ch)
		{
			memcpy(Sub->ch, &(S.ch[pos - 1]), len);
			Sub->ch[len] = '\0';
			Sub->length = len;
		}
	}
	return Sub;
}

int indexStr(HString main_T, HString sub_S)
{
	if (sub_S.length > main_T.length)
	{
		return -1;
	}
	int i = 0, j = 0;
	while (i < main_T.length && j < sub_S.length)
	{
		if (main_T.ch[i] == sub_S.ch[j])
		{
			++i;
			++j;
		}
		else
		{
			i = i - j + 1;//重新调整i的位置
			j = 0;
		}
	}
	if (j > sub_S.length - 1)//子串比较完了，说明存在
	{
		return i - sub_S.length + 1;
	}
	else
	{
		return -1;
	}
}

void printStr(HString T)
{
	if (!StrEmpty(T))
	{
		printf("%s\n", T.ch);
	}
	else
	{
		printf("空串\n");
	}
}
