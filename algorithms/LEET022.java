package com.zm.LeetCodeEx.algorithms;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;

/**
 * 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
 * 
 * 例如，给出 n = 3，生成结果为：
 * 
 * [ "((()))", "(()())", "(())()", "()(())", "()()()" ]
 * 
 * n=1 r=1 n=2 r=2 n=3 r=5
 * 
 * 
 * @author zm
 *
 */
public class LEET022 {
	public static void main(String[] args) {
		LEET022 l022 = new LEET022();
		System.out.println(JSON.toJSONString(l022.generateParenthesis2(3)));
	}

	/**
	 * 用一个数组维护左括号和右括号剩下的次数即可
	 * @param n
	 * @return
	 */
	public List<String> generateParenthesis(int n) {
		List<String> list = new ArrayList<>();
		int[] temp = new int[2];
		temp[0] = n;
		temp[1] = n;
		String s = "";
		gen(s, temp, list);
		return list;
	}

	private void gen(String s, int[] temp, List<String> list) {
		String s1 = "";
		String s2 = "";
		int[] temp2 = temp.clone();
		int[] temp3 = temp.clone();
		if (temp[0] == 0 && temp[1] == 0) {
			list.add(s);
		} else if (temp[0] == 0) {
			s1 = s + ")";
			temp2[1]--;
			gen(s1, temp2, list);
		} else if (temp[0] == temp[1]) {
			s1 = s + "(";
			temp2[0]--;
			gen(s1, temp2, list);
		} else {
			s1 = s + "(";
			temp2[0]--;
			gen(s1, temp2, list);
			s2 = s + ")";
			temp3[1]--;
			gen(s2, temp3, list);
		}
	}

	/**
	 * 使用两个数字代替数组（优化）
	 * @param n
	 * @return
	 */
	public List<String> generateParenthesis2(int n) {
		List<String> list = new ArrayList<>();
		int count1 = n;
		int count2 = n;
		String s = "";
		gen2(s, count1, count2, list);
		return list;
	}

	private void gen2(String s, int count1, int count2, List<String> list) {
		String s1 = "";
		String s2 = "";
		if (count1 == 0 && count2 == 0) {
			list.add(s);
		} else if (count1 == 0) {
			s1 = s + ")";
			gen2(s1, count1, --count2, list);
		} else if (count1 == count2) {
			s1 = s + "(";
			gen2(s1, --count1, count2, list);
		} else {
			s1 = s + "(";
			gen2(s1, --count1, count2, list);
			s2 = s + ")";
			gen2(s2, ++count1, --count2, list);
		}
	}
}
