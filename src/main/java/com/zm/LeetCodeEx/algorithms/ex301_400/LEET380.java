package com.zm.LeetCodeEx.algorithms.ex301_400;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 380. O(1) 时间插入、删除和获取随机元素
 * 实现RandomizedSet 类：
 * <p>
 * RandomizedSet() 初始化 RandomizedSet 对象
 * bool insert(int val) 当元素 val 不存在时，向集合中插入该项，并返回 true ；否则，返回 false 。
 * bool remove(int val) 当元素 val 存在时，从集合中移除该项，并返回 true ；否则，返回 false 。
 * int getRandom() 随机返回现有集合中的一项（测试用例保证调用此方法时集合中至少存在一个元素）。每个元素应该有 相同的概率 被返回。
 * 你必须实现类的所有函数，并满足每个函数的 平均 时间复杂度为 O(1) 。
 * <p>
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入
 * ["RandomizedSet", "insert", "remove", "insert", "getRandom", "remove", "insert", "getRandom"]
 * [[], [1], [2], [2], [], [1], [2], []]
 * 输出
 * [null, true, false, true, 2, true, false, 2]
 * <p>
 * 解释
 * RandomizedSet randomizedSet = new RandomizedSet();
 * randomizedSet.insert(1); // 向集合中插入 1 。返回 true 表示 1 被成功地插入。
 * randomizedSet.remove(2); // 返回 false ，表示集合中不存在 2 。
 * randomizedSet.insert(2); // 向集合中插入 2 。返回 true 。集合现在包含 [1,2] 。
 * randomizedSet.getRandom(); // getRandom 应随机返回 1 或 2 。
 * randomizedSet.remove(1); // 从集合中移除 1 ，返回 true 。集合现在包含 [2] 。
 * randomizedSet.insert(2); // 2 已在集合中，所以返回 false 。
 * randomizedSet.getRandom(); // 由于 2 是集合中唯一的数字，getRandom 总是返回 2 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * -2^31 <= val <= 2^31 - 1
 * 最多调用 insert、remove 和 getRandom 函数 2 * 105 次
 * 在调用 getRandom 方法时，数据结构中 至少存在一个 元素。
 *
 * @author zm
 */
public class LEET380 {
	public static void main(String[] args) {
		System.out.println(Arrays.toString(doTest(new String[]{}, new int[][]{})));
	}

	public static Object[] doTest(String[] function, int[][] params) {
		Solution.RandomizedSet obj = new Solution.RandomizedSet();
		int len = function.length;
		Object[] result = new Object[len];
		for (int i = 1; i < len; i++) {
			if ("insert".equals(function[i])) {
				result[i] = obj.insert(params[i][0]);
			}
			else if ("remove".equals(function[i])) {
				result[i] = obj.remove(params[i][0]);
			}
			else if ("getRandom".equals(function[i])) {
				result[i] = obj.getRandom();
			}
		}
		return result;
	}

	/**
	 *
	 */
	static class Solution {
		static class RandomizedSet {
			HashMap<Integer, Integer> numberStorage = new HashMap<>();

			public RandomizedSet() {
			}

			public boolean insert(int val) {
				return false;
			}

			public boolean remove(int val) {
				return false;
			}

			public int getRandom() {
				return 1;
			}
		}
	}
}
