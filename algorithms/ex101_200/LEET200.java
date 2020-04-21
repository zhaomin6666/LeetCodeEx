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
		System.out.println(l200.new Solution().numIslands(new char[][] { { '1', '1', '1', '0', '1' },
				{ '1', '1', '0', '1', '0' }, { '1', '0', '1', '0', '1' }, { '0', '1', '1', '0', '1' } }));
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
			int ret = 0;
			for (int i = 0; i < r; i++) {
				for (int j = 0; j < c; j++) {
					if (grid[i][j] == '1') {
						grid[i][j] = '0';
						ret++;
						Queue<int[]> queue = new LinkedList<int[]>();
						queue.add(new int[] { i, j });
						while (!queue.isEmpty()) {
							int[] index = queue.poll();
							for (int k = 0; k < 4; k++) {
								int ni = index[0] + di[k];
								int nj = index[1] + dj[k];
								if (ni >= 0 && ni < r && nj >= 0 && nj < c && grid[ni][nj] == '1') {
									grid[ni][nj] = '0';
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

	/**
	 * 官方，优化上面的解法 <br>
	 * 小技巧：储存进queue的坐标可以用一个数表示
	 * 
	 * @author zm
	 *
	 */
	class Solution2 {
		public int numIslands(char[][] grid) {
			if (grid == null || grid.length == 0) {
				return 0;
			}

			int nr = grid.length;
			int nc = grid[0].length;
			int num_islands = 0;

			for (int r = 0; r < nr; ++r) {
				for (int c = 0; c < nc; ++c) {
					if (grid[r][c] == '1') {
						++num_islands;
						grid[r][c] = '0';
						Queue<Integer> neighbors = new LinkedList<>();
						neighbors.add(r * nc + c);
						while (!neighbors.isEmpty()) {
							int id = neighbors.remove();
							int row = id / nc;
							int col = id % nc;
							if (row - 1 >= 0 && grid[row - 1][col] == '1') {
								neighbors.add((row - 1) * nc + col);
								grid[row - 1][col] = '0';
							}
							if (row + 1 < nr && grid[row + 1][col] == '1') {
								neighbors.add((row + 1) * nc + col);
								grid[row + 1][col] = '0';
							}
							if (col - 1 >= 0 && grid[row][col - 1] == '1') {
								neighbors.add(row * nc + col - 1);
								grid[row][col - 1] = '0';
							}
							if (col + 1 < nc && grid[row][col + 1] == '1') {
								neighbors.add(row * nc + col + 1);
								grid[row][col + 1] = '0';
							}
						}
					}
				}
			}

			return num_islands;
		}
	}

	/**
	 * 使用union-find，可以参考《算法4》WeightedQuickUnionUF 实现的细节可能不太一样
	 * 
	 * @author zm
	 *
	 */
	class Solution3 {
		class UnionFind {
			int count;
			int[] parent;
			int[] rank;

			public UnionFind(char[][] grid) {
				count = 0;
				int m = grid.length;
				int n = grid[0].length;
				parent = new int[m * n];
				rank = new int[m * n];
				// 初始化的时候只把所有为1的初始化
				for (int i = 0; i < m; ++i) {
					for (int j = 0; j < n; ++j) {
						if (grid[i][j] == '1') {
							parent[i * n + j] = i * n + j;
							++count;
						}
						rank[i * n + j] = 0;
					}
				}
			}

			public int find(int i) {
				if (parent[i] != i) {
					parent[i] = find(parent[i]);
				}
				return parent[i];
			}

			public void union(int x, int y) {
				int rootx = find(x);
				int rooty = find(y);
				if (rootx != rooty) {
					if (rank[rootx] > rank[rooty]) {
						parent[rooty] = rootx;
					} else if (rank[rootx] < rank[rooty]) {
						parent[rootx] = rooty;
					} else {
						parent[rooty] = rootx;
						rank[rootx] += 1;
					}
					// 如果两个节点的根节点不同，那么就说明他们是同一个岛屿。count--
					--count;
				}
			}

			public int getCount() {
				return count;
			}
		}

		public int numIslands(char[][] grid) {
			if (grid == null || grid.length == 0) {
				return 0;
			}

			int nr = grid.length;
			int nc = grid[0].length;
			UnionFind uf = new UnionFind(grid);
			for (int r = 0; r < nr; ++r) {
				for (int c = 0; c < nc; ++c) {
					if (grid[r][c] == '1') {
						grid[r][c] = '0';
						if (r - 1 >= 0 && grid[r - 1][c] == '1') {
							uf.union(r * nc + c, (r - 1) * nc + c);
						}
						if (r + 1 < nr && grid[r + 1][c] == '1') {
							uf.union(r * nc + c, (r + 1) * nc + c);
						}
						if (c - 1 >= 0 && grid[r][c - 1] == '1') {
							uf.union(r * nc + c, r * nc + c - 1);
						}
						if (c + 1 < nc && grid[r][c + 1] == '1') {
							uf.union(r * nc + c, r * nc + c + 1);
						}
					}
				}
			}

			return uf.getCount();
		}
	}

	/**
	 * 使用一位数组做uf
	 * 
	 * @author zm
	 *
	 */
	public class Solution4 {
		int[] parent;
		int rows;
		int cols;

		public int numIslands(char[][] grid) {
			if (grid.length == 0) {
				return 0;
			}
			rows = grid[0].length;
			cols = grid.length;
			int ans = 0;
			for (int i = 0; i < cols; i++) {
				for (int j = 0; j < rows; j++) {
					if (grid[i][j] == '1') {
						ans++;
					}
				}
			}
			// System.out.println(ans);
			makeset(rows * cols);
			for (int i = 0; i < cols; i++) {
				for (int j = 0; j < rows; j++) {
					if (grid[i][j] == '1') {
						grid[i][j] = '0';
						if (i + 1 < cols && grid[i + 1][j] == '1') {
							ans -= unionSet(node(i, j), node(i + 1, j));
						}
						if (j + 1 < rows && grid[i][j + 1] == '1') {
							ans -= unionSet(node(i, j), node(i, j + 1));
						}
					}
				}
			}
			return ans;

		}

		public int node(int i, int j) {
			return (i * rows) + j;
		}

		// 初始化数组
		public void makeset(int n) {
			parent = new int[n];
			for (int i = 0; i < n; i++) {
				parent[i] = i;
			}
		}

		// 将点同化
		public int unionSet(int i, int j) {
			// System.out.println(i);
			int p1 = parent(i);
			int p2 = parent(j);
			if (p1 != p2) {
				parent[p1] = p2;
				return 1;
			}
			return 0;
			// return int k = p1!=p2 ? 1:0;
		}

		// 寻找老大
		public int parent(int i) {
			int root = i;
			while (parent[root] != root) {
				parent[root] = parent[parent[root]];
				root = parent[root];
			}
			return root;
		}
	}

	/**
	 * 递归
	 * 
	 * @author zm
	 *
	 */
	class Solution5 {
		public int numIslands(char[][] grid) {
			int result = 0;
			for (int i = 0; i < grid.length; i++) {
				for (int j = 0; j < grid[0].length; j++) {
					if (grid[i][j] == '1') {
						result++;
						islandHelper(grid, i, j);
					}
				}
			}
			return result;
		}

		public void islandHelper(char[][] grid, int i, int j) {
			if (grid[i][j] == '0') {
				return;
			} else if (grid[i][j] == '1') {
				grid[i][j] = '0';
			}

			if (i - 1 >= 0) {
				islandHelper(grid, i - 1, j);
			}
			if (i + 1 < grid.length) {
				islandHelper(grid, i + 1, j);
			}
			if (j - 1 >= 0) {
				islandHelper(grid, i, j - 1);
			}
			if (j + 1 < grid[0].length) {
				islandHelper(grid, i, j + 1);
			}
		}
	}
}
