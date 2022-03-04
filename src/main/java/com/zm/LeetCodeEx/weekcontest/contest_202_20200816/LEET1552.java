package com.zm.LeetCodeEx.weekcontest.contest_202_20200816;

import java.util.Arrays;

/**
 * 1552. 两球之间的磁力
 * 在代号为 C-137 的地球上，Rick 发现如果他将两个球放在他新发明的篮子里，它们之间会形成特殊形式的磁力。Rick 有 n 个空的篮子，第 i 个篮子的位置在 position[i] ，Morty 想把 m 个球放到这些篮子里，使得任意两球间 最小磁力 最大。
 * <p>
 * 已知两个球如果分别位于 x 和 y ，那么它们之间的磁力为 |x - y| 。
 * <p>
 * 给你一个整数数组 position 和一个整数 m ，请你返回最大化的最小磁力。
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：position = [1,2,3,4,7], m = 3
 * 输出：3
 * 解释：将 3 个球分别放入位于 1，4 和 7 的三个篮子，两球间的磁力分别为 [3, 3, 6]。最小磁力为 3 。我们没办法让最小磁力大于 3 。
 * 示例 2：
 * <p>
 * 输入：position = [5,4,3,2,1,1000000000], m = 2
 * 输出：999999999
 * 解释：我们使用位于 1 和 1000000000 的篮子时最小磁力最大。
 * <p>
 * <p>
 * 提示：
 * <p>
 * n == position.length
 * 2 <= n <= 10^5
 * 1 <= position[i] <= 10^9
 * 所有 position 中的整数 互不相同 。
 * 2 <= m <= position.length
 *
 * @author zm
 * @version 1.0
 * @date 2022-3-4
 * @since 1.8
 */
public class LEET1552 {
	public static void main(String[] args) {
		System.out.println(new Solution2().maxDistance(new int[]{1, 2, 3, 4, 7}, 3));
		System.out.println(new Solution2().maxDistance(new int[]{1, 2, 3, 7}, 3));
		System.out.println(new Solution2().maxDistance(new int[]{1, 2, 5, 7}, 3));
	}

	/**
	 * 递归模拟所有情况
	 * 超时
	 */
	static class Solution {
		int max = 0;
		int[] pos;

		public int maxDistance(int[] position, int m) {
			Arrays.sort(position);
			this.pos = position;
			helper(0, m, Integer.MAX_VALUE, 0);
			return max;
		}

		private void helper(int curIndex, int leftBalls, int thisMin, int lastPos) {
			if (leftBalls == 0) {
				max = Math.max(max, thisMin);
				return;
			}
			for (int i = curIndex; i < pos.length; i++) {
				int curMin = thisMin;
				if (curIndex != 0) {
					curMin = Math.min(thisMin, pos[i] - lastPos);
				}
				helper(i + 1, leftBalls - 1, curMin, pos[i]);
			}
		}
	}

	/**
	 * 二分法判断间隔值是否满足情况
	 */
	static class Solution2 {
		public int maxDistance(int[] position, int m) {
			Arrays.sort(position);
			int maxInterval = position[position.length - 1] - position[0];
			if (m == 2) {
				return maxInterval;
			}
			int maxDistance = maxInterval / (m - 1);
			int result = 1;
			// 二分判断能否成功
			int l = 1, r = maxDistance;
			while (l <= r) {
				int mid = (l + r) >>> 1;
				// 检查mid是否可以
				int leftBalls = m - 1;
				int checkIndex = 1;
				int check = position[0];
				while (leftBalls > 0) {
					// 如果下标都超出了，球都没放完那就这个mid就不行
					if (checkIndex == position.length) {
						break;
					}
					// 如果这个当前位置距离上一个位置的距离大于检测值，说明可以放球
					if (position[checkIndex] - check >= mid) {
						check = position[checkIndex];
						leftBalls--;
					}
					checkIndex++;
				}
				if (leftBalls == 0) {
					result = mid;
					l = mid + 1;
				}
				else {
					r = mid - 1;
				}
			}
			return result;
		}
	}
}
