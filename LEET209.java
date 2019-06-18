package com.zm.LeetCodeEx;

/**
 * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的连续子数组。如果不存在符合条件的连续子数组，返回 0。
 * 
 * 示例:
 * 
 * 输入: s = 7, nums = [2,3,1,2,4,3] 输出: 2 解释: 子数组 [4,3] 是该条件下的长度最小的连续子数组。
 * 
 * 
 * @author zm
 *
 */
public class LEET209 {
	public static void main(String[] args) {
		LEET209 l209 = new LEET209();
		int[] nums = { 2, 3, 1, 2, 4, 3 };
		int s = 7;
		System.out.println(l209.minSubArrayLen3(s, nums));

	}

	public int minSubArrayLen(int s, int[] nums) {
		int i = 0, j = 0;
		int minlen = nums.length + 1;
		while (i < nums.length && j < nums.length) {
			int sum = 0;
			for (int i2 = i; i2 <= j; i2++) {
				sum += nums[i2];
			}
			// System.out.println("i="+i+",j="+j+",sum="+sum+",len="+(j - i +
			// 1));
			if (sum < s) {
				++j;
			} else {
				minlen = minlen < j - i + 1 ? minlen : j - i + 1;
				if (minlen == 1) {
					break;
				}
				if (sum > s) {
					if (i == j) {
						++i;
						++j;
					} else {
						++i;
					}
				} else {
					++i;
					++j;
				}
			}
		}
		return minlen == nums.length + 1 ? 0 : minlen;
	}

	public int minSubArrayLen2(int s, int[] nums) {
		int i = 0, j = 0;
		int minlen = nums.length + 1;
		if (nums.length == 0) {
			return 0;
		}
		int sum = nums[0];
		while (i < nums.length && j < nums.length) {
			System.out.println("i=" + i + ",j=" + j + ",sum=" + sum + ",len=" + (j - i + 1));
			if (sum < s) {
				if (++j < nums.length) {
					sum += nums[j];
				} else {
					break;
				}
			} else {
				minlen = minlen < j - i + 1 ? minlen : j - i + 1;
				if (minlen == 1) {
					break;
				}
				if (sum > s) {
					if (i == j) {
						if (++j < nums.length) {
							sum = nums[j];
							i = j;
						} else {
							break;
						}
					} else {
						sum -= nums[i++];
					}
				} else {
					if (++j < nums.length) {
						sum -= nums[i++];
						sum += nums[j];
					} else {
						break;
					}
				}
			}
		}
		return minlen == nums.length + 1 ? 0 : minlen;
	}

	public int minSubArrayLen3(int s, int[] nums) {
		int i = 0, j = 0;
		int sum = 0;
		int minlen = nums.length + 1;
		for (j = 0; j < nums.length; j++) {
			sum += nums[j];
			while (sum >= s && i <= j) {
				System.out.println("i=" + i + ",j=" + j + ",sum=" + sum + ",len=" + (j - i + 1));
				minlen = Math.min(minlen, j - i + 1);
				if (minlen == 1) {
					break;
				}
				sum -= nums[i++];
			}
		}
		return minlen == nums.length + 1 ? 0 : minlen;
	}
}
