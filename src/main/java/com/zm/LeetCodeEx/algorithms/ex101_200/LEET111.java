package com.zm.LeetCodeEx.algorithms.ex101_200;

import com.zm.LeetCodeEx.CommonFunctions;
import com.zm.LeetCodeEx.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 111. 二叉树的最小深度
 * <p>
 * 给定一个二叉树，找出其最小深度。
 * <p>
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例:
 * <p>
 * 给定二叉树 [3,9,20,null,null,15,7],
 * <p>
 * ....3
 * .../.\
 * ..9..20
 * ..../..\
 * ...15...7
 * <p>
 * 返回它的最小深度  2.
 *
 * @author zm
 */
public class LEET111 {
    public static void main(String[] args) {
        LEET111 l111 = new LEET111();
        System.out.println(
                l111.new Solution().minDepth(CommonFunctions.stringToTreeNode("[3,9,20,null,null,15,7]")));
        System.out.println(
                l111.new Solution().minDepth(CommonFunctions.stringToTreeNode("[3,9,20,1,2,15,7]")));
        System.out.println(
                l111.new Solution().minDepth(CommonFunctions.stringToTreeNode("[3,2]")));
        System.out.println(
                l111.new Solution().minDepth(CommonFunctions.stringToTreeNode("[3]")));
        System.out.println(
                l111.new Solution().minDepth(CommonFunctions.stringToTreeNode("[]")));
    }

    class Solution {
        public int minDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            int ret = 1;
            while (!queue.isEmpty()) {
                int curSize = queue.size();
                for (int i = 0; i < curSize; i++) {
                    TreeNode temp = queue.poll();
                    if (temp.left == null && temp.right == null) {
                        return ret;
                    }
                    if (temp.left != null) {
                        queue.add(temp.left);
                    }
                    if (temp.right != null) {
                        queue.add(temp.right);
                    }
                }
                ret++;
            }
            return ret;
        }
    }
}
