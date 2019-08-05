package com.zm.LeetCodeEx;

import java.math.BigDecimal;

import com.alibaba.fastjson.JSON;

/**
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * <p>
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * <p>
 * 如果数组中不存在目标值，返回 [-1, -1]。
 * <p>
 * 示例 1:
 * 
 * 输入: nums = [5,7,7,8,8,10], target = 8 输出: [3,4]
 * <p>
 * 示例 2:
 * 
 * 输入: nums = [5,7,7,8,8,10], target = 6 输出: [-1,-1]
 * 
 *
 * @author zm
 */
public class LEET034 {
	public static void main(String[] args) {
		LEET034 l034 = new LEET034();
		int[] nums = { 5, 7, 7, 8, 8, 10 };
		System.out.println(JSON.toJSONString(l034.searchRange(nums, 8)));

	}

	public int[] searchRange(int[] nums, int target) {
		int[] ret = searchRange(nums, 0, nums.length - 1, target);
		return ret;
	}

	public int[] searchRange(int[] nums, int first, int last, int target) {
		int begin = search(nums, first, last, target, 1);
		int end = search(nums, first, last, target, -1);
		int[] ret = new int[2];
		ret[0] = begin;
		ret[1] = end;
		return ret;
	}

	public int search(int[] nums, int first, int last, int target, int direction) {
		int ret = -1;
		if (nums == null || nums.length == 0) {
			return ret;
		}
		if (first == last) {
			if (target == nums[first]) {
				return first;
			} else {
				return ret;
			}
		}
		int mid = (first + last) / 2;
		// 二分法查找
		if (nums[mid] == target) {
			if (direction > 0) {
				ret = search(nums, mid + 1, last, target, direction);
				
			} else if (direction < 0) {
				ret = search(nums, first, mid - 1, target, direction);
			} else {
				return ret;
			}
			if (ret == -1) {
				return ret;
			}
		} else if (nums[mid] > target) {
			ret = search(nums, first, mid - 1, target, direction);
		} else {
			ret = search(nums, mid + 1, last, target, direction);
		}
		return ret;
	}
}
