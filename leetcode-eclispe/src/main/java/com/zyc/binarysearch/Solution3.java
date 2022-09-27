package com.zyc.binarysearch;

public class Solution3 {

    public int binarySearch(int[] arr, int key) {
        int len = arr.length;
        int left = 0, right = len - 1, mid = 0;
        while (left <= right) {
            mid = (left + right) / 2;
            if (arr[mid] == key) {
                return mid;
            } else if (arr[mid] > key) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -(left + 1);
    }

    /**
     * 矩阵中的值升序排列,查找target
     *
     * @param matrix 二维矩阵
     * @param target 若矩阵中有目标值则返回true,否则返回false
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length;
        int col = matrix[0].length;
        int i;
        for (i = 0; i < row; ++i) {
            if (matrix[i][0] <= target && target <= matrix[i][col - 1]) {//因为是有序的 所以逐行判断
                int index = binarySearch(matrix[i], target);
                if (index >= 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] M = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 33, 40}};
        System.out.println(new Solution3().searchMatrix(M, 13));
    }
}
