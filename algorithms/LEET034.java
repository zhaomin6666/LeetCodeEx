package com.zm.LeetCodeEx.algorithms;

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
		int[] nums = { 2, 2 };
		System.out.println(JSON.toJSONString(l034.searchRange(nums, 3)));
	}

	public int[] searchRange(int[] nums, int target) {
		int[] ret = searchRange(nums, 0, nums.length - 1, target);
		return ret;
	}

	public int[] searchRange(int[] nums, int first, int last, int target) {
		int begin = search2(nums, first, last, target, 1);
		int end = search2(nums, first, last, target, -1);
		int[] ret = new int[2];
		ret[0] = end;
		ret[1] = begin;
		return ret;
	}

	/**
	 * 参数direction用来控制在找到target值之后是继续向后查找还是向前查找
	 * 
	 * @param nums
	 * @param first
	 * @param last
	 * @param target
	 * @param direction
	 * @return
	 */
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
		int mid = (first + last) >>> 1;
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
				return mid;
			}
		} else if (nums[mid] > target) {
			ret = search(nums, first, mid - 1 >= first ? mid - 1 : first, target, direction);
		} else {
			ret = search(nums, mid + 1, last, target, direction);
		}
		return ret;
	}

	/**
	 * 二分法不使用递归，使用循环
	 * 
	 * @param nums
	 * @param first
	 * @param last
	 * @param target
	 * @param direction
	 * @return
	 */
	public int search2(int[] nums, int first, int last, int target, int direction) {
		int ret = -1;
		if (nums == null || nums.length == 0) {
			return ret;
		}

		// 先判断一波，可以去掉
//		if (first == last) {
//			if (target == nums[first]) {
//				return first;
//			} else {
//				return ret;
//			}
//		}

		// 二分法查找
		while (first <= last) {
			int mid = (first + last) >>> 1;
			if (nums[mid] == target) {
				ret = mid;
				if (direction > 0) {
					first = mid + 1;
				} else {
					last = mid - 1;
				}
			} else if (nums[mid] > target) {
				last = mid - 1;
			} else {
				first = mid + 1;
			}
		}
		return ret;
	}
}
