package com.zyc.array;

import java.util.ArrayDeque;
import java.util.Deque;

/*
	给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。

	请注意 ，必须在不复制数组的情况下原地对数组进行操作。
 */
public class MoveZeros {

    public void moveZeroes(int[] nums) {
        if (nums.length == 1) {
            return;
        }
        Deque<Integer> list = new ArrayDeque<>();
        for (int i : nums) {
            if (i != 0) {
                list.addLast(i);
            }
        }
        if (list.size() < nums.length) {
            int i = 0;
            while (list.size() != 0)
                nums[i++] = list.removeFirst();
            while (i < nums.length)
                nums[i++] = 0;

        }
    }

    public void moveZeroes_1(int[] nums) {
        int len = nums.length, l = 0, r, tmp;
        while (l < len && nums[l] != 0) {
            ++l;
        }
        r = l + 1;
        while (r < len && nums[r] == 0) {
            ++r;
        }
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

}
