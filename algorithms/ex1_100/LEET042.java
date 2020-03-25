package com.zm.LeetCodeEx.algorithms.ex1_100;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * 给定一个字符串 s 和一些长度相同的单词 words。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。 注意子串要与 words
 * 中的单词完全匹配，中间不能有其他字符，但不需要考虑 words 中单词串联的顺序。
 * 
 * @author zm
 *
 */
public class LEET042 {
	public static void main(String[] args) {
		LEET042 l042 = new LEET042();
		int[] heights = { 10527, 740, 9013, 1300, 29680 };
		int[] heights2 = { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };
		System.out.println(l042.trap2(heights));
		System.out.println(l042.trap2(heights2));

	}

	/**
	 * 思路是算出接满水的总和减去所有的柱子
	 * 
	 * @param height
	 * @return
	 */
	public int trap(int[] height) {
		Map<Integer, Integer> mapmin = new TreeMap<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1.compareTo(o2);
			}
		});
		Map<Integer, Integer> mapmax = new TreeMap<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1.compareTo(o2);
			}
		});
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < height.length; i++) {
			if (map.get(height[i]) == null) {
				map.put(height[i], 1);
			} else {
				map.put(height[i], map.get(height[i]) + 1);
			}
		}

		for (int i = 0; i < height.length; i++) {
			if (mapmin.get(height[i]) == null) {
				mapmin.put(height[i], i);
			}
		}
		for (int i = height.length - 1; i >= 0; i--) {
			if (mapmax.get(height[i]) == null) {
				mapmax.put(height[i], i);
			}
		}
		// 处理mapmin
		Object[] o = mapmin.keySet().toArray();
		int min = mapmin.get(o[o.length - 1]);
		for (int i = o.length - 2; i >= 0; i--) {
			if (mapmin.get(o[i]) > min) {
				mapmin.put((Integer) o[i], min);
			} else {
				min = mapmin.get(o[i]);
			}
		}

		// 处理mapmax
		int max = mapmax.get(o[o.length - 1]);
		for (int i = o.length - 2; i >= 0; i--) {
			if (mapmax.get(o[i]) < max) {
				mapmax.put((Integer) o[i], max);
			} else {
				max = mapmax.get(o[i]);
			}
		}
		long vall = 0;
		for (int i = o.length - 1; i >= 0; i--) {
			if (i == 0) {
				vall += (mapmax.get(o[i]) - mapmin.get(o[i]) + 1) * (Integer) o[i];
			} else {
				vall += (mapmax.get(o[i]) - mapmin.get(o[i]) + 1) * ((Integer) o[i] - (Integer) o[i - 1]);
			}
			vall -= map.get(o[i]) * (Integer) o[i];
		}
		return (int) (vall);
	}

	/**
	 * 先判断左指针和右指针大小，小的往中间移，最终到达最高点，这样只要有指针移动后比某侧当前最高点低的，就能接到水
	 * 
	 * @param height
	 * @return
	 */
	public int trap2(int[] height) {
		int left = 0;
		int right = height.length - 1;

		int leftMax = 0;
		int rightMax = 0;

		int v = 0;
		while (left < right) {
			if (height[left] < height[right]) {
				if (height[left] > leftMax) {
					leftMax = height[left];
				} else {
					v += leftMax - height[left];
				}
				left++;
			} else {
				if (height[right] > rightMax) {
					rightMax = height[right];
				} else {
					v += rightMax - height[right];
				}
				right--;
			}
		}
		return v;
	}
}
