package com.zm.LeetCodeEx.weekcontest.contest_190_20200524;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;

/**
 * 周赛 2020年5月24日
 * <p>
 * 5419. 两个子序列的最大点积
 * <p>
 * 给你两个数组 nums1 和 nums2 。
 * <p>
 * 请你返回 nums1 和 nums2 中两个长度相同的 非空 子序列的最大点积。
 * <p>
 * 数组的非空子序列是通过删除原数组中某些元素（可能一个也不删除）后剩余数字组成的序列，但不能改变数字间相对顺序。比方说，[2,3,5] 是 [1,2,3,4,5] 的一个子序列而 [1,5,3] 不是。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums1 = [2,1,-2,5], nums2 = [3,0,-6]
 * 输出：18
 * 解释：从 nums1 中得到子序列 [2,-2] ，从 nums2 中得到子序列 [3,-6] 。
 * 它们的点积为 (2*3 + (-2)*(-6)) = 18 。
 * 示例 2：
 * <p>
 * 输入：nums1 = [3,-2], nums2 = [2,-6,7]
 * 输出：21
 * 解释：从 nums1 中得到子序列 [3] ，从 nums2 中得到子序列 [7] 。
 * 它们的点积为 (3*7) = 21 。
 * 示例 3：
 * <p>
 * 输入：nums1 = [-1,-1], nums2 = [1,1]
 * 输出：-1
 * 解释：从 nums1 中得到子序列 [-1] ，从 nums2 中得到子序列 [1] 。
 * 它们的点积为 -1 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums1.length, nums2.length <= 500
 * -1000 <= nums1[i], nums2[i] <= 100
 * <p>
 * <p>
 * 点积：
 * <p>
 * 定义 a = [a1, a2,…, an] 和 b = [b1, b2,…, bn] 的点积为：
 * <p>
 * \mathbf{a}\cdot \mathbf{b} = \sum_{i=1}^n a_ib_i = a_1b_1 + a_2b_2 + \cdots + a_nb_n
 * <p>
 * 这里的 Σ 指示总和符号。
 *
 * @author zm
 */
public class LEET5419 {
    public static void main(String[] args) {
        LEET5419 l5419 = new LEET5419();
        System.out.println(JSON.toJSONString(l5419.new Solution().maxDotProduct(new int[]{2, 1, -2, 5}, new int[]{3, 0, -6})));
        System.out.println(JSON.toJSONString(l5419.new Solution().maxDotProduct(new int[]{3, -2}, new int[]{2, -6, 7})));
        System.out.println(JSON.toJSONString(l5419.new Solution().maxDotProduct(new int[]{-1, -1}, new int[]{1, 1})));
        System.out.println(JSON.toJSONString(l5419.new Solution().maxDotProduct(
                new int[]{-3, -8, 3, -10, 1, 3, 9}, new int[]{9, 2, 3, 7, -9, 1, -8, 5, -1, -1})));
    }

    class Solution {
        public int maxDotProduct(int[] nums1, int[] nums2) {
            int l1 = nums1.length;
            int l2 = nums2.length;
            int[][] dp = new int[l1][l2];
            int tm1 = Integer.MIN_VALUE;
            int nums11 = nums1[0];
            for (int i = 0; i < l2; i++) {
                tm1 = Math.max(tm1, nums11 * nums2[i]);
                dp[0][i] = tm1;
            }
            int tm2 = Integer.MIN_VALUE;
            int nums21 = nums2[0];
            for (int i = 0; i < l1; i++) {
                tm2 = Math.max(tm2, nums21 * nums1[i]);
                dp[i][0] = tm2;
            }
            for (int i = 1; i < l1; i++) {
                for (int j = 1; j < l2; j++) {
                    int mm = dp[i - 1][j];
                    for (int k = 1; k <= j; k++) {
                        mm = Math.max(mm, nums1[i] * nums2[k] + (dp[i - 1][k - 1] > 0 ? dp[i - 1][k - 1] : 0));
                    }
                    mm = Math.max(mm, nums1[i] * nums2[0]);
                    dp[i][j] = mm;
                }
            }
            for (int i = 0; i < dp.length; i++) {
                System.out.println(Arrays.toString(dp[i]));
            }
            return dp[l1 - 1][l2 - 1];
        }
    }
}
