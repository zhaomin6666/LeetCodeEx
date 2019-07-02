package com.zm.LeetCodeEx;

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
		System.out.println(JSON.toJSONString(l022.generateParenthesis(3)));
	}

	public List<String> generateParenthesis(int n) {
		List<String> list = new ArrayList<>();
		int[] temp = new int[2];
		temp[0] = n;
		temp[1] = n;
		String s = "";
		gen(s,temp);
	}

	private void gen(String s, int[] temp) {
		
		
	}

}
