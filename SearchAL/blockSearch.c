/*分块查找*/
#include <stdio.h>
typedef int ElemType;

//索引表节点结构
typedef struct {
    ElemType maxKey;//块内最大关键字
    int start, end;//块起始索引&结束索引
}Index;

ElemType List[100];//顺序表

/* 索引表可采用二分查找,low,high不越界的情况下,当索引表中关键字与key不同时,一定是在low指向的块内查找key
 * 因为当low=high时,mid分为两种情况:
 * 1.mid > key, high = mid-1;
 * 2.mid < key, low = mid+1;
 * 索引当查找失败时，一定是low>high,即mid>key,而索引表中关键字时块中最大值,所以要在low所指块内继续查找
 * /
