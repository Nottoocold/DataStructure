package com.zyc.binarysearch;

public class Solution4 {
	
	/**
	 * 数组原来非递减顺序排列，现进行了旋转之后作为输入数组
	 * @param nums
	 * @return 返回数组中最小值
	 */
	public static int findMin(int[] nums) {
		int len = nums.length;
		int left = 0, right = len-1;
		while(left < right) {
			int mid = left + (right-left)/2;
			if (nums[mid] < nums[right]) {
				right = mid;
			}else {
				left = mid+1;
			}
		}
		return nums[left];
	}
	
	public static void main(String[] args) {
		int nums[] = {3,4,6,1,2};
		System.out.println(findMin(nums));
	}
}
