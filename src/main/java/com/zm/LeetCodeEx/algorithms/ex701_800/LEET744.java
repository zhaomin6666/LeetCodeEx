package com.zm.LeetCodeEx.algorithms.ex701_800;

/**
 * 744. 寻找比目标字母大的最小字母
 * 给你一个排序后的字符列表 letters ，列表中只包含小写英文字母。另给出一个目标字母 target，请你寻找在这一有序列表里比目标字母大的最小字母。
 * <p>
 * 在比较时，字母是依序循环出现的。举个例子：
 * <p>
 * 如果目标字母 target = 'z' 并且字符列表为 letters = ['a', 'b']，则答案返回 'a'
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入: letters = ["c", "f", "j"]，target = "a"
 * 输出: "c"
 * 示例 2:
 * <p>
 * 输入: letters = ["c","f","j"], target = "c"
 * 输出: "f"
 * 示例 3:
 * <p>
 * 输入: letters = ["c","f","j"], target = "d"
 * 输出: "f"
 * <p>
 * <p>
 * 提示：
 * <p>
 * 2 <= letters.length <= 10^4
 * letters[i] 是一个小写字母
 * letters 按非递减顺序排序
 * letters 最少包含两个不同的字母
 * target 是一个小写字母
 *
 * @author zm
 */
public class LEET744 {
	public static void main(String[] args) {
		System.out.println(new Solution().nextGreatestLetter(new char[]{'c', 'f', 'j'}, 'a'));
		System.out.println(new Solution().nextGreatestLetter(new char[]{'c', 'f', 'j'}, 'c'));
	}

	/**
	 * 线性查找，第一个找到的大于目标值的返回。
	 * 由于比较时是循环的，所以如果没有找到比目标值大的数则返回第一个。
	 */
	static class Solution {
		public char nextGreatestLetter(char[] letters, char target) {
			char nextGreater = letters[0];
			for (char letter : letters) {
				if (letter > target) {
					nextGreater = letter;
					break;
				}
			}
			return nextGreater;
		}
	}

	/**
	 * 二分法查找
	 */
	static class Solution2 {
		public char nextGreatestLetter(char[] letters, char target) {
			return '1';
		}
	}
}
