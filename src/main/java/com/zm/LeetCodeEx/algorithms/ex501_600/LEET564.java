package com.zm.LeetCodeEx.algorithms.ex501_600;

/**
 * 564. 寻找最近的回文数
 * 给定一个表示整数的字符串 n ，返回与它最近的回文整数（不包括自身）。如果不止一个，返回较小的那个。
 * <p>
 * “最近的”定义为两个整数差的绝对值最小。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: n = "123"
 * 输出: "121"
 * 示例 2:
 * <p>
 * 输入: n = "1"
 * 输出: "0"
 * 解释: 0 和 2是最近的回文，但我们返回最小的，也就是 0。
 * <p>
 * <p>
 * 提示:
 * <p>
 * 1 <= n.length <= 18
 * n 只由数字组成
 * n 不含前导 0
 * n 代表在 [1, 10^18 - 1] 范围内的整数
 *
 * @author zm
 */
public class LEET564 {
	public static void main(String[] args) {
		System.out.println(new Solution().nearestPalindromic("1"));
		System.out.println(new Solution().nearestPalindromic("11"));
		System.out.println(new Solution().nearestPalindromic("12"));
		System.out.println(new Solution().nearestPalindromic("101"));
		System.out.println(new Solution().nearestPalindromic("123"));
		System.out.println(new Solution().nearestPalindromic("151"));
		System.out.println(new Solution().nearestPalindromic("157"));
		System.out.println(new Solution().nearestPalindromic("201"));
		System.out.println(new Solution().nearestPalindromic("257"));
		System.out.println(new Solution().nearestPalindromic("1001"));
		System.out.println(new Solution().nearestPalindromic("1283"));
		System.out.println(new Solution().nearestPalindromic("1991"));
		System.out.println(new Solution().nearestPalindromic("10901"));
		System.out.println(new Solution().nearestPalindromic("19091"));
		System.out.println(new Solution().nearestPalindromic("2147483647"));
		System.out.println(new Solution().nearestPalindromic("807045053224792883"));
	}

	static class Solution {
		public String nearestPalindromic(String n) {
			long intN = Long.parseLong(n);
			if (intN <= 10) {
				return "" + (Long.parseLong(n) - 1);
			}
			long zs = (long) Math.pow(10, Math.floor(Math.log10(intN * 1.0)));
			if (zs == intN || intN == zs + 1) {
				return "" + (zs - 1);
			}

			// 大于11的两位数数字，判断离11,22,33...99。哪个更近，如果就是本身则-11。
			if (intN < 99) {
				if (intN % 11 == 0) {
					return "" + (intN - 11);
				}
				else if (intN % 11 < 6) {
					return "" + intN / 11 * 11;
				}
				else {
					return "" + (intN / 11 + 1) * 11;
				}
			}
			if (intN == 99) {
				return "101";
			}

			// 从前往后回文
			char[] cs = pal(n);

			long currentPal = Long.parseLong(new String(cs));
			long isEqual = currentPal - intN;
			// 那么获取上一个和下一个回文字符串
			long lastOne;
			long nextOne;
			long add = ((cs[(cs.length + 1) / 2 - 1] == '9' && cs.length % 2 == 0 ? 0 : 10)
					+ (cs.length + 1) % 2) * (long) Math.pow(10, ((cs.length) >> 1) - 1);
			long minus = ((cs[(cs.length + 1) / 2 - 1] == '0' && cs.length % 2 == 0 ? 0 : 10)
					+ (cs.length + 1) % 2) * (long) Math.pow(10, ((cs.length) >> 1) - 1);
			if (isEqual > 0) {
				lastOne = currentPal - minus;
				nextOne = currentPal;
			}
			else if (isEqual == 0) {
				lastOne = currentPal - minus;
				nextOne = currentPal + add;
			}
			else {
				lastOne = currentPal;
				nextOne = currentPal + add;
			}
			long lastOneFinal = palLong(lastOne);
			long nextOneFinal = palLong(nextOne);
			// System.out.println("n:" + n + ", lastOne:" + lastOneFinal + ", nextOne:" + nextOneFinal);
			return "" + (nextOneFinal - intN < intN - lastOneFinal ?
					nextOneFinal : lastOneFinal);
		}

		public long palLong(long num) {
			int n = 0;
			long reverse = 0;
			long raw = num;
			while (num > 0) {
				reverse *= 10;
				reverse += num % 10;
				num /= 10;
				n++;
			}
			long mid = (long) Math.pow(10, n >> 1);
			return raw / mid * mid + reverse % mid;
		}

		private char[] pal(String n) {
			char[] cs = n.toCharArray();
			for (int i = 0; i <= cs.length >> 1; i++) {
				cs[cs.length - i - 1] = cs[i];
			}
			return cs;
		}
	}
}
