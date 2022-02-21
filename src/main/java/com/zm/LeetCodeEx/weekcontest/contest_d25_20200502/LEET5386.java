package com.zm.LeetCodeEx.weekcontest.contest_d25_20200502;

import java.util.PriorityQueue;

/**
 * 双周赛 2020年5月2日
 * <p>
 * 5386. 检查一个字符串是否可以打破另一个字符串
 * <p>
 * 给你两个字符串 s1 和 s2 ，它们长度相等，请你检查是否存在一个 s1  的排列可以打破 s2 的一个排列，或者是否存在一个 s2 的排列可以打破
 * s1 的一个排列。
 * <p>
 * 字符串 x 可以打破字符串 y （两者长度都为 n ）需满足对于所有 i（在 0 到 n - 1 之间）都有 x[i] >=
 * y[i]（字典序意义下的顺序）。
 * <p>
 * 示例 1：
 * <p>
 * 输入：s1 = "abc", s2 = "xya"<br>
 * 输出：true<br>
 * 解释："ayx" 是 s2="xya" 的一个排列，"abc" 是字符串 s1="abc" 的一个排列，且 "ayx" 可以打破 "abc" 。
 * <p>
 * 示例 2：
 * <p>
 * 输入：s1 = "abe", s2 = "acd"<br>
 * 输出：false <br>
 * 解释：s1="abe" 的所有排列包括："abe"，"aeb"，"bae"，"bea"，"eab" 和 "eba" ，s2="acd"
 * 的所有排列包括："acd"，"adc"，"cad"，"cda"，"dac" 和 "dca"。然而没有任何 s1 的排列可以打破 s2 的排列。也没有 s2
 * 的排列能打破 s1 的排列。
 * <p>
 * 示例 3：
 * <p>
 * 输入：s1 = "leetcodee", s2 = "interview"<br>
 * 输出：true  
 * <p>
 * 提示：
 * <p>
 * s1.length == n <br>
 * s2.length == n <br>
 * 1 <= n <= 10^5 <br>
 * 所有字符串都只包含小写英文字母。<br>
 * 
 * @author zm
 */
public class LEET5386 {
	public static void main(String[] args) {
		LEET5386 l5386 = new LEET5386();
		System.out.println(l5386.new Solution().checkIfCanBreak("abc", "xya"));
		System.out.println(l5386.new Solution().checkIfCanBreak("abe", "acd"));
		System.out.println(l5386.new Solution().checkIfCanBreak("leetcodee", "interview"));
	}

	class Solution {
		public boolean checkIfCanBreak(String s1, String s2) {
			PriorityQueue<Character> p1 = stringToPriQueue(s1);
			PriorityQueue<Character> p2 = stringToPriQueue(s2);
			char s1c1 = p1.poll();
			char s2c1 = p2.poll();
			int s1BiggerThanS2 = s1c1 > s2c1 ? 1 : s1c1 == s2c1 ? 0 : -1;
			for (int i = 0; i < s1.length() - 1; i++) {
				char s1ct = p1.poll();
				char s2ct = p2.poll();
				int tempStatus = s1ct > s2ct ? 1 : s1ct == s2ct ? 0 : -1;
				if (s1BiggerThanS2 == 0) {
					if (tempStatus != 0) {
						s1BiggerThanS2 = tempStatus;
					}
				} else {
					if (tempStatus != 0 && s1BiggerThanS2 != tempStatus) {
						return false;
					}
				}
			}
			return true;
		}

		private PriorityQueue<Character> stringToPriQueue(String s) {
			char[] cs = s.toCharArray();
			PriorityQueue<Character> priorityQueue = new PriorityQueue<>();
			for (int i = 0; i < cs.length; i++) {
				priorityQueue.add(cs[i]);
			}
			return priorityQueue;
		}
	}
}
