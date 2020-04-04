package com.zm.LeetCodeEx.weekcontest;

/**
 * 双周赛 2020年4月4日
 * <p>
 * 5360. 统计最大组的数目
 * <p>
 * 给你一个整数 n 。请你先将从 1 到 n 的所有整数按 10 进制对各个数位求和，然后把数位和相等的数字放到同一个组中。
 * <p>
 * 请你统计每个组的数字数目，并返回数字数目并列最多的组有多少个。
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 13<br>
 * 输出：4<br>
 * 解释：总共有 9 个组，将 1 到 13 按数位求和后这些组分别是：<br>
 * [1,10]，[2,11]，[3,12]，[4,13]，[5]，[6]，[7]，[8]，[9]。总共有 4 个组拥有的数字并列最多。
 * <p>
 * 示例 2：
 * <p>
 * 输入：n = 2<br>
 * 输出：2<br>
 * 解释：总共有 2 个大小为 1 的组 [1]，[2]。
 * <p>
 * 示例 3：
 * <p>
 * 输入：n = 15<br>
 * 输出：6
 * <p>
 * 示例 4：
 * <p>
 * 输入：n = 24<br>
 * 输出：5
 *
 * @author zm
 */
public class LEET5360 {
	public static void main(String[] args) {
		LEET5360 l5360 = new LEET5360();
		System.out.println(l5360.new Solution().countLargestGroup(13));
		System.out.println(l5360.new Solution().countLargestGroup(2));
		System.out.println(l5360.new Solution().countLargestGroup(15));
		System.out.println(l5360.new Solution().countLargestGroup(24));
	}

	class Solution {
		public int countLargestGroup(int n) {
			int[] cnt = new int[37];
			for (int i = 1; i <= n; i++) {
				int sum = 0;
				int temp = i;
				while (temp > 0) {
					sum += temp % 10;
					temp /= 10;
				}
				cnt[sum]++;
			}
			int max = 0;
			for (int i = 0; i < cnt.length; i++) {
				max = Math.max(max, cnt[i]);
			}
			int ret = 0;
			for (int i = 0; i < cnt.length; i++) {
				if (cnt[i] == max) {
					ret++;
				}
			}
			return ret;
		}
	}
}
