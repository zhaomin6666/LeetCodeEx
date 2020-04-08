package com.zm.LeetCodeEx.lcof;

import java.util.LinkedList;
import java.util.Queue;

import com.alibaba.fastjson.JSON;

/**
 * 面试题13. 机器人的运动范围
 * <p>
 * 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0]
 * 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格
 * [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
 * <p>
 * 示例 1：
 * <p>
 * 输入：m = 2, n = 3, k = 1<br>
 * 输出：3
 * <p>
 * 示例 2：
 * <p>
 * 输入：m = 3, n = 1, k = 0<br>
 * 输出：1
 * <p>
 * 提示：
 * <p>
 * 1 <= n,m <= 100 <br>
 * 0 <= k <= 20
 * 
 * 
 * @author zm
 *
 */
public class Lcof013 {
	public static void main(String[] args) {
		Lcof013 l057 = new Lcof013();
		System.out.println(JSON.toJSONString(l057.new Solution().movingCount(3, 3, 2))); // 6
		System.out.println(JSON.toJSONString(l057.new Solution().movingCount(2, 3, 1))); // 3
		System.out.println(JSON.toJSONString(l057.new Solution().movingCount(3, 1, 0))); // 1
		System.out.println(JSON.toJSONString(l057.new Solution().movingCount(40, 40, 18)));
		System.out.println(JSON.toJSONString(l057.new Solution2().movingCount(3, 3, 2))); // 6
		System.out.println(JSON.toJSONString(l057.new Solution2().movingCount(2, 3, 1))); // 3
		System.out.println(JSON.toJSONString(l057.new Solution2().movingCount(3, 1, 0))); // 1
		System.out.println(JSON.toJSONString(l057.new Solution2().movingCount(40, 40, 18)));
	}

	/**
	 * 直接dp
	 * 
	 * @author zm
	 *
	 */
	class Solution {
		public int movingCount(int m, int n, int k) {
			boolean[][] dp = new boolean[m][n];
			temp = new int[Math.max(m, n)];
			dp[0][0] = true;
			int cnt = 1;
			for (int i = 1; i < m; i++) {
				if (dp[i - 1][0] && get(i) <= k) {
					dp[i][0] = true;
					cnt++;
				}
			}
			for (int i = 1; i < n; i++) {
				if (dp[0][i - 1] && get(i) <= k) {
					dp[0][i] = true;
					cnt++;
				}
			}
			for (int i = 1; i < m; i++) {
				for (int j = 1; j < n; j++) {
					if ((dp[i][j - 1] || dp[i - 1][j]) && get(i) + get(j) <= k) {
						dp[i][j] = true;
						cnt++;
					}
				}
			}
			return cnt;
		}

		private int[] temp;

		private int get(int x) {
			if (temp[x] == 0) {
				int res = 0;
				for (; x > 0; x /= 10) {
					res += x % 10;
				}
				temp[x] = res;
				return res;
			} else {
				return temp[x];
			}
		}
	}

	/**
	 * BFS，还是需要用visist数组判断是否访问过，效率并不如dp。DFS的话用栈实现即可
	 * 
	 * @author zm
	 *
	 */
	class Solution2 {
		public int movingCount(int m, int n, int k) {
			boolean[][] v = new boolean[m][n];
			v[0][0] = true;
			Queue<int[]> queue = new LinkedList<int[]>();
			queue.add(new int[] { 0, 0 });
			int cnt = 1;
			while (!queue.isEmpty()) {
				int[] temp = queue.poll();
				if (temp[0] + 1 < m && get(temp[0] + 1) + get(temp[1]) <= k && !v[temp[0] + 1][temp[1]]) {
					queue.add(new int[] { temp[0] + 1, temp[1] });
					v[temp[0] + 1][temp[1]] = true;
					cnt++;
				}
				if (temp[1] + 1 < n && get(temp[0]) + get(temp[1] + 1) <= k && !v[temp[0]][temp[1] + 1]) {
					queue.add(new int[] { temp[0], temp[1] + 1 });
					v[temp[0]][temp[1] + 1] = true;
					cnt++;
				}
			}
			return cnt;
		}

		private int get(int x) {
			int res = 0;
			for (; x > 0; x /= 10) {
				res += x % 10;
			}
			return res;
		}
	}

	/**
	 * 优化计算各数位和，可以发现每次到达10位的时候如9->10，各数位和变化为-8. （100以内） <br>
	 * 不止可以用于dfs，在上面两个方法中均可进行优化。
	 * 
	 * @author zm
	 *
	 */
	class Solution3 {
		int m, n, k;
		boolean[][] visited;

		public int movingCount(int m, int n, int k) {
			this.m = m;
			this.n = n;
			this.k = k;
			this.visited = new boolean[m][n];
			return dfs(0, 0, 0, 0);
		}

		public int dfs(int i, int j, int si, int sj) {
			if (i < 0 || i >= m || j < 0 || j >= n || k < si + sj || visited[i][j])
				return 0;
			visited[i][j] = true;
			return 1 + dfs(i + 1, j, (i + 1) % 10 != 0 ? si + 1 : si - 8, sj)
					+ dfs(i, j + 1, si, (j + 1) % 10 != 0 ? sj + 1 : sj - 8);
		}
	}
}
