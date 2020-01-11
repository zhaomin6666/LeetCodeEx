package com.zm.LeetCodeEx;

/**
 * 62. 不同路径
 * <p>
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 * <p>
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 * <p>
 * 问总共有多少条不同的路径？
 * <p>
 * 例如，上图是一个7 x 3 的网格。有多少可能的路径？
 * <p>
 * 说明：m 和 n 的值均不超过 100。
 * <p>
 * 示例 1:
 * <p>
 * 输入: m = 3, n = 2 输出: 3 解释: 从左上角开始，总共有 3 条路径可以到达右下角。 1. 向右 -> 向右 -> 向下 2. 向右
 * -> 向下 -> 向右 3. 向下 -> 向右 -> 向右
 * <p>
 * 示例 2:
 * <p>
 * 输入: m = 7, n = 3 输出: 28
 * 
 * @author zm
 *
 */
public class LEET062 {
	public static void main(String[] args) {
		LEET062 l061 = new LEET062();
		System.out.println(l061.uniquePaths(2, 3));
		System.out.println(l061.uniquePaths(23, 12));

	}

	/**
	 * 其实这道题就是由m-1个"下"和n-1个"右"排列组合，无重复的排列数
	 * N = (m+n-2)!/((m-1)! * (n-1)!)
	 * 直接分开计算会导致溢出，所以先做了化简操作
	 * 
	 * @param m
	 * @param n
	 * @return
	 */
	public int uniquePaths(int m, int n) {
		// 化简CommonFunctions.factorial(n+m-2)/CommonFunctions.factorial(n-1)
		// 把较大数用来做化简，这样尽量保证被除数较小不会溢出
		if (m > n) {
			int t = n;
			n = m;
			m = t;
		}
		long ret = 1;
		for (int i = n; i < n + m - 1; i++) {
			ret *= i;
		}
		// return
		// CommonFunctions.factorial(n+m-2)/CommonFunctions.factorial(n-1)/CommonFunctions.factorial(m-1);
		return (int) (ret / CommonFunctions.factorial(m - 1));
	}
}
