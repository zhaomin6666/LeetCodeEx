package com.zm.LeetCodeEx;

import com.alibaba.fastjson.JSON;

/**
 * 给你一个仅由大写英文字母组成的字符串，你可以将任意位置上的字符替换成另外的字符，总共可最多替换 k 次。在执行上述操作后，找到包含重复字母的最长子串的长度。
 * </br>
 *
 * 注意:字符串长度 和 k 不会超过 10^4。 </br>
 *
 * 示例 1: </br>
 *
 * 输入:s="ABAB",k=2 </br>
 *
 * 输出:4 </br>
 *
 * 解释:用两个'A'替换为两个'B',反之亦然。 </br>
 *
 * 示例 2: </br>
 *
 * 输入:s="AABABBA",k=1 </br>
 *
 * 输出:4 </br>
 *
 * 解释:将中间的一个'A'替换为'B',字符串变为"AABBBBA"。子串"BBBB"有最长重复字母,答案为 4。 </br>
 *
 *
 *
 * @author zm
 *
 */
public class LEET424 {
	public static void main(String[] args) {
		LEET424 l424 = new LEET424();
		String s1 = "ABAA";
		int k1 = 0;
		System.out.println(JSON.toJSONString(l424.characterReplacement3(s1, k1)));
		String s2 = "AABBCA";
		int k2 = 2;
		System.out.println(JSON.toJSONString(l424.characterReplacement3(s2, k2)));
	}

	/**
	 * 计算滑动窗口中的众数，然后用窗口中所有的数减去众数出现的次数就是需要替换的个数，和k比较，
	 * 如果与k相等或k较大，那么可以扩大窗口大小，如果比k小，那么移动窗口
	 *
	 * @param s
	 * @param k
	 * @return
	 */
	public int characterReplacement(String s, int k) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		if (s.length() == 1) {
			return 1;
		}
		if (k >= s.length() - 1) {
			return s.length();
		}
		int l = 0, r = 0;
		int windowlength = 0;
		while (r < s.length()) {
			int[] windowcount = new int[26];
			for (int i = l; i <= r; i++) {
				windowcount[s.charAt(i) - 'A']++;
			}

			int max = windowcount[0];
			for (int i = 1; i < windowcount.length; i++) {
				if (windowcount[i] > max) {
					max = windowcount[i];
				}
			}
			if (r - l + 1 - max > k) {
				l++;
				r++;
			} else {
				r++;
				windowlength = r - l;
			}
		}
		return windowlength;
	}

	/**
	 * 优化窗口计数，不每次都重新计算
	 *
	 * @param s
	 * @param k
	 * @return
	 */
	public int characterReplacement2(String s, int k) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		if (s.length() == 1) {
			return 1;
		}
		if (k >= s.length() - 1) {
			return s.length();
		}
		int l = 0, r = 0;
		int windowlength = 0;
		int[] windowcount = new int[26];
		while (r < s.length()) {
			windowcount[s.charAt(r) - 'A']++;
			int max = windowcount[0];
			for (int i = 1; i < windowcount.length; i++) {
				if (windowcount[i] > max) {
					max = windowcount[i];
				}
			}
			if (r - l + 1 - max > k) {
				windowcount[s.charAt(l++) - 'A']--;
				r++;
			} else {
				r++;
				windowlength = r - l;
			}
		}
		return windowlength;
	}

	/**
	 * 优化窗口中取最大数
	 *
	 * @param s
	 * @param k
	 * @return
	 */
	public int characterReplacement3(String s, int k) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		if (s.length() == 1) {
			return 1;
		}
		if (k >= s.length() - 1) {
			return s.length();
		}
		int l = 0, r = 0; // 左右指针
		int windowlength = 0; // 校验成功的窗口长度
		int last = s.charAt(0); // 上一次的出现的最多的字母
		boolean lastsuc = true;
		int[] windowcount = new int[26];
		while (r < s.length()) {
			windowcount[s.charAt(r) - 'A']++; // 先把当前字母的计数+1
			int max = 0;
			// 判断当前字母的次数和之前出现的最多的字母的次数，获取最大出现次数
			// 情形1：如果是上一窗口满足了条件，扩大窗口大小，那么窗口中次数最多的字母只有可能是上一窗口中最多的字母或新加入的字母
			// 情形2：如果是上一窗口不满足条件，移动窗口，那么如果新加入的字母不能成为次数最多的字母，这个窗口必然无法满足条件
			if (windowcount[s.charAt(r) - 'A'] > windowcount[last - 'A'] || s.charAt(r) == last) {
				max = windowcount[s.charAt(r) - 'A'];
				last = s.charAt(r);
			} else {
				if (!lastsuc) {
					// 必然无法满足条件，直接移动窗口
					windowcount[s.charAt(l++) - 'A']--;
					r++;
					continue;
				}
				max = windowcount[last - 'A'];
			}

			// 校验是否符合条件
			if (r - l + 1 - max > k) {
				windowcount[s.charAt(l++) - 'A']--;
				r++;
				lastsuc = false;
			} else {
				r++;
				windowlength = r - l;
				lastsuc = true;
			}
		}
		return windowlength;
	}
}
