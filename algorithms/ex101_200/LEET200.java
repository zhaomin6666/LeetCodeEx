package com.zm.LeetCodeEx.algorithms.ex101_200;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 200. 岛屿数量
 * <p>
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * <p>
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * <p>
 * 此外，你可以假设该网格的四条边均被水包围。
 * <p>
 * 示例 1: <br>
 * 输入: <br>
 * 11110 <br>
 * 11010 <br>
 * 11000 <br>
 * 00000 <br>
 * 输出: 1
 * <p>
 * 示例 2: 输入: <br>
 * 11000 <br>
 * 11000 <br>
 * 00100 <br>
 * 00011 <br>
 * 输出: 3 <br>
 * 解释: 每座岛屿只能由水平和/或竖直方向上相邻的陆地连接而成。 <br>
 * 
 * 
 * @author zm
 */
public class LEET200 {
	public static void main(String[] args) {
		LEET200 l200 = new LEET200();
		System.out.println(l200.new Solution().numIslands(new char[][] { 
				{ '1', '1', '1', '0', '1' },
				{ '1', '1', '0', '1', '0' }, 
				{ '1', '0', '1', '0', '1' }, 
				{ '0', '1', '1', '0', '1' } }));
	}

	class Solution {
		public int numIslands(char[][] grid) {
			if (grid.length == 0 || grid[0].length == 0) {
				return 0;
			}
			int r = grid.length;
			int c = grid[0].length;
			int[] di = { 0, 0, 1, -1 };
			int[] dj = { 1, -1, 0, 0 };
			boolean[][] flag = new boolean[r][c];
			int ret = 0;
			for (int i = 0; i < r; i++) {
				for (int j = 0; j < c; j++) {
					if (grid[i][j] == '1' && !flag[i][j]) {
						flag[i][j] = true;
						ret++;
						Queue<int[]> queue = new LinkedList<int[]>();
						queue.add(new int[] { i, j });
						while (!queue.isEmpty()) {
							int[] index = queue.poll();
							for (int k = 0; k < 4; k++) {
								int ni = index[0] + di[k];
								int nj = index[1] + dj[k];
								if (ni >= 0 && ni < r && nj >= 0 && nj < c && grid[ni][nj] == '1' && !flag[ni][nj]) {
									flag[ni][nj] = true;
									queue.add(new int[] { ni, nj });
								}
							}
						}
					}
				}
			}
			return ret;
		}
	}
}
