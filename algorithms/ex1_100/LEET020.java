package com.zm.LeetCodeEx.algorithms.ex1_100;

import java.util.LinkedList;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * 
 * 有效字符串需满足：
 * 
 * 左括号必须用相同类型的右括号闭合。 左括号必须以正确的顺序闭合。 注意空字符串可被认为是有效字符串。</br>
 * 
 * 示例 1: </br>
 * 输入: "()" 输出: true </br>
 * 
 * 示例 2: </br>
 * 输入: "()[]{}" 输出: true </br>
 * 
 * 示例 3: </br>
 * 
 * 输入: "(]" 输出: false
 * 
 * 
 * @author zm
 *
 */
public class LEET020 {
	public static void main(String[] args) {
		LEET020 l020 = new LEET020();
		String s1 = "()";
		System.out.println(l020.isValid(s1));
		String s2 = "()[]{}";
		System.out.println(l020.isValid(s2));
		String s3 = "(]";
		System.out.println(l020.isValid(s3));
		String s4 = "{([])[]}";
		System.out.println(l020.isValid(s4));
	}

	/**
	 * 使用栈后进后出的思想
	 * @param s
	 * @return
	 */
	public boolean isValid(String s) {
		LinkedList<Character> list = new LinkedList<>();
		for (int i = 0; i < s.length(); i++) {
			if (list.size() > 0) {
				if (list.getLast() == s.charAt(i)) {
					list.removeLast();
				} else {
					list.add(getPair(s.charAt(i)));
				}
			} else {
				list.add(getPair(s.charAt(i)));
			}
		}
		return list.isEmpty();
	}

	private Character getPair(char ch) {
		switch (ch) {
		case '[':
			return ']';
		case '{':
			return '}';
		case '(':
			return ')';
		}
		return ch;
	}

}
