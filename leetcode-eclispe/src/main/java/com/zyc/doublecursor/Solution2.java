package com.zyc.doublecursor;

public class Solution2 {
    public static String prossorString(String s) {
        StringBuffer buffer = new StringBuffer();
        for (char c : s.toCharArray()) {
            if (c != '#') {
                buffer.append(c);
            } else if (buffer.length() > 0) {
                buffer.deleteCharAt(buffer.length() - 1);
            }
        }
        return buffer.toString();
    }

    public static boolean compareString(String s, String t) {
        if (s.length() == t.length()) {
            char[] s1 = s.toCharArray();
            char[] s2 = t.toCharArray();
            int len = s1.length, i = 0;
            while (len-- != 0) {
                if (s1[i] != s2[i]) {
                    return false;
                }
                ++i;
            }
            return true;
        }
        return false;
    }

    /*
     * 844.比较含退格的字符串 给定 s 和 t 两个字符串，当它们分别被输入到空白的文本编辑器后，如果两者相等，返回 true 。# 代表退格字符
     */
    public static boolean backspaceCompare(String s, String t) {
        String s1 = prossorString(s);
        String s2 = prossorString(t);
        return compareString(s1, s2);
    }

    public static void main(String[] args) {
        String s = "ab#c";
        String t = "ad#c";
        System.out.println(backspaceCompare(s, t));
    }

}
