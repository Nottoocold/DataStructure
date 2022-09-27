package com.zyc.str;

import java.util.HashSet;
import java.util.Set;

public class Solution1 {

    /*
     * 给定一个字符串 s，找出其中不含有重复字符串的最长子串长度，abcabcbb -> 3(abc) bbbb -> 1(b) pwwkew -> 3
     */
    public static int lengthOfLongestSubstring(String s) {
        if (s == null)
            return 0;
        int len = s.length();
        if (len == 1)
            return 1;
        // 处理长度 >= 2 的字符串
        Set<Character> set = new HashSet<>();//此集合可看作是一个能够快速判断是否有重复元素的队列
        int length = 0;
        int j = 0;
        for (int i = 0; i < len; ) {
            // if (i != 0)
            // set.remove(s.charAt(i - 1));
            while (j < len) {
                if (!set.contains(s.charAt(j))) {//队列中没有此元素则加入集合
                    set.add(s.charAt(j));
                    ++j;
                } else {
                    break;
                }
            }
            length = Math.max(length, j - i);//计算前一个最长子串的长度和当前长度谁更大
            // set.removeAll(set);
            set.remove(s.charAt(++i - 1));//移除队列中重复的元素
        }
        return length;
    }

}
