/**
 * 堆排序 在完全二叉树中
 * 大根堆 根节点的值大于左右孩子的值 L(i) >= L(2i) 且L(i) >= L(2i+1)
 * 小根堆 根节点的值小于左右孩子的值 L(i) <= L(2i) 且L(i) <= L(2i+1)
 * 由完全二叉树的性质 非终端节点的下标 i <= n/2
 *
 * 一个节点 每下坠一次 最多需要对比关键字2次
 * 若树高h 某节点在第i层 则将次节点进行调整最多只会下坠h-i层 故关键字对比不会超过 2(h-i) 次 而n个节点的完全二叉树树高 h = log2(n) + 1
 *
 * 第i层最多有2^(i-1)个节点 而只有第1~(h-1)层的节点才会被调整 故关键字对比次数不过超 2^(i-1)*2(h-i) 从h-1到1求和 最终结果 <= 4n
 * 故关键字对比次数不超过4n 建堆时间复杂度是O(n)
 * 由headSort算法得出每次交换堆顶和堆底元素后 堆顶的元素需要调整 最多只会下坠h-1层 每次最多对比关键字2次 故时间复杂度为树高 O(h) = O(log2N)
 * 故堆排序算法时间复杂度为O(NlogN)
 * 不稳定算法
 */
#include <stdio.h>
typedef int ElemType;

//调整以root为根的子树为大根堆
void HeadAdjust(ElemType A[], ElemType root, int size)
{
    A[0] = A[root];                           // A[0]暂存待调整的根节点元素
    for (int i = root * 2; i <= size; i *= 2) //从root较大的孩子向下调整
    {
        if (i < size && A[i] < A[i + 1])
            ++i; //筛选root的左右孩子较大者
        if (A[0] >= A[i])
            break; // root比左右孩子都大
        else
        {
            A[root] = A[i]; //将较大的孩子往上调整
            root = i;       // root指向新的根节点 继续向下遍历
        }
    }
    A[root] = A[0]; //将A[0]放入最终位置
}

//建立大根堆
void buildMaxHeap(ElemType A[], int size)
{
    int i = size / 2;
    for (; i > 0; --i)
        HeadAdjust(A, i, size);
}

void swap(ElemType *const x, ElemType *const y)
{
    ElemType t = *x;
    *x = *y;
    *y = t;
}

void headSort(ElemType A[], int size)
{
    buildMaxHeap(A, size);
    for (int i = size; i > 1; i--)
    {
        swap(&A[1], &A[i]);      //交换堆顶和堆底元素
        HeadAdjust(A, 1, i - 1); //调整剩余的元素为大根堆
    }
}

int main()
{
    ElemType a[] = {-1, 10, 4, 9, 7, 2, 1, 8, 5};
    int size = (sizeof(a) / sizeof(a[0])) - 1, i = 1;
    headSort(a, size);
    while (i <= size)
    {
        printf("%d ", a[i++]);
    }
    printf("\n");
    return 0;
}
