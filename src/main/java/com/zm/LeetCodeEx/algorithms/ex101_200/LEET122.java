package com.zm.LeetCodeEx.algorithms.ex101_200;

/**
 * 122. 买卖股票的最佳时机 II
 * <p>
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * <p>
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 * <p>
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 
 * 
 * <p>
 * 示例 1:
 * <p>
 * 输入: [7,1,5,3,6,4] <br>
 * 输出: 7 <br>
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。 随后，在第
 * 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
 * <p>
 * 示例 2:
 * <p>
 * 输入: [1,2,3,4,5] <br>
 * 输出: 4 <br>
 * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 * 注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。 因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 * <p>
 * 示例 3:
 * <p>
 * 输入: [7,6,4,3,1] <br>
 * 输出: 0 <br>
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 * <p>
 * 
 * 提示：
 * <p>
 * 1 <= prices.length <= 3 * 10 ^ 4 0 <= prices[i] <= 10 ^ 4
 * 
 * @author zm
 */
public class LEET122 {
	public static void main(String[] args) {
		LEET122 l0122 = new LEET122();
		int[] prices1 = { 7, 1, 5, 3, 6, 4 };
		int[] prices2 = { 7, 6, 4, 3, 1 };
		int[] prices3 = { 1, 6, 4, 5, 7 };
		int[] prices4 = { 1, 2, 3, 4, 5 };
		System.out.println(l0122.new Solution().maxProfit(prices1));
		System.out.println(l0122.new Solution().maxProfit(prices2));
		System.out.println(l0122.new Solution().maxProfit(prices3));
		System.out.println(l0122.new Solution().maxProfit(prices4));
	}

	class Solution {
		public int maxProfit(int[] prices) {
			int ret = 0;
			int l = 0, r = 1;
			while (r < prices.length) {
				if (prices[r] > prices[l]) {
					ret += prices[r] - prices[l];
				}
				l = r++;
			}
			return ret;
		}
	}
}
