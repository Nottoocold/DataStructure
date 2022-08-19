package com.zyc.day0709;

/*
 * 第一个错误的版本
 */
public class Solution2 {
    private static int bad;

    public static void setBad(int x) {
        bad = x;
    }

    static boolean isBadVersion(int version) {
        return version >= bad;
    }

    static int firstBadVersion(int n) {
        int left = 1, right = n;
        int mid = (left + right) / 2;
        while (left < right) {
            if (!isBadVersion(mid)) {
                left = mid + 1;
            } else {
                right = mid;
            }
            mid = (left + right) / 2;
        }
        return mid;
    }

    public static void main(String[] args) {
        Solution2.setBad(4);
        System.out.println(firstBadVersion(10));
    }
}
