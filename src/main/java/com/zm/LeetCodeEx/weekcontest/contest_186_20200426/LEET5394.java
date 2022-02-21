package com.zm.LeetCodeEx.weekcontest.contest_186_20200426;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.zm.LeetCodeEx.CommonFunctions;

/**
 * 周赛 2020年4月26日
 * <p>
 * 5394. 对角线遍历 II
 * <p>
 * 给你一个列表 nums ，里面每一个元素都是一个整数列表。请你依照下面各图的规则，按顺序返回 nums 中对角线上的整数。
 * <p>
 * 示例 1： <br>
 * 输入：nums = [[1,2,3],[4,5,6],[7,8,9]] <br>
 * 输出：[1,4,2,7,5,3,8,6,9]
 * <p>
 * 示例 2： 输入：nums = [[1,2,3,4,5],[6,7],[8],[9,10,11],[12,13,14,15,16]] <br>
 * 输出：[1,6,2,8,7,3,9,4,12,10,5,13,11,14,15,16] <br>
 * <p>
 * 示例 3： 输入：nums = [[1,2,3],[4],[5,6,7],[8],[9,10,11]] <br>
 * 输出：[1,4,2,5,3,8,6,9,7,10,11] <br>
 * <p>
 * 示例 4：<br>
 * 输入：nums = [[1,2,3,4,5,6]] <br>
 * 输出：[1,2,3,4,5,6]
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 10^5 <br>
 * 1 <= nums[i].length <= 10^5 <br>
 * 1 <= nums[i][j] <= 10^9 <br>
 * nums 中最多有 10^5 个数字。 <br>
 *
 * @author zm
 */
public class LEET5394 {
	public static void main(String[] args) {
		LEET5394 l5394 = new LEET5394();
		System.out.println(Arrays.toString(l5394.new Solution()
				.findDiagonalOrder(CommonFunctions.stringToIntegerArrayList("[[1,2,3],[4,5,6],[7,8,9]]"))));
		System.out.println(Arrays.toString(l5394.new Solution().findDiagonalOrder(
				CommonFunctions.stringToIntegerArrayList("[[1,2,3,4,5],[6,7],[8],[9,10,11],[12,13,14,15,16]]"))));
		System.out.println(Arrays.toString(l5394.new Solution()
				.findDiagonalOrder(CommonFunctions.stringToIntegerArrayList("[[1,2,3],[4],[5,6,7],[8],[9,10,11]]"))));
		System.out.println(Arrays.toString(
				l5394.new Solution().findDiagonalOrder(CommonFunctions.stringToIntegerArrayList("[[1,2,3,4,5,6]]"))));
	}

	class Solution {
		public int[] findDiagonalOrder(List<List<Integer>> nums) {
			List<Integer> list = new LinkedList<>();
			int[] index = new int[] { 0, 0 };
			Queue<int[]> queue = new LinkedList<int[]>();
			queue.add(index);
			while (!queue.isEmpty()) {
				int size = queue.size();
				for (int i = 0; i < size; i++) {
					int[] temp = queue.poll();
					list.add(nums.get(temp[0]).get(temp[1]));
					if (i == 0) {
						if (temp[0] + 1 < nums.size() && temp[1] < nums.get(temp[0] + 1).size()) {
							queue.add(new int[] { temp[0] + 1, temp[1] });
						}
					}
					if (temp[1] + 1 < nums.get(temp[0]).size()) {
						queue.add(new int[] { temp[0], temp[1] + 1 });
					}
				}
			}
			int[] ret = new int[list.size()];
			int r = 0;
			for (int i : list) {
				ret[r++] = i;
			}
			return ret;
		}
	}
}
