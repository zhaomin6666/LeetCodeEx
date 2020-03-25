package com.zm.LeetCodeEx.algorithms.ex1_100;

import java.util.Arrays;

import com.alibaba.fastjson.JSONObject;

/**
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target
 * 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 * 
 * @author zm
 *
 */
public class LEET016 {
	public static void main(String[] args) {
		int[] intArray = { -1, 2, 1, -4 };
		// int[] intArray = {-1, 2};
		int ans = new LEET016().threeSumClosest(intArray, 1);
		System.out.println(JSONObject.toJSONString(ans));
	}

	/**
	 * 类似于上一题，加入偏差值而已
	 * 
	 * @param nums
	 * @param target
	 * @return
	 */
	public int threeSumClosest(int[] nums, int target) {
		Arrays.sort(nums);
		int closetsum = nums[0] + nums[1] + nums[2];
		int offset = Math.abs(closetsum - target);
		int len = nums.length;

		for (int i = 0; i < len; i++) {
			int l = i + 1, r = len - 1;
			while (l < r) {
				int sumtemp = nums[i] + nums[l] + nums[r];
				if (Math.abs(sumtemp - target) < offset) {
					offset = Math.abs(sumtemp - target);
					closetsum = sumtemp;
				}
				if (sumtemp > target) {
					r--;
				} else if (sumtemp < target) {
					l++;
				} else {
					return target;
				}

			}
		}
		return closetsum;
	}
}
