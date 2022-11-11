package com.zyc.offer.dynamicprogramming;

import java.util.Arrays;

/**
 * @author zyc
 * @date 2022/11/11
 */
public class CountBits {

    public static void main(String[] args) {
        CountBits c = new CountBits();
        int[] bits = c.cb(10);
        System.out.println(Arrays.toString(bits));
    }

    public int[] cb(int n) {
        int[] bits = new int[n + 1];
        for (int i = 0; i <= n; ++i) {
            bits[i] = countOnes(i);
        }
        return bits;
    }

    private int countOnes(int x) {
        int s = 0;
        while (x != 0) {
            x = x & (x - 1);
            s++;
        }
        return s;
    }
}
