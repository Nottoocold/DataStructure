package com.zqqiliyc.alg;

import java.util.HashSet;

/**
 * longest-substring-without-repeating-characters, link:<a href="https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/">LeetCode 3</a>
 * <p>
 * Given a string s, find the length of the longest substring without repeating characters.
 * <p>
 * Example 1:
 * <p>
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * <p>
 * Example 2:
 * <p>
 * Input: s = "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * <p>
 * Example 3:
 * <p>
 * Input: s = "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 * <p>
 * tips:
 * 1. 0 <= s.length <= 5 * 10^4
 * 2. s consists of English letters, digits, symbols and spaces.
 *
 * @author zqqiliyc
 * @since 2024-09-05
 */
public class AlgLongestSubstring {

    public int lengthOfLongestSubstring(String s) {
        return longestSubstring(s).length();
    }

    public String longestSubstring(String str) {
        if (str == null || str.isEmpty()) {
            return "";
        }

        // sliding window [start, end)
        int start = 0, end = 0, maxLength = 0;
        int longestStart = 0;
        HashSet<Character> set = new HashSet<>();

        while (end < str.length()) {
            char c = str.charAt(end);

            // remove the character from start to end-1 that are already in the set
            while (set.contains(c)) {
                set.remove(str.charAt(start++));
            }

            // add the current character to the set
            set.add(c);
            // move the end pointer to the right
            end++;

            // update the maxLength and longestStart
            if (end - start > maxLength) {
                maxLength = end - start;
                longestStart = start;
            }
        }

        return str.substring(longestStart, longestStart + maxLength);
    }
}
