package com.zm.LeetCodeEx.weekcontest.contest_190_20200524;

import com.zm.LeetCodeEx.CommonFunctions;
import com.zm.LeetCodeEx.TreeNode;

/**
 * 周赛 2020年5月24日
 * <p>
 * 5418. 二叉树中的伪回文路径
 * <p>
 * 给你一棵二叉树，每个节点的值为 1 到 9 。我们称二叉树中的一条路径是 「伪回文」的，当它满足：路径经过的所有节点值的排列中，存在一个回文序列。
 * <p>
 * 请你返回从根到叶子节点的所有路径中 伪回文 路径的数目。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：root = [2,3,1,3,1,null,1]
 * 输出：2
 * 解释：上图为给定的二叉树。总共有 3 条从根到叶子的路径：红色路径 [2,3,3] ，绿色路径 [2,1,1] 和路径 [2,3,1] 。
 * 在这些路径中，只有红色和绿色的路径是伪回文路径，因为红色路径 [2,3,3] 存在回文排列 [3,2,3] ，绿色路径 [2,1,1] 存在回文排列 [1,2,1] 。
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：root = [2,1,1,1,3,null,null,null,null,null,1]
 * 输出：1
 * 解释：上图为给定二叉树。总共有 3 条从根到叶子的路径：绿色路径 [2,1,1] ，路径 [2,1,3,1] 和路径 [2,1] 。
 * 这些路径中只有绿色路径是伪回文路径，因为 [2,1,1] 存在回文排列 [1,2,1] 。
 * 示例 3：
 * <p>
 * 输入：root = [9]
 * 输出：1
 *  
 * <p>
 * 提示：
 * <p>
 * 给定二叉树的节点数目在 1 到 10^5 之间。
 * 节点值在 1 到 9 之间。
 *
 * @author zm
 */
public class LEET5418 {
    public static void main(String[] args) {
        LEET5418 l5418 = new LEET5418();
        System.out.println(l5418.new Solution().pseudoPalindromicPaths(CommonFunctions.stringToTreeNode("[2,3,1,3,1,null,1]")));
    }

    class Solution {
        int ret = 0;

        public int pseudoPalindromicPaths(TreeNode root) {
            int[] cnt = new int[10];
            dfs(root, cnt);
            return ret;
        }

        public void dfs(TreeNode node, int[] cnt) {
            cnt[node.val]++;
            if (node.left == null && node.right == null) {
                ret += check(cnt);
            }
            else {
                if (node.left != null) {
                    dfs(node.left, cnt);
                }
                if (node.right != null) {
                    dfs(node.right, cnt);
                }
            }
            cnt[node.val]--;
        }

        public int check(int[] cnt) {
            boolean has = false;
            for (int i : cnt) {
                if (i % 2 == 1) {
                    if (has) {
                        return 0;
                    } else {
                        has = true;
                    }
                }
            }
            return 1;
        }
    }
}
