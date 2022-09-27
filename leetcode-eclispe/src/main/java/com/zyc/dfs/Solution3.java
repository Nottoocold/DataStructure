package com.zyc.dfs;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Solution3 {
    /**
     * 542. 01矩阵,给定一个矩阵，返回一个新矩阵，新矩阵中每一个元素是旧矩阵中对应位置元素到0的最短距离。
     *
     * @param mat
     * @return
     */
    // 方位数组
    static int[][] directions = {
            {-1, 0}, // 上
            {1, 0},
            {0, -1}, // 左
            {0, 1},
    };

    public static int[][] updateMatrix(int[][] mat) {
        int row = mat.length, col = mat[0].length;
        int[][] ret = new int[row][col];// 结果数组
        boolean[][] flags = new boolean[row][col];// 标记数组
        Queue<int[]> queue = new ArrayDeque<>();
        // 将所有的0添加进队列中
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                if (mat[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                    flags[i][j] = true;//tag has been visited
                }
            }
        }

        //BFS
        while (!queue.isEmpty()) {
            int[] ps = queue.poll();
            int i = ps[0], j = ps[1];
            int derict = 0;
            while (derict < 4) {//loop 4 positions
                int ii = i + directions[derict][0];// current visit position
                int jj = j + directions[derict][1];
                if (ii >= 0 && ii < row && jj >= 0 && jj < col && !flags[ii][jj]) {
                    ret[ii][jj] = ret[i][j] + 1;//
                    flags[ii][jj] = true;
                    queue.offer(new int[]{ii, jj});
                }
                derict++;
            }
        }

        return ret;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {0, 0, 0},
                {0, 1, 0},
                {1, 1, 1},
                {0, 1, 1},
        };
        matrix = updateMatrix(matrix);
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }
}
