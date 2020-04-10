package com.zm.LeetCodeEx.algorithms.ex101_200;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * 151. 翻转字符串里的单词
 * <p>
 * 给定一个字符串，逐个翻转字符串中的每个单词。
 * <p>
 * 示例 1：
 * <p>
 * 输入: "the sky is blue"<br>
 * 输出: "blue is sky the"
 * <p>
 * 示例 2：
 * <p>
 * 输入: "  hello world!  "<br>
 * 输出: "world! hello"<br>
 * 解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * <p>
 * 示例 3：
 * <p>
 * 输入: "a good   example"<br>
 * 输出: "example good a"<br>
 * 解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 * 
 * 
 * 
 * @author zm
 */
public class LEET151 {
	public static void main(String[] args) {
		LEET151 l151 = new LEET151();
		System.out.println(l151.new Solution().reverseWords("the sky is blue"));
		System.out.println(l151.new Solution().reverseWords("  hello world!  "));
		System.out.println(l151.new Solution().reverseWords("a good   example"));
		System.out.println(l151.new Solution().reverseWords("  "));
		System.out.println(l151.new Solution().reverseWords(""));

	}

	class Solution {
		public String reverseWords(String s) {
			String trimString = s.trim();
			List<String> strings = Arrays.asList(trimString.split("\\s+"));
			Collections.reverse(strings);
			return String.join(" ", strings);
		}
	}

	class Solution2 {
		public String reverseWords(String s) {
			// String arr[] = s.trim().split("\\s+");
			// StringBuilder sb = new StringBuilder("");
			// for (int i = arr.length - 1; i >= 0; i--) {
			// sb.append(arr[i]).append(" ");
			// }
			// 下面这段效率比上面的高
			String arr[] = s.split(" ");
			StringBuilder sb = new StringBuilder("");
			for (int i = arr.length - 1; i >= 0; i--) {
				if ("".equals(arr[i])) {
					continue;
				}
				sb.append(arr[i]).append(" ");
			}
			String res = sb.toString();
			if ("".equals(res)) {
				return res;
			}
			return res.substring(0, res.length() - 1);
		}
	}

	class Solution3 {
		public String reverseWords(String s) {
			int len = s.length();
			if (len == 0) {
				return "";
			}
			char[] cs = s.toCharArray();
			Stack<String> stack = new Stack<>();
			StringBuilder sb = new StringBuilder();
			boolean hasChar = false;
			for (int i = 0; i < len; i++) {
				// System.out.println(cs[i] == ' ');
				if (cs[i] == ' ') {
					if (hasChar) {
						stack.add(sb.toString());
						sb = new StringBuilder();
						hasChar = false;
					}
				} else {
					sb.append(cs[i]);
					hasChar = true;
				}
			}
			if (hasChar) {
				stack.add(sb.toString());
			}
			StringBuilder retSb = new StringBuilder();
			while (!stack.isEmpty()) {
				String temp = stack.pop();
				retSb.append(temp).append(" ");
			}
			return retSb.length() == 0 ? "" : retSb.substring(0, retSb.length() - 1);
		}
	}
}
