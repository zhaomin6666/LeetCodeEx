package com.zm.LeetCodeEx.algorithms.ex101_200;

/**
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 * 
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 * 
 * 示例 1:
 * 
 * 输入: "A man, a plan, a canal: Panama" 输出: true
 * 
 * 示例 2:
 * 
 * 输入: "race a car" 输出: false
 * 
 * @author zm
 *
 */
public class LEET125 {
	public static void main(String[] args) {
		LEET125 l125 = new LEET125();
		String str1 = "A man, a plan, a canal: Panama";
		System.out.println(l125.isPalindrome(str1));
	}

	/**
	 * 双指针
	 * 
	 * @param s
	 * @return
	 */
	public boolean isPalindrome(String s) {
		int l = 0;
		int r = s.length() - 1;
		while (l < r && l < s.length() && r > 0) {
			if (!isReg(s.charAt(l))) {
				++l;
				continue;
			}
			if (!isReg(s.charAt(r))) {
				--r;
				continue;
			}
			if (!isSame(s.charAt(l), s.charAt(r))) {
				return false;
			}
			++l;
			--r;
		}
		return true;
	}

	boolean isReg(char c) {
		return (c > 47 && c < 58) || (c > 64 && c < 91) || (c > 96 && c < 123);
	}

	boolean isSame(char l, char r) {
		if (l < 'a' && l > '9') {
			l = (char) (l - 'A' + 'a');
		}
		if (r < 'a' && r > '9') {
			r = (char) (r - 'A' + 'a');
		}
		return l == r;
	}
}
