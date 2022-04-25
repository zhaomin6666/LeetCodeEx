package com.zm.LeetCodeEx.algorithms.ex301_400;

import java.util.*;

/**
 * 398. 随机数索引
 * 给定一个可能含有重复元素的整数数组，要求随机输出给定的数字的索引。 您可以假设给定的数字一定存在于数组中。
 * <p>
 * 注意：
 * 数组大小可能非常大。 使用太多额外空间的解决方案将不会通过测试。
 * <p>
 * 示例:
 * <p>
 * int[] nums = new int[] {1,2,3,3,3};
 * Solution solution = new Solution(nums);
 * <p>
 * // pick(3) 应该返回索引 2,3 或者 4。每个索引的返回概率应该相等。
 * solution.pick(3);
 * <p>
 * // pick(1) 应该返回 0。因为只有nums[0]等于1。
 * solution.pick(1);
 *
 * @author zm
 */
public class LEET398 {
	public static void main(String[] args) {
		Solution solution = new Solution(new int[]{1, 2, 3, 3, 3});
		System.out.println(solution.pick(3));
		System.out.println(solution.pick(3));
		System.out.println(solution.pick(3));
		System.out.println(solution.pick(1));
	}

	/**
	 * 直接使用哈希表+列表
	 */
	static class Solution {
		Map<Integer, List<Integer>> pos;
		Random random;

		public Solution(int[] nums) {
			pos = new HashMap<>();
			random = new Random();
			for (int i = 0; i < nums.length; ++i) {
				pos.putIfAbsent(nums[i], new ArrayList<>());
				pos.get(nums[i]).add(i);
			}
		}

		public int pick(int target) {
			List<Integer> indices = pos.get(target);
			return indices.get(random.nextInt(indices.size()));
		}
	}

	/**
	 * 水塘抽样
	 * 能够保证每一个被抽到的概率相等
	 * 第一个被遍历到的时候抽到的概率是1/1
	 * 第二个被遍历到的时候抽到的概率是1/2，此时第一个的概率变成1*(1-1/2)=1/2
	 * 第三个被遍历到的时候抽到的概率是1/3，此时第二个的概率变成1/2*(1-1/3)=1/3，第一个的概率变成1*(1-1/2)*(1-1/3)=1/3
	 */
	static class Solution2 {
		int[] nums;
		Random random;

		public Solution2(int[] nums) {
			this.nums = nums;
			random = new Random();
		}

		public int pick(int target) {
			int ans = 0;
			for (int i = 0, cnt = 0; i < nums.length; ++i) {
				if (nums[i] == target) {
					++cnt; // 第 cnt 次遇到 target
					if (random.nextInt(cnt) == 0) {
						ans = i;
					}
				}
			}
			return ans;
		}
	}
}
