#include <stdio.h>

int max(int* nums, int numsSize)
{
    int i, max;
    max = nums[0];
    for (i = 1; i < numsSize; i++)
    {
        if (max < nums[i])
        {
            max = nums[i];
        }
    }
    return max;
}

//
int maxSubArray(int* nums, int numsSize) {//动态规划，核心思想是前一个数是非负数时，则和当前数相加
    int i;
    for (i = 1; i < numsSize; i++)//初始为num[0]，即以第0个数字结尾的子数组只有a[0]，
    {
        if (nums[i - 1] > 0)
        {
            nums[i] = nums[i - 1] + nums[i];
        }
    }
    return max(nums, numsSize);
}

int main()
{
    int arr[] = { -2, 1, -3, 2, 4, -5, 7, -5, 4 };
    int size = sizeof(arr) / sizeof(int);
    int ret = maxSubArray(arr, size);
}