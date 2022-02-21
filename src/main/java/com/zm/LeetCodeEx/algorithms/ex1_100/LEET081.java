package com.zm.LeetCodeEx.algorithms.ex1_100;

/**
 * 81. 搜索旋转排序数组 II
 * <p>
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * <p>
 * ( 例如，数组 [0,0,1,2,2,5,6] 可能变为 [2,5,6,0,0,1,2] )。
 * <p>
 * 编写一个函数来判断给定的目标值是否存在于数组中。若存在返回 true，否则返回 false。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [2,5,6,0,0,1,2], target = 0<br>
 * 输出: true<br>
 * 示例 2:
 * <p>
 * 输入: nums = [2,5,6,0,0,1,2], target = 3<br>
 * 输出: false<br>
 * 进阶:
 * <p>
 * 这是 搜索旋转排序数组 的延伸题目，本题中的 nums  可能包含重复元素。 <br>
 * 这会影响到程序的时间复杂度吗？会有怎样的影响，为什么？
 *
 * 
 * @author zm
 */
public class LEET081 {
	public static void main(String[] args) {
		LEET081 l081 = new LEET081();
		System.out.println(l081.new Solution().search(new int[] { 2, 5, 6, 0, 0, 1, 2 }, 0));
		System.out.println(l081.new Solution().search(new int[] { 2, 5, 6, 0, 0, 1, 2 }, 3));
	}

	class Solution {
		public boolean search(int[] nums, int target) {
			return search(nums, 0, nums.length - 1, target);
		}

		public boolean search(int[] nums, int first, int last, int target) {
			boolean ret = false;
			if (nums == null || nums.length == 0) {
				return ret;
			}
			if (first == last) {
				if (target == nums[first]) {
					return true;
				} else {
					return ret;
				}
			}
			int mid = (first + last) >>> 1;
			if (nums[first] >= nums[last]) {
				// 不是有序数列，分为两部分
				ret = search(nums, first, mid, target);
				if (!ret) {
					ret = search(nums, mid + 1, last, target);
				}
			} else {
				// 是一个有序数列
				// 判断是否在这个有序数列里面
				if (target >= nums[first] && target <= nums[last]) {
					// 二分法查找
					if (nums[mid] == target) {
						return true;
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

}
