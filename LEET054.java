package com.zm.LeetCodeEx;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 54. 螺旋矩阵
 * <p>
 * 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
 * <p>
 * 示例 1:
 * <p>
 * <p>
 * 输入:
 * <p>
 * [<br>
 * [ 1, 2, 3 ],<br>
 * [ 4, 5, 6 ],<br>
 * [ 7, 8, 9 ]<br>
 * ]<br>
 * 输出: [1,2,3,6,9,8,7,4,5]
 * <p>
 * 示例 2:
 * <p>
 * 输入: [<br>
 * [1, 2, 3, 4],<br>
 * [5, 6, 7, 8],<br>
 * [9,10,11,12]<br>
 * ]<br>
 * 输出: [1,2,3,4,8,12,11,10,9,5,6,7]
 *
 *
 * @author zm
 */
public class LEET054 {
	public static void main(String[] args) {
		LEET054 l054 = new LEET054();
		int[][] matrix1 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		int[][] matrix2 = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };
		int[][] matrix3 = { { 1, 2, 3 } };
		System.out.println(l054.spiralOrder(matrix1));
		System.out.println(l054.spiralOrder(matrix2));
		System.out.println(l054.spiralOrder2(matrix3));
	}

	/**
	 * 模拟螺旋过程，碰壁之后转向
	 * 
	 * @param matrix
	 * @return
	 */
	public List<Integer> spiralOrder(int[][] matrix) {
		if (matrix.length == 0 || matrix[0].length == 0) {
			return Collections.emptyList();
		}
		int rows = matrix.length;
		int cols = matrix[0].length;
		List<Integer> retList = new ArrayList<Integer>();
		int direction = 0; // →:0,↓:1,←:2,↑:3
		boolean[][] flagMatrix = new boolean[matrix.length][matrix[0].length];
		int x = 0;
		int y = 0;

		loop: while (true) {
			retList.add(matrix[x][y]);
			flagMatrix[x][y] = true;
			switch (direction) {
			case 0:
				if (y + 1 == cols || flagMatrix[x][y + 1]) {
					if (x + 1 == rows || flagMatrix[x + 1][y]) {
						break loop;
					}
					x++;
					direction = 1;
				} else {
					y++;
				}
				break;
			case 1:
				if (x + 1 == rows || flagMatrix[x + 1][y]) {
					if (y - 1 == -1 || flagMatrix[x][y - 1]) {
						break loop;
					}
					y--;
					direction = 2;
				} else {
					x++;
				}
				break;
			case 2:
				if (y - 1 == -1 || flagMatrix[x][y - 1]) {
					if (x - 1 == -1 || flagMatrix[x - 1][y]) {
						break loop;
					}
					x--;
					direction = 3;
				} else {
					y--;
				}
				break;
			case 3:
				if (x - 1 == -1 || flagMatrix[x - 1][y]) {
					if (y + 1 == cols || flagMatrix[x][y + 1]) {
						break loop;
					} else {
						y++;
					}
					direction = 0;
				} else {
					x--;
				}
				break;
			default:
				break;
			}
		}
		return retList;
	}

	/**
	 * 官方题解，避免了switch-case，并且使用for循环直接控制次数，不需要上面的方法中需要两次碰壁之后break
	 * 
	 * @param matrix
	 * @return
	 */
	public List<Integer> spiralOrder2(int[][] matrix) {
		if (matrix.length == 0 || matrix[0].length == 0) {
			return Collections.emptyList();
		}
		List<Integer> ans = new ArrayList<Integer>();
		int R = matrix.length, C = matrix[0].length;
		boolean[][] seen = new boolean[R][C];
		int[] dr = { 0, 1, 0, -1 };
		int[] dc = { 1, 0, -1, 0 };
		int r = 0, c = 0, di = 0;
		for (int i = 0; i < R * C; i++) {
			ans.add(matrix[r][c]);
			seen[r][c] = true;
			int cr = r + dr[di];
			int cc = c + dc[di];
			if (0 <= cr && cr < R && 0 <= cc && cc < C && !seen[cr][cc]) {
				r = cr;
				c = cc;
			} else {
				di = (di + 1) % 4;
				r += dr[di];
				c += dc[di];
			}
		}
		return ans;
	}
}
