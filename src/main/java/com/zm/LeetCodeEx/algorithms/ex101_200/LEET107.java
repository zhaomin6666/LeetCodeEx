package com.zm.LeetCodeEx.algorithms.ex101_200;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import com.alibaba.fastjson.JSON;
import com.zm.LeetCodeEx.CommonFunctions;
import com.zm.LeetCodeEx.TreeNode;

/**
 * 107. 二叉树的层次遍历 II
 * <p>
 * 给定一个二叉树，返回其节点值自底向上的层次遍历。.（即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 * <p>
 * 例如：<br>
 * 给定二叉树.[3,9,20,null,null,15,7],<br>
 * 
 * ....3 <br>
 * .../.\ <br>
 * ..9..20 <br>
 * ..../..\ <br>
 * ...15...7 <br>
 * 返回其自底向上的层次遍历为： <br>
 * 
 * [ <br>
 * ..[15,7], <br>
 * ..[9,20], <br>
 * ..[3] <br>
 * ]
 * 
 * @author zm
 */
public class LEET107 {
	public static void main(String[] args) {
		LEET107 l107 = new LEET107();
		System.out.println(JSON.toJSONString(
				l107.new Solution2().levelOrderBottom(CommonFunctions.stringToTreeNode("[3,9,20,null,null,15,7]"))));

	}

	class Solution {
        public List<List<Integer>> levelOrderBottom(TreeNode root) {
            if (root == null) {
                return new LinkedList<>();
            }
            Stack<List<TreeNode>> stack = new Stack<>();
            List<TreeNode> list = new LinkedList<TreeNode>();
            list.add(root);
            stack.add(list);
            while (true) {
                List<TreeNode> listTemp = new LinkedList<TreeNode>();
                List<TreeNode> listLast = stack.peek();
                for (TreeNode treeNode : listLast) {
                    if (treeNode.left != null) {
                        listTemp.add(treeNode.left);
                    }
                    if (treeNode.right != null) {
                        listTemp.add(treeNode.right);
                    }
                }
                if (listTemp.isEmpty()) {
                    break;
                }
                else {
                    stack.add(listTemp);
                }
            }
            LinkedList<List<Integer>> ret = new LinkedList<>();
            while (!stack.isEmpty()) {
                List<TreeNode> temp = stack.pop();
                List<Integer> item = new LinkedList<>();
                for (TreeNode treeNode : temp) {
                    item.add(treeNode.val);
                }
                ret.add(item);
            }
            return ret;
        }
    }

    class Solution2 {
        private LinkedList<List<Integer>> ret = new LinkedList<List<Integer>>();

        public List<List<Integer>> levelOrderBottom(TreeNode root) {
            helper(root, 0);
            return ret;
        }

        private void helper(TreeNode node, int i) {
            if (node != null) {
                if (ret.size() <= i) {
                    List<Integer> row = new LinkedList<>();
                    row.add(node.val);
                    ret.addFirst(row);
                }
                else {
                    ret.get(ret.size() - i - 1).add(node.val);
                }
                helper(node.left, i + 1);
                helper(node.right, i + 1);
            }
        }
    }
}
