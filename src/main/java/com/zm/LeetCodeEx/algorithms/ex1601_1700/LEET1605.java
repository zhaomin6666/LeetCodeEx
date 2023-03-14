package com.zm.LeetCodeEx.algorithms.ex1601_1700;

import com.alibaba.fastjson.JSON;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 1605. 给定行和列的和求可行矩阵
 * 给你两个非负整数数组 rowSum 和 colSum ，其中 rowSum[i] 是二维矩阵中第 i 行元素的和， colSum[j] 是第 j 列元素的和。换言之你不知道矩阵里的每个元素，但是你知道每一行和每一列的和。
 * <p>
 * 请找到大小为 rowSum.length x colSum.length 的任意 非负整数 矩阵，且该矩阵满足 rowSum 和 colSum 的要求。
 * <p>
 * 请你返回任意一个满足题目要求的二维矩阵，题目保证存在 至少一个 可行矩阵。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：rowSum = [3,8], colSum = [4,7]
 * 输出：[[3,0],
 * [1,7]]
 * 解释：
 * 第 0 行：3 + 0 = 3 == rowSum[0]
 * 第 1 行：1 + 7 = 8 == rowSum[1]
 * 第 0 列：3 + 1 = 4 == colSum[0]
 * 第 1 列：0 + 7 = 7 == colSum[1]
 * 行和列的和都满足题目要求，且所有矩阵元素都是非负的。
 * 另一个可行的矩阵为：[[1,2],
 * [3,5]]
 * 示例 2：
 * <p>
 * 输入：rowSum = [5,7,10], colSum = [8,6,8]
 * 输出：[[0,5,0],
 * [6,1,0],
 * [2,0,8]]
 * 示例 3：
 * <p>
 * 输入：rowSum = [14,9], colSum = [6,9,8]
 * 输出：[[0,9,5],
 * [6,0,3]]
 * 示例 4：
 * <p>
 * 输入：rowSum = [1,0], colSum = [1]
 * 输出：[[1],
 * [0]]
 * 示例 5：
 * <p>
 * 输入：rowSum = [0], colSum = [0]
 * 输出：[[0]]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= rowSum.length, colSum.length <= 500
 * 0 <= rowSum[i], colSum[i] <= 108
 * sum(rowSum) == sum(colSum)
 *
 * @author zm
 */
public class LEET1605 {
	public static void main(String[] args) {
		System.out.println(JSON.toJSONString(new Solution().restoreMatrix(new int[]{14,9}, new int[]{6,9,8})));
	}

	/**
	 * 寻找行中的最小值和列中的最小值放入矩阵中
	 */
	static class Solution {
		public int[][] restoreMatrix(int[] rowSum, int[] colSum) {
			int m = rowSum.length;
			int n = colSum.length;
			int[][] result = new int[m][n];
			PriorityQueue<int[]> pqRow = putIntoQueue(rowSum);
			PriorityQueue<int[]> pqCol = putIntoQueue(colSum);

			while (!pqRow.isEmpty()) {
				int[] minRow = pqRow.poll();
				int[] minCol = pqCol.poll();
				int min = Math.min(minRow[0], minCol[0]);
				result[minRow[1]][minCol[1]] = min;
				minRow[0] -= min;
				minCol[0] -= min;
				if (minRow[0] > 0) {
					pqRow.offer(minRow);
				}
				if (minCol[0] > 0) {
					pqCol.offer(minCol);
				}
			}
			return result;
		}

		private PriorityQueue<int[]> putIntoQueue(int[] arr) {
			PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(item -> item[0]));
			int n = arr.length;
			for (int i = 0; i < n; i++) {
				if (arr[i] != 0) {
					pq.offer(new int[]{arr[i], i});
				}
			}
			return pq;
		}
	}

	/**
	 * 从左上角的各自开始，判断列和行的和那个小，直接把小的值填入，然后根据剩余的数向下或（且）向右走格子
	 *
	 */
	static class Solution2 {
		public int[][] restoreMatrix(int[] rowSum, int[] colSum) {
			int n = rowSum.length, m = colSum.length;
			int[][] matrix = new int[n][m];
			int i = 0, j = 0;
			while (i < n && j < m) {
				int v = Math.min(rowSum[i], colSum[j]);
				matrix[i][j] = v;
				rowSum[i] -= v;
				colSum[j] -= v;
				if (rowSum[i] == 0) {
					++i;
				}
				if (colSum[j] == 0) {
					++j;
				}
			}
			return matrix;
		}
	}
}
