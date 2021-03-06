package com.zm.LeetCodeEx.algorithms.ex101_200;

import com.alibaba.fastjson.JSON;

/**
 * 给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。
 * 
 * 函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。
 * 
 * 说明:
 * 
 * 返回的下标值（index1 和 index2）不是从零开始的。 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。 示例:
 * 
 * 输入: numbers = [2, 7, 11, 15], target = 9 输出: [1,2] 解释: 2 与 7 之和等于目标数 9 。因此
 * index1 = 1, index2 = 2 。
 * 
 * 
 * 
 * @author zm
 *
 */
public class LEET167 {
	public static void main(String[] args) {
		LEET167 l167 = new LEET167();
		int[] input1 = { 2, 7, 11, 15 };
		System.out.println(JSON.toJSONString(l167.twoSum(input1, 9))); // 3
		int[] input2 = { 2, 7, 8, 15 };
		System.out.println(JSON.toJSONString(l167.twoSum(input2, 15)));

	}

	public int[] twoSum(int[] numbers, int target) {
		if (numbers == null || numbers.length < 2) {
			return new int[] {};
		}
		int i = 0, j = numbers.length - 1;
		while (i < j) {
			if (numbers[i] + numbers[j] == target) {
				return new int[] { i, j };
			} else if (numbers[i] + numbers[j] > target) {
				--j;
			} else {
				++i;
			}
		}
		return new int[] {};
	}
}
