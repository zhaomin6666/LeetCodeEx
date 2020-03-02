package com.zm.LeetCodeEx;

/**
 * 70. 爬楼梯
 * <p>
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * <p>
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * <p>
 * 注意：给定 n 是一个正整数。
 * <p>
 * 示例 1：
 * <p>
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 * 示例 2：
 * <p>
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 *
 * @author zm
 */
public class LEET070 {
    public static void main(String[] args) {
        LEET070 l070 = new LEET070();
        System.out.println(l070.new Solution().climbStairs(2));
        System.out.println(l070.new Solution().climbStairs(3));
        System.out.println(l070.new Solution().climbStairs(4));
        System.out.println(l070.new Solution().climbStairs(5));
        System.out.println(l070.new Solution().climbStairs(6));
    }

    /**
     * 斐波那切数列通项直接求解
     */
    class Solution {
        public int climbStairs(int n) {
            if (n <= 3) {
                return n;
            }
            return (int) (1.0 / Math.sqrt(5) * (Math.pow((1 + Math.sqrt(5)) / 2, n + 1) - Math.pow((1 - Math.sqrt(5)) / 2, n + 1)));
        }
    }
}
