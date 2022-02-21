package com.zm.LeetCodeEx.weekcontest.before20200426;

import java.util.Arrays;

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
		System.out.println(l5195.new Solution().longestDiverseString(6, 2, 0));
		System.out.println(l5195.new Solution().longestDiverseString(6, 1, 1));
		System.out.println(l5195.new Solution().longestDiverseString(4, 4, 3));

	}

	class Solution {
		public String longestDiverseString(int a, int b, int c) {
			MyChar[] myChars = new MyChar[] { new MyChar('a', a), new MyChar('b', b), new MyChar('c', c), };
			Arrays.sort(myChars);
			// for (int i = 0; i < myChars.length; i++) {
			// System.out.println(myChars[i].toString());
			// }
			MyChar myChar1 = myChars[0];
			MyChar myChar2 = myChars[1];
			MyChar myChar3 = myChars[2];
			StringBuilder sb = new StringBuilder();
			if ((myChar1.count + myChar2.count + 1) * 2 < myChar3.count) {
				while (myChar1.count + myChar2.count > 0) {
					// 如果最多的字符不能被全用用完，则每次增加两个最多的字符，再加一个其他的字符
					sb.append(myChar3.ch).append(myChar3.ch);
					if (myChar2.count > 0) {
						sb.append(myChar2.ch);
						myChar2.count--;
					} else {
						sb.append(myChar1.ch);
						myChar1.count--;
					}
				}
				// 最后再加上两个最多的字符
				sb.append(myChar3.ch).append(myChar3.ch);
				return sb.toString();
			} else {
				// 如果所有字符都能被用完，先用2个最多的字符，
				// 第二、三个字符就要考虑此时字符能否被都用完，如果用了第二（三）个字符之后，最多的字符不能被用完那么就不用。
				while (myChar1.count + myChar2.count + myChar3.count > 0) {
					// 最多的字符
					for (int i = 0; i < 2; i++) {
						if (myChar3.count > 0) {
							sb.append(myChar3.ch);
							myChar3.count--;
						}
					}
					// 第二多的字符
					for (int i = 0; i < 2; i++) {
						if (myChar2.count > 0 && (myChar2.count + myChar1.count) * 2 >= myChar3.count) {
							sb.append(myChar2.ch);
							myChar2.count--;
						}
					}
					// 最少的字符
					for (int i = 0; i < 2; i++) {
						if (myChar1.count > 0 && (myChar1.count + myChar2.count) * 2 >= myChar3.count) {
							sb.append(myChar1.ch);
							myChar1.count--;
						}
					}
				}
				return sb.toString();
			}
		}
	}

	private class MyChar implements Comparable<MyChar> {
		char ch;
		int count;

		public MyChar(char ch, int count) {
			this.ch = ch;
			this.count = count;
		}

		@Override
		public int compareTo(MyChar o) {
			MyChar other = o;
			return this.count - other.count == 0 ? other.ch - this.ch : this.count - other.count;
		}

		@Override
		public String toString() {
			return "'" + ch + "': " + count;
		}
	}
}
