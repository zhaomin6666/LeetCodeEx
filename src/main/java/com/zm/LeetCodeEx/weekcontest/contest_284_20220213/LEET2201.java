package com.zm.LeetCodeEx.weekcontest.contest_284_20220213;

import java.util.HashSet;

/**
 * 2201. 统计可以提取的工件
 * 存在一个 n x n 大小、下标从 0 开始的网格，网格中埋着一些工件。给你一个整数 n 和一个下标从 0 开始的二维整数数组 artifacts ，artifacts 描述了矩形工件的位置，其中 artifacts[i] = [r1i, c1i, r2i, c2i] 表示第 i 个工件在子网格中的填埋情况：
 * <p>
 * (r1i, c1i) 是第 i 个工件 左上 单元格的坐标，且
 * (r2i, c2i) 是第 i 个工件 右下 单元格的坐标。
 * 你将会挖掘网格中的一些单元格，并清除其中的填埋物。如果单元格中埋着工件的一部分，那么该工件这一部分将会裸露出来。如果一个工件的所有部分都都裸露出来，你就可以提取该工件。
 * <p>
 * 给你一个下标从 0 开始的二维整数数组 dig ，其中 dig[i] = [ri, ci] 表示你将会挖掘单元格 (ri, ci) ，返回你可以提取的工件数目。
 * <p>
 * 生成的测试用例满足：
 * <p>
 * 不存在重叠的两个工件。
 * 每个工件最多只覆盖 4 个单元格。
 * dig 中的元素互不相同。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：n = 2, artifacts = [[0,0,0,0],[0,1,1,1]], dig = [[0,0],[0,1]]
 * 输出：1
 * 解释：
 * 不同颜色表示不同的工件。挖掘的单元格用 'D' 在网格中进行标记。
 * 有 1 个工件可以提取，即红色工件。
 * 蓝色工件在单元格 (1,1) 的部分尚未裸露出来，所以无法提取该工件。
 * 因此，返回 1 。
 * 示例 2：
 * <p>
 * <p>
 * 输入：n = 2, artifacts = [[0,0,0,0],[0,1,1,1]], dig = [[0,0],[0,1],[1,1]]
 * 输出：2
 * 解释：红色工件和蓝色工件的所有部分都裸露出来（用 'D' 标记），都可以提取。因此，返回 2 。
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 1000
 * 1 <= artifacts.length, dig.length <= min(n2, 105)
 * artifacts[i].length == 4
 * dig[i].length == 2
 * 0 <= r1i, c1i, r2i, c2i, ri, ci <= n - 1
 * r1i <= r2i
 * c1i <= c2i
 * 不存在重叠的两个工件
 * 每个工件 最多 只覆盖 4 个单元格
 * dig 中的元素互不相同
 * <p>
 */
public class LEET2201 {
	public static void main(String[] args) {
		System.out.println(new Solution2().digArtifacts(2, new int[][]{{0, 0, 0, 0}, {0, 1, 1, 1}}, new int[][]{{0, 0}, {0, 1}}));
		System.out.println(new Solution2().digArtifacts(2, new int[][]{{0, 0, 0, 0}, {0, 1, 1, 1}}, new int[][]{{0, 0}, {0, 1}, {1, 1}}));
		System.out.println(new Solution2().digArtifacts(6,
				new int[][]{{0, 2, 0, 5}, {0, 1, 1, 1}, {3, 0, 3, 3}, {4, 4, 4, 4}, {2, 1, 2, 4}},
				new int[][]{{0, 2}, {0, 3}, {0, 4}, {2, 0}, {2, 1}, {2, 2}, {2, 5}, {3, 0}, {3, 1}, {3, 3}, {3, 4}, {4, 0}, {4, 3}, {4, 5}, {5, 0}, {5, 1}, {5, 2}, {5, 4}, {5, 5}}));
	}

	static class Solution {
		public int digArtifacts(int n, int[][] artifacts, int[][] dig) {
			boolean[][] board = new boolean[n][n];
			for (int[] digDetail : dig) {
				board[digDetail[0]][digDetail[1]] = true;
			}
			int result = 0;
			for (int[] artifact : artifacts) {
				if (artifact[0] != artifact[2] && artifact[1] != artifact[3]) {
					// 由于最多覆盖4个零件，所以在长宽大于1的情况下只有2*2这一种可能
					if (board[artifact[0]][artifact[1]] && board[artifact[2]][artifact[3]]
							&& board[artifact[0]][artifact[3]] && board[artifact[2]][artifact[1]]) {
						result++;
					}
				}
				else {
					boolean digged = true;
					// 判断长条内的格子是否都被挖出来
					if (artifact[0] != artifact[2]) {
						int max = Math.max(artifact[0], artifact[2]);
						int min = Math.min(artifact[0], artifact[2]);
						for (int j = min; j <= max; j++) {
							if (!board[j][artifact[1]]) {
								digged = false;
								break;
							}
						}
					}
					else {
						int max = Math.max(artifact[1], artifact[3]);
						int min = Math.min(artifact[1], artifact[3]);
						for (int j = min; j <= max; j++) {
							if (!board[artifact[0]][j]) {
								digged = false;
								break;
							}
						}
					}
					if (digged) {
						result++;
					}
				}
			}
			return result;
		}
	}

	/**
	 * 储存使用hash表优化（效率降低，空间减少）
	 * 判断的时候使用一套循环代码
	 */
	static class Solution2 {
		public int digArtifacts(int n, int[][] artifacts, int[][] dig) {
			HashSet<Integer> digSet = new HashSet<>(dig.length);
			for (int[] digDetail : dig) {
				digSet.add(digDetail[0] * n + digDetail[1]);
			}
			int result = 0;
			loop:
			for (int[] artifact : artifacts) {
				for (int i = artifact[0]; i <= artifact[2]; i++) {
					for (int j = artifact[1]; j <= artifact[3]; j++) {
						if (!digSet.contains(i * n + j)) {
							continue loop;
						}
					}
				}
				++result;
			}
			return result;
		}
	}

	/**
	 * 储存还是用Array
	 * 判断的时候使用一套循环代码
	 */
	static class Solution3 {
		public int digArtifacts(int n, int[][] artifacts, int[][] dig) {
			boolean[][] board = new boolean[n][n];
			for (int[] digDetail : dig) {
				board[digDetail[0]][digDetail[1]] = true;
			}
			int result = 0;
			loop:
			for (int[] artifact : artifacts) {
				for (int i = artifact[0]; i <= artifact[2]; i++) {
					for (int j = artifact[1]; j <= artifact[3]; j++) {
						if (!board[i][j]) {
							continue loop;
						}
					}
				}
				++result;
			}
			return result;
		}
	}
}
