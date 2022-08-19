#include <stdio.h>
#include <stdlib.h>

int min(int x, int y) {
    return x < y ? x : y;
}

//归并排序
void merge_sort(int arr[], int len) {
    int* a = arr;
    int* b = (int*)malloc(len * sizeof(int));
    int seg, start;
    for (seg = 1; seg < len; seg += seg) {
        for (start = 0; start < len; start += seg * 2) {
            int low = start, mid = min(start + seg, len), high = min(start + seg * 2, len);
            int k = low;
            int start1 = low, end1 = mid;
            int start2 = mid, end2 = high;
            while (start1 < end1 && start2 < end2)
                b[k++] = a[start1] < a[start2] ? a[start1++] : a[start2++];
            while (start1 < end1)
                b[k++] = a[start1++];
            while (start2 < end2)
                b[k++] = a[start2++];
        }
        int* temp = a;
        a = b;
        b = temp;
    }
    if (a != arr) {
        int i;
        for (i = 0; i < len; i++)
            b[i] = a[i];
        b = a;
    }
    free(b);
}


void bell(int* nums,int numsSize) {
    int tem, i, j;
    for (i = 0; i < numsSize - 1; i++)//冒泡排序
    {
        for (j = 0; j < numsSize - i - 1; j++)
        {
            if (nums[j] > nums[j + 1])
            {
                tem = nums[j];
                nums[j] = nums[j + 1];
                nums[j + 1] = tem;
            }
        }
    }
}

bool containsDuplicate(int* nums, int numsSize) {
    int i;
    for (i = 0; i < numsSize-1; i++)//排序过后的数组相邻的元素是相同的
    {
        if (nums[i] == nums[i + 1])
        {
            return true;
        }
    }
    return false;
}

int main()
{
    int nums[5] = { 2,4,3,1,5};
    int size = sizeof(nums) / sizeof(int);
    bool ret= containsDuplicate(nums, size);
    if (ret)
    {  
        printf("ok\n");
    }
    else
    {
        printf("error");
    }
}