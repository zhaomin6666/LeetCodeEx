package com.zm.LeetCodeEx.algorithms.ex1_100;

import com.alibaba.fastjson.JSON;

/**
 * 97. 交错字符串
 * <p>
 * 给定三个字符串 s1, s2, s3, 验证 s3 是否是由 s1 和 s2 交错组成的。
 * <p>
 * 示例 1:<br>
 * 输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"<br>
 * 输出: true
 * <p>
 * 示例 2:<br>
 * 输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"<br>
 * 输出: false
 * 
 * @author zm
 */
public class LEET097 {
	public static void main(String[] args) {
		LEET097 l097 = new LEET097();
		System.out.println(JSON.toJSONString((l097.new Solution().isInterleave("aabcc", "dbbca", "aadbbcbcac"))));
		System.out.println(JSON.toJSONString((l097.new Solution().isInterleave("aabcc", "dbbca", "aadbbbaccc"))));
		System.out.println(JSON.toJSONString((l097.new Solution().isInterleave("aabcc", "", "aabcc"))));
		System.out.println(JSON.toJSONString((l097.new Solution().isInterleave("ab", "acb", "aacbb"))));
	}

	/**
	 * dp[i][j]表示s1[0~i-1]和s2[0~j-1]能否交错组成s3[0~i+j-1]
	 * 
	 * @author zm
	 *
	 */
	class Solution {
		public boolean isInterleave(String s1, String s2, String s3) {
			int l1 = s1.length();
			int l2 = s2.length();
			if (s3.length() != l1 + l2) {
				return false;
			}
			boolean[][] dp = new boolean[l1 + 1][l2 + 1];
			dp[0][0] = true;
			char[] c1 = s1.toCharArray();
			char[] c2 = s2.toCharArray();
			char[] c3 = s3.toCharArray();
			for (int i = 1; i <= l1; i++) {
				if (dp[i - 1][0] && c1[i - 1] == c3[i - 1]) {
					dp[i][0] = true;
				} else {
					dp[i][0] = false;
				}
			}
			for (int i = 1; i <= l2; i++) {
				if (dp[0][i - 1] && c2[i - 1] == c3[i - 1]) {
					dp[0][i] = true;
				} else {
					dp[0][i] = false;
				}
			}
			for (int i = 1; i <= c1.length; i++) {
				for (int j = 1; j <= c2.length; j++) {
					if ((dp[i - 1][j] && c1[i - 1] == c3[i + j - 1]) 
							|| (dp[i][j - 1] && c2[j - 1] == c3[i + j - 1])) {
						dp[i][j] = true;
					}else {
						dp[i][j] = false;
					}
				}
			}
			return dp[l1][l2];
		}
	}
}
