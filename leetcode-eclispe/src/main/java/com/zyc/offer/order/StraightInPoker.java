package com.zyc.offer.order;

import com.zyc.sort.MyArrays;

public class StraightInPoker {

    /**
     * 判断给定的数组是不是顺子<p>
     * 从若干副扑克牌中随机抽 5 张牌，判断是不是一个顺子，
     * 即这5张牌是不是连续的。2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王为 0 ，可以看成任意数字。A 不能视为 14。
     *
     * @param nums 带判定的5张牌
     * @return 如果是顺子返回true
     */
    public boolean isStraight(int[] nums) {
        MyArrays.MergeSort(nums);
        int zero = 0, gap = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i]==0) {
                ++zero;
            } else {
                // 连续两张同张牌
                if (nums[i]==nums[i + 1]) {
                    return false;
                }
                // 前后两张牌不一样 计算差值 如: 2,5 -> (5-2)-1 = 2
                if (nums[i]!=nums[i + 1]) {
                    gap += (nums[i + 1] - nums[i] - 1);
                }
            }
        }
        // 万能牌0的个数大于差值
        return zero >= gap;
    }
}
