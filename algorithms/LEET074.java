package com.zm.LeetCodeEx.algorithms;

import java.util.Arrays;

/**
 * 74. 搜索二维矩阵
 * <p>
 * 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
 * <p>
 * 每行中的整数从左到右按升序排列。<br>
 * 每行的第一个整数大于前一行的最后一个整数。
 * <p>
 * 示例 1:
 * <p>
 * 输入:<br>
 * matrix = [<br>
 * [1, 3, 5, 7],<br>
 * [10, 11, 16, 20],<br>
 * [23, 30, 34, 50]<br>
 * ]<br>
 * target = 3<br>
 * 输出: true
 * <p>
 * 示例 2:
 * <p>
 * 输入:<br>
 * matrix = [<br>
 * [1, 3, 5, 7],<br>
 * [10, 11, 16, 20],<br>
 * [23, 30, 34, 50]<br>
 * ]<br>
 * target = 13<br>
 * 输出: false
 * 
 * 
 * @author zm
 */
public class LEET074 {
	public static void main(String[] args) {
		LEET074 l074 = new LEET074();
		int[][] matrix1 = { { 1, 3, 5, 7 }, { 10, 11, 16, 20 }, { 23, 30, 34, 50 } };
		int[][] matrix2 = { { 1, 3, 5, 7 }, { 10, 11, 16, 20 }, { 23, 30, 34, 50 } };
		int[][] matrix3 = { { 1 } };
		System.out.println(l074.new Solution().searchMatrix(matrix1, 3));
		System.out.println(l074.new Solution().searchMatrix(matrix2, 13));
		System.out.println(l074.new Solution().searchMatrix(matrix3, 2));
	}

	class Solution {
		public boolean searchMatrix(int[][] matrix, int target) {
			if (matrix.length == 0) {
				return false;
			}
			int C = matrix[0].length;
			if (C == 1) {
				return false;
			}
			int l = 0;
			int r = matrix.length - 1;
			while (l <= r) {
				int mid = (l + r) >>> 1;
				int numR = matrix[mid][C - 1];
				int numL = matrix[mid][0];
				if (numR == target || numL == target) {
					return true;
				} else if (numL > target) {
					r = mid - 1;
				} else if (numR < target) {
					l = mid + 1;
				} else {
					int index = Arrays.binarySearch(matrix[mid], target);
					return index >= 0;
				}
			}
			return false;
		}
	}
}
