package com.zm.LeetCodeEx.algorithms.ex801_900;

/**
 * 837. 新21点
 * <p>
 * 爱丽丝参与一个大致基于纸牌游戏 “21点” 规则的游戏，描述如下：
 * <p>
 * 爱丽丝以 0 分开始，并在她的得分少于 K 分时抽取数字。 抽取时，她从 [1, W] 的范围中随机获得一个整数作为分数进行累计，其中 W 是整数。 每次抽取都是独立的，其结果具有相同的概率。
 * <p>
 * 当爱丽丝获得不少于 K 分时，她就停止抽取数字。 爱丽丝的分数不超过 N 的概率是多少？
 * <p>
 * 示例 1：
 * <p>
 * 输入：N = 10, K = 1, W = 10
 * 输出：1.00000
 * 说明：爱丽丝得到一张卡，然后停止。
 * 示例 2：
 * <p>
 * 输入：N = 6, K = 1, W = 10
 * 输出：0.60000
 * 说明：爱丽丝得到一张卡，然后停止。
 * 在 W = 10 的 6 种可能下，她的得分不超过 N = 6 分。
 * 示例 3：
 * <p>
 * 输入：N = 21, K = 17, W = 10
 * 输出：0.73278
 * 提示：
 * <p>
 * 0 <= K <= N <= 10000
 * 1 <= W <= 10000
 * 如果答案与正确答案的误差不超过 10^-5，则该答案将被视为正确答案通过。
 * 此问题的判断限制时间已经减少。
 *
 * @author zm
 */
public class LEET837 {
    public static void main(String[] args) {
        LEET837 l837 = new LEET837();
        System.out.println(l837.new Solution().new21Game(10, 1, 10));
        System.out.println(l837.new Solution().new21Game(6, 1, 10));
        System.out.println(l837.new Solution().new21Game(21, 17, 10));
    }

    class Solution {
        public double new21Game(int N, int K, int W) {
            double[] dp = new double[K + W];
            double sum = 0;
            // 不能再拿纸牌之后获胜概率就是判断是否i>N
            for (int i = K; i < K + W; i++) {
                dp[i] = i > N ? 0 : 1;
                sum += dp[i];
            }
            // 可以拿纸牌的时候就是后面K个概率的平均值
            for (int i = K - 1; i >= 0; i--) {
                dp[i] = sum * 1.0 / W;
                sum += dp[i] - dp[i + W];
            }
            return dp[0];
        }
    }
}
