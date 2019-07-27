package com.zm.LeetCodeEx;

import com.alibaba.fastjson.JSON;

import java.util.*;

/**
 * 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 * <p>
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 * <p>
 * 必须原地修改，只允许使用额外常数空间。
 * <p>
 * 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 *
 * @author zm
 */
public class LEET031 {
    public static void main(String[] args) {
        LEET031 l031 = new LEET031();
        int[] nums = {1, 5, 1};
        l031.nextPermutation2(nums);
        System.out.println(JSON.toJSONString(nums));
    }

    public void nextPermutation(int[] nums) {
        boolean hasNextPermutation = false;
        int index = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[nums.length - i - 1] > nums[nums.length - i - 2]) {
                hasNextPermutation = true;
                index = nums.length - i - 1;
                break;
            }
        }
        if (hasNextPermutation) {
            // 从index开始找比index-1的数大的数中最小的数
            int min = Integer.MAX_VALUE;
            int minIndex = -1;
            for (int i = index; i < nums.length; i++) {
                if (nums[i] > nums[index - 1] && nums[i] < min) {
                    min = nums[i];
                    minIndex = i;
                }
            }
            // 交换最小的数和index-1的数
            int temp = nums[index - 1];
            nums[index - 1] = nums[minIndex];
            nums[minIndex] = temp;
            // 从index开始排序
            Arrays.sort(nums, index, nums.length);
        } else {
            for (int i = 0; i < nums.length / 2; i++) {
                int a = nums[i];
                nums[i] = nums[nums.length - i - 1];
                nums[nums.length - i - 1] = a;
            }
        }
    }

    /**
     * 省去上面一个方法中的排序，直接将后面部分翻转即可
     *
     * @param nums
     */
    public void nextPermutation2(int[] nums) {
        boolean hasNextPermutation = false;
        int index = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[nums.length - i - 1] > nums[nums.length - i - 2]) {
                hasNextPermutation = true;
                index = nums.length - i - 1;
                break;
            }
        }
        if (hasNextPermutation) {
            for (int i = index; i < nums.length; i++) {
                if (nums[i] <= nums[index - 1]) {
                    swap(nums, i - 1, index - 1);
                    break;
                } else if (i == nums.length - 1) {
                    swap(nums, i, index - 1);
                }
            }
        }
        reverse(nums, index);
    }

    private void reverse(int[] nums, int start) {
        int i = start, j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
