package com.zm.LeetCodeEx.lcof;

/**
 * 面试题64. 求1+2+…+n
 * <p>
 * 求 1+2+...+n ，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入: n = 3
 * 输出: 6
 * 示例 2：
 * <p>
 * 输入: n = 9
 * 输出: 45
 *  
 * <p>
 * 限制：
 * <p>
 * 1 <= n <= 10000
 *
 * @author zm
 */
public class Lcof064 {
    public static void main(String[] args) {
        Lcof064 l064 = new Lcof064();
        System.out.println(l064.new Solution().sumNums(3));
        System.out.println(l064.new Solution().sumNums(6));
    }

    class Solution {
        public int sumNums(int n) {
            return ((int) Math.pow(n, 2.0) + n) >> 1;
        }
    }

    class Solution2 {
        public int sumNums(int n) {
            int sum = n;
            boolean flag = n > 1 && (sum += sumNums(n - 1)) > 0;
            return sum;
        }
    }
}
