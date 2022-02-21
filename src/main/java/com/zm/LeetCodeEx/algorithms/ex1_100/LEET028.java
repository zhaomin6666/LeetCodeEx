package com.zm.LeetCodeEx.algorithms.ex1_100;

/**
 * 实现 strStr() 函数。
 * 
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置
 * (从0开始)。如果不存在，则返回 -1。
 * 
 * 示例 1:
 * 
 * 输入: haystack = "hello", needle = "ll" 输出: 2 示例 2:
 * 
 * 输入: haystack = "aaaaa", needle = "bba" 输出: -1
 * 
 * needle 为空字符串时，返回0
 * 
 * @author zm
 *
 */
public class LEET028 {
	public static void main(String[] args) {
		LEET028 l028 = new LEET028();
		String str1 = "hello";
		String str2 = "ll";
		String str3 = "abaaaa";
		String str4 = "aaaaa";

		System.out.println(l028.strStr2(str1, str2));
		System.out.println(l028.strStr2(str3, str4));
	}

	/**
	 * 遍历第一个字符串如果有第一个一样的就继续看下一个字母
	 * 
	 * @param haystack
	 * @param needle
	 * @return
	 */
	public int strStr(String haystack, String needle) {
		int ret = 0;
		if ("".equals(needle)) {
			return ret;
		}
		for (ret = 0; ret < haystack.length(); ret++) {
			boolean flag = true;
			for (int j = 0; j < needle.length(); j++) {
				if (ret + j >= haystack.length()) {
					return -1;
				} else {
					if (haystack.charAt(ret + j) != needle.charAt(j)) {
						flag = false;
						break;
					}
				}
			}
			if (flag) {
				return ret;
			}
		}
		return -1;
	}

	/**
	 * 直接判断字符串是否相等，并且减少循环次数
	 * 
	 * @param haystack
	 * @param needle
	 * @return
	 */
	public int strStr2(String haystack, String needle) {
		int ret = 0;
		if ("".equals(needle)) {
			return ret;
		}
		for (ret = 0; ret <= haystack.length() - needle.length(); ret++) {
			if (haystack.substring(ret, ret + needle.length()).equals(needle)) {
				return ret;
			}
		}
		return -1;
	}
}
