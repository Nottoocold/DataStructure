package com.zyc.day0712;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
 * 三数之和。
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 */
public class Solution2 {
	static List<List<Integer>> thressSum(int[] nums) {
		if (nums.length == 0 || nums.length == 1 || nums.length == 2) {
			List<List<Integer>> list = new ArrayList<>();
			return list;
		}
		Arrays.sort(nums);// 升序排序数组
		// -4 -1 -1 0 1 2
		List<List<Integer>> ret = new ArrayList<>();
		int third = 0;// 记录第三个数
		int pre = nums[0];// 记录之前一次的第三个数
		for (int i = 0; i < nums.length - 2; ++i) {
			if (i != 0) {// 如果不是第一个元素
				if (nums[i - 1] == nums[i]) {
					continue;
				}
			}
			for (int j = i + 1; j < nums.length; ++j) {
				third = 0 - nums[j] - nums[i];// 算出满足的第三个数
				if (pre != third) {// 若此时的第三个数和前一个不相等，防止重复
					if (third > nums[j]) {// 二分查找
						int index = Arrays.binarySearch(nums, j + 1, nums.length, third);
						if (index > 0) {
							List<Integer> list = new ArrayList<>();
							Collections.addAll(list, nums[i], nums[j], third);
							ret.add(list);
						}
					} else {
						if (j == nums.length - 1) {
							continue;
						} else {// j不是最后一个元素
							if (third == nums[j + 1]) {
								List<Integer> list = new ArrayList<>();
								Collections.addAll(list, nums[i], nums[j], third);
								ret.add(list);
							}
						}
					}
					pre = third;//更新之前的第三个数
				} else// pre!=third
					continue;
			}
		}
		return ret;
	}

	public static void main(String[] args) {
		int[] a = { 0,0,0};
		List<List<Integer>> ret = thressSum(a);
		for (List<Integer> list : ret) {
			for (int i : list) {
				System.out.print(i + " ");
			}
			System.out.println();
		}
	}
}
