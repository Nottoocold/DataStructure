package com.zyc.p;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author zyc
 * @package: com.zyc.p
 * @description:
 * @create_time 2022/11/29 - 周二
 */
public class One {

    /**
     * 217.存在重复元素
     * 方法一，排序
     *
     * @param nums 数组
     * @return 存在返回true, 否则返回false
     */
    public boolean containsDuplicate_1(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        for (int i = 0; i < len - 1; i++) {
            if (nums[i] == nums[i + 1])
                return true;
        }
        return false;
    }

    // 方法二，哈希表
    public boolean containsDuplicate_2(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (!set.add(num))
                return true;
        }
        return false;
    }

    /**
     * 53.最大连续子数组的和
     * 动态规划
     *
     * @param nums 数组
     * @return 最大和
     */
    public int maxSubArray_1(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];
        dp[0] = nums[0];
        for (int i = 1; i < len; i++) {
            if (dp[i - 1] > 0)
                dp[i] = dp[i - 1] + nums[i];
            else
                dp[i] = nums[i];
        }
        int r = dp[0];
        for (int i = 1; i < len; ++i) {
            r = Math.max(r, dp[i]);
        }
        return r;
    }

    public int maxSubArray_2(int[] nums) {
        int pre = 0; // 前述和 初始值为0
        int max = nums[0]; // 最大和 初始值为nums[0]
        for (int n : nums) {
            pre = Math.max(n, pre + n);// 当前值和(前述和+当前值)取最大
            max = Math.max(pre, max); // 再次比较取最大
        }
        return max;
    }

}
