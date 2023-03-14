package com.zm.LeetCodeEx.weekcontest.contest_233_20210321;

import java.util.Arrays;

/**
 * 1803. 统计异或值在范围内的数对有多少
 * <p>
 * 给你一个整数数组 nums （下标 从 0 开始 计数）以及两个整数：low 和 high ，请返回 漂亮数对 的数目。
 * <p>
 * 漂亮数对 是一个形如 (i, j) 的数对，其中 0 <= i < j < nums.length 且 low <= (nums[i] XOR nums[j]) <= high 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,4,2,7], low = 2, high = 6
 * 输出：6
 * 解释：所有漂亮数对 (i, j) 列出如下：
 * - (0, 1): nums[0] XOR nums[1] = 5
 * - (0, 2): nums[0] XOR nums[2] = 3
 * - (0, 3): nums[0] XOR nums[3] = 6
 * - (1, 2): nums[1] XOR nums[2] = 6
 * - (1, 3): nums[1] XOR nums[3] = 3
 * - (2, 3): nums[2] XOR nums[3] = 5
 * 示例 2：
 * <p>
 * 输入：nums = [9,8,4,2,1], low = 5, high = 14
 * 输出：8
 * 解释：所有漂亮数对 (i, j) 列出如下：
 * - (0, 2): nums[0] XOR nums[2] = 13
 * - (0, 3): nums[0] XOR nums[3] = 11
 * - (0, 4): nums[0] XOR nums[4] = 8
 * - (1, 2): nums[1] XOR nums[2] = 12
 * - (1, 3): nums[1] XOR nums[3] = 10
 * - (1, 4): nums[1] XOR nums[4] = 9
 * - (2, 3): nums[2] XOR nums[3] = 6
 * - (2, 4): nums[2] XOR nums[4] = 5
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 2 * 10^4
 * 1 <= nums[i] <= 2 * 10^4
 * 1 <= low <= high <= 2 * 10^4
 */
public class LEET1803 {
	public static void main(String[] args) {
		System.out.println(new Solution3().countPairs(new int[]{1, 4, 2, 7}, 2, 6));
		System.out.println(new Solution3().countPairs(new int[]{9, 8, 4, 2, 1}, 5, 14));
	}


	/**
	 * 暴力解超时
	 * O(n^2)
	 */
	static class Solution {
		public int countPairs(int[] nums, int low, int high) {
			int len = nums.length;
			int cnt = 0;
			for (int i = 0; i < len; i++) {
				for (int j = i; j < len; j++) {
					int xor = nums[i] ^ nums[j];
					if (xor >= low && xor <= high) {
						cnt++;
					}
				}
			}
			return cnt;
		}
	}

	/**
	 * 优化暴力解，先排序后对于相同的i值直接取上一次的值。
	 * O(n^2)
	 */
	static class Solution2 {
		public int countPairs(int[] nums, int low, int high) {
			int len = nums.length;
			Arrays.sort(nums);
			int cnt = 0;
			int pre = nums[0];
			int preTotal = 0;
			for (int i = 0; i < len; i++) {
				if (i == 0 || nums[i] != pre) {
					preTotal = 0;
					for (int j = i; j < len; j++) {
						int xor = nums[i] ^ nums[j];
						if (xor >= low && xor <= high) {
							preTotal++;
						}
					}
				}
				cnt += preTotal;
				pre = nums[i];
			}
			return cnt;
		}
	}

	static class Solution3 {
		public int countPairs(int[] nums, int low, int high) {
			// 获取最大值的最高二进制位
			int max = 0;
			for (int num : nums) {
				max = Math.max(max, num);
			}
			max = Math.max(max, high);
			int bitMax = -1;
			while (max > 0) {
				max = max >> 1;
				bitMax++;
			}
			// 构建字典树
			TrieTree trieTree = new TrieTree(bitMax);
			for (int num : nums) {
				trieTree.insert(num);
			}
			// 查询每一个数是否有满足条件的配对
			int result = 0;
			for (int num : nums) {
				// 其实配对中会多一个自身的配对，但是这边相减总会减掉
				System.out.println(trieTree.query(num, high) + "-" + trieTree.query(num, low - 1));
				result += trieTree.query(num, high) - trieTree.query(num, low - 1);
			}
			return result >> 1;
		}

		static class TrieNode {
			TrieNode left;
			TrieNode right;
			int cnt;
		}

		static class TrieTree {
			int bitMax;
			TrieNode root;

			public TrieTree(int bitMax) {
				this.bitMax = bitMax;
				this.root = new TrieNode();
			}

			public void insert(int num) {
				int bitMaxTemp = 1 << bitMax;
				TrieNode current = root;
				while (bitMaxTemp > 0) {
					int xor = bitMaxTemp & num;
					if (xor == 0) {
						if (current.left == null) {
							current.left = new TrieNode();
						}
						current.left.cnt++;
						current = current.left;
					}
					else {
						if (current.right == null) {
							current.right = new TrieNode();
						}
						current.right.cnt++;
						current = current.right;
					}
					bitMaxTemp = bitMaxTemp >> 1;
				}
			}

			public int query(int num, int ceiling) {
				TrieNode current = root;
				int count = 0;
				for (int bitMaxTemp = bitMax; bitMaxTemp >= 0; bitMaxTemp--) {
					if (current == null) {
						return count;
					}
					// 获取最大值的当前位
					int checkCeiling = (ceiling >> bitMaxTemp) & 1;
					// 获取查询数的当前位
					int checkNum = (num >> bitMaxTemp) & 1;
					if (checkCeiling == 0) {
						// 如果最大值当前位是0，那么查询数和树中数的当前位xor就要是0，left节点为0，right为1。
						// 如果查询数当前位是0，那只能是走左节点0，如果查询数当前为1，那么只能走右节点1。
						current = checkNum == 0 ? current.left : current.right;
					}
					else {
						// 如果最大值当前位是1，那个查询数和树中数的当前位xor是0的全部满足条件，xor必然比最大值小。
						if (checkNum != 0) {
							// 如果查询数当前位是1，那么右节点1与其xor为0，满足条件。左节点继续判断。
							count += current.right != null ? current.right.cnt : 0;
							current = current.left;
						}
						else {
							// 如果查询数当前位是0，那么左节点0与其xor为0，满足条件。右节点继续判断。
							count += current.left != null ? current.left.cnt : 0;
							current = current.right;
						}
					}
				}
				// 最后还会可能剩一个节点
				if (current != null) {
					return count + current.cnt;
				}
				return count;
			}
		}
	}
}
