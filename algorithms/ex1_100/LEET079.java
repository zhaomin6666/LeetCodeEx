package com.zm.LeetCodeEx.algorithms.ex1_100;

/**
 * 79. 单词搜索
 * <p>
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 * <p>
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 * <p>
 * 示例:
 * <p>
 * board =
 * [
 * ['A','B','C','E'],
 * ['S','F','C','S'],
 * ['A','D','E','E']
 * ]
 * <p>
 * 给定 word = "ABCCED", 返回 true.
 * 给定 word = "SEE", 返回 true.
 * 给定 word = "ABCB", 返回 false.
 *
 * @author zm
 */
public class LEET079 {
    public static void main(String[] args) {
        LEET079 l079 = new LEET079();
        System.out.println(l079.new Solution().exist(new char[][]{
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        }, "ABCCED"));
        //true
        System.out.println(l079.new Solution().exist(new char[][]{
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        }, "SEE"));
        //true
        System.out.println(l079.new Solution().exist(new char[][]{
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        }, "ABCB"));
        //false
        System.out.println(l079.new Solution().exist(new char[][]{
                {'A', 'B'}
        }, "BA"));
    }

    class Solution {
        private char[][] board;
        private boolean[][] used;
        private char[] s;

        public boolean exist(char[][] board, String word) {
            if ("".equals(word)) {
                return false;
            }
            if (board.length == 0 || board[0].length == 0) {
                return false;
            }
            this.board = board;
            this.used = new boolean[board.length][board[0].length];
            this.s = word.toCharArray();
            for (int i = 0; i < board.length; i++) {
                char[] cRow = board[i];
                for (int j = 0; j < cRow.length; j++) {
                    if (cRow[j] == word.charAt(0)) {
                        used[i][j] = true;
                        boolean res = existHelper(i, j, 1);
                        if (res) {
                            return res;
                        }
                        used[i][j] = false;
                    }
                }
            }
            return false;
        }

        private boolean existHelper(int i, int j, int strIndex) {
            if (strIndex == s.length) {
                return true;
            }
            if (i > 0) {
                if (check(i - 1, j, strIndex)) {
                    return true;
                }
            }
            if (j > 0) {
                if (check(i, j - 1, strIndex)) {
                    return true;
                }
            }
            if (i < board.length - 1) {
                if (check(i + 1, j, strIndex)) {
                    return true;
                }
            }
            if (j < board[0].length - 1) {
                if (check(i, j + 1, strIndex)) {
                    return true;
                }
            }
            return false;
        }

        private boolean check(int i, int j, int strIndex) {
            if (!used[i][j]) {
                if (board[i][j] == s[strIndex]) {
                    used[i][j] = true;
                    if (existHelper(i, j, strIndex + 1)) {
                        return true;
                    }
                    used[i][j] = false;
                }
            }
            return false;
        }
    }
}
