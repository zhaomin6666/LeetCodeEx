package com.zm.LeetCodeEx.algorithms.ex501_600;

import java.util.LinkedList;
import java.util.Queue;

import com.alibaba.fastjson.JSON;

/**
 * 542. 01 矩阵
 * <p>
 * 给定一个由 0 和 1 组成的矩阵，找出每个元素到最近的 0 的距离。
 * <p>
 * 两个相邻元素间的距离为 1 。
 * <p>
 * 示例 1: <br>
 * 输入: <br>
 * 0 0 0 <br>
 * 0 1 0 <br>
 * 0 0 0 <br>
 * 输出: <br>
 * 0 0 0 <br>
 * 0 1 0 <br>
 * 0 0 0
 * <p>
 * 示例 2: <br>
 * 输入: <br>
 * 0 0 0 <br>
 * 0 1 0 <br>
 * 1 1 1 <br>
 * 输出: <br>
 * 0 0 0 <br>
 * 0 1 0 <br>
 * 1 2 1
 * <p>
 * 注意:
 * <p>
 * 给定矩阵的元素个数不超过 10000。 <br>
 * 给定矩阵中至少有一个元素是 0。 <br>
 * 矩阵中的元素只在四个方向上相邻: 上、下、左、右。 <br>
 * 
 * 
 * @author zm
 */
public class LEET542 {
	public static void main(String[] args) {
		LEET542 l542 = new LEET542();

		System.out.println(JSON
				.toJSONString(l542.new Solution().updateMatrix(new int[][] { { 0, 0, 0 }, { 0, 1, 0 }, { 0, 0, 0 } })));
		System.out.println(JSON
				.toJSONString(l542.new Solution().updateMatrix(new int[][] { { 0, 0, 0 }, { 0, 1, 0 }, { 1, 1, 1 } })));
		System.out.println(JSON
				.toJSONString(l542.new Solution().updateMatrix(new int[][] { { 1, 1, 1 }, { 0, 1, 1 }, { 0, 0, 1 } })));
	}

	/**
	 * BFS
	 * 
	 * @author zm
	 *
	 */
	class Solution {
		public int[][] updateMatrix(int[][] matrix) {
			int r = matrix.length;
			int c = matrix[0].length;
			if (r == 0 || c == 0) {
				return matrix;
			}
			boolean[][] flag = new boolean[r][c];
			Queue<int[]> queue = new LinkedList<int[]>();
			for (int i = 0; i < r; i++) {
				for (int j = 0; j < c; j++) {
					if (matrix[i][j] == 0) {
						queue.add(new int[] { i, j });
						flag[i][j] = true;
					}
				}
			}
			int[] di = { 1, 0, -1, 0 };
			int[] dj = { 0, -1, 0, 1 };
			while (!queue.isEmpty()) {
				int[] temp = queue.poll();
				for (int d = 0; d < 4; d++) {
					int ni = temp[0] + di[d];
					int nj = temp[1] + dj[d];
					if (ni >= 0 && ni < r && nj >= 0 && nj < c && !flag[ni][nj]) {
						matrix[ni][nj] = matrix[temp[0]][temp[1]] + 1;
						queue.add(new int[] { ni, nj });
						flag[ni][nj] = true;
					}
				}
			}
			return matrix;
		}
	}

	/**
	 * 动态规划 
	 * 类似于1162. 地图分析
	 * {@link com.zm.LeetCodeEx.algorithms.ex1101_1201.LEET1162.Solution2}
	 * @author zm
	 *
	 */
	class Solution2 {
		public int[][] updateMatrix(int[][] matrix) {
			int m = matrix.length, n = matrix[0].length;
			int[][] dp = new int[m][n];
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					dp[i][j] = matrix[i][j] == 0 ? 0 : 10000;
				}
			}

			// 从左上角开始
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					if (i - 1 >= 0) {
						dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + 1);
					}
					if (j - 1 >= 0) {
						dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + 1);
					}
				}
			}
			// 从右下角开始
			for (int i = m - 1; i >= 0; i--) {
				for (int j = n - 1; j >= 0; j--) {
					if (i + 1 < m) {
						dp[i][j] = Math.min(dp[i][j], dp[i + 1][j] + 1);
					}
					if (j + 1 < n) {
						dp[i][j] = Math.min(dp[i][j], dp[i][j + 1] + 1);
					}
				}
			}
			return dp;
		}
	}

}
