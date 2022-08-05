/**
 * 归并:将两个或多个已经有序的序列合并成一个
 * 在内部排序算法中一般采取2路归并
 * 归并的总趟数等于 O(logN)
 * 每次进行归并操作时间复杂度是 O(N)
 * 所以归并排序时间复杂度是 O(NlogN)
 * 稳定的排序算法
 */
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#define MAXSIZE 30

void Merge(int A[], int low, int mid, int high)
{
    int i, j, k;
    int *B = (int *)calloc(MAXSIZE, sizeof(int)); //申请辅助数组
    for (k = low; k <= high; ++k)
        B[k] = A[k]; //将元素复制到辅助数组中
    for (i = low, j = mid + 1, k = i; i <= mid && j <= high; ++k)
    { //进行归并
        if (B[i] <= B[j])
            A[k] = B[i++];
        else
            A[k] = B[j++];
    }
    while (i <= mid)
        A[k++] = B[i++];
    while (j <= high)
        A[k++] = B[j++];
    free(B);
}

void _mergeSort(int nums[], int low, int high)
{
    if (low < high)
    {
        int mid = (low + high) / 2;
        _mergeSort(nums, low, mid);      //递归左区域
        _mergeSort(nums, mid + 1, high); //递归右区域
        Merge(nums, low, mid, high);     //归并
    }
}

void MergeSort(int nums[], int size)
{
    if (size == 0 || size == 1)
        return;
    _mergeSort(nums, 0, size - 1);
}

int main()
{
    int j;
    int * nums = (int*)calloc(MAXSIZE,sizeof(int));
    for(j = 0; j < MAXSIZE; ++j){
//        srand((unsigned)time(NULL));
        nums[j] = rand() % 100;
    }
    MergeSort(nums, MAXSIZE);
    j = 0;
    while (j < MAXSIZE)
        printf("%d ", nums[j++]);
    printf("\n");
    return 0;
}
