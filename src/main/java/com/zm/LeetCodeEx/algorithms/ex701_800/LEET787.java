package com.zm.LeetCodeEx.algorithms.ex701_800;

import java.util.*;

/**
 * 787. K 站中转内最便宜的航班
 * 有 n 个城市通过一些航班连接。给你一个数组 flights ，其中 flights[i] = [fromi, toi, pricei] ，表示该航班都从城市 fromi 开始，以价格 pricei 抵达 toi。
 * <p>
 * 现在给定所有的城市和航班，以及出发城市 src 和目的地 dst，你的任务是找到出一条最多经过 k 站中转的路线，使得从 src 到 dst 的 价格最便宜 ，并返回该价格。 如果不存在这样的路线，则输出 -1。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入:
 * n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
 * src = 0, dst = 2, k = 1
 * 输出: 200
 * 解释:
 * 城市航班图如下
 * <p>
 * <p>
 * 从城市 0 到城市 2 在 1 站中转以内的最便宜价格是 200，如图中红色所示。
 * 示例 2：
 * <p>
 * 输入:
 * n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
 * src = 0, dst = 2, k = 0
 * 输出: 500
 * 解释:
 * 城市航班图如下
 * <p>
 * <p>
 * 从城市 0 到城市 2 在 0 站中转以内的最便宜价格是 500，如图中蓝色所示。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 100
 * 0 <= flights.length <= (n * (n - 1) / 2)
 * flights[i].length == 3
 * 0 <= fromi, toi < n
 * fromi != toi
 * 1 <= pricei <= 104
 * 航班没有重复，且不存在自环
 * 0 <= src, dst, k < n
 * src != dst
 *
 * @author zm
 */
public class LEET787 {
	public static void main(String[] args) {
		System.out.println(new Solution().findCheapestPrice(
				3, new int[][]{{0, 1, 100}, {1, 2, 100}, {0, 2, 500}}, 0, 2, 1));
		System.out.println(new Solution().findCheapestPrice(
				3, new int[][]{{0, 1, 100}, {1, 2, 100}, {0, 2, 500}}, 0, 2, 0));
	}

	static class Solution {
		public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
			if (src == dst) {
				return 0;
			}
			// 记录到达每个点可能的最小值
			int[] minCost = new int[n];
			Arrays.fill(minCost, Integer.MAX_VALUE);

			// 记录起点所在的航线
			HashMap<Integer, List<int[]>> startAndEdgeMap = new HashMap<>();
			for (int[] flight : flights) {
				startAndEdgeMap.compute(flight[0], (key, value) -> value == null ? new ArrayList<>() : value).add(flight);
			}

			// BFS
			int flightCount = 0;
			Deque<int[]> queue = new ArrayDeque<>();
			queue.add(new int[]{src, 0});
			while (flightCount <= k && !queue.isEmpty()) {
				int len = queue.size();
				for (int i = 0; i < len; i++) {
					int[] curPlace = queue.removeFirst();
					List<int[]> list = startAndEdgeMap.get(curPlace[0]);
					if (list != null) {
						for (int[] flight : list) {
							int des = flight[1];
							int cost = curPlace[1] + flight[2];
							// 只有当前消耗的钱比原有少的时候才值得把当前结果加入队列
							if (minCost[des] > cost) {
								minCost[des] = cost;
								queue.addLast(new int[]{des, cost});
							}
						}
					}
				}
				flightCount++;
			}
			if (minCost[dst] == Integer.MAX_VALUE) {
				return -1;
			}
			return minCost[dst];
		}
	}

	/**
	 * dp, f[t][i] = Math.min(f[t][i], f[t - 1][j] + cost);
     * t代表飞行几次，i表示当前到达的点，j表示出发的点
	 */
	static class Sulotion2 {
		public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
			int[][] f = new int[k + 2][n];
			for (int i = 0; i < k + 2; ++i) {
				Arrays.fill(f[i], Integer.MAX_VALUE);
			}
			f[0][src] = 0;
			for (int t = 1; t <= k + 1; ++t) {
				for (int[] flight : flights) {
					int j = flight[0], i = flight[1], cost = flight[2];
					f[t][i] = Math.min(f[t][i], f[t - 1][j] + cost);
				}
			}
			int ans = Integer.MAX_VALUE;
			for (int t = 1; t <= k + 1; ++t) {
				ans = Math.min(ans, f[t][dst]);
			}
			return ans == Integer.MAX_VALUE ? -1 : ans;
		}
	}
}
