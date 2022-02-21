package com.zm.LeetCodeEx.lcci;

/**
 * 面试题 01.06. 字符串压缩
 * <p>
 * 字符串压缩。利用字符重复出现的次数，编写一种方法，实现基本的字符串压缩功能。比如，字符串aabcccccaaa会变为a2b1c5a3。若“压缩”后的字符串没有变短，则返回原先的字符串。你可以假设字符串中只包含大小写英文字母（a至z）。
 * <p>
 * 示例1:<br>
 * 输入："aabcccccaaa"<br>
 * 输出："a2b1c5a3"
 * <p>
 * 示例2:<br>
 * 输入："abbccd"<br>
 * 输出："abbccd"<br>
 * 解释："abbccd"压缩后为"a1b2c2d1"，比原字符串长度更长。
 * <p>
 * 提示：<br>
 * 字符串长度在[0, 50000]范围内。
 * 
 * @author zm
 *
 */
public class Lcci0106 {
	public static void main(String[] args) {
		Lcci0106 lcci0106 = new Lcci0106();
		System.out.println(lcci0106.new Solution().compressString("aabcccccaaa"));
		System.out.println(lcci0106.new Solution().compressString("abbccd"));
	}

	class Solution {
		public String compressString(String S) {
			StringBuilder stringBuilder = new StringBuilder();
			char[] chars = S.toCharArray();
			if (chars.length == 0) {
				return "";
			}
			int cnt = 1;
			char c = chars[0];
			for (int i = 1; i < chars.length; i++) {
				if (chars[i] == c) {
					cnt++;
				} else {
					stringBuilder.append(c).append(cnt);
					c = chars[i];
					cnt = 1;
				}
			}
			stringBuilder.append(c).append(cnt);
			return chars.length > stringBuilder.length() ? stringBuilder.toString() : S;
		}
	}
}
