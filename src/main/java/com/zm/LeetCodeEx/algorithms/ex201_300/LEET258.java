package com.zm.LeetCodeEx.algorithms.ex201_300;

/**
 * 258. 各位相加
 * 给定一个非负整数 num，反复将各个位上的数字相加，直到结果为一位数。返回这个结果。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: num = 38
 * 输出: 2
 * 解释: 各位相加的过程为：
 * 38 --> 3 + 8 --> 11
 * 11 --> 1 + 1 --> 2
 * 由于 2 是一位数，所以返回 2。
 * 示例 1:
 * <p>
 * 输入: num = 0
 * 输出: 0
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= num <= 2^31 - 1
 *
 * @author zm
 */
public class LEET258 {
	public static void main(String[] args) {
		System.out.println(new Solution().addDigits(18));
	}

	/**
	 * 直接模拟
	 */
	static class Solution {
		public int addDigits(int num) {
			while (num > 9) {
				num = addAll(num);
			}
			return num;
		}

		private int addAll(int num) {
			int result = 0;
			while (num > 0) {
				result += num % 10;
				num /= 10;
			}
			return result;
		}
	}

	/**
	 * 数学方法可以证明num和最终的各位之和模9同余。
	 * https://leetcode-cn.com/problems/add-digits/solution/ge-wei-xiang-jia-by-leetcode-solution-u4kj/
	 */
	static class Solution2 {
		public int addDigits(int num) {
			return (num - 1) % 9 + 1;
		}
	}
}
