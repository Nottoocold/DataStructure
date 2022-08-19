#include <stdlib.h>
#include <stdio.h>
#include <string.h>
typedef int bool;
#define TRUE 1
#define FALSE 0

typedef struct Stack {
    int top;
    size_t size;
    char* base;
}Stack;

void Init(Stack* S,size_t size) {
    char* p = (char*)calloc(size, sizeof(char));
    if (p) {
        S->base = p;
        S->top = -1;
        S->size = size;
    }
}

bool Push(Stack* S, char c) {
    if (S->top != S->size - 1) {
        S->base[++S->top] = c;
        return TRUE;
    }
    return FALSE;
}

bool Pop(Stack* S, char* c) {
    if (S->top != -1) {
        *c = S->base[S->top--];
        return TRUE;
    }
    return FALSE;
}

char cmp(char c) {
    if (c == '(')
    {
        return ')';
    }
    if (c == '[')
    {
        return ']';
    }
    if (c == '{')
    {
        return '}';
    }
    return FALSE;
}

bool isValid(char* s) {
    int len = strlen(s);
    if (len % 2 != 0)//ÆæÊý¸ö
    {
        return FALSE;
    }
    Stack S;
    Init(&S,len);
    char c = 0;
    for (int i = 0; i < strlen(s); ++i)
    {
        switch (s[i])
        {
        case '(':
            Push(&S, '('); break;
        case '[':
            Push(&S, '['); break;
        case '{':
            Push(&S, '{'); break;
        case ')':
            if(!Pop(&S, &c)) return FALSE;
            if (!(cmp(c) == s[i])) return FALSE;
            break;
        case ']':
            if (!Pop(&S, &c)) return FALSE;
            if (!(cmp(c) == s[i])) return FALSE;
            break;
        case '}':
            if (!Pop(&S, &c)) return FALSE;
            if (!(cmp(c) == s[i])) return FALSE;
            break;
        default:
            break;
        }
    }
    if (S.top != -1)
    {
        return FALSE;
    }
    return TRUE;
}

int main()
{
    char c[] = "()))";
    if (isValid(c))
    {
        printf("yes\n");
    }
    else
    {
        printf("no\n");
    }
}