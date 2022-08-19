package com.zyc.day0709;

/*
 * 二分查找，
 */
public class Solution1 {

    public static int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int mid = (left + right) / 2;
        while (left <= right) {
            if (target < nums[mid]) {
                right = mid - 1;
            }
            if (target > nums[mid]) {
                left = mid + 1;
            }
            if (target == nums[mid]) {
                return mid;
            }
            mid = (left + right) / 2;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-1, 0, 3, 5, 9, 12};
        System.out.println(search(nums, 2));
    }
}
