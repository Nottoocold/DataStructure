package com.zyc.dfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Solution1 {
    /**
     * 色块上色问题，在以给定的行列坐标的单元的上下左右，以及被染色的单元的上下左右继续递归的染色
     *
     * @param image 给定的二维矩阵
     * @param sr 行
     * @param sc 列
     * @param newColor 需要更新的颜色
     * @return
     */
    static int[] dx = {1, 0, 0, -1};//定义下右左上 四个方向的数组
    static int[] dy = {0, 1, -1, 0};

    //1.广度优先遍历
    public static int[][] floodFill_BFS(int[][] image, int sr, int sc, int newColor) {
        int oldColor = image[sr][sc];//记录初始节点的颜色
        if (oldColor == newColor)
            return image;
        int row = image.length, col = image[0].length;
        Queue<int[]> queue = new LinkedList<>();
        image[sr][sc] = newColor;//上色
        queue.offer(new int[]{sr, sc});//将当前节点的坐标入队
        while (!queue.isEmpty()) {
            int[] e = queue.poll();
            int oldx = e[0], oldy = e[1];//讲旧的坐标记录
            for (int i = 0; i < 4; ++i) {//遍历上下左右四个方向
                int newx = oldx + dx[i], newy = oldy + dy[i];//记录将要遍历的新坐标
                if (newx >= 0 && newx < row && newy >= 0 && newy < col && image[newx][newy] == oldColor) {//新坐标合法且颜色为旧颜色
                    queue.offer(new int[]{newx, newy});//入队并上色
                    image[newx][newy] = newColor;
                }
            }
        }
        return image;
    }

    public static void DFS(int[][] image, int row, int col, int oldColor, int newColor) {
        if (row >= 0 && row < image.length && col >= 0 && col < image[0].length) {
            if (image[row][col] == oldColor) {
                image[row][col] = newColor;
                DFS(image, row + 1, col, oldColor, newColor);//下
                DFS(image, row, col + 1, oldColor, newColor);//右
                DFS(image, row, col - 1, oldColor, newColor);//左
                DFS(image, row - 1, col, oldColor, newColor);//上
            }
        }
    }

    //2.深度优先遍历
    public static void floodFill_DFS(int[][] image, int sr, int sc, int newColor) {
        int old = image[sr][sc];
        if (old != newColor) {
            DFS(image, sr, sc, old, newColor);
        }
    }

    public static void main(String[] args) {
        int[][] image = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
        //image = floodFill_BFS(image, 1, 1, 2);
        floodFill_DFS(image, 1, 1, 2);
        for (int[] row : image) {
            System.out.println(Arrays.toString(row));
        }
    }
}
