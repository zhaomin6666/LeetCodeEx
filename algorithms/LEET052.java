package com.zm.LeetCodeEx.algorithms;

/**
 * 51. N皇后
 * <p>
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * <p>
 * <p>
 * <p>
 * 上图为 8 皇后问题的一种解法。
 * <p>
 * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
 * <p>
 * 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 * <p>
 * 示例:
 * <p>
 * 输入: 4
 * 输出: [
 * [
 * ".Q..",  // 解法 1
 * "...Q",
 * "Q...",
 * "..Q."],
 * <p>
 * [
 * "..Q.",  // 解法 2
 * "Q...",
 * "...Q",
 * ".Q.."]
 * ]
 * 解释: 4 皇后问题存在两个不同的解法。
 * <p>
 * 4:2
 * 5:10
 * 6:4
 * 7:40
 * 8:92
 *
 * <p>
 *
 * @author zm
 */
public class LEET052 {
    public static void main(String[] args) {
        LEET052 leet052 = new LEET052();
        System.out.println(leet052.totalNQueens(4));
    }

    public int totalNQueens(int n) {
        return new LEET051().solveNQueens(n).size();
    }
}
