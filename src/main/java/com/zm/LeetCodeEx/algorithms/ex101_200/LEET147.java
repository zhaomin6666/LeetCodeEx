package com.zm.LeetCodeEx.algorithms.ex101_200;

/**
 * 给定一个字符串 s ，找出 至多 包含两个不同字符的最长子串 t 。
 * 
 * 示例 1:
 * 
 * 输入: "eceba" 输出: 3 解释: t 是 "ece"，长度为3。 示例 2:
 * 
 * 输入: "ccaabbb" 输出: 5 解释: t 是 "aabbb"，长度为5。
 * 
 * 
 * 
 * @author zm
 *
 */
public class LEET147 {
	public static void main(String[] args) {
		LEET147 l147 = new LEET147();
		String input1 = "eceba";
		System.out.println(l147.lengthOfLongestSubstringTwoDistinct(input1)); // 3
		String input2 = "121333";
		System.out.println(l147.lengthOfLongestSubstringTwoDistinct(input2));
		String input3 = "ccaabbb";
		System.out.println(l147.lengthOfLongestSubstringTwoDistinct(input3));

	}

	/**
	 * 利用一个二位数组来记录窗口中的两个数，再用两个数来记录这两个数在字符串中连续出现的最早的位置
	 * 
	 * @param s
	 * @return
	 */
	public int lengthOfLongestSubstringTwoDistinct(String s) {
		if (s == null || "".equals(s)) {
			return 0;
		}
		int fst = 0; // 第一个数的位置
		int sec = 0; // 第二个数的位置
		int thd = 0; // 循环用指针
		int len = 0; // 当前长度
		int maxlen = 1; // 最长长度
		char[] chars = new char[2]; // 记录两个数的数组，永远保持后出现的在后面===一个队列，后出现的数从后面插入
		chars[1] = s.charAt(0);
		while (thd < s.length()) {
			if (chars[1] == s.charAt(thd)) {
				len++; // 如果当前数是第二个数，那么保持不动
			} else if (chars[0] == s.charAt(thd)) {
				fst = sec;
				sec = thd;
				char c = chars[0];
				chars[0] = chars[1];
				chars[1] = c; // 如果当前数是第一个数，那么交换数组中两数的位置，记录出现的位置
				len++;
			} else {
				chars[0] = chars[1];
				chars[1] = s.charAt(thd);
				fst = sec;
				sec = thd;
				len = sec - fst + 1; // 如果当前数不是数组中的数，那么去掉数组中的第一个数，重新计算长度
			}
			maxlen = len > maxlen ? len : maxlen;
			++thd;
		}
		return maxlen;
	}
}
