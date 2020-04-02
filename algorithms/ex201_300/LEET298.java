package com.zm.LeetCodeEx.algorithms.ex201_300;

import com.alibaba.fastjson.JSON;

/**
 * 
 * 289. 生命游戏
 * 
 * 根据 百度百科 ，生命游戏，简称为生命，是英国数学家约翰·何顿·康威在 1970 年发明的细胞自动机。
 * 
 * 给定一个包含 m × n 个格子的面板，每一个格子都可以看成是一个细胞。每个细胞都具有一个初始状态：1 即为活细胞（live），或 0
 * 即为死细胞（dead）。每个细胞与其八个相邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律：
 * 
 * 如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡； 如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
 * 如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡； 如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
 * 根据当前状态，写一个函数来计算面板上所有细胞的下一个（一次更新后的）状态。下一个状态是通过将上述规则同时应用于当前状态下的每个细胞所形成的，其中细胞的出生和死亡是同时发生的。
 * 
 *  
 * 
 * 示例：
 * 
 * 输入： [<br>
 *   [0,1,0],<br>
 *   [0,0,1],<br>
 *   [1,1,1],<br>
 *   [0,0,0] ]<br>
 * 输出： [<br>
 *   [0,0,0],<br>
 *   [1,0,1],<br>
 *   [0,1,1],<br>
 *   [0,1,0]<br>
 * ]  
 * <p>
 * 进阶：
 * <p>
 * 你可以使用原地算法解决本题吗？请注意，面板上所有格子需要同时被更新：你不能先更新某些格子，然后使用它们的更新后的值再更新其他格子。
 * 本题中，我们使用二维数组来表示面板。原则上，面板是无限的，但当活细胞侵占了面板边界时会造成问题。你将如何解决这些问题？
 * 
 * 
 * @author zm
 *
 */
public class LEET298 {
	public static void main(String[] args) {
		LEET298 l287 = new LEET298();
		int[][] board = { { 0, 1, 0 }, { 0, 0, 1 }, { 1, 1, 1 }, { 0, 0, 0 } };
		l287.new Solution().gameOfLife(board);
		System.out.println(JSON.toJSONString(board));
	}

	/**
	 * 用原地算法解决，使用2,3两个状态来标记死细胞转为活细胞或者活细胞转化为死细胞
	 * @author zm
	 *
	 */
	class Solution {
		int[] di = { -1, -1, -1, 0, 0, 1, 1, 1 };
		int[] dj = { -1, 0, 1, -1, 1, -1, 0, 1 };

		public void gameOfLife(int[][] board) {
			int r = board.length;
			if (r == 0) {
				return;
			}
			int c = board[0].length;
			for (int i = 0; i < r; i++) {
				for (int j = 0; j < c; j++) {
					int cnt = 0;
					for (int direct = 0; direct < 8; direct++) {
						if (i + di[direct] >= 0 && i + di[direct] < r && j + dj[direct] >= 0 && j + dj[direct] < c) {
							int status = board[i + di[direct]][j + dj[direct]];
							if (status == 1 || status == 3) {
								cnt++;
							}
						}
					}
					if (board[i][j] == 1) {
						if (cnt < 2 || cnt > 3) {
							board[i][j] = 3; // 3代表原活细胞变为死细胞
						}
					} else {
						if (cnt == 3) {
							board[i][j] = 2; // 2代表原死细胞变为活细胞
						}
					}
				}
			}

			for (int i = 0; i < board.length; i++) {
				for (int j = 0; j < board[0].length; j++) {
					if (board[i][j] == 2) {
						board[i][j] = 1;
					} else if (board[i][j] == 3) {
						board[i][j] = 0;
					}
				}
			}
		}
	}
}
