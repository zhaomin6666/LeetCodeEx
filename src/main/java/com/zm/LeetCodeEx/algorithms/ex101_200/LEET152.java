package com.zm.LeetCodeEx.algorithms.ex101_200;

import java.util.Arrays;

/**
 * 152. 乘积最大子数组
 * <p>
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 * 示例 2:
 * <p>
 * 输入: [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 *
 * @author zm
 */
public class LEET152 {
    public static void main(String[] args) {
        LEET152 l152 = new LEET152();
        System.out.println(l152.new Solution3().maxProduct(new int[]{2, 3, -2, 4}));
        System.out.println(l152.new Solution3().maxProduct(new int[]{-2, 0, -1}));
        System.out.println(l152.new Solution3().maxProduct(new int[]{2, 3, -2, 4, -1, -2, -1}));
    }

    /**
     * 暴力解法
     */
    class Solution {
        public int maxProduct(int[] nums) {
            if (nums.length == 0) {
                return 0;
            }
            int ret = nums[0];
            for (int i = 0; i < nums.length; i++) {
                int mul = 1;
                for (int j = i; j < nums.length; j++) {
                    mul *= nums[j];
                    ret = Math.max(ret, mul);
                }
            }
            return ret;
        }
    }

    /**
     * dp因为有正负值，所以用两个dp数组，一个保存最大的正数，一个保存可能的最小的负数
     * 每次的dp值可能是正数*当前正数或负数*当前负数或当前数自己
     */
    class Solution2 {
        public int maxProduct(int[] nums) {
            if (nums.length == 0) {
                return 0;
            }
            int[] dpMax = Arrays.copyOf(nums, nums.length);
            int[] dpMin = Arrays.copyOf(nums, nums.length);
            for (int i = 1; i < nums.length; i++) {
                dpMax[i] = Math.max(Math.max(dpMax[i - 1] * nums[i], nums[i]), dpMin[i - 1] * nums[i]);
                dpMin[i] = Math.min(Math.min(dpMin[i - 1] * nums[i], nums[i]), dpMax[i - 1] * nums[i]);
            }
            int max = dpMax[0];
            for (int t : dpMax) {
                max = Math.max(t, max);
            }
            return max;
        }
    }

    /**
     * 优化
     */
    class Solution3 {
        public int maxProduct(int[] nums) {
            if (nums.length == 0) {
                return 0;
            }
            int dpMax = nums[0];
            int dpMin = nums[0];
            int ret = nums[0];
            for (int i = 1; i < nums.length; i++) {
                int t = nums[i];
                int preMax = dpMax;
                dpMax = Math.max(Math.max(preMax * t, t), dpMin * t);
                dpMin = Math.min(Math.min(dpMin * t, t), preMax * t);
                ret = Math.max(dpMax, ret);
            }
            return ret;
        }
    }
}
