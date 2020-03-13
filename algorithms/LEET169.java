package com.zm.LeetCodeEx.algorithms;

import java.util.HashMap;
import java.util.Random;

/**
 * 169. 多数元素
 * <p>
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 * <p>
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 * <p>
 * 示例 1:<br>
 * 输入: [3,2,3]<br>
 * 输出: 3
 * <p>
 * 示例 2:<br>
 * 输入: [2,2,1,1,1,2,2]<br>
 * 输出: 2
 * 
 * @author zm
 */
public class LEET169 {
	public static void main(String[] args) {
		LEET169 l169 = new LEET169();
		System.out.println(l169.new Solution().majorityElement(new int[] { 3, 2, 3 }));
		System.out.println(l169.new Solution().majorityElement(new int[] { 2, 2, 1, 1, 1, 2, 2 }));
	}

	class Solution {
		public int majorityElement(int[] nums) {
			HashMap<Integer, Integer> map = new HashMap<>();
			int L = nums.length;
			int K = (L + 2) >> 1;
			for (int i = 0; i < nums.length; i++) {
				int cnt = map.getOrDefault(nums[i], 0) + 1;
				if (cnt >= K) {
					return nums[i];
				}
				map.put(nums[i], cnt);
			}
			return -1;
		}
	}
	
	class Solution2 {
	    private int countOccurences(int[] nums, int num) {
	        int count = 0;
	        for (int i = 0; i < nums.length; i++) {
	            if (nums[i] == num) {
	                count++;
	            }
	        }
	        return count;
	    }

		public int majorityElement(int[] nums) {
			Random rand = new Random();
	        int majorityCount = nums.length/2;

	        while (true) {
	            int candidate = nums[rand.nextInt(nums.length)];
	            if (countOccurences(nums, candidate) > majorityCount) {
	                return candidate;
	            }
	        }
		}
	}
}
