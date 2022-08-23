package com.zyc.offer;

public class replaceSpace {

    /**
     * 替换字符串的空格
     * @param s 待处理字符串
     * @return 处理完后的字符串
     */
    public String replaceSpace0(String s){
        if (s.length() == 0)
            return "";
        char[] charArray = s.toCharArray();
        StringBuilder builder = new StringBuilder();
        for (char c : charArray) {
            if (c != ' ') {
                builder.append(c);
            }else{
                builder.append("%20");
            }
        }
        return builder.toString();
    }
}
