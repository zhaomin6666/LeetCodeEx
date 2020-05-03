package com.zm.LeetCodeEx.weekcontest.contest_187_20200503;

/**
 * 周赛 2020年5月3日
 * <p>
 * 5403. 有序矩阵中的第 k 个最小数组和
 * <p>
 * 给你一个 m * n 的矩阵 mat，以及一个整数 k ，矩阵中的每一行都以非递减的顺序排列。
 * <p>
 * 你可以从每一行中选出 1 个元素形成一个数组。返回所有可能数组中的第 k 个 最小 数组和。
 * <p>
 * 示例 1：
 * <p>
 * 输入：mat = [[1,3,11],[2,4,6]], k = 5 <br>
 * 输出：7 <br>
 * 解释：从每一行中选出一个元素，前 k 个和最小的数组分别是： <br>
 * [1,2], [1,4], [3,2], [3,4], [1,6]。其中第 5 个的和是 7 。
 * <p>
 * 示例 2：
 * <p>
 * 输入：mat = [[1,3,11],[2,4,6]], k = 9 <br>
 * 输出：17
 * <p>
 * 示例 3：
 * <p>
 * 输入：mat = [[1,10,10],[1,4,5],[2,3,6]], k = 7 <br>
 * 输出：9 <br>
 * 解释：从每一行中选出一个元素，前 k 个和最小的数组分别是： <br>
 * [1,1,2], [1,1,3], [1,4,2], [1,4,3], [1,1,6], [1,5,2], [1,5,3]。其中第 7 个的和是 9 。
 * <p>
 * 示例 4：
 * <p>
 * 输入：mat = [[1,1,10],[2,2,9]], k = 7 <br>
 * 输出：12
 * <p>
 * 提示：
 * <p>
 * m == mat.length <br>
 * n == mat.length[i] <br>
 * 1 <= m, n <= 40 <br>
 * 1 <= k <= min(200, n ^ m) <br>
 * 1 <= mat[i][j] <= 5000 <br>
 * mat[i] 是一个非递减数组 <br>
 *
 * @author zm
 */
public class LEET5403 {
	public static void main(String[] args) {
		LEET5403 l5403 = new LEET5403();
		System.out.println(l5403.new Solution().kthSmallest(new int[][] { { 1, 3, 11 }, { 2, 4, 6 } }, 5));
		System.out.println(l5403.new Solution().kthSmallest(new int[][] { { 1, 3, 11 }, { 2, 4, 6 } }, 9));
		System.out
				.println(l5403.new Solution().kthSmallest(new int[][] { { 1, 10, 10 }, { 1, 4, 5 }, { 2, 3, 6 } }, 7));
		System.out.println(l5403.new Solution().kthSmallest(new int[][] { { 1, 1, 10 }, { 2, 2, 9 } }, 7));
	}

	class Solution {
		public int kthSmallest(int[][] mat, int k) {
			return 0;
		}
	}
}
