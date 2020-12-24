package com.zm.LeetCodeEx.weekcontest.contest_192_20200607;

/**
 * 周赛 2020年6月7日
 * <p>
 * 5431. 给房子涂色 III
 * <p>
 * 在一个小城市里，有 m 个房子排成一排，你需要给每个房子涂上 n 种颜色之一（颜色编号为 1 到 n ）。有的房子去年夏天已经涂过颜色了，所以这些房子不需要被重新涂色。
 * <p>
 * 我们将连续相同颜色尽可能多的房子称为一个街区。（比方说 houses = [1,2,2,3,3,2,1,1] ，它包含 5 个街区  [{1}, {2,2}, {3,3}, {2}, {1,1}] 。）
 * <p>
 * 给你一个数组 houses ，一个 m * n 的矩阵 cost 和一个整数 target ，其中：
 * <p>
 * houses[i]：是第 i 个房子的颜色，0 表示这个房子还没有被涂色。
 * cost[i][j]：是将第 i 个房子涂成颜色 j+1 的花费。
 * 请你返回房子涂色方案的最小总花费，使得每个房子都被涂色后，恰好组成 target 个街区。如果没有可用的涂色方案，请返回 -1 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：houses = [0,0,0,0,0], cost = [[1,10],[10,1],[10,1],[1,10],[5,1]], m = 5, n = 2, target = 3
 * 输出：9
 * 解释：房子涂色方案为 [1,2,2,1,1]
 * 此方案包含 target = 3 个街区，分别是 [{1}, {2,2}, {1,1}]。
 * 涂色的总花费为 (1 + 1 + 1 + 1 + 5) = 9。
 * 示例 2：
 * <p>
 * 输入：houses = [0,2,1,2,0], cost = [[1,10],[10,1],[10,1],[1,10],[5,1]], m = 5, n = 2, target = 3
 * 输出：11
 * 解释：有的房子已经被涂色了，在此基础上涂色方案为 [2,2,1,2,2]
 * 此方案包含 target = 3 个街区，分别是 [{2,2}, {1}, {2,2}]。
 * 给第一个和最后一个房子涂色的花费为 (10 + 1) = 11。
 * 示例 3：
 * <p>
 * 输入：houses = [0,0,0,0,0], cost = [[1,10],[10,1],[1,10],[10,1],[1,10]], m = 5, n = 2, target = 5
 * 输出：5
 * 示例 4：
 * <p>
 * 输入：houses = [3,1,2,3], cost = [[1,1,1],[1,1,1],[1,1,1],[1,1,1]], m = 4, n = 3, target = 3
 * 输出：-1
 * 解释：房子已经被涂色并组成了 4 个街区，分别是 [{3},{1},{2},{3}] ，无法形成 target = 3 个街区。
 * <p>
 * <p>
 * 提示：
 * <p>
 * m == houses.length == cost.length
 * n == cost[i].length
 * 1 <= m <= 100
 * 1 <= n <= 20
 * 1 <= target <= m
 * 0 <= houses[i] <= n
 * 1 <= cost[i][j] <= 10^4
 *
 * @author zm
 */
public class LEET5431 {
    public static void main(String[] args) {
        LEET5431 l5431 = new LEET5431();
        System.out.println(l5431.new Solution2().minCost(new int[]{0, 0, 0, 0, 0},
                new int[][]{{1, 10}, {10, 1}, {10, 1}, {1, 10}, {5, 1}}, 5, 2, 3));

        System.out.println(l5431.new Solution2().minCost(new int[]{0, 2, 1, 2, 0},
                new int[][]{{1, 10}, {10, 1}, {10, 1}, {1, 10}, {5, 1}}, 5, 2, 3));

        System.out.println(l5431.new Solution2().minCost(new int[]{0, 0, 0, 0, 0},
                new int[][]{{1, 10}, {10, 1}, {1, 10}, {10, 1}, {1, 10}}, 5, 2, 5));

        System.out.println(l5431.new Solution2().minCost(new int[]{3, 1, 2, 3},
                new int[][]{{1, 1, 1}, {1, 1, 1}, {1, 1, 1}, {1, 1, 1}, {1, 1, 1}}, 4, 3, 3));


    }

    /**
     * 回溯法超时
     */
    class Solution {
        int m;
        int n;
        int[][] cost;
        int target;
        int[] houses;
        int curMinCost = -1;

        public int minCost(int[] houses, int[][] cost, int m, int n, int target) {
            this.m = m;
            this.n = n;
            this.cost = cost;
            this.target = target;
            this.houses = houses;
            back(0, 0, 0);
            return curMinCost;
        }

        private void back(int i, int curCost, int blocks) {
            if (blocks > target) {
                return;
            }
            if (i == m) {
                if (blocks == target) {
                    if (curMinCost != -1) {
                        curMinCost = Math.min(curMinCost, curCost);
                    } else {
                        curMinCost = curCost;
                    }
                }
                return;
            }
            if (curMinCost != -1 && curMinCost < curCost) {
                return;
            }
            if (houses[i] != 0) {
                if (i == 0) {
                    blocks++;
                } else {
                    if (houses[i] != houses[i - 1]) {
                        blocks++;
                    }
                }
                back(i + 1, curCost, blocks);
            } else {
                for (int j = 0; j < n; j++) {
                    houses[i] = j + 1;
                    int nextBlocks = blocks;
                    if (i == 0) {
                        nextBlocks++;
                    } else {
                        if (houses[i] != houses[i - 1]) {
                            nextBlocks++;
                        }
                    }
                    if (target - nextBlocks <= m - i - 1) {
                        back(i + 1, curCost + cost[i][j], nextBlocks);
                    }
                    houses[i] = 0;
                }
            }
        }
    }

    class Solution2 {
        public int minCost(int[] houses, int[][] cost, int m, int n, int target) {
            int[][][] dp = new int[m][n][target];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    for (int k = 0; k < target; k++) {
                        dp[i][j][k] = Integer.MAX_VALUE;
                    }
                }
            }
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    for (int k = 0; k < target; k++) {
                        if (houses[i] != 0) {
                            if (j == houses[i] - 1) {
                                if (i == 0) {
                                    if (k == 0) {
                                        dp[i][j][k] = 0;
                                    }
                                } else {
                                    int curMin = dp[i - 1][j][k];
                                    if (k != 0) {
                                        for (int l = 0; l < n; l++) {
                                            if (l != j) {
                                                curMin = Math.min(dp[i - 1][l][k - 1], curMin);
                                            }
                                        }
                                    }
                                    dp[i][j][k] = curMin;
                                }
                            }
                        } else {
                            if (i == 0) {
                                if (k == 0) {
                                    dp[i][j][k] = cost[i][j];
                                }
                            } else {
                                int curMin = dp[i - 1][j][k];
                                if (k != 0) {
                                    for (int l = 0; l < n; l++) {
                                        if (l != j) {
                                            curMin = Math.min(dp[i - 1][l][k - 1], curMin);
                                        }
                                    }
                                }
                                if (curMin != Integer.MAX_VALUE) {
                                    dp[i][j][k] = curMin + cost[i][j];
                                }
                            }
                        }
                    }
                }
            }
            int ret = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                ret = Math.min(ret, dp[m - 1][i][target - 1]);
            }
            return ret == Integer.MAX_VALUE ? -1 : ret;
        }
    }
}
