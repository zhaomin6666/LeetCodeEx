package com.zm.LeetCodeEx.algorithms.ex1_100;

import com.zm.LeetCodeEx.CommonFunctions;
import com.zm.LeetCodeEx.TreeNode;

/**
 * 100. 相同的树
 * <p>
 * 给定两个二叉树，编写一个函数来检验它们是否相同。
 * <p>
 * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
 * <p>
 * 示例 1:<br>
 * 输入:<br>
 * 1 1 <br>
 * / \ / \ <br>
 * 2 3 2 3 <br>
 * [1,2,3], [1,2,3]<br>
 * 输出: true
 * <p>
 * 示例 2:<br>
 * 输入: <br>
 * 1 1 <br>
 * / \ <br>
 * 2 2 <br>
 * [1,2], [1,null,2]<br>
 * 输出: false
 * <p>
 * 示例 3:<br>
 * 输入: <br>
 * 1 1<br>
 * / \ / \ <br>
 * 2 1 1 2<br>
 * [1,2,1], [1,1,2]<br>
 * 输出: false
 * 
 * @author zm
 */
public class LEET100 {
	public static void main(String[] args) {
		LEET100 l100 = new LEET100();
		System.out.println(l100.new Solution().isSameTree(CommonFunctions.stringToTreeNode("[1,2,3]"),
				CommonFunctions.stringToTreeNode("[1,2,3]")));
		System.out.println(l100.new Solution().isSameTree(CommonFunctions.stringToTreeNode("[1,2]"),
				CommonFunctions.stringToTreeNode("[1,null,2]")));
		System.out.println(l100.new Solution().isSameTree(CommonFunctions.stringToTreeNode("[1,2,1]"),
				CommonFunctions.stringToTreeNode("[1,1,2]")));
		System.out.println(l100.new Solution().isSameTree(CommonFunctions.stringToTreeNode("[10,5,15]"),
				CommonFunctions.stringToTreeNode("[10,5,null,null,15]")));
	}

	class Solution {
		public boolean isSameTree(TreeNode<Integer> p, TreeNode<Integer> q) {
			if (p != null) {
				if (q == null) {
					return false;
				}
				return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
			}
			if(q != null) {
				return false;
			}
			return true;
		}
	}
}
