package com.zm.LeetCodeEx.algorithms.ex101_200;

import java.util.HashMap;

import com.zm.LeetCodeEx.CommonFunctions;
import com.zm.LeetCodeEx.TreeNode;

/**
 * 105. 从前序与中序遍历序列构造二叉树
 * <p>
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 * 
 * 注意: <br>
 * 你可以假设树中没有重复的元素。 <br>
 * 
 * 例如，给出 <br>
 * 前序遍历 preorder = [3,9,20,15,7] <br>
 * 中序遍历 inorder = [9,3,15,20,7] <br>
 * 返回如下的二叉树： <br>
 * 
 * ....3 <br>
 * .../.\ <br>
 * ..9..20 <br>
 * ..../..\ <br>
 * ...15...7 <br>
 *
 * 
 * @author zm
 */
public class LEET105 {
	public static void main(String[] args) {
		LEET105 l105 = new LEET105();
		System.out.println(CommonFunctions.treeNodeToString(
				l105.new Solution().buildTree(new int[] { 3, 9, 20, 15, 7 }, new int[] { 9, 3, 15, 20, 7 })));
		System.out.println(CommonFunctions
				.treeNodeToString(l105.new Solution().buildTree(new int[] { 1, 2, 3 }, new int[] { 1, 2, 3 })));
		System.out.println(CommonFunctions
				.treeNodeToString(l105.new Solution().buildTree(new int[] { 1, 2, 3 }, new int[] { 1, 3, 2 })));
		System.out.println(CommonFunctions
				.treeNodeToString(l105.new Solution().buildTree(new int[] { 1, 2, 3 }, new int[] { 2, 3, 1 })));

	}
	
	/**
	 * 先序遍历中先遇到的结点为根节点，然后在中序遍历中根节点会将树分为左右两个部分。
	 * 找到根节点在中序遍历中的index，递归生成左右子树
	 * @author zm
	 *
	 */
	class Solution {
		int pre_idx = 0;
		int[] preorder, inorder;
		HashMap<Integer, Integer> idx_map = new HashMap<Integer, Integer>();

		public TreeNode buildTree(int[] preorder, int[] inorder) {
			if (preorder.length == 0) {
				return null;
			}
			this.preorder = preorder;
			this.inorder = inorder;
			int idx = 0;
			for (Integer val : inorder) {
				idx_map.put(val, idx++);
			}
			return helper(0, inorder.length);

		}

		private TreeNode<Integer> helper(int in_left, int in_right) {
			// if there is no elements to construct subtrees
			if (in_left == in_right) {
				return null;
			}
			// pick up pre_idx element as a root
			int root_val = preorder[pre_idx];
			TreeNode root = new TreeNode(root_val);

			// root splits inorder list
			// into left and right subtrees
			int index = idx_map.get(root_val);

			// recursion
			pre_idx++;
			// build left subtree
			root.left = helper(in_left, index);
			// build right subtree
			root.right = helper(index + 1, in_right);
			return root;
		}
	}
}
