package com.zm.LeetCodeEx.algorithms.ex1_100;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;

/**
 * 45. 跳跃游戏 II
 * <p>
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * <p>
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * <p>
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 * <p>
 * 示例:
 * <p>
 * 输入: [2,3,1,1,4] 输出: 2
 * <p>
 * 解释: 跳到最后一个位置的最小跳跃数是 2。   从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 * <p>
 * 说明: 假设你总是可以到达数组的最后一个位置。
 *
 * @author zm
 */
public class LEET045 {
    public static void main(String[] args) {
        LEET045 l045 = new LEET045();
        int[] nums = {2, 3, 1, 1, 4, 2, 2};
        int[] nums2 = {8, 2, 4, 4, 4, 9, 5, 2, 5, 8, 8, 0, 8, 6, 9, 1, 1, 6, 3, 5, 1, 2, 6, 6, 0, 4, 8, 6, 0, 3, 2, 8,
                7, 6, 5, 1, 7, 0, 3, 4, 8, 3, 5, 9, 0, 4, 0, 1, 0, 5, 9, 2, 0, 7, 0, 2, 1, 0, 8, 2, 5, 1, 2, 3, 9, 7, 4,
                7, 0, 0, 1, 8, 5, 6, 7, 5, 1, 9, 9, 3, 5, 0, 7, 5};
        int[] nums3 = {5, 9, 3, 2, 1, 0, 2, 3, 3, 1, 0, 0};
        System.out.println(l045.jump3(nums3));
    }

    private int minStep = -1;
    private int curStep = 0;

    /**
     * 超时了
     *
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        if (nums.length <= 1) {
            minStep = curStep;
        } else {
            if (curStep != minStep) {
                for (int i = Math.min(nums[0], nums.length - 1); i > 0; i--) {
                    curStep++;
                    jump(Arrays.copyOfRange(nums, i, nums.length));
                    curStep--;
                }
            }
        }
        return minStep;
    }

    /**
     * 贪心算法
     * <p>
     * 每次跳跃使用nextreach记录能够到达的最远距离
     *
     * @param nums
     * @return
     */
    public int jump2(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        int reach = 0;
        int nextreach = nums[0];
        int step = 0;
        for (int i = 0; i < nums.length; i++) {
            nextreach = Math.max(i + nums[i], nextreach);
            if (nextreach >= nums.length - 1)
                return (step + 1);
            if (i == reach) {
                step++;
                reach = nextreach;
            }
        }
        return step;
    }

    /**
     * 动态规划
     * <p>
     * [2,3,1,1,4,2,2]
     * <p>
     * dp:
     * [0,1,1,2,2,3,3]
     * 可以兼容无法到达的情况
     * <p>
     * 不会超时，但是明显时间复杂度O(n2)相比贪心的O(n)差很多
     *
     * @param nums
     * @return
     */
    public int jump3(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        int[] dp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j <= i + nums[i]; j++) {
                if (dp[j] == 0) {
                    dp[j] = dp[i] + 1;
                    if (j == nums.length - 1) {
                        return dp[j];
                    }
                }
            }
        }
        return -1;
    }

}
