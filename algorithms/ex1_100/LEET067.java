package com.zm.LeetCodeEx.algorithms.ex1_100;

import java.math.BigInteger;

/**
 * 67. 二进制求和
 * <p>
 * 给定两个二进制字符串，返回他们的和（用二进制表示）。
 * <p>
 * 输入为非空字符串且只包含数字 1 和 0。
 * <p>
 * 示例 1:
 * <p>
 * 输入: a = "11", b = "1" 输出: "100"
 * <p>
 * 示例 2:
 * <p>
 * 输入: a = "1010", b = "1011" 输出: "10101"
 * <p>
 *
 * @author zm
 */
public class LEET067 {
	public static void main(String[] args) {
		LEET067 l067 = new LEET067();
		System.out.println(l067.new Solution3().addBinary("111", "1"));
		System.out.println(l067.new Solution3().addBinary("101", "1"));
		System.out.println(l067.new Solution3().addBinary("1010", "1011"));
	}

	private class Solution {
		public String addBinary(String a, String b) {
			String ret = "";
			int k = 0;
			int i;
			for (i = 0; i < a.length() && i < b.length(); i++) {
				int sum = a.charAt(a.length() - i - 1) + b.charAt(b.length() - i - 1) + k;
				if (sum >= 98) {
					k = 1;
					ret = (sum - 98) + ret;
				} else {
					k = 0;
					ret = (sum - 96) + ret;
				}
			}
			if (i == a.length() && i == b.length()) {
				if (k == 1) {
					ret = k + ret;
				}
			} else {
				if (i == b.length() && i < a.length()) {
					b = a;
				}
				for (; i < b.length(); i++) {
					if (k == 1) {
						int sum = k + b.charAt(b.length() - i - 1);
						if (sum == 50) {
							k = 1;
							ret = 0 + ret;
						} else {
							k = 0;
							ret = (sum - 48) + ret;
						}
					} else {
						ret = b.substring(0, b.length() - i) + ret;
						break;
					}
				}
				if (k == 1) {
					ret = 1 + ret;
				}
			}
			return ret;
		}
	}

	/**
	 * 由于要照顾到较大的入参所以使用BigInteger，不然优先考虑使用Integer或Long
	 * 
	 * @author zm
	 *
	 */
	private class Solution2 {
		public String addBinary(String a, String b) {
			BigInteger x = new BigInteger(a, 2);
			BigInteger y = new BigInteger(b, 2);
			BigInteger zero = new BigInteger("0", 2);
			BigInteger carry, answer;
			while (y.compareTo(zero) != 0) {
				answer = x.xor(y);
				carry = x.and(y).shiftLeft(1);
				x = answer;
				y = carry;
			}
			return x.toString(2);
		}
	}

	/**
	 * 由于要照顾到较大的入参所以使用BigInteger，不然优先考虑使用Integer或Long 使用现成轮子
	 * 
	 * @author zm
	 *
	 */
	private class Solution3 {
		public String addBinary(String a, String b) {
			BigInteger x = new BigInteger(a, 2);
			BigInteger y = new BigInteger(b, 2);
			return x.add(y).toString(2);
		}
	}

	private class Solution4 {
		public String addBinary(String a, String b) {
			int n = a.length(), m = b.length();
			if (n < m) {
				// 保证 a长度大于等于b
				return addBinary(b, a);
			}
			int L = Math.max(n, m);

			StringBuilder sb = new StringBuilder();
			int carry = 0, j = m - 1;
			for (int i = L - 1; i > -1; --i) {
				// 前面保证了a长度大于等于b所以取出来的L为a.length()
				if (a.charAt(i) == '1') {
					++carry;
				}
				if (j > -1 && b.charAt(j--) == '1') {
					++carry;
				}

				if (carry % 2 == 1) {
					sb.append('1');
				} else {
					sb.append('0');
				}
				carry /= 2;
			}
			if (carry == 1) {
				sb.append('1');
			}
			sb.reverse();
			return sb.toString();
		}
	}

	/**
	 * 相比上面的没有去判断a,b哪个更长，而是在计算过程中如果某一字符串用完了就+0
	 * @author zm
	 *
	 */
	private class Solution5 {
		public String addBinary(String a, String b) {
			StringBuilder sb = new StringBuilder();
			int c = 0;
			for (int i = a.length() - 1, j = b.length() - 1; i >= 0 || j >= 0; i--, j--) {
				int sum = c;
				sum += i >= 0 ? a.charAt(i) - '0' : 0;
				sum += j >= 0 ? b.charAt(j) - '0' : 0;
				sb.append(sum % 2);
				c = sum / 2;
			}
			sb.append(c == 1 ? 1 : "");
			return sb.reverse().toString();
		}
	}
}
