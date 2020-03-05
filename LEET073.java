package com.zm.LeetCodeEx;

import java.util.HashSet;
import java.util.Set;

import com.alibaba.fastjson.JSON;

/**
 * 73. 矩阵置零
 * <p>
 * 给定一个 m x n 的矩阵，如果一个元素为 0，则将其所在行和列的所有元素都设为 0。请使用原地算法。
 * <p>
 * 示例 1:
 * <p>
 * 输入: <br>
 * [<br>
 *   [1,1,1],<br>
 *   [1,0,1],<br>
 *   [1,1,1]<br>
 * ]<br>
 * 输出: <br>
 * [<br>
 *   [1,0,1],<br>
 *   [0,0,0],<br>
 *   [1,0,1]<br>
 * ]<br>
 * 示例 2:
 * <p>
 * 输入: <br>
 * [<br>
 *   [0,1,2,0],<br>
 *   [3,4,5,2],<br>
 *   [1,3,1,5]<br>
 * ]<br>
 * 输出: <br>
 * [<br>
 *   [0,0,0,0],<br>
 *   [0,4,5,0],<br>
 *   [0,3,1,0]<br>
 * ]<br>
 * 进阶:
 * <p>
 * 一个直接的解决方案是使用  O(mn) 的额外空间，但这并不是一个好的解决方案。<br>
 * 一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。<br>
 * 你能想出一个常数空间的解决方案吗？<br>
 *
 * 
 * @author zm
 */
public class LEET073 {
	public static void main(String[] args) {
		LEET073 l073 = new LEET073();
		int[][] matrix1 = { { 1, 1, 1 }, { 1, 0, 1 }, { 1, 1, 1 } };
		int[][] matrix2 = { { 0, 1, 2, 0 }, { 3, 4, 5, 2 }, { 1, 3, 1, 5 } };
		l073.new Solution().setZeroes(matrix1);
		l073.new Solution().setZeroes(matrix2);
		System.out.println(JSON.toJSONString(matrix1));
		System.out.println(JSON.toJSONString(matrix2));
	}

	/**
	 * 空间复杂度O(m+n)的方法就是每行每个看有没有0，用额外空间记录有0的行或列，最后再把含有0的行列设置掉。
	 */
	class Solution {
		public void setZeroes(int[][] matrix) {
			int R = matrix.length;
			int C = matrix[0].length;
			Set<Integer> rows = new HashSet<Integer>();
			Set<Integer> cols = new HashSet<Integer>();

			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if (matrix[i][j] == 0) {
						rows.add(i);
						cols.add(j);
					}
				}
			}

			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if (rows.contains(i) || cols.contains(j)) {
						matrix[i][j] = 0;
					}
				}
			}
		}
	}

	/**
	 * 用每行或每列的第一个数来判断该列和该行是否有0，[0][0]位置的用来表示行，额外用一个来标识列
	 * @author zm
	 *
	 */
	class Sulotion2 {
		public void setZeroes(int[][] matrix) {
			Boolean isCol = false;
			int R = matrix.length;
			int C = matrix[0].length;

			for (int i = 0; i < R; i++) {
				if (matrix[i][0] == 0) {
					isCol = true;
				}

				for (int j = 1; j < C; j++) {
					if (matrix[i][j] == 0) {
						matrix[0][j] = 0;
						matrix[i][0] = 0;
					}
				}
			}

			for (int i = 1; i < R; i++) {
				for (int j = 1; j < C; j++) {
					if (matrix[i][0] == 0 || matrix[0][j] == 0) {
						matrix[i][j] = 0;
					}
				}
			}

			if (matrix[0][0] == 0) {
				for (int j = 0; j < C; j++) {
					matrix[0][j] = 0;
				}
			}

			if (isCol) {
				for (int i = 0; i < R; i++) {
					matrix[i][0] = 0;
				}
			}
		}
	}
}
