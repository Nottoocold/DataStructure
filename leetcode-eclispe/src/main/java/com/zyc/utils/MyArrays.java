package com.zyc.utils;

import java.util.Random;

public final class MyArrays {
    private static final Random RANDOM = new Random();

    /**
     * 快速排序,时间复杂度 O(NlogN),空间复杂度O(logN)
     *
     * @param arr 待排序数组
     */
    public static void QuickSort(int[] arr) {
        if (arr != null) {
            QuickSort(arr, 0, arr.length - 1);
        }
    }

    private static void QuickSort(int[] nums, int left, int right) {
        if (left < right) {
            int pivot_index = Partition_2(nums, left, right);//划分区间
            QuickSort(nums, left, pivot_index - 1);//递归左区间
            QuickSort(nums, pivot_index + 1, right);//递归右区间
        }
    }

    //不等概率选取枢轴元素的话效率会变低
    private static int Partition(int[] nums, int left, int right) {
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

    private static int Partition_2(int[] nums, int left, int right) {
        int idx = left + RANDOM.nextInt(right - left + 1);//随机取一个元素
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
     * 时间复杂度O(N^2)
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

    /**
     * 插入排序
     *
     * @param nums 待排序数组
     */
    public static void InsertSort(int[] nums) {
        int len = nums.length;
        for (int i = 1; i < len; ++i) {
            int temp = nums[i];//存储到临时变量中
            int j = i;
            while (j > 0 && nums[j - 1] > temp) {
                nums[j] = nums[j - 1];
                --j;
            }
            nums[j] = temp;
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

    //int[] arr = {2, 4, 7, 1, 6, 9, 0};
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
}
