package com.zyc.str;

public class StrToInt {

    /**
     * 8.字符串转换整数(atoi)
     *
     * @param s
     * @return
     */
    public int strToIntAtoi(String s) {
        s = s.trim();//remove space
        if (s.length() == 0) {
            return 0;
        }
        if (s.charAt(0) != '-' && s.charAt(0) != '+' && (!Character.isDigit(s.charAt(0))))//the first char of string is a letter
            return 0;
        int i = Character.isDigit(s.charAt(0)) ? 0 : 1;
        boolean flag = (s.charAt(0) == '-');
        boolean over = false;//over (int) maxvalue or minvalue
        long ret = 0L;
        while (i < s.length() && Character.isDigit(s.charAt(i))) {
            ret = ret * 10 + (s.charAt(i++) - '0');
            if (flag && -ret < Integer.MIN_VALUE) {//underflow
                ret = Integer.MIN_VALUE;
                over = true;
                break;
            }
            if (!flag && ret > Integer.MAX_VALUE) {//overflow
                ret = Integer.MAX_VALUE;
                over = true;
                break;
            }
        }
        return over ? (int) ret : (flag ? -(int) ret : (int) ret);
    }

    public int atoi(String str) {
        str = str.trim();
        if (str.length() == 0)
            return 0;
        if (str.charAt(0) != '-' && str.charAt(0) != '+' && (!Character.isDigit(str.charAt(0))))
            return 0;
        int i = Character.isDigit(str.charAt(0)) ? 0 : 1;
        // 正负号
        boolean sign = str.charAt(0) == '-';
        int b = Integer.MAX_VALUE / 10; // 边界
        int ret = 0;
        for (int j = i; j < str.length(); ++j) {
            if (!Character.isDigit(str.charAt(j)))
                break;
            if (ret > b || (ret == b && str.charAt(j) > '7')) {
                return sign ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            ret = ret * 10 + str.charAt(j) - '0';
        }

        return sign ? -ret : ret;
    }

}
