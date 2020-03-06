package com.zm.LeetCodeEx.weekcontest;

import com.alibaba.fastjson.JSON;

/**
 * 周赛 2019年9月29日 5207. 尽可能使字符串相等
 * <p>
 * 给你两个长度相同的字符串，s 和 t。
 * 
 * 将 s 中的第 i 个字符变到 t 中的第 i 个字符需要 |s[i] - t[i]| 的开销（开销可能为 0），也就是两个字符的 ASCII
 * 码值的差的绝对值。
 * 
 * 用于变更字符串的最大预算是 maxCost。在转化字符串时，总开销应当小于等于该预算，这也意味着字符串的转化可能是不完全的。
 * 
 * 如果你可以将 s 的子字符串转化为它在 t 中对应的子字符串，则返回可以转化的最大长度。
 * 
 * 如果 s 中没有子字符串可以转化成 t 中对应的子字符串，则返回 0。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：s = "abcd", t = "bcdf", cost = 3 输出：3 解释：s 中的 "abc" 可以变为 "bcd"。开销为
 * 3，所以最大长度为 3。 示例 2：
 * 
 * 输入：s = "abcd", t = "cdef", cost = 3 输出：1 解释：s 中的任一字符要想变成 t 中对应的字符，其开销都是
 * 2。因此，最大长度为 1。 示例 3：
 * 
 * 输入：s = "abcd", t = "acde", cost = 0 输出：1 解释：你无法作出任何改动，所以最大长度为 1。
 * 
 * 
 * 提示：
 * 
 * 1 <= s.length, t.length <= 10^5 0 <= maxCost <= 10^6 s 和 t 都只含小写英文字母。
 * 
 * @author zm
 *
 */
public class LEET5207 {
	public static void main(String[] args) {
		LEET5207 l5207 = new LEET5207();
		String s = "abcd";
		String t = "acde";
		int maxCost = 3;
		System.out.println(l5207.equalSubstring(s, t, maxCost));
	}

	public int equalSubstring(String s, String t, int maxCost) {
		int[] costArray = new int[s.length()];
		for (int i = 0; i < costArray.length; i++) {
			costArray[i] = Math.abs(s.charAt(i) - t.charAt(i));
		}
		System.out.println(JSON.toJSON(costArray));
		int maxCostTemp = maxCost;
		int retNum = 0;
		int left = 0, right = 0;
		while (right < s.length()) {
			maxCostTemp -= costArray[right];
			if (maxCostTemp > 0) {
				retNum = Math.max(retNum, ++right - left);
			} else if (maxCostTemp == 0) {
				retNum = Math.max(retNum, ++right - left);
				maxCostTemp += costArray[left++];
			} else {
				right++;
				maxCostTemp += costArray[left++];
			}
		}

		return retNum;
	}
}
