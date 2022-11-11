package com.zyc.offer.dynamicprogramming;

/**
 * 青蛙跳台问题
 *
 * @author zyc
 * @date 2022/11/11
 */
public class ClimbStairs {

    public static void main(String[] args) {
        ClimbStairs stairs = new ClimbStairs();
        int sum = stairs.climbStairs(45);
        System.out.println(sum);
    }

    public int climbStairs(int n) {
        if (n == 1)
            return 1;
        if (n == 2)
            return 2;
        int x = 1, y = 2;
        int[] dp = new int[n + 1];
        for (int i = 3; i <= n; ++i) {
            dp[0] = y;
            dp[i] = x + y;
            x = dp[0];
            y = dp[i];
        }
        return dp[n];
    }
}
