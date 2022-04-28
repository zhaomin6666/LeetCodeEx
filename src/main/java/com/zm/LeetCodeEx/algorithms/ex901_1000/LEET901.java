package com.zm.LeetCodeEx.algorithms.ex901_1000;

import java.util.Arrays;

/**
 * 905. 按奇偶排序数组
 * 给你一个整数数组 nums，将 nums 中的的所有偶数元素移动到数组的前面，后跟所有奇数元素。
 * <p>
 * 返回满足此条件的 任一数组 作为答案。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [3,1,2,4]
 * 输出：[2,4,3,1]
 * 解释：[4,2,3,1]、[2,4,1,3] 和 [4,2,1,3] 也会被视作正确答案。
 * 示例 2：
 * <p>
 * 输入：nums = [0]
 * 输出：[0]
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 5000
 * 0 <= nums[i] <= 5000
 *
 * @author zm
 */
public class LEET901 {
	public static void main(String[] args) {
		System.out.println(Arrays.toString(new Solution().sortArrayByParity(new int[]{3, 1, 2, 4})));
		System.out.println(Arrays.toString(new Solution().sortArrayByParity(new int[]{0})));
	}

	/**
	 * 原地替换
	 */
	static class Solution {
		public int[] sortArrayByParity(int[] nums) {
			int len = nums.length;
			int l = 0;
			int r = len - 1;
			while (l < r) {
				while (l < r && nums[l] % 2 == 0) {
					l++;
				}
				while (l < r && nums[r] % 2 == 1) {
					r--;
				}
				if (l < r) {
					int temp = nums[l];
					nums[l] = nums[r];
					nums[r] = temp;
					l++;
					r--;
				}
			}
			return nums;
		}
	}

	/**
	 * 双指针
	 */
	static class Solution2 {
		public int[] sortArrayByParity(int[] nums) {
			int len = nums.length;
			int l = 0;
			int r = len - 1;
			int[] result = new int[len];
			for (int i = 0; i < len; i++) {
				if (nums[i] % 2 == 0) {
					result[l++] = nums[i];
				}
				else {
					result[r--] = nums[i];
				}
			}
			return result;
		}
	}
}
