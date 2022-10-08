package com.zyc.array;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * @author zyc
 * @date 2022/10/8
 */
public class MaxTemperature {
    static final MaxTemperature in = new MaxTemperature();

    public static void main(String[] args) {
        int[] arr = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] results = in.dailyTemperatures(arr);
        int[] res = in.byStack(arr);
        System.out.println(Arrays.toString(results));
        System.out.println(Arrays.toString(res));
    }

    /**
     * @param temperatures 温度数组
     * @return answer[i] 代表对于第i天 下一个更高温出现在几天后
     */
    public int[] dailyTemperatures(int[] temperatures) {
        if (temperatures.length == 1) {
            return new int[]{0};
        }
        int[] answer = new int[temperatures.length];
        for (int i = 0; i < temperatures.length; ++i) {
            for (int j = i + 1; j < temperatures.length; ++j) {
                if (temperatures[j] > temperatures[i]) {
                    answer[i] = j - i;
                    break;
                }
            }
        }
        return answer;
    }

    public int[] byStack(int[] t) {
        Deque<Integer> stack = new ArrayDeque<>();
        int[] result = new int[t.length];
        for (int i = 0; i < t.length; ++i) {
            while (!stack.isEmpty() && t[i] > t[stack.peek()]) {
                int j = stack.pop();
                result[j] = i - j;
            }
            stack.push(i);
        }
        return result;
    }
}
