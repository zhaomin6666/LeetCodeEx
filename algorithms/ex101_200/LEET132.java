package com.zm.LeetCodeEx.algorithms.ex101_200;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 131. 分割回文串
 * <p>
 * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
 * <p>
 * 回文串 是正着读和反着读都一样的字符串。
 * <p>
 *
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "aab"
 * 输出：[["a","a","b"],["aa","b"]]
 * 示例 2：
 * <p>
 * 输入：s = "a"
 * 输出：[["a"]]
 *
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 16
 * s 仅由小写英文字母组成
 *
 * @author zm
 */
public class LEET132 {
    public static void main(String[] args) {
        System.out.println(new Solution().minCut("aab"));
        System.out.println(new Solution().minCut("a"));
        System.out.println(new Solution().minCut("ab"));
        System.out.println(new Solution2().minCut("aabccbaa"));
    }

    /**
     * 使用131的回溯去处理计算长度会超时
     */
    static class Solution {
        boolean[][] f;
        int ret = Integer.MAX_VALUE;
        List<String> ans = new ArrayList<String>();
        int n;

        public int minCut(String s) {
            n = s.length();
            f = new boolean[n][n];
            for (int i = 0; i < n; ++i) {
                Arrays.fill(f[i], true);
            }

            for (int i = n - 1; i >= 0; --i) {
                for (int j = i + 1; j < n; ++j) {
                    f[i][j] = (s.charAt(i) == s.charAt(j)) && f[i + 1][j - 1];
                }
            }

            dfs(s, 0);
            return ret - 1;
        }

        public void dfs(String s, int i) {
            if (ans.size() > ret) {
                return;
            }
            if (i == n) {
                ret = Math.min(ans.size(), ret);
                return;
            }
            for (int j = i; j < n; ++j) {
                if (f[i][j]) {
                    ans.add(s.substring(i, j + 1));
                    dfs(s, j + 1);
                    ans.remove(ans.size() - 1);
                }
            }
        }
    }

    /**
     * 官方题解
     */
    static class Solution2 {
        public int minCut(String s) {
            int n = s.length();
            // dp预处理，f[i][j]表示字符串中从i到j的子字符串是否为回文字符串
            // 由于转移方程为 f[i][j] = (s.charAt(i) == s.charAt(j)) && f[i + 1][j - 1];
            // 需要用到i+1，所以i循环的时候从大到小遍历。
            boolean[][] g = new boolean[n][n];
            for (int i = 0; i < n; ++i) {
                Arrays.fill(g[i], true);
            }
            for (int i = n - 1; i >= 0; --i) {
                for (int j = i + 1; j < n; ++j) {
                    g[i][j] = s.charAt(i) == s.charAt(j) && g[i + 1][j - 1];
                }
            }

            // dp f[i]表示字符串s中0，i子串被分割成回文子串的最小分割数
            // f[i] = Math.min(f[j] + 1)  (g(j+1,i)==true, j=0->i-1)
            int[] f = new int[n];
            Arrays.fill(f, Integer.MAX_VALUE);
            for (int i = 0; i < n; ++i) {
                if (g[0][i]) {
                    f[i] = 0;
                }
                else {
                    for (int j = 0; j < i; ++j) {
                        if (g[j + 1][i]) {
                            f[i] = Math.min(f[i], f[j] + 1);
                        }
                    }
                }
            }

            return f[n - 1];
        }
    }
}
