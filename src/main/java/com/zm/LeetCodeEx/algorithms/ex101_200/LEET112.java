package com.zm.LeetCodeEx.algorithms.ex101_200;

import com.zm.LeetCodeEx.CommonFunctions;
import com.zm.LeetCodeEx.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 112. 路径总和
 * <p>
 * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例: 
 * 给定如下二叉树，以及目标和 sum = 22，
 * <p>
 * ..............5
 * ............./.\
 * ............4...8
 * .........../.../.\
 * ..........11..13..4
 * ........./..\......\
 * ........7....2......1
 * <p>
 * 返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。
 *
 * @author zm
 */
public class LEET112 {
    public static void main(String[] args) {
        LEET112 l112 = new LEET112();
        System.out.println(
                l112.new Solution().hasPathSum(CommonFunctions.stringToTreeNode(
                        "[5,4,8,11,null,13,4,7,2,null,null,null,1]"), 25));
        System.out.println(
                l112.new Solution().hasPathSum(CommonFunctions.stringToTreeNode(
                        "[5,4,8,11,null,13,4,7,2,null,null,null,1]"), 26));
        System.out.println(
                l112.new Solution().hasPathSum(CommonFunctions.stringToTreeNode(
                        "[5,4,8,11,null,13,4,7,2,null,null,null,1]"), 27));
        System.out.println(
                l112.new Solution().hasPathSum(CommonFunctions.stringToTreeNode(
                        "[]"), 0));
        System.out.println(
                l112.new Solution().hasPathSum(CommonFunctions.stringToTreeNode(
                        "[0]"), 0));
        System.out.println(
                l112.new Solution().hasPathSum(CommonFunctions.stringToTreeNode(
                        "[1,2]"), 1));
        System.out.println(
                l112.new Solution().hasPathSum(CommonFunctions.stringToTreeNode(
                        "[1,2]"), 3));
        System.out.println(
                l112.new Solution().hasPathSum(CommonFunctions.stringToTreeNode(
                        "[1,null,2]"), 3));
    }

    /**
     * 另一种递归
     */
    class Solution {
        public boolean hasPathSum(TreeNode root, int sum) {
            // 当节点已经不在树中了，仍然没有返回true说明，该路没有所要的解
            if (root == null) {
                return false;
            }
            // root节点的左右孩子都为null说明该节点是叶子节点
            if (root.left == null && root.right == null && root.val == sum) {
                return true;
            }
            return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
        }
    }

    /**
     *
     */
    class Solution2 {
        public boolean hasPathSum(TreeNode root, int sum) {
            if (root == null) {
                return false;
            }
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                TreeNode temp = queue.poll();
                if (temp.right == null && temp.left == null) {
                    if (temp.val == sum) {
                        return true;
                    }
                }
                if (temp.right != null) {
                    temp.right.val += temp.val;
                    queue.add(temp.right);
                }
                if (temp.left != null) {
                    temp.left.val += temp.val;
                    queue.add(temp.left);
                }

            }
            return false;
        }
    }

    class Solution3 {
        private int sum;

        public boolean hasPathSum(TreeNode root, int sum) {
            this.sum = sum;
            return root != null && helper(root, 0);
        }

        private boolean helper(TreeNode node, int i) {
            int temp = i + node.val;
            if (node.left == null && node.right == null) {
                return sum == temp;
            }
            return (node.left != null && helper(node.left, temp))
                    || (node.right != null && helper(node.right, temp));

        }
    }
}
