package com.zm.LeetCodeEx.weekcontest;

/**
 * 周赛 2020年3月29日 5368. 找出数组中的幸运数
 * <p>
 * 在整数数组中，如果一个整数的出现频次和它的数值大小相等，我们就称这个整数为「幸运数」。
 * <p>
 * 给你一个整数数组 arr，请你从中找出并返回一个幸运数。
 * <p>
 * 如果数组中存在多个幸运数，只需返回 最大 的那个。
 * 如果数组中不含幸运数，则返回 -1 。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：arr = [2,2,3,4]
 * 输出：2
 * 解释：数组中唯一的幸运数是 2 ，因为数值 2 的出现频次也是 2 。
 * 示例 2：
 * <p>
 * 输入：arr = [1,2,2,3,3,3]
 * 输出：3
 * 解释：1、2 以及 3 都是幸运数，只需要返回其中最大的 3 。
 * 示例 3：
 * <p>
 * 输入：arr = [2,2,2,3,3]
 * 输出：-1
 * 解释：数组中不存在幸运数。
 * 示例 4：
 * <p>
 * 输入：arr = [5]
 * 输出：-1
 * 示例 5：
 * <p>
 * 输入：arr = [7,7,7,7,7,7,7]
 * 输出：7
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= arr.length <= 500
 * 1 <= arr[i] <= 500
 *
 * @author zm
 */
public class LEET5368 {
    public static void main(String[] args) {
        LEET5368 l5368 = new LEET5368();
        System.out.println(l5368.new Solution().findLucky(new int[]{2, 2, 3, 4}));
        System.out.println(l5368.new Solution().findLucky(new int[]{1, 2, 2, 3, 3, 3}));
        System.out.println(l5368.new Solution().findLucky(new int[]{2, 2, 2, 3, 3}));
        System.out.println(l5368.new Solution().findLucky(new int[]{5}));
        System.out.println(l5368.new Solution().findLucky(new int[]{7, 7, 7, 7, 7, 7, 7}));
    }


    class Solution {
        public int findLucky(int[] arr) {
            int[] cnt = new int[500];
            for (int i = 0; i < arr.length; i++) {
                cnt[arr[i] - 1]++;
            }
            for (int i = 499; i >= 0; i--) {
                if (cnt[i] == i + 1) {
                    return i + 1;
                }
            }
            return -1;
        }
    }
}
