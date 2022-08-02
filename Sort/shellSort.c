/*
 *希尔排序 最坏时间复杂度 O(n2)
 *不稳定排序算法·
 */
#include <stdio.h>

void shellSort(int A[], int size){
    int d, i, j;
    //A[0] 是暂存空间
    for(d = size >> 1; d >= 1; d = d >> 1){
        for(i = 1+d; i <= size; ++i){//++i 会轮换切换处理的子表
            if(A[i] < A[i-d]){
                A[0] = A[i];
                for(j = i - d; j > 0 && A[j] > A[0]; j -=d)//查找插入位置
                    A[j+d] = A[j];
                A[j+d] = A[0];
            }
        }
    }
}

int main(){
    int a[] = {-1,8,0,9,1,4,2,7,3,5};
    int size = sizeof(a) / sizeof(a[0]), i = 1;
    shellSort(a,size-1);
    while(i < size)
        printf("%d ",a[i++]);
    printf("\n");
    return 0;
}
