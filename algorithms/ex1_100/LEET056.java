package com.zm.LeetCodeEx.algorithms.ex1_100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import com.alibaba.fastjson.JSON;

/**
 * 56. 合并区间
 * <p>
 * 给出一个区间的集合，请合并所有重叠的区间。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [[1,3],[2,6],[8,10],[15,18]]<br>
 * 输出: [[1,6],[8,10],[15,18]]<br>
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].<br>
 * <p>
 * 示例 2:
 * <p>
 * 输入: [[1,4],[4,5]]<br>
 * 输出: [[1,5]]<br>
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。<br>
 * 
 * 
 * @author zm
 */
public class LEET056 {
	public static void main(String[] args) {
		LEET056 l056 = new LEET056();
		int[][] intervals = { { 1, 3 }, { 2, 6 }, { 8, 10 }, { 15, 18 } };
		int[][] intervals2 = { { 1, 4 }, { 4, 5 } };
		System.out.println(JSON.toJSONString(l056.merge(intervals)));
		System.out.println(JSON.toJSONString(l056.merge(intervals2)));
	}

	public int[][] merge(int[][] intervals) {
		if (intervals.length == 0) {
			return intervals;
		}
		Arrays.sort(intervals, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] < o2[0] ? -1 : o1[0] == o2[0] ? 0 : 1;
			}

		});
		LinkedList<int[]> retList = new LinkedList<int[]>();
		retList.add(intervals[0]);
		for (int i = 1; i < intervals.length; i++) {
			int curMaxTo = retList.getLast()[1];
			if (curMaxTo >= intervals[i][0]) {
				retList.getLast()[1] = Math.max(intervals[i][1], curMaxTo);
			} else {
				retList.add(intervals[i]);
			}
		}
		return (int[][]) retList.toArray(new int[retList.size()][2]);
	}

	/**
	 * 由于所有数字都是整数，用一个数组来标识当前位置数字是否在区域内
	 * @author zm
	 *
	 */
	class Solution2 {
		public int[][] merge(int[][] intervals) {
			int max = 0;
			for (int i = 0; i < intervals.length; i++) {
				for (int j = 0; j < intervals[0].length; j++) {
					if (intervals[i][j] > max) {
						max = intervals[i][j];
					}
				}
			}
			int[] base = new int[max + 1];
			for (int i = 0; i < intervals.length; i++) {
				int a = intervals[i][0];
				int b = intervals[i][1];
				for (int k = a; k < b; k++) {
					base[k] = 1;
				}
				if (base[b] != 1) {
					base[b] = -1;
				}
			}
			List<int[]> ret = new ArrayList<>();
			boolean flag = false;
			int[] temp = new int[2];
			for (int i = 0; i < base.length; i++) {
				if ((flag == false || i == 0) && base[i] == 1) {
					flag = true;
					temp = new int[2];
					temp[0] = i;
				}
				if (base[i] == -1) {
					if (flag == true) {
						temp[1] = i;
						ret.add(temp);
					} else {
						ret.add(new int[] { i, i });
					}
					flag = false;
				}
			}
			int[][] result = new int[ret.size()][];
			result = ret.toArray(result);
			return result;
		}
	}
}
