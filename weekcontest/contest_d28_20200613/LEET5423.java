package com.zm.LeetCodeEx.weekcontest.contest_d28_20200613;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 双周赛 2020年6月13日
 * <p>
 * 给你一个整数数组 arr 和一个整数值 target 。
 * <p>
 * 请你在 arr 中找 两个互不重叠的子数组 且它们的和都等于 target 。可能会有多种方案，请你返回满足要求的两个子数组长度和的 最小值 。
 * <p>
 * 请返回满足要求的最小长度和，如果无法找到这样的两个子数组，请返回 -1 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：arr = [3,2,2,4,3], target = 3
 * 输出：2
 * 解释：只有两个子数组和为 3 （[3] 和 [3]）。它们的长度和为 2 。
 * 示例 2：
 * <p>
 * 输入：arr = [7,3,4,7], target = 7
 * 输出：2
 * 解释：尽管我们有 3 个互不重叠的子数组和为 7 （[7], [3,4] 和 [7]），但我们会选择第一个和第三个子数组，因为它们的长度和 2 是最小值。
 * 示例 3：
 * <p>
 * 输入：arr = [4,3,2,6,2,3,4], target = 6
 * 输出：-1
 * 解释：我们只有一个和为 6 的子数组。
 * 示例 4：
 * <p>
 * 输入：arr = [5,5,4,4,5], target = 3
 * 输出：-1
 * 解释：我们无法找到和为 3 的子数组。
 * 示例 5：
 * <p>
 * 输入：arr = [3,1,1,1,5,1,2,1], target = 3
 * 输出：3
 * 解释：注意子数组 [1,2] 和 [2,1] 不能成为一个方案因为它们重叠了。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= arr.length <= 10^5
 * 1 <= arr[i] <= 1000
 * 1 <= target <= 10^8
 *
 * @author zm
 */
public class LEET5423 {
    public static void main(String[] args) {
        LEET5423 l5423 = new LEET5423();
        System.out.println(l5423.new Solution().minSumOfLengths(new int[]{3, 2, 2, 4, 3}, 3));
    }

    class Solution {
        public int minSumOfLengths(int[] arr, int target) {
            int hasOne = 0;
            int len = arr.length;
            PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o[1] - o[0]));
            int l = 0;
            int r = 0;
            int sum = 0;
            while (r < len) {
                sum += arr[r];
                if (sum > target) {
                    while (sum > target) {
                        sum -= arr[l++];
                    }
                }
                if (sum == target) {
                    if (l == r) {
                        hasOne++;
                        if (hasOne == 2) {
                            return 2;
                        }
                    }
                    priorityQueue.add(new int[]{l, r});
                    sum -= arr[l++];
                    r++;
                } else {
                    r++;
                }
            }
            if (priorityQueue.isEmpty() || priorityQueue.size() == 1) {
                return -1;
            }
            int[] first = priorityQueue.poll();
            while (!priorityQueue.isEmpty()) {
                int[] second = priorityQueue.poll();
                if (first[0] > second[1] || first[1] < second[0]) {
                    return first[1] - first[0] + second[1] - second[0] + 2;
                }
            }
            return -1;
        }
    }
}
