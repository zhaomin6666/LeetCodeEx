package com.zm.LeetCodeEx.algorithms.ex101_200;

import java.util.HashMap;

import com.zm.LeetCodeEx.CommonFunctions;
import com.zm.LeetCodeEx.TreeNode;

/**
 * 106. 从中序与后序遍历序列构造二叉树
 * <p>
 * 根据一棵树的中序遍历与后序遍历构造二叉树。<br>
 * 
 * 注意:<br>
 * 你可以假设树中没有重复的元素。<br>
 * 
 * 例如，给出<br>
 * 
 * 中序遍历 inorder = [9,3,15,20,7]<br>
 * 后序遍历 postorder = [9,15,7,20,3]<br>
 * 返回如下的二叉树：<br>
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
public class LEET106 {
	public static void main(String[] args) {
		LEET106 l105 = new LEET106();
		System.out.println(CommonFunctions.treeNodeToString(
				l105.new Solution().buildTree(new int[] { 9, 3, 15, 20, 7 }, new int[] { 9, 15, 7, 20, 3 })));
		System.out.println(CommonFunctions.treeNodeToString(l105.new Solution().buildTree(
				new int[] { 4, 2, 5, 1, 8, 6, 9, 3, 10, 7, 11 }, new int[] { 4, 5, 2, 8, 9, 6, 10, 11, 7, 3, 1 })));

	}

	class Solution {
		HashMap<Integer, Integer> inorderMap = new HashMap<>();
		int[] inorder;
		int[] postorder;
		int postIdx;

		public TreeNode buildTree(int[] inorder, int[] postorder) {
			if (inorder.length == 0) {
				return null;
			}
			this.inorder = inorder;
			this.postorder = postorder;
			postIdx = postorder.length - 1;
			for (int i = 0; i < inorder.length; i++) {
				inorderMap.put(inorder[i], i);
			}
			return helper(0, inorder.length - 1);
		}

		private TreeNode helper(int l, int r) {
			if (l > r) {
				return null;
			}
			int v = postorder[postIdx];
			postIdx--;
			TreeNode<Integer> node = new TreeNode<Integer>(v);
			node.right = helper(inorderMap.get(v) + 1, r);
			node.left = helper(l, inorderMap.get(v) - 1);
			return node;
		}
	}
}
