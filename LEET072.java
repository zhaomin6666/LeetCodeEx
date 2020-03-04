package com.zm.LeetCodeEx;

/**
 * 72. 编辑距离
 * <p>
 * 给定两个单词 word1 和 word2，计算出将 word1 转换成 word2 所使用的最少操作数 。
 * <p>
 * 你可以对一个单词进行如下三种操作：
 * <p>
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 * 示例 1:
 * <p>
 * 输入: word1 = "horse", word2 = "ros"
 * 输出: 3
 * 解释:
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 * <p>
 * 示例 2:
 * <p>
 * 输入: word1 = "intention", word2 = "execution"
 * 输出: 5
 * 解释:
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 *
 * @author zm
 */
public class LEET072 {
    public static void main(String[] args) {
        LEET072 l072 = new LEET072();
        System.out.println(l072.new Solution().minDistance("horse", "ros"));
        System.out.println(l072.new Solution().minDistance("intention", "execution"));
    }

    /**
     * 优化了解法，先使用了char[]代替String、写成了一个整的循环
     */
    class Solution {
        public int minDistance(String word1, String word2) {
            char[] chars1 = word1.toCharArray();
            char[] chars2 = word2.toCharArray();
            int[][] dp = new int[chars1.length + 1][chars2.length + 1];
            for (int i = 0; i <= chars1.length; i++) {
                for (int j = 0; j <= chars2.length; j++) {
                    if (i == 0 || j == 0) dp[i][j] = i != 0 ? i : j;
                    else if (chars1[i - 1] == chars2[j - 1]) dp[i][j] = dp[i - 1][j - 1];
                    else dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j])) + 1;
                }
            }
            return dp[chars1.length][chars2.length];
        }
    }

    /**
     * 二维动态规划
     * 第一个单词的前i位变成第二个单词的前j-1位，然后再插入一个字符（d[i][j-1]+1）
     * 第一个单词的前i-1位变成第二个单词的前j位，然后再删去一个字符（d[i-1][j]+1）
     * 第一个单词的前i-1位变成第二个单词的前j-1位，然后替换最后一个字符，如果最后一个字符相同，即第一个单词的第i位和第二个单词的第j位相同的话，就不用替换了（d[i-1][j-1]），反之，如果不同就替换最后一位（d[i-1][j-1]+1）
     */
    class Solution2 {
        public int minDistance(String word1, String word2) {
            int[][] dp = new int[word1.length() + 1][word2.length() + 1];
            for (int i = 0; i <= word1.length(); i++) {
                dp[i][0] = i;
            }
            for (int i = 0; i <= word2.length(); i++) {
                dp[0][i] = i;
            }
            for (int i = 1; i <= word1.length(); i++) {
                for (int j = 1; j <= word2.length(); j++) {
                    if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else {
                        dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                    }
                }
            }
            return dp[word1.length()][word2.length()];
        }
    }
}
