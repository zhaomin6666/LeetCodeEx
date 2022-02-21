package com.zm.LeetCodeEx.weekcontest.before20200426;

/**
 * 周赛 2020年1月12日 5309. 连通网络的操作次数
 * <p>
 * 用以太网线缆将 n 台计算机连接成一个网络，计算机的编号从 0 到 n-1。线缆用 connections 表示，其中 connections[i] = [a, b] 连接了计算机 a 和 b。
 * <p>
 * 网络中的任何一台计算机都可以通过网络直接或者间接访问同一个网络中其他任意一台计算机。
 * <p>
 * 给你这个计算机网络的初始布线 connections，你可以拔开任意两台直连计算机之间的线缆，并用它连接一对未直连的计算机。请你计算并返回使所有计算机都连通所需的最少操作次数。如果不可能，则返回 -1 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 4, connections = [[0,1],[0,2],[1,2]]
 * 输出：1
 * 解释：拔下计算机 1 和 2 之间的线缆，并将它插到计算机 1 和 3 上。
 * 示例 2：
 * <p>
 * 输入：n = 6, connections = [[0,1],[0,2],[0,3],[1,2],[1,3]]
 * 输出：2
 * 示例 3：
 * <p>
 * 输入：n = 6, connections = [[0,1],[0,2],[0,3],[1,2]]
 * 输出：-1
 * 解释：线缆数量不足。
 * 示例 4：
 * <p>
 * 输入：n = 5, connections = [[0,1],[0,2],[3,4],[2,3]]
 * 输出：0
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 10^5
 * 1 <= connections.length <= min(n*(n-1)/2, 10^5)
 * connections[i].length == 2
 * 0 <= connections[i][0], connections[i][1] < n
 * connections[i][0] != connections[i][1]
 * 没有重复的连接。
 * 两台计算机不会通过多条线缆连接。
 *
 * @author zm
 */
public class LEET5309 {
    public static void main(String[] args) {
        LEET5309 l5309 = new LEET5309();
        /*System.out.println(l5309.makeConnected(4, new int[][]{{0, 1}, {0, 2}, {1, 2}}));
        System.out.println(l5309.makeConnected(6, new int[][]{{0, 1}, {0, 2}, {0, 3}, {1, 2}, {1, 3}}));
        System.out.println(l5309.makeConnected(6, new int[][]{{0, 1}, {0, 2}, {0, 3}, {1, 2}}));
        System.out.println(l5309.makeConnected(5, new int[][]{{0, 1}, {0, 2}, {3, 4}, {2, 3}}));*/
        System.out.println(l5309.makeConnected(12, new int[][]{{1, 5}, {1, 7}, {1, 2}, {1, 4}, {3, 7}, {4, 7}, {3, 5}, {0, 6}, {0, 1}, {0, 4}, {2, 6}, {0, 3}, {0, 2}}));
        //System.out.println(l5309.makeConnected(11, new int[][]{{1,4},{0,3},{1,3},{3,7},{2,7},{0,1},{2,4},{3,6},{5,6},{6,7},{4,7},{0,7},{5,7}}));
    }

    public int makeConnected(int n, int[][] connections) {
        if (connections.length < n - 1) {
            return -1;
        }
        int[] rootMap = new int[n];
        for (int i = 0; i < n; i++) {
            rootMap[i] = i;
        }
        int[] treeWeightMap = new int[n];
        for (int i = 0; i < n; i++) {
            treeWeightMap[i] = 1;
        }
        int alreadyConnectedCnt = 0;
        for (int i = 0; i < connections.length; i++) {
            if (rootMap[connections[i][0]] == rootMap[connections[i][1]]) {
                alreadyConnectedCnt++;
            } else {
                int totalWeight = treeWeightMap[rootMap[connections[i][0]]] + treeWeightMap[rootMap[connections[i][1]]];
                if (treeWeightMap[rootMap[connections[i][0]]] >= treeWeightMap[rootMap[connections[i][1]]]) {
                    int cur =rootMap[connections[i][1]];
                    for (int j = 0; j < n; j++) {
                        if (rootMap[j] == cur) {
                            rootMap[j] = rootMap[connections[i][0]];
                        }
                    }
                } else {
                    int cur =rootMap[connections[i][0]];
                    for (int j = 0; j < n; j++) {

                        if (rootMap[j] == cur) {
                            rootMap[j] = rootMap[connections[i][1]];
                        }
                    }
                }
                treeWeightMap[rootMap[connections[i][0]]] = totalWeight;
            }
        }
        return n - 1 - (connections.length - alreadyConnectedCnt);
    }
}
