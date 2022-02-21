package com.zm.LeetCodeEx.weekcontest.before20200426;

/**
 * 周赛 2019年11月10日  5255. 奇数值单元格的数目
 * 给你一个 n 行 m 列的矩阵，最开始的时候，每个单元格中的值都是 0。
 * <p>
 * 另有一个索引数组 indices，indices[i] = [ri, ci] 中的 ri 和 ci 分别表示指定的行和列（从 0 开始编号）。
 * <p>
 * 你需要将每对 [ri, ci] 指定的行和列上的所有单元格的值加 1。
 * <p>
 * 请你在执行完所有 indices 指定的增量操作后，返回矩阵中 「奇数值单元格」 的数目。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：n = 2, m = 3, indices = [[0,1],[1,1]]
 * 输出：6
 * 解释：最开始的矩阵是 [[0,0,0],[0,0,0]]。
 * 第一次增量操作后得到 [[1,2,1],[0,1,0]]。
 * 最后的矩阵是 [[1,3,1],[1,3,1]]，里面有 6 个奇数。
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：n = 2, m = 2, indices = [[1,1],[0,0]]
 * 输出：0
 * 解释：最后的矩阵是 [[2,2],[2,2]]，里面没有奇数。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 50
 * 1 <= m <= 50
 * 1 <= indices.length <= 100
 * 0 <= indices[i][0] < n
 * 0 <= indices[i][1] < m
 *
 * @author zm
 */
public class LEET5255 {
    public static void main(String[] args) {
        LEET5255 l5255 = new LEET5255();
        int n = 2;
        int m = 2;
        int[][] indices = {{0, 1}, {1, 1}};
        System.out.println(l5255.oddCells(n, m, indices));

    }

    public int oddCells(int n, int m, int[][] indices) {
        boolean[][] matrix = new boolean[n][m];
        for (int[] indice : indices) {
            for (int i = 0; i < m; i++) {
                matrix[indice[0]][i] = !matrix[indice[0]][i];
            }

            for (int i = 0; i < n; i++) {
                matrix[i][indice[1]] = !matrix[i][indice[1]];
            }
        }
        int ret = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j]) {
                    ret++;
                }
            }
        }
        return ret;
    }


}
