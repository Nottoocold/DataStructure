package com.zyc.day0626;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {

    /**
     * 求数组中两个和为target的索引
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{i, map.get(target - nums[i])};
            }
            map.put(nums[i], i);
        }
        return new int[0];
    }

    /**
     * 输入一个递增排序的数组和一个数字s，在数组中查找两个数，使得它们的和正好是s。如果有多对数字的和等于s，则输出任意一对即可。
     *  双指针法
     * @param nums   非递减数组
     * @param target 目标值
     * @return 返回其中那一对数字
     */
    public int[] twoSum0(int[] nums, int target) {
        // 因为是排序数组
        int i, j;
        for (i = 0, j = nums.length - 1; i != j; ) {
            int sum = nums[i] + nums[j];
            if (sum < target) i++;
            else if (sum > target) j--;
            else return new int[]{nums[i], nums[j]};
        }
        return new int[0];
    }

    /**
     * 二分法
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum2(int[] nums, int target) {
        int i;
        for (i = 0; i < nums.length - 1; i++) {
            int other = target - nums[i];
            if (other < nums[i]) continue;
            int index = Arrays.binarySearch(nums, i + 1, nums.length, other);
            if (index > 0)
                return new int[]{nums[i], nums[index]};
        }
        return new int[0];
    }

}
