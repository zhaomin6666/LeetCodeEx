package com.zm.LeetCodeEx.algorithms.ex2101_2200;

import com.alibaba.fastjson.JSON;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 2104. 子数组范围和
 * 给你一个整数数组 nums 。nums 中，子数组的 范围 是子数组中最大元素和最小元素的差值。
 * <p>
 * 返回 nums 中 所有 子数组范围的 和 。
 * <p>
 * 子数组是数组中一个连续 非空 的元素序列。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3]
 * 输出：4
 * 解释：nums 的 6 个子数组如下所示：
 * [1]，范围 = 最大 - 最小 = 1 - 1 = 0
 * [2]，范围 = 2 - 2 = 0
 * [3]，范围 = 3 - 3 = 0
 * [1,2]，范围 = 2 - 1 = 1
 * [2,3]，范围 = 3 - 2 = 1
 * [1,2,3]，范围 = 3 - 1 = 2
 * 所有范围的和是 0 + 0 + 0 + 1 + 1 + 2 = 4
 * 示例 2：
 * <p>
 * 输入：nums = [1,3,3]
 * 输出：4
 * 解释：nums 的 6 个子数组如下所示：
 * [1]，范围 = 最大 - 最小 = 1 - 1 = 0
 * [3]，范围 = 3 - 3 = 0
 * [3]，范围 = 3 - 3 = 0
 * [1,3]，范围 = 3 - 1 = 2
 * [3,3]，范围 = 3 - 3 = 0
 * [1,3,3]，范围 = 3 - 1 = 2
 * 所有范围的和是 0 + 0 + 0 + 2 + 0 + 2 = 4
 * 示例 3：
 * <p>
 * 输入：nums = [4,-2,-3,4,1]
 * 输出：59
 * 解释：nums 中所有子数组范围的和是 59
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 1000
 * -109 <= nums[i] <= 109
 * <p>
 * <p>
 * 进阶：你可以设计一种时间复杂度为 O(n) 的解决方案吗？
 *
 * @author zm
 */
public class LEET2104 {
	public static void main(String[] args) {
		System.out.println(JSON.toJSONString(new Solution().subArrayRanges(new int[]{1, 2, 3})));
		System.out.println(JSON.toJSONString(new Solution().subArrayRanges(new int[]{1, 3, 3})));
		System.out.println(JSON.toJSONString(new Solution().subArrayRanges(new int[]{4, -2, -3, 4, 1})));
	}

	/**
	 * 遍历所有子数组
	 * O(n)
	 */
	static class Solution {
		public long subArrayRanges(int[] nums) {
			long result = 0;
			int n = nums.length;
			for (int i = 0; i < n; i++) {
				int min = Integer.MAX_VALUE;
				int max = Integer.MIN_VALUE;
				for (int j = i; j < n; j++) {
					min = Math.min(min, nums[j]);
					max = Math.max(max, nums[j]);
					result += max - min;
				}
			}
			return result;
		}
	}

	/**
	 * 官方题解
	 * 单调栈计算出每个元素左右两边第一个比它小（大）的元素
	 * [4, -2, -3, 4, 1] 如nums[1]=-2左右两边第一个比它大元素为nums[0]=4和nums[3]=4。所以以-2为最大值的子数组有(1-0)*(3-1)个。
	 * result是所有子数组最大值的和-所有子数组最小值的和，使用上面的方法计算出所有最大值和最小值即可
	 * <p>
	 * 备注：由于可能有相同的元素，所欲需要定义一个逻辑大小如下
	 * 为了使子数组的最小值或最大值唯一，我们定义如果nums[i]=nums[j]，那么nums[i]与nums[j]的逻辑大小由下标 i 与下标 j 的逻辑大小决定，
	 * 即如果 i<j，那么 nums[i] 逻辑上小于 nums[j]
	 */
	static class Solution2 {
		public long subArrayRanges(int[] nums) {
			int n = nums.length;
			int[] minLeft = new int[n];
			int[] minRight = new int[n];
			int[] maxLeft = new int[n];
			int[] maxRight = new int[n];
			Deque<Integer> minStack = new ArrayDeque<>();
			Deque<Integer> maxStack = new ArrayDeque<>();
			// 从左往右，计算一个元素左边的最大（小）值
			for (int i = 0; i < n; i++) {
				// 最小值
				while (!minStack.isEmpty() && nums[minStack.peek()] > nums[i]) {
					minStack.pop();
				}
				minLeft[i] = minStack.peek() == null ? -1 : minStack.peek();
				minStack.push(i);
				// 最大值
				// 从左往右，栈中元素下标j必然小于i，在元素值相等情况下，num[j]逻辑小于num[i]，由于是单调递减栈，所以num[j]出栈
				while (!maxStack.isEmpty() && nums[maxStack.peek()] <= nums[i]) {
					maxStack.pop();
				}
				maxLeft[i] = maxStack.peek() == null ? -1 : maxStack.peek();
				maxStack.push(i);
			}
			minStack.clear();
			maxStack.clear();
			// 从右往左
			for (int i = n - 1; i >= 0; i--) {
				// 如果 nums[minStack.peek()] == nums[i], 那么根据定义，
				// nums[minStack.peek()] 逻辑上大于 nums[i]，因为 minStack.peek() > i
				while (!minStack.isEmpty() && nums[minStack.peek()] >= nums[i]) {
					minStack.pop();
				}
				minRight[i] = minStack.isEmpty() ? n : minStack.peek();
				minStack.push(i);

				while (!maxStack.isEmpty() && nums[maxStack.peek()] < nums[i]) {
					maxStack.pop();
				}
				maxRight[i] = maxStack.isEmpty() ? n : maxStack.peek();
				maxStack.push(i);
			}
			long sumMax = 0, sumMin = 0;
			for (int i = 0; i < n; i++) {
				sumMax += (long) (maxRight[i] - i) * (i - maxLeft[i]) * nums[i];
				sumMin += (long) (minRight[i] - i) * (i - minLeft[i]) * nums[i];
			}
			return sumMax - sumMin;
		}
	}
}
