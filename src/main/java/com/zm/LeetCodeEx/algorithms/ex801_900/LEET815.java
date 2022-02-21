package com.zm.LeetCodeEx.algorithms.ex801_900;

import java.util.*;

/**
 * 815. 公交路线
 * 给你一个数组 routes ，表示一系列公交线路，其中每个 routes[i] 表示一条公交线路，第 i 辆公交车将会在上面循环行驶。
 * <p>
 * 例如，路线 routes[0] = [1, 5, 7] 表示第 0 辆公交车会一直按序列 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 -> ... 这样的车站路线行驶。
 * 现在从 source 车站出发（初始时不在公交车上），要前往 target 车站。 期间仅可乘坐公交车。
 * <p>
 * 求出 最少乘坐的公交车数量 。如果不可能到达终点车站，返回 -1 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：routes = [[1,2,7],[3,6,7]], source = 1, target = 6
 * 输出：2
 * 解释：最优策略是先乘坐第一辆公交车到达车站 7 , 然后换乘第二辆公交车到车站 6 。
 * 示例 2：
 * <p>
 * 输入：routes = [[7,12],[4,5,15],[6],[15,19],[9,12,13]], source = 15, target = 12
 * 输出：-1
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= routes.length <= 500.
 * 1 <= routes[i].length <= 10^5
 * routes[i] 中的所有值 互不相同
 * sum(routes[i].length) <= 10^5
 * 0 <= routes[i][j] < 10^6
 * 0 <= source, target < 10^6
 *
 * @author zm
 */
public class LEET815 {
    public static void main(String[] args) {
        System.out.println(
                new Solution().numBusesToDestination(new int[][]{{1, 2, 7}, {3, 6, 7}}, 1, 6));
        System.out.println(
                new Solution().numBusesToDestination(new int[][]{{1, 7}, {3, 5}}, 5, 5));
    }

    static class Solution {
        public int numBusesToDestination(int[][] routes, int source, int target) {
            if (source == target) {
                return 0;
            }
            int routeNum = routes.length;
            // 保存每个站点所在的路线
            Map<Integer, List<Integer>> siteToRoute = new HashMap<>();
            // 保存每两条线路之间的连接关系
            boolean[][] routeConnections = new boolean[routeNum][routeNum];
            for (int i = 0; i < routeNum; i++) {
                for (int site : routes[i]) {
                    // 获取所有这个站点所在的路线，并把这些路线和当前路线的连接关系设置为true
                    List<Integer> routeListPassThisSite = siteToRoute.computeIfAbsent(site, k -> new ArrayList<>());
                    for (int j : routeListPassThisSite) {
                        routeConnections[i][j] = routeConnections[j][i] = true;
                    }
                    routeListPassThisSite.add(i);
                }
            }

            // BFS获取从开始车站到每一条路线所需班次。
            // 如[2,3],[3,4]，如果source为2，那么到2同路线的其他站点就需要经过一个班次，到4所在路线就需要经过两个班次。
            // 由于是要最少班次，所以用BFS，后续再遇到经过的就不再更新dis。
            int[] dis = new int[routeNum];
            Arrays.fill(dis, -1);
            Queue<Integer> que = new LinkedList<>();
            for (int bus : siteToRoute.getOrDefault(source, new ArrayList<>())) {
                dis[bus] = 1;
                que.offer(bus);
            }
            while (!que.isEmpty()) {
                int x = que.poll();
                for (int y = 0; y < routeNum; y++) {
                    if (routeConnections[x][y] && dis[y] == -1) {
                        dis[y] = dis[x] + 1;
                        que.offer(y);
                    }
                }
            }

            // 遍历目的地所在的路线，获取这些路线到起始路线所经过的班次的最小值
            int ret = Integer.MAX_VALUE;
            for (int bus : siteToRoute.getOrDefault(target, new ArrayList<>())) {
                if (dis[bus] != -1) {
                    ret = Math.min(ret, dis[bus]);
                }
            }
            return ret == Integer.MAX_VALUE ? -1 : ret;
        }
    }
}
