package com.zm.LeetCodeEx.lcof;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;

/**
 * 面试题57 - II. 和为s的连续正数序列
 * <p>
 * 输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
 * <p>
 * 序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。
 * <p>
 * 示例 1：<br>
 * 输入：target = 9<br>
 * 输出：[[2,3,4],[4,5]]
 * <p>
 * 示例 2：
 * <p>
 * 输入：target = 15<br>
 * 输出：[[1,2,3,4,5],[4,5,6],[7,8]]
 * <p>
 * 限制：<br>
 * 1 <= target <= 10^5
 * 
 * @author zm
 *
 */
public class Lcof057 {
	public static void main(String[] args) {
		Lcof057 l057 = new Lcof057();
		System.out.println(JSON.toJSONString(l057.new Solution().findContinuousSequence(9)));
		System.out.println(JSON.toJSONString(l057.new Solution().findContinuousSequence(10)));
		System.out.println(JSON.toJSONString(l057.new Solution().findContinuousSequence(15)));
	}

	/**
	 * 滑动窗口
	 * 
	 * @author zm
	 *
	 */
	class Solution {
		public int[][] findContinuousSequence(int target) {
			if (target <= 2) {
				return new int[0][0];
			}
			int l = 1, r = 2;
			int s = 3;
			List<int[]> retList = new ArrayList<int[]>();
			//LinkedList<Integer> list = new LinkedList<Integer>();
			//list.add(1);
			//list.add(2);
			while (l < r) {
				if (s == target) {
					// List 效率太低，直接在这边新建数组
					int[] a = new int[r - l + 1];
					for (int j = 0; j < r - l + 1; j++) {
						a[j] = l + j;
					}
					retList.add(a);
					//retList.add(list.stream().mapToInt(Integer::valueOf).toArray());
					//list.add(++r);
					s += ++r;
					//list.removeFirst();
					s -= l++;
				} else if (s > target) {
					//list.removeFirst();
					s -= l++;
				} else {
					//list.add(++r);
					s += ++r;
				}
			}
			return retList.toArray(new int[0][]);
		}
	}

	/**
	 * 枚举 + 数学优化
	 * 
	 * 枚举每个正整数为起点，判断累加和是否为target，使用等差数列求和
	 * 
	 * @author zm
	 *
	 */
	class Solution2 {
		public int[][] findContinuousSequence(int target) {
			if (target <= 2) {
				return new int[0][0];
			}
			List<int[]> retList = new ArrayList<int[]>();
			int limit = target >> 1;
			for (int i = 1; i <= limit; i++) {
				double delta = 1 - 4 * (i - 1.0 * i * i - 2 * target);
				if (delta < 0) {
					continue;
				}
				double delta_sqrt = Math.sqrt(delta);
				if (Double.valueOf(delta_sqrt).equals(Double.valueOf((int) delta_sqrt))
						&& ((int) delta_sqrt - 1) % 2 == 0) {
					int y = ((int) delta_sqrt - 1) / 2;
					if (i < y) {
						int[] a = new int[y - i + 1];
						for (int j = 0; j < y - i + 1; j++) {
							a[j] = i + j;
						}
						retList.add(a);
					}
				}
			}
			return retList.toArray(new int[0][]);
		}
	}
}
