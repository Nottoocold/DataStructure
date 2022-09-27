package com.zyc.binarysearch;

/*
 * 搜索插入位置,查找成功，返回索引，否则返target应插入的位置
 */
public class SearchInsert {

    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int mid = (left + right) / 2;
        while (left <= right) {
            if (target < nums[mid]) {
                right = mid - 1;
            }
            if (target > nums[mid]) {
                left = mid + 1;
            }
            if (target == nums[mid])
                return mid;
            mid = (left + right) / 2;
        }
        //查找失败，left就是要插入的索引位置
        if (left > nums.length - 1)
            return nums.length;
        return left;
    }

}
