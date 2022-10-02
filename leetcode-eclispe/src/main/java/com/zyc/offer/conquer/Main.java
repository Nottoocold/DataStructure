package com.zyc.offer.conquer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zyc
 * @date 2022/10/2
 */
public class Main {

    public double pow(double x, int n) {
        if (x == 0.0f) {
            return 0.0d;
        }
        // n 取反可能会上溢
        long y = n;
        double result = 1.0;
        // 指数为负数
        if (y < 0) {
            x = 1 / x;
            y = -y;
        }
        while (y > 0) {
            // 指数为奇数 将多出的一项直接乘入结果中
            if ((y & 1) == 1) {
                result *= x;
            }
            x *= x;
            y >>= 1;
        }
        return result;
    }

    /**
     * 打印1到最大的n位数
     *
     * @param n 正整数
     * @return 结果列表
     */
    public List<Integer> printNumbers(int n) {
        int bound = (int) (pow(10, n) - 1);
        List<Integer> list = new ArrayList<>();
        int i = 1;
        while (i <= bound)
            list.add(i++);
        return list;
    }
}
