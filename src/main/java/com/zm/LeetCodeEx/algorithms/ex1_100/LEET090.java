package com.zm.LeetCodeEx.algorithms.ex1_100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.alibaba.fastjson.JSON;

/**
 * 90. 子集 II
 * <p>
 * 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。<br>
 * 
 * 说明：解集不能包含重复的子集。<br>
 * 
 * 示例:<br>
 * 
 * 输入: [1,2,2]<br>
 * 输出:<br>
 * [<br>
 * [2],<br>
 * [1],<br>
 * [1,2,2],<br>
 * [2,2],<br>
 * [1,2],<br>
 * []<br>
 * ]
 * 
 * 
 * @author zm
 */
public class LEET090 {
	public static void main(String[] args) {
		LEET090 l090 = new LEET090();
		int[] nums1 = { 1, 2, 2 };
		int[] nums2 = { 1, 2, 2, 2 };
		int[] nums3 = { 4, 4, 1, 4 };
		System.out.println(JSON.toJSONString(l090.new Solution().subsetsWithDup(nums1)));
		System.out.println(JSON.toJSONString(l090.new Solution().subsetsWithDup(nums2)));
		System.out.println(JSON.toJSONString(l090.new Solution().subsetsWithDup(nums3)));
	}

	/**
	 * 回溯法，沿用{@link LEET078} 1.增加回溯时的判断 需要和上一个数不相同 2.需要先排序
	 * 
	 * @author zm
	 *
	 */
	class Solution {
		List<List<Integer>> output = new ArrayList<>();
		int n, k;

		public void backtrack(int first, ArrayList<Integer> curr, int[] nums) {
			if (curr.size() == k) {
				output.add(new ArrayList<>(curr));
			}
			for (int i = first, j = 0; i < n; ++i, ++j) {
				if (j > 0 && nums[i] == nums[i - 1]) {
					continue;
				}
				curr.add(nums[i]);
				backtrack(i + 1, curr, nums);
				curr.remove(curr.size() - 1);
			}
		}

		public List<List<Integer>> subsetsWithDup(int[] nums) {
			n = nums.length;
			Arrays.sort(nums);
			for (k = 0; k < n + 1; ++k) {
				backtrack(0, new ArrayList<Integer>(), nums);
			}
			return output;
		}
	}
}
