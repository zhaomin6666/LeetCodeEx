package com.zm.LeetCodeEx.algorithms.ex2001_2100;

import com.alibaba.fastjson.JSON;

/**
 * 2055. 蜡烛之间的盘子
 * 给你一个长桌子，桌子上盘子和蜡烛排成一列。给你一个下标从 0 开始的字符串 s ，它只包含字符 '*' 和 '|' ，其中 '*' 表示一个 盘子 ，'|' 表示一支 蜡烛 。
 * <p>
 * 同时给你一个下标从 0 开始的二维整数数组 queries ，其中 queries[i] = [lefti, righti] 表示 子字符串 s[lefti...righti]
 * （包含左右端点的字符）。对于每个查询，你需要找到 子字符串中 在 两支蜡烛之间 的盘子的 数目 。
 * 如果一个盘子在 子字符串中 左边和右边 都 至少有一支蜡烛，那么这个盘子满足在 两支蜡烛之间 。
 * <p>
 * 比方说，s = "||**||**|*" ，查询 [3, 8] ，表示的是子字符串 "*||**|" 。子字符串中在两支蜡烛之间的盘子数目为 2 ，子字符串中右边两个盘子在它们左边和右边 都 至少有一支蜡烛。
 * 请你返回一个整数数组 answer ，其中 answer[i] 是第 i 个查询的答案。
 * <p>
 * 示例 1:
 * <p>
 * ex-1
 * <p>
 * 输入：s = "**|**|***|", queries = [[2,5],[5,9]]
 * 输出：[2,3]
 * 解释：
 * - queries[0] 有两个盘子在蜡烛之间。
 * - queries[1] 有三个盘子在蜡烛之间。
 * 示例 2:
 * <p>
 * ex-2
 * <p>
 * 输入：s = "***|**|*****|**||**|*", queries = [[1,17],[4,5],[14,17],[5,11],[15,16]]
 * 输出：[9,0,0,0,0]
 * 解释：
 * - queries[0] 有 9 个盘子在蜡烛之间。
 * - 另一个查询没有盘子在蜡烛之间。
 * <p>
 * 提示：
 * <p>
 * 3 <= s.length <= 10^5
 * s 只包含字符 '*' 和 '|' 。
 * 1 <= queries.length <= 10^5
 * queries[i].length == 2
 * 0 <= lefti <= righti < s.length
 *
 * @author zm
 */
public class LEET2055 {
	public static void main(String[] args) {
		System.out.println(JSON.toJSONString(new Solution().platesBetweenCandles("||**||**|*",
				new int[][]{{3, 8}})));
		System.out.println(JSON.toJSONString(new Solution().platesBetweenCandles("**|**|***|",
				new int[][]{{2, 5}, {5, 9}})));
		System.out.println(JSON.toJSONString(new Solution().platesBetweenCandles("***|**|*****|**||**|*",
				new int[][]{{1, 17}, {4, 5}, {14, 17}, {5, 11}, {15, 16}})));
	}

	/**
	 * 预处理获得每一个盘子左右最近的蜡烛。然后再利用前缀和计算两个蜡烛之间的盘子数
	 */
	static class Solution {
		public int[] platesBetweenCandles(String s, int[][] queries) {
			int n = s.length();
			char[] cs = s.toCharArray();
			int[] leftCandle = new int[n];
			int[] rightCandle = new int[n];
			int[] prefixPlate = new int[n];
			int lastOne = -1;
			int cntPlate = 0;
			for (int i = 0; i < n; i++) {
				if (cs[i] == '|') {
					lastOne = i;
				}
				else {
					cntPlate++;
				}
				leftCandle[i] = lastOne;
				prefixPlate[i] = cntPlate;
			}
			lastOne = -1;
			for (int i = n - 1; i >= 0; i--) {
				if (cs[i] == '|') {
					lastOne = i;
				}
				rightCandle[i] = lastOne;
			}
			int resultN = queries.length;
			int[] result = new int[resultN];
			for (int i = 0; i < resultN; i++) {
				int[] query = queries[i];
				int l = rightCandle[query[0]];
				int r = leftCandle[query[1]];
				if (l != -1 && r != -1 && r > l) {
					result[i] = prefixPlate[r] - prefixPlate[l];
				}
			}
			return result;
		}
	}
}
