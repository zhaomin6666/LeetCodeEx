package com.zm.LeetCodeEx.weekcontest.contest_268_20211121;

/**
 *
 */
public class LEET5931 {
    public static void main(String[] args) {
        LEET5931 leet5931 = new LEET5931();
        System.out.println(leet5931.new Solution().wateringPlants(new int[]{3,2,4,2,1}, 6));
    }

    class Solution {
        public int wateringPlants(int[] plants, int capacity) {
            int curleft = capacity;
            int step = 0;
            for (int i = 0; i < plants.length; i++) {
                if (curleft >= plants[i]) {
                    step++;
                    curleft -= plants[i];
                } else {
                    step += i + i + 1;
                    curleft = capacity - plants[i];
                }
            }
            return step;
        }
    }
}
