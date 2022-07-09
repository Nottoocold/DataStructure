#include "../include/f"

int add(int x, int y)
{
    return x + y;
}

//二维数组的一位遍历方法
void printNums(int *p, int row, int col)
{
    int *front = p;
    int *last = p + row * col - 1;
    while (front <= last)
    {
        cout << *front << " ";
        front++;
    }
}

//二维数组的指针遍历方法
void printNUms__(int (*p)[4], int row, int col)
{
    for (int i = 0; i < row; ++i)
    {
        for (int j = 0; j < col; ++j)
        {
            cout << *(*(p + i) + j) << " ";
        }
    }
}