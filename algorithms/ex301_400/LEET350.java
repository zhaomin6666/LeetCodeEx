package com.zm.LeetCodeEx.algorithms.ex301_400;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

import com.alibaba.fastjson.JSON;

/**
 * 给定两个数组，编写一个函数来计算它们的交集。</br>
 * 注：相比上一个题目这里不需要去重</br>
 * 
 * 示例 1: </br>
 * 
 * 输入: nums1 = [1,2,2,1], nums2 = [2,2] 输出: [2,2] </br>
 * 示例 2: </br>
 * 
 * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4] 输出: [9,4] </br>
 * 
 * 说明: </br>
 * 
 * 输出结果中的每个元素一定是唯一的。 我们可以不考虑输出结果的顺序。
 * 
 * 进阶:</br>
 * 
 * 如果给定的数组已经排好序呢？你将如何优化你的算法？ </br>
 * 如果 nums1 的大小比 nums2 小很多，哪种方法更优？</br>
 * 如果 nums2 的元素存储在磁盘上，磁盘内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？</br>
 * 
 * 1.算法已经是 排序过的算法</br>
 * 2.算法1因为需要排序，快排是O(max(m,n)log(max(m,n))),然后遍历两个数组O(n+m)，算法二遍历两个数组O(n+m),hashmap操作是O(1)(理想状态)
 * 若两个nums数组大小都很大，使用方法1可以减少内存消耗，如果nums1的大小小很多，用第二种的把nums1存入hashmap</br>
 * 3.使用第二种方法，hashmap记录次数，这么nums2可以分批读取
 * 
 * @author zm
 *
 */
public class LEET350 {
	public static void main(String[] args) {
		LEET350 l349 = new LEET350();
		int[] nums1 = { 1, 2, 2, 1 };
		int[] nums2 = { 2, 2 };
		int[] nums3 = { 4, 9, 5 };
		int[] nums4 = { 9, 4, 9, 8, 4 };
		System.out.println(JSON.toJSONString(l349.intersection2(nums1, nums2)));
		System.out.println(JSON.toJSONString(l349.intersection2(nums3, nums4)));
		// test(l349);

	}

	@SuppressWarnings("unused")
	private static void test(LEET350 l349) {
		int[] numstest1 = new int[100000];
		Random random = new Random();
		for (int i = 0; i < numstest1.length; i++) {
			numstest1[i] = random.nextInt(1000000);
		}
		int[] numstest2 = new int[100000];
		for (int i = 0; i < numstest1.length; i++) {
			numstest2[i] = random.nextInt(1000000);
		}
		long start1 = System.currentTimeMillis();
		l349.intersection(numstest1, numstest2);
		long end1 = System.currentTimeMillis();
		l349.intersection2(numstest1, numstest2);
		long end2 = System.currentTimeMillis();
		System.out.println(end1 - start1);
		System.out.println(end2 - end1);
	}

	/**
	 * 双指针，排序算法快排也就是nlogn
	 * 
	 * @param nums1
	 * @param nums2
	 * @return
	 */
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
			}
		}
		if (k == 0) {
			return new int[] {};
		}
		return Arrays.copyOfRange(res, 0, k);
	}

	/**
	 * 使用Map+List 理论上是O(n)级别
	 * 
	 * @param nums1
	 * @param nums2
	 * @return
	 */
	public int[] intersection2(int[] nums1, int[] nums2) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		// 先遍历第一个数组，初始化map
		for (int i = 0; i < nums1.length; i++) {
			if (map.containsKey(nums1[i]))
				map.put(nums1[i], map.get(nums1[i]) + 1);
			else
				map.put(nums1[i], 1);
		}

		// 再遍历第二个数组，将于map中找到的key放入list中
		LinkedList<Integer> list = new LinkedList<Integer>();
		for (int j = 0; j < nums2.length; j++) {
			if (map.containsKey(nums2[j]) && map.get(nums2[j]) > 0) {
				list.add(nums2[j]); // 添加到list中
				map.put(nums2[j], map.get(nums2[j]) - 1);
			}
		}

		// 最后，将list中的值放入数组中
		int count = list.size();
		int[] res = new int[count];
		for (int i = 0; i < count; i++) {
			res[i] = list.poll();
		}
		return res;
	}
}
