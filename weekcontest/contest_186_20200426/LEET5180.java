package com.zm.LeetCodeEx.weekcontest.contest_186_20200426;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.PriorityQueue;

/**
 * 周赛 2020年4月26日
 * <p>
 * 5180. 带限制的子序列和
 * <p>
 * 给你一个整数数组 nums 和一个整数 k ，请你返回 非空 子序列元素和的最大值，子序列需要满足：子序列中每两个 相邻 的整数 nums[i] 和
 * nums[j] ，它们在原数组中的下标 i 和 j 满足 i < j 且 j - i <= k 。
 * <p>
 * 数组的子序列定义为：将数组中的若干个数字删除（可以删除 0 个数字），剩下的数字按照原本的顺序排布。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [10,2,-10,5,20], k = 2 <br>
 * 输出：37 <br>
 * 解释：子序列为 [10, 2, 5, 20] 。
 * <p>
 * 示例 2：
 * <p>
 * 输入：nums = [-1,-2,-3], k = 1 <br>
 * 输出：-1 <br>
 * 解释：子序列必须是非空的，所以我们选择最大的数字。
 * <p>
 * 示例 3：
 * <p>
 * 输入：nums = [10,-2,-10,-5,20], k = 2 <br>
 * 输出：23 <br>
 * 解释：子序列为 [10, -2, -5, 20] 。
 * <p>
 * 提示：
 * <p>
 * 1 <= k <= nums.length <= 10^5 <br>
 * -10^4 <= nums[i] <= 10^4 <br>
 *
 * @author zm
 */
public class LEET5180 {
	public static void main(String[] args) {
		LEET5180 l5180 = new LEET5180();
		System.out.println(l5180.new Solution().constrainedSubsetSum(new int[] { 10, 2, -10, 5, 20 }, 2));
		System.out.println(l5180.new Solution().constrainedSubsetSum(new int[] { 10, 2, -10, -9, -8, 5, 20 }, 2));
		System.out.println(l5180.new Solution().constrainedSubsetSum(new int[] { -3, -2, -1 }, 2));
		System.out.println(l5180.new Solution().constrainedSubsetSum(new int[] { 10, -2, -10, -5, 20 }, 2));
	}

	class Solution {
		public int constrainedSubsetSum(int[] nums, int k) {
			int max = Integer.MIN_VALUE;
			int cur = 0;
			boolean hasNum = false;
			PriorityQueue<int[]> delMap = new PriorityQueue<>(new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					return o2[0] - o1[0];
				}
			});
			for (int i = 0; i < nums.length; i++) {
				if (nums[i] < 0) {
					delMap.add(new int[] { nums[i], i });
					if (!hasNum) {
						max = Math.max(max, nums[i]);
					}
					if (delMap.size() == k) {
						int[] entry = delMap.poll();
						cur += entry[0];
						if (cur < 0) {
							cur = 0;
						} else {
							max = Math.max(max, cur);
						}
						i = entry[1];
						delMap.clear();
					}
				} else {
					cur += nums[i];
					delMap.clear();
					hasNum = true;
					max = Math.max(max, cur);
				}
			}
			return max;
		}
	}

	/**
	 * dp+单调栈
	 * 
	 * dp[i+1]=max(nums[i+1],dp[i+1−j]+nums[i+1]) for 1 <= j <= k
	 * 
	 * 如果k=2, 那么dp[i] = <br>
	 * max( <br>
	 * nums[i], 之前的数为负数，不要之前的数<br>
	 * dp[i-1]+nums[i], 前面的数加上当前数<br>
	 * dp[i-2]+nums[i] 跳过前面一个数加上当前数<br>
	 * ) 要保存前面k个dp[i]的最大值就可以用单调栈实现
	 * 
	 * 这里代码是参考题解的，用的是从后向前遍历，以后做到区间最大值再改吧
	 * @author zm
	 *
	 */
	class Solution2 {
		public int constrainedSubsetSum(int[] nums, int k) {
			Deque<Integer> queue = new ArrayDeque<>();
			int res = Integer.MIN_VALUE, n = nums.length;
			for (int i = n - 1; i >= 0; i--) {
				int max = Math.max(queue.isEmpty() ? 0 : nums[queue.peekFirst()], 0);
				int num = nums[i] + max;
				res = Math.max(res, num);
				nums[i] = num;
				while (!queue.isEmpty() && nums[i] >= nums[queue.peekLast()]) {
					queue.pollLast();
				}
				queue.addLast(i);
				while (queue.peekFirst() - (i - 1) > k) {
					queue.pollFirst();
				}
			}
			return res;
		}
	}
}
