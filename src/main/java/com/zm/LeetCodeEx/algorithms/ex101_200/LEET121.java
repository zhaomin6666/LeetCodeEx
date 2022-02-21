package com.zm.LeetCodeEx.algorithms.ex101_200;

/**
 * 121. 买卖股票的最佳时机
 * <p>
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * <p>
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。
 * <p>
 * 注意你不能在买入股票前卖出股票。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [7,1,5,3,6,4] <br>
 * 输出: 5 <br>
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。<br>
 * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
 * <p>
 * 示例 2:
 * <p>
 * 输入: [7,6,4,3,1] <br>
 * 输出: 0 <br>
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。<br>
 * 
 * 
 * 
 * @author zm
 */
public class LEET121 {
	public static void main(String[] args) {
		LEET121 l074 = new LEET121();
		int[] prices1 = { 7, 1, 5, 3, 6, 4 };
		int[] prices2 = { 7, 6, 4, 3, 1 };
		int[] prices3 = { 1, 6, 4, 5, 7 };
		System.out.println(l074.new Solution().maxProfit(prices1));
		System.out.println(l074.new Solution().maxProfit(prices2));
		System.out.println(l074.new Solution().maxProfit(prices3));
	}

	class Solution {
		public int maxProfit(int[] prices) {
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
}
