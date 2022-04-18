package com.zm.LeetCodeEx.weekcontest.contest_284_20220213;

/**
 * 2202. K 次操作后最大化顶端元素
 * 给你一个下标从 0 开始的整数数组 nums ，它表示一个 栈 ，其中 nums[0] 是栈顶的元素。
 * <p>
 * 每一次操作中，你可以执行以下操作 之一 ：
 * <p>
 * 如果栈非空，那么 删除 栈顶端的元素。
 * 如果存在 1 个或者多个被删除的元素，你可以从它们中选择任何一个，添加 回栈顶，这个元素成为新的栈顶元素。
 * 同时给你一个整数 k ，它表示你总共需要执行操作的次数。
 * <p>
 * 请你返回 恰好 执行 k 次操作以后，栈顶元素的 最大值 。如果执行完 k 次操作以后，栈一定为空，请你返回 -1 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [5,2,2,4,0,6], k = 4
 * 输出：5
 * 解释：
 * 4 次操作后，栈顶元素为 5 的方法之一为：
 * - 第 1 次操作：删除栈顶元素 5 ，栈变为 [2,2,4,0,6] 。
 * - 第 2 次操作：删除栈顶元素 2 ，栈变为 [2,4,0,6] 。
 * - 第 3 次操作：删除栈顶元素 2 ，栈变为 [4,0,6] 。
 * - 第 4 次操作：将 5 添加回栈顶，栈变为 [5,4,0,6] 。
 * 注意，这不是最后栈顶元素为 5 的唯一方式。但可以证明，4 次操作以后 5 是能得到的最大栈顶元素。
 * 示例 2：
 * <p>
 * 输入：nums = [2], k = 1
 * 输出：-1
 * 解释：
 * 第 1 次操作中，我们唯一的选择是将栈顶元素弹出栈。
 * 由于 1 次操作后无法得到一个非空的栈，所以我们返回 -1 。
 * 提示：
 * <p>
 * 1 <= nums.length <= 105
 * 0 <= nums[i], k <= 109
 */
public class LEET2202 {
	public static void main(String[] args) {
		System.out.println(
				new Solution().maximumTop(new int[]{5, 2, 2, 6, 5, 7}, 4));
		System.out.println(
				new Solution().maximumTop(new int[]{5, 2, 2, 4, 0, 6}, 4));
		System.out.println(
				new Solution().maximumTop(new int[]{2}, 1));
		System.out.println(
				new Solution().maximumTop(new int[]{2}, 2));
		System.out.println(
				new Solution().maximumTop(new int[]{2}, 3));
		System.out.println(
				new Solution().maximumTop(new int[]{2, 3}, 2));
		System.out.println(
				new Solution().maximumTop(new int[]{2, 3}, 3));
		System.out.println(
				new Solution().maximumTop(new int[]{2, 3}, 4));
	}

	/**
	 * n=1: 操作被限制在一个数的存取
	 * n>1,k>n: 操作次数大于n，数组中所有数字都可以在最后一次操作中选择被放回
	 * n>1,k<=n: 只有k-1位置上的数不能被放回
	 */
	static class Solution {
		public int maximumTop(int[] nums, int k) {
			int n = nums.length;
			if (k <= n) {
				int max = -1;
				for (int i = 0; i <= k && i < n; i++) {
					if (i != k - 1) {
						max = Math.max(max, nums[i]);
					}
				}
				return max;
			}
			if (n == 1) {
				return k % 2 == 0 ? nums[0] : -1;
			}
			int max = -1;
			for (int num : nums) {
				max = Math.max(max, num);
			}
			return max;
		}
	}

	/**
	 * 合并上面方法中的二三两种情况
	 * n=1: 操作被限制在一个数的存取
	 * n>1: 只有k-1位置上的数不能被放回
	 */
	static class Solution2 {
		public int maximumTop(int[] nums, int k) {
			int n = nums.length;
			if (n == 1) {
				return k % 2 == 0 ? nums[0] : -1;
			}
			else {
				int max = -1;
				for (int i = 0; i <= Math.min(k, n - 1); i++) {
					if (i != k - 1) {
						max = Math.max(max, nums[i]);
					}
				}
				return max;
			}
		}
	}
}
