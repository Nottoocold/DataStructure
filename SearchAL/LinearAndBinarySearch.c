#include <stdio.h>

typedef int ElemType;

typedef struct {
    ElemType *data;
    int length;
}LinearList;

//时间复杂度O(n)
int Seq_Search(LinearList L, ElemType key){
    L.data[0] = key;//哨兵
    for(int i = L.length; L.data[i] != key; --i)
    return i;
}
/*优化思路,可以先将线性表有序排列*/


//二分查找,升序排列线性表,时间复杂度O(logN)
int Binary_Search(LinearList L, ElemType key){
    int low = 0, high = L.length, mid = 0;
    while(low <= high){
        mid  = low + (high - low) / 2;
        if(L.data[mid] == key)
            return mid;
        else if(L.data[mid] > key)
            high = mid - 1;
        else
            low = mid + 1;
    }
    return -1;
}
