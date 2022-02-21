package com.zm.LeetCodeEx.algorithms.ex1_100;

import com.alibaba.fastjson.JSON;

/**
 * 给定一个数组 nums 和一个值 val，你需要原地移除所有数值等于 val 的元素，返回移除后数组的新长度。
 * 
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 * 
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 * 
 * 示例 1:
 * 
 * 给定 nums = [3,2,2,3], val = 3,
 * 
 * 函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。
 * 
 * 你不需要考虑数组中超出新长度后面的元素。 示例 2:
 * 
 * 给定 nums = [0,1,2,2,3,0,4,2], val = 2,
 * 
 * 函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。
 * 
 * 注意这五个元素可为任意顺序。
 * 
 * 你不需要考虑数组中超出新长度后面的元素。
 * 
 * @author zm
 *
 */
public class LEET027 {
	public static void main(String[] args) {
		LEET027 l027 = new LEET027();
		int[] nums1 = { 0, 0, 1, 1, 1, 2, 2, 3, 3, 4 };
		int[] nums2 = { 0, 0, 2, 0 };
		System.out.println(l027.removeElement(nums1, 0));
		System.out.println(JSON.toJSONString(nums1));
		System.out.println(l027.removeElement2(nums2, 0));
		System.out.println(JSON.toJSONString(nums2));
	}

	/**
	 * 两个指针都从头开始，如果是需要去除的，右指针右移1，不是的话，把右指针位置的元素放到左指针上，并且左右指针一起右移
	 * 
	 * @param nums
	 * @param val
	 * @return
	 */
	public int removeElement(int[] nums, int val) {
		int l = 0;
		for (int i = 0; i < nums.length; i++) {
			while (i < nums.length && nums[i] == val) {
				i++;
			}
			if (i == nums.length) {
				break;
			}
			nums[l++] = nums[i];
		}
		return l;
	}

	/**
	 * 更简洁，没必要上面一个方法多余的步骤
	 * 
	 * @param nums
	 * @param val
	 * @return
	 */
	public int removeElement2(int[] nums, int val) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		int i = 0;
		for (int j = 0; j < nums.length; j++) {
			if (nums[j] != val) {
				nums[i++] = nums[j];
			}
		}
		return i;
	}
}
