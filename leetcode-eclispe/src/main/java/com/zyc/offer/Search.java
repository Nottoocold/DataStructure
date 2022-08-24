package com.zyc.offer;

import com.zyc.sort.MyArrays;

import java.util.HashSet;
import java.util.Set;

public class Search {

    /**
     * 给定一数组 找出数组中任意一个重复的数字<br>
     * 数组长度 2<=len<=100000<br>
     * 数组中数字范围在 [0, len-1]
     *
     * @param nums 待查找数组
     * @return 任意一个重复的数字
     */
    public int findRepeatNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (!set.add(num))
                return num;
        }
        return -1;
    }

    public int findRepeat(int[] arr) {
        int len = arr.length;
        for (int i = 0; i < len; ) {
            if (i == arr[i]) {
                ++i;
                continue;
            }
            if (arr[i] == arr[arr[i]]) {
                return arr[i];
            }
            int tmp = arr[i];
            arr[i] = arr[tmp];
            arr[tmp] = tmp;
        }
        return -1;
    }

    public int findRepeatBySort(int[] arr) {
        MyArrays.QuickSort(arr);
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] == arr[i + 1])
                return arr[i];
        }
        return -1;
    }

    /**
     * arr是一个已经排序的数组
     *
     * @param arr    非递减数组
     * @param target 目标数字
     * @return 返回target出现的次数
     */
    public int searchCount(int[] arr, int target) {
        int index = binSearch(arr, target);
        if (index < 0) {
            return 0;
        }
        int count = 0, i = index - 1;
        while (i >= 0 && arr[i] == arr[index]) {
            count++;
            i--;
        }
        i = index + 1;
        while (i < arr.length && arr[i] == arr[index]) {
            count++;
            i++;
        }
        return count + 1;
    }

    public static int binSearch(int[] arr, int key) {
        int left = 0, right = arr.length - 1, mid;
        while (left <= right) {
            mid = left + ((right - left) >> 1);
            if (arr[mid] == key)
                return mid;
            else if (key <= arr[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -(left + 1);
    }

    /**
     * 一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。<br>
     * 在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字。<br>
     * e.g. arr = {0,1,2,3,4,5,7} return 6
     * @param arr 待处理数组
     * @return 返回数组中确实的数字
     */
    public int missingNumber(int[] arr) {
        int left = 0, right = arr.length - 1, mid;
        while (left <= right) {
            mid = left + ((right - left) >> 1);
            if (arr[mid] == mid) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

}
