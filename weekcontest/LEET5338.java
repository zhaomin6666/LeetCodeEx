package com.zm.LeetCodeEx.weekcontest;

import com.zm.LeetCodeEx.CommonFunctions;
import com.zm.LeetCodeEx.TreeNode;

/**
 * 双周赛 2020年3月7日 5338. 二叉树中的最长交错路径
 * <p>
 * 给你一棵以 root 为根的二叉树，二叉树中的交错路径定义如下：
 * <p>
 * 选择二叉树中 任意 节点和一个方向（左或者右）。
 * 如果前进方向为右，那么移动到当前节点的的右子节点，否则移动到它的左子节点。
 * 改变前进方向：左变右或者右变左。
 * 重复第二步和第三步，直到你在树中无法继续移动。
 * 交错路径的长度定义为：访问过的节点数目 - 1（单个节点的路径长度为 0 ）。
 * <p>
 * 请你返回给定树中最长 交错路径 的长度。
 *
 * @author zm
 */
public class LEET5338 {
    public static void main(String[] args) {
        LEET5338 l5338 = new LEET5338();
        System.out.println(l5338.new Solution().longestZigZag(CommonFunctions.stringToTreeNode("[1,null,1,1,1,null,null,1,1,null,1,null,null,null,1,null,1]")));
        System.out.println(l5338.new Solution().longestZigZag(CommonFunctions.stringToTreeNode("[1,1,1,null,1,null,null,1,1,null,1]")));
    }

    class Solution {
        private int ret = 0;

        public int longestZigZag(TreeNode root) {
            if (root != null) {
                helper(0, root.left, true);
                helper(0, root.right, false);
            }
            return ret;
        }

        private void helper(int i, TreeNode node, boolean isLeft) {
            if (node != null) {
                ret = ++i > ret ? i : ret;
                boolean helperR = false;
                boolean helperL = false;
                if (isLeft && node.right != null) {
                    helperR = true;
                    helper(i, node.right, false);
                } else if (!isLeft && node.left != null) {
                    helperL = true;
                    helper(i, node.left, true);
                }
                if (!helperR) {
                    helper(0, node.right, false);
                }
                if (!helperL) {
                    helper(0, node.left, true);
                }
            }
        }
    }
}
