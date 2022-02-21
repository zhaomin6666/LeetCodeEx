package com.zm.LeetCodeEx.algorithms.ex1001_1100;

/**
 * 1014. 最佳观光组合
 * <p>
 * 给定正整数数组 A，A[i] 表示第 i 个观光景点的评分，并且两个景点 i 和 j 之间的距离为 j - i。
 * <p>
 * 一对景点（i < j）组成的观光组合的得分为（A[i] + A[j] + i - j）：景点的评分之和减去它们两者之间的距离。
 * <p>
 * 返回一对观光景点能取得的最高分。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：[8,1,5,2,6]
 * 输出：11
 * 解释：i = 0, j = 2, A[i] + A[j] + i - j = 8 + 5 + 0 - 2 = 11
 *  
 * <p>
 * 提示：
 * <p>
 * 2 <= A.length <= 50000
 * 1 <= A[i] <= 1000
 *
 * @author zm
 */
public class LEET1014 {
    public static void main(String[] args) {
        LEET1014 l1014 = new LEET1014();
        System.out.println(l1014.new Solution().maxScoreSightseeingPair(new int[]{8, 1, 5, 2, 6}));
        System.out.println(l1014.new Solution().maxScoreSightseeingPair(new int[]{4, 7, 5, 8}));
    }

    /**
     * 显然O(n^2)会超时
     */
    class Solution {
        public int maxScoreSightseeingPair(int[] A) {
            int max = 0;
            int len = A.length;
            for (int i = 0; i < len - 1; i++) {
                int cur = A[i];
                int pre = A[i + 1];
                max = Math.max(max, cur + pre - 1);
                for (int j = i + 2; j < len; j++) {
                    int ccur = A[j];
                    if (ccur > pre + 1) {
                        max = Math.max(max, cur + ccur - j + i);
                    }
                    pre = ccur;
                }
            }
            return max;
        }
    }

    /**
     * 官方题解
     * <p>
     * 可以看到公式A[i] + A[j] + i - j可以拆分为A[i]+i和A[j]-j
     * 那么可以从i=0开始记录max{A[i]+i}，每走一步更新max{A[i]+i}同时更新ans
     */
    class Solution2 {
        public int maxScoreSightseeingPair(int[] A) {
            int ans = 0, mx = A[0] + 0;
            for (int j = 1; j < A.length; ++j) {
                ans = Math.max(ans, mx + A[j] - j);
                // 边遍历边维护
                mx = Math.max(mx, A[j] + j);
            }
            return ans;
        }
    }
}
