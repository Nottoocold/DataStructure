package com.zyc.datastructure.list.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class SparseArray {
    private final Random random = new Random();
    private Map<Integer[], Integer> position;

    /**
     * 生成原始的二维数组，不同值随机生成
     *
     * @param row      行
     * @param col      列
     * @param diff     不同元素的个数
     * @param _default 默认值
     * @return 原始数组
     */
    public int[][] generateOriginalArray(int row, int col, int diff, int _default) {
        int[][] original = new int[row][col];
        if (_default!=0) {
            for (int i = 0; i < row; i++) {
                Arrays.fill(original[i], _default);
            }
        }
        for (int i = 0; i < diff; i++) {
            int nextInt = random.nextInt(9) + 1;
            if (nextInt!=_default) {
                int x = random.nextInt(row);
                int y = random.nextInt(col);
                original[x][y] = nextInt;
            }
        }
        return original;
    }

    /**
     * 压缩原始数组
     *
     * @param origin   原始数组
     * @param _default 原始数组中的默认值
     * @return 压缩后的数组
     */
    public int[][] compression(int[][] origin, int _default) {
        int count = 0;
        Map<Integer[], Integer> map = new HashMap<>();
        for (int i = 0; i < origin.length; i++) {
            for (int j = 0; j < origin[0].length; j++) {
                if (origin[i][j]!=_default) {
                    count++;
                    map.put(new Integer[]{i, j}, origin[i][j]);
                }
            }
        }
        Set<Map.Entry<Integer[], Integer>> entries = map.entrySet();
        Iterator<Map.Entry<Integer[], Integer>> it = entries.iterator();
        int[][] work = new int[count + 1][3];
        work[0][0] = origin.length;
        work[0][1] = origin[0].length;
        work[0][2] = count;
        for (int i = 1; i < work.length; i++) {
            Map.Entry<Integer[], Integer> entry = it.next();
            for (int j = 0; j < 3; j++) {
                switch (j) {
                    case 0: {
                        work[i][j] = entry.getKey()[0];
                        break;
                    }
                    case 1: {
                        work[i][j] = entry.getKey()[1];
                        break;
                    }
                    case 2: {
                        work[i][j] = entry.getValue();
                        break;
                    }
                }
            }
        }
        position = map;
        return work;
    }

    public int[][] decompress(int[][] compressedArr) {
        int[][] origin = new int[compressedArr[0][0]][compressedArr[0][1]];
        position.forEach((t1, t2) -> {
            origin[t1[0]][t1[1]] = t2;
        });
        return origin;
    }

    public static void main(String[] args) {
        SparseArray sparseArray = new SparseArray();
        int[][] originalArray = sparseArray.generateOriginalArray(11, 11, 4, 0);
        System.out.println("原始数组:");
        print(originalArray);
        int[][] after = sparseArray.compression(originalArray, 0);
        System.out.println("压缩后的数组:");
        print(after);
        int[][] decompress = sparseArray.decompress(after);
        System.out.println("还原后的数组:");
        print(decompress);
        System.out.print("验证: ");
        sparseArray.verify(originalArray,decompress);
    }

    public static void print(int[][] arr) {
        for (int[] ints : arr) {
            System.out.println(Arrays.toString(ints));
        }
    }

    public void verify(int[][] origin, int[][] decompressed) {
        System.out.println(Arrays.deepEquals(origin, decompressed));
    }

}
