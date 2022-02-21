package com.zm.LeetCodeEx.algorithms.ex1_100;

import java.util.Arrays;

/**
 * 85. 最大矩形
 * <p>
 * 给定一个仅包含 0 和 1 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。<br>
 * 
 * 示例: <br>
 * 输入:<br>
 * [<br>
 * ["1","0","1","0","0"],<br>
 * ["1","0","1","1","1"],<br>
 * ["1","1","1","1","1"],<br>
 * ["1","0","0","1","0"]<br>
 * ]<br>
 * 输出: 6
 *
 * 
 * @author zm
 */
public class LEET085 {
	public static void main(String[] args) {
		LEET085 l085 = new LEET085();
		System.out.println(l085.new Solution2().maximalRectangle(new char[][] { { '1', '0', '1', '0', '0' },
				{ '1', '0', '1', '1', '1' }, { '1', '1', '1', '1', '1' }, { '1', '0', '0', '1', '0' } }));
	}

	/**
	 * 使用{@code LEET085} 暴力优化解法
	 * 
	 * @author zm
	 *
	 */
	class Solution {
		public int maximalRectangle(char[][] matrix) {
			if (matrix.length == 0) {
				return 0;
			}
			int maxarea = 0;
			int[][] dp = new int[matrix.length][matrix[0].length];

			for (int i = 0; i < matrix.length; i++) {
				for (int j = 0; j < matrix[0].length; j++) {
					if (matrix[i][j] == '1') {
						// 每行找出一行中各个连续的宽度 如[1,1,0,1,1,1]就是[1,2,0,1,2,3]
						dp[i][j] = j == 0 ? 1 : dp[i][j - 1] + 1;
						int width = dp[i][j];
						// 从该行向上寻找最大矩形
						for (int k = i; k >= 0; k--) {
							width = Math.min(width, dp[k][j]);
							maxarea = Math.max(maxarea, width * (i - k + 1));
						}
					}
				}
			}
			return maxarea;
		}
	}

	/**
	 * 使用{@code LEET085} 栈解决方案，将dp每一列输出到84题中获取最大值
	 * 
	 * @author zm
	 *
	 */
	class Solution2 {
		public int maximalRectangle(char[][] matrix) {
			if (matrix.length == 0) {
				return 0;
			}
			int maxarea = 0;
			int[] dp = new int[matrix[0].length];

			for (int i = 0; i < matrix.length; i++) {
				for (int j = 0; j < matrix[0].length; j++) {
					// 每行找出一行中各个连续的宽度 如[1,1,0,1,1,1]就是[1,2,0,1,2,3]
					// 这样每一列就是一个以该列为底，向左延伸的一个矩形图
					dp[j] = matrix[i][j] == '1' ? dp[j] + 1 : 0;
				}
				maxarea = Math.max(maxarea, new LEET084().new Solution4().largestRectangleArea(dp));
			}
			return maxarea;
		}
	}

	/**
	 * 官方题解，记录一个点最能达到的最高值，及最大高度之间能达到的最大左右值
	 * 
	 * @author zm
	 *
	 */
	class Solution3 {
		public int maximalRectangle(char[][] matrix) {
			if (matrix.length == 0) {
				return 0;
			}
			int m = matrix.length;
			int n = matrix[0].length;

			int[] left = new int[n]; // initialize left as the leftmost boundary possible
			int[] right = new int[n];
			int[] height = new int[n];

			Arrays.fill(right, n); // initialize right as the rightmost boundary possible

			int maxarea = 0;
			for (int i = 0; i < m; i++) {
				int cur_left = 0, cur_right = n;
				// update height
				for (int j = 0; j < n; j++) {
					if (matrix[i][j] == '1') {
						// 如果是1的话直接+1即可
						height[j]++;
					} else {
						height[j] = 0;
					}
				}
				// update left
				for (int j = 0; j < n; j++) {
					if (matrix[i][j] == '1') {
						// 如果是1的话需要判断当前left[j]（也就是上一行）的最左边界，和当前点的最左边界，
						// 这样才能计算出整个高度内的最左边界
						left[j] = Math.max(left[j], cur_left);
					} else {
						// 如果这个点为0，那么下一个点就是下一个点的最左边界
						left[j] = 0;
						cur_left = j + 1;
					}
				}
				// update right
				// 逻辑和左侧一样 从右侧开始向左遍历
				for (int j = n - 1; j >= 0; j--) {
					if (matrix[i][j] == '1') {
						right[j] = Math.min(right[j], cur_right);
					} else {
						right[j] = n;
						cur_right = j;
					}
				}
				// update area
				for (int j = 0; j < n; j++) {
					maxarea = Math.max(maxarea, (right[j] - left[j]) * height[j]);
				}
			}
			return maxarea;
		}
	}
}
