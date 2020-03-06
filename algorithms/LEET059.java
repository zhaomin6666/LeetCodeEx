package com.zm.LeetCodeEx.algorithms;

import com.alibaba.fastjson.JSON;

/**
 * 59. 螺旋矩阵 II
 * <p>
 * 给定一个正整数 n，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。
 * <p>
 * 示例:
 * <p>
 * 输入: 3<br>
 * 输出:<br>
 * [<br>
 * [ 1, 2, 3 ],<br>
 * [ 8, 9, 4 ],<br>
 * [ 7, 6, 5 ]<br>
 * ]
 *
 * 
 * @author zm
 */
public class LEET059 {
	public static void main(String[] args) {
		LEET059 l059 = new LEET059();
		System.out.println(JSON.toJSONString(l059.generateMatrix(1)));
		System.out.println(JSON.toJSONString(l059.generateMatrix(2)));
		System.out.println(JSON.toJSONString(l059.generateMatrix(3)));
	}

	public int[][] generateMatrix(int n) {
		int[][] retMatrix = new int[n][n];
		int[] dr = { 0, 1, 0, -1 };
		int[] dc = { 1, 0, -1, 0 };
		int r = 0, c = 0, di = 0;
		for (int i = 1; i <= n * n; i++) {
			retMatrix[r][c] = i;
			int checkC = c + dc[di];
			int checkR = r + dr[di];
			if (0 <= checkC && checkC < n && 0 <= checkR && checkR < n && retMatrix[checkR][checkC] == 0) {
				c = checkC;
				r = checkR;
			}else {
				di = (di + 1) % 4;
				r += dr[di];
				c += dc[di];
			}
		}
		return retMatrix;
	}
}
