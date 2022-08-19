#include "head.h"

int main()
{
    //数组的平方，并升序排列
    int num[] = {2, -1, 3, 4};
    int retSize;
    int *ret = sortedSquares(num, 4, retSize);
    int *last = ret + retSize - 1;
    while (last >= ret)
    {
        cout << *last << " ";
        last--;
    }

    //轮换数组中的数k位
    int a[] = {-1, -100, 3, 99};
    rotate(a, 4, 2);
    for (int i : a)
    {
        cout << i << " ";
    }

    return 0;
}