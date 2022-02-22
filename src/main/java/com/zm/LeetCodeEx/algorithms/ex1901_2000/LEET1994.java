package com.zm.LeetCodeEx.algorithms.ex1901_2000;

import com.alibaba.fastjson.JSON;

/**
 * 1994. 好子集的数目
 * 给你一个整数数组 nums 。如果 nums 的一个子集中，所有元素的乘积可以表示为一个或多个 互不相同的质数 的乘积，那么我们称它为 好子集 。
 * <p>
 * 比方说，如果 nums = [1, 2, 3, 4] ：
 * [2, 3] ，[1, 2, 3] 和 [1, 3] 是 好 子集，乘积分别为 6 = 2*3 ，6 = 2*3 和 3 = 3 。
 * [1, 4] 和 [4] 不是 好 子集，因为乘积分别为 4 = 2*2 和 4 = 2*2 。
 * 请你返回 nums 中不同的 好 子集的数目对 109 + 7 取余 的结果。
 * <p>
 * nums 中的 子集 是通过删除 nums 中一些（可能一个都不删除，也可能全部都删除）元素后剩余元素组成的数组。如果两个子集删除的下标不同，那么它们被视为不同的子集。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3,4]
 * 输出：6
 * 解释：好子集为：
 * - [1,2]：乘积为 2 ，可以表示为质数 2 的乘积。
 * - [1,2,3]：乘积为 6 ，可以表示为互不相同的质数 2 和 3 的乘积。
 * - [1,3]：乘积为 3 ，可以表示为质数 3 的乘积。
 * - [2]：乘积为 2 ，可以表示为质数 2 的乘积。
 * - [2,3]：乘积为 6 ，可以表示为互不相同的质数 2 和 3 的乘积。
 * - [3]：乘积为 3 ，可以表示为质数 3 的乘积。
 * 示例 2：
 * <p>
 * 输入：nums = [4,2,3,15]
 * 输出：5
 * 解释：好子集为：
 * - [2]：乘积为 2 ，可以表示为质数 2 的乘积。
 * - [2,3]：乘积为 6 ，可以表示为互不相同质数 2 和 3 的乘积。
 * - [2,15]：乘积为 30 ，可以表示为互不相同质数 2，3 和 5 的乘积。
 * - [3]：乘积为 3 ，可以表示为质数 3 的乘积。
 * - [15]：乘积为 15 ，可以表示为互不相同质数 3 和 5 的乘积。
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 30
 *
 * @author zm
 */
public class LEET1994 {
	public static void main(String[] args) {
		System.out.println(JSON.toJSONString(new Solution().numberOfGoodSubsets(new int[]{1, 2, 3, 4})));
		System.out.println(JSON.toJSONString(new Solution().numberOfGoodSubsets(new int[]{4, 2, 3, 15})));
	}

	/**
	 * 由于30以内的质数只有10个，所以用一个mask来记录这10个质数的使用情况，比如nums子集是2,15，那么mask就为1'0000000111
	 * dp[mask]标识当前mask情况下的方案数
	 */
	static class Solution {
		public int numberOfGoodSubsets(int[] nums) {
			int MOD = (int) 1e9 + 7;
			int[] primeNums = new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29};
			int[] cntNum = new int[31];
			int n = nums.length;
			// 统计数量
			for (int num : nums) {
				cntNum[num]++;
			}
			// 初始化dp
			int mask = 1 << 10;
			long[] dp = new long[mask];
			dp[0] = 1;
			for (int i = 2; i <= 30; i++) {
				if (cntNum[i] == 0) {
					continue;
				}
				// 对i进行试除，当前mask保存在cur
				int cur = 0;
				// x用来试除，不修改i值
				int x = i;
				// 如果是4,8,9这种本身就带有两种质数就直接不加入
				boolean isValidNum = true;
				for (int j = 0; j < 10; j++) {
					int primeNum = primeNums[j];
					int cntPrime = 0;
					while (x % primeNum == 0) {
						// 更新mask
						cur |= (1 << j);
						x /= primeNum;
						cntPrime++;
					}
					// 如果 i 能够被同一质数试除多次，说明 i 不能加到子集，跳过
					if (cntPrime > 1) {
						isValidNum = false;
						break;
					}
				}
				if (!isValidNum) {
					continue;
				}
				// 枚举前一个状态 prev
				//（确保考虑一个新数值 i 时，依赖的子集 prev 存储的为尚未考虑 i 的方案数）
				for (int prev = mask - 1; prev >= 0; prev--) {
					// 只有当前选择数与前一个状态不冲突，则能够进行转移，将方案数进行累加
					// 如prev的num为2，cur的num为15(3,5)即prev=1,cur=110,prev&cur=0
					// 如prev的num为2，cur的num为10(2,5)即prev=1,cur=101,prev&cur=1
					if ((prev & cur) != 0) {
						continue;
					}
					// dp[prev | cur] 表示在dp[prev]状态的基础上加上dp[cur]状态依然没有重复的质数
					// 如prev的num为2，cur的num为15(3,5)即prev=1,cur=110,prev|cur=111
					dp[prev | cur] = (dp[prev | cur] + dp[prev] * cntNum[i]) % MOD;
				}
			}
			long ans = 0;
			// 统计所有非空集的方案数
			for (int i = 1; i < mask; i++) {
				ans = (ans + dp[i]) % MOD;
			}
			// 在此基础上，考虑每个1选择与否对答案的影响
			for (int i = 0; i < cntNum[1]; i++) {
				ans = ans * 2 % MOD;
			}
			return (int) ans;
		}
	}
}
