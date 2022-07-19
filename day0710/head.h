#ifndef __HEAD_H_
#define __HEAD_H_

#include <iostream>
using namespace std;

void swap(int* x,int *y);
void rotate(int* nums, int numsSize, int k);
int cmp(const void* x, const void* y);
int* sortedSquares(int* nums, int numsSize, int &returnSize);

#endif