package com.zyc.offer.dynamicprogramming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zyc
 * @date 2022/11/9
 */
public class ShortestPathOfTriangle {

    private static final int[][] triangle = {
            {2, 0, 0, 0},
            {3, 4, 0, 0},
            {6, 5, 7, 0},
            {4, 1, 8, 3}
    };

    private static final Map<String, Integer> map = new HashMap<>((1 + triangle.length) * triangle.length / 2);

    private static int traverse(int i, int j, int totalRow) { // 递归+减枝 解法
        String key = i + "" + j;
        if (map.containsKey(key))
            return map.get(key);
        if (i >= totalRow - 1)
            return 0;
        // 左下
        int left_sum = traverse(i + 1, j, totalRow) + triangle[i + 1][j];
        // 右下
        int right_sum = traverse(i + 1, j + 1, totalRow) + triangle[i + 1][j + 1];
        int result = Math.min(left_sum, right_sum);
        map.put(key, result);
        // 取最小
        return result;
    }

    private static int traverse(int row) {
        /*
            mini数组变化：
                11
                9,  10
                7,  6,  10
                4,  1,    8,  3
         */
        // 最后一行的数据
        int[] mini = Arrays.copyOfRange(triangle[row - 1], 0, triangle[row - 1].length);
        // 从倒数第二行开始逐行遍历每一个非0元素
        for (int i = row - 2; i >= 0; i--) {
            for (int j = 0; j < triangle[i].length; j++) {
                if (triangle[i][j] == 0)
                    continue;
                // 计算出每一层的最小值
                mini[j] = triangle[i][j] + Math.min(mini[j], mini[j + 1]);
            }
        }
        return mini[0];
    }

    public static int minPath(int start, int end, boolean isReentrant) {
        return !isReentrant ? traverse(start, end, triangle.length) + triangle[start][end] : traverse(triangle.length);
    }

    public static void main(String[] args) {
        int minPath = ShortestPathOfTriangle.minPath(0, 0, true);
        System.out.println(minPath);
    }
}
