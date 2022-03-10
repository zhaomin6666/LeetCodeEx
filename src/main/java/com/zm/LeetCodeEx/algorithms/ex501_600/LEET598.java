package com.zm.LeetCodeEx.algorithms.ex501_600;

import com.zm.LeetCodeEx.CommonFunctions;
import com.zm.LeetCodeEx.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * 589. N 叉树的前序遍历
 * 给定一个 n 叉树的根节点  root ，返回 其节点值的 前序遍历 。
 * <p>
 * n 叉树 在输入中按层序遍历进行序列化表示，每组子节点由空值 null 分隔（请参见示例）。
 * <p>
 * 示例 1：
 * 输入：root = [1,null,3,2,4,null,5,6]
 * 输出：[1,3,5,6,2,4]
 * 示例 2：
 * 输入：root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * 输出：[1,2,3,6,7,11,14,4,8,12,5,9,13,10]
 * <p>
 * 提示：
 * <p>
 * 节点总数在范围 [0, 10^4]内
 * 0 <= Node.val <= 10^4
 * n 叉树的高度小于或等于 1000
 *
 * @author zm
 */
public class LEET598 {
	public static void main(String[] args) {
		System.out.println(new Solution().preorder(CommonFunctions.stringToNodeByLevel(
				"[1,null,3,2,4,null,5,6]")));
		System.out.println(new Solution().preorder(CommonFunctions.stringToNodeByLevel(
				"[1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14")));
	}

	/**
	 * 递归dfs
	 */
	static class Solution {
		public List<Integer> preorder(Node root) {
			List<Integer> result = new ArrayList<>();
			dfs(result, root);
			return result;
		}

		private void dfs(List<Integer> result, Node node) {
			if (node == null) {
				return;
			}
			result.add(node.val);
			if (node.children == null) {
				return;
			}
			for (Node child : node.children) {
				dfs(result, child);
			}
		}
	}
}
