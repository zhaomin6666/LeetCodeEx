package com.zm.LeetCodeEx.weekcontest.contest_288_20220410;

import java.util.*;

/**
 * 6037. 按奇偶性交换后的最大数字
 * 给你一个正整数 num 。你可以交换 num 中 奇偶性 相同的任意两位数字（即，都是奇数或者偶数）。
 * <p>
 * 返回交换 任意 次之后 num 的 最大 可能值。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：num = 1234
 * 输出：3412
 * 解释：交换数字 3 和数字 1 ，结果得到 3214 。
 * 交换数字 2 和数字 4 ，结果得到 3412 。
 * 注意，可能存在其他交换序列，但是可以证明 3412 是最大可能值。
 * 注意，不能交换数字 4 和数字 1 ，因为它们奇偶性不同。
 * 示例 2：
 * <p>
 * 输入：num = 65875
 * 输出：87655
 * 解释：交换数字 8 和数字 6 ，结果得到 85675 。
 * 交换数字 5 和数字 7 ，结果得到 87655 。
 * 注意，可能存在其他交换序列，但是可以证明 87655 是最大可能值。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= num <= 109
 */
public class LEET6037 {
	public static void main(String[] args) {
		System.out.println(new Solution().largestInteger(1234));
		System.out.println(new Solution().largestInteger(65875));
		System.out.println(new Solution().largestInteger(427));
	}

	static class Solution {
		public int largestInteger(int num) {
			List<Integer> listOdd = new ArrayList<>();
			List<Integer> listEven = new ArrayList<>();
			List<Boolean> listIsOdd = new ArrayList<>();

			while (num > 0) {
				int cur = num % 10;
				if (cur % 2 == 1) {
					listOdd.add(cur);
					listIsOdd.add(true);
				}
				else {
					listEven.add(cur);
					listIsOdd.add(false);
				}
				num /= 10;
			}
			Collections.reverse(listIsOdd);
			listOdd.sort(Comparator.comparingInt(i -> -i));
			listEven.sort(Comparator.comparingInt(i -> -i));
			int result = 0;
			int evenIndex = 0;
			int oddIndex = 0;
			for (Boolean isOdd : listIsOdd) {
				result *= 10;
				if (isOdd) {
					result += listOdd.get(oddIndex++);
				}
				else {
					result += listEven.get(evenIndex++);
				}
			}
			return result;
		}
	}
}
