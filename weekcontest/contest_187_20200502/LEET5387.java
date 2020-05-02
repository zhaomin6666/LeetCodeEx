package com.zm.LeetCodeEx.weekcontest.contest_187_20200502;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

import com.zm.LeetCodeEx.CommonFunctions;

/**
 * 双周赛 2020年5月2日
 * <p>
 * 5387. 每个人戴不同帽子的方案数
 * <p>
 * 总共有 n 个人和 40 种不同的帽子，帽子编号从 1 到 40 。
 * <p>
 * 给你一个整数列表的列表 hats ，其中 hats[i] 是第 i 个人所有喜欢帽子的列表。
 * <p>
 * 请你给每个人安排一顶他喜欢的帽子，确保每个人戴的帽子跟别人都不一样，并返回方案数。
 * <p>
 * 由于答案可能很大，请返回它对 10^9 + 7 取余后的结果。
 * <p>
 * 示例 1：
 * <p>
 * 输入：hats = [[3,4],[4,5],[5]] <br>
 * 输出：1 <br>
 * 解释：给定条件下只有一种方法选择帽子。 <br>
 * 第一个人选择帽子 3，第二个人选择帽子 4，最后一个人选择帽子 5。
 * <p>
 * 示例 2：
 * <p>
 * 输入：hats = [[3,5,1],[3,5]] <br>
 * 输出：4 <br>
 * 解释：总共有 4 种安排帽子的方法： <br>
 * (3,5)，(5,3)，(1,3) 和 (1,5) <br>
 * <p>
 * 示例 3：
 * <p>
 * 输入：hats = [[1,2,3,4],[1,2,3,4],[1,2,3,4],[1,2,3,4]] <br>
 * 输出：24 <br>
 * 解释：每个人都可以从编号为 1 到 4 的帽子中选。 <br>
 * (1,2,3,4) 4 个帽子的排列方案数为 24 。 <br>
 * <p>
 * 示例 4：
 * <p>
 * 输入：hats = [[1,2,3],[2,3,5,6],[1,3,7,9],[1,8,9],[2,5,7]] <br>
 * 输出：111  
 * <p>
 * <p>
 * 提示：
 * <p>
 * n == hats.length <br>
 * 1 <= n <= 10 <br>
 * 1 <= hats[i].length <= 40 <br>
 * 1 <= hats[i][j] <= 40 <br>
 * hats[i] 包含一个数字互不相同的整数列表。 <br>
 *
 * @author zm
 */
public class LEET5387 {
	public static void main(String[] args) {
		LEET5387 l5387 = new LEET5387();
		System.out.println(
				l5387.new Solution().numberWays(CommonFunctions.stringToIntegerArrayList("[[3,4],[4,5],[5]]")));
		System.out
				.println(l5387.new Solution().numberWays(CommonFunctions.stringToIntegerArrayList("[[3,5,1],[3,5]]")));
		System.out.println(l5387.new Solution()
				.numberWays(CommonFunctions.stringToIntegerArrayList("[[1,2,3,4],[1,2,3,4],[1,2,3,4],[1,2,3,4]]")));
		System.out.println(l5387.new Solution()
				.numberWays(CommonFunctions.stringToIntegerArrayList("[[1,2,3],[2,3,5,6],[1,3,7,9],[1,8,9],[2,5,7]]")));
		System.out.println(l5387.new Solution().numberWays(CommonFunctions.stringToIntegerArrayList(
				"[[1,2,4,6,7,8,9,11,12,13,14,15,16,18,19,20,23,24,25],[2,5,16],[1,4,5,6,7,8,9,12,15,16,17,19,21,22,24,25],[1,3,6,8,11,12,13,16,17,19,20,22,24,25],[11,12,14,16,18,24],[2,3,4,5,7,8,13,14,15,17,18,21,24],[1,2,6,7,10,11,13,14,16,18,19,21,23],[1,3,6,7,8,9,10,11,12,14,15,16,18,20,21,22,23,24,25],[2,3,4,6,7,10,12,14,15,16,17,21,22,23,24,25]]")));
	}

	class Solution {
		int sum = 0;
		public int numberWays(List<List<Integer>> hats) {
			PriorityQueue<HashSet<Integer>> priorityQueue = new PriorityQueue<>(new Comparator<HashSet<Integer>>() {
				@Override
				public int compare(HashSet<Integer> o1, HashSet<Integer> o2) {
					return o1.size() - o2.size();
				}
			});
			for (List<Integer> item : hats) {
				priorityQueue.add(new HashSet<>(item));
			}
			back(priorityQueue);
			return sum;
		}

		private void back(PriorityQueue<HashSet<Integer>> priorityQueue) {
			if (priorityQueue.size() == 1) {
				sum += priorityQueue.poll().size();
				sum %= 100000007;
				return;
			}
			HashSet<Integer> minList = priorityQueue.poll();
			for (int item : minList) {
				PriorityQueue<HashSet<Integer>> priorityQueueTemp = new PriorityQueue<>(
						new Comparator<HashSet<Integer>>() {
							@Override
							public int compare(HashSet<Integer> o1, HashSet<Integer> o2) {
								return o1.size() - o2.size();
							}
						});
				for (HashSet<Integer> setPre : priorityQueue) {
					HashSet<Integer> setTemp = new HashSet<>(setPre);
					setTemp.remove(item);
					if (setTemp.isEmpty()) {
						return;
					} else {
						priorityQueueTemp.add(setTemp);
					}
				}
				back(priorityQueueTemp);
			}
		}
	}

	class Solution2 {
		int sum = 0;
		boolean[] hatseen = new boolean[40];
		List<List<Integer>> hatInput;
		int l;

		public int numberWays(List<List<Integer>> hats) {
			l = hats.size();
			hatInput = hats;
			back(0);
			return sum;
		}

		private void back(int index) {
			if (index == l) {
				sum++;
				sum %= 100000007;
				return;
			}
			List<Integer> list = hatInput.get(index);
			for (int i = 0; i < list.size(); i++) {
				int hat = list.get(i);
				if (!hatseen[hat]) {
					hatseen[hat] = true;
					back(index + 1);
					hatseen[hat] = false;
				}
			}
		}
	}

	/**
	 * 尝试优化1，仍然超时
	 * 
	 * @author zm
	 *
	 */
	class Solution3 {
		int sum = 0;

		public int numberWays(List<List<Integer>> hats) {
			MyClass myClass = new MyClass();
			for (List<Integer> item : hats) {
				myClass.add(new HashSet<>(item));
			}
			back(myClass);
			return sum;
		}

		private void back(MyClass priorityQueue) {
			if (priorityQueue.size() == 1) {
				sum += priorityQueue.min.size();
				sum %= 100000007;
				return;
			}
			HashSet<Integer> minList = priorityQueue.getMin();
			for (int item : minList) {
				MyClass myClassTemp = new MyClass();
				for (HashSet<Integer> setPre : priorityQueue.list.values()) {
					HashSet<Integer> setTemp = new HashSet<>(setPre);
					setTemp.remove(item);
					if (setTemp.isEmpty()) {
						return;
					} else {
						myClassTemp.add(setTemp);
					}
				}
				back(myClassTemp);
			}
		}

		class MyClass {
			HashSet<Integer> min;
			int minCnt = 0;
			HashMap<Integer, HashSet<Integer>> list;
			int minIndex = 0;
			int curIndex = 0;

			public void add(HashSet<Integer> temp) {
				list.put(curIndex, temp);
				if (min == null) {
					min = temp;
					minCnt = temp.size();
					minIndex = curIndex;
				} else {
					if (minCnt > temp.size()) {
						minCnt = temp.size();
						min = temp;
						minIndex = curIndex;
					}
				}
				curIndex++;
			}

			public int size() {
				return list.size();
			}

			public HashSet<Integer> getMin() {
				list.remove(minIndex);
				return min;
			}

			public MyClass() {
				list = new HashMap<>();
			}
		}
	}
	
	/**
	 * 状态压缩+dp
	 */
	class Solution4 {
		// TODO
	}
}
