package com.zyc.offer;

public class SumNums {

    public static void main(String[] args) {
//        System.out.println(fun(100));
        System.out.println(sumNums(3));
    }

    public static int fun(int n) {
//        return n==0 ? 0:(n + fun(n - 1));
        boolean flag = n > 0 && (n += fun(n - 1)) > 0;
        return n;
    }

    static int[] test = new int[]{0};

    public static int sumNums(int n) {
        try {
            return test[n];
        } catch (Exception e) {
            return n + sumNums(n - 1);
        }
    }
}
