package com.zm.LeetCodeEx.algorithms.ex1_100;

import com.alibaba.fastjson.JSON;

/**
 * 编写一个程序，通过已填充的空格来解决数独问题。
 * <p>
 * 一个数独的解法需遵循如下规则：
 * <p>
 * 数字 1-9 在每一行只能出现一次。 数字 1-9 在每一列只能出现一次。 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 * 空白格用 '.' 表示。 [</br>
 * ["5","3",".",".","7",".",".",".","."],</br>
 * ["6",".",".","1","9","5",".",".","."],</br>
 * [".","9","8",".",".",".",".","6","."],</br>
 * ["8",".",".",".","6",".",".",".","3"],</br>
 * ["4",".",".","8",".","3",".",".","1"],</br>
 * ["7",".",".",".","2",".",".",".","6"],</br>
 * [".","6",".",".",".",".","2","8","."],</br>
 * [".",".",".","4","1","9",".",".","5"],</br>
 * [".",".",".",".","8",".",".","7","9"]</br>
 * ]
 * <p>
 * 一个数独。
 * <p>
 * [</br>
 * ["5","3","4","6","7","8","9","1","2"],</br>
 * ["6","7","2","1","9","5","3","4","8"],</br>
 * ["1","9","8","3","4","2","5","6","7"],</br>
 * ["8","5","9","7","6","1","4","2","3"],</br>
 * ["4","2","6","8","5","3","7","9","1"],</br>
 * ["7","1","3","9","2","4","8","5","6"],</br>
 * ["9","6","1","5","3","7","2","8","4"],</br>
 * ["2","8","7","4","1","9","6","3","5"],</br>
 * ["3","4","5","2","8","6","1","7","9"]</br>
 * ]
 * <p>
 * <p>
 * Note: 给定的数独序列只包含数字 1-9 和字符 '.' 。 你可以假设给定的数独只有唯一解。 给定数独永远是 9x9 形式的。
 *
 * @author zm
 */
public class LEET037 {
    public static void main(String[] args) {
        LEET037 l037 = new LEET037();
        char[][] board1 = {{'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'}, {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'}, {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'}, {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'}, {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
        l037.solveSudoku(board1);
        System.out.println(JSON.toJSONString(board1));
    }

    private boolean isSolved = false;
    private int[][] intBoard = new int[9][9];
    private int[] row = new int[9];
    private int[] col = new int[9];
    private int[] box = new int[9];

    public void solveSudoku(char[][] board) {
        intBoard = convertBoardCharToInt(board);
        //System.out.println(JSON.toJSONString(intBoard));
        backTrack(0, 0);
        //System.out.println(JSON.toJSONString(intBoard));
        convertBoardIntToChar(intBoard, board);
        //System.out.println(JSON.toJSONString(board));
    }

    private void convertBoardIntToChar(int[][] intBoard, char[][] charBoard) {
        for (int i = 0; i < intBoard.length; i++) {
            for (int j = 0; j < intBoard[i].length; j++) {
                if (intBoard[i][j] == 0) {
                    charBoard[i][j] = '.';
                } else {
                    charBoard[i][j] = (char) (intBoard[i][j] + '0');
                }
            }
        }
    }

    private void backTrack(int i, int j) {
        if (intBoard[i][j] == 0) {
            for (int num = 1; num < 10; num++) {
                if (couldPlace(i, j, num)) {
                    placeNum(i, j, num);
                    placeNextNum(i, j);
                    if (!isSolved) {
                        removeNum(i, j);
                    }
                }
            }
        } else {
            placeNextNum(i, j);
        }

    }

    private void removeNum(int i, int j) {
        row[i] -= (1 << intBoard[i][j]);
        col[j] -= (1 << intBoard[i][j]);
        int boxNum = i / 3 * 3 + j / 3;
        box[boxNum] -= (1 << intBoard[i][j]);
        intBoard[i][j] = 0;
    }

    private void placeNum(int i, int j, int num) {
        intBoard[i][j] = num;
        row[i] += 1 << num;
        col[j] += 1 << num;
        int boxNum = i / 3 * 3 + j / 3;
        box[boxNum] += 1 << num;
    }

    private boolean couldPlace(int i, int j, int num) {
        int boxNum = i / 3 * 3 + j / 3;
        return ((row[i] >> num) % 2 + (col[j] >> num) % 2 + (box[boxNum] >> num) % 2) == 0;
    }

    private void placeNextNum(int i, int j) {
        if (i == 8 && j == 8) {
            isSolved = true;
        } else {
            if (j == 8) {
                backTrack(i + 1, 0);
            } else {
                backTrack(i, j + 1);
            }
        }
    }

    private int[][] convertBoardCharToInt(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == '.') {
                    intBoard[i][j] = 0;
                } else {
                    intBoard[i][j] = board[i][j] - '0';
                    placeNum(i, j, intBoard[i][j]);
                }
            }
        }
        return intBoard;
    }
}
