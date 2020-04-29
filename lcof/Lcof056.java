package com.zm.LeetCodeEx.lcof;

import java.util.HashSet;

import com.alibaba.fastjson.JSON;

/**
 * 面试题56 - I. 数组中数字出现的次数
 * <p>
 * 一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。
 * <p>
 * 请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。
 * <p>
 * 示例 1：<br>
 * 输入：nums = [4,1,4,6]<br>
 * 输出：[1,6] 或 [6,1]
 * <p>
 * 示例 2：
 * <p>
 * 输入：nums = [1,2,10,4,1,4,3,3]<br>
 * 输出：[2,10] 或 [10,2]
 * <p>
 * 限制：<br>
 * 2 <= nums <= 10000
 * 
 * @author zm
 *
 */
public class Lcof056 {
	public static void main(String[] args) {
		Lcof056 l057 = new Lcof056();
		System.out.println(JSON.toJSONString(l057.new Solution().singleNumbers(new int[] { 4, 1, 4, 6 })));
		System.out.println(JSON.toJSONString(l057.new Solution().singleNumbers(new int[] { 1, 2, 10, 4, 1, 4, 3, 3 })));
	}

	class Solution {
		public int[] singleNumbers(int[] nums) {
			HashSet<Integer> set = new HashSet<>();
			for (int i = 0; i < nums.length; i++) {
				int t = nums[i];
				if (set.contains(t)) {
					set.remove(t);
				} else {
					set.add(t);
				}
			}
			int[] ret = new int[2];
			int i = 0;
			for (Integer integer : set) {
				ret[i++] = integer;
			}
			return ret;
		}
	}

	/**
	 * 使用异或 因为 a^a=0,a^b^a=b 当一个数组中只有一个数只出现一次时直接全部异或即可，但是这里有两个。 <br>
	 * 所以需要把数组拆分为两个数组，满足： 1、两个只出现一次的数分别在两个数组； <br>
	 * 2、相同的数在同一个数组。
	 * 
	 * 把这个包含一个a和一个b的数组全部异或可以得到a^b。 <br>
	 * 因为a,b不同，所以a^b必定有二进制位数为1的位置。 <br>
	 * 比如a^b中第4位为1，那么就把所有第4位为1的数分为一组，所有第4位为0的数分为一组。<br>
	 * 这样便可以满足两个条件。
	 * 
	 * @author zm
	 *
	 */
	class Solution2 {
		public int[] singleNumbers(int[] nums) {
			int allYH = 0;
			for (int i = 0; i < nums.length; i++) {
				allYH ^= nums[i];
			}
			int div = 1;
			while ((allYH & div) == 0) {
				div <<= 1;
			}
			int a = 0;
			int b = 0;
			for (int i : nums) {
				if ((i & div) == 0) {
					a ^= i;
				} else {
					b ^= i;
				}
			}
			return new int[] { a, b };
		}
	}
}
