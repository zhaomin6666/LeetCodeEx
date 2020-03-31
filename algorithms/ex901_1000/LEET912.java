package com.zm.LeetCodeEx.algorithms.ex901_1000;

import java.util.Arrays;

/**
 * 912. 排序数组
 * <p>
 * 给你一个整数数组 nums，将该数组升序排列。
 * 
 * 示例 1：<br>
 * 输入：nums = [5,2,3,1]<br>
 * 输出：[1,2,3,5]
 * <p>
 * 示例 2：<br>
 * 输入：nums = [5,1,1,2,0,0]<br>
 * 输出：[0,0,1,1,2,5]<br>
 *  
 * 
 * 提示：<br>
 * 
 * 1 <= nums.length <= 50000 <br>
 * -50000 <= nums[i] <= 50000 <br>
 * 
 * 
 * 
 * @author zm
 */
public class LEET912 {
	public static void main(String[] args) {
		LEET912 l912 = new LEET912();
		System.out.println(Arrays.toString(l912.new Solution().sortArray(new int[] { 5,2,3,1 })));
		System.out.println(Arrays.toString(l912.new Solution().sortArray(new int[] { 5,1,1,2,0,0 })));
	}

	/**
	 * 要学习基础还需花里胡哨，这里先用基本库解决。后面慢慢写
	 * @author zm
	 *
	 */
	class Solution {
	    public int[] sortArray(int[] nums) {
	    	Arrays.sort(nums);
	        return nums;
	    }
	}
}
