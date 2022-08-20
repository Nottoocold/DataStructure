package com.zyc.backtracking;

import java.util.*;

public class BackTrack {

    /**
     * 22.括号生成<p>
     * n = 2 -> ["(())","()()"]
     *
     * @param n 生成括号的对数
     * @return list 结果集合
     */
    public List<String> generateParenthesis(int n) {
        List<String> combinations = new ArrayList<>();
        //1.暴力枚举
//        generateAll(new char[2 * n], 0, combinations);
        //2.回溯
//        backTrack(combinations, new StringBuilder(), 0, 0, n);
        //3.逆向回溯
        dfs(combinations, n, n, new StringBuilder());
        return combinations;
    }

    /*
        暴力枚举
     */
    private void generateAll(char[] current, int pos, List<String> result) {
        if (pos == current.length) {
            if (valid(current)) {
                result.add(new String(current));
            }
        } else {
            current[pos] = '(';
            generateAll(current, pos + 1, result);
            current[pos] = ')';
            generateAll(current, pos + 1, result);
        }
    }

    private boolean valid(char[] current) {
        int balance = 0;
        for (char c : current) {
            if (c == '(') {
                ++balance;
            } else {
                --balance;
            }
            if (balance < 0) {
                return false;
            }
        }
        return balance == 0;
    }

    /**
     * 回溯法,从无到有拼接结果
     *
     * @param ret   结果集合
     * @param bf    字符串拼接
     * @param open  '('的数量
     * @param close ')'的数量
     * @param n     生成括号的对数
     */
    private void backTrack(List<String> ret, StringBuilder bf, int open, int close, int n) {
        if (bf.length() == 2 * n) {
            if (valid(bf.toString().toCharArray())) {
                ret.add(bf.toString());
                return;
            }
        }
        if (open < n) {
            bf.append('(');
            backTrack(ret, bf, open + 1, close, n);
            bf.deleteCharAt(bf.length() - 1);
        }
        if (close < n) {
            bf.append(')');
            if (bf.length() > 0 && bf.charAt(0) == ')') return;
            backTrack(ret, bf, open, close + 1, n);
            bf.deleteCharAt(bf.length() - 1);
        }
    }


    /**
     * @param ret   结果集
     * @param open  左括号的数量
     * @param close 右括号数量
     * @param str   当前拼接结果字符串
     */
    private void dfs(List<String> ret, int open, int close, StringBuilder str) {
        if (open == 0 && close == 0) {//左右括号都使用完了
            ret.add(str.toString());
            return;
        }
        if (open > 0) {
            dfs(ret, open - 1, close, str.append('('));
            str.deleteCharAt(str.length() - 1);
        }
        if (close > open) {//右括号剩余数量大于左括号
            dfs(ret, open, close - 1, str.append(')'));
            str.deleteCharAt(str.length() - 1);
        }
    }

    /**
     * 括号匹配
     * @param str 括号字符串
     * @return 若检测匹配成功返回true
     */
    public boolean Verification(String str) {
        if (str.length() % 2 != 0)//奇数个括号
            return false;
        Deque<Character> stack = new ArrayDeque<>();
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (map.containsKey(c)) {//c是右括号
                if (stack.isEmpty() || stack.peek() != map.get(c))//若栈空 或 栈顶元素不是对应的左括号
                    return false;
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        return stack.isEmpty();//栈空说明所有的右括号都得到了匹配
    }

}
