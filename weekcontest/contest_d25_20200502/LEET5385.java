package com.zm.LeetCodeEx.weekcontest.contest_d25_20200502;

import com.alibaba.fastjson.JSON;

/**
 * 双周赛 2020年5月2日
 * <p>
 * 5385. 改变一个整数能得到的最大差值
 * <p>
 * 给你一个整数 num 。你可以对它进行如下步骤恰好 两次 ：
 * <p>
 * 选择一个数字 x (0 <= x <= 9)。<br>
 * 选择另一个数字 y (0 <= y <= 9) 。数字 y 可以等于 x 。<br>
 * 将 num 中所有出现 x 的数位都用 y 替换。<br>
 * 得到的新的整数 不能 有前导 0 ，得到的新整数也 不能 是 0 。<br>
 * 令两次对 num 的操作得到的结果分别为 a 和 b 。<br>
 * <p>
 * 请你返回 a 和 b 的 最大差值 。
 *
 * @author zm
 */
public class LEET5385 {
	public static void main(String[] args) {
		LEET5385 l5385 = new LEET5385();
		System.out.println(JSON.toJSONString(l5385.new Solution().maxDiff(555)));
		System.out.println(JSON.toJSONString(l5385.new Solution().maxDiff(9)));
		System.out.println(JSON.toJSONString(l5385.new Solution().maxDiff(123456)));
		System.out.println(JSON.toJSONString(l5385.new Solution().maxDiff(10000)));
		System.out.println(JSON.toJSONString(l5385.new Solution().maxDiff(9288)));
	}

	class Solution {
		public int maxDiff(int num) {
			String input = String.valueOf(num);
			// 找大数
			char[] inputCharsToBig = input.toCharArray();
			char changeTo9Num = 'a';
			for (int i = 0; i < inputCharsToBig.length; i++) {
				char cur = inputCharsToBig[i];
				if (cur != '9') {
					changeTo9Num = cur;
					break;
				}
			}
			int bigNum = 0;
			if (changeTo9Num == 'a') {
				bigNum = num;
			} else {
				for (int i = 0; i < inputCharsToBig.length; i++) {
					char cur = inputCharsToBig[i];
					if (cur == changeTo9Num) {
						inputCharsToBig[i] = '9';
					}
				}
				bigNum = Integer.valueOf(new String(inputCharsToBig));
			}
			// 找小数
			int smallNum = 0;
			char[] inputCharsToSmall = input.toCharArray();
			char changeTo0Num = 'a';
			if (inputCharsToSmall[0] != '1') {
				changeTo0Num = inputCharsToSmall[0];
				for (int i = 0; i < inputCharsToSmall.length; i++) {
					char cur = inputCharsToSmall[i];
					if (cur == changeTo0Num) {
						inputCharsToSmall[i] = '1';
					}
				}
				smallNum = Integer.valueOf(new String(inputCharsToSmall));
			} else {
				for (int i = 1; i < inputCharsToSmall.length; i++) {
					char cur = inputCharsToSmall[i];
					if (cur != '0' && cur != '1') {
						changeTo0Num = cur;
						break;
					}
				}
				if (changeTo0Num == 'a') {
					smallNum = num;
				} else {
					for (int i = 1; i < inputCharsToBig.length; i++) {
						char cur = inputCharsToSmall[i];
						if (cur == changeTo0Num) {
							inputCharsToSmall[i] = '0';
						}
					}
					smallNum = Integer.valueOf(new String(inputCharsToSmall));
				}
			}
			return bigNum - smallNum;
		}
	}
}
