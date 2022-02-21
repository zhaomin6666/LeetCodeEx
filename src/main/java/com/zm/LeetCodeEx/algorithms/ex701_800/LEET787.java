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
        LEET787 l787 = new LEET787();
        System.out.println(l787.new Solution().findCheapestPrice(
                3, new int[][]{{0, 1, 100}, {1, 2, 100}, {0, 2, 500}},
                0, 2, 0));
    }

    class Solution {
        public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
            if (src == dst) {
                return 0;
            }
            k++;
            int[] minCost = new int[n];
            Arrays.fill(minCost, Integer.MAX_VALUE);

            HashMap<Integer, List<int[]>> startAndEdgeMap = new HashMap<>();
            for (int[] flight : flights) {
                if (startAndEdgeMap.containsKey(flight[0])) {
                    startAndEdgeMap.get(flight[0]).add(flight);
                } else {
                    List<int[]> list = new ArrayList<>();
                    list.add(flight);
                    startAndEdgeMap.put(flight[0], list);
                }
            }
            int flightCount = 0;
            LinkedList<int[]> queue = new LinkedList<>();
            queue.add(new int[]{src, 0});
            while (flightCount < k && !queue.isEmpty()) {
                int len = queue.size();
                for (int i = 0; i < len; i++) {
                    int[] curPlace = queue.removeFirst();
                    List<int[]> list = startAndEdgeMap.get(curPlace[0]);
                    if (list != null) {
                        for (int[] flight : list) {
                            int des = flight[1];
                            int cost = curPlace[1] + flight[2];
                            if (minCost[des] > cost) {
                                minCost[des] = cost;
                                queue.add(new int[]{des, cost});
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
}
