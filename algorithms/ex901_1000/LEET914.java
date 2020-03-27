package com.zm.LeetCodeEx.algorithms.ex901_1000;

import java.util.HashMap;

/**
 * 914. 卡牌分组
 * <p>
 * 给定一副牌，每张牌上都写着一个整数。
 * <p>
 * 此时，你需要选定一个数字 X，使我们可以将整副牌按下述规则分成 1 组或更多组：
 * <p>
 * 每组都有 X 张牌。<br>
 * 组内所有的牌上都写着相同的整数。<br>
 * 仅当你可选的 X >= 2 时返回 true。
 * <p>
 *  
 * 
 * 示例 1：<br>
 * 
 * 输入：[1,2,3,4,4,3,2,1]<br>
 * 输出：true<br>
 * 解释：可行的分组是 [1,1]，[2,2]，[3,3]，[4,4]
 * <p>
 * 示例 2：<br>
 * 
 * 输入：[1,1,1,2,2,2,3,3]<br>
 * 输出：false<br>
 * 解释：没有满足要求的分组。
 * <p>
 * 示例 3：<br>
 * 
 * 输入：[1]<br>
 * 输出：false<br>
 * 解释：没有满足要求的分组。
 * <p>
 * 示例 4：<br>
 * 
 * 输入：[1,1]<br>
 * 输出：true<br>
 * 解释：可行的分组是 [1,1]
 * <p>
 * 示例 5：<br>
 * 
 * 输入：[1,1,2,2,2,2]<br>
 * 输出：true<br>
 * 解释：可行的分组是 [1,1]，[2,2]，[2,2]
 * <p>
 * 提示：
 * <p>
 * 1 <= deck.length <= 10000 0 <= deck[i] < 10000
 * 
 * 
 * @author zm
 */
public class LEET914 {
	public static void main(String[] args) {
		LEET914 l914 = new LEET914();
		System.out.println(l914.new Solution2().hasGroupsSizeX(new int[] { 1, 2, 3, 4, 4, 3, 2, 1 }));
		System.out.println(l914.new Solution2().hasGroupsSizeX(new int[] { 1, 1, 1, 2, 2, 2, 3, 3 }));
		System.out.println(l914.new Solution2().hasGroupsSizeX(new int[] { 1 }));
		System.out.println(l914.new Solution2().hasGroupsSizeX(new int[] { 1, 1 }));
		System.out.println(l914.new Solution2().hasGroupsSizeX(new int[] { 1, 1, 2, 2, 2, 2 }));
	}

	class Solution {
		public boolean hasGroupsSizeX(int[] deck) {
			if (deck.length <= 1) {
				return false;
			}
			HashMap<Integer, Integer> map = new HashMap<>();
			for (int i = 0; i < deck.length; i++) {
				map.put(deck[i], map.getOrDefault(deck[i], 0) + 1);
			}
			for (int i = 2; i <= deck.length; i++) {
				if (deck.length % i == 0) {
					boolean isOk = true;
					for (int v : map.values()) {
						if (v % i != 0) {
							isOk = false;
							break;
						}
					}
					if (isOk) {
						return true;
					}
				}
			}
			return false;
		}
	}

	/**
	 * 用数组代替map
	 * 
	 * @author zm
	 *
	 */
	class Solution2 {
		public boolean hasGroupsSizeX(int[] deck) {
			if (deck.length <= 1) {
				return false;
			}

			int[] cnt = new int[10000];
			for (int i = 0; i < deck.length; i++) {
				cnt[deck[i]]++;
			}
			for (int i = 2; i <= deck.length; i++) {
				if (deck.length % i == 0) {
					boolean isOk = true;
					for (int v : cnt) {
						if (v % i != 0) {
							isOk = false;
							break;
						}
					}
					if (isOk) {
						return true;
					}
				}
			}
			return false;
		}
	}

	/**
	 * 先求最大公约数
	 * 
	 * @author zm
	 *
	 */
	class Solution3 {
		public boolean hasGroupsSizeX(int[] deck) {
			if (deck.length <= 1) {
				return false;
			}

			int[] cnt = new int[10000];
			for (int i = 0; i < deck.length; i++) {
				cnt[deck[i]]++;
			}
			int g = -1;
			for (int v : cnt) {
				if (v > 0) {
					if (g == -1) {
						g = v;
					} else {
						g = gcd(g, v);
					}
				}
			}
			return g >= 2;
		}

		public int gcd(int x, int y) {
			return x == 0 ? y : gcd(y % x, x);
		}
	}
}
