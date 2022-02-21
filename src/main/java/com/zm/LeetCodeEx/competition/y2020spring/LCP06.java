package com.zm.LeetCodeEx.competition.y2020spring;

public class LCP06 {

    public static void main(String[] args) {
        LCP06 LCP06 = new LCP06();
        System.out.println(LCP06.new Solution().minCount(new int[]{4, 2, 1}));
        System.out.println(LCP06.new Solution().minCount(new int[]{2, 3, 10}));
    }

    class Solution {
        public int minCount(int[] coins) {
            int ret = 0;
            for (int i = 0; i < coins.length; i++) {
                ret += (coins[i] + 1) >> 1;
            }
            return ret;
        }
    }
}
