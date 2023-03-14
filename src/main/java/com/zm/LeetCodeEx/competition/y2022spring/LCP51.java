package com.zm.LeetCodeEx.competition.y2022spring;

/**
 * LCP 51. 烹饪料理
 * <p>
 * 欢迎各位勇者来到力扣城，城内设有烹饪锅供勇者制作料理，为自己恢复状态。
 * <p>
 * 勇者背包内共有编号为 0 ~ 4 的五种食材，其中 meterials[j] 表示第 j 种食材的数量。通过这些食材可以制作若干料理，cookbooks[i][j] 表示制作第 i 种料理需要第 j 种食材的数量，而 attribute[i] = [x,y] 表示第 i 道料理的美味度 x 和饱腹感 y。
 * <p>
 * 在饱腹感不小于 limit 的情况下，请返回勇者可获得的最大美味度。如果无法满足饱腹感要求，则返回 -1。
 * <p>
 * 注意：
 * <p>
 * 每种料理只能制作一次。
 * 示例 1：
 * <p>
 * 输入：meterials = [3,2,4,1,2]
 * cookbooks = [[1,1,0,1,2],[2,1,4,0,0],[3,2,4,1,0]]
 * attribute = [[3,2],[2,4],[7,6]]
 * limit = 5
 * <p>
 * 输出：7
 * <p>
 * 解释：
 * 食材数量可以满足以下两种方案：
 * 方案一：制作料理 0 和料理 1，可获得饱腹感 2+4、美味度 3+2
 * 方案二：仅制作料理 2， 可饱腹感为 6、美味度为 7
 * 因此在满足饱腹感的要求下，可获得最高美味度 7
 * <p>
 * 示例 2：
 * <p>
 * 输入：meterials = [10,10,10,10,10]
 * cookbooks = [[1,1,1,1,1],[3,3,3,3,3],[10,10,10,10,10]]
 * attribute = [[5,5],[6,6],[10,10]]
 * limit = 1
 * <p>
 * 输出：11
 * <p>
 * 解释：通过制作料理 0 和 1，可满足饱腹感，并获得最高美味度 11
 * <p>
 * 提示：
 * <p>
 * meterials.length == 5
 * 1 <= cookbooks.length == attribute.length <= 8
 * cookbooks[i].length == 5
 * attribute[i].length == 2
 * 0 <= meterials[i], cookbooks[i][j], attribute[i][j] <= 20
 * 1 <= limit <= 100
 */
public class LCP51 {

	public static void main(String[] args) {
		System.out.println(new Solution().perfectMenu(new int[]{3, 2, 4, 1, 2},
				new int[][]{{1, 1, 0, 1, 2}, {2, 1, 4, 0, 0}, {3, 2, 4, 1, 0}},
				new int[][]{{3, 2}, {2, 4}, {7, 6}}, 5));
		System.out.println(new Solution().perfectMenu(new int[]{3, 2, 4, 1, 2},
				new int[][]{{1, 1, 0, 1, 2}, {2, 1, 4, 0, 0}, {3, 2, 4, 1, 0}},
				new int[][]{{3, 2}, {2, 4}, {7, 6}}, 100));
	}

	static class Solution {
		int max = -1;
		int[] materials;
		int[][] cookbooks;
		int[][] attribute;

		public int perfectMenu(int[] materials, int[][] cookbooks, int[][] attribute, int limit) {
			this.materials = materials;
			this.cookbooks = cookbooks;
			this.attribute = attribute;
			backTrack(0, 0, limit);
			return max;
		}

		private void backTrack(int cookbookIndex, int current, int limit) {
			if (cookbookIndex == cookbooks.length) {
				if (limit <= 0) {
					max = Math.max(current, max);
				}
			}
			else {
				for (int i = cookbookIndex; i < cookbooks.length; i++) {
					if (!canCook(i)) {
						if (limit <= 0) {
							max = Math.max(current, max);
						}
					}
					else {
						cook(i);
						current += attribute[i][0];
						limit -= attribute[i][1];
						backTrack(i + 1, current, limit);
						redoCook(i);
						current -= attribute[i][0];
						limit += attribute[i][1];
					}
				}
			}
		}

		private boolean canCook(int cookbookIndex) {
			int[] currentCook = cookbooks[cookbookIndex];
			for (int i = 0; i < currentCook.length; i++) {
				if (currentCook[i] > materials[i]) {
					return false;
				}
			}
			return true;
		}

		private void cook(int cookbookIndex) {
			int[] currentCook = cookbooks[cookbookIndex];
			for (int i = 0; i < currentCook.length; i++) {
				materials[i] -= currentCook[i];
			}
		}

		private void redoCook(int cookbookIndex) {
			int[] currentCook = cookbooks[cookbookIndex];
			for (int i = 0; i < currentCook.length; i++) {
				materials[i] += currentCook[i];
			}
		}
	}
}

