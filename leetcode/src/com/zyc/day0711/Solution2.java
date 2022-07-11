package com.zyc.day0711;

import java.util.Arrays;

/*
 * 	给你一个下标从 1 开始的整数数组 numbers ，该数组已按 非递减顺序排列  ，请你从数组中找出满足相加之和等于目标数 target 的两个数。
 * 	如果设这两个数分别是 numbers[index1] 和 numbers[index2] ，则 1 <= index1 < index2 <= numbers.length 。

	以长度为 2 的整数数组 [index1, index2] 的形式返回这两个整数的下标 index1 和 index2。
	
	你可以假设每个输入 只对应唯一的答案 ，而且你 不可以 重复使用相同的元素。
	
	你所设计的解决方案必须只使用常量级的额外空间。
 */

public class Solution2 {

	static int[] twoSum(int[] numbers, int target) {
		int i = 0, index1 = -1, index2 = -1;
		int[] ret = new int[] { index1, index2 };
		while (i < numbers.length) {
			int k = target - numbers[i];
			if (k >= numbers[i]) {
				index2 = Arrays.binarySearch(numbers, i + 1, numbers.length, k);// [left,right)
				if (index2 > 0) {
					ret[0] = i + 1;
					ret[1] = index2 + 1;
					return ret;
				}
			} else {
				index1 = Arrays.binarySearch(numbers, 0, i, k);
				if (index1 > 0) {
					ret[0] = index1 + 1;
					ret[1] = i + 1;
					return ret;
				}
			}
			i++;
		}
		return ret;
	}

	public static void main(String[] args) {
		int[] nums = { 0, 0, 3, 4 };
		nums = twoSum(nums, 0);
		System.out.println(Arrays.toString(nums));
	}

}
