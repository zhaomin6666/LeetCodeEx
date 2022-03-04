package com.zm.LeetCodeEx.weekcontest.contest_201_20200809;

import java.util.HashSet;
import java.util.Set;

/**
 * 1546. 和为目标值且不重叠的非空子数组的最大数目
 * 给你一个数组 nums 和一个整数 target 。
 * <p>
 * 请你返回 非空不重叠 子数组的最大数目，且每个子数组中数字和都为 target 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,1,1,1,1], target = 2
 * 输出：2
 * 解释：总共有 2 个不重叠子数组（加粗数字表示） [1,1,1,1,1] ，它们的和为目标值 2 。
 * 示例 2：
 * <p>
 * 输入：nums = [-1,3,5,1,4,2,-9], target = 6
 * 输出：2
 * 解释：总共有 3 个子数组和为 6 。
 * ([5,1], [4,2], [3,5,1,4,2,-9]) 但只有前 2 个是不重叠的。
 * 示例 3：
 * <p>
 * 输入：nums = [-2,6,6,3,5,4,1,2,8], target = 10
 * 输出：3
 * 示例 4：
 * <p>
 * 输入：nums = [0,0,0], target = 0
 * 输出：3
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * 0 <= target <= 10^6
 *
 * @author zm
 * @version 1.0
 * @date 2022-3-4
 * @since 1.8
 */
public class LEET1546 {

	public static void main(String[] args) {
		System.out.println(new Solution2().maxNonOverlapping(new int[]{5, 5, 5, 20, 10, 5, 29, 1, 30, 5, 5}, 30));
		System.out.println(new Solution2().maxNonOverlapping(new int[]{2, 2, 3, 3, -3, -1, 2, -3}, 4));

	}

	/**
	 * 贪心：记录从left到right所有的和是否有加上right等于target。有的话就更新left，因为从左往右越早找到满足的值，后面就越有机会找到符合的值。
	 * 超时
	 */
	static class Solution {
		public int maxNonOverlapping(int[] nums, int target) {
			int ret = 0;
			int left = 0;
			int right = 0;
			loop:
			while (right < nums.length) {
				if (nums[right] == target) {
					left = ++right;
					ret++;
				}
				else {
					for (int i = left; i < right; i++) {
						nums[i] += nums[right];
						if (nums[i] == target) {
							ret++;
							left = ++right;
							continue loop;
						}
					}
					right++;
				}
			}
			return ret;
		}
	}

	/**
	 * 优化，记录和使用HashSet记录sum值，a1,a2,a3,...a(n)。记录sum[a1],sum[a1,a2],sum[a1,a2,a3],...,sum[a1,a2,a3,...,a(n)]
	 * 当sum[a1,a2,a3,...,a(n),a(n+1)] - target 属于上述set中时，能够得到sum[a1,a2,a3,...,a(n),a(n+1)]-sum[a1,a2,a3,...,a(i)] = target
	 * 即 sum[a(i),a(i+1),...,a(n),a(n+1)] = target
	 */
	static class Solution2 {
		public int maxNonOverlapping(int[] nums, int target) {
			int ret = 0;
			int right = 0;
			// 重复使用set而不是在循环里新增，可以提高执行效率
			Set<Integer> set = new HashSet<>();
			while (right < nums.length) {
				if (nums[right] == target) {
					++right;
					ret++;
					continue;
				}
				int sum = 0;
				// 重复使用set而不是在循环里新增，可以提高执行效率
				set.clear();
				while (right < nums.length) {
					sum += nums[right];
					if (sum == target || set.contains(sum - target)) {
						ret++;
						break;
					}
					else {
						set.add(sum);
						++right;
					}
				}
				++right;
			}
			return ret;
		}

		/**
		 * 提前预处理前缀和
		 */
		static class Solution3 {
			public int maxNonOverlapping(int[] nums, int target) {
				int n = nums.length;
				int ans = 0;
				int[] preSum = new int[nums.length + 1];

				for (int i = 1; i <= n; i++) {
					preSum[i] = nums[i - 1] + preSum[i - 1];
				}
				HashSet<Integer> set = new HashSet<>();
				set.add(0);
				for (int i = 1; i <= n; i++) {
					if (set.contains(preSum[i] - target)) {
						ans++;
						set.clear();
					}
					set.add(preSum[i]);
				}
				return ans;
			}
		}
	}
}
