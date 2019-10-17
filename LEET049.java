package com.zm.LeetCodeEx;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

/**
 * 49. 字母异位词分组
 * <p>
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 * <p>
 * 示例:
 * <p>
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * <p>
 * 输出: [ ["ate","eat","tea"], ["nat","tan"], ["bat"] ]
 * <p>
 * 说明：
 * <p>
 * 所有输入均为小写字母。 不考虑答案输出的顺序。
 *
 * 
 * @author zm
 */
public class LEET049 {
	public static void main(String[] args) {
		LEET049 l049 = new LEET049();
		String[] strs = { "eat", "tea", "tan", "ate", "nat", "bat" };
		String[] strs2 = { "ron", "huh", "gay", "tow", "moe", "tie", "who", "ion", "rep", "bob", "gte", "lee", "jay",
				"may", "wyo", "bay", "woe", "lip", "tit", "apt", "doe", "hot", "dis", "fop", "low", "bop", "apt", "dun",
				"ben", "paw", "ere", "bad", "ill", "fla", "mop", "tut", "sol", "peg", "pop", "les" };
		System.out.println(JSON.toJSONString(l049.groupAnagrams(strs)));
		System.out.println(JSON.toJSONString(l049.groupAnagrams(strs2)));
	}

	/**
	 * 用一个map的key来保存一个string里面的字符排序后的值，value保存符合的value
	 * @param strs
	 * @return
	 */
	public List<List<String>> groupAnagrams(String[] strs) {
		if (strs.length == 0) {
			return Collections.emptyList();
		}
		Map<String, List<String>> ans = new HashMap<String, List<String>>();
		for (String s : strs) {
			char[] ca = s.toCharArray();
			Arrays.sort(ca);
			String key = String.valueOf(ca);
			if (!ans.containsKey(key)) {
				ans.put(key, new ArrayList<>());
			}
			ans.get(key).add(s);
		}
		return new ArrayList<List<String>>(ans.values());
	}
}
