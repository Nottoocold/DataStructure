package com.zyc.offer.dynamicprogramming;

public class Fibonacci {
    final int M = 1000000007;

    /**
     * 斐波那契数列
     *
     * @param n n阶
     * @return 返回斐波那契数
     */
    public int fib(int n) {
        if (n == 0 || n == 1)
            return n;
        int a, b = 0, r = 1;
        // 递归超出时间限制 使用循环求余法
        /*
            求余运算规则：设正整数 x, y, p,求余符号为 ⊙ ，则有 (x + y) ⊙ p = (x ⊙ p + y ⊙ p) ⊙ p = (x ⊙ p + y ⊙ p ) ⊙ p
            所以 f(n) ⊙ p = [f(n-1) ⊙ p + f(n-2) ⊙ p] ⊙ p
         */
        for (int i = 2; i <= n; ++i) {
            a = b;
            b = r;
            r = (a + b) % M;
        }
        return r;
    }

    /**
     * 青蛙跳台问题
     *
     * @param n
     * @return
     */
    public int numWays(int n) {
        if (n == 0 || n == 1)
            return 1;
        int _n_1 = 2, _n_2 = 1, N = 0;
        for (int i = 3; i <= n; i++) {
            N = (_n_1 + _n_2) % M;
            _n_2 = _n_1;
            _n_1 = N;
        }
        return N;
    }
}
