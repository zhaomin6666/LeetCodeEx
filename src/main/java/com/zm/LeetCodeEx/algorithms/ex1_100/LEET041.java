package com.zm.LeetCodeEx.algorithms.ex1_100;

/**
 * 41. 缺失的第一个正数
 * 给定一个未排序的整数数组，找出其中没有出现的最小的正整数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,0]
 * 输出: 3
 * 示例 2:
 * <p>
 * 输入: [3,4,-1,1]
 * 输出: 2
 * 示例 3:
 * <p>
 * 输入: [7,8,9,11,12]
 * 输出: 1
 * 说明:
 * <p>
 * 你的算法的时间复杂度应为O(n)，并且只能使用常数级别的空间。
 * <p>
 *
 * @author zm
 */
public class LEET041 {
    public static void main(String[] args) {
        LEET041 l041 = new LEET041();
        int[] nums = {2, 1};
        System.out.println(l041.firstMissingPositive(nums));

    }

    public int firstMissingPositive(int[] nums) {
        int spare;
        int temp;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0 && nums[i] <= nums.length && nums[nums[i] - 1] != nums[i]) {
                spare = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                while (spare > 0 && spare <= nums.length && nums[spare - 1] != spare) {
                    temp = spare;
                    spare = nums[temp - 1];
                    nums[temp - 1] = temp;
                }
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return nums.length + 1;
    }
}
