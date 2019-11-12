package com.zm.LeetCodeEx;

/**
 * 53. 最大子序和
 * <p>
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * <p>
 * 示例:
 * <p>
 * 输入: [-2,1,-3,4,-1,2,1,-5,4], 输出: 6
 * <p>
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * <p>
 * 进阶:
 * <p>
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 *
 *
 * @author zm
 */
public class LEET053 {
	public static void main(String[] args) {
		LEET053 l053 = new LEET053();
		int[] nums1 = {-2,-3};
		System.out.println(l053.maxSubArray(nums1));
	}

	public int maxSubArray(int[] nums) {
		int maxSum = Integer.MIN_VALUE;
		int sum = 0;
		int right = 0;
		while (right < nums.length) {
			sum += nums[right];
			if (maxSum < sum) {
				maxSum = sum;
			}
			if (sum <= 0) {
				sum = 0;
			}
			right++;
		}
		return maxSum;
	}
}
