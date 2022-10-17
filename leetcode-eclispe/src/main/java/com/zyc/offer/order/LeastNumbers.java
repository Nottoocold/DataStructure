package com.zyc.offer.order;

import com.zyc.datastructure.queue.MyPriorityQueue;

public class LeastNumbers {

    /**
     * 获取nums数组中最小的k个数
     *
     * @param nums 待排序数组
     * @param k    最前的k个数
     * @return k个数组成的数组
     */
    public int[] getLeastNumbers(int[] nums, int k) {
        if (k == 0) {
            return new int[k];
        }
        MyPriorityQueue queueMax = new MyPriorityQueue(k);
        for (int i = 0; i < k; i++) {
            queueMax.add(nums[i]);
        }
        for (int i = k; i < nums.length; ++i) {
            if (queueMax.peek() > nums[i]) {
                queueMax.poll();
                queueMax.add(nums[i]);
            }
        }
        int[] ret = new int[k];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = queueMax.poll();
        }
        return ret;
    }

}
