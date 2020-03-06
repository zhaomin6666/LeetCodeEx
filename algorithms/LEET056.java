package com.zm.LeetCodeEx.algorithms;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

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
}
