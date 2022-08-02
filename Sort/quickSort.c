/**快速排序
 * 空间复杂度 最坏O(n) 最好 O(logn) 
 * 时间复杂度 最坏O(n2) 最好O(nlogn) 
 * Partition函数划分区间的时间复杂度最坏O(n)
 * 而quick递归函数本质上是一个二叉排序树，树高最矮是O(logn)
 * 故时间复杂度O(nlogn)
 * 算法表现取决于递归深度，当枢轴元素将序列划分的越均匀，则递归深度越低，效率越高
 * 不稳定的排序算法 
 */

#include <stdio.h>
typedef int ElementType;
int Partition(int A[], int, int);

void QuickSort(int A[], int low, int high){
    if(low < high){
        int pivot_pos = Partition(A,low,high);//划分区间
        QuickSort(A,low,pivot_pos - 1);//递归左区间
        QuickSort(A,pivot_pos + 1,high);//递归右区间
    }
}

int Partition(int A[], int low, int high){
    ElementType pivot = A[low];//取第一个元素作为枢轴元素
    while(low < high){
        while(low < high && A[high] >= pivot)//找到比pivot小的值
            --high;
        A[low] = A[high];
        while(low < high && A[low] <= pivot)//找到比pivot大的元素
            ++low;
        A[high] = A[low];
    }
    A[low] = pivot;
    return low;//返回枢轴元素的索引
}

int main(){
    ElementType a[] = {12,45,3,16,30,20,28,33};
    int len = sizeof(a) / sizeof(a[0]), i = 0;
    QuickSort(a,0,len - 1);
    while(i < len)
        printf("%d ",a[i++]);
    printf("\n");
    return 0;
}

