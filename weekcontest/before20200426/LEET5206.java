package com.zm.LeetCodeEx.weekcontest.before20200426;

import java.util.LinkedList;

/**
 * 周赛 2019年9月29日 5206. 删除字符串中的所有相邻重复项 II
 * <p>
 * 给你一个字符串 s，「k 倍重复项删除操作」将会从 s 中选择 k 个相邻且相等的字母，并删除它们，使被删去的字符串的左侧和右侧连在一起。
 * 
 * 你需要对 s 重复进行无限次这样的删除操作，直到无法继续为止。
 * 
 * 在执行完所有删除操作后，返回最终得到的字符串。
 * 
 * 本题答案保证唯一。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：s = "abcd", k = 2 输出："abcd" 解释：没有要删除的内容。 示例 2：
 * 
 * 输入：s = "deeedbbcccbdaa", k = 3 输出："aa" 解释： 先删除 "eee" 和 "ccc"，得到 "ddbbbdaa"
 * 再删除 "bbb"，得到 "dddaa" 最后删除 "ddd"，得到 "aa" 示例 3：
 * 
 * 输入：s = "pbbcggttciiippooaais", k = 2 输出："ps"
 * 
 * 
 * 提示：
 * 
 * 1 <= s.length <= 10^5 2 <= k <= 10^4 s 中只含有小写英文字母。
 * 
 * @author zm
 *
 */
public class LEET5206 {
	public static void main(String[] args) {
		LEET5206 l5206 = new LEET5206();
		String s = "deeedbbcccbdaa";
		int k = 3;
		System.out.println(l5206.removeDuplicates(s, k));
	}

	/**
	 * 待优化，感觉结果应该没问题
	 * @param s
	 * @param k
	 * @return
	 */
	public String removeDuplicates(String s, int k) {
		int left = 0;
		int right = k;
		LinkedList<Character> list = new LinkedList<>();
		for (int i = 0; i < s.length(); i++) {
			list.add(s.charAt(i));
		}
		while (right < list.size()) {
			int temp = list.get(left);
			boolean isDup = true;
			loop: for (int i = left; i < right; i++) {
				if (temp != list.get(i)) {
					isDup = false;
					break loop;
				}
			}
			if (isDup) {
				for (int i = 0; i < k; i++) {
					list.remove(left);
				}
				if (left > 0) {
					char temp2 = list.get(left - 1);
					loop2: for (int i = left - 2; i >= 0; i--) {
						if (temp2 != list.get(i)) {
							break loop2;
						} else {
							left--;
						}
					}
					left--;
					right = left + k;
				}
			} else {
				left++;
				right++;
			}
		}
		StringBuffer stringBuffer = new StringBuffer();
		for (Character character : list) {
			stringBuffer.append(character);
		}
		return stringBuffer.toString();
	}

}
