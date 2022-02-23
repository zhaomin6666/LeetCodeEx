package com.zm.LeetCodeEx.weekcontest.contest_d72_20220219;

public class LEET5999 {

    public static void main(String[] args) {
        System.out.println(new Solution2().goodTriplets(new int[]{2, 0, 1, 3}, new int[]{0, 1, 2, 3}));
        System.out.println(new Solution2().goodTriplets(new int[]{4, 0, 1, 3, 2}, new int[]{4, 1, 0, 2, 3}));
    }

    /**
     * 暴力解法
     * 时间超出限制
     */
    static class Solution {
        public long goodTriplets(int[] nums1, int[] nums2) {
            int[] index = new int[nums2.length];
            for (int i = 0; i < nums2.length; i++) {
                index[nums2[i]] = i;
            }
            long result = 0;
            for (int i = 0; i < nums1.length; i++) {
                for (int j = i + 1; j < nums1.length; j++) {
                    if (index[nums1[j]] > index[nums1[i]]) {
                        for (int k = j + 1; k < nums1.length; k++) {
                            if (index[nums1[k]] > index[nums1[j]]) {
                                result++;
                            }
                        }
                    }
                }
            }
            return result;
        }
    }

    /**
     * 用一个二维数组来保存nums2中每两个数字的位置关系
     * 内存超出限制
     */
    static class Solution2 {
        public long goodTriplets(int[] nums1, int[] nums2) {
            boolean[][] statistics = new boolean[nums1.length][nums1.length];
            for (int i = 0; i < nums2.length; i++) {
                for (int j = i + 1; j < nums2.length; j++) {
                    statistics[nums2[i]][nums2[j]] = true;
                }
            }

            long result = 0;
            for (int i = 1; i < nums1.length - 1; i++) {
                int left = 0;
                for (int j = 0; j < i; j++) {
                    if (statistics[nums1[j]][nums1[i]]) {
                        left++;
                    }
                }
                if (left == 0) {
                    continue;
                }
                int right = 0;
                for (int j = i + 1; j < nums1.length; j++) {
                    if (statistics[nums1[i]][nums1[j]]) {
                        right++;
                    }
                }

                result += (long) left * right;
            }
            return result;
        }
    }
}
