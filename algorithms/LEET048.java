package com.zm.LeetCodeEx.algorithms;

import com.alibaba.fastjson.JSON;

/**
 * 48. 旋转图像
 * <p>
 * 给定一个 n × n 的二维矩阵表示一个图像。
 * <p>
 * 将图像顺时针旋转 90 度。
 * <p>
 * 说明：
 * <p>
 * 你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。
 * <p>
 * 示例 1:
 * <p>
 * 给定 matrix = <br>
 * [<br>
 * [1,2,3],<br>
 * [4,5,6],<br>
 * [7,8,9]<br>
 * ],
 * 
 * 原地旋转输入矩阵，使其变为: <br>
 * [ <br>
 * [7,4,1], <br>
 * [8,5,2], <br>
 * [9,6,3] <br>
 * ]
 * <p>
 * 示例 2:
 * 
 * 给定 matrix = <br>
 * [ <br>
 * [ 5, 1, 9,11], <br>
 * [ 2, 4, 8,10], <br>
 * [13, 3, 6, 7], <br>
 * [15,14,12,16] <br>
 * ],
 * 
 * 原地旋转输入矩阵，使其变为: <br>
 * [ <br>
 * [15,13, 2, 5], <br>
 * [14, 3, 4, 1], <br>
 * [12, 6, 8, 9], <br>
 * [16, 7,10,11] <br>
 * ]
 *
 * 
 * @author zm
 */
public class LEET048 {
	public static void main(String[] args) {
		LEET048 l048 = new LEET048();
		int[][] matrix1 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		int[][] matrix2 = { { 5, 1, 9, 11 }, { 2, 4, 8, 10 }, { 13, 3, 6, 7 }, { 15, 14, 12, 16 } };
		int[][] matrix3 = { { 5, 1, 9, 11, 17 }, { 2, 4, 8, 10, 18 }, { 13, 3, 6, 7, 19 }, { 15, 14, 12, 16, 20 },
				{ 21, 22, 23, 24, 25 } };
		l048.rotate(matrix1);
		l048.rotate2(matrix2);
		l048.rotate2(matrix3);
		System.out.println(JSON.toJSONString(matrix1));
		System.out.println(JSON.toJSONString(matrix2));
		System.out.println(JSON.toJSONString(matrix3));
	}

	/**
	 * 从外到内旋转一条边上除了最后一位的数 </br>
	 * [</br>
	 * [ 1, 2, 3, 4, 5],</br>
	 * [ 6, 7, 8, 9,10],</br>
	 * [11,12,13,14,15],</br>
	 * [16,17,18,19,20],</br>
	 * [21,22,23,24,25]] </br>
	 * 第一步：旋转 [1,2,3,4] ---> [5,10,15,20] ---> [25,24,23,22] ---> [21,16,11,6] </br>
	 * 外层完成</br>
	 * 第二步：旋转 [7,8] ---> [9,14] ---> [19,18] ---> [17,12] </br>
	 * 第二层完成 </br>
	 * 最内侧不需要
	 * 
	 * @param matrix
	 */
	public void rotate(int[][] matrix) {
		for (int i = 0; i < (matrix.length + 1) >> 1; i++) {
			for (int j = i; j < Math.max(matrix.length - i - 1, i + 1); j++) {
				int temp = matrix[i][j];
				matrix[i][j] = matrix[matrix.length - j - 1][i];
				matrix[matrix.length - j - 1][i] = matrix[matrix.length - i - 1][matrix.length - j - 1];
				matrix[matrix.length - i - 1][matrix.length - j - 1] = matrix[j][matrix.length - i - 1];
				matrix[j][matrix.length - i - 1] = temp;
			}
		}
	}

	/**
	 * 沿右上--左下翻转 ====> 顺旋转270度+左右翻转 </br>
	 * 上下翻转 ====> 逆旋转180度+左右翻转 </br>
	 * ps:居然右边可以看做加减法便相当于顺旋转90度
	 * 
	 * @param matrix
	 */
	public void rotate2(int[][] matrix) {
		// 沿右上--左下翻转
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length - i - 1; j++) {
				int temp = matrix[i][j];
				matrix[i][j] = matrix[matrix[i].length - j - 1][matrix[i].length - i - 1];
				matrix[matrix[i].length - j - 1][matrix[i].length - i - 1] = temp;
			}
		}
		// 上下翻转
		for (int i = 0; i < (matrix.length+1)>>1; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				int temp = matrix[i][j];
				matrix[i][j] = matrix[matrix[i].length - i - 1][j];
				matrix[matrix[i].length - i - 1][j] = temp;
			}
		}
	}
}
