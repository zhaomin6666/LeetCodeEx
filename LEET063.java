package com.zm.LeetCodeEx;

/**
 * 62. 不同路径 II
 * <p>
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 * <p>
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 * <p>
 * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
 * <p>
 * 网格中的障碍物和空位置分别用 1 和 0 来表示。
 * <p>
 * 说明：m 和 n 的值均不超过 100。
 * <p>
 * 示例 1:
 * <p>
 * 输入:<br>
 * [<br>
 *   [0,0,0],<br>
 *   [0,1,0],<br>
 *   [0,0,0]<br>
 * ]<br>
 * 输出: 2<br>
 * 解释:<br>
 * 3x3 网格的正中间有一个障碍物。<br>
 * 从左上角到右下角一共有 2 条不同的路径：<br>
 * 1. 向右 -> 向右 -> 向下 -> 向下<br>
 * 2. 向下 -> 向下 -> 向右 -> 向右<br>
 *
 * @author zm
 */
public class LEET063 {
    public static void main(String[] args) {
        LEET063 l063 = new LEET063();
        int[][] grid = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        int[][] grid2 = {{0, 0, 0, 0, 0, 0}, {1, 0, 0, 0, 0, 0}, {0, 1, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 0}, {0, 0, 0, 0, 0, 0}};
        System.out.println(l063.uniquePathsWithObstacles(grid));
        System.out.println(l063.uniquePathsWithObstacles(grid2));
    }

    /**
     * 动态规划
     * 要到达某一个点只有可能是从上或者从左，所以到这个点的路线就是到达它左边的点的路线数+到达他上面的点的路线数。
     * 注意第一行特殊处理一下
     *
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int[][] retTable = new int[obstacleGrid.length][obstacleGrid[0].length];
        if (obstacleGrid[0][0] == 1) {
            return 0;
        }
        boolean isFirstRowHasObstacle = false;
        for (int i = 0; i < obstacleGrid[0].length; i++) {
            if (isFirstRowHasObstacle) {
                retTable[0][i] = 0;
            } else if (obstacleGrid[0][i] == 0) {
                retTable[0][i] = 1;
            } else {
                retTable[0][i] = 0;
                isFirstRowHasObstacle = true;
            }
        }

        for (int i = 1; i < obstacleGrid.length; i++) {
            for (int j = 0; j < obstacleGrid[0].length; j++) {
                if (obstacleGrid[i][j] == 1) {
                    retTable[i][j] = 0;
                } else if (j == 0) {
                    retTable[i][j] = retTable[i - 1][j];
                } else {
                    retTable[i][j] = retTable[i - 1][j] + retTable[i][j - 1];
                }
            }
        }
        return retTable[obstacleGrid.length - 1][obstacleGrid[0].length - 1];
    }

}
