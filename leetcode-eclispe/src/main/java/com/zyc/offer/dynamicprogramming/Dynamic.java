package com.zyc.offer.dynamicprogramming;

public class Dynamic {

    /**
     * 动态规划
     * 53.最大子数组和
     *
     * @param arr 带规划数组
     * @return 最大和
     */
    public int maxSubArr(int[] arr) {
        if (arr == null)
            return 0;
        if (arr.length == 1) {
            return arr[0];
        }
        int curSum = arr[0];
        int maxSum = arr[0];
        int len = arr.length;
        for (int i = 1; i < len; i++) {
            curSum = Math.max(arr[i], (curSum + arr[i]));//若 (原先的和 + 当前值) 反而比当前值更小，则舍弃原先的和,更新当前值为当前和
            maxSum = Math.max(curSum, maxSum); // 判断当前和是否比最大和还大
        }
        return maxSum;
    }

    /**
     * 买卖股票的最佳时机
     * @param prices 按时间顺序的价格数组
     * @return [7,1,5,3,6,4]
     */
    public int maxProfit(int[] prices) {
        int min_price = prices[0];// 初始化最低价格
        int max = 0; // 初始化最大利润
        for (int i = 1; i < prices.length; ++i) {
            if (prices[i] < min_price) {
                min_price = prices[i];
            } else if (prices[i] - min_price > max) {
                max = prices[i] - min_price;
            }
        }
        return max;
    }
}
