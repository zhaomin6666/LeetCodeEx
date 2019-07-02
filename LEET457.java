package com.zm.LeetCodeEx;

/**
 * 给定一个含有正整数和负整数的环形数组 nums。 如果某个索引中的数 k 为正数，则向前移动 k 个索引。相反，如果是负数 (-k)，则向后移动
 * k 个索引。因为数组是环形的，所以可以假设最后一个元素的下一个元素是第一个元素，而第一个元素的前一个元素是最后一个元素。
 * 
 * 确定 nums 中是否存在循环（或周期）。循环必须在相同的索引处开始和结束并且循环长度 >
 * 1。此外，一个循环中的所有运动都必须沿着同一方向进行。换句话说，一个循环中不能同时包括向前的运动和向后的运动。  
 * 
 * 示例 1：
 * 
 * 输入：[2,-1,1,2,2] 输出：true 解释：存在循环，按索引 0 -> 2 -> 3 -> 0 。循环长度为 3 。
 * 
 * 示例 2：
 * 
 * 输入：[-1,2] 输出：false 解释：按索引 1 -> 1 -> 1 ... 的运动无法构成循环，因为循环的长度为 1
 * 。根据定义，循环的长度必须大于 1 。
 * 
 * 示例 3:
 * 
 * 输入：[-2,1,-1,-2,-2] 输出：false 解释：按索引 1 -> 2 -> 1 -> ... 的运动无法构成循环，因为按索引 1 -> 2
 * 的运动是向前的运动，而按索引 2 -> 1 的运动是向后的运动。一个循环中的所有运动都必须沿着同一方向进行。
 * 
 * 
 * @author zm
 *
 */
public class LEET457 {
	public static void main(String[] args) {
		LEET457 l457 = new LEET457();
		int[] nums1 = { 2, -1, 1, 2, 2 };
		System.out.println(l457.circularArrayLoop2(nums1));
		int[] nums2 = { -1, 2 };
		System.out.println(l457.circularArrayLoop2(nums2));
		int[] nums3 = { -2, 1, -1, -2, -2 };
		System.out.println(l457.circularArrayLoop2(nums3));
		int[] nums4 = { 1, 1, 2 };
		System.out.println(l457.circularArrayLoop2(nums4));
	}

	/**
	 * 
	 * @param nums
	 * @return
	 */
	public boolean circularArrayLoop(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			// time:记录循环中元素的个数，用来判断是否满足题意(循环体长度必须超过1)
			// count:记录一共走了多少步，用来及时跳出循环(count数小于数组长度即可，因为走完整个数组时候必定会走到重复的，这时就会产生循环，只是此时的循环不一定会满足需求)
			int count = 0, time = 0;
			if (nums[i] < 0) {
				// 向后走
				for (int j = i; count < nums.length; count++) {
					if (nums[j] > 0) {
						break;
					} else {
						int temp = j;
						j = j + nums[j];
						while (j < 0) {
							j += nums.length;
						}
						if (temp != j) {
							time++;
						}
					}
					if (j == i && time > 1) {
						return true;
					}
				}
			} else if (nums[i] > 0) {
				for (int j = i; count < nums.length; count++) {
					if (nums[j] < 0) {
						break;
					} else {
						int temp = j;
						j = j + nums[j];
						while (j >= nums.length) {
							j -= nums.length;
						}
						if (temp != j) {
							time++;
						}
					}
					if (j == i && time > 1) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * 增加表标记
	 * 
	 * @param nums
	 * @return
	 */
	public boolean circularArrayLoop2(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			// time:记录循环中元素的个数，用来判断是否满足题意(循环体长度必须超过1)
			// count:记录一共走了多少步，用来及时跳出循环(count数小于数组长度即可，因为走完整个数组时候必定会走到重复的，这时就会产生循环，只是此时的循环不一定会满足需求)
			int count = 0, time = 0;
			if (nums[i] < 0) {
				// 向后走
				for (int j = i; count < nums.length; count++) {
					if (j < i) {
						break;
					}
					if (nums[j] > 0) {
						break;
					} else {
						int temp = j;
						j = j + nums[j];
						while (j < 0) {
							j += nums.length;
						}
						if (temp != j) {
							time++;
						}
					}
					if (j == i && time > 1) {
						return true;
					}
				}
			} else if (nums[i] > 0) {
				for (int j = i; count < nums.length; count++) {
					if (j < i) {
						break;
					}
					if (nums[j] < 0) {
						break;
					} else {
						int temp = j;
						j = j + nums[j];
						while (j >= nums.length) {
							j -= nums.length;
						}
						if (temp != j) {
							time++;
						}
					}
					if (j == i && time > 1) {
						return true;
					}
				}
			}
		}
		return false;
	}

}
