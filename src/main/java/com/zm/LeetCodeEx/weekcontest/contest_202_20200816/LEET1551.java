package com.zm.LeetCodeEx.weekcontest.contest_202_20200816;

/**
 * 1551. 使数组中所有元素相等的最小操作数
 * 存在一个长度为 n 的数组 arr ，其中 arr[i] = (2 * i) + 1 （ 0 <= i < n ）。
 * <p>
 * 一次操作中，你可以选出两个下标，记作 x 和 y （ 0 <= x, y < n ）并使 arr[x] 减去 1 、arr[y] 加上 1 （即 arr[x] -=1 且 arr[y] += 1 ）。最终的目标是使数组中的所有元素都 相等 。题目测试用例将会 保证 ：在执行若干步操作后，数组中的所有元素最终可以全部相等。
 * <p>
 * 给你一个整数 n，即数组的长度。请你返回使数组 arr 中所有元素相等所需的 最小操作数 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 3
 * 输出：2
 * 解释：arr = [1, 3, 5]
 * 第一次操作选出 x = 2 和 y = 0，使数组变为 [2, 3, 4]
 * 第二次操作继续选出 x = 2 和 y = 0，数组将会变成 [3, 3, 3]
 * 示例 2：
 * <p>
 * 输入：n = 6
 * 输出：9
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 10^4
 *
 * @author zm
 * @version 1.0
 * @date 2022-3-4
 * @since 1.8
 */
public class LEET1551 {
	public static void main(String[] args) {
		System.out.println(new Solution().minOperations(3));
		System.out.println(new Solution().minOperations(6));
	}

	/**
	 * 如果是奇数，两边的都要变成中间的。如果是偶数，两边的都要变成中间两个的平均值。
	 * 如135都是变成3，1357都要变成4。
	 */
	static class Solution {
		public int minOperations(int n) {
			if (n % 2 == 0) {
				return (1 + n - 1) * n / 2 / 2;
			}
			else {
				return (n / 2 * 2) * (n + 1) / 2 / 2;
			}
		}
	}
}
