package com.zyc.offer;

public class ReverseWord {

    /**
     * 字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。<p>
     * 比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"
     *
     * @param s 待处理字符串 1 <= s.len <= 10000
     * @param n 左旋的字符串个数
     * @return 结果
     */
    public String reverseLeftWords(String s, int n) {
        StringBuilder stringBuilder = new StringBuilder();
        int i = n;
        for (; i < s.length(); ++i) {
            stringBuilder.append(s.charAt(i));
        }
        for (i = 0; i < n; ++i) {
            stringBuilder.append(s.charAt(i));
        }
        return stringBuilder.toString();
    }

}
