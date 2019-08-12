package com.zm.LeetCodeEx;

/**
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * 
 * 你可以假设数组中无重复元素。
 * <p>
 * 示例 1: 输入: [1,3,5,6], 5 输出: 2
 * <p>
 * 示例 2: 输入: [1,3,5,6], 2 输出: 1
 * <p>
 * 示例 3: 输入: [1,3,5,6], 7 输出: 4
 * <p>
 * 示例 4: 输入: [1,3,5,6], 0 输出: 0
 * 
 * @author zm
 */
public class LEET035 {
	public static void main(String[] args) {
		LEET035 l035 = new LEET035();
		int[] nums = { 1, 3, 5, 6 };
		System.out.println(l035.searchInsert(nums, 0));
	}

	public int searchInsert(int[] nums, int target) {
		int first = 0;
		int last = nums.length - 1;
		while (first <= last) {
			int mid = (first + last) >>> 1;
			if (nums[mid] == target) {
				return mid;
			} else if (nums[mid] > target) {
				last = mid - 1;
			} else {
				first = mid + 1;
			}
		}
		return first;
	}
}
