package com.zyc.day0723;

public class Solution2 {

    /**
     * 8.字符串转换整数(atoi)
     *
     * @param s
     * @return
     */
    public int myAtoi(String s) {
        if (s.length() == 0)
            return 0;
        s = s.trim();//remove space
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

    public static void main(String[] args) {
        String string = "   -4039words";
        System.out.println(new Solution2().myAtoi(string));
    }
}
