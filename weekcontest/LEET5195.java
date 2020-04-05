package com.zm.LeetCodeEx.weekcontest;

import java.util.Arrays;

import com.alibaba.fastjson.JSON;

/**
 * 周赛 2020年4月5日
 * <p>
 * 5195. 最长快乐字符串
 * <p>
 * 如果字符串中不含有任何 'aaa'，'bbb' 或 'ccc' 这样的字符串作为子串，那么该字符串就是一个「快乐字符串」。
 * <p>
 * 给你三个整数 a，b ，c，请你返回 任意一个 满足下列全部条件的字符串 s：
 * <p>
 * s 是一个尽可能长的快乐字符串。 <br>
 * s 中 最多 有a 个字母 'a'、b 个字母 'b'、c 个字母 'c' 。 <br>
 * s 中只含有 'a'、'b' 、'c' 三种字母。 <br>
 * 如果不存在这样的字符串 s ，请返回一个空字符串 ""。
 * <p>
 * 示例 1：
 * <p>
 * 输入：a = 1, b = 1, c = 7 <br>
 * 输出："ccaccbcc" <br>
 * 解释："ccbccacc" 也是一种正确答案。
 * <p>
 * 示例 2：
 * <p>
 * 输入：a = 2, b = 2, c = 1 <br>
 * 输出："aabbc"
 * <p>
 * 示例 3：
 * <p>
 * 输入：a = 7, b = 1, c = 0 <br>
 * 输出："aabaa" <br>
 * 解释：这是该测试用例的唯一正确答案。
 * <p>
 * 
 * 提示：
 * <p>
 * 0 <= a, b, c <= 100 <br>
 * a + b + c > 0
 *
 * @author zm
 */
public class LEET5195 {
	public static void main(String[] args) {
		LEET5195 l5195 = new LEET5195();
		System.out.println(l5195.new Solution().longestDiverseString(1, 1, 7));
		System.out.println(l5195.new Solution().longestDiverseString(2, 1, 1));
		System.out.println(l5195.new Solution().longestDiverseString(7, 1, 0));
		System.out.println(l5195.new Solution().longestDiverseString(4, 4, 3));

	}

	class Solution {
		public String longestDiverseString(int a, int b, int c) {
			int[] array = new int[3];
			array[0] = a;
			array[1] = b;
			array[2] = c;
			Arrays.sort(array);
			char[] cs = { 'd', 'd', 'd' };

			for (int i = cs.length - 1; i >= 0; i--) {
				if (array[i] == a && cs[i] == 'd') {
					cs[i] = 'a';
					break;
				}
			}
			for (int i = cs.length - 1; i >= 0; i--) {
				if (array[i] == b && cs[i] == 'd') {
					cs[i] = 'b';
					break;
				}
			}
			for (int i = cs.length - 1; i >= 0; i--) {
				if (array[i] == c && cs[i] == 'd') {
					cs[i] = 'c';
					break;
				}
			}
			System.out.println(JSON.toJSONString(cs));
			System.out.println(JSON.toJSONString(array));
			if ((array[0] + array[1] + 1) * 2 < array[2]) {
				StringBuilder sb = new StringBuilder();
				while (array[0] + array[1] > 0) {
					sb.append(cs[2]).append(cs[2]);
					if (array[1] > 0) {
						sb.append(cs[1]);
						array[1]--;
					} else {
						sb.append(cs[0]);
						array[0]--;
					}
				}
				sb.append(cs[2]).append(cs[2]);
				return sb.toString();
			} else {
				StringBuilder sb = new StringBuilder();
				while (array[0] + array[1] > 0) {
					if (array[2] > 0) {
						sb.append(cs[2]);
						array[2]--;
					}
					if (array[2] > 0) {
						sb.append(cs[2]);
						array[2]--;
					}
					if (array[1] > 0) {
						sb.append(cs[1]);
						if (array[1] > array[2] && array[1] > 1) {
							sb.append(cs[1]);
							array[1]--;
						}
						array[1]--;
					}
					if (array[0] > 0) {
						sb.append(cs[0]);
						if (array[0] > array[2] && array[0] > 1) {
							sb.append(cs[0]);
							array[0]--;
						}
						array[0]--;
					}
				}
				while (array[2] > 0) {
					sb.append(cs[2]);
					array[2]--;
				}
				while (array[1] > 0) {
					sb.append(cs[1]);
					array[1]--;
				}
				while (array[0] > 0) {
					sb.append(cs[0]);
					array[0]--;
				}
				return sb.toString();
			}
		}
	}
}
