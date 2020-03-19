package com.zm.LeetCodeEx.algorithms;

/**
 * 409. 最长回文串
 * <p>
 * 给定一个包含大写字母和小写字母的字符串，找到通过这些字母构造成的最长的回文串。
 * <p>
 * 在构造过程中，请注意区分大小写。比如 "Aa" 不能当做一个回文字符串。
 * <p>
 * 注意: 假设字符串的长度不会超过 1010。
 * <p>
 * 示例 1: 输入: "abccccdd" 输出: 7
 * <p>
 * 解释: 我们可以构造的最长的回文串是"dccaccd", 它的长度是 7。
 * 
 * 
 * @author zm
 */
public class LEET409 {
	public static void main(String[] args) {
		LEET409 l409 = new LEET409();
		System.out.println(l409.new Solution().longestPalindrome("aaaAAA"));
	}

	class Solution {
		public int longestPalindrome(String s) {
			int[] charCnt = new int['z' - 'A' + 1];
			char[] chars = s.toCharArray();
			for (int i = 0; i < chars.length; i++) {
				charCnt[chars[i] - 'A']++;
			}
			boolean hasOdd = false;
			int ret = 0;
			for (int i = 0; i < charCnt.length; i++) {
				if (charCnt[i] % 2 == 0) {
					ret += charCnt[i];
				} else {
					ret += charCnt[i] - 1;
					if (!hasOdd) {
						hasOdd = true;
					}
				}
			}
			return hasOdd ? ret + 1 : ret;
		}
	}
}
