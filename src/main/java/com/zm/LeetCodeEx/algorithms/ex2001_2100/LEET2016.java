package com.zm.LeetCodeEx.algorithms.ex2001_2100;

import com.alibaba.fastjson.JSON;

/**
 * 2016. 增量元素之间的最大差值
 * 给你一个下标从 0 开始的整数数组 nums ，该数组的大小为 n ，请你计算 nums[j] - nums[i] 能求得的 最大差值 ，其中 0 <= i < j < n 且 nums[i] < nums[j] 。
 * <p>
 * 返回 最大差值 。如果不存在满足要求的 i 和 j ，返回 -1 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [7,1,5,4]
 * 输出：4
 * 解释：
 * 最大差值出现在 i = 1 且 j = 2 时，nums[j] - nums[i] = 5 - 1 = 4 。
 * 注意，尽管 i = 1 且 j = 0 时 ，nums[j] - nums[i] = 7 - 1 = 6 > 4 ，但 i > j 不满足题面要求，所以 6 不是有效的答案。
 * 示例 2：
 * <p>
 * 输入：nums = [9,4,3,2]
 * 输出：-1
 * 解释：
 * 不存在同时满足 i < j 和 nums[i] < nums[j] 这两个条件的 i, j 组合。
 * 示例 3：
 * <p>
 * 输入：nums = [1,5,2,10]
 * 输出：9
 * 解释：
 * 最大差值出现在 i = 0 且 j = 3 时，nums[j] - nums[i] = 10 - 1 = 9 。
 * <p>
 * 提示：
 * <p>
 * n == nums.length
 * 2 <= n <= 1000
 * 1 <= nums[i] <= 109
 *
 * @author zm
 */
public class LEET2016 {
    public static void main(String[] args) {
        System.out.println(JSON.toJSONString(new Solution().maximumDifference(new int[]{7, 1, 5, 4})));
        System.out.println(JSON.toJSONString(new Solution().maximumDifference(new int[]{9, 4, 3, 2})));
        System.out.println(JSON.toJSONString(new Solution().maximumDifference(new int[]{1, 5, 2, 10})));
    }

    /**
     * 遍历的同时记录最小值，则最小值的小标i能保证小于当前值的下表j
     */
    static class Solution {
        public int maximumDifference(int[] nums) {
            int result = -1;
            int min = Integer.MAX_VALUE;
            for (int num : nums) {
                int interval = num - min;
                if (interval > 0) {
                    result = Math.max(result, num - min);
                }
                min = Math.min(min, num);
            }
            return result;
        }
    }
}
