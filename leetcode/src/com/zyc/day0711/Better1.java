package com.zyc.day0711;
/*
 * 	使用双指针，左指针指向当前已经处理好的序列的尾部，右指针指向待处理序列的头部。

	右指针不断向右移动，每次右指针指向非零数，则将左右指针对应的数交换，同时左指针右移。

	*注意到以下性质：

     1.左指针左边均为非零数；

     2.右指针左边直到左指针处均为零。

	因此每次交换，都是将左指针的零与右指针的非零数交换，且非零数的相对顺序并未改变。
 */
public class Better1 {
	static void moveZeroes(int[] nums) {
		int len = nums.length, l = 0, r = 0, tmp;
		while (r < len) {
			if (nums[r] != 0) {
				tmp = nums[l];
				nums[l] = nums[r];
				nums[r] = tmp;
				l++;
			}
			r++;
		}
	}

	public static void main(String[] args) {
		int nums[] = { 1, 3, 2, 0, 0, 5, 8, 4 };
		moveZeroes(nums);
		for (int n : nums) {
			System.out.print(n + " ");
		}
	}
}
