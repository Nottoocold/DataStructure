package com.zyc.offer.order;

/**
 * @author zyc
 */
public class Matrix {

    /**
     * 给定一个m * n 二维字符网格board 和一个字符串单词word 。如果word 存在于网格中，返回 true ；否则，返回 false 。
     * <p>
     * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
     *
     * @param board n*m 阶二维矩阵
     * @param word  待匹配单词
     * @return 若word存在于board中返回true
     */
    public boolean exist(char[][] board, String word) {
        int row = board.length, col = board[0].length;
        boolean[][] visited = new boolean[row][col];
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                if (dfs(board, visited, word, 0, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 深度优先遍历
     *
     * @param matrix    被遍历矩阵
     * @param isVisited 标记某节点是否被访问
     * @param w         待匹配单词
     * @param index     单词索引
     * @param row       矩阵行索引
     * @param col       矩阵列索引
     * @return 如果匹配成功返回true
     */
    private boolean dfs(char[][] matrix, boolean[][] isVisited, String w, int index, int row, int col) {
        if (row >= 0 && row < matrix.length && col >= 0 && col < matrix[0].length && !isVisited[row][col]) {
            if (matrix[row][col] == w.charAt(index)) {
                isVisited[row][col] = true;
                ++index;
                if (index <= w.length() - 1) {
                    // 下
                    boolean a = dfs(matrix, isVisited, w, index, row + 1, col);
                    // 上
                    boolean b = dfs(matrix, isVisited, w, index, row - 1, col);
                    // 右
                    boolean c = dfs(matrix, isVisited, w, index, row, col + 1);
                    // 左
                    boolean d = dfs(matrix, isVisited, w, index, row, col - 1);
                    // 四个方向都不太行
                    if (!(a || b || c || d)) {
                        isVisited[row][col] = false;
                    } else {
                        return true;
                    }
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。
     * <p>
     * 一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子
     * <p>问机器人可以到达多少个格子</p>
     *
     * @param m 行
     * @param n 列
     * @param k 限制和
     * @return 返回可到达的格子数
     */
    public int movingCount(int m, int n, int k) {
        boolean[][] visited = new boolean[m][n];
        return dfs(m, n, 0, 0, k, visited);
    }

    private int dfs(int m, int n, int i, int j, int k, boolean[][] visited) {
        if (k == 0) {
            return 1;
        }
        if (i < 0 || i >= m || j < 0 || j >= n || visited[i][j] || (sum(i, j) > k)) {
            return 0;
        }
        visited[i][j] = true;
        return 1 +
                dfs(m, n, i, j + 1, k, visited) +
                dfs(m, n, i, j - 1, k, visited) +
                dfs(m, n, i - 1, j, k, visited) +
                dfs(m, n, i + 1, j, k, visited);

    }

    /**
     * 34 + 23 = 3 + 4 + 2 + 3 = 12
     *
     * @param x 操作数x
     * @param y 操作数y
     * @return 条件相加结果
     */
    private int sum(int x, int y) {
        int sum = 0;
        while (x != 0) {
            sum += (x % 10);
            x /= 10;
        }
        while (y != 0) {
            sum += (y % 10);
            y /= 10;
        }
        return sum;
    }

}
