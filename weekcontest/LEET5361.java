package com.zm.LeetCodeEx.weekcontest;

/**
 * 双周赛 2020年4月4日
 * <p>
 * 5361. 圆和矩形是否有重叠
 * <p>
 * 给你一个以 (radius, x_center, y_center) 表示的圆和一个与坐标轴平行的矩形 (x1, y1, x2, y2)，其中 (x1,
 * y1) 是矩形左下角的坐标，(x2, y2) 是右上角的坐标。
 * <p>
 * 如果圆和矩形有重叠的部分，请你返回 True ，否则返回 False 。
 * <p>
 * 换句话说，请你检测是否 存在 点 (xi, yi) ，它既在圆上也在矩形上（两者都包括点落在边界上的情况）。
 * <p>
 * 示例 1：
 * <p>
 * 输入：radius = 1, x_center = 0, y_center = 0, x1 = 1, y1 = -1, x2 = 3, y2 = 1
 * <br>
 * 输出：true <br>
 * 解释：圆和矩形有公共点 (1,0)
 * <p>
 * 示例 2：
 * <p>
 * 输入：radius = 1, x_center = 0, y_center = 0, x1 = -1, y1 = 0, x2 = 0, y2 = 1
 * <br>
 * 输出：true
 * <p>
 * 示例 3：
 * <p>
 * 输入：radius = 1, x_center = 1, y_center = 1, x1 = -3, y1 = -3, x2 = 3, y2 = 3
 * <br>
 * 输出：true
 * <p>
 * 示例 4：
 * <p>
 * 输入：radius = 1, x_center = 1, y_center = 1, x1 = 1, y1 = -3, x2 = 2, y2 = -1
 * <br>
 * 输出：false
 * <p>
 * 提示：
 * <p>
 * 1 <= radius <= 2000 <br>
 * -10^4 <= x_center, y_center, x1, y1, x2, y2 <= 10^4 <br>
 * x1 < x2 <br>
 * y1 < y2
 *
 * @author zm
 */
public class LEET5361 {
	public static void main(String[] args) {
		LEET5361 l5361 = new LEET5361();
		System.out.println(l5361.new Solution().checkOverlap(1, 0, 0, 1, -1, 3, 1));
		System.out.println(l5361.new Solution().checkOverlap(1, 0, 0, -1, 0, 0, 1));
		System.out.println(l5361.new Solution().checkOverlap(1, 1, 1, -3, -3, 3, 3));
		System.out.println(l5361.new Solution().checkOverlap(1, 1, 1, 1, -3, 2, -1));

	}

	class Solution {
		public boolean checkOverlap(int radius, int x_center, int y_center, int x1, int y1, int x2, int y2) {
			if (x_center >= x1 && x_center <= x2) {
				if (y_center >= y1 && y_center <= y2) {
					return true;
				}
				if (y_center <= y1) {
					if (y1 - y_center <= radius) {
						return true;
					}
					return false;
				}
				if (y_center >= y2) {
					if (y_center - y2 <= radius) {
						return true;
					}
					return false;
				}
				return false;
			}
			if (x_center <= x1) {
				if (y_center >= y1 && y_center <= y2 && x1 - x_center <= radius) {
					return true;
				}
				if (y_center <= y1) {
					if (cal(x_center, y_center, x1, y1) >= radius) {
						return false;
					} else {
						return true;
					}
				}
				if (y_center >= y2 && y_center - y2 <= radius) {
					if (cal(x_center, y_center, x1, y2) >= radius) {
						return false;
					} else {
						return true;
					}
				}
				return false;
			}
			if (x_center >= x2) {
				if (y_center >= y1 && y_center <= y2 && x_center - x2<= radius) {
					return true;
				}
				if (y_center <= y1) {
					if (cal(x_center, y_center, x2, y1) >= radius) {
						return false;
					} else {
						return true;
					}
				}
				if (y_center >= y2 && y_center - y2 <= radius) {
					if (cal(x_center, y_center, x2, y2) >= radius) {
						return false;
					} else {
						return true;
					}
				}
				return false;
			}
			return false;
		}

		private double cal(int x_center, int y_center, int x1, int y1) {
			return Math.sqrt(Math.pow(x1 - x_center, 2) + Math.pow(y1 - y_center, 2));
		}
	}
}
