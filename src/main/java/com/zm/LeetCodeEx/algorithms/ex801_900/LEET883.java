package com.zm.LeetCodeEx.algorithms.ex801_900;

import com.zm.LeetCodeEx.CommonFunctions;
import com.zm.LeetCodeEx.ListNode;

/**
 * 883. 三维形体投影面积
 * 在 n x n 的网格 grid 中，我们放置了一些与 x，y，z 三轴对齐的 1 x 1 x 1 立方体。
 * <p>
 * 每个值 v = grid[i][j] 表示 v 个正方体叠放在单元格 (i, j) 上。
 * <p>
 * 现在，我们查看这些立方体在 xy 、yz 和 zx 平面上的投影。
 * <p>
 * 投影 就像影子，将 三维 形体映射到一个 二维 平面上。从顶部、前面和侧面看立方体时，我们会看到“影子”。
 * <p>
 * 返回 所有三个投影的总面积 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：[[1,2],[3,4]]
 * 输出：17
 * 解释：这里有该形体在三个轴对齐平面上的三个投影(“阴影部分”)。
 * 示例 2:
 * <p>
 * 输入：grid = [[2]]
 * 输出：5
 * 示例 3：
 * <p>
 * 输入：[[1,0],[0,2]]
 * 输出：8
 * <p>
 * 提示：
 * <p>
 * n == grid.length == grid[i].length
 * 1 <= n <= 50
 * 0 <= grid[i][j] <= 50
 *
 * @author zm
 */
public class LEET883 {
	public static void main(String[] args) {
		System.out.println(new Solution().projectionArea(new int[][]{{1, 2}, {3, 4}}));
		System.out.println(new Solution().projectionArea(new int[][]{{2}}));
		System.out.println(new Solution().projectionArea(new int[][]{{1, 0}, {0, 2}}));
	}

	static class Solution {
		public int projectionArea(int[][] grid) {
			int m = grid.length;
			int n = grid[0].length;
			int xy = 0;
			int xz = 0;
			for (int i = 0; i < m; i++) {
				int max = 0;
				for (int j = 0; j < n; j++) {
					if (grid[i][j] > 0) {
						++xy;
						max = Math.max(max, grid[i][j]);
					}
				}
				xz += max;
			}
			int yz = 0;
			for (int i = 0; i < n; i++) {
				int max = 0;
				for (int j = 0; j < m; j++) {
					max = Math.max(max, grid[j][i]);
				}
				yz += max;
			}
			return xy + xz + yz;
		}
	}
}
