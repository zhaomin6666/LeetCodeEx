package com.zm.LeetCodeEx.algorithms.ex101_200;

import com.alibaba.fastjson.JSON;
import com.zm.LeetCodeEx.CommonFunctions;
import com.zm.LeetCodeEx.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 130. 被围绕的区域
 * <p>
 * 给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
 * 输出：[["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
 * 解释：被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 * 示例 2：
 * <p>
 * 输入：board = [["X"]]
 * 输出：[["X"]]
 *  
 * <p>
 * 提示：
 * <p>
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 200
 * board[i][j] 为 'X' 或 'O'
 *
 * @author zm
 */
public class LEET130 {
    public static void main(String[] args) {
        LEET130 l130 = new LEET130();
        char[][] chars1 = new char[][]{{'X', 'X', 'X', 'X'}, {'X', 'O', 'O', 'X'}, {'X', 'X', 'O', 'X'}, {'X', 'O', 'X', 'X'}};
        char[][] chars2 = new char[][]{{'X', 'X', 'X', 'X'}, {'X', 'O', 'O', 'X'}, {'X', 'X', 'O', 'O'}, {'X', 'O', 'X', 'X'}};
        char[][] chars3 = new char[][]{{'X', 'X', 'X', 'X', 'X'}, {'X', 'O', 'O', 'O', 'X'}, {'X', 'O', 'X', 'O', 'X'}, {'X', 'O', 'O', 'O', 'X'}, {'X', 'X', 'X', 'X', 'X'}};

        char[][][] chars = new char[][][]{chars1, chars2, chars3};
        for (char[][] myChar : chars) {
            l130.new Solution().solve(myChar);
            for (char[] row : myChar) {
                System.out.println(JSON.toJSONString(row));
            }
            System.out.println("--------");
        }
    }

    class Solution {
        char[][] table;
        int[][] status; // 0:还未遍历到,1:正在遍历,2:已遍历
        int m;
        int n;
        int[] dx = new int[]{0, 0, -1, 1};
        int[] dy = new int[]{-1, 1, 0, 0};

        public void solve(char[][] board) {
            table = board;
            m = board.length;
            n = board[0].length;
            status = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (table[i][j] == 'O' && status[i][j] == 0) {
                        List<int[]> check = new ArrayList<>();
                        status[i][j] = 1;
                        boolean isAround = checkIsAround(i, j, check);
                        for (int[] pair : check) {
                            if (isAround) {
                                table[pair[0]][pair[1]] = 'X';
                            }
                            status[pair[0]][pair[1]] = 2;
                        }
                    }
                    status[i][j] = 2;
                }
            }
        }

        public boolean checkIsAround(int i, int j, List<int[]> check) {
            check.add(new int[]{i, j});
            for (int k = 0; k < 4; k++) {
                int x = i + dx[k];
                int y = j + dy[k];
                if (x < 0 || x >= m || y < 0 || y >= n || (table[x][y] == 'O' && status[x][y] == 2)) {
                    return false;
                }
                if (table[x][y] == 'O' && status[x][y] == 0) {
                    status[i][j] = 1;
                    if (!checkIsAround(x, y, check)) {
                        return false;
                    }
                }
            }
            return true;
        }
    }

    /**
     * 官方题解
     * <p>
     * 从外层dfs，遇到O就可以改为A标识与外界联通。
     * 相比自己写的方法就不需要去记录遍历走过的节点，最后统一处理
     */
    class Solution2 {
        int n, m;

        public void solve(char[][] board) {
            n = board.length;
            if (n == 0) {
                return;
            }
            m = board[0].length;
            for (int i = 0; i < n; i++) {
                dfs(board, i, 0);
                dfs(board, i, m - 1);
            }
            for (int i = 1; i < m - 1; i++) {
                dfs(board, 0, i);
                dfs(board, n - 1, i);
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (board[i][j] == 'A') {
                        board[i][j] = 'O';
                    } else if (board[i][j] == 'O') {
                        board[i][j] = 'X';
                    }
                }
            }
        }

        public void dfs(char[][] board, int x, int y) {
            if (x < 0 || x >= n || y < 0 || y >= m || board[x][y] != 'O') {
                return;
            }
            board[x][y] = 'A';
            dfs(board, x + 1, y);
            dfs(board, x - 1, y);
            dfs(board, x, y + 1);
            dfs(board, x, y - 1);
        }
    }

}
