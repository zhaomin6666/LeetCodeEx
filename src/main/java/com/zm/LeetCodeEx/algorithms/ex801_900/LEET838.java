package com.zm.LeetCodeEx.algorithms.ex801_900;

/**
 * 838. 推多米诺
 * n 张多米诺骨牌排成一行，将每张多米诺骨牌垂直竖立。在开始时，同时把一些多米诺骨牌向左或向右推。
 * <p>
 * 每过一秒，倒向左边的多米诺骨牌会推动其左侧相邻的多米诺骨牌。同样地，倒向右边的多米诺骨牌也会推动竖立在其右侧的相邻多米诺骨牌。
 * <p>
 * 如果一张垂直竖立的多米诺骨牌的两侧同时有多米诺骨牌倒下时，由于受力平衡， 该骨牌仍然保持不变。
 * <p>
 * 就这个问题而言，我们会认为一张正在倒下的多米诺骨牌不会对其它正在倒下或已经倒下的多米诺骨牌施加额外的力。
 * <p>
 * 给你一个字符串 dominoes 表示这一行多米诺骨牌的初始状态，其中：
 * <p>
 * dominoes[i] = 'L'，表示第 i 张多米诺骨牌被推向左侧，
 * dominoes[i] = 'R'，表示第 i 张多米诺骨牌被推向右侧，
 * dominoes[i] = '.'，表示没有推动第 i 张多米诺骨牌。
 * 返回表示最终状态的字符串。
 * <p>
 * 示例 1：
 * <p>
 * 输入：dominoes = "RR.L"
 * 输出："RR.L"
 * 解释：第一张多米诺骨牌没有给第二张施加额外的力。
 * 示例 2：
 * <p>
 * 输入：dominoes = ".L.R...LR..L.."
 * 输出："LL.RR.LLRRLL.."
 * <p>
 * 提示：
 * <p>
 * n == dominoes.length
 * 1 <= n <= 105
 * dominoes[i] 为 'L'、'R' 或 '.'
 *
 * @author zm
 */
public class LEET838 {
	public static void main(String[] args) {
		System.out.println(new Solution().pushDominoes("RR.L"));
		System.out.println(new Solution().pushDominoes(".L.R...LR..L.."));
		System.out.println(new Solution().pushDominoes(".L.R...LR..L..R.."));
		System.out.println(new Solution().pushDominoes(".L.R...LR..L.."));
	}

	/**
	 * 从头遍历模拟推倒。对于每一个骨牌遍历一次，推倒再遍历一次。O(n)
	 */
	static class Solution {
		public String pushDominoes(String dominoes) {
			char lastDirection = 'L';
			char[] cs = dominoes.toCharArray();
			int n = cs.length;
			for (int i = 0; i < n; i++) {
				int j = i;
				// 找到一段连续没有被推动的骨牌
				while (j < n && cs[j] == '.') {
					j++;
				}
				// 获取当前的推动方向
				char currentDirection = j < n ? cs[j] : 'R';
				// 如果方向相同，那么直接推倒中间的
				if (currentDirection == lastDirection) {
					while (i < j) {
						cs[i++] = currentDirection;
					}
				}
				// 如果左边是向右倒，右边是向左倒，那么中间的需要往中间倒
				else if (lastDirection == 'R' && currentDirection == 'L') {
					int r = j - 1;
					while (i < r) {
						cs[i++] = 'R';
						cs[r--] = 'L';
					}
					i = j;
				}
				else {
					i = j;
				}
				lastDirection = currentDirection;
			}
			return new String(cs);
		}
	}
}
