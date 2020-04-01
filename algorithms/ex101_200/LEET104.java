package com.zm.LeetCodeEx.algorithms.ex101_200;

import com.alibaba.fastjson.JSON;
import com.zm.LeetCodeEx.CommonFunctions;
import com.zm.LeetCodeEx.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 103. 二叉树的锯齿形层次遍历
 * <p>
 * 给定一个二叉树，找出其最大深度。
 * <p>
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例：
 * 二叉树：[3,9,20,null,null,15,7],
 * <p>
 * .....3
 * ..../.\
 * ...9..20
 * ...../..\
 * ....15...7
 * 返回它的最大深度 3 。
 *
 * @author zm
 */
public class LEET104 {
    public static void main(String[] args) {
        LEET104 l104 = new LEET104();
        System.out.println(JSON.toJSONString(l104.new Solution().maxDepth(
                CommonFunctions.stringToTreeNode("[]"))));
        System.out.println(JSON.toJSONString(l104.new Solution().maxDepth(
                CommonFunctions.stringToTreeNode("[1,2]"))));
        System.out.println(JSON.toJSONString(l104.new Solution().maxDepth(
                CommonFunctions.stringToTreeNode("[1,2,3,4,5,6,7,8,9,10,11,12]"))));
        System.out.println(JSON.toJSONString(l104.new Solution().maxDepth(
                CommonFunctions.stringToTreeNode("[3,9,20,null,null,15,7]"))));

    }

    /**
     * DFS
     */
    class Solution {
        private int depth = 0;

        public int maxDepth(TreeNode root) {
            helper(root, 0);
            return depth;
        }

        private void helper(TreeNode node, int i) {
            if (node != null) {
                depth = Math.max(++i, depth);
                helper(node.right, i);
                helper(node.left, i);
            }
        }
    }

    /**
     * BFS
     */
    class Solution2 {
        public int maxDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }
            Queue<TreeNode> queue = new ArrayDeque<>();
            queue.add(root);
            int level = 0;
            while (!queue.isEmpty()) {
                level++;
                int levelCnt = queue.size();
                for (int i = 0; i < levelCnt; ++i) {
                    TreeNode node = queue.remove();
                    if (node.left != null) {
                        queue.add(node.left);
                    }
                    if (node.right != null) {
                        queue.add(node.right);
                    }
                }
            }
            return level;
        }
    }
}
