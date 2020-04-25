package com.zm.LeetCodeEx.competition.y2020spring;

public class LCP12 {

    public static void main(String[] args) {
        LCP12 LCP12 = new LCP12();
        System.out.println(LCP12.new Solution().minTime(new int[]{1, 2, 3, 3}, 2)); // 3
        System.out.println(LCP12.new Solution().minTime(new int[]{1, 1, 3, 3}, 2)); // 2
        System.out.println(LCP12.new Solution().minTime(new int[]{1, 2, 3, 3}, 3)); // 1
        System.out.println(LCP12.new Solution().minTime(new int[]{1, 2, 2, 3, 3}, 2)); // 3
        System.out.println(LCP12.new Solution().minTime(new int[]{3, 2, 1, 3, 3}, 2)); // 3
        System.out.println(LCP12.new Solution().minTime(new int[]{999, 999, 999}, 4)); // 0
        System.out.println(LCP12.new Solution().minTime(new int[]{999, 999, 999, 1000}, 2)); // 0
    }

    class Solution {
        public int minTime(int[] time, int m) {
            int s = 0;
            for (int x : time) {
                s += x;
            }
            int l = 0, r = s, mid;
            int n = time.length;
            int ans = 0;
            while (l <= r) {
                mid = (l + r) / 2;
                int total = 0, max = 0, parts = 0;
                for (int i = 0; i < n; ++i) {
                    total += time[i];
                    max = Math.max(max, time[i]);
                    if (total - max > mid) {
                        ++parts;
                        max = time[i];
                        total = time[i];
                    }
                }
                if (total > 0) {
                    ++parts;
                }
                if (parts <= m) {
                    ans = mid;
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
            return ans;
        }
    }
}
