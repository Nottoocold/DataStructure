package com.zyc.offer;

import com.zyc.sort.MyArrays;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
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
     *
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

    /**
     * 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。<br>
     * 请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
     *
     * @param matrix 待处理二维数组
     * @param target 目标值
     * @return 若存在目标值返回true
     */
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        int row = matrix.length;
        for (int[] nums : matrix) {
            int index = binSearch(nums, target);
            if (index >= 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 求旋转数组的最小值<br>
     * 数组长度 1<=len<=5000,数组中有重复值
     *
     * @param arr 已经过1-n次的旋转的数组
     * @return 数组中的最小值
     */
    public int minArray(int[] arr) {
        int i = 0, j = arr.length - 1, mid;
        while (i < j) {
            mid = i + ((j - i) >> 1);
            if (arr[mid] < arr[j]) {
                j = mid;
            } else if (arr[mid] > arr[j]) {
                i = mid + 1;
            } else {
                j = j - 1;
            }
        }
        return arr[i];
    }

    /**
     * 在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。
     *
     * @param s 待处理字符串 0<=len<=50000
     * @return 返回第一个只出现一次的字符
     */
    public char firstUniqChar(String s) {
        char[] charArray = s.toCharArray();
        int[] tmp = new int[26];
        for (char c : charArray) {
            tmp[c - 'a']++;
        }
        for (char c : charArray) {
            if (tmp[c - 'a'] == 1)
                return c;
        }
        return ' ';
    }

}
