package com.zm.LeetCodeEx;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * 53. 最大子序和
 * <p>
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * <p>
 * 示例:
 * <p>
 * 输入: [-2,1,-3,4,-1,2,1,-5,4], 输出: 6
 * <p>
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * <p>
 * 进阶:
 * <p>
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 *
 * @author zm
 */
public class LEET053 {
    public static void main(String[] args) {
        LEET053 l053 = new LEET053();
        int[] nums1 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int[] nums2 = new int[100];
        for (int i = 0; i < nums2.length; i++) {
            nums2[i] = new Random().nextInt(100);
        }
        long start = System.currentTimeMillis();
        System.out.println(l053.maxSubArray(nums2));
        System.out.println("1 spend time:" + (System.currentTimeMillis() - start) + "ms");
        start = System.currentTimeMillis();
        System.out.println(l053.maxSubArray2(nums2));
        System.out.println("2 spend time:" + (System.currentTimeMillis() - start) + "ms");
        start = System.currentTimeMillis();
        System.out.println(l053.maxSubArray3(nums2));
        System.out.println("3 spend time:" + (System.currentTimeMillis() - start) + "ms");

    }


    /**
     * 动态规划
     * dp[i]=max{nums[i],dp[i−1]+nums[i]}
     * sum代表dp[i-1]，如果dp[i-1]<0，max{nums[i],dp[i−1]+nums[i]}就是取nums[i]
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int maxSum = Integer.MIN_VALUE;
        int sum = 0;
        int right = 0;
        while (right < nums.length) {
            sum += nums[right];
            if (maxSum < sum) {
                maxSum = sum;
            }
            if (sum <= 0) {
                sum = 0;
            }
            right++;
        }
        return maxSum;
    }

    /**
     * 分治法
     * 把一个数组分为L,R两个部分，最大连续子数组只有三种可能
     * (1)全部在L中；
     * (2)全部在R中；
     * (3)LR中都有。
     *
     * @param nums
     * @return
     */
    public int maxSubArray2(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }
        return maxSubArraySum(nums, 0, len - 1);
    }

    /**
     * 第三种情况的最大值
     *
     * @param nums
     * @param left
     * @param mid
     * @param right
     * @return
     */
    private int maxCrossingSum(int[] nums, int left, int mid, int right) {
        // 一定会包含 nums[mid] 这个元素
        int sum = 0;
        int leftSum = Integer.MIN_VALUE;
        // 左半边包含 nums[mid] 元素，最多可以到什么地方
        // 走到最边界，看看最值是什么
        // 计算以 mid 结尾的最大的子数组的和
        for (int i = mid; i >= left; i--) {
            sum += nums[i];
            if (sum > leftSum) {
                leftSum = sum;
            }
        }
        sum = 0;
        int rightSum = Integer.MIN_VALUE;
        // 右半边不包含 nums[mid] 元素，最多可以到什么地方
        // 计算以 mid+1 开始的最大的子数组的和
        for (int i = mid + 1; i <= right; i++) {
            sum += nums[i];
            if (sum > rightSum) {
                rightSum = sum;
            }
        }
        return leftSum + rightSum;

    }

    private int maxSubArraySum(int[] nums, int left, int right) {
        if (left == right) {
            return nums[left];
        }
        int mid = (left + right) >>> 1;
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return max3(maxSubArraySum(nums, left, mid),
                maxSubArraySum(nums, mid + 1, right),
                maxCrossingSum(nums, left, mid, right));
    }

    private int max3(int num1, int num2, int num3) {
        return Math.max(num1, Math.max(num2, num3));
    }


    /**
     * 分治法尝试使用fork-join多线程处理
     * 结论
     *
     * @param nums
     * @return
     */
    public int maxSubArray3(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }
        ForkJoinPool pool = new ForkJoinPool();
        MaxSubArraySumTask maxSubArray = new MaxSubArraySumTask(nums, 0, len - 1);
        return pool.invoke(maxSubArray);
    }

    private static class MaxSubArraySumTask extends RecursiveTask<Integer> {

        private int[] nums;
        private int left;
        private int right;
        private LEET053 leet053 = new LEET053();

        public MaxSubArraySumTask(int[] nums, int left, int right) {
            this.nums = nums;
            this.left = left;
            this.right = right;
        }

        @Override
        protected Integer compute() {
            if (left == right) {
                return nums[left];
            }
            int mid = (left + right) >>> 1;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            MaxSubArraySumTask leftTask = new MaxSubArraySumTask(nums, left, mid);
            MaxSubArraySumTask rightTask = new MaxSubArraySumTask(nums, mid + 1, right);
            MaxCrossingSumTask crossingTask = new MaxCrossingSumTask(nums, left, mid, right);
            invokeAll(leftTask, rightTask, crossingTask);
            int leftResult = leftTask.join();
            int rightResult = rightTask.join();
            int crossingResult = crossingTask.join();
            return leet053.max3(leftResult, rightResult, crossingResult);
        }
    }

    private static class MaxCrossingSumTask extends RecursiveTask<Integer> {
        private int[] nums;
        private int left;
        private int right;
        private int mid;

        public MaxCrossingSumTask(int[] nums, int left, int mid, int right) {
            this.nums = nums;
            this.left = left;
            this.right = right;
            this.mid = mid;
        }

        @Override
        protected Integer compute() {
            // 一定会包含 nums[mid] 这个元素
            int sum = 0;
            int leftSum = Integer.MIN_VALUE;
            // 左半边包含 nums[mid] 元素，最多可以到什么地方
            // 走到最边界，看看最值是什么
            // 计算以 mid 结尾的最大的子数组的和
            for (int i = mid; i >= left; i--) {
                sum += nums[i];
                if (sum > leftSum) {
                    leftSum = sum;
                }
            }
            sum = 0;
            int rightSum = Integer.MIN_VALUE;
            // 右半边不包含 nums[mid] 元素，最多可以到什么地方
            // 计算以 mid+1 开始的最大的子数组的和
            for (int i = mid + 1; i <= right; i++) {
                sum += nums[i];
                if (sum > rightSum) {
                    rightSum = sum;
                }
            }
            return leftSum + rightSum;
        }

    }
}
