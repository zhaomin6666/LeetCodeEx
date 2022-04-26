package com.zm.LeetCodeEx.competition.y2022spring;

import com.zm.LeetCodeEx.CommonFunctions;
import com.zm.LeetCodeEx.TreeNode;

/**
 * LCP 52. 二叉搜索树染色
 * <p>
 * 欢迎各位勇者来到力扣城，本次试炼主题为「二叉搜索树染色」。
 * <p>
 * 每位勇士面前设有一个二叉搜索树的模型，模型的根节点为 root，树上的各个节点值均不重复。初始时，所有节点均为蓝色。现在按顺序对这棵二叉树进行若干次操作， ops[i] = [type, x, y] 表示第 i 次操作为：
 * <p>
 * type 等于 0 时，将节点值范围在 [x, y] 的节点均染蓝
 * type 等于 1 时，将节点值范围在 [x, y] 的节点均染红
 * 请返回完成所有染色后，该二叉树中红色节点的数量。
 * <p>
 * 注意：
 * <p>
 * 题目保证对于每个操作的 x、y 值定出现在二叉搜索树节点中
 * 示例 1：
 * <p>
 * 输入：root = [1,null,2,null,3,null,4,null,5], ops = [[1,2,4],[1,1,3],[0,3,5]]
 * <p>
 * 输出：2
 * <p>
 * 解释：
 * 第 0 次操作，将值为 2、3、4 的节点染红；
 * 第 1 次操作，将值为 1、2、3 的节点染红；
 * 第 2 次操作，将值为 3、4、5 的节点染蓝；
 * 因此，最终值为 1、2 的节点为红色节点，返回数量 2
 * image.png
 * <p>
 * 示例 2：
 * <p>
 * 输入：root = [4,2,7,1,null,5,null,null,null,null,6]
 * ops = [[0,2,2],[1,1,5],[0,4,5],[1,5,7]]
 * <p>
 * 输出：5
 * <p>
 * 解释：
 * 第 0 次操作，将值为 2 的节点染蓝；
 * 第 1 次操作，将值为 1、2、4、5 的节点染红；
 * 第 2 次操作，将值为 4、5 的节点染蓝；
 * 第 3 次操作，将值为 5、6、7 的节点染红；
 * 因此，最终值为 1、2、5、6、7 的节点为红色节点，返回数量 5
 * image.png
 * <p>
 * 提示：
 * <p>
 * 1 <= 二叉树节点数量 <= 10^5
 * 1 <= ops.length <= 10^5
 * ops[i].length == 3
 * ops[i][0] 仅为 0 or 1
 * 0 <= ops[i][1] <= ops[i][2] <= 10^9
 * 0 <= 节点值 <= 10^9
 */
public class LCP52 {

	public static void main(String[] args) {
		//System.out.println(new Solution().getNumber(CommonFunctions.stringToTreeNode("[1,null,2,null,3,null,4,null,5]"),
		//		new int[][]{{1, 2, 4}, {1, 1, 3}, {0, 3, 5}}));
		//System.out.println(new Solution().getNumber(CommonFunctions.stringToTreeNode("[4,2,7,1,null,5,null,null,null,null,6]"),
		//		new int[][]{{0, 2, 2}, {1, 1, 5}, {0, 4, 5}, {1, 5, 7}}));
		System.out.println(new Solution().getNumber(CommonFunctions.stringToTreeNode("[3,2,10,1,null,5,null,null,null,4,8,null,null,7,9]"),
				new int[][]{{1, 1, 8}, {0, 3, 3}, {0, 2, 6}, {1, 3, 4}}));
	}

	static class Solution {
		int result = 0;

		public int getNumber(TreeNode root, int[][] ops) {
			TreeNode dummyHead = new TreeNode(-1);
			dummyHead.right = root;
			for (int i = ops.length - 1; i >= 0; i--) {
				boolean isRed = ops[i][0] == 1;
				searchRange(dummyHead.right, ops[i][1], ops[i][2], dummyHead, false, isRed);
			}
			return result;
		}

		public void searchRange(TreeNode root, int k1, int k2, TreeNode parent, boolean isLeftNode, boolean needAdd) {
			travel(root, k1, k2, parent, isLeftNode, needAdd);
		}

		private void travel(TreeNode root, int k1, int k2, TreeNode parent, boolean isLeftNode, boolean needAdd) {
			if (root == null) {
				return;
			}
			while (root != null && k1 <= root.val && root.val <= k2) {
				if (needAdd) {
					result++;
				}
				deleteNode(root, parent, isLeftNode);
				root = isLeftNode ? parent.left : parent.right;
			}
			if (root != null) {
				// 剪枝，如果当前节点小于等于k1，不必访问左子树
				if (root.val > k1) {
					travel(root.left, k1, k2, root, true, needAdd);
				}
				// 剪枝，如果当前节点大于等于k2，不必访问右子树
				if (root.val < k2) {
					travel(root.right, k1, k2, root, false, needAdd);
				}
			}
		}

		public void deleteNode(TreeNode root, TreeNode parent, boolean isLeftNode) {
			if (root.left == null && root.right == null) {
				if (isLeftNode) {
					parent.left = null;
				}
				else {
					parent.right = null;
				}
			}
			else if (root.right != null) {
				root.val = delSuccessor(root);
			}
			else {
				root.val = delPredecessor(root);
			}
		}

		public int delSuccessor(TreeNode root) {
			TreeNode pre = root;
			root = root.right;
			if (root.left == null) {
				pre.right = root.right;
			}
			else {
				while (root.left != null) {
					pre = root;
					root = root.left;
				}
				pre.left = root.right;
			}
			return root.val;
		}

		public int delPredecessor(TreeNode root) {
			TreeNode pre = root;
			root = root.left;
			if (root.right == null) {
				pre.left = root.left;
			}
			else {
				while (root.right != null) {
					pre = root;
					root = root.right;
				}
				pre.right = root.left;
			}
			return root.val;
		}
	}

	/**
	 * 直接对每一个节点进行判断，每个节点根据倒序的操作，如果有操作则直接判断值
	 */
	static class Solution2 {
		private int ret = 0;
		private int[][] ops;

		public int getNumber(TreeNode root, int[][] ops) {
			this.ops = ops;
			dfs(root);
			return ret;
		}

		private void dfs(TreeNode root) {
			if (root == null) {
				return;
			}
			dfs(root.left);
			check(root.val);
			dfs(root.right);
		}

		private void check(int tar) {
			for (int i = ops.length - 1; i >= 0; i--) {
				int[] p = ops[i];
				if (tar >= p[1] && tar <= p[2]) {
					ret += p[0] == 1 ? 1 : 0;
					break;
				}
			}
		}
	}
}

