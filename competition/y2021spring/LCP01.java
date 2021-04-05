package com.zm.LeetCodeEx.competition.y2021spring;

import java.util.Arrays;

/**
 * 1. 采购方案
 * <p>
 * 小力将 N 个零件的报价存于数组 nums。小力预算为 target，假定小力仅购买两个零件，要求购买零件的花费不超过预算，请问他有多少种采购方案。
 * <p>
 * 注意：答案需要以 1e9 + 7 (1000000007) 为底取模，如：计算初始结果为：1000000008，请返回 1
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [2,5,3,5], target = 6
 * <p>
 * 输出：1
 * <p>
 * 解释：预算内仅能购买 nums[0] 与 nums[2]。
 * <p>
 * 示例 2：
 * <p>
 * 输入：nums = [2,2,1,9], target = 10
 * <p>
 * 输出：4
 * <p>
 * 解释：符合预算的采购方案如下：
 * nums[0] + nums[1] = 4
 * nums[0] + nums[2] = 3
 * nums[1] + nums[2] = 3
 * nums[2] + nums[3] = 10
 * <p>
 * 提示：
 * <p>
 * 2 <= nums.length <= 10^5
 * 1 <= nums[i], target <= 10^5
 */
public class LCP01 {

    public static void main(String[] args) {
        LCP01 LCP01 = new LCP01();
        System.out.println(LCP01.new Solution().purchasePlans(new int[]{2, 5, 3, 5}, 6));
        System.out.println(LCP01.new Solution().purchasePlans(new int[]{2, 2, 1, 9}, 10));
    }

    class Solution {
        public int purchasePlans(int[] nums, int target) {
            Arrays.sort(nums);
            int ret = 0;
            for (int i = 0; i < nums.length - 1; i++) {
                int left = i + 1;
                int right = nums.length - 1;

                int remain = target - nums[i];
                while (left <= right) {
                    int mid = (left + right) >>> 1;
                    if (nums[mid] <= remain) {
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
                if (right - i == 0) {
                    return ret;
                }
                ret += right - i;
                ret %= 100000009;
            }
            return ret;
        }
    }
}
