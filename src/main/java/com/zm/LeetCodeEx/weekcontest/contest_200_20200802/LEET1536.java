package com.zm.LeetCodeEx.weekcontest.contest_200_20200802;

import java.util.Arrays;

public class LEET1536 {

	public static void main(String[] args) {
		System.out.println(new Solution2().minSwaps(new int[][]{{0, 0, 1}, {1, 1, 0}, {1, 0, 0}}));
	}

	/**
	 * 从需要的最多0的行数开始找，找到就依次交换将需要的行移到对应位置，并且更新其他行的最新位置
	 */
	static class Solution {
		public int minSwaps(int[][] grid) {
			// rowIndex记录原行的当前位置
			int[] rowIndex = new int[grid.length];
			for (int i = 0; i < grid.length; i++) {
				rowIndex[i] = i;
			}
			int ret = 0;
			for (int i = 0; i < grid.length - 1; i++) {
				// 需要找一行后面有 grid.length - i - 1 个0
				boolean findflag = false;
				for (int j = 0; j < grid.length; j++) {
					if (rowIndex[j] >= i) {
						boolean flag = true;
						for (int k = i + 1; k < grid.length; k++) {
							if (grid[j][k] != 0) {
								flag = false;
								break;
							}
						}
						if (flag) {
							if (rowIndex[j] != i) {
								ret += rowIndex[j] - i;
								// 更新其他行的最新行数：
								// 如4要移到1的位置，那么需要34交换变成1243，24交换变成1423，14交换变成4123，一共3步。
								for (int k = 0; k < grid.length; k++) {
									if (rowIndex[k] >= i && rowIndex[k] < rowIndex[j]) {
										rowIndex[k]++;
									}
								}
								rowIndex[j] = i;
							}
							findflag = true;
							break;
						}
					}
				}
				if (!findflag) {
					return -1;
				}
			}
			return ret;
		}
	}

	/**
	 * 对于寻找拥有末尾n个0的行，可以进行预处理pos[]，不用每次都去计数
	 * 不再记录rowIndex，直接交换pos[]中的数值
	 */
	static class Solution2 {
		public int minSwaps(int[][] grid) {
			int n = grid.length;
			// 预处理数据
			int[] pos = new int[n];
			Arrays.fill(pos, -1);
			for (int i = 0; i < n; ++i) {
				for (int j = n - 1; j >= 0; --j) {
					if (grid[i][j] == 1) {
						pos[i] = j;
						break;
					}
				}
			}

			int ret = 0;
			for (int i = 0; i < n - 1; i++) {
				// 需要从后面行中找一行后面有 grid.length - i - 1 个0
				boolean findflag = false;
				for (int j = i; j < n; j++) {
					if (pos[j] <= i) {
						// 如4要移到1的位置，那么需要34交换变成1243，24交换变成1423，14交换变成4123，一共3步。
						ret += j - i;
						// 依次执行交换34,24,14
						for (int k = j; k > i; --k) {
							int temp = pos[k];
							pos[k] = pos[k - 1];
							pos[k - 1] = temp;
						}
						findflag = true;
						break;
					}
				}
				if (!findflag) {
					return -1;
				}
			}
			return ret;
		}
	}
}
