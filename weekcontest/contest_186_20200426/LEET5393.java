package com.zm.LeetCodeEx.weekcontest.contest_186_20200426;

/**
 * 周赛 2020年4月26日
 * <p>
 * 5393. 可获得的最大点数
 * <p>
 * 几张卡牌 排成一行，每张卡牌都有一个对应的点数。点数由整数数组 cardPoints 给出。
 * <p>
 * 每次行动，你可以从行的开头或者末尾拿一张卡牌，最终你必须正好拿 k 张卡牌。
 * <p>
 * 你的点数就是你拿到手中的所有卡牌的点数之和。
 * <p>
 * 给你一个整数数组 cardPoints 和整数 k，请你返回可以获得的最大点数。
 * 
 * <p>
 * 示例 1：
 * <p>
 * 输入：cardPoints = [1,2,3,4,5,6,1], k = 3 输出：12 解释：第一次行动，不管拿哪张牌，你的点数总是 1
 * 。但是，先拿最右边的卡牌将会最大化你的可获得点数。最优策略是拿右边的三张牌，最终点数为 1 + 6 + 5 = 12 。
 * <p>
 * 示例 2：
 * <p>
 * 输入：cardPoints = [2,2,2], k = 2 输出：4 解释：无论你拿起哪两张卡牌，可获得的点数总是 4 。
 * <p>
 * 示例 3：
 * <p>
 * 输入：cardPoints = [9,7,7,9,7,7,9], k = 7 输出：55 解释：你必须拿起所有卡牌，可以获得的点数为所有卡牌的点数之和。
 * <p>
 * 示例 4：
 * <p>
 * 输入：cardPoints = [1,1000,1], k = 1 输出：1 解释：你无法拿到中间那张卡牌，所以可以获得的最大点数为 1 。
 * <p>
 * 示例 5：
 * <p>
 * 输入：cardPoints = [1,79,80,1,1,1,200,1], k = 3 输出：202
 * <p>
 * 
 * 提示：
 * <p>
 * 1 <= cardPoints.length <= 10^5 <br>
 * 1 <= cardPoints[i] <= 10^4 <br>
 * 1 <= k <= cardPoints.length <br>
 *
 * @author zm
 */
public class LEET5393 {
	public static void main(String[] args) {
		LEET5393 l5393 = new LEET5393();
		/*System.out.println(l5393.new Solution().maxScore(new int[] { 1, 2, 3, 4, 5, 6, 1 }, 3));
		System.out.println(l5393.new Solution().maxScore(new int[] { 2, 2, 2 }, 2));
		System.out.println(l5393.new Solution().maxScore(new int[] { 9, 7, 7, 9, 7, 7, 9 }, 7));
		System.out.println(l5393.new Solution().maxScore(new int[] { 1, 1000, 1 }, 1));
		System.out.println(l5393.new Solution().maxScore(new int[] { 1, 79, 80, 1, 1, 1, 200, 1 }, 3));
		*/System.out.println(l5393.new Solution().maxScore(new int[] { 96, 90, 41, 82, 39, 74, 64, 50, 30 }, 8)); // 536
	}

	class Solution {
		public int maxScore(int[] cardPoints, int k) {
			int sum = 0;
			for (int i = 0; i < cardPoints.length; i++) {
				sum += cardPoints[i];
			}
			int minSumNotGet = Integer.MAX_VALUE;
			int l = 0;
			int r = 0;
			int cur = 0;
			int lNotGet = cardPoints.length - k;
			while (r < cardPoints.length) {
				if (r - l < lNotGet) {
					cur += cardPoints[r];
					r++;
				} else {
					minSumNotGet = Math.min(minSumNotGet, cur);
					cur += cardPoints[r];
					cur -= cardPoints[l];
					r++;
					l++;
				}
			}
			minSumNotGet = Math.min(minSumNotGet, cur);
			return sum - minSumNotGet;
		}
	}
}
