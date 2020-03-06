package com.zm.LeetCodeEx.algorithms;

/**
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * <p>
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * <p>
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 * <p>
 * 你可以假设数组中不存在重复的元素。
 * <p>
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [4,5,6,7,0,1,2], target = 0 输出: 4
 * <p>
 * 示例 2:
 * <p>
 * 输入: nums = [4,5,6,7,0,1,2], target = 3 输出: -1
 * 
 *
 * @author zm
 */
public class LEET033 {
	public static void main(String[] args) {
		LEET033 l033 = new LEET033();
		int[] nums = { 4, 5, 6, 7, 0, 1, 2 };
		System.out.println(l033.search(nums, 1));
	}

	/**
	 * O(log n)能够最先想到的就是二分法查找，由于旋转过的数组分为两部分之后肯定有一部分是有序的，另一部分可能是有序的
	 * 
	 * @param nums
	 * @param target
	 * @return
	 */
	public int search(int[] nums, int target) {
		int ret = search(nums, 0, nums.length - 1, target);
		return ret;
	}

	public int search(int[] nums, int first, int last, int target) {
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
		if (nums[first] > nums[last]) {
			// 不是有序数列，分为两部分
			ret = search(nums, first, mid, target);
			if (ret == -1) {
				ret = search(nums, mid + 1, last, target);
			}
		} else {
			// 是一个有序数列
			// 判断是否在这个有序数列里面
			if (target >= nums[first] && target <= nums[last]) {
				// 二分法查找
				if (nums[mid] == target) {
					return mid;
				} else if (nums[mid] > target) {
					ret = search(nums, first, mid - 1, target);
				} else {
					ret = search(nums, mid + 1, last, target);
				}
			}

		}
		return ret;

	}

}
