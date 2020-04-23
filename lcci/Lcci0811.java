package com.zm.LeetCodeEx.lcci;

/**
 * 面试题 08.11. 硬币
 * <p>
 * 硬币。给定数量不限的硬币，币值为25分、10分、5分和1分，编写代码计算n分有几种表示法。(结果可能会很大，你需要将结果模上1000000007)
 * <p>
 * 示例1:
 * <p>
 * 输入: n = 5
 * 输出：2
 * 解释: 有两种方式可以凑成总金额:
 * 5=5
 * 5=1+1+1+1+1
 * 示例2:
 * <p>
 * 输入: n = 10
 * 输出：4
 * 解释: 有四种方式可以凑成总金额:
 * 10=10
 * 10=5+5
 * 10=5+1+1+1+1+1
 * 10=1+1+1+1+1+1+1+1+1+1
 * 说明：
 * <p>
 * 注意:
 * <p>
 * 你可以假设：
 * <p>
 * 0 <= n (总金额) <= 1000000
 *
 * @author zm
 */
public class Lcci0811 {
    public static void main(String[] args) {
        Lcci0811 lcci0811 = new Lcci0811();
        System.out.println(lcci0811.new Solution().waysToChange(4)); // 1
        System.out.println(lcci0811.new Solution().waysToChange(5)); // 2
        System.out.println(lcci0811.new Solution().waysToChange(6)); // 2
        System.out.println(lcci0811.new Solution().waysToChange(10));// 4
        System.out.println(lcci0811.new Solution().waysToChange(14));// 4
        System.out.println(lcci0811.new Solution().waysToChange(15));// 6
        System.out.println(lcci0811.new Solution().waysToChange(16));// 6
        System.out.println(lcci0811.new Solution().waysToChange(20));// 9
        System.out.println(lcci0811.new Solution().waysToChange(26));// 12
        System.out.println(lcci0811.new Solution().waysToChange(61));// 73
    }

    /**
     * 数学方法
     * 25使用循环去除。然后每一个10都会产生2个5，所以是一个跟10数量n相关的，差为2的等差数列。
     */
    class Solution {
        public int waysToChange(int n) {
            int mod = 1000000007;
            int ans = 0;
            for (int i = 0; i * 25 <= n; ++i) {
                int rest = n - i * 25;
                int a = rest / 10;
                int b = rest % 10 / 5;
                ans = (int) ((ans + (long) (a + 1) * (a + b + 1) % mod) % mod);
            }
            return ans;
        }
    }

    /**
     * dp
     * f(4,90)=f(3,90)+f(3,90−25)+f(3,90−2×25)+f(3,90−3×25)
     * <p>
     * f(4,90-25)=f(3,90−25)+f(3,90−2×25)+f(3,90−3×25)+f(3,90−4×25)
     * <p>
     * f(4,90)-f(4,90-25)=f(3,90)-f(3,90−4×25)  (f(3,90−4×25) = 0)
     * <p>
     * f(4,90)=f(4,90-25)+f(3,90)
     * <p>
     * f(i,v)=f(i−1,v)+f(i,v−ci)  ci=5,10,25 (1可以不看)
     * <p>
     * 再把二维数组优化成一维数组
     */
    class Solution2 {
        public int waysToChange(int n) {
            int mod = 1000000007;
            int[] dp = new int[n + 1];
            int[] coins = {1, 5, 10, 25};
            for (int i = 0; i <= n; i++) {
                dp[i] = 1;
            }
            for (int i = 1; i < 4; i++) {
                for (int j = 1; j <= n; j++) {
                    if (j >= coins[i]) {
                        dp[j] = (dp[j] + dp[j - coins[i]]) % mod;
                    }
                }
            }
            return dp[n];
        }
    }
}
