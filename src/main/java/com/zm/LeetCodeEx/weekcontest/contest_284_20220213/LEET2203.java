package com.zm.LeetCodeEx.weekcontest.contest_284_20220213;

import java.util.*;

/**
 * 6011. 完成比赛的最少时间
 * 给你一个下标从 0 开始的二维整数数组 tires ，其中 tires[i] = [fi, ri] 表示第 i 种轮胎如果连续使用，第 x 圈需要耗时 fi * ri(x-1) 秒。
 * <p>
 * 比方说，如果 fi = 3 且 ri = 2 ，且一直使用这种类型的同一条轮胎，那么该轮胎完成第 1 圈赛道耗时 3 秒，完成第 2 圈耗时 3 * 2 = 6 秒，完成第 3 圈耗时 3 * 22 = 12 秒，依次类推。
 * 同时给你一个整数 changeTime 和一个整数 numLaps 。
 * <p>
 * 比赛总共包含 numLaps 圈，你可以选择 任意 一种轮胎开始比赛。每一种轮胎都有 无数条 。每一圈后，你可以选择耗费 changeTime 秒 换成 任意一种轮胎（也可以换成当前种类的新轮胎）。
 * <p>
 * 请你返回完成比赛需要耗费的 最少 时间。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：tires = [[2,3],[3,4]], changeTime = 5, numLaps = 4
 * 输出：21
 * 解释：
 * 第 1 圈：使用轮胎 0 ，耗时 2 秒。
 * 第 2 圈：继续使用轮胎 0 ，耗时 2 * 3 = 6 秒。
 * 第 3 圈：耗费 5 秒换一条新的轮胎 0 ，然后耗时 2 秒完成这一圈。
 * 第 4 圈：继续使用轮胎 0 ，耗时 2 * 3 = 6 秒。
 * 总耗时 = 2 + 6 + 5 + 2 + 6 = 21 秒。
 * 完成比赛的最少时间为 21 秒。
 * 示例 2：
 * <p>
 * 输入：tires = [[1,10],[2,2],[3,4]], changeTime = 6, numLaps = 5
 * 输出：25
 * 解释：
 * 第 1 圈：使用轮胎 1 ，耗时 2 秒。
 * 第 2 圈：继续使用轮胎 1 ，耗时 2 * 2 = 4 秒。
 * 第 3 圈：耗时 6 秒换一条新的轮胎 1 ，然后耗时 2 秒完成这一圈。
 * 第 4 圈：继续使用轮胎 1 ，耗时 2 * 2 = 4 秒。
 * 第 5 圈：耗时 6 秒换成轮胎 0 ，然后耗时 1 秒完成这一圈。
 * 总耗时 = 2 + 4 + 6 + 2 + 4 + 6 + 1 = 25 秒。
 * 完成比赛的最少时间为 25 秒。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= tires.length <= 10^5
 * tires[i].length == 2
 * 1 <= fi, changeTime <= 10^5
 * 2 <= ri <= 10^5
 * 1 <= numLaps <= 1000
 */
public class LEET2203 {
	public static void main(String[] args) {
		System.out.println(
				new Solution().minimumWeight(6,
						new int[][]{{0, 2, 2}, {0, 5, 6}, {1, 0, 3}, {1, 4, 5}, {2, 1, 1}, {2, 3, 3}, {2, 3, 4}, {3, 4, 2}, {4, 5, 1}},
						0, 1, 5));
		System.out.println(
				new Solution().minimumWeight(5,
						new int[][]{{0, 2, 1}, {0, 3, 1}, {2, 4, 1}, {3, 4, 1}, {1, 2, 1}, {1, 3, 10}},
						0, 1, 4));
		System.out.println(
				new Solution().minimumWeight(3,
						new int[][]{{0, 1, 1}, {2, 1, 1}},
						0, 1, 2));
	}

	/**
	 * 计算src1,src2,dest到各个点的最短距离
	 * 可能的几种方式 src1->dest, src2->dest
	 * src1->src2->dest
	 * src2->src1->dest
	 * src1->nodeA, src2->nodeA, nodeA->dest
	 * <p>
	 * 把ArrayDeque改成PriorityQueue不内存溢出不超时
	 * <p>
	 * dijkstra计算从src1,src2,dest到各点的最短路
	 */
	static class Solution {
		public long minimumWeight(int n, int[][] edges, int src1, int src2, int dest) {
			HashMap<Integer, List<Integer[]>> toMap = new HashMap<>(n);
			HashMap<Integer, List<Integer[]>> fromMap = new HashMap<>(n);
			for (int[] edge : edges) {
				List<Integer[]> toPoint = toMap.compute(edge[0], (k, v) -> v == null ? new ArrayList<>() : v);
				toPoint.add(new Integer[]{edge[1], edge[2]});
				List<Integer[]> fromPoint = fromMap.compute(edge[1], (k, v) -> v == null ? new ArrayList<>() : v);
				fromPoint.add(new Integer[]{edge[0], edge[2]});
			}
			long[] src1Min = dijkstra(n, toMap, src1);
			long[] src2Min = dijkstra(n, toMap, src2);
			long[] destMin = dijkstra(n, fromMap, dest);
			//System.out.println(Arrays.toString(src1Min));
			//System.out.println(Arrays.toString(src2Min));
			//System.out.println(Arrays.toString(destMin));
			long min = Long.MAX_VALUE;
			for (int i = 0; i < n; i++) {
				if (destMin[i] != Long.MAX_VALUE && src1Min[i] != Long.MAX_VALUE && src2Min[i] != Long.MAX_VALUE) {
					min = Math.min(min, destMin[i] + src1Min[i] + src2Min[i]);
				}
			}
			return min == Long.MAX_VALUE ? -1 : min;
		}

		private long[] dijkstra(int n, HashMap<Integer, List<Integer[]>> map, int src) {
			long[] result = new long[n];
			Arrays.fill(result, Long.MAX_VALUE);
			result[src] = 0;
			// 记录节点是否记录过
			boolean[] vis = new boolean[n];
			PriorityQueue<long[]> queue = new PriorityQueue<>(Comparator.comparingLong(a -> a[1]));
			queue.add(new long[]{src, 0});
			while (!queue.isEmpty()) {
				long[] curPoint = queue.poll();
				if (vis[(int) curPoint[0]]) {
					continue;
				}
				vis[(int) curPoint[0]] = true;
				List<Integer[]> toPoints = map.get((int) curPoint[0]);
				if (toPoints != null) {
					for (Integer[] toPointIndex : toPoints) {
						long cost = curPoint[1] + toPointIndex[1];
						if (result[toPointIndex[0]] > cost) {
							result[toPointIndex[0]] = cost;
							queue.add(new long[]{toPointIndex[0], cost});
						}
					}
				}
			}
			return result;
		}
	}
}
