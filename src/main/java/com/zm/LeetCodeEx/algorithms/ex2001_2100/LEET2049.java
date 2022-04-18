package com.zm.LeetCodeEx.algorithms.ex2001_2100;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;

/**
 * 2049. 统计最高分的节点数目
 * 给你一棵根节点为 0 的 二叉树 ，它总共有 n 个节点，节点编号为 0 到 n - 1 。同时给你一个下标从 0 开始的整数数组 parents 表示这棵树，其中 parents[i] 是节点 i 的父节点。由于节点 0 是根，所以 parents[0] == -1 。
 * <p>
 * 一个子树的 大小 为这个子树内节点的数目。每个节点都有一个与之关联的 分数 。求出某个节点分数的方法是，将这个节点和与它相连的边全部 删除 ，剩余部分是若干个 非空 子树，这个节点的 分数 为所有这些子树 大小的乘积 。
 * <p>
 * 请你返回有 最高得分 节点的 数目 。
 * <p>
 * 示例 1:
 * <p>
 * example-1
 * <p>
 * 输入：parents = [-1,2,0,2,0]
 * 输出：3
 * 解释：
 * - 节点 0 的分数为：3 * 1 = 3
 * - 节点 1 的分数为：4 = 4
 * - 节点 2 的分数为：1 * 1 * 2 = 2
 * - 节点 3 的分数为：4 = 4
 * - 节点 4 的分数为：4 = 4
 * 最高得分为 4 ，有三个节点得分为 4 （分别是节点 1，3 和 4 ）。
 * 示例 2：
 * <p>
 * example-2
 * <p>
 * 输入：parents = [-1,2,0]
 * 输出：2
 * 解释：
 * - 节点 0 的分数为：2 = 2
 * - 节点 1 的分数为：2 = 2
 * - 节点 2 的分数为：1 * 1 = 1
 * 最高分数为 2 ，有两个节点分数为 2 （分别为节点 0 和 1 ）。
 * <p>
 * 提示：
 * <p>
 * n == parents.length
 * 2 <= n <= 105
 * parents[0] == -1
 * 对于 i != 0 ，有 0 <= parents[i] <= n - 1
 * parents 表示一棵二叉树。
 *
 * @author zm
 */
public class LEET2049 {
	public static void main(String[] args) {
		System.out.println(JSON.toJSONString(new Solution().countHighestScoreNodes(
				new int[]{-1, 2, 0, 2, 0})));
		System.out.println(JSON.toJSONString(new Solution().countHighestScoreNodes(
				new int[]{-1, 2, 0})));
	}

	static class Solution {
		ArrayList<Integer>[] children;
		int cnt = 0;
		long max = 0;
		int n;

		public int countHighestScoreNodes(int[] parents) {
			n = parents.length;
			children = new ArrayList[n];
			for (int i = 0; i < n; i++) {
				if (children[parents[i]] == null) {
					children[parents[i]] = new ArrayList<>();
				}
				children[parents[i]].add(i);
			}
			dfs(0);
			return cnt;
		}

		private int dfs(int node) {
			long score = 1;
			int size = n - 1; // 去掉自身节点
			for (int child : children[node]) {
				int childNodeCount = dfs(child);
				score *= childNodeCount;
				size -= childNodeCount;
			}
			if (node != 0) {
				// 非根节点有父节点
				score *= size;
			}
			if (score == max) {
				++cnt;
			}
			else if (score > max) {
				cnt = 1;
				max = score;
			}
			return n - size; // size = n - 1 - nodeChildrenCount, n - size = nodeChildrenCount + 1(自身节点)
		}
	}
}
