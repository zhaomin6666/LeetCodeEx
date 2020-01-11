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
 * 
 * @author zm
 *
 */
public class LEET063 {
	public static void main(String[] args) {
		LEET063 l063 = new LEET063();
		System.out.println(l063.uniquePaths(2, 3));
		System.out.println(l063.uniquePaths(23, 12));

	}

	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        
    }
}
