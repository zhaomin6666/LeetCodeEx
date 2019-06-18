package com.zm.LeetCodeEx;

import java.util.Arrays;

/**
 * 给定一个长度为 n 的整数数组和一个目标值 target，寻找能够使条件 nums[i] + nums[j] + nums[k] < target
 * 成立的三元组 i, j, k 个数（0 <= i < j < k。 < n）
 * 
 * 示例：
 * 
 * 输入: nums = [-2,0,1,3], target = 2 </br>
 * 输出: 2 </br>
 * 解释: 因为一共有两个三元组满足累加和小于 2: [-2,0,1] [-2,0,3] </br>
 * 进阶：是否能在 O(n2) 的时间复杂度内解决？
 * 
 * @author zm
 *
 */
public class LEET259 {
	public static void main(String[] args) {
		LEET259 l234 = new LEET259();
		int[] nums = { 1, -2, 2, 1, 0 };
		int target = 1;
		System.out.println(l234.threeSumSmaller(nums, target));
	}

	public int threeSumSmaller(int[] nums, int target) {
		if (nums.length < 3) {
			return 0;
		}
		Arrays.sort(nums);
		int cnt = 0;
		for (int i = 0; i < nums.length - 2; i++) {
			int j = i + 1;
			for (int k = nums.length - 1; j < k;) {
				if (nums[i] + nums[j] + nums[k] < target) {
					cnt += k - j++;
				} else {
					k--;
				}
			}
		}
		return cnt;
	}
}
