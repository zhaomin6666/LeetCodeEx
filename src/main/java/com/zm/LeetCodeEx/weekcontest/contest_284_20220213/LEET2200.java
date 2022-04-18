package com.zm.LeetCodeEx.weekcontest.contest_284_20220213;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * 2200. 找出数组中的所有 K 近邻下标
 * 给你一个下标从 0 开始的整数数组 nums 和两个整数 key 和 k 。K 近邻下标 是 nums 中的一个下标 i ，并满足至少存在一个下标 j 使得 |i - j| <= k 且 nums[j] == key 。
 * <p>
 * 以列表形式返回按 递增顺序 排序的所有 K 近邻下标。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [3,4,9,1,3,9,5], key = 9, k = 1
 * 输出：[1,2,3,4,5,6]
 * 解释：因此，nums[2] == key 且 nums[5] == key 。
 * - 对下标 0 ，|0 - 2| > k 且 |0 - 5| > k ，所以不存在 j 使得 |0 - j| <= k 且 nums[j] == key 。所以 0 不是一个 K 近邻下标。
 * - 对下标 1 ，|1 - 2| <= k 且 nums[2] == key ，所以 1 是一个 K 近邻下标。
 * - 对下标 2 ，|2 - 2| <= k 且 nums[2] == key ，所以 2 是一个 K 近邻下标。
 * - 对下标 3 ，|3 - 2| <= k 且 nums[2] == key ，所以 3 是一个 K 近邻下标。
 * - 对下标 4 ，|4 - 5| <= k 且 nums[5] == key ，所以 4 是一个 K 近邻下标。
 * - 对下标 5 ，|5 - 5| <= k 且 nums[5] == key ，所以 5 是一个 K 近邻下标。
 * - 对下标 6 ，|6 - 5| <= k 且 nums[5] == key ，所以 6 是一个 K 近邻下标。
 * 因此，按递增顺序返回 [1,2,3,4,5,6] 。
 * 示例 2：
 * <p>
 * 输入：nums = [2,2,2,2,2], key = 2, k = 2
 * 输出：[0,1,2,3,4]
 * 解释：对 nums 的所有下标 i ，总存在某个下标 j 使得 |i - j| <= k 且 nums[j] == key ，所以每个下标都是一个 K 近邻下标。
 * 因此，返回 [0,1,2,3,4] 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 1000
 * 1 <= nums[i] <= 1000
 * key 是数组 nums 中的一个整数
 * 1 <= k <= nums.length
 */
public class LEET2200 {
	public static void main(String[] args) {
		System.out.println(JSON.toJSONString(new Solution().findKDistantIndices(new int[]{3, 4, 9, 1, 3, 9, 5}, 9, 1)));
		System.out.println(JSON.toJSONString(new Solution().findKDistantIndices(new int[]{2, 2, 2, 2, 2}, 2, 2)));
		System.out.println(JSON.toJSONString(new Solution().findKDistantIndices(new int[]{1}, 1, 1)));
		System.out.println(JSON.toJSONString(new Solution().findKDistantIndices(new int[]{734, 228, 636, 204, 552, 732, 686, 461, 973, 874, 90, 537, 939, 986, 855, 387, 344, 939, 552, 389, 116, 93, 545, 805, 572, 306, 157, 899, 276, 479, 337, 219, 936, 416, 457, 612, 795, 221, 51, 363, 667, 112, 686, 21, 416, 264, 942, 2, 127, 47, 151, 277, 603, 842, 586, 630, 508, 147, 866, 434, 973, 216, 656, 413, 504, 360, 990, 228, 22, 368, 660, 945, 99, 685, 28, 725, 673, 545, 918, 733, 158, 254, 207, 742, 705, 432, 771, 578, 549, 228, 766, 998, 782, 757, 561, 444, 426, 625, 706, 946},
				939,
				34)));
	}

	/**
	 * 直接遍历找Key，再把Key对应下标+-k的数加入返回列表
	 */
	static class Solution {
		public List<Integer> findKDistantIndices(int[] nums, int key, int k) {
			List<Integer> result = new ArrayList<>();
			int max = -1;
			int n = nums.length;
			for (int i = 0; i < n; i++) {
				if (nums[i] == key) {
					for (int j = Math.max(i - k, max + 1); j <= Math.min(i + k, n - 1); j++) {
						result.add(j);
						max = j;
					}
				}
			}
			return result;
		}
	}
}
