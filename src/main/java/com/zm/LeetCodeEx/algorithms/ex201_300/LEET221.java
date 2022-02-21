package com.zm.LeetCodeEx.algorithms.ex201_300;

/**
 * 221. 最大正方形
 * <p>
 * 在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * <p>
 * 1 0 1 0 0 <br>
 * 1 0 1 1 1 <br>
 * 1 1 1 1 1 <br>
 * 1 0 0 1 0
 * <p>
 * 输出: 4
 * 
 * 
 * @author zm
 *
 */
public class LEET221 {
	public static void main(String[] args) {
		LEET221 l221 = new LEET221();
		System.out.println(l221.new Solution().maximalSquare(new char[][] { //
				{ '1', '0', '1', '0', '0' }, //
				{ '1', '0', '1', '1', '1' }, //
				{ '1', '1', '1', '1', '1' }, //
				{ '1', '0', '0', '1', '0' } }));
		System.out.println(l221.new Solution().maximalSquare(new char[][] { //
				{ '1', '0', '0', '0', '0' }, //
				{ '0', '1', '1', '0', '1' }, //
				{ '0', '1', '1', '1', '1' }, //
				{ '0', '0', '1', '1', '1' }, //
				{ '0', '0', '1', '1', '1' } }));
	}

	class Solution {
		public int maximalSquare(char[][] matrix) {
			if (matrix.length == 0 || matrix[0].length == 0) {
				return 0;
			}
			int ret = 0;
			int[][][] dp = new int[matrix.length][matrix[0].length][3];
			for (int i = 0; i < dp.length; i++) {
				for (int j = 0; j < dp[i].length; j++) {
					if (matrix[i][j] != '0') {
						int[] cur = dp[i][j];
						if (i == 0) {
							cur[0] = 1;
						} else {
							cur[0] = dp[i - 1][j][0] + 1;
						}
						if (j == 0) {
							cur[1] = 1;
						} else {
							cur[1] = dp[i][j - 1][1] + 1;
						}
						if (i == 0 || j == 0) {
							cur[2] = 1;
						} else {
							cur[2] = Math.min(Math.min(cur[0], cur[1]), dp[i - 1][j - 1][2] + 1);
						}
						ret = Math.max(cur[2], ret);
					}
				}
			}
			// 显示dp
//			for (int i = 0; i < dp.length; i++) {
//				StringBuilder sbBuilder= new StringBuilder();
//				for (int j = 0; j < dp[0].length; j++) {
//					sbBuilder.append(Arrays.toString(dp[i][j])+" ");
//				}
//				System.out.println(sbBuilder.toString());
//			}
			return ret * ret;
		}
	}

	/**
	 * 方法一想多了，直接用dp(i,j)=min(dp(i−1,j),dp(i−1,j−1),dp(i,j−1))+1
	 * 
	 * @author zm
	 *
	 */
	class Solution2 {
		public int maximalSquare(char[][] matrix) {
			if (matrix.length == 0 || matrix[0].length == 0) {
				return 0;
			}
			int ret = 0;
			int[][] dp = new int[matrix.length][matrix[0].length];
			for (int i = 0; i < dp.length; i++) {
				for (int j = 0; j < dp[i].length; j++) {
					if (matrix[i][j] == '1') {
						if (i == 0 || j == 0) {
							dp[i][j] = 1;
						} else {
							dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
						}
						ret = Math.max(ret, dp[i][j]);
					}
				}
			}
			// 显示dp
//			for (int i = 0; i < dp.length; i++) {
//				System.out.println(Arrays.toString(dp[i]));
//			}
			return ret * ret;
		}
	}
}
