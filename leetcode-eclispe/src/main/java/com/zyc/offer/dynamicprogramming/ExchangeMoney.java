package com.zyc.offer.dynamicprogramming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zyc
 * @date 2022/11/9
 */
public class ExchangeMoney {

    private static final Map<Integer, Integer> map = new HashMap<>();

    public static int exchange(int[] coins, int amount) {
        if (map.containsKey(amount))
            return map.get(amount);
        // 刚好可以
        if (amount == 0)
            return 0;
        if (amount < 0)
            return -1;
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int sub = exchange(coins, amount - coin);
            if (sub == -1) continue;
            min = Math.min(min, sub + 1);
        }
        if (min == Integer.MAX_VALUE)
            return -1;
        map.put(amount, min);
        return min;
    }

    private static int exchangeDp(int[] coins, int amount) {
        // 金额数据
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, 1, dp.length, amount + 1);
        // 自底向上遍历每一个子总金额 i
        for (int i = 1; i <= amount; i++) {
            // 枚举每一个面值的硬币 f(i) = min( f(i-1) + f(i-2) + f(i-5)) + 1;
            for (int coin : coins) { // 比较三次取到最小值
                if (coin <= i) // 若面值小于等于当前金额
                    /*
                        dp[i]表示金额为i时需要的最小硬币数量，每轮第一次比较时都是amount+1
                        dp[i - coin] 表示当前金额减去当前枚举的硬币面额后所需金额的最小硬币数量,值为0时表示刚好，不多不少
                     */
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int amount = 11;
        int min = exchange(coins, amount);
        int dp = exchangeDp(coins, amount);
        System.out.println(min);
        System.out.println(dp);
    }
}
