package com.zm.LeetCodeEx.weekcontest.contest_233_20210321;

/**
 * 5696. 统计异或值在范围内的数对有多少
 * <p>
 * 给你一个整数数组 nums （下标 从 0 开始 计数）以及两个整数：low 和 high ，请返回 漂亮数对 的数目。
 * <p>
 * 漂亮数对 是一个形如 (i, j) 的数对，其中 0 <= i < j < nums.length 且 low <= (nums[i] XOR nums[j]) <= high 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,4,2,7], low = 2, high = 6
 * 输出：6
 * 解释：所有漂亮数对 (i, j) 列出如下：
 * - (0, 1): nums[0] XOR nums[1] = 5
 * - (0, 2): nums[0] XOR nums[2] = 3
 * - (0, 3): nums[0] XOR nums[3] = 6
 * - (1, 2): nums[1] XOR nums[2] = 6
 * - (1, 3): nums[1] XOR nums[3] = 3
 * - (2, 3): nums[2] XOR nums[3] = 5
 * 示例 2：
 * <p>
 * 输入：nums = [9,8,4,2,1], low = 5, high = 14
 * 输出：8
 * 解释：所有漂亮数对 (i, j) 列出如下：
 * - (0, 2): nums[0] XOR nums[2] = 13
 * - (0, 3): nums[0] XOR nums[3] = 11
 * - (0, 4): nums[0] XOR nums[4] = 8
 * - (1, 2): nums[1] XOR nums[2] = 12
 * - (1, 3): nums[1] XOR nums[3] = 10
 * - (1, 4): nums[1] XOR nums[4] = 9
 * - (2, 3): nums[2] XOR nums[3] = 6
 * - (2, 4): nums[2] XOR nums[4] = 5
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 2 * 104
 * 1 <= nums[i] <= 2 * 104
 * 1 <= low <= high <= 2 * 104
 */
public class LEET5696 {
    public static void main(String[] args) {
        LEET5696 leet5711 = new LEET5696();
        System.out.println(leet5711.new Solution().countPairs(new int[]{1, 4, 2, 7}, 2, 6));
        System.out.println(leet5711.new Solution().countPairs(new int[]{9, 8, 4, 2, 1}, 5, 14));
    }


    /**
     * 暴力解超时
     */
    class Solution {
        public int countPairs(int[] nums, int low, int high) {
            int len = nums.length;
            int cnt = 0;
            for (int i = 0; i < len; i++) {
                for (int j = i; j < len; j++) {
                    int xor = nums[i] ^ nums[j];
                    if (xor >= low && xor <= high) {
                        cnt++;
                    }
                }
            }
            return cnt;
        }
    }
}
