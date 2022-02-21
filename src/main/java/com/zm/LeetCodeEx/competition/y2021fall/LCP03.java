package com.zm.LeetCodeEx.competition.y2021fall;

import com.alibaba.fastjson.JSON;

import java.util.HashSet;

/**
 * 2. 心算挑战
 * 「力扣挑战赛」心算项目的挑战比赛中，要求选手从 N 张卡牌中选出 cnt 张卡牌，若这 cnt 张卡牌数字总和为偶数，则选手成绩「有效」且得分为 cnt 张卡牌数字总和。
 * 给定数组 cards 和 cnt，其中 cards[i] 表示第 i 张卡牌上的数字。 请帮参赛选手计算最大的有效得分。若不存在获取有效得分的卡牌方案，则返回 0。
 * <p>
 * 示例 1：
 * <p>
 * 输入：cards = [1,2,8,9], cnt = 3
 * <p>
 * 输出：18
 * <p>
 * 解释：选择数字为 1、8、9 的这三张卡牌，此时可获得最大的有效得分 1+8+9=18。
 * <p>
 * 示例 2：
 * <p>
 * 输入：cards = [3,3,1], cnt = 1
 * <p>
 * 输出：0
 * <p>
 * 解释：不存在获取有效得分的卡牌方案。
 * <p>
 * 提示：
 * <p>
 * 1 <= cnt <= cards.length <= 10^5
 * 1 <= cards[i] <= 1000
 */
public class LCP03 {

    public static void main(String[] args) {
        LCP03 LCP03 = new LCP03();
        System.out.println(LCP03.new Solution().flipChess(
                new String[]{".......", ".......", ".......", "X......", ".O.....", "..O....", "....OOX"}));
        System.out.println(LCP03.new Solution().flipChess(
                new String[]{"....X.", "....X.", "XOOO..", "......", "......"}));
        System.out.println(LCP03.new Solution().flipChess(
                new String[]{".X.", ".O.", "XO."}));
    }

    class Solution {
        int[][] dirs = new int[][]{
                {1, -1}, {1, 0}, {1, 1},
                {0, -1}, {0, 1},
                {-1, -1}, {-1, 0}, {-1, 1}};

        public int flipChess(String[] chessboard) {
            int n = chessboard.length;
            int m = chessboard[0].length();
            char[][] cs = generateCs(n, m, chessboard);

            HashSet<Integer> canPlaceX = new HashSet<>();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (cs[i][j] == 'O') {
                        checkPlaceX(i, j, n, m, cs, canPlaceX);
                    }
                }
            }
            System.out.println(JSON.toJSONString(cs));
            System.out.println(JSON.toJSONString(canPlaceX));
            int max = 0;
            for (int canPlace : canPlaceX) {
                int i = canPlace / 10;
                int j = canPlace % 10;
                System.out.println("StartPlace i:" + i + ",j:" + j);
                int sum = place(i, j, n, m, cs);
                max = Math.max(sum, max);
                cs = generateCs(n, m, chessboard);
            }
            return max == 0 ? 0 : max - 1;
        }

        private char[][] generateCs(int n, int m, String[] chessboard) {
            char[][] cs = new char[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    cs[i][j] = chessboard[i].charAt(j);
                }
            }
            return cs;
        }

        private int place(int i, int j, int n, int m, char[][] cs) {
            int sum = 1;
            if (cs[i][j] == 'X') {
                return 0;
            }
            cs[i][j] = 'X';
            System.out.println(JSON.toJSONString(cs));
            for (int[] dir : dirs) {
                int checki = i + dir[0];
                int checkj = j + dir[1];
                if (checki >= 0 && checki < n && checkj >= 0 && checkj < m) {
                    if (cs[checki][checkj] == 'O') {
                        // 继续查询后直到有X
                        int nextChecki = checki + dir[0];
                        int nextCheckj = checkj + dir[1];
                        boolean isX = false;
                        int cntO = 1;
                        while (nextChecki >= 0 && nextChecki < n && nextCheckj >= 0 && nextCheckj < m) {
                            if (cs[nextChecki][nextCheckj] == 'O') {
                                cntO++;
                                // 查找下一个
                                nextChecki += dir[0];
                                nextCheckj += dir[1];
                            } else if (cs[nextChecki][nextCheckj] == 'X') {
                                isX = true;
                                break;
                            } else {
                                break;
                            }
                        }
                        if (isX) {
                            while (cntO > 0) {
                                sum += place(checki, checkj, n, m, cs);
                                checki += dir[0];
                                checkj += dir[1];
                                cntO--;
                            }
                        }
                    }
                }

            }
            return sum;
        }

        private void checkPlaceX(int i, int j, int n, int m, char[][] cs, HashSet<Integer> canPlaceX) {
            for (int[] dir : dirs) {
                int checki = i + dir[0];
                int checkj = j + dir[1];
                while (checki >= 0 && checki < n && checkj >= 0 && checkj < m) {
                    if (cs[checki][checkj] == 'X') {
                        // 反向查询后结束
                        int reverseChecki = i - dir[0];
                        int reverseCheckj = j - dir[1];
                        while (reverseChecki >= 0 && reverseChecki < n && reverseCheckj >= 0 && reverseCheckj < m) {
                            if (cs[reverseChecki][reverseCheckj] == 'O') {
                                // 查找下一个
                                reverseChecki -= dir[0];
                                reverseCheckj -= dir[1];
                            } else if (cs[reverseChecki][reverseCheckj] == '.') {
                                // 找到了
                                canPlaceX.add(reverseChecki * 10 + reverseCheckj);
                                break;
                            } else {
                                break;
                            }
                        }
                        break;
                    } else if (cs[checki][checkj] == 'O') {
                        // 查找下一个
                        checki += dir[0];
                        checkj += dir[1];
                    } else {
                        break;
                    }
                }

            }
        }
    }

}
