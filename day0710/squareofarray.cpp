#include <iostream>
#include <stdlib.h>

using namespace std;

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

int main()
{
    int num[] = {2,-1,3,4};
    int retSize;
    int* ret = sortedSquares(num,4,retSize);
    int* last = ret + retSize - 1;
    while(last >= ret)
    {
        cout << *last << " ";
        last--;
    }
    return 0;
}