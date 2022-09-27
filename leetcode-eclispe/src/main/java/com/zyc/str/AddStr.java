package com.zyc.str;

public class AddStr {
    /**
     * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和并同样以字符串形式返回
     *
     * @param num1
     * @param num2
     * @return
     */
    private static String addStrings(String num1, String num2) {
        int i = num1.length() - 1, j = num2.length() - 1;
        int add = 0;// 进位标志
        StringBuilder bf = new StringBuilder();
        while (i >= 0 || j >= 0 || add != 0) {
            int a = i >= 0 ? num1.charAt(i--) - '0' : 0;
            int b = j >= 0 ? num2.charAt(j--) - '0' : 0;
            int ret = a + b + add;
            bf.append(ret % 10);
            add = ret / 10;
        }
        bf.reverse();
        return bf.toString();
    }
}
