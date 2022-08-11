package com.zyc.binarysearch;

public class Solution5 {
	
	/*
	 * 寻找峰值元素,返回下标
	 */
	public static int findPeakElement(int[] nums) {
		int left = 0, right = nums.length - 1;
		while (left < right) {
			int mid = left + (right - left) / 2;
			if (nums[mid] < nums[mid+1]) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		return left;
	}
	
	public static void main(String[] args) {
		int nums1[] = {1,2,1,3,5,6,4};
		int nums2[] = {1,2,3,1};
		System.out.println(findPeakElement(nums1));
		System.out.println(findPeakElement(nums2));
	}
}
