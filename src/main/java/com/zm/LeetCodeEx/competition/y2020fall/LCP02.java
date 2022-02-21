package com.zm.LeetCodeEx.competition.y2020fall;

import java.util.Arrays;

public class LCP02 {

    public static void main(String[] args) {
        LCP02 LCP06 = new LCP02();
        System.out.println(LCP06.new Solution().breakfastNumber(
                new int[]{2, 1, 1}, new int[]{8, 9, 5, 1}, 9));
        System.out.println(LCP06.new Solution().breakfastNumber(
                new int[]{10, 20, 5}, new int[]{5, 5, 2}, 15));
    }

    /**
     * m = staple.length
     * n = drinks.length
     * <p>
     * O(m log2n)
     */
    class Solution {
        public int breakfastNumber(int[] staple, int[] drinks, int x) {
            int ret = 0;
            Arrays.sort(drinks);
            for (int i = 0; i < staple.length; i++) {
                int leftCoins = x - staple[i];
                if (drinks[0] > leftCoins) {
                    continue;
                }
                int l = 0;
                int r = drinks.length - 1;
                while (r > l) {
                    int mid = (l + r) >>> 1;
                    if (drinks[mid] > leftCoins) {
                        r = mid - 1;
                    } else {
                        l = mid + 1;
                    }
                }
                if (l == r && drinks[l] > leftCoins) {
                    r--;
                }
                ret += r + 1;
                ret %= 1000000007;
            }
            return ret;
        }
    }
}
