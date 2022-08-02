/**简单插入排序 稳定的排序算法
 * 最好时间复杂度 O(n) 原本有序
 * 最坏时间复杂度 O(n2) 原本逆序
 */
#include <stdio.h> 

void insertSort(int nums[], int size){
    int temp, i, j;
    for(i = 1; i < size; ++i){
        if(nums[i] < nums[i-1]){//若当前元素小于前驱
            temp = nums[i];//记录当前被排序的元素
            for(j = i - 1; j >= 0 && nums[j] > temp; --j)
                nums[j+1] = nums[j];
           nums[j+1] = temp;//复制到插入位置 
        }
    }
}

/**
 * 因为在与前面的元素比较时，前面的元素已经有序
 * 可以使用二分查找
 * 折半插入排序 稳定的排序算法
 * 最坏 时间复杂度 O(n2)
 * 当数据量不大时，效率较高
 */
void bin_insertSort(int nums[], int size){
    int temp, l = -1, r = -1, m = -1;
    for(int i = 1; i < size; ++i){
        if(nums[i] < nums[i-1]){
            temp = nums[i];
            l = 0, r = i - 1;
            while(l <= r){
                m = l + ((r - l) >> 1);
                if(temp >= nums[m])//保证稳定性 当mid == temp
                    l = m + 1;
                else
                    r = m -1;
            }
            for(int j = i - 1; j >= l; --j){
                nums[j+1] = nums[j];
            }
            nums[l] = temp;
        }
    }
}

int main(){
    int a[] = {10,7,3,1,6,3,8,2,4};
    int size = sizeof(a) / sizeof(a[0]), i = 0;
    insertSort(a,size);
    while(i < size)
        printf("%d ", a[i++]);
    printf("\n");
    return 0;
}
