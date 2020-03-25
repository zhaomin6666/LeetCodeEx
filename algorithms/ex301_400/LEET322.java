package com.zm.LeetCodeEx.algorithms.ex301_400;

import java.util.Arrays;

/**
 * 322. 零钱兑换
 * <p>
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 * <p>
 * 示例 1:
 * <p>
 * 输入: coins = [1, 2, 5], amount = 11
 * 输出: 3
 * 解释: 11 = 5 + 5 + 1
 * 示例 2:
 * <p>
 * 输入: coins = [2], amount = 3
 * 输出: -1
 * 说明:
 * 你可以认为每种硬币的数量是无限的。
 *
 * @author zm
 */
public class LEET322 {
    public static void main(String[] args) {
        LEET322 l322 = new LEET322();
        //System.out.println(l322.new Solution().coinChange(new int[]{1, 2, 5}, 11));
        //System.out.println(l322.new Solution().coinChange(new int[]{2}, 3));
        //System.out.println(l322.new Solution().coinChange(new int[]{0}, 0));
        //System.out.println(l322.new Solution().coinChange(new int[]{2, 5, 10, 1}, 27));
        System.out.println(l322.new Solution().coinChange(new int[]{186, 419, 83, 408}, 6249));
    }

    /**
     * 动态规划  C为面值数，需要遍历   自上而下
     * F(S)=F(S−C)+1
     */
    class Solution {
        int[] cache;

        public int coinChange(int[] coins, int amount) {
            cache = new int[amount];
            if (amount < 1) return 0;
            return coinChangeHelper(coins, amount);
        }

        private int coinChangeHelper(int[] coins, int rem) {
            if (rem < 0) {
                return -1;
            }
            if (rem == 0) {
                return 0;
            }
            if (cache[rem - 1] != 0) {
                return cache[rem - 1];
            }
            int min = Integer.MAX_VALUE;
            for (int coin : coins) {
                int res = coinChangeHelper(coins, rem - coin);
                if (res >= 0 && res < min) {
                    min = 1 + res;
                }
            }
            cache[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
            return cache[rem - 1];
        }
    }

    /**
     * 自下而上
     */
    class Solution2 {
        public int coinChange(int[] coins, int amount) {
            int max = amount + 1;
            int[] dp = new int[amount + 1];
            Arrays.fill(dp, max);
            dp[0] = 0;
            for (int i = 1; i <= amount; i++) {
                for (int j = 0; j < coins.length; j++) {
                    if (coins[j] <= i) {
                        dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                    }
                }
            }
            return dp[amount] > amount ? -1 : dp[amount];
        }
    }
}
