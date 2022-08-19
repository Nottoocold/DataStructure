package com.zyc.day0709;

/*
 * 搜索插入位置,查找成功，返回索引，否则返target应插入的位置
 */
public class Solution3 {

    static int searchInsert(int[] nums, int target) {
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
                System.out.println("left is " + left + " right is " + right + " mid is " + mid);
                System.out.println("nums[left] is " + nums[left] + " nums[right] is " + nums[right]);
                return mid;
            }
            mid = (left + right) / 2;
        }
        //查找失败，left就是要插入的索引位置
        System.out.println("left is " + left + " right is " + right);
        if (left < nums.length && right >= 0)
            System.out.println("nums[left] is " + nums[left] + " nums[right] is " + nums[right]);
        if (left > nums.length - 1)
            return nums.length;
        return left;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 4, 6, 8, 10, 11, 13, 15};
        System.out.println("target's index is " + searchInsert(nums, 1));
    }
}
