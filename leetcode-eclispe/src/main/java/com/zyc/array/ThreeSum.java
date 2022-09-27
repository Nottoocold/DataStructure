package com.zyc.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
 * 三数之和。
 * 给你一个包含 n 个整数的数组nums，判断nums中是否存在三个元素 a，b，c ，使得a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 */
public class ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {
        if (nums.length == 0 || nums.length == 1 || nums.length == 2) {
            return new ArrayList<>();
        }
        Arrays.sort(nums);// 快速排序
        // -4 -1 -1 0 1 2
        List<List<Integer>> ret = new ArrayList<>();
        int third;// 记录第三个数
        for (int i = 0; i < nums.length - 2; ++i) {// 枚举第一个数
            if (i > 0 && nums[i] == nums[i - 1])// 防止此次枚举的i和上一次相等
                continue;
            for (int j = i + 1; j < nums.length; ++j) {// 枚举第二个数
                if (j > i + 1 && nums[j] == nums[j - 1])// 防止此次枚举的j和上一次相等
                    continue;
                third = -nums[j] - nums[i];// 算出满足的第三个数
                int index = Arrays.binarySearch(nums, j + 1, nums.length, third);
                if (index > 0) {
                    List<Integer> list = new ArrayList<>();
                    Collections.addAll(list, nums[i], nums[j], third);
                    ret.add(list);
                }
            }
        }
        return ret;
    }
}
