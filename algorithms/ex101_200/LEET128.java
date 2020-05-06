package com.zm.LeetCodeEx.algorithms.ex101_200;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * 128. 最长连续序列
 * <p>
 * 给定一个未排序的整数数组，找出最长连续序列的长度。
 * <p>
 * 要求算法的时间复杂度为 O(n)。
 * <p>
 * 示例:
 * <p>
 * 输入: [100, 4, 200, 1, 3, 2] <br>
 * 输出: 4 <br>
 * 解释: 最长连续序列是 [1, 2, 3, 4]。它的长度为 4。
 * 
 * @author zm
 *
 */
public class LEET128 {
	public static void main(String[] args) {
		LEET128 l128 = new LEET128();
		System.out.println(l128.new Solution().longestConsecutive(new int[] { 100, 4, 200, 1, 3, 2 }));
		System.out.println(l128.new Solution().longestConsecutive(new int[] { 1, 3, 5, 7, 9, 11, 4, 8, 10 }));
		System.out.println(l128.new Solution().longestConsecutive(new int[] {}));
		System.out.println(l128.new Solution().longestConsecutive(new int[] { 1, 2, 0, 1 }));
	}

	class Solution {
		int[] unionFindArray;
		int[] unionFindWeight;
		int length;

		public int longestConsecutive(int[] nums) {
			HashMap<Integer, Integer> intToIndex = new HashMap<>();
			length = nums.length;
			unionFindArray = new int[length];
			for (int i = 0; i < length; i++) {
				unionFindArray[i] = i;
			}
			unionFindWeight = new int[length];
			Arrays.fill(unionFindWeight, 1);
			for (int i = 0; i < length; i++) {
				int cur = nums[i];
				if (!intToIndex.containsKey(cur)) {
					if (intToIndex.containsKey(cur - 1)) {
						union(i, intToIndex.get(cur - 1));
					}
					if (intToIndex.containsKey(cur + 1)) {
						union(i, intToIndex.get(cur + 1));
					}
					intToIndex.put(cur, i);
				}
			}
			// System.out.println(Arrays.toString(unionFindWeight));
			// System.out.println(Arrays.toString(unionFindArray));
			int max = 0;
			for (int i = 0; i < unionFindWeight.length; i++) {
				max = Math.max(max, unionFindWeight[i]);
			}
			return max;
		}

		private void union(int index1, int index2) {
			int root1 = getRoot(index1);
			int root2 = getRoot(index2);
			if (root1 == root2) {
				return;
			}
			if (unionFindWeight[root1] > unionFindWeight[root2]) {
				unionFindArray[root2] = root1;
				unionFindWeight[root1] += unionFindWeight[root2];
			} else {
				unionFindArray[root1] = root2;
				unionFindWeight[root2] += unionFindWeight[root1];
			}
		}

		private int getRoot(int index) {
			while (unionFindArray[index] != index) {
				index = unionFindArray[index];
			}
			return index;
		}
	}

	/**
	 * 官方题解，优化的暴力解法 <br>
	 * 原暴力解法：遍历每个数字看下一个数字是否在数组中 <br>
	 * 现在 <br>
	 * 1.用HashSet来使判断某一数字是否在数组中变为O(1) <br>
	 * 2.只有当前数字的前一个数字不在数组中时，才开始把这个数字作为序列的开头开始一个个往下找。 <br>
	 * 因为如果当前数字的上一个数字在数组中，那么在遍历到上一个数字的时候往后一个个查找也会找到当前数字。
	 * 
	 * @author zm
	 *
	 */
	class Solution2 {
		public int longestConsecutive(int[] nums) {
			Set<Integer> num_set = new HashSet<Integer>();
			for (int num : nums) {
				num_set.add(num);
			}
			int longestStreak = 0;
			for (int num : num_set) {
				if (!num_set.contains(num - 1)) {
					int currentNum = num;
					int currentStreak = 1;
					// 这里在for循环中嵌套while，看似是O(n^2)，但是for循环中的if条件和while中的条件是互补的
					// 如果while这个的当前数字的下一个数字在数组中，那么for循环到下一个数字时不会执行。
					// O(n+n) = O(n)
					while (num_set.contains(currentNum + 1)) {
						currentNum += 1;
						currentStreak += 1;
					}
					longestStreak = Math.max(longestStreak, currentStreak);
				}
			}
			return longestStreak;
		}
	}

}
