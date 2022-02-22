package com.zm.LeetCodeEx.weekcontest.contest_234_20210328;

import java.util.Arrays;

/**
 * 5715. 还原排列的最少操作步数
 * 给你一个偶数 n ，已知存在一个长度为 n 的排列 perm ，其中 perm[i] == i​（下标 从 0 开始 计数）。
 * <p>
 * 一步操作中，你将创建一个新数组 arr ，对于每个 i ：
 * <p>
 * 如果 i % 2 == 0 ，那么 arr[i] = perm[i / 2]
 * 如果 i % 2 == 1 ，那么 arr[i] = perm[n / 2 + (i - 1) / 2]
 * 然后将 arr 赋值给 perm 。
 * <p>
 * 要想使 perm 回到排列初始值，至少需要执行多少步操作？返回最小的 非零 操作步数。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 2
 * 输出：1
 * 解释：最初，perm = [0,1]
 * 第 1 步操作后，perm = [0,1]
 * 所以，仅需执行 1 步操作
 * 示例 2：
 * <p>
 * 输入：n = 4
 * 输出：2
 * 解释：最初，perm = [0,1,2,3]
 * 第 1 步操作后，perm = [0,2,1,3]
 * 第 2 步操作后，perm = [0,1,2,3]
 * 所以，仅需执行 2 步操作
 * 示例 3：
 * <p>
 * 输入：n = 6
 * 输出：4
 * <p>
 * <p>
 * 提示：
 * <p>
 * 2 <= n <= 1000
 * n 是一个偶数
 */
public class LEET5715 {
    public static void main(String[] args) {
        LEET5715 leet5710 = new LEET5715();
        System.out.println(leet5710.new Solution().reinitializePermutation(2));
        System.out.println(leet5710.new Solution().reinitializePermutation(4));
        System.out.println(leet5710.new Solution().reinitializePermutation(6));
    }

    /**
     * 直接模拟
     */
    class Solution {
        public int reinitializePermutation(int n) {
            int[] array1 = new int[n];
            int[] array2 = new int[n];
            for (int i = 0; i < n; i++) {
                array1[i] = i;
            }
            int ret = 0;
            do {
                ret++;
                for (int i = 0; i < n; i++) {
                    if (i % 2 == 0) {
                        array2[i] = array1[i / 2];
                    } else {
                        array2[i] = array1[n / 2 + (i - 1) / 2];
                    }
                }
                array1 = Arrays.copyOf(array2, array2.length);
            }
            while (!check(array1));
            return ret;
        }

        private boolean check(int[] array) {
            int n = array.length;
            for (int i = 0; i < n; i++) {
                if (array[i] != i) {
                    return false;
                }
            }
            return true;
        }
    }
}