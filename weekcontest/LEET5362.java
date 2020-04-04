package com.zm.LeetCodeEx.weekcontest;

/**
 * 双周赛 2020年4月4日
 * <p>
 * 5362. 构造 K 个回文字符串
 * <p>
 * 给你一个字符串 s 和一个整数 k 。请你用 s 字符串中 所有字符 构造 k 个非空 回文串 。
 * <p>
 * 如果你可以用 s 中所有字符构造 k 个回文字符串，那么请你返回 True ，否则返回 False 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "annabelle", k = 2 <br>
 * 输出：true <br>
 * 解释：可以用 s 中所有字符构造 2 个回文字符串。 <br>
 * 一些可行的构造方案包括："anna" + "elble"，"anbna" + "elle"，"anellena" + "b"
 * <p>
 * 示例 2：
 * <p>
 * 输入：s = "leetcode", k = 3 <br>
 * 输出：false <br>
 * 解释：无法用 s 中所有字符构造 3 个回文串。
 * <p>
 * 示例 3：
 * <p>
 * 输入：s = "true", k = 4 <br>
 * 输出：true <br>
 * 解释：唯一可行的方案是让 s 中每个字符单独构成一个字符串。
 * <p>
 * 示例 4：
 * <p>
 * 输入：s = "yzyzyzyzyzyzyzy", k = 2 <br>
 * 输出：true <br>
 * 解释：你只需要将所有的 z 放在一个字符串中，所有的 y 放在另一个字符串中。那么两个字符串都是回文串。
 * <p>
 * 示例 5：
 * <p>
 * 输入：s = "cr", k = 7 <br>
 * 输出：false <br>
 * 解释：我们没有足够的字符去构造 7 个回文串。
 * 
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 10^5 <br>
 * s 中所有字符都是小写英文字母。 <br>
 * 1 <= k <= 10^5
 *
 * @author zm
 */
public class LEET5362 {
	public static void main(String[] args) {
		LEET5362 l5362 = new LEET5362();
		System.out.println(l5362.new Solution().canConstruct("annabelle", 2));
		System.out.println(l5362.new Solution().canConstruct("leetcode", 3));
		System.out.println(l5362.new Solution().canConstruct("true", 4));
		System.out.println(l5362.new Solution().canConstruct("yzyzyzyzyzyzyzy", 2));
		System.out.println(l5362.new Solution().canConstruct("cr", 7));

	}

	class Solution {
		public boolean canConstruct(String s, int k) {
			if (s.length() < k) {
				return false;
			}
			int[] map = new int[26];
			char[] cs = s.toCharArray();
			for (int i = 0; i < cs.length; i++) {
				map[cs[i] - 'a']++;
			}
			int odd = 0; // 奇数
			for (int i = 0; i < map.length; i++) {
				if (map[i] % 2 != 0) {
					odd++;
				}
			}
			return odd <= k;
		}
	}
}
