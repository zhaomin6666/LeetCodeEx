package com.zm.LeetCodeEx.algorithms.ex101_200;

import java.util.Arrays;

/**
 * 123. 买卖股票的最佳时机 III
 * <p>
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 * <p>
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
 * <p>
 * 注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,3,5,0,0,3,1,4] 输出: 6 解释: 在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 =
 * 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。   随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 =
 * 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
 * <p>
 * 示例 2:
 * <p>
 * 输入: [1,2,3,4,5] 输出: 4 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出,
 * 这笔交易所能获得利润 = 5-1 = 4 。     注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。    
 * 因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 * <p>
 * 示例 3:
 * <p>
 * 输入: [7,6,4,3,1] 输出: 0 解释: 在这个情况下, 没有交易完成, 所以最大利润为 0。
 *
 * @author zm
 */
public class LEET123 {
    public static void main(String[] args) {
        LEET123 l0123 = new LEET123();
        int[] prices1 = {7, 1, 5, 3, 6, 4};
        int[] prices2 = {7, 6, 4, 3, 1};
        int[] prices3 = {1, 6, 4, 5, 7};
        int[] prices4 = {1, 2, 3, 4, 5};
        int[] prices5 = {3, 3, 5, 0, 0, 3, 1, 4};
        int[] prices6 = {1, 2, 4, 2, 5, 7, 2, 4, 9, 0};
        int[] prices7 = {2, 1, 4};
        int[] prices8 = {8, 3, 6, 2, 8, 8, 8, 4, 2, 0, 7, 2, 9, 4, 9};
        /*System.out.println(l0123.new Solution2().maxProfit(prices1));
        System.out.println(l0123.new Solution2().maxProfit(prices2));
        System.out.println(l0123.new Solution2().maxProfit(prices3));
        System.out.println(l0123.new Solution2().maxProfit(prices4));
        System.out.println(l0123.new Solution2().maxProfit(prices5));
        System.out.println(l0123.new Solution2().maxProfit(prices6));
        System.out.println(l0123.new Solution2().maxProfit(prices7));*/
        System.out.println(l0123.new Solution2().maxProfit(prices8));
    }

    /**
     * 利用121题来做，但是时间复杂度为O(n2)
     */
    class Solution {
        public int maxProfit(int[] prices) {
            int ret = 0;
            int l = 0, r = 1;
            while (r < prices.length) {
                if (prices[r] > prices[l]) {
                    if (r + 1 < prices.length && prices[r + 1] > prices[r]) {
                    } else {
                        ret = Math.max(ret,
                                prices[r] - prices[l] + maxProfitForOnce(Arrays.copyOfRange(prices, r, prices.length)));
                    }
                }
                if (r + 1 < prices.length) {
                    r++;
                } else {
                    l++;
                    r = l + 1;
                }

            }
            return ret;
        }

        private int maxProfitForOnce(int[] prices) {
            int max = 0;
            int l = 0, r = 1;
            while (r < prices.length) {
                if (prices[r] <= prices[l]) {
                    l = r++;
                } else {
                    int inteval = prices[r++] - prices[l];
                    max = inteval > max ? inteval : max;
                }
            }
            return max;
        }
    }

    /**
     * 优化方法1
     */
    class Solution2 {
        public int maxProfit(int[] prices) {
            int l = prices.length;
            int[] change = new int[l];
            for (int i = 1; i < l; i++) {
                change[i] = prices[i] - prices[i - 1];
            }
            int cur = 0;
            int max = 0;
            for (int i = 0; i < l; i++) {
                cur += change[i];
                if (change[i] > 0 && (i + 1 >= l || change[i + 1] <= 0)) {
                    max = Math.max(max, cur + maxProfitForOnce(change, i + 1));
                } else {
                    if (cur < 0) {
                        cur = 0;
                    }
                }
            }
            return max;
        }

        private int maxProfitForOnce(int[] change, int start) {
            int max = 0;
            int cur = 0;
            while (start < change.length) {
                cur += change[start];
                if (cur < 0) {
                    cur = 0;
                } else {
                    max = Math.max(max, cur);
                }
                start++;
            }
            return max;
        }
    }
}
