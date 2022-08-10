package com.zyc.binarysearch;

/**
 * @see Solution4.java
 * @author zyc
 */
public class Solution2 {
	/**
	 * 二分查找
	 * 
	 * @param nums
	 * @param key
	 * @return 没找到返回-1
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

	/**
	 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转， 使数组变为 [nums[k],
	 * nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。
	 * 例如，[0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2]
	 * 
	 * 给你旋转后的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1
	 * 
	 * @param nums
	 * @param target
	 * @return
	 */
	public static int search(int[] nums, int target) {
		int low = 0, high = nums.length - 1;
		while (low <= high) {
			int mid = (low + high) / 2;
			if (nums[mid] == target)
				return mid;
			else if (nums[mid] < nums[high]) {// 数组右半部分是有序的
				if (nums[mid] < target && target <= nums[high])// 若target落在右半部分的有序区间中
					return binarySearch(nums, target, mid, high);
				else
					high = mid - 1;

			} else {// else if //数组左半部分是有序的
				if (nums[low] <= target && target < nums[mid])
					return binarySearch(nums, target, 0, mid);
				else
					low = mid + 1;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		int[] nums1 = { 10, 1, 2, 4, 5, 6, 7, 8, 9 };
		int[] nums2 = { 6, 7, 8, 9, 10, 1, 2, 4, 5 };
		int[] nums3 = { 34, 35, 36, 40, 50, 3, 23, 24, 25 };
		System.out.println(search(nums1, 4));
		System.out.println(search(nums2, 1));
		System.out.println(search(nums3, 24));
	}
}
