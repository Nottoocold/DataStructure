package com.zyc.day0701;

import java.util.Arrays;

/**
 * 	给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。

	注意：若 s 和 t 中每个字符出现的次数都相同，则称 s 和 t 互为字母异位词
	
 * @author zyc
 * 2022-07-01
 */
public class Solution3 {

	public static boolean isAnagram(String s, String t) {
		//由题意知，s和t在排序后一定相同
		int m = s.length();
		int n = t.length();
		if(m != n){
			return false;
		}
		char[] a = s.toCharArray();
		char[] b = t.toCharArray();
		Arrays.sort(a);
		Arrays.sort(b);		
		return Arrays.equals(a, b);
    }
	
	
	
	public static void main(String[] args) {
		System.out.println(isAnagram("catt", "taca"));
	}
}
