package com.zyc.binarysearch;

import java.util.Arrays;

public class Solution1 {
	/**
	 * 二分查找
	 * @param nums
	 * @param key
	 * @return 没找到返回-1
	 */
	public static int binarySearch(int[] nums, int key) {
		int low = 0, high = nums.length - 1, mid = -1;
		while(low <= high) {
			mid = low + (high - low) / 2;
			if(nums[mid] == key)
				return mid;
			else if(nums[mid] > key)
				high = mid - 1;
			else 
				low = mid + 1;
		}
		return -1;
	}
	
	/**
	 * 在给定非降序数组nums和目标值target中找出target出现的开始位置和结束位置
	 * @param nums 目标数组
	 * @param target 目标值
	 * @return 若存在目标值返回索引数组 否则返回[-1,-1]
	 */
	public static int[] searchRange(int[] nums, int target) {
		int index;
		index = binarySearch(nums, target);
		if (index == -1) {//数组中不存在target
			return new int[] {-1,-1};
		}
		//存在target
		int left = index, right = index;
		while(left - 1 >= 0 && nums[left - 1] == target)//向左边找
			--left;
		while(right + 1 < nums.length && nums[right + 1] == target)//向右边找
			++right;
		return new int[] {left,right};
    }
	
	public static void main(String[] args) {
		int[] nums = {5,7,7,8,8,8,8,8,8,10};int target = 8;
		System.out.println(Arrays.toString(searchRange(nums, target)));
	}
}
