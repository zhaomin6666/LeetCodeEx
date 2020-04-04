package com.zm.LeetCodeEx.weekcontest;

import java.util.Arrays;

/**
 * 双周赛 2020年4月4日
 * <p>
 * 5363. 做菜顺序
 * <p>
 * 一个厨师收集了他 n 道菜的满意程度 satisfaction ，这个厨师做出每道菜的时间都是 1 单位时间。
 * <p>
 * 一道菜的 「喜爱时间」系数定义为烹饪这道菜以及之前每道菜所花费的时间乘以这道菜的满意程度，也就是 time[i]*satisfaction[i] 。
 * <p>
 * 请你返回做完所有菜 「喜爱时间」总和的最大值为多少。
 * <p>
 * 你可以按 任意 顺序安排做菜的顺序，你也可以选择放弃做某些菜来获得更大的总和。
 * <p>
 * 示例 1：
 * <p>
 * 输入：satisfaction = [-1,-8,0,5,-9]<br>
 * 输出：14<br>
 * 解释：去掉第二道和最后一道菜，最大的喜爱时间系数和为 (-1*1 + 0*2 + 5*3 = 14) 。每道菜都需要花费 1 单位时间完成。
 * <p>
 * 示例 2：
 * <p>
 * 输入：satisfaction = [4,3,2]<br>
 * 输出：20<br>
 * 解释：按照原来顺序相反的时间做菜 (2*1 + 3*2 + 4*3 = 20)
 * <p>
 * 示例 3：
 * <p>
 * 输入：satisfaction = [-1,-4,-5]<br>
 * 输出：0<br>
 * 解释：大家都不喜欢这些菜，所以不做任何菜可以获得最大的喜爱时间系数。
 * <p>
 * 示例 4：
 * <p>
 * 输入：satisfaction = [-2,5,-1,0,3,-3]<br>
 * 输出：35
 * <p>
 * 提示：
 * <p>
 * n == satisfaction.length <br>
 * 1 <= n <= 500 <br>
 * -10^3 <= satisfaction[i] <= 10^3
 *
 * @author zm
 */
public class LEET5363 {
	public static void main(String[] args) {
		LEET5363 l5361 = new LEET5363();
		System.out.println(l5361.new Solution2().maxSatisfaction(new int[] { -1, -8, 0, 5, -9 }));
		System.out.println(l5361.new Solution2().maxSatisfaction(new int[] { 4, 3, 2 }));
		System.out.println(l5361.new Solution2().maxSatisfaction(new int[] { -1, -4, -5 }));
		System.out.println(l5361.new Solution2().maxSatisfaction(new int[] { -2, 5, -1, 0, 3, -3 }));
	}

	/**
	 * 先排序，然后从第一个非正数开始往后一个开始计算喜爱程度。 不断加上一个负数使得后面的正数*时间越来越大，直到ret不再变大为止。
	 * 因为每一次向前移动一个（加上一个负数），就是让ret+这个负数右边的所有数+这个负数，
	 * 如果这个负数右边的所有数+这个负数小于0，那么后面的ret只会越来越小，而负数的绝对值是越来越大。 O(n^2)
	 * 
	 * @author zm
	 *
	 */
	class Solution {
		public int maxSatisfaction(int[] satisfaction) {
			Arrays.sort(satisfaction);
			if (satisfaction[satisfaction.length - 1] <= 0) {
				return 0;
			}
			int ret = 0;
			if (satisfaction[0] >= 0) {
				for (int i = 0; i < satisfaction.length; i++) {
					ret += satisfaction[i] * (i + 1);
				}
				return ret;
			}

			for (int i = satisfaction.length - 1; i >= 0; i--) {
				if (satisfaction[i] >= 0) {
					continue;
				}
				int temp = 0;
				for (int j = i + 1; j < satisfaction.length; j++) {
					temp += satisfaction[j] * (j - i);
				}
				if (temp >= ret) {
					ret = temp;
				} else {
					return ret;
				}
			}
			int temp = 0;
			for (int i = 0; i < satisfaction.length; i++) {
				temp += satisfaction[i] * (i + 1);
			}
			ret = Math.max(temp, ret);
			return ret;
		}
	}

	/**
	 * 受方法一启发，可以发现每增加一个负数都是在原来ret的基础上+cur[i]+cur[i+1]+...+cur[n] <br>
	 * 可以用一个数组记录下值，防止重复计算
	 * 
	 * @author zm
	 *
	 */
	class Solution2 {
		public int maxSatisfaction(int[] satisfaction) {
			Arrays.sort(satisfaction);
			// 如果全是负数直接返回0
			if (satisfaction[satisfaction.length - 1] <= 0) {
				return 0;
			}
			int ret = 0;
			// 如果全是正数就直接全部计算
			if (satisfaction[0] >= 0) {
				for (int i = 0; i < satisfaction.length; i++) {
					ret += satisfaction[i] * (i + 1);
				}
				return ret;
			}
			// 用两个数组记录下每次计算出的值防止重复计算
			// 如1,2,3,4,5
			// 5:第一次循环就是5
			// 4:需要加上新增的4和原来就有的5,以及因为向后推了一个时间单位导致增加的5 <br>
			// 4+(5)+(5)=(5)+(4+5)
			// 3:需要加上新增的3和原来就有的4+5+5,以及因为向后推了一个时间单位导致增加的4,5 <br>
			// 3+(4+5+5)+(4+5)=(4+5+5)+(3+4+5)
			// 2:需要加上新增的2和原来就有的3+4+5+5+4+5,以及因为向后推了一个时间单位导致增加的3,4,5 <br>
			// 2+(3+4+5+5+4+5)+(3+4+5)=(3+4+5+5+4+5)+(2+3+4+5)
			// 第一个括号内为dp2,第二个括号和新增上的那个数组成为dp1
			// dp1就是数组从后向前的累加，dp2就是dp1从后向前的累加
			int[] dp = new int[satisfaction.length];
			int[] dp2 = new int[satisfaction.length];
			dp[satisfaction.length - 1] = satisfaction[satisfaction.length - 1];
			dp2[satisfaction.length - 1] = satisfaction[satisfaction.length - 1];
			ret = satisfaction[satisfaction.length - 1];
			for (int i = satisfaction.length - 1; i >= 0; i--) {
				if (i != satisfaction.length - 1) {
					dp[i] = dp[i + 1] + satisfaction[i];
					dp2[i] = dp2[i + 1] + dp[i];
					if (dp2[i] < ret) {
						return ret;
					} else {
						ret = dp2[i];
					}
				}
			}
			return dp2[0]; // 走到最后的话dp2[0]和ret是一样的，任意返回一个
		}
	}
	
	/**
	 * 优化方法2
	 * @author zm
	 *
	 */
	class Solution3 {
		public int maxSatisfaction(int[] satisfaction) {
			Arrays.sort(satisfaction);
			// 如果全是负数直接返回0
			if (satisfaction[satisfaction.length - 1] <= 0) {
				return 0;
			}
			int ret = 0;
			// 如果全是正数就直接全部计算
			if (satisfaction[0] >= 0) {
				for (int i = 0; i < satisfaction.length; i++) {
					ret += satisfaction[i] * (i + 1);
				}
				return ret;
			}
			// 用两个数代替数组
			int sum = 0;
			for (int i = satisfaction.length - 1; i >= 0; i--) {
				sum += satisfaction[i];
				if (sum < 0) {
					break;
				}
				ret += sum;
			}
			return ret;
		}
	}
}
