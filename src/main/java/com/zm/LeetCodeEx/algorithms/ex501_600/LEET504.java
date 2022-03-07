package com.zm.LeetCodeEx.algorithms.ex501_600;

/**
 * 504. 七进制数
 * 给定一个整数 num，将其转化为 7 进制，并以字符串形式输出。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: num = 100
 * 输出: "202"
 * 示例 2:
 * <p>
 * 输入: num = -7
 * 输出: "-10"
 * <p>
 * <p>
 * 提示：
 * <p>
 * -10^7 <= num <= 10^7
 *
 * @author zm
 */
public class LEET504 {
	public static void main(String[] args) {
		System.out.println(new Solution().convertToBase7(100));
		System.out.println(new Solution().convertToBase7(-7));
	}

	/**
	 * 如果两个字符串不一样，那么直接去较长的那个，如果一样则说明没有特殊序列
	 */
	static class Solution {
		public String convertToBase7(int num) {
			if (num == 0) {
				return "0";
			}
			boolean negative = num < 0;
			StringBuilder sb = new StringBuilder();
			if (num < 0) {
				num = -num;
			}
			while (num > 0) {
				sb.append(num % 7);
				num /= 7;
			}
			if (negative) {
				sb.append('-');
			}
			return sb.reverse().toString();
		}
	}
}
