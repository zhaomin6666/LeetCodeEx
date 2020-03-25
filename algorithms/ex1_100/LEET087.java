package com.zm.LeetCodeEx.algorithms.ex1_100;

/**
 * 87. 扰乱字符串
 * <p>
 * 给定一个字符串 s1，我们可以把它递归地分割成两个非空子字符串，从而将其表示为二叉树。
 * <p>
 * 下图是字符串 s1 = "great" 的一种可能的表示形式。
 * 
 * great<br>
 * / \<br>
 * gr eat<br>
 * / \ / \<br>
 * g r e at<br>
 * / \<br>
 * a t<br>
 * 在扰乱这个字符串的过程中，我们可以挑选任何一个非叶节点，然后交换它的两个子节点。
 * <p>
 * 例如，如果我们挑选非叶节点 "gr" ，交换它的两个子节点，将会产生扰乱字符串 "rgeat" 。
 * <p>
 * rgeat<br>
 * / \<br>
 * rg eat<br>
 * / \ / \<br>
 * r g e at<br>
 * / \<br>
 * a t<br>
 * 我们将 "rgeat” 称作 "great" 的一个扰乱字符串。
 * <p>
 * 同样地，如果我们继续交换节点 "eat" 和 "at" 的子节点，将会产生另一个新的扰乱字符串 "rgtae" 。
 * <p>
 * rgtae / \ rg tae / \ / \ r g ta e / \ t a 我们将 "rgtae” 称作 "great" 的一个扰乱字符串。
 * <p>
 * 给出两个长度相等的字符串 s1 和 s2，判断 s2 是否是 s1 的扰乱字符串。
 * <p>
 * 示例 1:<br>
 * 输入: s1 = "great", s2 = "rgeat"<br>
 * 输出: true
 * <p>
 * 示例 2:<br>
 * 输入: s1 = "abcde", s2 = "caebd" <br>
 * 输出: false
 *
 * 
 * @author zm
 */
public class LEET087 {
	public static void main(String[] args) {
		LEET087 l085 = new LEET087();
		System.out.println(l085.new Solution().isScramble("abb", "bba"));
	}

	/**
	 * 根据不同条件递归判断是否满足要求
	 * 
	 * @author zm
	 *
	 */
	class Solution {
		public boolean isScramble(String s1, String s2) {
			if (s1.length() != s2.length()) {
				return false;
			}
			if (s1.equals(s2)) {
				return true;
			}
			int[] letters = new int[26];
			for (int i = 0; i < s1.length(); i++) {
				letters[s1.charAt(i) - 'a']++;
				letters[s2.charAt(i) - 'a']--;
			}
			for (int i = 0; i < 26; i++) {
				if (letters[i] != 0) {
					return false;
				}
			}
			for (int i = 1; i < s1.length(); i++) {
				if (isScramble(s1.substring(0, i), s2.substring(0, i))
						&& isScramble(s1.substring(i), s2.substring(i))) {
					return true;
				}
				if (isScramble(s1.substring(i), s2.substring(0, s2.length() - i))
						&& isScramble(s1.substring(0, i), s2.substring(s2.length() - i))) {
					return true;
				}
			}
			return false;
		}
	}

	/**
	 * 评论区中的dp解法 S=S1+S1,T=T1+T2 以下条件满足一个即可 1.S1由T1转化出来并且S2由T2转化出来
	 * 2.S1由T2转化出来并且S2由T1转化出来
	 * 
	 * @author zm
	 *
	 */
	class Solution2 {
		public boolean isScramble(String s1, String s2) {
			char[] chars1 = s1.toCharArray();
			char[] chars2 = s2.toCharArray();

			int n = s1.length();
			if (n != s2.length()) {
				return false;
			}
			boolean[][][] dp = new boolean[n][n][n + 1];
			// 初始化单个字符情况
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					dp[i][j][1] = chars1[i] == chars2[j];
				}
			}
			// 枚举区间长度2～n
			for (int len = 2; len <= n; len++) {
				// 枚举S中的起点位置
				for (int i = 0; i <= n - len; i++) {
					// 枚举T中的起点位置
					for (int j = 0; j <= n - len; j++) {
						// 枚举划分位置
						for (int k = 1; k <= len - 1; k++) {
							// 第一种情况：S1->T1,S2->T2
							if (dp[i][j][k] && dp[i + k][j + k][len - k]) {
								dp[i][j][len] = true;
								break;
							}
							// 第二种情况：S1->T2,S2->T1
							// S1起点i，T2起点j + 前面那段长度len-k，S2起点i+前面长度k
							if (dp[i][j + len - k][k] && dp[i + k][j][len - k]) {
								dp[i][j][len] = true;
								break;
							}
						}
					}
				}
			}
			return dp[0][0][n];
		}
	}
}
