package com.zm.LeetCodeEx;

import com.alibaba.fastjson.JSON;

/**
 * 64. 最小路径和
 * <p>
 * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * <p>
 * 说明：每次只能向下或者向右移动一步。
 * <p>
 * 示例:
 * <p>
 * 输入: [<br>
 * [1,3,1],<br>
 * [1,5,1],<br>
 * [4,2,1]<br>
 * ]<br>
 * 输出: 7<br>
 * 解释: 因为路径 1→3→1→1→1 的总和最小。<br>
 * <p>
 * 
 * @author zm
 *
 */
public class LEET064 {
	public static void main(String[] args) {
		LEET064 l064 = new LEET064();
		System.out.println(l064.minPathSum(CommonFunctions.stringToIntegerArray2("[[1,3,1],[1,5,1],[4,2,1]]")));

	}

	public int minPathSum(int[][] grid) {
		if (grid.length == 0 || grid[0].length == 0) {
			return -1;
		}
		int[][] pathGrid = new int[grid.length][grid[0].length];
		pathGrid[0][0] = grid[0][0];
		for (int i = 1; i < pathGrid.length; i++) {
			pathGrid[i][0] = pathGrid[i - 1][0] + grid[i][0];
		}
		for (int i = 1; i < pathGrid[0].length; i++) {
			pathGrid[0][i] = pathGrid[0][i - 1] + grid[0][i];
		}

		System.out.println(JSON.toJSONString(pathGrid));
		return pathGrid[grid.length - 1][grid[0].length - 1];
	}

}
