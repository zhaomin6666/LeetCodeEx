package com.zm.LeetCodeEx;

import java.util.ArrayList;
import java.util.List;

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
		System.out.println(JSON.toJSONString(l049.groupAnagrams(strs)));
	}

	public List<List<String>> groupAnagrams(String[] strs) {
		List<Integer> index = new ArrayList<>();
		List<List<String>> ret = new ArrayList<>();
		for (int i = 0; i < strs.length; i++) {
			char[] chars = strs[i].toCharArray();
			int hashSum =0;
			for (int j = 0; j < chars.length; j++) {
				hashSum += ((Character)chars[j]).hashCode(); // 会有重复的，后面用int的每一位存吧
			}
			if(index.contains(hashSum)) {
				ret.get(index.indexOf(hashSum)).add(strs[i]);
			}else {
				List<String> item = new ArrayList<>();
				item.add(strs[i]);
				ret.add(item);
				index.add(hashSum);
			}
		}
		return ret;
	}
}
