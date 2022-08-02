/**
 * 冒泡排序
 * 稳定的排序算法
 * 时间复杂度 O(n)
 */

#include <stdio.h>
typedef enum {false,true}bool;
void swap(int* const,int* const);

void bubbleSort(int a[], int size){
    for(int i = 0; i < size - 1; ++i){
        bool flag = false;//标记本趟排序是否发生元素交换
        for(int j = size - 1; j > i; --j){
            if(a[j-1] > a[j])//前面的值大
            {
                swap(&a[j-1],&a[j]);
                flag = true;
            }
        }
        if(flag == false)
            return;
    }
}

void swap(int* const x, int* const y){
    int t = *x;
    *x = *y;
    *y = t;
}

int main(){
    int a[] = {34,1,8,10,14,2,6,2,8,9,3,5};
    int i = 0, size = sizeof(a) / sizeof(a[0]);
    bubbleSort(a, size);
    while(i < size)
        printf("%d ",a[i++]);
    printf("\n");
    return 0;
}
