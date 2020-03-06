package com.zm.LeetCodeEx.weekcontest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 周赛 2019年10月13日  5223. 可以攻击国王的皇后
 * 在一个 8x8 的棋盘上，放置着若干「黑皇后」和一个「白国王」。
 * <p>
 * 「黑皇后」在棋盘上的位置分布用整数坐标数组 queens 表示，「白国王」的坐标用数组 king 表示。
 * <p>
 * 「黑皇后」的行棋规定是：横、直、斜都可以走，步数不受限制，但是，不能越子行棋。
 * <p>
 * 请你返回可以直接攻击到「白国王」的所有「黑皇后」的坐标（任意顺序）。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * [K,Q,-,-,Q,-,-,-,-]
 * [Q,-,-,-,-,-,-,-,-]
 * [-,-,-,-,Q,-,-,-,-]
 * [-,-,-,Q,-,-,-,-,-]
 * [Q,-,-,-,-,-,-,-,-]
 * [-,-,-,-,-,-,-,-,-]
 * [-,-,-,-,-,-,-,-,-]
 * [-,-,-,-,-,-,-,-,-]
 * <p>
 * 输入：queens = [[0,1],[1,0],[4,0],[0,4],[3,3],[2,4]], king = [0,0]
 * 输出：[[0,1],[1,0],[3,3]]
 * 解释：
 * [0,1] 的皇后可以攻击到国王，因为他们在同一行上。
 * [1,0] 的皇后可以攻击到国王，因为他们在同一列上。
 * [3,3] 的皇后可以攻击到国王，因为他们在同一条对角线上。
 * [0,4] 的皇后无法攻击到国王，因为她被位于 [0,1] 的皇后挡住了。
 * [4,0] 的皇后无法攻击到国王，因为她被位于 [1,0] 的皇后挡住了。
 * [2,4] 的皇后无法攻击到国王，因为她和国王不在同一行/列/对角线上。
 *
 * @author zm
 */
public class LEET5223 {
    public static void main(String[] args) {
        LEET5223 l5223 = new LEET5223();
        int[][] queens = {{0, 1}, {1, 0}, {4, 0}, {0, 4}, {3, 3}, {2, 4}};
        int[] king = {0, 0};
        int[][] queens2 = {{5, 6}, {7, 7}, {2, 1}, {0, 7}, {1, 6}, {5, 1}, {3, 7}, {0, 3}, {4, 0}, {1, 2},
                {6, 3}, {5, 0}, {0, 4}, {2, 2}, {1, 1}, {6, 4}, {5, 4}, {0, 0}, {2, 6}, {4, 5}, {5, 2}, {1, 4},
                {7, 5}, {2, 3}, {0, 5}, {4, 2}, {1, 0}, {2, 7}, {0, 1}, {4, 6}, {6, 1}, {0, 6}, {4, 3}, {1, 7}};
        int[] king2 = {3, 4};
        System.out.println(l5223.queensAttacktheKing(queens2, king2));
    }

    private int rows = 8;

    public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
        List<List<Integer>> ret = new ArrayList<>();
        int[][] chessBoard = new int[rows][rows];
        for (int i = 0; i < queens.length; i++) {
            chessBoard[queens[i][0]][queens[i][1]] = 1;
        }
        for (int i = 0; i < queens.length; i++) {
            int[] queen = queens[i];
            boolean check = true;
            if (queen[0] == king[0]) {
                int small = Math.min(queen[1], king[1]);
                int big = Math.max(queen[1], king[1]);
                checkLoop:
                for (int j = small + 1; j < big; j++) {
                    if (chessBoard[queen[0]][j] == 1) {
                        check = false;
                        break checkLoop;
                    }
                }
            } else if (queen[1] == king[1]) {
                int small = Math.min(queen[0], king[0]);
                int big = Math.max(queen[0], king[0]);
                checkLoop:
                for (int j = small + 1; j < big; j++) {
                    if (chessBoard[j][queen[1]] == 1) {
                        check = false;
                        break checkLoop;
                    }
                }
            } else if (Math.abs(queen[0] - king[0]) == Math.abs(queen[1] - king[1])) {
                checkLoop:
                for (int j = 1; j < Math.abs(queen[0] - king[0]); j++) {
                    int checkR = queen[0] > king[0] ? king[0] + j : king[0] - j;
                    int checkC = queen[1] > king[1] ? king[1] + j : king[1] - j;
                    if (chessBoard[checkR][checkC] == 1) {
                        check = false;
                        break checkLoop;
                    }
                }
            } else {
                check = false;
            }
            if (check) {
                List<Integer> retItem = new ArrayList<>();
                retItem.add(queen[0]);
                retItem.add(queen[1]);
                ret.add(retItem);
            }
        }
        return ret;
    }
}
