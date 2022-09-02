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

    /**
     * 输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。为简单起见，标点符号和普通字母一样处理。
     * 例如输入字符串"I am a student. "，则输出"student. a am I"。
     *
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        s = s.trim();//去除首位空格
        char[] charArray = s.toCharArray();
        reverse(charArray, 0, s.length());// 整个字符反转
        int start = 0, end = 0;
        while (end < charArray.length) {// 遍历数组，挨个反转每个单词
            if (charArray[end] != ' ') {
                if (end > 0 && charArray[end - 1] == ' ')
                    start = end;
            } else {
                if (charArray[end - 1] != ' ') {
                    reverse(charArray, start, end);
                }
            }
            end++;
        }
        reverse(charArray, start, end);
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] != ' ')
                ret.append(charArray[i]);
            else {
                if (charArray[i - 1] == ' ') {
                    continue;
                }
                ret.append(' ');
            }
        }
        return ret.toString();
    }

    private void reverse(char[] arr, int i, int j) {
        int st = i, ed = j - 1;
        while (st < ed) {
            char c = arr[st];
            arr[st] = arr[ed];
            arr[ed] = c;
            st++;
            ed--;
        }
    }

}
