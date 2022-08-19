package com.zyc.day0701;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个字符串 s ，找到它的第一个不重复的字符，并返回它的索引 。如果不存在，则返回 -1 。
 *
 * @author modua
 */
public class Solution1 {

    /*
     * map存储索引值，若重复出现设置为-1
     */
    public static int firstUniqChar(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                map.put(c, -1);
            } else {
                map.put(c, i);
            }
        }

        int result = s.length();
        for (Map.Entry<Character, Integer> entries : map.entrySet()) {
            int p = entries.getValue();
            if (p != -1 && p < result) {
                result = p;
            }
        }

        if (result == s.length()) {
            return -1;
        }

        return result;
    }

    public static void main(String[] args) {
        String string = "leetcode";
        System.out.println(firstUniqChar(string));
    }
}
