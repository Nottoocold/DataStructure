package com.zyc.datastructure.sort;

import java.util.Random;

public final class SimpleArraySort {
    private static final Random RANDOM = new Random();

    /**
     * 冒泡排序
     * 设数组有n个元素，则遍历一次数组复杂度是O(n)，需要遍历n-1次
     * 故渐进时间复杂度是O(n^2)<br>
     * 稳定的排序算法
     *
     * @param arr 待排序数组
     */
    public static void bubbleSort(int[] arr) {
        int len = arr.length;
        boolean flag;
        for (int i = len - 1; i > 0; --i) {
            flag = false;
            for (int j = 0; j < i; ++j) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    flag = true;
                }
            }
            if (!flag)
                break;
        }
    }

    /**
     * 快速排序,时间复杂度 O(NlogN),空间复杂度O(logN)
     * 不稳定算法
     *
     * @param arr 待排序数组
     */
    public static void QuickSort(int[] arr) {
        if (arr != null) {
            QuickSort(arr, 0, arr.length - 1);
        }
    }

    public static void qsort(int[] arr) {
        qsort(arr, 0, arr.length - 1);
    }

    /**
     * 时间复杂度最坏O(n^2)
     * 平均时间复杂度O(NlogN)
     *
     * @param arr   待排序数组
     * @param left  左边界
     * @param right 右边界
     */
    public static void qsort(int[] arr, int left, int right) {
        if (left < right) {
            int i = left, j = right, p = arr[i];
            while (i < j) {
                while (i < j && arr[j] > p)
                    --j;
                if (i < j)
                    arr[i++] = arr[j];
                while (i < j && arr[i] < p)
                    ++i;
                if (i < j)
                    arr[j--] = arr[i];
            }
            arr[i] = p;
            qsort(arr, left, i - 1);
            qsort(arr, i + 1, right);
        }
    }

    public static void QuickSort(int[] nums, int left, int right) {
        if (left < right) {
            int pivot_index = partition(nums, left, right);//划分区间
            QuickSort(nums, left, pivot_index - 1);//递归左区间
            QuickSort(nums, pivot_index + 1, right);//递归右区间
        }
    }

    private static int partition(int[] nums, int left, int right) {
        int pivot = nums[left];//取区间左边界元素作为枢轴元素
        while (left < right) {
            while (left < right && nums[right] >= pivot) //找到第一个比枢轴元素的小的元素
                --right;
            nums[left] = nums[right];
            while (left < right && nums[left] <= pivot) //找到第一个比枢轴元素大的元素
                ++left;
            nums[right] = nums[left];
        }
        nums[left] = pivot;
        return left;//返回枢轴元素的索引
    }

    private static int partitionRandom(int[] nums, int left, int right) {
        int idx = left + RANDOM.nextInt(right - left + 1);//随机取一个元素枢轴元素
        swap(nums, left, idx);//将其与区间左边界交换
        int pivot = nums[left];//获取枢轴元素
        int lb = left;//
        //区间 [left,lb]为小于等于pivot的值
        for (int i = left + 1; i <= right; ++i) {
            if (nums[i] <= pivot) {
                lb++;
                swap(nums, lb, i);
            }
        }
        swap(nums, left, lb);
        return lb;
    }

    /**
     * 选择排序：每一轮选择最小元素交换到未排定部分的开头<br/>
     * 贪心思想 只考虑当前
     * 减治思想 外层循环每次都能搞定一个元素 每次问题规模在减小
     * 时间复杂度O(N^2)<br>
     * 不稳定算法
     *
     * @param nums 待排序数组
     */
    public static void SelectSort(int[] nums) {
        int len = nums.length;
        // 循环不变量：[0, i) 有序，且该区间里所有元素就是最终排定的样子
        for (int i = 0; i < len - 1; i++) {
            // 选择区间 [i, len - 1] 里最小的元素的索引，交换到下标 i
            int minIndex = i;
            for (int j = i + 1; j < len; j++) {
                if (nums[j] < nums[minIndex]) {
                    minIndex = j;
                }
            }
            swap(nums, i, minIndex);
        }
    }

    private static void swap(int[] nums, int index1, int index2) {
        if (index1 == index2)
            return;
        nums[index1] = nums[index1] ^ nums[index2];
        nums[index2] = nums[index1] ^ nums[index2];
        nums[index1] = nums[index1] ^ nums[index2];
    }

    /* 插入排序 */

    /**
     * 插入排序,算法思想：<br>
     * 每次将一个待排序记录插入到前面已排好序的子序列<br>
     * 复杂度O(n^2)
     * 稳定排序算法
     * 适合基本有序或数据量不大的序列
     *
     * @param nums 待排序数组
     */
    public static void InsertSort(int[] nums) {
        int len = nums.length;
        for (int i = 1; i < len; ++i) { // nums[0] 假设是已经有序子序列
            int temp = nums[i];//存储到临时变量中
            int j = i;
            while (j > 0 && nums[j - 1] > temp) {
                nums[j] = nums[j - 1];// 为当前待排序记录腾出位置
                --j;
            }
            nums[j] = temp; // 当前待排序记录插入到本次确定的位置
        }
    }

    /**
     * 折半插入排序，在数据量不大的情况下 性能优于直接插入排序
     * 时间复杂度仍为O(n^2)
     *
     * @param nums 待排序数组
     */
    public static void binInsertSort(int[] nums) {
        int len = nums.length;
        int i, j, l, r, m;
        for (i = 1; i < len; ++i) {
            int temp = nums[i];
            l = 0;
            r = i - 1;
            // 二分查找插入位置 二分结束后r的下标就是待插入位置的前驱索引
            while (l <= r) {
                m = (l + r) / 2;
                if (nums[m] > temp)
                    r = m - 1;
                else
                    l = m + 1;
            }
            // 向后搬运元素
            for (j = i - 1; j >= r + 1; --j)
                nums[j + 1] = nums[j];
            // 将temp复制到目标位置
            nums[r + 1] = temp;
        }
    }

    /**
     * 希尔排序 复杂度O(n^2)
     *
     * @param arr 待排序数组
     */
    public static void shellSort(int[] arr) {
        int length = arr.length;
        int temp;
        // dk 步长
        for (int dk = length / 2; dk >= 1; dk /= 2) {
            for (int i = dk; i < length; i++) { // 轮流切换分组进行直接插入排序
                /* 进行直接插入排序 */
                temp = arr[i];
                int j = i - dk; // 前驱元素索引
                while (j >= 0 && arr[j] > temp) {
                    arr[j + dk] = arr[j];
                    j -= dk;
                }
                arr[j + dk] = temp;
            }
        }
    }

    /**
     * 二路归并排序
     *
     * @param arr 待排序数组
     */
    public static void MergeSort(int[] arr) {
        int[] work = new int[arr.length];//工作空间数组
        MergeSort(arr, 0, arr.length - 1, work);
    }

    private static void MergeSort(int[] arr, int left, int right, int[] work) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            MergeSort(arr, left, mid, work);
            MergeSort(arr, mid + 1, right, work);
            if (arr[mid] <= arr[mid + 1])//原本就是有序的
                return;
            MergeTwoArr(arr, left, mid, right, work);
        }
    }

    private static void MergeTwoArr(int[] arr, int left, int mid, int right, int[] work) {
        //拷贝原数组到工作数组
        System.arraycopy(arr, left, work, left, right - left + 1);
        int i = left, j = mid + 1, k;
        for (k = i; i <= mid && j <= right; ++k) {
            //进行归并
            if (work[i] <= work[j]) {
                arr[k] = work[i++];
            } else {
                arr[k] = work[j++];
            }
        }
        while (i <= mid)
            arr[k++] = work[i++];
        while (j <= right)
            arr[k++] = work[j++];
    }

    /**
     * 堆排序 堆是一种特殊的完全二叉树，特殊之处在于任意节点(除根节点外)的关键字都大于等于它的左右孩子<br/>
     * 大根堆 根节点的值大于左右孩子的值 L(i) >= L(2i) 且L(i) >= L(2i+1)
     * 小根堆 根节点的值小于左右孩子的值 L(i) <= L(2i) 且L(i) <= L(2i+1)
     * 由完全二叉树的性质 非终端节点的下标 i <= n/2
     * <p>
     * 一个节点 每下坠一次 最多需要对比关键字2次
     * 若树高h 某节点在第i层 则将次节点进行调整最多只会下坠h-i层 故关键字对比不会超过 2(h-i) 次 而n个节点的完全二叉树树高 h = log2(n) + 1
     * <p>
     * 第i层最多有2^(i-1)个节点 而只有第1~(h-1)层的节点才会被调整 故关键字对比次数不过超 2^(i-1)*2(h-i) 从h-1到1求和 最终结果 <= 4n
     * 故关键字对比次数不超过4n 建堆时间复杂度是O(n)
     * 由headSort算法得出每次交换堆顶和堆底元素后 堆顶的元素需要调整 最多只会下坠h-1层 每次最多对比关键字2次 故时间复杂度为树高 O(h) = O(log2N)
     * 故堆排序算法时间复杂度为O(NlogN)
     * 不稳定算法
     *
     * @param arr 待排序数组
     */
    public static void heapSortAsc(int[] arr) {
        BuildMaxHeap(arr, arr.length - 1);
        for (int i = arr.length - 1; i > 0; i--) {
            swap(arr, 0, i);      //交换堆顶和堆底元素
            MaxHeapAdjust(arr, 0, i - 1); //调整剩余的元素为大根堆
        }
    }

    private static void BuildMaxHeap(int[] arr, int size) {
        for (int i = size / 2; i >= 0; --i) {
            MaxHeapAdjust(arr, i, size);
        }
    }

    //调整以arr[origin]为根的子树为大根堆
    private static void MaxHeapAdjust(int[] arr, int origin, int size) {
        int Root = arr[origin];//暂存根节点元素
        int i;
        for (i = (origin << 1) + 1; i <= size; i = (i << 1) + 1) {
            if (i < size && arr[i] < arr[i + 1]) {// i < size 保证arr[i] 有右兄弟  if若成立则i指向最大孩子的下标
                ++i;
            }
            if (Root >= arr[i]) {//若当前arr[origin]的值大于他最大孩子的值
                break;
            } else {
                arr[origin] = arr[i];//将大孩子向上调整
                origin = i;//origin指向最大孩子下标
            }
        }
        arr[origin] = Root;//将root放入最终位置
    }

    /**
     * 小根堆排序,降序排列
     *
     * @param arr 待排序数组
     */
    public static void heapSortDesc(int[] arr) {
        BuildMinHeap(arr, arr.length - 1);
        for (int i = arr.length - 1; i > 0; --i) {
            swap(arr, 0, i);
            MinHeapAdjust(arr, 0, i - 1);
        }
    }

    public static void BuildMinHeap(int[] arr, int size) {
        for (int i = size / 2; i >= 0; --i) {
            MinHeapAdjust(arr, i, size);
        }
    }

    public static void MinHeapAdjust(int[] arr, int origin, int size) {
        int Root = arr[origin];//暂存根节点元素
        int i;
        for (i = (origin << 1) + 1; i <= size; i = (i << 1) + 1) {
            if (i < size && arr[i] > arr[i + 1]) {// i < size 保证arr[i] 有右兄弟  if若成立则i指向最小孩子的下标
                ++i;
            }
            if (Root <= arr[i]) {//若当前arr[origin]的值小于他最小孩子的值
                break;
            } else {
                arr[origin] = arr[i];//将小孩子向上调整
                origin = i;//origin指向最小孩子下标
            }
        }
        arr[origin] = Root;//将Root放入最终位置
    }
}
