/*KMP算法，时间复杂度，因为在匹配前需要对模式串进行预处理，即求出模式串的next数组，需要O(m)的时间，
  而进行匹配的最坏时间复杂度是O(n)，即主串指针会一直移动到末尾。*/
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#define MAXSIZE 255
typedef struct{
	char ch[MAXSIZE];//0号位置不使用
	int length;
}HString;

HString* createString(const char* c){
	unsigned int len = strlen(c);
	HString* S = (HString*)calloc(1,sizeof(HString));
	memcpy(&(S->ch[1]), c, len + 1);
	S->length = (int)len;
	return S;
}

//朴素模式匹配算法
int normal_pattern(HString S, HString T){
	int n = S.length, m = T.length;
	int i = 1, j = 1;
	while(i <= n && j <= m){
		if(S.ch[i] == T.ch[j]){
			i++;j++;
		}
		else {
			i = i -j + 2;//指针后退重新匹配下一个子串
			j = 1;
		}
	}
	if(j > T.length)//匹配成功
		return i - m;
	else
		return 0;
}

//求next[j]数组,0号位置不使用
int* getNext(HString T){
	int* next = (int*)calloc(1,sizeof(int) * (T.length + 1));
	next[1] = 0;
	int i = 1, j = 0;
	while(i < T.length){
		if(j == 0 || T.ch[i] == T.ch[j]){
			++i;++j;
			next[i] = j;//ch[i] == cg[j] , next[j+1] = next[j] + 1;
		}
		else
			j = next[j];
	}
	return next;
}

//KMP算法,S为主串，T为模式串
int KMP(HString S, HString T){
	int* next = NULL;
	if(T.length != 0){
		next = getNext(T);
	}
	int i = 1, j = 1;
	while(i <= S.length && j <= T.length){
		if(j == 0 || S.ch[i] == T.ch[j]){
			++i;++j;//continue	
		}
		else
			j = next[j];//模式串指针右移
	}
	free(next); next = NULL;
	if(j > T.length)
		return i - T.length;//匹配成功
	else
		return 0;
}

int main()
{
	char s1[] = "abaabbabaaababaabab";
	char s2[] = "abaaba";
	HString* S1 = createString(s1);
	HString* S2 = createString(s2);
	printf("S1 is %s, len is %d\n",&(S1->ch[1]),S1->length);
	printf("S2 is %s, len is %d\n",&(S2->ch[1]),S2->length);
	printf("S2 in S1 pos is %d\n",KMP(*S1,*S2));//13
	return 0;
}
