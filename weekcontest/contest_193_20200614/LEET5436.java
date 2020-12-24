package com.zm.LeetCodeEx.weekcontest.contest_193_20200614;

import java.util.*;

/**
 * 周赛 2020年6月14日
 * <p>
 * 5436. 一维数组的动态和
 * <p>
 * 给你一个数组 nums 。数组「动态和」的计算公式为：runningSum[i] = sum(nums[0]…nums[i]) 。
 * <p>
 * 请返回 nums 的动态和。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3,4]
 * 输出：[1,3,6,10]
 * 解释：动态和计算过程为 [1, 1+2, 1+2+3, 1+2+3+4] 。
 * 示例 2：
 * <p>
 * 输入：nums = [1,1,1,1,1]
 * 输出：[1,2,3,4,5]
 * 解释：动态和计算过程为 [1, 1+1, 1+1+1, 1+1+1+1, 1+1+1+1+1] 。
 * 示例 3：
 * <p>
 * 输入：nums = [3,1,2,10,1]
 * 输出：[3,4,6,16,17]
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 1000
 * -10^6 <= nums[i] <= 10^6
 *
 * @author zm
 */
public class LEET5436 {
    public static void main(String[] args) {
        LEET5436 l5436 = new LEET5436();
        System.out.println(Arrays.toString(l5436.new Solution().runningSum(new int[]{4, 3, 1, 1, 3, 3, 2})));
    }

    class Solution {
        public int[] runningSum(int[] nums) {
            int sum = 0;
            int[] ret = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
                ret[i] = sum;
            }
            return ret;
        }
    }
}
