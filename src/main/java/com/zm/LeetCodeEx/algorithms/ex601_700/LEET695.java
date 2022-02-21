package com.zm.LeetCodeEx.algorithms.ex601_700;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

/**
 * 695. 岛屿的最大面积
 * <p>
 * 给定一个包含了一些 0 和 1的非空二维数组 grid , 一个 岛屿 是由四个方向 (水平或垂直) 的 1 (代表土地) 构成的组合。你可以假设二维矩阵的四个边缘都被水包围着。
 * <p>
 * 找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为0。)
 * <p>
 * 示例 1:
 * <p>
 * [[0,0,1,0,0,0,0,1,0,0,0,0,0],
 * [0,0,0,0,0,0,0,1,1,1,0,0,0],
 * [0,1,1,0,1,0,0,0,0,0,0,0,0],
 * [0,1,0,0,1,1,0,0,1,0,1,0,0],
 * [0,1,0,0,1,1,0,0,1,1,1,0,0],
 * [0,0,0,0,0,0,0,0,0,0,1,0,0],
 * [0,0,0,0,0,0,0,1,1,1,0,0,0],
 * [0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * 对于上面这个给定矩阵应返回 6。注意答案不应该是11，因为岛屿只能包含水平或垂直的四个方向的‘1’。
 * <p>
 * 示例 2:
 * <p>
 * [[0,0,0,0,0,0,0,0]]
 * 对于上面这个给定的矩阵, 返回 0。
 * <p>
 * 注意: 给定的矩阵grid 的长度和宽度都不超过 50。
 *
 * @author zm
 */
public class LEET695 {
    public static void main(String[] args) {
        LEET695 l084 = new LEET695();
        System.out.println(l084.new Solution3().maxAreaOfIsland(new int[][]{{0, 0, 0, 0}}));
        System.out.println(l084.new Solution3().maxAreaOfIsland(
                new int[][]{{0, 0, 0, 0},
                        {1, 1, 0, 0},
                        {0, 0, 1, 0},
                        {0, 1, 1, 1}
                }));
    }

    /**
     * DFS
     */
    class Solution {
        public int maxAreaOfIsland(int[][] grid) {
            int max = 0;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    max = Math.max(max, dfs(grid, i, j));
                }
            }
            return max;
        }

        private int dfs(int[][] grid, int i, int j) {
            if (i < 0 || j < 0 || i == grid.length || j == grid[0].length || grid[i][j] == 0) {
                return 0;
            }
            grid[i][j] = 0;
            int ret = 1;
            int[] di = {1, 0, -1, 0};
            int[] dj = {0, -1, 0, 1};
            for (int k = 0; k < 4; k++) {
                ret += dfs(grid, i + di[k], j + dj[k]);
            }
            return ret;
        }
    }

    /**
     * DFS用栈实现
     */
    class Solution2 {
        public int maxAreaOfIsland(int[][] grid) {
            int max = 0;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    int cur = 0;
                    Stack<int[]> stack = new Stack<>();
                    stack.push(new int[]{i, j});
                    while (!stack.isEmpty()) {
                        int[] curIndex = stack.pop();
                        int ci = curIndex[0];
                        int cj = curIndex[1];
                        if (ci < 0 || cj < 0 || ci == grid.length || cj == grid[0].length || grid[ci][cj] == 0) {
                            continue;
                        }
                        grid[ci][cj] = 0;
                        cur++;
                        int[] di = {1, 0, -1, 0};
                        int[] dj = {0, -1, 0, 1};
                        for (int k = 0; k < 4; k++) {
                            stack.push(new int[]{ci + di[k], cj + dj[k]});
                        }
                    }
                    max = Math.max(max, cur);
                }
            }
            return max;
        }
    }

    /**
     * 把栈改成队列就可以实现广度优先
     */
    class Solution3 {
        public int maxAreaOfIsland(int[][] grid) {
            int max = 0;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    int cur = 0;
                    Queue<int[]> queue = new ArrayDeque<>();
                    queue.add(new int[]{i, j});
                    while (!queue.isEmpty()) {
                        int[] curIndex = queue.poll();
                        int ci = curIndex[0];
                        int cj = curIndex[1];
                        if (ci < 0 || cj < 0 || ci == grid.length || cj == grid[0].length || grid[ci][cj] == 0) {
                            continue;
                        }
                        grid[ci][cj] = 0;
                        cur++;
                        int[] di = {1, 0, -1, 0};
                        int[] dj = {0, -1, 0, 1};
                        for (int k = 0; k < 4; k++) {
                            queue.add(new int[]{ci + di[k], cj + dj[k]});
                        }
                    }
                    max = Math.max(max, cur);
                }
            }
            return max;
        }
    }
}
