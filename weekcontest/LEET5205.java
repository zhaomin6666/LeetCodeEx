package com.zm.LeetCodeEx.weekcontest;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;

/**
 * 周赛 2019年9月29日 5205. 独一无二的出现次数
 * <p>
 * 给你一个整数数组 arr，请你帮忙统计数组中每个数的出现次数。
 * 
 * 如果每个数的出现次数都是独一无二的，就返回 true；否则返回 false。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：arr = [1,2,2,1,1,3] 输出：true 解释：在该数组中，1 出现了 3 次，2 出现了 2 次，3 只出现了 1
 * 次。没有两个数的出现次数相同。 示例 2：
 * 
 * 输入：arr = [1,2] 输出：false 示例 3：
 * 
 * 输入：arr = [-3,0,1,-3,1,1,1,-3,10,0] 输出：true
 * 
 * 
 * 提示：
 * 
 * 1 <= arr.length <= 1000 -1000 <= arr[i] <= 1000
 * 
 * @author zm
 *
 */
public class LEET5205 {
	public static void main(String[] args) {
		LEET5205 l5205 = new LEET5205();
		int[] nums1 = { 1, 2, 2, 1, 1, 3, 3 };
		System.out.println(l5205.uniqueOccurrences(nums1));
	}

	public boolean uniqueOccurrences(int[] arr) {
		Map<Integer, Integer> cntMap = new HashMap<Integer, Integer>();
		for (int i = 0; i < arr.length; i++) {
			cntMap.put(arr[i], cntMap.getOrDefault(arr[i], 0) + 1);
		}
		System.out.println(JSON.toJSONString(cntMap));
		Map<Integer, Integer> cntCntMap = new HashMap<Integer, Integer>();
		for (int i : cntMap.values()) {
			if (cntCntMap.get(i) != null) {
				return false;
			}
			cntCntMap.put(i, 1);
		}
		return true;
	}

}
