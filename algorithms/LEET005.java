package com.zm.LeetCodeEx.algorithms;

/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。 输入: "babad" 输出: "bab" 注意: "aba"
 * 也是一个有效答案。
 * 
 * @author zm
 *
 */
public class LEET005 {
	public static void main(String[] args) {
		System.out.println(new LEET005().longestPalindrome("abcbs"));
		System.out.println(new LEET005().longestPalindrome("abcbs"));
	}

	/**
	 * 回文字符串有两种情况，如果是abba即是偶数个，走loop1，如果是abcba走loop2
	 * 用max记录最长长度，len1&len2记录当前长度，dev1&dev2记录距离中心点的长度
	 * 
	 * @param s
	 * @return
	 */
	public String longestPalindrome(String s) {
		char[] chars = s.toCharArray();
		if (s.length() == 0) {
			return "";
		}
		int max = 1;
		int index = 0;
		for (int i = 0; i < chars.length; i++) {
			if (i > 0) {
				int dev1 = 0;
				int len1 = 0;

				loop1: while (i - dev1 > 0 && i + dev1 < chars.length) { // abba
					if (chars[i + dev1] == chars[i - dev1 - 1]) {
						len1 = len1 + 2;
						if (len1 > max) {
							max = len1;
							index = i + dev1;
						}
						dev1++;
					} else {
						break loop1;
					}
				}
				if (i > 1) {
					int dev2 = 0;
					int len2 = 1;
					loop2: while (i - dev2 - 1 > 0 && i + dev2 < chars.length) { // abcba
						if (chars[i + dev2] == chars[i - dev2 - 2]) {
							len2 = len2 + 2;
							if (len2 > max) {
								max = len2;
								index = i + dev2;
							}
							dev2++;
						} else {
							break loop2;
						}
					}
				}
			}
		}
		return s.substring(index - max + 1, index + 1);
	}

	/**
	 * 把每次判断的代码提取出来
	 * 
	 * @param s
	 * @return
	 */
	public String longestPalindrome2(String s) {
		int n = s.length();

		int[] range = new int[2];
		for (int i = 0; i < n; i++) {
			i = helper(s, range, i);
		}

		return s.substring(range[0], range[1]);
	}

	public int helper(String s, int[] range, int i) {
		int lo = i;
		int hi = i;
		while (hi < s.length() - 1 && s.charAt(hi) == s.charAt(hi + 1)) {
			hi++;
		}

		int ret = hi;
		while (lo > 0 && hi < s.length() - 1 && s.charAt(lo - 1) == s.charAt(hi + 1)) {
			lo--;
			hi++;
		}

		if (hi - lo + 1 > range[1] - range[0]) {
			range[0] = lo;
			range[1] = hi + 1;
		}

		return ret;
	}

	/**
	 * 官方题解，进一步优化代码
	 * 
	 * @param s
	 * @return
	 */
	public String longestPalindrome3(String s) {

		if (s == null || s.length() < 1)
			return "";
		int start = 0, end = 0;
		for (int i = 0; i < s.length(); i++) {
			int len1 = expandAroundCenter(s, i, i);
			int len2 = expandAroundCenter(s, i, i + 1);
			int len = Math.max(len1, len2);
			if (len > end - start) {
				start = i - (len - 1) / 2;
				end = i + len / 2;
			}
		}
		return s.substring(start, end + 1);
	}

	private int expandAroundCenter(String s, int left, int right) {
		int L = left, R = right;
		while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
			L--;
			R++;
		}
		return R - L - 1;
	}
}
