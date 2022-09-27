package com.zyc.dfs;

/**
 * 695.岛屿的最大面积
 *
 * @author zyc
 */
public class Solution2 {

    /**
     * 岛屿是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在 水平 或者 竖直 的四个方向上相邻。
     * 你可以假设 grid 的四个边缘都被 0（代表水）包围着。
     *
     * @param grid 二进制矩阵
     * @return 返回最大的岛屿面积，若没有返回0
     */
    public static int DFS(int[][] grid, int row, int col) {
        int m = grid.length, n = grid[0].length;
        if (row < 0 || row >= m || col < 0 || col >= n || grid[row][col] == 0) {
            return 0;
        }
        grid[row][col] = 0;//标记访问过的节点
        return 1 +
                DFS(grid, row + 1, col) +
                DFS(grid, row, col + 1) +
                DFS(grid, row, col - 1) +
                DFS(grid, row - 1, col);
    }

    public static int maxAreaOfIsland(int[][] grid) {
        int max = 0;
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[0].length; ++j) {
                if (grid[i][j] == 1) {
                    max = Math.max(max, DFS(grid, i, j));
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[][] grid = {
                {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
        };
        System.out.println(maxAreaOfIsland(grid));// 6
    }
}
