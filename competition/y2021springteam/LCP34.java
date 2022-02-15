package com.zm.LeetCodeEx.competition.y2021springteam;

import com.zm.LeetCodeEx.CommonFunctions;
import com.zm.LeetCodeEx.TreeNode;

public class LCP34 {

    public static void main(String[] args) {
        System.out.println(new Solution().maxValue(CommonFunctions.stringToTreeNode("[5,2,3,4]"), 2));
        System.out.println(new Solution().maxValue(CommonFunctions.stringToTreeNode("[4,1,3,9,null,null,2]"), 2));
        System.out.println(new Solution().maxValue(CommonFunctions.stringToTreeNode("[8,1,3,9,9,9,null,9,5,6,8]"), 2));
    }

    /**
     * 树自底向上进行dp，dp[x]中的x表示当前节点的状态，值表示当前状态下的最优解
     * dp[0]表示当前节点没有涂
     * dp[1]表示当前节点涂了，但是下面两个叶子节点都没有涂
     * dp[n]表示当前节点涂了，共有n个连着的节点涂了
     */
    static class Solution {
        public int maxValue(TreeNode root, int k) {
            if (k == 0) {
                return 0;
            }
            int[] dp = doPaint(root, k);
            return getMax(dp);
        }

        private int[] doPaint(TreeNode root, int k) {
            int[] dp = new int[k + 1];
            // 当前节点不paint，可以直接取左节点的最大值和右节点的最大值
            int[] dpLeft = root.left == null ? new int[k + 1] : doPaint(root.left, k);
            int[] dpRight = root.right == null ? new int[k + 1] : doPaint(root.right, k);
            dp[0] = getMax(dpLeft) + getMax(dpRight);
            // 当前节点paint，那么左节点和右节点加起来最多只能k-1个
            for (int i = 0; i <= k - 1; i++) {
                int paintMax = 0;
                for (int j = 0; j <= i; j++) {
                    paintMax = Math.max(paintMax, dpLeft[j] + dpRight[i - j]);
                }
                dp[i + 1] = paintMax + root.val;
            }
            return dp;
        }

        private int getMax(int[] dp) {
            int max = 0;
            for (int i : dp) {
                max = Math.max(max, i);
            }
            return max;
        }
    }
}
