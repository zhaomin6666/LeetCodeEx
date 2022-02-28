package com.zm.LeetCodeEx.algorithms.ex1601_1700;

import com.alibaba.fastjson.JSON;

/**
 * 1601. 最多可达成的换楼请求数目
 * 我们有 n 栋楼，编号从 0 到 n - 1 。每栋楼有若干员工。由于现在是换楼的季节，部分员工想要换一栋楼居住。
 * <p>
 * 给你一个数组 requests ，其中 requests[i] = [fromi, toi] ，表示一个员工请求从编号为 fromi 的楼搬到编号为 toi 的楼。
 * <p>
 * 一开始 所有楼都是满的，所以从请求列表中选出的若干个请求是可行的需要满足 每栋楼员工净变化为 0 。意思是每栋楼 离开 的员工数目 等于 该楼 搬入 的员工数数目。比方说 n = 3 且两个员工要离开楼 0 ，一个员工要离开楼 1 ，一个员工要离开楼 2 ，如果该请求列表可行，应该要有两个员工搬入楼 0 ，一个员工搬入楼 1 ，一个员工搬入楼 2 。
 * <p>
 * 请你从原请求列表中选出若干个请求，使得它们是一个可行的请求列表，并返回所有可行列表中最大请求数目。
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 5, requests = [[0,1],[1,0],[0,1],[1,2],[2,0],[3,4]]
 * 输出：5
 * 解释：请求列表如下：
 * 从楼 0 离开的员工为 x 和 y ，且他们都想要搬到楼 1 。
 * 从楼 1 离开的员工为 a 和 b ，且他们分别想要搬到楼 2 和 0 。
 * 从楼 2 离开的员工为 z ，且他想要搬到楼 0 。
 * 从楼 3 离开的员工为 c ，且他想要搬到楼 4 。
 * 没有员工从楼 4 离开。
 * 我们可以让 x 和 b 交换他们的楼，以满足他们的请求。
 * 我们可以让 y，a 和 z 三人在三栋楼间交换位置，满足他们的要求。
 * 所以最多可以满足 5 个请求。
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：n = 3, requests = [[0,0],[1,2],[2,1]]
 * 输出：3
 * 解释：请求列表如下：
 * 从楼 0 离开的员工为 x ，且他想要回到原来的楼 0 。
 * 从楼 1 离开的员工为 y ，且他想要搬到楼 2 。
 * 从楼 2 离开的员工为 z ，且他想要搬到楼 1 。
 * 我们可以满足所有的请求。
 * 示例 3：
 * <p>
 * 输入：n = 4, requests = [[0,3],[3,1],[1,2],[2,0]]
 * 输出：4
 *
 * @author zm
 */
public class LEET1601 {
	public static void main(String[] args) {
		System.out.println(JSON.toJSONString(new Solution().maximumRequests(5,
				new int[][]{{0, 1}, {1, 0}, {0, 1}, {1, 2}, {2, 0}, {3, 4}})));
		System.out.println(JSON.toJSONString(new Solution().maximumRequests(3,
				new int[][]{{0, 0}, {1, 2}, {2, 1}})));
		System.out.println(JSON.toJSONString(new Solution().maximumRequests(4,
				new int[][]{{0, 3}, {3, 1}, {1, 2}, {2, 0}})));

	}

	/**
	 * dfs枚举所有可能情况
	 * 用一个数组来记录完成耨写请求之后每幢的人员进出情况，只有全是0的时候才满足条件
	 */
	static class Solution {
		// 请求之后每幢的人员进出情况
		private int[] delta;
		// delta中0的数量
		private int cntZero;
		// 返回结果
		private int result = 0;
		// 当前请求数量
		private int cnt = 0;
		// 楼房数
		private int n;

		public int maximumRequests(int n, int[][] requests) {
			delta = new int[n];
			this.n = n;
			cntZero = n;
			dfs(requests, 0);
			return result;
		}

		private void dfs(int[][] requests, int position) {
			if (position == requests.length) {
				// 如果遍历到最后一个
				if (cntZero == n) {
					// 并且所有楼房中的人员进出数为0，更新最大值
					result = Math.max(result, cnt);
				}
				return;
			}
			// 不选request[position]直接dfs
			dfs(requests, position + 1);
			// 选request[position]更新对应状态
			int z = cntZero;
			cnt++;
			int[] request = requests[position];
			// x出发，y到达
			int x = request[0], y = request[1];
			cntZero -= delta[x] == 0 ? 1 : 0;
			--delta[x];
			cntZero += delta[x] == 0 ? 1 : 0;
			cntZero -= delta[y] == 0 ? 1 : 0;
			++delta[y];
			cntZero += delta[y] == 0 ? 1 : 0;
			// dfs
			dfs(requests, position + 1);
			// 还原状态
			--delta[y];
			++delta[x];
			--cnt;
			cntZero = z;
		}
	}
}
