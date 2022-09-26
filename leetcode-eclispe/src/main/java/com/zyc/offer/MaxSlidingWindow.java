package com.zyc.offer;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;

public class MaxSlidingWindow {

    public int[] maxWindow(int[] nums, int k) {
        if (nums.length == 0 || k == 0) {
            return new int[0];
        }

        Deque<Integer> deque = new ArrayDeque<>();
        int[] ret = new int[nums.length - k + 1];

        // 先遍历前k个元素
        for (int i = 0; i < k; ++i) {
            while (!deque.isEmpty() && deque.peekLast() < nums[i]) {
                deque.removeLast();
            }
            deque.addLast(nums[i]);
        }
        ret[0] = Objects.requireNonNull(deque.peekFirst());

        for (int i = k; i < nums.length; ++i) {
            // 窗口已经右移，则之前窗口左边界nums[i-k]的值需要出队
            // 又因为在入队时会将比入队元素都小的元素全部出队 所以不一定会出队成功
            if (Objects.requireNonNull(deque.peekFirst()) == nums[i - k])
                deque.removeFirst();
            // 当队不为空 且队尾元素小于当前右边界的值 则出队
            while (!deque.isEmpty() && deque.peekLast() < nums[i])
                deque.removeLast();
            // 最终将新的右边界的值入队
            deque.addLast(nums[i]);
            // 将队首元素(当前窗口中的最大值)
            ret[i - k + 1] = Objects.requireNonNull(deque.peekFirst());
        }
        return ret;
    }

}
