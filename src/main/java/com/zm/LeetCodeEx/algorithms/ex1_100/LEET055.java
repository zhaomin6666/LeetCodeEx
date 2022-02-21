package com.zm.LeetCodeEx.algorithms.ex1_100;

/**
 * 55. 跳跃游戏
 * <p>
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * <p>
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * <p>
 * 判断你是否能够到达最后一个位置。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [2,3,1,1,4] <br>
 * 输出: true<br>
 * 解释: 我们可以先跳 1 步，从位置 0 到达 位置 1, 然后再从位置 1 跳 3 步到达最后一个位置。<br>
 * <p>
 * 示例 2:
 * <p>
 * 输入: [3,2,1,0,4]<br>
 * 输出: false<br>
 * 解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
 *
 * @author zm
 */
public class LEET055 {
    public static void main(String[] args) {
        LEET055 l055 = new LEET055();
        int[] nums = {2, 3, 1, 1, 4};
        int[] nums2 = {3, 2, 1, 0, 4};
        System.out.println(l055.canJump(nums));
        System.out.println(l055.canJump(nums2));
    }

    /**
     * 参考跳跃游戏Ⅱ
     *
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        if (nums.length == 1) {
            return true;
        }
        int reach = 0;
        int nextreach = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (reach < i) {
                return false;
            }
            nextreach = Math.max(i + nums[i], nextreach);
            if (nextreach >= nums.length - 1) {
                return true;
            }
            if (i == reach) {
                reach = nextreach;
            }
        }
        if (reach >= nums.length - 1) {
            return true;
        }
        return false;
    }

    /**
     * 当一个0的前面所有数都跳不到这个0的后面，那么就跳不出这个数组
     */
    class Solution2 {
        public boolean canJump(int[] nums) {
            for (int i = 0; i < nums.length - 1; i++) {
                if (nums[i] == 0) {
                    if (passZero(nums, i)) {
                        continue;
                    } else {
                        return false;
                    }
                }
            }
            return true;
        }

        //判断是否能跳出当前0
        private boolean passZero(int[] nums, int index) {
            for (int i = index; i >= 0; i--) {
                if (nums[i] > (index - i)) {
                    return true;
                }
            }
            return false;
        }

    }
}
