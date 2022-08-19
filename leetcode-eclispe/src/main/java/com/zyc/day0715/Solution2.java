package com.zyc.day0715;

import java.util.Arrays;

public class Solution2 {

    /**
     * 给你两个字符串 s1 和 s2 ，写一个函数来判断 s2 是否包含 s1 的排列。如果是，返回 true ；否则，返回 false 。 换句话说，s1
     * 的排列之一是 s2 的 子串
     *
     * @param s1 s1.length >= 1
     * @param s2 s2.length >= 1
     * @return
     */
    // ab eidbaooo
    public static boolean checkInclusion0(String s1, String s2) {
        boolean flag = false;// 标记每一次比较是否为为真
        int len_s1 = s1.length();
        int len_s2 = s2.length();
        if (len_s1 > len_s2)
            return flag;
        int i = 0;
        while (i < len_s1) {
            int j = 0;
            while (j < len_s2) { // 寻找以s1.i为起始的子串
                if (s1.charAt(i) != s2.charAt(j)) {
                    // if (i - 1 != -1 && s1.charAt(i - 1) == s2.charAt(j - 1))
                    // i = 0;
                    ++j;
                } else {
                    int m = j;
                    int n = i;
                    while (m < j + len_s1) {
                        if (s1.charAt(n) == s2.charAt(m)) {
                            ++m;
                            ++n;
                            flag = true;
                        } else {
                            flag = false;// 匹配不成功
                            break;
                        }
                    }
                    if (flag)// success
                        return flag;
                    else {

                    }
                    ++j;
                    i = 0;
                }
            } // j
            i++; // 以s1.i为起始的子串没找到
        } // i
        return flag;
    }

    /**
     * 由于排列不会改变字符串中每个字符的个数，所以只有当两个字符串每个字符的个数均相等时，一个字符串才是另一个字符串的排列。
     * 根据这个性质，记s1的长度为n，遍历s2中每个长度为n的子串，若子串和s1中的每个字符的个数都相等，则说明该子串是s1的一个排列。
     * 使用两个数组c1和c2，c1记录s1中个字符出现的次数，c2记录当前统计的子串中各字符出现的次数，数组的下表索引即代表字符 a-z 的位置
     * 需要遍历的子串长度均为s1.length,使用固定长度的 滑动窗口
     * 来维护c2；滑动窗口每向右移动一次，就进入一个新字符，离开一个旧字符。然后判断c1和c2是否相等
     *
     * @param s1
     * @param s2
     * @return
     */
    public static boolean checkInclusion1(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        if (n > m) {//
            return false;
        }
        // 初始化两个数组
        int[] cnt1 = new int[26];
        int[] cnt2 = new int[26];
        for (int i = 0; i < n; ++i) {// 统计s1和s2中前n个字符出现的次数
            ++cnt1[s1.charAt(i) - 'a'];
            ++cnt2[s2.charAt(i) - 'a'];
        }
        if (Arrays.equals(cnt1, cnt2)) {// 比较前n个字符
            return true;
        }
        for (int i = n; i < m; ++i) {// 因为前n个字符已经比较过了，所以从n+1开始统计并更新数组
            ++cnt2[s2.charAt(i) - 'a'];// 新进入的字符
            --cnt2[s2.charAt(i - n) - 'a'];// 出去的旧字符，
            if (Arrays.equals(cnt1, cnt2)) {
                return true;
            }
        }
        return false;//
    }

    public static void main(String[] args) {
        String s1 = "dl";
        String s2 = "world";
        System.out.println(checkInclusion1(s1, s2));
    }
}
