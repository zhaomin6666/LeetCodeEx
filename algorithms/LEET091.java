package com.zm.LeetCodeEx.algorithms;

import com.alibaba.fastjson.JSON;

/**
 * 91. 解码方法
 * <p>
 * 一条包含字母 A-Z 的消息通过以下方式进行了编码：<br>
 * 
 * 'A' -> 1 <br>
 * 'B' -> 2 <br>
 * ... <br>
 * 'Z' -> 26 <br>
 * 给定一个只包含数字的非空字符串，请计算解码方法的总数。<br>
 * 
 * 示例 1: <br>
 * 输入: "12" <br>
 * 输出: 2 <br>
 * 解释: 它可以解码为 "AB"（1 2）或者 "L"（12）。
 * <p>
 * 示例 2:<br>
 * 输入: "226"<br>
 * 输出: 3<br>
 * 解释: 它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
 * 
 * 
 * @author zm
 */
public class LEET091 {
	public static void main(String[] args) {
		LEET091 l091 = new LEET091();
		System.out.println(JSON.toJSONString(l091.new Solution().numDecodings("12")));
		System.out.println(JSON.toJSONString(l091.new Solution().numDecodings("1212")));
		System.out.println(JSON.toJSONString(l091.new Solution().numDecodings("1227")));
		System.out.println(JSON.toJSONString(l091.new Solution().numDecodings("226")));
		System.out.println(JSON.toJSONString(l091.new Solution().numDecodings("0")));
		System.out.println(JSON.toJSONString(l091.new Solution().numDecodings("30")));
	}

	class Solution {
		public int numDecodings(String s) {
			char[] cs = s.toCharArray();
			if (cs.length == 0) {
				return 0;
			}
			if (cs[0] == '0') {
				return 0;
			}
			int[] dp = new int[cs.length + 1];
			dp[0] = 1;
			dp[1] = 1;
			char last = cs[0];
			char cur;
			for (int i = 2; i < dp.length; i++) {
				cur = cs[i - 1];
				int sum = cur == '0' ? 0 : dp[i - 1];
				if (cur == '0' && last == '0') {
					return 0;
				} else {
					int twoDigit = (last - '0') * 10 + (cur - '0');
					if (twoDigit >= 10 && twoDigit <= 26) {
						sum += dp[i - 2];
					}
				}
				dp[i] = sum;
				last = cur;
			}
			return dp[cs.length];
		}
	}
}
