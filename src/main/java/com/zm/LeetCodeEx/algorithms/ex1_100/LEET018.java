package com.zm.LeetCodeEx.algorithms.ex1_100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c
 * + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
 * 
 * 注意：
 * 
 * 答案中不可以包含重复的四元组。
 * 
 * 示例：
 * 
 * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
 * 
 * 满足要求的四元组集合为： [ [-1, 0, 0, 1], [-2, -1, 1, 2], [-2, 0, 0, 2] ]
 * 
 * @author zm
 *
 */
public class LEET018 {
	public static void main(String[] args) {
		LEET018 l018 = new LEET018();
		int[] numarray = { 1, 0, -1, 0, -2, 2 };
		int[] numarray2 = { 0, 0, 0, 0 };
		int[] numarray3 = { 1, -2, -5, -4, -3, 3, 3, 5 };// [1,-2,-5,-4,-3,3,3,5]
		System.out.println(JSONObject.toJSONString(l018.fourSum(numarray3, -11)));
		System.out.println(JSONObject.toJSONString(l018.fourSum(numarray, 1)));
		System.out.println(JSONObject.toJSONString(l018.fourSum(numarray2, 0)));
	}

	HashMap<Character, String> hashMap = new HashMap<>();
	List<String> ret = new LinkedList<String>();

	/**
	 * 利用之前的三数之和，新增一个target参数使三数之和等于某一值
	 * 
	 * @param nums
	 * @param target
	 * @return
	 */
	public List<List<Integer>> fourSum(int[] nums, int target) {
		List<List<Integer>> ret = new ArrayList<List<Integer>>();
		if (nums.length < 4) {
			return ret;
		}
		Arrays.sort(nums);
		for (int i = 0; i < nums.length - 3; i++) {
			List<List<Integer>> ans = threeSum2(Arrays.copyOfRange(nums, i + 1, nums.length), -nums[i] + target);
			if (!ans.isEmpty()) {
				for (List<Integer> list : ans) {
					List<Integer> lst = new ArrayList<Integer>();
					lst.add(nums[i]);
					lst.addAll(list);
					ret.add(lst);
				}
			}
			while (i < nums.length - 3 && nums[i] == nums[i + 1]) {
				i++;
			}
		}
		return ret;
	}

	/**
	 * <b>双指针法</b><br>
	 * <b>增加了target参数</b><br>
	 * 先进行排序，取定第一个数之后使用双指针法 如果和大了那么右指针左移，如果和小了左指针右移
	 * 
	 * @param nums
	 * @return
	 */
	public static List<List<Integer>> threeSum2(int[] nums, int target) {
		Arrays.sort(nums);
		List<List<Integer>> lists = new ArrayList<>();
		int len = nums.length;
		for (int i = 0; i < len; i++) {
			int target2 = -nums[i] + target;
			if (target2 < 0 && nums[i] > 0) {
				break;
			}
			int j = i + 1, k = len - 1;
			while (j < k) {
				if (nums[j] + nums[k] == target2) {
					lists.add(Arrays.asList(nums[i], nums[j], nums[k]));
					int v1 = nums[j], v2 = nums[k];
					// 下面两个判断做了优化处理
					while (j < k && nums[j] == v1) {
						j++;
					}
					while (j < k && nums[k] == v2) {
						k--;
					}
				} else if (nums[j] + nums[k] < target2) {
					j++;
				} else {
					k--;
				}
			}
			while (i + 1 < len && nums[i] == nums[i + 1]) {
				i++;
			}
		}
		return lists;
	}
}
