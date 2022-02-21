package com.zm.LeetCodeEx.lcof;

/**
 * 面试题62. 圆圈中最后剩下的数字
 * <p>
 * 0,1,,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字。求出这个圆圈里剩下的最后一个数字。
 * <p>
 * 例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。
 * 示例 1：
 * <p>
 * 输入: n = 5, m = 3
 * 输出: 3
 * 示例 2：
 * <p>
 * 输入: n = 10, m = 17
 * 输出: 2
 *  
 * <p>
 * 限制：
 * <p>
 * 1 <= n <= 10^5
 * 1 <= m <= 10^6
 *
 * @author zm
 */
public class Lcof062 {
    public static void main(String[] args) {
        Lcof062 l062 = new Lcof062();
        System.out.println(l062.new Solution().lastRemaining(5, 3));
        System.out.println(l062.new Solution().lastRemaining(10, 17));


    }

    /**
     * n个数字每一次去掉一个之后，都会从n-1个数中再去去掉一次，但是此时不是从0开始数，
     * 而是从当前数开始数，所以f(n,m) = (f(n-1,m) + 当前数位置m%n)%n
     */
    class Solution {
        public int lastRemaining(int n, int m) {
            return f(n, m);
        }

        private int f(int n, int m) {
            if (n == 1) {
                return 0;
            }
            int x = f(n - 1, m);
            return (m + x) % n;
        }
    }

    class Solution2 {
        public int lastRemaining(int n, int m) {
            int f = 0;
            for (int i = 2; i != n + 1; ++i) {
                f = (m + f) % i;
            }
            return f;
        }
    }
}
