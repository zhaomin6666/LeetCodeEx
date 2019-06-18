package com.zm.LeetCodeEx;

import com.alibaba.fastjson.JSON;

/**
 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素最多出现两次，返回移除后数组的新长度。
 * 
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 * 
 * 示例 1: 给定 nums = [1,1,1,2,2,3], 函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2,
 * 2, 3 。 你不需要考虑数组中超出新长度后面的元素。 <br>
 * 示例 2: 给定 nums = [0,0,1,1,1,1,2,3,3], 函数应返回新长度 length = 7, 并且原数组的前五个元素被修改为 0,
 * 0, 1, 1, 2, 3, 3 。 你不需要考虑数组中超出新长度后面的元素。
 * 
 * @author zm
 *
 */
public class LEET080 {
	public static void main(String[] args) {
		LEET080 l080 = new LEET080();
		int[] nums = { 1, 1, 1, 1, 2, 3, 4, 4, 4, 5 };
		int[] nums2 = { 1, 1, 1, 2, 2, 3 };
		int[] nums3 = { 1, 2 };
		System.out.println(l080.removeDuplicates2(nums));
		System.out.println(JSON.toJSON(nums));
		System.out.println(l080.removeDuplicates2(nums2));
		System.out.println(JSON.toJSON(nums2));
		System.out.println(l080.removeDuplicates2(nums3));
		System.out.println(JSON.toJSON(nums3));

	}

	/**
	 * 评论中的方法。直接判断当前数字和他前面的第二个
	 * 
	 * @param nums
	 * @return
	 */
	public int removeDuplicates(int[] nums) {
		int i = 0;
		for (int n : nums)
			if (i < 2 || n > nums[i - 2])
				nums[i++] = n;
		return i;
	}

	/**
	 * 双指针，类似于之前做过一个去掉数列中重复项，这里加入cnt值。
	 * 
	 * @param nums
	 * @return
	 */
	public int removeDuplicates2(int[] nums) {
		int i = 0;
		int count = 1;
		for (int j = 1; j < nums.length; j++) {
			if (nums[i] != nums[j]) {
				nums[++i] = nums[j];
				count = 1;
			} else if (count >= 2) {
				continue;
			} else {
				nums[++i] = nums[j];
				count++;
			}
		}
		return i + 1;
	}

}
