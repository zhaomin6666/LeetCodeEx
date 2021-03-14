package com.zm.LeetCodeEx.weekcontest.contest_232_20210314;

/**
 * 5704. 好子数组的最大分数
 * <p>
 * <p>
 * 给你一个整数数组nums（下标从 0 开始）和一个整数k。
 * <p>
 * 一个子数组 (i, j)的 分数定义为min(nums[i], nums[i+1], ..., nums[j]) * (j - i + 1)。一个好子数组的两个端点下标需要满足i <= k <= j。
 * <p>
 * 请你返回 好子数组的最大可能 分数。
 * <p>
 *
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,4,3,7,4,5], k = 3
 * 输出：15
 * 解释：最优子数组的左右端点下标是 (1, 5) ，分数为 min(4,3,7,4,5) * (5-1+1) = 3 * 5 = 15 。
 * 示例 2：
 * <p>
 * 输入：nums = [5,5,4,5,4,1,1,1], k = 0
 * 输出：20
 * 解释：最优子数组的左右端点下标是 (0, 4) ，分数为 min(5,5,4,5,4) * (4-0+1) = 4 * 5 = 20 。
 *
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 2 * 104
 * 0 <= k < nums.length
 */
public class LEET5704 {
    public static void main(String[] args) {
        LEET5704 leet5703 = new LEET5704();
        System.out.println(leet5703.new Solution().maximumScore(
                new int[]{1, 4, 3, 7, 4, 5}, 3));
        System.out.println(leet5703.new Solution().maximumScore(
                new int[]{5, 5, 4, 5, 4, 1, 1, 1}, 0));
    }


    /**
     * 双指针，向左右找当前的最小值
     */
    class Solution {
        public int maximumScore(int[] nums, int k) {
            int n = nums.length;
            int max = nums[k];
            int curMin = nums[k];
            int left = k - 1;
            int right = k + 1;
            int curLen = 1;
            while (left >= 0 || right < n) {
                if (left < 0) {
                    curMin = Math.min(curMin, nums[right]);
                    right++;
                } else if (right >= n) {
                    curMin = Math.min(curMin, nums[left]);
                    left--;
                } else {
                    if (nums[right] >= nums[left]) {
                        curMin = Math.min(curMin, nums[right]);
                        right++;
                    } else {
                        curMin = Math.min(curMin, nums[left]);
                        left--;
                    }
                }
                curLen++;
                max = Math.max(max, curMin * curLen);
            }
            return max;
        }
    }
}
