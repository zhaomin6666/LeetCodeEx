package com.zm.LeetCodeEx.weekcontest.contest_199_20200726;

import com.zm.LeetCodeEx.CommonFunctions;
import com.zm.LeetCodeEx.TreeNode;

/**
 * 1530. 好叶子节点对的数量
 * 给你二叉树的根节点 root 和一个整数 distance 。
 * <p>
 * 如果二叉树中两个 叶 节点之间的 最短路径长度 小于或者等于 distance ，那它们就可以构成一组 好叶子节点对 。
 * <p>
 * 返回树中 好叶子节点对的数量 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：root = [1,2,3,null,4], distance = 3
 * 输出：1
 * 解释：树的叶节点是 3 和 4 ，它们之间的最短路径的长度是 3 。这是唯一的好叶子节点对。
 * <p>
 * 示例 2：
 * 输入：root = [1,2,3,4,5,6,7], distance = 3
 * 输出：2
 * 解释：好叶子节点对为 [4,5] 和 [6,7] ，最短路径长度都是 2 。但是叶子节点对 [4,6] 不满足要求，因为它们之间的最短路径长度为 4 。
 * 示例 3：
 * <p>
 * 输入：root = [7,1,4,6,null,5,3,null,null,null,null,null,2], distance = 3
 * 输出：1
 * 解释：唯一的好叶子节点对是 [2,5] 。
 * 示例 4：
 * <p>
 * 输入：root = [100], distance = 1
 * 输出：0
 * 示例 5：
 * <p>
 * 输入：root = [1,1,1], distance = 2
 * 输出：1
 * <p>
 * <p>
 * 提示：
 * <p>
 * tree 的节点数在 [1, 2^10] 范围内。
 * 每个节点的值都在 [1, 100] 之间。
 * 1 <= distance <= 10
 */
public class LEET1530 {

    public static void main(String[] args) {
        LEET1530 leet5453 = new LEET1530();
        System.out.println(leet5453.new Solution().countPairs(
                CommonFunctions.stringToTreeNode("[1,2,3,4,5,6,7]"), 3));

    }

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */
    class Solution {
        int ret = 0;
        int dis = 0;

        public int countPairs(TreeNode root, int distance) {
            dis = distance;
            doCountPairs(root);
            return ret;
        }

        public int[] doCountPairs(TreeNode node) {
            if (node.left == null && node.right == null) {
                return new int[]{1};
            }
            // 只有一边的情况处理
            if (node.left == null) {
                int[] right = doCountPairs(node.right);
                for (int i = 0; i < right.length; i++) {
                    right[i]++;
                }
                return right;
            }
            if (node.right == null) {
                int[] left = doCountPairs(node.left);
                for (int i = 0; i < left.length; i++) {
                    left[i]++;
                }
                return left;
            }
            // 查找匹配对
            int[] right = doCountPairs(node.right);
            int[] left = doCountPairs(node.left);
            for (int aRight : right) {
                for (int aLeft : left) {
                    if (aRight + aLeft <= dis) {
                        ret++;
                    }
                }
            }
            // 把左右子节点统计在一起向上返回
            int[] retArr = new int[right.length + left.length];
            for (int i = 0; i < right.length; i++) {
                retArr[i] = right[i] + 1;
            }
            for (int j = 0; j < left.length; j++) {
                retArr[j + right.length] = left[j] + 1;
            }
            return retArr;
        }
    }
}
