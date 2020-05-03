package com.zm.LeetCodeEx.weekcontest.contest_187_20200503;

/**
 * 周赛 2020年5月3日
 * <p>
 * 5401. 是否所有 1 都至少相隔 k 个元素
 * <p>
 * 给你一个由若干 0 和 1 组成的数组 nums 以及整数 k。如果所有 1 都至少相隔 k 个元素，则返回 True ；否则，返回 False 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,0,0,0,1,0,0,1], k = 2 <br>
 * 输出：true <br>
 * 解释：每个 1 都至少相隔 2 个元素。
 * <p>
 * 示例 2：
 * <p>
 * 输入：nums = [1,0,0,1,0,1], k = 2 <br>
 * 输出：false <br>
 * 解释：第二个 1 和第三个 1 之间只隔了 1 个元素。
 * <p>
 * 示例 3：
 * <p>
 * 输入：nums = [1,1,1,1,1], k = 0 <br>
 * 输出：true
 * <p>
 * 示例 4：
 * <p>
 * 输入：nums = [0,1,0,1], k = 1 <br>
 * 输出：true
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 10^5 <br>
 * 0 <= k <= nums.length <br>
 * nums[i] 的值为 0 或 1
 *
 * @author zm
 */
public class LEET5401 {
	public static void main(String[] args) {
		LEET5401 l5401 = new LEET5401();
		System.out.println(l5401.new Solution().kLengthApart(new int[] { 1, 0, 0, 0, 1, 0, 0, 1 }, 2));
		System.out.println(l5401.new Solution().kLengthApart(new int[] { 1, 0, 0, 1, 0, 1 }, 2));
		System.out.println(l5401.new Solution().kLengthApart(new int[] { 1, 1, 1, 1, 1 }, 0));
		System.out.println(l5401.new Solution().kLengthApart(new int[] { 0, 1, 0, 1 }, 1));
	}

	class Solution {
		public boolean kLengthApart(int[] nums, int k) {
			int cnt0 = 0;
			boolean has1 = false;
			for (int i = 0; i < nums.length; i++) {
				if (!has1) {
					if (nums[i] == 1) {
						has1 = true;
					}
				} else {
					if (nums[i] == 1) {
						if (cnt0 < k) {
							return false;
						}
						cnt0 = 0;
					} else {
						++cnt0;
					}
				}
			}
			return true;
		}
	}
}
