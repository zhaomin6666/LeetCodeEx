package com.zm.LeetCodeEx.algorithms.ex501_600;

import com.zm.LeetCodeEx.CommonFunctions;
import com.zm.LeetCodeEx.TreeNode;

/**
 * 543. 二叉树的直径
 * <p>
 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过根结点。
 * <p>
 * 示例 :<br>
 * 给定二叉树
 * <p>
 *      1 <br>
 *     / \ <br>
 *    2   3 <br>
 *   / \ <br>
 *  4   5 <br>
 * 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
 * <p>
 * 注意：两结点之间的路径长度是以它们之间边的数目表示。
 * 
 * 
 * 
 * @author zm
 */
public class LEET543 {
	public static void main(String[] args) {
		LEET543 l543 = new LEET543();
		System.out.println(l543.new Solution().diameterOfBinaryTree(CommonFunctions.stringToTreeNode("[1,2,3,4,5]")));
	}

	class Solution {
        private int max = 0;

        public int diameterOfBinaryTree(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int treeDepthLeft = root.left == null ? 0 : depthOfBinaryTree(root.left);
            int treeDepthRight = root.right == null ? 0 : depthOfBinaryTree(root.right);
            int width = treeDepthLeft + treeDepthRight + 1;
            max = width > max ? width : max;
            return max - 1;
        }

        private int depthOfBinaryTree(TreeNode node) {
            int treeDepthLeft = node.left == null ? 0 : depthOfBinaryTree(node.left);
            int treeDepthRight = node.right == null ? 0 : depthOfBinaryTree(node.right);
            int width = treeDepthLeft + treeDepthRight + 1;
            max = width > max ? width : max;
            return (treeDepthLeft > treeDepthRight ? treeDepthLeft : treeDepthRight) + 1;
        }
    }
}
