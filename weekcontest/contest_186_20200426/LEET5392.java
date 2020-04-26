package com.zm.LeetCodeEx.weekcontest.contest_186_20200426;

/**
 * 周赛 2020年4月26日
 * <p>
 * 5392. 分割字符串的最大得分
 * <p>
 * 给你一个由若干 0 和 1 组成的字符串 s ，请你计算并返回将该字符串分割成两个 非空 子字符串（即 左 子字符串和 右 子字符串）所能获得的最大得分。
 * <p>
 * 「分割字符串的得分」为 左 子字符串中 0 的数量加上 右 子字符串中 1 的数量。
 * 
 * 
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "011101"<br>
 * 输出：5 <br>
 * 解释：<br>
 * 将字符串 s 划分为两个非空子字符串的可行方案有：<br>
 * 左子字符串 = "0" 且 右子字符串 = "11101"，得分 = 1 + 4 = 5 <br>
 * 左子字符串 = "01" 且 右子字符串 = "1101"，得分 = 1 + 3 = 4 <br>
 * 左子字符串 = "011" 且 右子字符串 = "101"，得分 = 1 + 2 = 3 <br>
 * 左子字符串 = "0111" 且 右子字符串 = "01"，得分 = 1 + 1 = 2 <br>
 * 左子字符串 = "01110" 且 右子字符串 = "1"，得分 = 2 + 1 = 3
 * <p>
 * 示例 2：
 * <p>
 * 输入：s = "00111"<br>
 * 输出：5<br>
 * 解释：当 左子字符串 = "00" 且 右子字符串 = "111" 时，我们得到最大得分 = 2 + 3 = 5
 * <p>
 * 示例 3：
 * <p>
 * 输入：s = "1111"<br>
 * 输出：3
 * <p>
 * 提示：
 * <p>
 * 2 <= s.length <= 500 <br>
 * 字符串 s 仅由字符 '0' 和 '1' 组成。
 *
 * @author zm
 */
public class LEET5392 {
	public static void main(String[] args) {
		LEET5392 l5392 = new LEET5392();
		System.out.println(l5392.new Solution().maxScore("011101"));
		System.out.println(l5392.new Solution().maxScore("00111"));
		System.out.println(l5392.new Solution().maxScore("1111"));
	}

	class Solution {
		public int maxScore(String s) {
			int cnt0 = 0;
			char[] cs = s.toCharArray();
			for (int i = 0; i < cs.length; i++) {
				if (cs[i] == '0') {
					cnt0++;
				}
			}
			int cnt1 = cs.length - cnt0;
			int max = (cs[0] == '0' ? 1 : 0) + cnt1 - (cs[0] == '0' ? 0 : 1);
			int cur = max;
			for (int i = 1; i < cs.length -1; i++) {
				if (cs[i] == '1') {
					cur--;
				}else {
					cur++;
					max = Math.max(max, cur);
				}
			}
			return max;
		}
	}
}
