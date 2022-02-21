package com.zm.LeetCodeEx.lcci;

/**
 * 面试题 17.16.按摩师
 * <p>
 * 一个有名的按摩师会收到源源不断的预约请求，每个预约都可以选择接或不接。在每次预约服务之间要有休息时间，因此她不能接受相邻的预约。给定一个预约请求序列，替按摩师找到最优的预约集合（总预约时间最长），返回总的分钟数。
 * <p>
 * 注意：本题相对原题稍作改动
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入： [1,2,3,1]
 * 输出： 4
 * 解释： 选择 1 号预约和 3 号预约，总时长 = 1 + 3 = 4。
 * 示例 2：
 * <p>
 * 输入： [2,7,9,3,1]
 * 输出： 12
 * 解释： 选择 1 号预约、 3 号预约和 5 号预约，总时长 = 2 + 9 + 1 = 12。
 * 示例 3：
 * <p>
 * 输入： [2,1,4,5,3,1,1,3]
 * 输出： 12
 * 解释： 选择 1 号预约、 3 号预约、 5 号预约和 8 号预约，总时长 = 2 + 4 + 3 + 3 = 12。
 *
 * @author zm
 */
public class Lcci1716 {
    public static void main(String[] args) {
        Lcci1716 lcci1716 = new Lcci1716();
        System.out.println(lcci1716.new Solution().massage(new int[]{1, 2, 3, 1}));
        System.out.println(lcci1716.new Solution().massage(new int[]{2, 7, 9, 3, 1}));
        System.out.println(lcci1716.new Solution().massage(new int[]{2, 1, 4, 5, 3, 1, 1, 3}));
    }

    class Solution {
        public int massage(int[] nums) {

            int l = nums.length;
            if (l == 0) {
                return 0;
            }
            if (l == 1) {
                return nums[0];
            }
            if (l == 2) {
                return Math.max(nums[0], nums[1]);
            }
            if (l == 3) {
                return Math.max(nums[0] + nums[2], nums[1]);
            }
            int[] dp = new int[l];
            dp[0] = nums[0];
            dp[1] = Math.max(nums[0], nums[1]);
            dp[2] = Math.max(nums[0] + nums[2], nums[1]);
            for (int i = 3; i < l; i++) {
                dp[i] = Math.max(dp[i - 3], dp[i - 2]) + nums[i];
            }
            return Math.max(dp[l - 1], dp[l - 2]);
        }
    }
}
