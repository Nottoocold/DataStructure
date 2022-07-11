#include "head.h"
#include <stdlib.h>

int cmp(const void* x, const void* y)
{
    return *((int*)x) - *((int*)y);
}

int* sortedSquares(int* nums, int numsSize, int &returnSize){
    if(numsSize == 1)
    {
        int* p = (int*)calloc(1,sizeof(int));
        *p = nums[0] * nums[0];
        returnSize = 1;
        return p;
    }
    int* ret = (int*)calloc(numsSize,sizeof(int));
    for(int i = 0; i < numsSize; ++i)
    {
        ret[i] = nums[i] * nums[i];
    }
    qsort(ret,numsSize,sizeof(nums[0]),cmp);
    returnSize = numsSize;
    return ret;
}

