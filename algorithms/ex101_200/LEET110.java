package com.zm.LeetCodeEx.algorithms.ex101_200;

import com.zm.LeetCodeEx.CommonFunctions;
import com.zm.LeetCodeEx.TreeNode;

/**
 * 110. 平衡二叉树
 * <p>
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 * <p>
 * 本题中，一棵高度平衡二叉树定义为：
 * <p>
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
 * <p>
 * 示例 1:
 * <p>
 * 给定二叉树 [3,9,20,null,null,15,7]
 * <p>
 * ......0 <br>
 * ...../.\ <br>
 * ...-3...9 <br>
 * .../.../ <br>
 * .-10..5 <br>
 * 返回 true 。
 * <p>
 * 示例 2:
 * <p>
 * 给定二叉树 [1,2,2,3,3,null,null,4,4]
 * <p>
 * ........1 <br>
 * ......./.\ <br>
 * ......2...2 <br>
 * ...../.\ <br>
 * ....3...3 <br>
 * .../.\ <br>
 * ..4...4 <br>
 * 返回 false 。
 *
 * @author zm
 */
public class LEET110 {
    public static void main(String[] args) {
        LEET110 l110 = new LEET110();
        System.out.println(
                l110.new Solution().isBalanced(CommonFunctions.stringToTreeNode("[3,9,20,null,null,15,7]")));
        System.out.println(
                l110.new Solution().isBalanced(CommonFunctions.stringToTreeNode("[1,2,2,3,3,null,null,4,4]")));
    }

    class Solution {
        public boolean isBalanced(TreeNode root) {
            if (root == null) {
                return true;
            }
            int leftD = getDepth(root.left);
            int rightD = getDepth(root.right);
            return leftD != -1 && rightD != -1 && Math.abs(leftD - rightD) < 2;
        }

        private int getDepth(TreeNode node) {
            if (node == null) {
                return 0;
            } else {
                int leftD = getDepth(node.left);
                int rightD = getDepth(node.right);
                if (leftD == -1 || rightD == -1 || Math.abs(leftD - rightD) >= 2) {
                    return -1;
                }
                return Math.max(leftD, rightD) + 1;
            }
        }
    }
}
