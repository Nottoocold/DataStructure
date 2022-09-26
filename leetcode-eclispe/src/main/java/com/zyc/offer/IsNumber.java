package com.zyc.offer;

public class IsNumber {

    static int i = 0;

    public boolean isNumber0(String s) {

        if (s.length() < 1) return false;

        boolean dot = false;
        int i = 0;

        // 忽略头部的空格
        while (s.charAt(i) == ' ')
            ++i;

        // 正负号
        if (s.charAt(i) == '+' || s.charAt(i) == '-') ++i;

        // 读小数点
        if (s.charAt(i) == '.') {
            ++i;
            dot = true;
        }

        if (s.charAt(i) > '9' || s.charAt(i) < '0') return false;

        while (s.charAt(i) >= '0' && s.charAt(i) <= '9')
            ++i;

        if (s.charAt(i) == '.') {
            if (dot)
                return false;
            else {
                ++i;
                while (s.charAt(i) >= '0' && s.charAt(i) <= '9') ++i;
            }
        }

        if (s.charAt(i) == 'e' || s.charAt(i) == 'E') {
            ++i;
            if (s.charAt(i) == '+' || s.charAt(i) == '-') ++i;
            if (s.charAt(i) > '9' || s.charAt(i) < '0') return false;
            while (s.charAt(i) >= '0' && s.charAt(i) <= '9') ++i;
        }

        while (i < s.length() && s.charAt(i) == ' ')
            ++i;

        return true;
    }

    public boolean isNumber(String s) {

        if (s.length() < 1)
            return false;

        while (s.charAt(i) == ' ')
            ++i;

        // 扫描数字 带不带符号都可以
        boolean numeric = scanInt(s);

        // 如果出现 '.' 则说明接下来是小数部分
        if (s.charAt(i) == '.') {
            ++i;
            // 1.小数可以没有整数部分，如.123
            // 2.小数点后面可以没有数字，如233.
            // 3.小数点前后可以都有数字
            numeric = scanUInt(s);
        }

        // 如果出现'e'或'E' 说明接下来是数字的指数部分
        if (s.charAt(i) == 'e' || s.charAt(i) == 'E') {
            ++i;
            // 用&&的原因：
            // 1.当e或E前面没有数字时，整个字符串不能表示数字，如.e1、e1;
            // 2.当e或E后面没有整数时，整个字符串不能表示数字，如12e，12e+5.4
            numeric = numeric && scanUInt(s);
        }

        // 尾部空格
        while (s.charAt(i) == ' ')
            ++i;

        return numeric && i == s.length();
    }

    private boolean scanInt(final String s) {
        if (s.charAt(i) == '+' || s.charAt(i) == '-')
            ++i;
        return scanUInt(s);
    }

    private boolean scanUInt(final String s) {
        int pos = i;
        while (i != s.length() && s.charAt(i) >= '0' && s.charAt(i) <= '9')
            ++i;
        return i > pos;
    }

}
