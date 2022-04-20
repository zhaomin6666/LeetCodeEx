package com.zm.LeetCodeEx.weekcontest.contest_257_20210905;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 1996. 游戏中弱角色的数量
 * <p>
 * 你正在参加一个多角色游戏，每个角色都有两个主要属性：攻击 和 防御 。给你一个二维整数数组 properties ，其中 properties[i] = [attacki, defensei] 表示游戏中第 i 个角色的属性。
 * <p>
 * 如果存在一个其他角色的攻击和防御等级 都严格高于 该角色的攻击和防御等级，则认为该角色为 弱角色 。更正式地，如果认为角色 i 弱于 存在的另一个角色 j ，那么 attackj > attacki 且 defensej > defensei 。
 * <p>
 * 返回 弱角色 的数量。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：properties = [[5,5],[6,3],[3,6]]
 * 输出：0
 * 解释：不存在攻击和防御都严格高于其他角色的角色。
 * 示例 2：
 * <p>
 * 输入：properties = [[2,2],[3,3]]
 * 输出：1
 * 解释：第一个角色是弱角色，因为第二个角色的攻击和防御严格大于该角色。
 * 示例 3：
 * <p>
 * 输入：properties = [[1,5],[10,4],[4,3]]
 * 输出：1
 * 解释：第三个角色是弱角色，因为第二个角色的攻击和防御严格大于该角色。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 2 <= properties.length <= 105
 * properties[i].length == 2
 * 1 <= attacki, defensei <= 105
 */
public class LEET1996 {
	public static void main(String[] args) {
		System.out.println(new Solution().numberOfWeakCharacters(new int[][]{{1, 5}, {10, 4}, {4, 3}}));
	}

	/**
	 * 排序数组，进攻值按照倒序排序，防御按照正序排序。
	 * 这样能够保证当进攻值相同的时候，下一位的防御值大于等于上一个的防御值，不会被误计入结果。
	 * 否则如p1=(7,9),p2=(7,5)，5<9的情况下由于p1在p2前面，遍历到p2的时候会把p2加入结果计数。
	 */
	static class Solution {
		public int numberOfWeakCharacters(int[][] properties) {
			Arrays.sort(properties, (o1, o2) -> o1[0] == o2[0] ? (o1[1] - o2[1]) : (o2[0] - o1[0]));
			int cnt = 0;
			int max = 0;
			for (int[] property : properties) {
				if (max > property[1]) {
					++cnt;
				}
				else {
					max = property[1];
				}
			}
			return cnt;
		}
	}

	/**
	 * 单调栈保存防御值，栈中元素单调递减
	 * 数组排序按照攻击正序，防御倒序
	 * 这样遍历中遇到防御值大于栈顶元素，能够保证攻击值大于栈顶元素
	 */
	static class Solution2 {
		public int numberOfWeakCharacters(int[][] properties) {
			Arrays.sort(properties, (o1, o2) -> o1[0] == o2[0] ? (o2[1] - o1[1]) : (o1[0] - o2[0]));
			int ans = 0;
			Deque<Integer> st = new ArrayDeque<>();
			for (int[] p : properties) {
				while (!st.isEmpty() && st.peek() < p[1]) {
					st.pop();
					ans++;
				}
				st.push(p[1]);
			}
			return ans;
		}
	}
}
