package com.zyc.day0711;

import java.util.LinkedList;

/*
	给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。

	请注意 ，必须在不复制数组的情况下原地对数组进行操作。
 */
public class Solution1 {

	public static void moveZeroes(int[] nums) {
		if(nums.length == 1) {
			return;
		}
		LinkedList<Integer> list = new LinkedList<>();
		for(int i : nums) {
			if(i != 0) {
				list.addLast(i);
			}
		}
		if(list.size() < nums.length) {
			int i = 0;
			while(list.size() != 0) {
				nums[i] = list.removeFirst();
				i++;
			}
			while(i < nums.length) {
				nums[i] = 0;
				i++;
			}
		}
    }
	
	public static void main(String[] args) {
		int nums[] = {0,1,0,3,12};
		moveZeroes(nums);
		for(int i: nums) {
			System.out.print(i+" ");
		}
	}
}
