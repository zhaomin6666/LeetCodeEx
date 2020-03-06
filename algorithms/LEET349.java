package com.zm.LeetCodeEx.algorithms;

import java.util.Arrays;

import com.alibaba.fastjson.JSON;

/**
 * 给定两个数组，编写一个函数来计算它们的交集。</br>
 * 
 * 示例 1: </br>
 * 
 * 输入: nums1 = [1,2,2,1], nums2 = [2,2] 输出: [2] </br>
 * 示例 2: </br>
 * 
 * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4] 输出: [9,4] </br>
 * 
 * 说明: </br>
 * 
 * 输出结果中的每个元素一定是唯一的。 我们可以不考虑输出结果的顺序。
 * 
 * 
 * @author zm
 *
 */
public class LEET349 {
	public static void main(String[] args) {
		LEET349 l349 = new LEET349();
		int[] nums1 = { 1, 2, 2, 1 };
		int[] nums2 = { 2, 2 };
		int[] nums3 = { 4, 9, 5 };
		int[] nums4 = { 9, 4, 9, 8, 4 };
		System.out.println(JSON.toJSONString(l349.intersection(nums1, nums2)));
		System.out.println(JSON.toJSONString(l349.intersection(nums3, nums4)));
	}

	public int[] intersection(int[] nums1, int[] nums2) {
		Arrays.sort(nums1);
		Arrays.sort(nums2);
		int[] res = new int[nums1.length > nums2.length ? nums2.length : nums1.length];
		int k = 0;
		int i = 0, j = 0;
		while (i < nums1.length && j < nums2.length) {
			if (nums1[i] < nums2[j]) {
				i++;
				while (i < nums1.length && nums1[i] == nums1[i - 1]) {
					i++;
				}
			} else if (nums1[i] > nums2[j]) {
				j++;
				while (j < nums2.length && nums2[j] == nums2[j - 1]) {
					j++;
				}
			} else {
				res[k] = nums1[i];
				k++;
				i++;
				j++;
				while (i < nums1.length && nums1[i] == nums1[i - 1]) {
					i++;
				}
				while (j < nums2.length && nums2[j] == nums2[j - 1]) {
					j++;
				}
			}
		}
		if (k == 0) {
			return new int[] {};
		}
		return Arrays.copyOfRange(res, 0, k);
	}
}
