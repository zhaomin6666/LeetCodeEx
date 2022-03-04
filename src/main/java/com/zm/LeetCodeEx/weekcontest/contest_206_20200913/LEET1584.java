package com.zm.LeetCodeEx.weekcontest.contest_206_20200913;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 1584. 连接所有点的最小费用
 * 给你一个points 数组，表示 2D 平面上的一些点，其中 points[i] = [xi, yi] 。
 * <p>
 * 连接点 [xi, yi] 和点 [xj, yj] 的费用为它们之间的 曼哈顿距离 ：|xi - xj| + |yi - yj| ，其中 |val| 表示 val 的绝对值。
 * <p>
 * 请你返回将所有点连接的最小总费用。只有任意两点之间 有且仅有 一条简单路径时，才认为所有点都已连接。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
 * 输出：20
 * 解释：
 * <p>
 * 我们可以按照上图所示连接所有点得到最小总费用，总费用为 20 。
 * 注意到任意两个点之间只有唯一一条路径互相到达。
 * 示例 2：
 * <p>
 * 输入：points = [[3,12],[-2,5],[-4,1]]
 * 输出：18
 * 示例 3：
 * <p>
 * 输入：points = [[0,0],[1,1],[1,0],[-1,1]]
 * 输出：4
 * 示例 4：
 * <p>
 * 输入：points = [[-1000000,-1000000],[1000000,1000000]]
 * 输出：4000000
 * 示例 5：
 * <p>
 * 输入：points = [[0,0]]
 * 输出：0
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= points.length <= 1000
 * -106 <= xi, yi <= 106
 * 所有点 (xi, yi) 两两不同。
 *
 * @author zm
 * @version 1.0
 * @date 2022-3-4
 * @since 1.8
 */
public class LEET1584 {
	public static void main(String[] args) {
		System.out.println(new Solution().minCostConnectPoints(new int[][]{{2, -3}, {-17, -8}, {13, 8}, {-17, -15}}));
	}

	/**
	 * 使用uf将点一个个连接，从长度最小的边开始连，遇到已经连上的就跳过，直到所有的点都连上
	 */
	static class Solution {
		public int minCostConnectPoints(int[][] points) {
			int n = points.length;
			DisjointSetUnion dsu = new DisjointSetUnion(n);
			List<Edge> edges = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				for (int j = i + 1; j < n; j++) {
					edges.add(new Edge(dist(points, i, j), i, j));
				}
			}
			edges.sort(Comparator.comparingInt(edge -> edge.len));
			int ret = 0, num = 1;
			for (Edge edge : edges) {
				int len = edge.len, x = edge.x, y = edge.y;
				if (dsu.unionSet(x, y)) {
					ret += len;
					num++;
					if (num == n) {
						break;
					}
				}
			}
			return ret;
		}

		public int dist(int[][] points, int x, int y) {
			return Math.abs(points[x][0] - points[y][0]) + Math.abs(points[x][1] - points[y][1]);
		}

		static class DisjointSetUnion {
			int[] f;
			int[] rank;
			int n;

			public DisjointSetUnion(int n) {
				this.n = n;
				this.rank = new int[n];
				Arrays.fill(this.rank, 1);
				this.f = new int[n];
				for (int i = 0; i < n; i++) {
					this.f[i] = i;
				}
			}

			public int find(int x) {
				return f[x] == x ? x : (f[x] = find(f[x]));
			}

			public boolean unionSet(int x, int y) {
				int fx = find(x), fy = find(y);
				if (fx == fy) {
					return false;
				}
				if (rank[fx] < rank[fy]) {
					int temp = fx;
					fx = fy;
					fy = temp;
				}
				rank[fx] += rank[fy];
				f[fy] = fx;
				return true;
			}
		}

		static class Edge {
			int len, x, y;

			public Edge(int len, int x, int y) {
				this.len = len;
				this.x = x;
				this.y = y;
			}
		}
	}

	static class Solution2 {
		public int minCostConnectPoints(int[][] points) {
			int n = points.length;
			int[] dist = new int[n];
			// 初始化距离。dist[i] 代表，i 节点与已处理的节点集合最近的距离
			Arrays.fill(dist, Integer.MAX_VALUE);
			int res = 0;
			// 标记是否访问
			boolean[] visited = new boolean[n];
			for (int i = 0; i < n; i++) {
				// 距离已处理完的节点最短的节点，第一次进来由于dist全是MAX_VALUE所以会直接用0作为初始节点
				int min = 0;
				for (int j = 0; j < n; j++) {
					if (!visited[j] && dist[j] < dist[min]) {
						min = j;
					}
				}
				// 最短的节点标记为已经处理
				visited[min] = true;
				// 除了第一个节点其他都应该有距离
				if (dist[min] != Integer.MAX_VALUE) {
					res += dist[min];
				}
				// 将当前节点取出来
				int[] point = points[min];
				// 计算当前节点到其他没有访问过的节点的距离，如果距离更短则更新处理过的节点到其他没有访问过的节点的距离
				for (int j = 0; j < n; j++) {
					if (!visited[j]) {
						int newDis = Math.abs(points[j][0] - point[0]) + Math.abs(points[j][1] - point[1]);
						if (newDis < dist[j]) {
							dist[j] = newDis;
						}
					}
				}
			}
			return res;
		}
	}
}
