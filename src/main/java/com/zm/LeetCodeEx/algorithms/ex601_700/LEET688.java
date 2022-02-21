package com.zm.LeetCodeEx.algorithms.ex601_700;

/**
 * 688. 骑士在棋盘上的概率
 * 在一个 n x n 的国际象棋棋盘上，一个骑士从单元格 (row, column) 开始，并尝试进行 k 次移动。行和列是 从 0 开始 的，所以左上单元格是 (0,0) ，右下单元格是 (n - 1, n - 1) 。
 * 象棋骑士有8种可能的走法，如下图所示。每次移动在基本方向上是两个单元格，然后在正交方向上是一个单元格。
 * 每次骑士要移动时，它都会随机从8种可能的移动中选择一种(即使棋子会离开棋盘)，然后移动到那里。
 * 骑士继续移动，直到它走了 k 步或离开了棋盘。
 * 返回 骑士在棋盘停止移动后仍留在棋盘上的概率 。
 * <p>
 * 示例 1：
 * <p>
 * 输入: n = 3, k = 2, row = 0, column = 0
 * 输出: 0.0625
 * 解释: 有两步(到(1,2)，(2,1))可以让骑士留在棋盘上。
 * 在每一个位置上，也有两种移动可以让骑士留在棋盘上。
 * 骑士留在棋盘上的总概率是0.0625。
 * 示例 2：
 * <p>
 * 输入: n = 1, k = 0, row = 0, column = 0
 * 输出: 1.00000
 * <p>
 * <p>
 * 提示:
 * <p>
 * 1 <= n <= 25
 * 0 <= k <= 100
 * 0 <= row, column <= n
 *
 * @author zm
 */
public class LEET688 {
    public static void main(String[] args) {
        System.out.println(new Solution().knightProbability(3, 2, 0, 0));
        System.out.println(new Solution().knightProbability(1, 0, 0, 0));
    }

    /**
     * 动态规划
     * dp[step][i][j] = 1/8 ∑dp[step-1][i+direction[k][0]][j+direction[k][1]] k=0-7
     */
    static class Solution {
        // 行动方向
        static int[][] directions = new int[][]{{1, 2}, {1, -2}, {-1, 2}, {-1, -2}, {2, 1}, {2, -1}, {-2, 1}, {-2, -1}};

        public double knightProbability(int n, int k, int row, int column) {
            double[][][] dp = new double[k + 1][n][n];
            dp[0][row][column] = 1;
            for (int step = 1; step <= k; step++) {
                for (int r = 0; r < n; r++) {
                    for (int c = 0; c < n; c++) {
                        double possibility = 0;
                        for (int[] direction : directions) {
                            int rr = r + direction[0];
                            int cc = c + direction[1];
                            if (rr >= 0 && cc >= 0 && rr < n && cc < n) {
                                possibility += dp[step - 1][rr][cc];
                            }
                        }
                        dp[step][r][c] = possibility / 8;
                    }
                }
            }
            double result = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    result += dp[k][i][j];
                }
            }
            return result;
        }
    }
}
