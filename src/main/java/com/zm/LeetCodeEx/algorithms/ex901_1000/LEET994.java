package com.zm.LeetCodeEx.algorithms.ex901_1000;

import java.util.ArrayList;
import java.util.List;

/**
 * 994. 腐烂的橘子
 * <p>
 * 在给定的网格中，每个单元格可以有以下三个值之一：
 * <p>
 * 值 0 代表空单元格； 值 1 代表新鲜橘子； 值 2 代表腐烂的橘子。 每分钟，任何与腐烂的橘子（在 4 个正方向上）相邻的新鲜橘子都会腐烂。
 * <p>
 * 返回直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1。
 * <p>
 * 示例 1：
 * <p>
 * 输入：[[2,1,1],[1,1,0],[0,1,1]] 输出：4
 * <p>
 * 示例 2：
 * <p>
 * 输入：[[2,1,1],[0,1,1],[1,0,1]]<br>
 * 输出：-1<br>
 * 解释：左下角的橘子（第 2 行， 第 0 列）永远不会腐烂，因为腐烂只会发生在 4 个正向上。
 * <p>
 * 示例 3：
 * <p>
 * 输入：[[0,2]]<br>
 * 输出：0 <br>
 * 解释：因为 0 分钟时已经没有新鲜橘子了，所以答案就是 0 。  
 * <p>
 * 提示：
 * <p>
 * 1 <= grid.length <= 10, 1 <= grid[0].length <= 10, grid[i][j] 仅为 0、1 或 2
 * 
 * @author zm
 */
public class LEET994 {
	public static void main(String[] args) {
		LEET994 l994 = new LEET994();
		System.out.println(l994.new Solution().orangesRotting(new int[][] { { 2, 1, 1 }, { 1, 1, 0 }, { 0, 1, 1 } }));
		System.out.println(l994.new Solution().orangesRotting(new int[][] { { 2, 1, 1 }, { 0, 1, 1 }, { 1, 0, 1 } }));
		System.out.println(l994.new Solution().orangesRotting(new int[][] { { 0, 2 } }));
		System.out.println(l994.new Solution().orangesRotting(new int[][] { { 0 } }));
	}

	class Solution {
		public int orangesRotting(int[][] grid) {
			List<int[]> freshListFirst = getOrangeByState(1, grid);
			if (freshListFirst.isEmpty()) {
				return 0;
			}
			List<int[]> rotList = getOrangeByState(2, grid);
			List<int[]> newRotList = new ArrayList<>();
			int cnt = -1;
			while (!rotList.isEmpty()) {
				for (int i = 0; i < rotList.size(); i++) {
					int[] rot = rotList.get(i);
					if (rot[0] > 0 && grid[rot[0] - 1][rot[1]] == 1) {
						grid[rot[0] - 1][rot[1]] = 2;
						newRotList.add(new int[] { rot[0] - 1, rot[1] });
					}
					if (rot[1] > 0 && grid[rot[0]][rot[1] - 1] == 1) {
						grid[rot[0]][rot[1] - 1] = 2;
						newRotList.add(new int[] { rot[0], rot[1] - 1 });
					}
					if (rot[0] < grid.length - 1 && grid[rot[0] + 1][rot[1]] == 1) {
						grid[rot[0] + 1][rot[1]] = 2;
						newRotList.add(new int[] { rot[0] + 1, rot[1] });
					}
					if (rot[1] < grid[0].length - 1 && grid[rot[0]][rot[1] + 1] == 1) {
						grid[rot[0]][rot[1] + 1] = 2;
						newRotList.add(new int[] { rot[0], rot[1] + 1 });
					}
				}
				rotList = newRotList;
				newRotList = new ArrayList<>();
				cnt++;
			}
			List<int[]> freshList = getOrangeByState(1, grid);
			if (!freshList.isEmpty()) {
				return -1;
			}
			return cnt;
		}

		private List<int[]> getOrangeByState(int state, int[][] grid) {
			List<int[]> retList = new ArrayList<int[]>();
			for (int i = 0; i < grid.length; i++) {
				for (int j = 0; j < grid[0].length; j++) {
					if (grid[i][j] == state) {
						retList.add(new int[] { i, j });
					}
				}
			}
			return retList;
		}
	}

	class Solution2 {
		public int orangesRotting(int[][] grid) {
			List<int[]> rotList = getOrangeByState(2, grid);
			if (rotList == null)
				return 0;
			List<int[]> newRotList = new ArrayList<>();
			int cnt = -1;
			while (!rotList.isEmpty()) {
				for (int i = 0; i < rotList.size(); i++) {
					int[] rot = rotList.get(i);
					if (rot[0] > 0 && grid[rot[0] - 1][rot[1]] == 1) {
						grid[rot[0] - 1][rot[1]] = 2;
						newRotList.add(new int[] { rot[0] - 1, rot[1] });
					}
					if (rot[1] > 0 && grid[rot[0]][rot[1] - 1] == 1) {
						grid[rot[0]][rot[1] - 1] = 2;
						newRotList.add(new int[] { rot[0], rot[1] - 1 });
					}
					if (rot[0] < grid.length - 1 && grid[rot[0] + 1][rot[1]] == 1) {
						grid[rot[0] + 1][rot[1]] = 2;
						newRotList.add(new int[] { rot[0] + 1, rot[1] });
					}
					if (rot[1] < grid[0].length - 1 && grid[rot[0]][rot[1] + 1] == 1) {
						grid[rot[0]][rot[1] + 1] = 2;
						newRotList.add(new int[] { rot[0], rot[1] + 1 });
					}
				}
				rotList = newRotList;
				newRotList = new ArrayList<>();
				cnt++;
			}
			List<int[]> freshList = getOrangeByState(1, grid);
			if (!freshList.isEmpty()) {
				return -1;
			}
			return cnt;
		}

		private List<int[]> getOrangeByState(int state, int[][] grid) {
			List<int[]> retList = new ArrayList<int[]>();
			int cnt1 = 0;
			for (int i = 0; i < grid.length; i++) {
				for (int j = 0; j < grid[0].length; j++) {
					if (grid[i][j] == state) {
						retList.add(new int[] { i, j });
					}
					if (state == 2 && grid[i][j] == 1) {
						cnt1++;
					}
				}
			}
			if (state == 2 && cnt1 == 0)
				return null;
			return retList;
		}
	}
}
