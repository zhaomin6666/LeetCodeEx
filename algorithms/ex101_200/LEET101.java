package com.zm.LeetCodeEx.algorithms.ex101_200;

import java.util.Stack;

import com.zm.LeetCodeEx.CommonFunctions;
import com.zm.LeetCodeEx.TreeNode;

/**
 * 101. 对称二叉树
 * <p>
 * 给定一个二叉树，检查它是否是镜像对称的。
 * <p>
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 * <p>
 * ....1 <br>
 * .../.\ <br>
 * ..2...2 <br>
 * ./.\./.\ <br>
 * 3..4.4..3 <br>
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 * <p>
 * ....1 <br>
 * .../.\ <br>
 * ..2...2 <br>
 * ...\...\ <br>
 * ...3....3 <br>
 * <p>
 * 说明: 如果你可以运用递归和迭代两种方法解决这个问题，会很加分。
 * 
 * 
 * @author zm
 */
public class LEET101 {
	public static void main(String[] args) {
		LEET101 l101 = new LEET101();
		/*System.out.println(l101.new Solution2().isSymmetric(CommonFunctions.stringToTreeNode("[1,2,2,3,4,4,3]")));
		System.out.println(l101.new Solution2().isSymmetric(CommonFunctions.stringToTreeNode("[1,2,2,null,3,null,3]")));
		System.out.println(l101.new Solution2().isSymmetric(CommonFunctions.stringToTreeNode("[1]")));*/
		System.out.println(l101.new Solution2().isSymmetric(CommonFunctions.stringToTreeNode("[2,3,3,4,5,5]")));
		
	}

	/**
	 * 递归
	 * 
	 * @author zm
	 *
	 */
	class Solution {
		public boolean isSymmetric(TreeNode root) {
			if (root == null) {
				return true;
			} else {
				return isSymmetricSub(root.left, root.right);
			}
		}

		public boolean isSymmetricSub(TreeNode p, TreeNode q) {
			if (p == null && q == null) {
				return true;
			} else if (p != null && q != null) {
				return p.val == q.val && isSymmetricSub(p.left, q.right) && isSymmetricSub(p.right, q.left);
			} else {
				return false;
			}
		}
	}

	/**
	 * 迭代
	 * 
	 * @author zm
	 *
	 */
	class Solution2 {
		public boolean isSymmetric(TreeNode root) {
			if (root == null) {
				return true;
			} else {
				Stack<TreeNode> stack = new Stack<>();
				stack.push(root.left);
				stack.push(root.right);
				while (!stack.isEmpty()) {
					TreeNode r = stack.pop();
					TreeNode l = stack.pop();
					if (r == null && l == null) {
						continue;
					} else if (r != null && l != null && r.val == l.val) {
						stack.push(l.left);
						stack.push(r.right);
						stack.push(l.right);
						stack.push(r.left);
					} else {
						return false;
					}
				}
				return true;
			}
		}
	}
}
