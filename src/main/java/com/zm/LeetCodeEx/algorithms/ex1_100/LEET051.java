package com.zm.LeetCodeEx.algorithms.ex1_100;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

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
 *
 * <p>
 *
 * @author zm
 */
public class LEET051 {
    public static void main(String[] args) {
        // LEET051 l051 = new LEET051();
        System.out.println(JSON.toJSONString(new LEET051().solveNQueens(4)));
        System.out.println(JSON.toJSONString(new LEET051().solveNQueens2(4)));
    }

    public static List<String> convertArrayToResult(boolean[][] board) {
        List<String> ret = new ArrayList<>();
        for (boolean[] row : board) {
            StringBuffer sb = new StringBuffer();
            for (boolean item : row) {
                if (item) {
                    sb.append("Q");
                } else {
                    sb.append(".");
                }
            }
            ret.add(sb.toString());
        }
        return ret;
    }

    List<List<String>> retList = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        boolean[][] board = new boolean[n][n];
        backTrack(board, 0);
        return retList;
    }

    private void backTrack(boolean[][] board, int i) {
        for (int j = 0; j < board.length; j++) {
            // 检查是否可以放
            if (checkIsAllow(board, i, j)) {
                board[i][j] = true;
                if (i == board.length - 1) {
                    retList.add(convertArrayToResult(board));
                    board[i][j] = false;
                    return;
                } else {
                    backTrack(board, i + 1);
                }
                board[i][j] = false;
            }
        }
    }

    private static boolean checkIsAllow(boolean[][] board, int i, int j) {
        // 上方
        for (int k = 0; k < i; k++) {
            if (board[k][j]) {
                return false;
            }
        }
        // 左上方
        for (int k = 1; k <= i; k++) {
            if (j - k < 0) {
                break;
            }
            if (board[i - k][j - k]) {
                return false;
            }
        }
        // 右上方
        for (int k = 1; k <= i; k++) {
            if (j + k >= board.length) {
                break;
            }
            if (board[i - k][j + k]) {
                return false;
            }
        }
        return true;
    }

    private int[] queen;
    private boolean[] col;
    private boolean[] diag;
    private boolean[] backdiag;
    private int nQ;

    public static List<String> convertArrayToResult(int n, int[] queen) {
        List<String> ret = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringBuffer sb = new StringBuffer();
            for (int j = 0; j < n; j++) {
                if (queen[i] == j) {
                    sb.append("Q");
                } else {
                    sb.append(".");
                }
            }
            ret.add(sb.toString());
        }
        return ret;
    }


    public List<List<String>> solveNQueens2(int n) {
        nQ = n;
        queen = new int[n];
        col = new boolean[n];
        diag = new boolean[2 * n - 1];
        backdiag = new boolean[2 * n - 1];
        backTrack2(0);
        return retList;
    }

    private void backTrack2(int i) {
        for (int j = 0; j < queen.length; j++) {
            if (!col[j] && !diag[nQ + j - i - 1] && !backdiag[i + j]) {
                queen[i] = j;
                if (i == nQ - 1) {
                    retList.add(convertArrayToResult(nQ, queen));
                    queen[i] = 0;
                    return;
                }
                col[j] = true;
                diag[nQ + j - i - 1] = true;
                backdiag[i + j] = true;
                backTrack2(i + 1);
                col[j] = false;
                diag[nQ + j - i - 1] = false;
                backdiag[i + j] = false;
            }
        }
    }
}
