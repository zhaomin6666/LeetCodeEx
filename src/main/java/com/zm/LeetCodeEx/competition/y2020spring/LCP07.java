package com.zm.LeetCodeEx.competition.y2020spring;

import java.util.LinkedList;
import java.util.Queue;

public class LCP07 {

    public static void main(String[] args) {
        LCP07 LCP07 = new LCP07();
        System.out.println(LCP07.new Solution().numWays(5, new int[][]{{0, 2}, {2, 1}, {3, 4}, {2, 3}, {1, 4}, {2, 0}, {0, 4}}, 3));
        System.out.println(LCP07.new Solution().numWays(3, new int[][]{{0, 2}, {2, 1}}, 2));
    }

    class Solution {
        public int numWays(int n, int[][] relation, int k) {
            Queue<Integer> queue = new LinkedList<>();
            queue.add(0);
            int r = 0;
            while (!queue.isEmpty()) {
                int qs = queue.size();
                for (int i = 0; i < qs; i++) {
                    int temp = queue.poll();
                    for (int j = 0; j < relation.length; j++) {
                        if (relation[j][0] == temp) {
                            queue.add(relation[j][1]);
                        }
                    }
                }
                r++;
                if (r == k) {
                    int cnt = 0;
                    for (int i : queue) {
                        if (i == n - 1) {
                            cnt++;
                        }
                    }
                    return cnt;
                }
            }
            return 0;
        }
    }
}
