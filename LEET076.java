package LeetCode;

import java.util.HashMap;

/**
 * 给定一个字符串 S 和一个字符串 T，请在 S 中找出包含 T 所有字母的最小子串。
 * 
 * 示例：
 * 
 * 输入: S = "ADOBECODEBANC", T = "ABC" 输出: "BANC" 说明：
 * 
 * 如果 S 中不存这样的子串，则返回空字符串 ""。 如果 S 中存在这样的子串，我们保证它是唯一的答案。
 * 
 * @author zm
 *
 */
public class LEET076 {
	public static void main(String[] args) {
		LEET076 l076 = new LEET076();
		String s1 = "ADOBECODEBANCz";
		String s2 = "ABCz";
		//String s3 = "BAB";
		//String s4 = "A";
		System.out.println(l076.minWindow2(s1, s2));
		// 测试下自己写的第二种方法和网上答案中比较快的进行下比较，时间差不多
		long a = System.currentTimeMillis();
		for (int i = 0; i < 10000000; i++) {
			l076.minWindow3(s1, s2);
		}
		long b = System.currentTimeMillis();
		System.out.println(b-a);
	}

	public String minWindow(String s, String t) {
		HashMap<Character, Integer> tmap = CommonFunctions
				.convertStringToMap(t);
		HashMap<Character, Integer> win = new HashMap<>();
		int minlen = s.length() + 1;
		String ret = "";
		int l = 0;
		for (int r = 0; r < s.length() && l < s.length(); r++) {
			if (tmap.get(s.charAt(r)) == null) {
				continue;
			}

			if (win.get(s.charAt(r)) == null) {
				win.put(s.charAt(r), 1);
			} else {
				win.put(s.charAt(r), win.get(s.charAt(r)) + 1);
			}

			// 判断两个map的符合程度
			boolean flag = true;
			for (Character c : tmap.keySet()) {
				if (tmap.get(c) == null || tmap
						.get(c) > (win.get(c) == null ? 0 : win.get(c))) {
					flag = false;
					break;
				}
			}
			if (flag) {
				if (r - l + 1 < minlen) {
					minlen = r - l + 1;
					ret = s.substring(l, r + 1);
				}
			}
			boolean flag1 = l < r && win.get(s.charAt(l)) != null
					&& win.get(s.charAt(l)) > (tmap.get(s.charAt(l)) == null
							? 0
							: tmap.get(s.charAt(l)));
			boolean flag2 = l < s.length() && l < r
					&& tmap.get(s.charAt(l)) == null;
			boolean flag4 = false;
			while (flag1 || flag2) {
				flag4 = true;
				if (flag1) {
					win.put(s.charAt(l), win.get(s.charAt(l)) - 1);
				}
				l++;
				flag1 = l < r && win.get(s.charAt(l)) != null
						&& win.get(s.charAt(l)) > (tmap.get(s.charAt(l)) == null
								? 0
								: tmap.get(s.charAt(l)));
				flag2 = l < s.length() && l < r
						&& tmap.get(s.charAt(l)) == null;
			}
			if (flag4 && flag) {
				if (r - l < minlen) {
					minlen = r - l + 1;
					ret = s.substring(l, r + 1);
				}
			}
		}
		return ret;
	}

	/**
	 * 换用数组，提升速度
	 * @param s
	 * @param t
	 * @return
	 */
	public String minWindow2(String s, String t) {
		int[] tmap = new int[58];
		// 初始化tmap
		for (int i = 0; i < t.length(); i++) {
			tmap[t.charAt(i) - 'A']++;
		}
		int[] win = new int[58];
		int minlen = s.length() + 1;
		String ret = "";
		int l = 0;
		for (int r = 0; r < s.length() && l < s.length(); r++) {
			if (tmap[s.charAt(r) - 'A'] == 0) {
				continue;
			}
			win[s.charAt(r) - 'A'] = win[s.charAt(r) - 'A'] + 1;

			// 判断两个map的符合程度
			boolean flag = true;
			for (int i = 0; i < 58; i++) {
				if (tmap[i] > win[i]) {
					flag = false;
					break;
				}
			}
			if (flag) {
				if (r - l + 1 < minlen) {
					minlen = r - l + 1;
					ret = s.substring(l, r + 1);
				}
			}
			while (l <= r) {
				if (tmap[s.charAt(l) - 'A'] > 0) {
					if (win[s.charAt(l) - 'A'] > tmap[s.charAt(l) - 'A']) {
						win[s.charAt(l) - 'A']--;
						l++;
					} else {
						if (flag) {
							if (r - l + 1 < minlen) {
								minlen = r - l + 1;
								ret = s.substring(l, r + 1);
							}
						}
						break;
					}
				} else {
					l++;
				}
			}
		}
		return ret;
	}

	/**
	 * 网站上所有提交中找的较快的，时间差不多
	 * @param s
	 * @param t
	 * @return
	 */
	public String minWindow3(String s, String t) {
		int lenS = s.length(), lenT = t.length();
		if (lenS < lenT) {
			return "";
		}
		int[] tCount = new int[256];
		for (int i = 0; i < lenT; i++) {
			tCount[t.charAt(i)]++;
		}
		int[] sCount = new int[256];
		int left = 0, right = 0;
		int count = 0; // 保存窗口中等于字符串t的字符的数量，当count等于lenT时，说明找到一个子串
		int minLen = lenS + 1, start = -1;
		while (left < lenS) {
			if (right < lenS && count < lenT) { // right右滑一格
				char charRight = s.charAt(right);
				sCount[charRight]++;
				if (sCount[charRight] <= tCount[charRight]) {
					count++;
				}
				right++;
			} else {
				char charLeft = s.charAt(left);
				if (count == lenT && right - left < minLen) { // 更新最小字串的长度和起始索引
					minLen = right - left;
					start = left;
				}
				// left左滑一格
				sCount[charLeft]--;
				if (sCount[charLeft] < tCount[charLeft]) {
					count--;
				}
				left++;
			}
		}
		if (start != -1) {
			return s.substring(start, start + minLen);
		}
		return "";
	}

}
