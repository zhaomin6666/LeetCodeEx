package com.zm.LeetCodeEx.algorithms.ex1_100;

import java.util.HashMap;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * 
 * @author zm
 *
 */
public class LEET003 {
	public static void main(String[] args) {
		LEET003 l003 = new LEET003();
		System.out.println(l003.new Solution().lengthOfLongestSubstring("abcabcbb"));// 3
		System.out.println(l003.new Solution().lengthOfLongestSubstring("tmmzuxt"));// 5
		System.out.println(l003.new Solution().lengthOfLongestSubstring("aabb"));// 2
		System.out.println(l003.new Solution().lengthOfLongestSubstring("dvdf"));// 3
		System.out.println(l003.new Solution().lengthOfLongestSubstring("aab"));// 2
		System.out.println(l003.new Solution().lengthOfLongestSubstring("au"));// 2
		System.out.println(l003.new Solution().lengthOfLongestSubstring("a"));// 1
		System.out.println(l003.new Solution().lengthOfLongestSubstring(""));// 0
	}

	class Solution {
		/**
		 * 循环遍历字符串，把子串的初始位置设为-1（因为第一个是0，这样第一个字符的时候长度就是0-（-1）=1）。没有重复的时候长度就+1，并且判断一下是否是最长的数，保存一下。
		 * 当有重复的时候，判断重复的第一个字符所在位置和当前起始位置，取较后面的为子串的初始位置。
		 * 
		 * @param s
		 * @return
		 */
		public int lengthOfLongestSubstring(String s) {
			HashMap<Character, Integer> hashMap = new HashMap<>();
			int max = 0;
			int last = -1;
			for (int i = 0; i < s.length(); i++) {
				char charitem = s.charAt(i);
				if (!hashMap.containsKey(charitem)) {
					hashMap.put(charitem, i);
					int len = i - last;
					if (len > max)
						max = len;
				} else {
					if (hashMap.get(charitem) > last) {
						last = hashMap.get(charitem);
					} else {
						int len = i - last;
						if (len > max)
							max = len;
					}
					hashMap.replace(charitem, i);
				}
			}
			return max;
		}
	}

	class Solution2 {
		/**
		 * 滑动窗口（官方题解） 貌似和上面的方法思路差不多，但是优化了代码
		 * 
		 * @param s
		 * @return
		 */
		public int lengthOfLongestSubstring2(String s) {
			int max = 0;
			int left = -1;
			HashMap<Character, Integer> hashMap = new HashMap<>();
			for (int i = 0; i < s.length(); i++) {
				char charitem = s.charAt(i);
				if (hashMap.containsKey(charitem)) {
					left = Math.max(left, hashMap.get(charitem));
				}
				max = Math.max(i - left, max);
				hashMap.put(charitem, i);
			}
			return max;
		}
	}

	/**
	 * 用数组代替hashmap
	 * 
	 * @author zm
	 *
	 */
	class Solution3 {
		public int lengthOfLongestSubstring(String s) {
			int n = s.length();
			int[] index = new int[128];
			int i = 0;
			int maxLen = 0;
			for (int j = 0; j < n; j++) {
				i = Math.max(index[s.charAt(j)], i);
				maxLen = Math.max(maxLen, j - i + 1);
				index[s.charAt(j)] = j + 1;
			}
			return maxLen;
		}
	}
}
