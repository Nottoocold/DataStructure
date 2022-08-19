package com.zyc.utils;

public class Utils {

    /*
     * 包括左右指针 找不到返回-1
     */
    public static int binarySearch(int[] nums, int key, int left, int right) {
        int low = left, high = right, mid = -1;
        while (low <= high) {
            mid = low + (high - low) / 2;
            if (nums[mid] == key)
                return mid;
            else if (nums[mid] > key)
                high = mid - 1;
            else
                low = mid + 1;
        }
        return -1;
    }
}
