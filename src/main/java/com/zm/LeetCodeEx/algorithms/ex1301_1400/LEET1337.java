package com.zm.LeetCodeEx.algorithms.ex1301_1400;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 1337. 矩阵中战斗力最弱的 K 行
 *
 * <p>
 * 给你一个大小为m* n的矩阵mat，矩阵由若干军人和平民组成，分别用 1 和 0 表示。
 * <p>
 * 请你返回矩阵中战斗力最弱的k行的索引，按从最弱到最强排序。
 * <p>
 * 如果第i行的军人数量少于第j行，或者两行军人数量相同但 i 小于 j，那么我们认为第 i 行的战斗力比第 j 行弱。
 * <p>
 * 军人 总是 排在一行中的靠前位置，也就是说 1 总是出现在 0 之前。
 * <p>
 *
 * <p>
 * 示例 1：
 * <p>
 * 输入：mat =
 * [[1,1,0,0,0],
 * [1,1,1,1,0],
 * [1,0,0,0,0],
 * [1,1,0,0,0],
 * [1,1,1,1,1]],
 * k = 3
 * 输出：[2,0,3]
 * 解释：
 * 每行中的军人数目：
 * 行 0 -> 2
 * 行 1 -> 4
 * 行 2 -> 1
 * 行 3 -> 2
 * 行 4 -> 5
 * 从最弱到最强对这些行排序后得到 [2,0,3,1,4]
 * 示例 2：
 * <p>
 * 输入：mat =
 * [[1,0,0,0],
 * [1,1,1,1],
 * [1,0,0,0],
 * [1,0,0,0]],
 * k = 2
 * 输出：[0,2]
 * 解释：
 * 每行中的军人数目：
 * 行 0 -> 1
 * 行 1 -> 4
 * 行 2 -> 1
 * 行 3 -> 1
 * 从最弱到最强对这些行排序后得到 [0,2,3,1]
 *
 * <p>
 * 提示：
 * <p>
 * m == mat.length
 * n == mat[i].length
 * 2 <= n, m <= 100
 * 1 <= k <= m
 * matrix[i][j] 不是 0 就是 1
 *
 * @author zm
 */
public class LEET1337 {
	public static void main(String[] args) {
		System.out.println(Arrays.toString(new Solution().kWeakestRows(new int[][]{{1, 1, 0, 0, 0},
				{1, 1, 1, 1, 0},
				{1, 0, 0, 0, 0},
				{1, 1, 0, 0, 0},
				{1, 1, 1, 1, 1}}, 3)));
	}

	/**
	 * 计算每个队列的军人数量并记录i，记录在数组中。使用优先队列进行排序取前n位
	 */
	static class Solution {
		public int[] kWeakestRows(int[][] mat, int k) {
			PriorityQueue<int[]> pq = new PriorityQueue<>((a1, a2) -> {
				if (a1[1] == a2[1]) {
					return Integer.compare(a1[0], a2[0]);
				}
				return Integer.compare(a1[1], a2[1]);
			});
			for (int i = 0; i < mat.length; i++) {
				int[] row = mat[i];
				int sum = 0;
				for (int num : row) {
					if (num == 1) {
						sum++;
					}
				}
				pq.add(new int[]{i, sum});
			}
			int[] ret = new int[k];
			for (int i = 0; i < k; i++) {
				ret[i] = pq.poll()[0];
			}
			return ret;
		}
	}

	/**
	 * 官方题解 使用二分查找
	 */
	static class Solution2 {
		public int[] kWeakestRows(int[][] mat, int k) {
			int m = mat.length, n = mat[0].length;
			List<int[]> power = new ArrayList<int[]>();
			for (int i = 0; i < m; ++i) {
				int l = 0, r = n - 1, pos = -1;
				while (l <= r) {
					int mid = (l + r) / 2;
					if (mat[i][mid] == 0) {
						r = mid - 1;
					}
					else {
						pos = mid;
						l = mid + 1;
					}
				}
				power.add(new int[]{pos + 1, i});
			}

			PriorityQueue<int[]> pq = new PriorityQueue<>((pair1, pair2) -> {
				if (pair1[0] != pair2[0]) {
					return pair1[0] - pair2[0];
				}
				else {
					return pair1[1] - pair2[1];
				}
			});
			for (int[] pair : power) {
				pq.offer(pair);
			}
			int[] ans = new int[k];
			for (int i = 0; i < k; ++i) {
				ans[i] = pq.poll()[1];
			}
			return ans;
		}
	}

	/**
	 * 官方题解，使用快速选择（快排类似），前面的步骤还是使用二分法对队列中军人数量和序号进行统计保存。然后对于统计数组进行随机快速选择。
	 * 「剑指 Offer 40. 最小的k个数」官方题解的方法三或者「215. 数组中的第K个最大元素」的官方题解
	 */
	static class Solution3 {
		public int[] kWeakestRows(int[][] mat, int k) {
			int m = mat.length, n = mat[0].length;
			int[][] power = new int[m][2];
			for (int i = 0; i < m; ++i) {
				int l = 0, r = n - 1, pos = -1;
				while (l <= r) {
					int mid = (l + r) / 2;
					if (mat[i][mid] == 0) {
						r = mid - 1;
					}
					else {
						pos = mid;
						l = mid + 1;
					}
				}
				power[i][0] = pos + 1;
				power[i][1] = i;
			}

			int[][] minimum = LEET1337Helper.getLeastNumbers(power, k);
			Arrays.sort(minimum, (pair1, pair2) -> {
				if (pair1[0] != pair2[0]) {
					return pair1[0] - pair2[0];
				}
				else {
					return pair1[1] - pair2[1];
				}
			});
			int[] ans = new int[k];
			for (int i = 0; i < k; ++i) {
				ans[i] = minimum[i][1];
			}
			return ans;
		}


	}

	static class LEET1337Helper {
		public static int[][] getLeastNumbers(int[][] arr, int k) {
			randomizedSelected(arr, 0, arr.length - 1, k);
			int[][] vec = new int[k][2];
			for (int i = 0; i < k; ++i) {
				vec[i][0] = arr[i][0];
				vec[i][1] = arr[i][1];
			}
			return vec;
		}

		private static void randomizedSelected(int[][] arr, int l, int r, int k) {
			if (l >= r) {
				return;
			}
			int pos = randomizedPartition(arr, l, r);
			int num = pos - l + 1;
			if (k == num) {
				return;
			}
			else if (k < num) {
				randomizedSelected(arr, l, pos - 1, k);
			}
			else {
				randomizedSelected(arr, pos + 1, r, k - num);
			}
		}

		// 基于随机的划分（详见随机快排，避免因原数组为正序或倒序导致的最差情况）
		private static int randomizedPartition(int[][] nums, int l, int r) {
			int i = (int) (Math.random() * (r - l + 1)) + l;
			swap(nums, r, i);
			return partition(nums, l, r);
		}

		private static int partition(int[][] nums, int l, int r) {
			int[] pivot = nums[r];
			// i标识小于pivot值的值下标，每找到一个进行+1
			int i = l - 1;
			for (int j = l; j <= r - 1; ++j) {
				if (compare(nums[j], pivot) <= 0) {
					i = i + 1;
					swap(nums, i, j);
				}
			}
			swap(nums, i + 1, r);
			return i + 1;
		}

		private static void swap(int[][] nums, int i, int j) {
			int[] temp = new int[nums[i].length];
			System.arraycopy(nums[i], 0, temp, 0, nums[i].length);
			System.arraycopy(nums[j], 0, nums[i], 0, nums[i].length);
			System.arraycopy(temp, 0, nums[j], 0, nums[i].length);
		}

		private static int compare(int[] pair, int[] pivot) {
			if (pair[0] != pivot[0]) {
				return pair[0] - pivot[0];
			}
			else {
				return pair[1] - pivot[1];
			}
		}
	}
}


