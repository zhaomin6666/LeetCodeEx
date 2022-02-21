package com.zm.LeetCodeEx.weekcontest.contest_191_20200531;

import java.util.Arrays;

/**
 * 周赛 2020年5月31日
 * <p>
 * 1464. 数组中两元素的最大乘积
 * <p>
 * 给你一个整数数组 nums，请你选择数组的两个不同下标 i 和 j，使 (nums[i]-1)*(nums[j]-1) 取得最大值。
 * <p>
 * 请你计算并返回该式的最大值。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [3,4,5,2]
 * 输出：12
 * 解释：如果选择下标 i=1 和 j=2（下标从 0 开始），则可以获得最大值，(nums[1]-1)*(nums[2]-1) = (4-1)*(5-1) = 3*4 = 12 。
 * 示例 2：
 * <p>
 * 输入：nums = [1,5,4,5]
 * 输出：16
 * 解释：选择下标 i=1 和 j=3（下标从 0 开始），则可以获得最大值 (5-1)*(5-1) = 16 。
 * 示例 3：
 * <p>
 * 输入：nums = [3,7]
 * 输出：12
 *
 * @author zm
 */
public class LEET1464 {
    public static void main(String[] args) {
        LEET1464 l1464 = new LEET1464();
        System.out.println(l1464.new Solution().maxProduct(new int[]{3, 4, 5, 2}));
    }

    class Solution {
        public int maxProduct(int[] nums) {
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < nums.length; i++) {
                int numi = nums[i] - 1;
                for (int j = i + 1; j < nums.length; j++) {
                    max = Math.max(max, numi * (nums[j] - 1));
                }
            }
            return max;
        }
    }

    class Solution2 {
        public int maxProduct(int[] nums) {
            int len = nums.length;
            Arrays.sort(nums);
            return (nums[--len] - 1) * (nums[--len] - 1);
        }
    }
}
