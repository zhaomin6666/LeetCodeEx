package com.zm.LeetCodeEx.algorithms;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;

/**
 * 78. 子集
 * <p>
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * <p>
 * 说明：解集不能包含重复的子集。
 * <p>
 * 示例:
 * <p>
 * 输入: nums = [1,2,3]<br>
 * 输出:<br>
 * [<br>
 * [3],<br>
 *   [1],<br>
 *   [2],<br>
 *   [1,2,3],<br>
 *   [1,3],<br>
 *   [2,3],<br>
 *   [1,2],<br>
 *   []<br>
 * ]
 * 
 * 
 * @author zm
 */
public class LEET078 {
	public static void main(String[] args) {
		LEET078 l078 = new LEET078();
		int[] nums1 = { 1, 2, 3 };
		int[] nums2 = { 1, 2, 3, 4 };
		System.out.println(JSON.toJSONString(l078.new Solution3().subsets(nums1)));
		System.out.println(l078.new Solution().subsets(nums2));
	}

	class Solution {
		public List<List<Integer>> subsets(int[] nums) {
			List<List<Integer>> output = new ArrayList<>();
			int n = nums.length;
			int nthBit = 1 << n;
			for (int i = 0; i < 1 << n; ++i) {
				String bitmask = Integer.toBinaryString(i | nthBit).substring(1);
				// 这边的for循环起始和结束参考了官方题解还可以使用
				// for (int i = 1 << n; i < 1 << (n + 1); ++i) {
				// String bitmask = Integer.toBinaryString(i).substring(1);
				List<Integer> curr = new ArrayList<>();
				for (int j = 0; j < n; ++j) {
					if (bitmask.charAt(j) == '1') {
						curr.add(nums[j]);
					}
				}
				output.add(curr);
			}
			return output;
		}
	}

	/**
	 * 回溯法
	 * 
	 * @author zm
	 *
	 */
	class Solution2 {
		List<List<Integer>> output = new ArrayList<>();
		int n, k;

		public void backtrack(int first, ArrayList<Integer> curr, int[] nums) {
			if (curr.size() == k) {
				output.add(new ArrayList<>(curr));
			}
			for (int i = first; i < n; ++i) {
				curr.add(nums[i]);
				backtrack(i + 1, curr, nums);
				curr.remove(curr.size() - 1);
			}
		}

		public List<List<Integer>> subsets(int[] nums) {
			n = nums.length;
			for (k = 0; k < n + 1; ++k) {
				backtrack(0, new ArrayList<Integer>(), nums);
			}
			return output;
		}
	}

	/**
	 * 循环在原来的基础上添加新的数
	 * @author zm
	 *
	 */
	class Solution3 {
		public List<List<Integer>> subsets(int[] nums) {
			List<List<Integer>> output = new ArrayList<>();
			output.add(new ArrayList<Integer>());

			for (int num : nums) {
				List<List<Integer>> newSubsets = new ArrayList<>();
				for (List<Integer> curr : output) {
					newSubsets.add(new ArrayList<Integer>(curr) {
						{
							add(num);
						}
					});
				}
				for (List<Integer> curr : newSubsets) {
					output.add(curr);
				}
			}
			return output;
		}
	}
}
