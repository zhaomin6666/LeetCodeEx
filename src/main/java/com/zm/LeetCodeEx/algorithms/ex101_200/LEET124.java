package com.zm.LeetCodeEx.algorithms.ex101_200;

import com.zm.LeetCodeEx.CommonFunctions;
import com.zm.LeetCodeEx.TreeNode;

/**
 * 124. 二叉树中的最大路径和
 * <p>
 * 给定一个非空二叉树，返回其最大路径和。
 * <p>
 * 本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,3]
 * <p>
 * .......1 <br>
 * ....../.\ <br>
 * .....2...3 <br>
 * <p>
 * 输出: 6
 * <p>
 * 示例 2:
 * <p>
 * 输入: [-10,9,20,null,null,15,7]
 * <p>
 * ...-10 <br>
 * .../.\ <br>
 * ..9..20 <br>
 * ..../..\ <br>
 * ...15...7 <br>
 * <p>
 * 输出: 42
 *
 * @author zm
 */
public class LEET124 {
	public static void main(String[] args) {
		LEET124 l0124 = new LEET124();
		System.out.println(l0124.new Solution().maxPathSum(CommonFunctions.stringToTreeNode("[1,2,3]")));
		System.out.println(
				l0124.new Solution().maxPathSum(CommonFunctions.stringToTreeNode("[-10,9,20,null,null,15,7]")));
		System.out.println(
				l0124.new Solution().maxPathSum(CommonFunctions.stringToTreeNode("[-1,9,20,null,null,-15,-7]")));
		System.out.println(
				l0124.new Solution().maxPathSum(CommonFunctions.stringToTreeNode("[-1]")));
		System.out.println(
				l0124.new Solution().maxPathSum(CommonFunctions.stringToTreeNode("[-3,-2,-1]")));
	}

	/**
     * 递归，按照二叉树的后序遍历，先处理子节点，返回子节点可以贡献的最大值。
     * 每次递归的时候把左右子节点可以贡献的最大值相加再加上本身值和ret取最大值。
     *
     * @author zm
     */
    class Solution {
        private int ret = Integer.MIN_VALUE;

        public int maxPathSum(TreeNode root) {
            maxPathSumHelper(root);
            return ret;
        }

        private int maxPathSumHelper(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int left = maxPathSumHelper(root.left);
            int right = maxPathSumHelper(root.right);
            int pathSum = left + right + root.val;
            ret = Math.max(pathSum, ret);
            return Math.max(0, Math.max(right, left) + root.val);
        }
    }
}
