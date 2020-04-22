package com.zm.LeetCodeEx.algorithms.ex101_200;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.zm.LeetCodeEx.CommonFunctions;
import com.zm.LeetCodeEx.TreeNode;

/**
 * 199. 二叉树的右视图
 * <p>
 * 给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,2,3,null,5,null,4] 输出: [1,3,4]
 * <p>
 * 解释:
 * <p>
 * ...1............<--- <br>
 * ./...\ <br>
 * 2.....3.........<--- <br>
 * .\.....\ <br>
 * ..5.....4.......<--- <br>
 * 
 * 
 * @author zm
 */
public class LEET199 {
	public static void main(String[] args) {
		LEET199 l199 = new LEET199();
		System.out
				.println(l199.new Solution().rightSideView(CommonFunctions.stringToTreeNode("[1,2,3,null,5,null,4]")));
		System.out
				.println(l199.new Solution().rightSideView(CommonFunctions.stringToTreeNode("[1,2,3,null,5,null,null]")));
	}

	class Solution {
		public List<Integer> rightSideView(TreeNode<Integer> root) {
			if (root == null) {
				return new LinkedList<>();
			}
			List<Integer> ret = new LinkedList<>();
			Queue<TreeNode<Integer>> queue = new LinkedList<>();
			queue.add(root);
			while (!queue.isEmpty()) {
				int size = queue.size();
				for (int i = 0; i < size; i++) {
					TreeNode<Integer> node = queue.poll();
					if (i == size - 1) {
						ret.add(node.val);
					}
					if (node.left != null) {
						queue.add(node.left);
					}
					if (node.right != null) {
						queue.add(node.right);
					}
				}
			}
			return ret;
		}
	}
	
	/**
	 * 递归，优先右节点
	 * @author zm
	 *
	 */
	public class Solution2{

	    List<Integer> res = new LinkedList<>();
	    int depth = 0;

	    public List<Integer> rightSideView(TreeNode<Integer> root) {
	        helper(root, 0);
	        return res;
	    }

	    private void helper(TreeNode<Integer> root, int curDepth) {
	        if (root == null) {
	            return;
	        }
	        if (curDepth == depth) {
	            res.add(root.val);
	            depth++;
	        }

	        // 先找右节点，如果右节点存在的话，那么下一层的能看到的数据就是右节点
	        helper(root.right, curDepth+1);
	        // 相同的curDepth作为参数，如果右节点存在，那么左节点的数据不可能存入res
	        helper(root.left, curDepth+1);
	    }
	}
}
