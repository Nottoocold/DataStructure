#include <iostream>
#include <cstring>
using namespace std;

void Swap(const void *x, const void *y)
{
    char c = *(char *)x;
    *(char *)x = *(char *)y;
    *(char *)y = c;
}

/*反转字符串，要求使用O(1)的空间*/
void reverseString(char *s, int sSize)
{
    int mid = sSize / 2;
    int i;
    for (i = 0; i < mid; ++i)
    {
        Swap(&s[i], &s[sSize - 1 - i]);
    }
}

/*反转字符串中的单词*/
char *reverseWords(char *s)
{
    int len = strlen(s) + 1;
    int start = 0; //一个单词的开始
    int end = len; //一个单词的结束，' ' 或 '\0'
    for (int i = 0; i < len;)
    {
        if (s[i] == ' ' || s[i] == '\0')
        {
            end = i;
            reverseString(&s[start], end - start);
            start = ++i;
        }
        else
        {
            ++i;
        }
    }
    return s;
}

int main()
{
    char s[] = "Hello world";
    char *ret = reverseWords(s);
    cout << ret << endl;
    return 0;
}