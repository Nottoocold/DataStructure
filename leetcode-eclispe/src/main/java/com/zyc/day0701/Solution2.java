package com.zyc.day0701;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你两个字符串：ransomNote 和 magazine ，判断 ransomNote 能不能由 magazine 里面的字符构成。
 * <p>
 * 如果可以，返回 true ；否则返回 false 。
 * <p>
 * magazine 中的每个字符只能在 ransomNote 中使用一次。
 *
 * @author modua
 */
public class Solution2 {

    public static boolean canConstruct(String ransomNote, String magazine) {
        int ransomNoteLen = ransomNote.length();
        int magazinLen = magazine.length();
        if (ransomNoteLen > magazinLen) {//字符转1长度大于字符串2
            return false;
        }

        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < ransomNoteLen; ++i) {//遍历字符串,将每个字母出现的频次记录到map中
            char c = ransomNote.charAt(i);
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }
        for (int j = 0; j < magazinLen; ++j) {//遍历字符串,对于每个字母，若map中包含，则v-1
            char h = magazine.charAt(j);
            if (map.containsKey(h)) {
                map.put(h, map.get(h) - 1);
            }
        }

        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            int v = entry.getValue();
            if (v > 0) {//大于0说明相同的字母串2中的少,不足以构成串1
                return false;
            }
        }

        return true;
    }


    public static void main(String[] args) {
        System.out.println(canConstruct("dadada", "daada"));
    }
}
