package com.zm.LeetCodeEx.weekcontest.contest_187_20200503;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 周赛 2020年5月3日
 * <p>
 * 5402. 绝对差不超过限制的最长连续子数组
 * <p>
 * 给你一个整数数组 nums ，和一个表示限制的整数 limit，请你返回最长连续子数组的长度，该子数组中的任意两个元素之间的绝对差必须小于或者等于
 * limit 。
 * <p>
 * 如果不存在满足条件的子数组，则返回 0 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [8,2,4,7], limit = 4 <br>
 * 输出：2 <br>
 * 解释：所有子数组如下： <br>
 * [8] 最大绝对差 |8-8| = 0 <= 4. <br>
 * [8,2] 最大绝对差 |8-2| = 6 > 4. <br>
 * [8,2,4] 最大绝对差 |8-2| = 6 > 4. <br>
 * [8,2,4,7] 最大绝对差 |8-2| = 6 > 4. <br>
 * [2] 最大绝对差 |2-2| = 0 <= 4. <br>
 * [2,4] 最大绝对差 |2-4| = 2 <= 4. <br>
 * [2,4,7] 最大绝对差 |2-7| = 5 > 4. <br>
 * [4] 最大绝对差 |4-4| = 0 <= 4. <br>
 * [4,7] 最大绝对差 |4-7| = 3 <= 4. <br>
 * [7] 最大绝对差 |7-7| = 0 <= 4. <br>
 * 因此，满足题意的最长子数组的长度为 2 。
 * <p>
 * 示例 2：
 * <p>
 * 输入：nums = [10,1,2,4,7,2], limit = 5 <br>
 * 输出：4 <br>
 * 解释：满足题意的最长子数组是 [2,4,7,2]，其最大绝对差 |2-7| = 5 <= 5 。
 * <p>
 * 示例 3：
 * <p>
 * 输入：nums = [4,2,2,2,4,4,2,2], limit = 0 输出：3
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 10^5 <br>
 * 1 <= nums[i] <= 10^9 <br>
 * 0 <= limit <= 10^9
 *
 * @author zm
 */
public class LEET5402 {
	public static void main(String[] args) {
		LEET5402 l5402 = new LEET5402();
		System.out.println(l5402.new Solution().longestSubarray(new int[] { 8, 2, 4, 7 }, 4));
		System.out.println(l5402.new Solution().longestSubarray(new int[] { 10, 1, 2, 4, 7, 2 }, 5));
		System.out.println(l5402.new Solution().longestSubarray(new int[] { 4, 2, 2, 2, 4, 4, 2, 2 }, 0));
	}

	class Solution {
		public int longestSubarray(int[] nums, int limit) {
			int ret = 1;
			int l = 0;
			int r = 1;
			Deque<Integer> max = new LinkedList<>();
			Deque<Integer> min = new LinkedList<>();
			max.add(nums[0]);
			min.add(nums[0]);
			while (l <= r && r < nums.length) {
				int newInt = nums[r];
				// 处理最大值
				while (!max.isEmpty() && max.getLast() < newInt) {
					max.removeLast();
				}
				max.add(newInt);
				// 处理最小值
				while (!min.isEmpty() && min.getLast() > newInt) {
					min.removeLast();
				}
				min.add(newInt);
				if (max.getFirst() - min.getFirst() <= limit) {
					ret = Math.max(ret, r - l + 1);
				} else {
					while (max.getFirst() - min.getFirst() > limit) {
						int delInt = nums[l];
						if (min.getFirst() == delInt) {
							min.removeFirst();
						}
						if (max.getFirst() == delInt) {
							max.removeFirst();
						}
						l++;
					}
				}
				r++;
			}
			return ret;
		}
	}
}
