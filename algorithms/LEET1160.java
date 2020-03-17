package com.zm.LeetCodeEx.algorithms;

import java.util.HashMap;
import java.util.Map.Entry;

/**
 * 1160. 拼写单词
 * <p>
 * 给你一份『词汇表』（字符串数组） words 和一张『字母表』（字符串） chars。
 * <p>
 * 假如你可以用 chars 中的『字母』（字符）拼写出 words 中的某个『单词』（字符串），那么我们就认为你掌握了这个单词。
 * <p>
 * 注意：每次拼写时，chars 中的每个字母都只能用一次。
 * <p>
 * 返回词汇表 words 中你掌握的所有单词的 长度之和。
 * <p>
 * 示例 1： 输入：words = ["cat","bt","hat","tree"], chars = "atach"<br>
 * 输出：6<br>
 * 解释： <br>
 * 可以形成字符串 "cat" 和 "hat"，所以答案是 3 + 3 = 6。
 * <p>
 * 示例 2： 输入：words = ["hello","world","leetcode"], chars = "welldonehoneyr"<br>
 * 输出：10<br>
 * 解释：<br>
 * 可以形成字符串 "hello" 和 "world"，所以答案是 5 + 5 = 10。
 * <p>
 * 提示：<br>
 * 1 <= words.length <= 1000<br>
 * 1 <= words[i].length, chars.length <= 100<br>
 * 所有字符串中都仅包含小写英文字母<br>
 *
 * 
 * @author zm
 */
public class LEET1160 {
	public static void main(String[] args) {
		LEET1160 l1160 = new LEET1160();
		System.out.println(l1160.new Solution3().countCharacters(new String[] { "cat", "bt", "hat", "tree" }, "atach"));
		System.out.println(
				l1160.new Solution3().countCharacters(new String[] { "hello", "world", "leetcode" }, "welldonehoneyr"));
	}

	/**
	 * 每个单词做一次循环，每写一个字符去掉一个字符
	 * 
	 * @author zm
	 *
	 */
	class Solution {
		public int countCharacters(String[] words, String chars) {
			HashMap<Character, Integer> charMap = new HashMap<>();
			char[] charsArray = chars.toCharArray();
			for (int i = 0; i < charsArray.length; i++) {
				charMap.put(charsArray[i], charMap.getOrDefault(charsArray[i], 0) + 1);
			}
			int ret = 0;
			for (int i = 0; i < words.length; i++) {
				HashMap<Character, Integer> charMapTemp = new HashMap<>();
				charMapTemp.putAll(charMap);
				char[] sCharArray = words[i].toCharArray();
				boolean flag = true;
				for (int j = 0; j < sCharArray.length; j++) {
					int leftTimes = charMapTemp.getOrDefault(sCharArray[j], 0);
					if (leftTimes <= 0) {
						flag = false;
						break;
					} else {
						charMapTemp.put(sCharArray[j], leftTimes - 1);
					}
				}
				if (flag) {
					ret += sCharArray.length;
				}
			}
			return ret;
		}
	}

	/**
	 * 每个单词做一次循环，每个单词中的字符数不超过能用的字符数
	 * 
	 * @author zm
	 *
	 */
	class Solution2 {
		public int countCharacters(String[] words, String chars) {
			HashMap<Character, Integer> charMap = new HashMap<>();
			char[] charsArray = chars.toCharArray();
			for (int i = 0; i < charsArray.length; i++) {
				charMap.put(charsArray[i], charMap.getOrDefault(charsArray[i], 0) + 1);
			}
			int ret = 0;
			loop1: for (int i = 0; i < words.length; i++) {
				HashMap<Character, Integer> charMapTemp = new HashMap<>();
				char[] sArray = words[i].toCharArray();
				for (int j = 0; j < sArray.length; j++) {
					charMapTemp.put(sArray[j], charMapTemp.getOrDefault(sArray[j], 0) + 1);
				}
				for (Entry<Character, Integer> entry : charMapTemp.entrySet()) {
					if (charMap.getOrDefault(entry.getKey(), 0) < entry.getValue()) {
						continue loop1;
					}
				}
				ret += sArray.length;
			}
			return ret;
		}
	}

	/**
	 * 每个单词做一次循环，每个单词中的字符数不超过能用的字符数 用数组试试
	 * 
	 * @author zm
	 *
	 */
	class Solution3 {
		public int countCharacters(String[] words, String chars) {
			int[] charMap = new int[26];
			char[] charsArray = chars.toCharArray();
			for (int i = 0; i < charsArray.length; i++) {
				charMap[charsArray[i] - 'a']++;
			}
			int ret = 0;
			loop1: for (int i = 0; i < words.length; i++) {
				int[] charMapcharMapTemp = new int[26];
				char[] sArray = words[i].toCharArray();
				for (int j = 0; j < sArray.length; j++) {
					int index = sArray[j] - 'a';
					charMapcharMapTemp[index]++;
					if (charMapcharMapTemp[index] > charMap[index]) {
						continue loop1;
					}
				}
				ret += sArray.length;
			}
			return ret;
		}
	}
}
