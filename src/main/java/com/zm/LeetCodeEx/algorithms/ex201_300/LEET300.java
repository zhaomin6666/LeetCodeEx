package com.zm.LeetCodeEx.algorithms.ex201_300;

/**
 * 300. 最长上升子序列
 * <p>
 * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 * <p>
 * 示例:
 * <p>
 * 输入: [10,9,2,5,3,7,101,18]
 * 输出: 4
 * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
 * 说明:
 * <p>
 * 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
 * 你算法的时间复杂度应该为 O(n2) 。
 * 进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?
 *
 * @author zm
 */
public class LEET300 {
    public static void main(String[] args) {
        LEET300 l300 = new LEET300();
        System.out.println(l300.new Solution2().lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
        System.out.println(l300.new Solution2().lengthOfLIS(new int[]{3, 5, 6, 2, 5, 4, 19, 5, 6, 7, 12}));
        System.out.println(l300.new Solution2().lengthOfLIS(new int[]{0}));
    }

    /**
     * 动态规划，寻找当前数字之前的比当前数字小的数字的最大dp值
     * dp[n] = max{dp[i]}  0<=i<=n-1 且 nums[i]<nums[n]
     */
    class Solution {
        public int lengthOfLIS(int[] nums) {
            int l = nums.length;
            if (l < 2) {
                return l;
            }
            int[] dp = new int[l];
            dp[0] = 1;
            int retMax = 1;
            for (int i = 1; i < l; i++) {
                int maxInt = 0;
                for (int j = 0; j < i; j++) {
                    if (nums[j] < nums[i] && dp[j] > maxInt) {
                        maxInt = dp[j];
                    }
                }
                dp[i] = maxInt + 1;
                retMax = maxInt + 1 > retMax ? maxInt + 1 : retMax;
            }
            return retMax;
        }
    }

    /**
     * 动态规划，寻找当前数字之前的比当前数字小的数字的最大dp值
     * dp[n] = max{dp[i]}  0<=i<=n-1 且 nums[i]<nums[n]
     */
    class Solution2 {
        public int lengthOfLIS(int[] nums) {
            int l = nums.length;
            if (l < 2) {
                return l;
            }
            int[] dp = new int[l];
            int dpIndex = 0;
            dp[dpIndex] = nums[0];
            loop1:
            for (int i = 1; i < l; i++) {
                if (nums[i] > dp[dpIndex]) {
                    dp[++dpIndex] = nums[i];
                } else if (nums[i] < dp[dpIndex]) {
                    int left = 0;
                    int right = dpIndex;
                    while (left <= right) {
                        int mid = (left + right) >>> 1;
                        if (dp[mid] == nums[i]) {
                            continue loop1;
                        } else if (dp[mid] > nums[i]) {
                            right = mid - 1;
                        } else {
                            left = mid + 1;
                        }
                    }
                    dp[left] = nums[i];
                }
            }
            return dpIndex + 1;
        }
    }
}
