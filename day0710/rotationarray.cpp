#include "head.h"
/*轮换数组k次*/
void swap( int* x,  int* y)
{
    int tmp = *x;
    *x = *y;
    *y = tmp;
}

void rotate(int* nums, int numsSize, int k){
    if(k < 0 || numsSize == 0 || numsSize == 1) return;
    int i = 1;
    int last = numsSize - 1;
    while(i <= k)
    {
        int j = 0;
        for(; j < last; ++j)
        {
            swap(&nums[j],&nums[last]);
        }
        ++i;
    }
}

