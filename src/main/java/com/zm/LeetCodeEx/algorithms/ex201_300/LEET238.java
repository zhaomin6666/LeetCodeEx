package com.zm.LeetCodeEx.algorithms.ex201_300;

import com.zm.LeetCodeEx.CommonFunctions;
import com.zm.LeetCodeEx.TreeNode;

import java.util.*;

/**
 * 238. 除自身以外数组的乘积
 * <p>
 * 给你一个长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,2,3,4]
 * 输出: [24,12,8,6]
 *  
 * <p>
 * 提示：题目数据保证数组之中任意元素的全部前缀元素和后缀（甚至是整个数组）的乘积都在 32 位整数范围内。
 * <p>
 * 说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
 * <p>
 * 进阶：
 * 你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）
 *
 * @author zm
 */
public class LEET238 {
    public static void main(String[] args) {
        LEET238 l238 = new LEET238();
        System.out.println(Arrays.toString(l238.new Solution().productExceptSelf(new int[]{1, 2, 3, 4})));

    }

    class Solution {
        public int[] productExceptSelf(int[] nums) {
            // n > 1
            int len = nums.length;
            if (len <= 1) {
                return nums;
            }
            int[] left = new int[len];
            int[] right = new int[len];
            int tempL = nums[0];
            left[0] = tempL;
            for (int i = 1; i < len; i++) {
                tempL *= nums[i];
                left[i] = tempL;
            }
            int tempR = nums[len - 1];
            right[len - 1] = tempR;
            for (int i = len - 2; i >= 0; i--) {
                tempR *= nums[i];
                right[i] = tempR;
            }
            int[] ret = new int[len];
            ret[0] = right[1];
            ret[len - 1] = left[len - 2];
            for (int i = 1; i < len - 1; i++) {
                ret[i] = left[i - 1] * right[i + 1];
            }
            return ret;
        }
    }

    /**
     * 直接在输出数组上进行一次从左边的遍历，一次从右边的遍历
     */
    class Solution2 {
        public int[] productExceptSelf(int[] nums) {
            int len = nums.length;
            int[] ret = new int[len];

            // answer[i] 表示索引 i 左侧所有元素的乘积
            // 因为索引为 '0' 的元素左侧没有元素， 所以 answer[0] = 1
            ret[0] = 1;
            for (int i = 1; i < len; i++) {
                ret[i] = nums[i - 1] * ret[i - 1];
            }

            // R 为右侧所有元素的乘积
            // 刚开始右边没有元素，所以 R = 1
            int right = 1;
            for (int i = len - 1; i >= 0; i--) {
                // 对于索引 i，左边的乘积为 answer[i]，右边的乘积为 R
                ret[i] = ret[i] * right;
                // R 需要包含右边所有的乘积，所以计算下一个结果时需要将当前值乘到 R 上
                right *= nums[i];
            }
            return ret;
        }
    }
}
