package com.zm.LeetCodeEx.algorithms.ex1_100;

/**
 * 69. x 的平方根
 * <p>
 * 实现 int sqrt(int x) 函数。
 * <p>
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 * <p>
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 * <p>
 * 示例 1: 输入: 4 输出: 2
 * <p>
 * 示例 2: 输入: 8 输出: 2 说明: 8 的平方根是 2.82842...,   由于返回类型是整数，小数部分将被舍去。
 *
 * @author zm
 */
public class LEET069 {
    public static void main(String[] args) {
        LEET069 l069 = new LEET069();
        /*
         * System.out.println(l069.new Solution().mySqrt(4));
         * System.out.println(l069.new Solution().mySqrt(2));
         * System.out.println(l069.new Solution().mySqrt(0));
         */
        System.out.println(l069.new Solution().mySqrt(2147483647));
        System.out.println(Math.sqrt(2147483647));
    }

    /**
     * 二分查找 0<k<x/2
     */
    private class Solution {
        public int mySqrt(int x) {
            if (x < 2) {
                return x;
            }
            long num;
            int p, l = 2, r = x >> 1;
            while (l <= r) {
                p = (l + r) >> 1;
                num = (long) p * p;
                if (num > x) {
                    r = p - 1;
                } else if (num < x) {
                    l = p + 1;
                } else {
                    return p;
                }
            }
            return r;
        }
    }

    /**
     * 官方题解中的袖珍计算器算法
     */
    private class Solution2 {
        public int mySqrt(int x) {
            if (x < 2) {
                return x;
            }
            int left = (int) Math.pow(Math.E, 0.5 * Math.log(x));
            int right = left + 1;
            return (long) right * right > x ? left : right;
        }
    }
}
