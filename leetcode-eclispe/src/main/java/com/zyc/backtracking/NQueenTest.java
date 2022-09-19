package com.zyc.backtracking;

public class NQueenTest {
    public static void main(String[] args) throws Exception {
        NQueen queen = new NQueen(8);
        System.out.println(queen.compute());
    }
}

class NQueen {

    public NQueen(int n) {
        arr = new int[n];
        this.N = n;
    }

    public int compute() {
        Runnable task = () -> check(0);
        Thread thread = new Thread(task, "计算线程");
        System.out.println(thread.getName() + "开始计算...");
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return count;
    }

    // N个皇后
    private final int N;

    // index表示第行数 arr[i]表示列数
    private final int[] arr;

    private static int count;

    private void check(int n) {
        if (n==N) { // 表示N个皇后的位置已经摆好

            count++;
            return;
        }
        for (int i = 0; i < N; i++) { // 每一个皇后的所在行数已经确定 只需确定所在列即可
            arr[n] = i; // 将第n个皇后放在第i列上
            if (!conflict(n)) { // 检测是否冲突
                check(n + 1);
            }
        }
    }

    // 检查第n个皇后与前n-1个皇后的位置是否冲突
    private boolean conflict(int n) {
        for (int i = 0; i < n; i++) {
            if (arr[i]==arr[n] || Math.abs(n - i)==Math.abs(arr[n] - arr[i])) {
                return true;
            }
        }
        return false;
    }
}
