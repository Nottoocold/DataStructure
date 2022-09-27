package com.zyc.binarysearch;

/*
 * 第一个错误的版本
 */
public class BadVersion {
    private int bad;

    public void setBad(int x) {
        bad = x;
    }

    public boolean isBadVersion(int version) {
        return version >= bad;
    }

    public int firstBadVersion(int n) {
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
}
