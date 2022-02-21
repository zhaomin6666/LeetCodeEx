package com.zm.LeetCodeEx.algorithms.ex301_400;

/**
 * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。</br>
 * 
 * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。</br>
 * 
 * 你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。</br>
 * 
 * 
 * 
 * 示例 1：</br>
 * 
 * 输入：["h","e","l","l","o"] 输出：["o","l","l","e","h"] </br>
 * 
 * 示例 2：</br>
 * 
 * 输入：["H","a","n","n","a","h"] 输出：["h","a","n","n","a","H"]
 * 
 * @author zm
 *
 */
public class LEET344 {
	public static void main(String[] args) {
		LEET344 l344 = new LEET344();
		String s = "hello";
		String s2 = "Hannah";
		char[] c = s.toCharArray();
		char[] c2 = s2.toCharArray();
		l344.reverseString(c);
		l344.reverseString(c2);
		System.out.println(String.valueOf(c));
		System.out.println(String.valueOf(c2));
	}

	public void reverseString(char[] s) {
		for (int i = 0; i < s.length / 2; i++) {
			/*
			 * char t = s[i]; s[i] = s[s.length - i - 1]; s[s.length - i - 1] = t;
			 */
			s[s.length - i - 1] = (char) (s[s.length - i - 1] + s[i]);
			s[i] = (char) (s[s.length - i - 1] - s[i]);
			s[s.length - i - 1] = (char) (s[s.length - i - 1] - s[i]);
		}
	}
}
