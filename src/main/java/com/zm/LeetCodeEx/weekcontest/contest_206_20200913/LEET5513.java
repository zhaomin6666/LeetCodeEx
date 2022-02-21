package com.zm.LeetCodeEx.weekcontest.contest_206_20200913;

public class LEET5513 {

    public static void main(String[] args) {
        LEET5513 leet5453 = new LEET5513();
        System.out.println(leet5453.new Solution().minCostConnectPoints(new int[][]{{2, -3}, {-17, -8}, {13, 8}, {-17, -15}}));

    }

    class Solution {
        public int minCostConnectPoints(int[][] points) {
            if (points.length == 1) {
                return 0;
            }
            boolean[] linked = new boolean[points.length];
            int dis = 0;
            for (int i = 0; i < points.length; i++) {
                if (!linked[i]) {
                    int toLink = -1;
                    int minDis = Integer.MAX_VALUE;
                    for (int j = 0; j < points.length; j++) {
                        if (j != i) {
                            int curDis = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
                            if (curDis < minDis) {
                                minDis = curDis;
                                toLink = j;
                            }
                        }
                    }
                    dis += minDis;
                    linked[i] = true;
                    linked[toLink] = true;
                }
            }
            return dis;
        }
    }
}
