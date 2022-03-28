package com.zm.LeetCodeEx.algorithms.ex601_700;

/**
 * 693. 交替位二进制数
 * 给定一个正整数，检查它的二进制表示是否总是 0、1 交替出现：换句话说，就是二进制表示中相邻两位的数字永不相同。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 5
 * 输出：true
 * 解释：5 的二进制表示是：101
 * 示例 2：
 * <p>
 * 输入：n = 7
 * 输出：false
 * 解释：7 的二进制表示是：111.
 * 示例 3：
 * <p>
 * 输入：n = 11
 * 输出：false
 * 解释：11 的二进制表示是：1011.
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 2^31 - 1
 *
 * @author zm
 */
public class LEET693 {
	public static void main(String[] args) {
		System.out.println(new Solution().hasAlternatingBits(5));
		System.out.println(new Solution().hasAlternatingBits(7));
		System.out.println(new Solution().hasAlternatingBits(10));
		System.out.println(new Solution().hasAlternatingBits(11));
	}

	static class Solution {
		public boolean hasAlternatingBits(int n) {
			String bits = Integer.toBinaryString(n);
			char[] bitsChars = bits.toCharArray();
			boolean check = bitsChars[0] == '1';
			for (int i = 1; i < bits.length(); i++) {
				char current = bitsChars[i];
				if ((current == '1') == check) {
					return false;
				}
				else {
					check = !check;
				}
			}
			return true;
		}
	}

	static class Solution2 {
		public boolean hasAlternatingBits(int n) {
			int pre = 2;
			while (n > 0) {
				int cur = n % 2;
				if (cur == pre) {
					return false;
				}
				pre = cur;
				n /= 2;
			}
			return true;
		}
	}

	/**
	 * `  101010101
	 * ^   10101010
	 * =  111111111
	 * <p>
	 * `  111111111
	 * & 1000000000
	 * =          0
	 */
	static class Solution3 {
		public boolean hasAlternatingBits(int n) {
			int temp = n ^ (n >> 1);
			return (temp & (temp + 1)) == 0;
		}
	}
}
