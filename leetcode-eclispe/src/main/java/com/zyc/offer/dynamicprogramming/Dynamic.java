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
     *
     * @param prices 按时间顺序的价格数组
     * @return [7, 1, 5, 3, 6, 4]
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

    /**
     * 在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。<br>
     * 你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直到到达棋盘的右下角。<br>
     * 给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？
     * 设 f(i, j)为从棋盘左上角走至单元格 (i,j) 的礼物最大累计价值，<br>
     * 易得到以下递推关系：f(i,j) 等于 f(i,j-1) 和 f(i-1,j) 中的较大值加上当前单元格礼物价值 grid(i,j)
     *
     * @param grid
     * @return 礼物的最大价值
     */
    public int maxValue(int[][] grid) {
        if (grid == null || grid.length == 0)
            return 0;
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) continue;
                if (i == 0) grid[i][j] += grid[i][j - 1];
                else if (j == 0) grid[i][j] += grid[i - 1][j];
                else grid[i][j] += Math.max(grid[i][j - 1], grid[i - 1][j]);
            }
        }
        return grid[m - 1][n - 1];
    }

}
