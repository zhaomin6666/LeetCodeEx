package com.zm.LeetCodeEx.algorithms;

/**
 * 836. 矩形重叠
 * <p>
 * 矩形以列表 [x1, y1, x2, y2] 的形式表示，其中 (x1, y1) 为左下角的坐标，(x2, y2) 是右上角的坐标。
 * <p>
 * 如果相交的面积为正，则称两矩形重叠。需要明确的是，只在角或边接触的两个矩形不构成重叠。
 * <p>
 * 给出两个矩形，判断它们是否重叠并返回结果。
 * 
 * 示例 1：<br>
 * 输入：rec1 = [0,0,2,2], rec2 = [1,1,3,3]<br>
 * 输出：true
 * <p>
 * 示例 2：<br>
 * 输入：rec1 = [0,0,1,1], rec2 = [1,0,2,1]<br>
 * 输出：false  
 * <p>
 * 提示：
 * <p>
 * 两个矩形 rec1 和 rec2 都以含有四个整数的列表的形式给出。<br>
 * 矩形中的所有坐标都处于 -10^9 和 10^9 之间。<br>
 * x 轴默认指向右，y 轴默认指向上。<br>
 * 你可以仅考虑矩形是正放的情况。<br>
 * 
 * @author zm
 */
public class LEET836 {
	public static void main(String[] args) {
		LEET836 l836 = new LEET836();
		// System.out.println(l836.new Solution().isRectangleOverlap(new int[] { 0, 0,
		// 2, 2 }, new int[] { 1, 1, 3, 3 }));
		// System.out.println(l836.new Solution().isRectangleOverlap(new int[] { 0, 0,
		// 1, 1 }, new int[] { 1, 0, 2, 1 }));
		System.out.println(
				l836.new Solution().isRectangleOverlap(new int[] { 7, 8, 13, 15 }, new int[] { 10, 8, 12, 20 }));
	}

	class Solution {
		public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
			return !(rec1[2] <= rec2[0] || // left rec1在rec2左边
					rec1[3] <= rec2[1] || // bottom rec1在rec2下边
					rec1[0] >= rec2[2] || // right rec1在rec2右边
					rec1[1] >= rec2[3]); // top rec1在rec2上边
		}
	}

	/**
	 * 官方题解2：检查区域 将两个矩形的长宽两条边映射到x轴和y轴上，如果两个矩形有重叠，那么映射在x轴的两条线段必有重叠，
	 * y轴的也一样，这样就把问题看作一维线段是否有交集的问题
	 * 
	 * @author zm
	 *
	 */
	class Solution2 {
		public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
			return (Math.min(rec1[2], rec2[2]) > Math.max(rec1[0], rec2[0])
					&& Math.min(rec1[3], rec2[3]) > Math.max(rec1[1], rec2[1]));
		}
	}
}
