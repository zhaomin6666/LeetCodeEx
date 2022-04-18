package com.zm.LeetCodeEx.algorithms.ex301_400;

import java.util.ArrayList;
import java.util.List;

/**
 * 386. 字典序排数
 * 给你一个整数 n ，按字典序返回范围 [1, n] 内所有整数。
 * <p>
 * 你必须设计一个时间复杂度为 O(n) 且使用 O(1) 额外空间的算法。
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 13
 * 输出：[1,10,11,12,13,2,3,4,5,6,7,8,9]
 * 示例 2：
 * <p>
 * 输入：n = 2
 * 输出：[1,2]
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 5 * 10^4
 *
 * @author zm
 */
public class LEET386 {
	public static void main(String[] args) {
		System.out.println((new Solution().lexicalOrder(13)));
		System.out.println((new Solution().lexicalOrder(2)));
		System.out.println((new Solution2().lexicalOrder(191)));
	}

	/**
	 * 递归实现，递归并不是空间复杂度O(1)
	 */
	static class Solution {
		List<Integer> result = new ArrayList<>();
		int digitCount = 0;
		int n;

		public List<Integer> lexicalOrder(int n) {
			this.n = n;
			int digitCountN = n;
			while (digitCountN > 0) {
				++digitCount;
				digitCountN /= 10;
			}
			doCreate(0, 0);
			return result;
		}

		private void doCreate(int currentDigit, int currentNumber) {
			if (currentDigit >= digitCount) {
				return;
			}
			for (int i = currentDigit == 0 ? 1 : 0; i < 10; i++) {
				currentNumber += i;
				if (currentNumber > n) {
					break;
				}
				result.add(currentNumber);
				doCreate(currentDigit + 1, currentNumber * 10);
				currentNumber -= i;
			}
		}
	}

	static class Solution2 {
		public List<Integer> lexicalOrder(int n) {
			List<Integer> result = new ArrayList<>();
			int number = 1;
			for (int i = 0; i < n; i++) {
				result.add(number);
				if (number * 10 <= n) {
					// 如果可以在后面加0，那么直接加0
					number *= 10;
				}
				else {
					while (number % 10 == 9 || number + 1 > n) {
						// 需要进位的情况，如number=129，下一位是13，需要/=10再+1。
						number /= 10;
					}
					number++;
				}
			}
			return result;
		}
	}
}
