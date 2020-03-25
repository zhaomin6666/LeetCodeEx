package com.zm.LeetCodeEx.algorithms.ex801_900;

/**
 * 876. 三维形体的表面积
 * <p>
 * 在 N * N 的网格上，我们放置一些 1 * 1 * 1  的立方体。
 * <p>
 * 每个值 v = grid[i][j] 表示 v 个正方体叠放在对应单元格 (i, j) 上。
 * <p>
 * 请你返回最终形体的表面积。
 * <p>
 * 示例 1：<br>
 * 输入：[[2]]<br>
 * 输出：10
 * <p>
 * 示例 2：<br>
 * 输入：[[1,2],[3,4]]<br>
 * 输出：34
 * <p>
 * 示例 3：<br>
 * 输入：[[1,0],[0,2]]<br>
 * 输出：16
 * <p>
 * 示例 4：<br>
 * 输入：[[1,1,1],[1,0,1],[1,1,1]]<br>
 * 输出：32
 * <p>
 * 示例 5：<br>
 * 输入：[[2,2,2],[2,1,2],[2,2,2]]<br>
 * 输出：46<br>
 * 
 * 提示：<br>
 * 
 * 1 <= N <= 50 <br>
 * 0 <= grid[i][j] <= 50 <br>
 *
 * 
 * @author zm
 */
public class LEET892 {
	public static void main(String[] args) {
		LEET892 l876 = new LEET892();
		System.out.println(l876.new Solution().surfaceArea(new int[][] { { 2 } }));
		System.out.println(l876.new Solution().surfaceArea(new int[][] { { 1, 2 }, { 3, 4 } }));
		System.out.println(l876.new Solution().surfaceArea(new int[][] { { 1, 0 }, { 0, 2 } }));
		System.out.println(l876.new Solution().surfaceArea(new int[][] { { 1, 1, 1 }, { 1, 0, 1 }, { 1, 1, 1 } }));
		System.out.println(l876.new Solution().surfaceArea(new int[][] { { 2, 2, 2 }, { 2, 1, 2 }, { 2, 2, 2 } }));
	}

	/**
	 * 查看每个立柱体贡献多少的表面积，查看前后左右4个位置是否有立柱，有的话减去相邻的高度即可
	 * 
	 * @author zm
	 *
	 */
	class Solution {
		public int surfaceArea(int[][] grid) {
			int[] dr = new int[] { 0, 1, 0, -1 };
			int[] dc = new int[] { 1, 0, -1, 0 };

			int l = grid.length;
			int ans = 0;

			for (int r = 0; r < l; ++r) {
				for (int c = 0; c < l; ++c) {
					if (grid[r][c] > 0) {
						ans += 2;
						for (int k = 0; k < 4; ++k) {
							int nr = r + dr[k];
							int nc = c + dc[k];
							int nv = 0;
							if (0 <= nr && nr < l && 0 <= nc && nc < l) {
								nv = grid[nr][nc];
							}
							ans += Math.max(grid[r][c] - nv, 0);
						}
					}
				}
			}
			return ans;
		}
	}

	/**
	 * 评论中解法
	 * 先把面积加上，然后只用判断两个面是否有重复的减去
	 * 
	 * @author sweetiee
	 *
	 */
	class Solution2 {
		public int surfaceArea(int[][] grid) {
			int n = grid.length, area = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					// 先把grid[i][j]赋值给level，省掉了bound check，可以略微略微略微优化一下耗时。。。
					int level = grid[i][j];
					if (level > 0) {
						// 一个柱体中：2个底面 + 所有的正方体都贡献了4个侧表面积
						area += (level << 2) + 2;
						// 减掉 i 与 i-1 相贴的两份表面积
						area -= i > 0 ? Math.min(level, grid[i - 1][j]) << 1 : 0;
						// 减掉 j 与 j-1 相贴的两份表面积
						area -= j > 0 ? Math.min(level, grid[i][j - 1]) << 1 : 0;
					}
				}
			}
			return area;
		}
	}
}
