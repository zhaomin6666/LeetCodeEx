package com.zm.LeetCodeEx.algorithms.ex1_100;

import com.zm.LeetCodeEx.CommonFunctions;
import com.zm.LeetCodeEx.TreeNode;

/**
 * 98. 验证二叉搜索树
 * <p>
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 * <p>
 * 假设一个二叉搜索树具有如下特征：
 * <p>
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * 示例 1:
 * <p>
 * 输入:
 * · 2
 * ·/ \
 * 1   3
 * 输出: true
 * 示例 2:
 * <p>
 * 输入:
 * ·   5
 * ·  / \
 * · 1   4
 *      / \
 *     3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 *      根节点的值为 5 ，但是其右子节点值为 4 。
 *
 * @author zm
 */
public class LEET098 {
    public static void main(String[] args) {
        LEET098 l097 = new LEET098();
        System.out.println((l097.new Solution().isValidBST(CommonFunctions.stringToTreeNode("[2,1,3]"))));
        System.out.println((l097.new Solution().isValidBST(CommonFunctions.stringToTreeNode("[5,1,4,null,null,3,6]"))));
        System.out.println((l097.new Solution().isValidBST(CommonFunctions.stringToTreeNode("[4,1,6,null,null,5,7]"))));
        System.out.println((l097.new Solution().isValidBST(CommonFunctions.stringToTreeNode("[-2147483648,null,2147483647]"))));
        System.out.println((l097.new Solution().isValidBST(CommonFunctions.stringToTreeNode("[]"))));
    }

    class Solution {
        public boolean isValidBST(TreeNode<Integer> root) {
            return root == null || ((root.left == null || valid(root.left, null, root.val))
                    && (root.right == null || valid(root.right, root.val, null)));
        }

        private boolean valid(TreeNode<Integer> node, Integer min, Integer max) {
            return (min == null || node.val > min) && (max == null || node.val < max)
                    && (node.left == null || valid(node.left, min, node.val))
                    && (node.right == null || valid(node.right, node.val, max));
        }
    }
}
