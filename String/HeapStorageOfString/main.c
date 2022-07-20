#include "HString.h"

int main()
{
	HString T, S, SS, SSS;
	char ch1[] = "hello world,";
	char ch2[] = "I love you!";
	StrAssign(&T, ch1);
	StrAssign(&S, ch2);
	printf("T-> ");
	printStr(T);
	printf("S-> ");
	printStr(S);
	Concat(&SS, T, S);
	printf("Concat(&SS,T,S)->SS-> ");
	printStr(SS);
	HString* p = SubString(T, 7, 5);
	printf("SubString(T,7,5)-> %s\n", p->ch);
	StrCopy(&T, S);
	printf("StrCopy(T,S)..T-> ");
	printStr(T);
	printf("S-> ");
	printStr(S);
	printf("\n");
	char ch3[] = "abcadadsjhdk";
	char ch4[] = "adsj";
	char ch5[] = "hdka";
	char ch6[] = "sjdasjldjalsjdla";
	StrAssign(&T, ch3);
	StrAssign(&S, ch4);
	StrAssign(&SS, ch5);
	StrAssign(&SSS, ch6);
	printf("T is ");
	printStr(T);
	printf("S is ");
	printStr(S);
	printf("SS is ");
	printStr(SS);
	printf("SSS is ");
	printStr(SSS);
	printf("\n");
	printf("S in T index is %d\n", indexStr(T, S));//6
	printf("SS in T index is %d\n", indexStr(T, SS));//-1
	printf("SSS in T index is %d\n", indexStr(T, SSS));//-1

	return 0;
}
