package com.zm.LeetCodeEx.algorithms.ex1_100;

import com.alibaba.fastjson.JSON;
import com.zm.LeetCodeEx.CommonFunctions;
import com.zm.LeetCodeEx.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 94. 二叉树的中序遍历
 * <p>
 * 给定一个二叉树，返回它的中序 遍历。
 * <p>
 * 示例:<br>
 * <p>
 * 输入: [1,null,2,3] <br>
 * 1 <br>
 * \ <br>
 * 2 <br>
 * / <br>
 * 3
 * <p>
 * 输出: [1,3,2] 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 *
 * @author zm
 */
public class LEET094 {
    public static void main(String[] args) {
        LEET094 l094 = new LEET094();
        System.out.println(JSON.toJSONString(
                (l094.new Solution2().inorderTraversal(CommonFunctions.stringToTreeNode("[1,null,2,3]")))));
    }

    class Solution {
        List<Integer> list = new LinkedList<>();

        public List<Integer> inorderTraversal(TreeNode root) {
            helper(root);
            return list;
        }

        private void helper(TreeNode node) {
            if (node != null) {
                if (node.left != null) {
                    helper(node.left);
                }
                list.add(node.val);
                if (node.right != null) {
                    helper(node.right);
                }
            }
        }
    }

    class Solution2 {
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> list = new LinkedList<>();
            Stack<TreeNode> stack = new Stack<>();
            TreeNode cur = root;
            while (cur != null || !stack.isEmpty()) {
                if (cur != null) {
                    stack.push(cur);
                    cur = cur.left;
                }
                else {
                    cur = stack.pop();
                    list.add(cur.val);
                    cur = cur.right;
                }
            }
            return list;
        }
    }
}
