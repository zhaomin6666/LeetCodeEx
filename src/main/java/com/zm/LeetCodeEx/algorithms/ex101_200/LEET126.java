package com.zm.LeetCodeEx.algorithms.ex101_200;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.zm.LeetCodeEx.CommonFunctions;

/**
 * 126. 单词接龙 给定两个单词（beginWord 和 endWord）和一个字典，找出所有从 beginWord 到 endWord
 * 的最短转换序列。转换需遵循如下规则：
 * <p>
 * 每次转换只能改变一个字母。<br>
 * 转换过程中的中间单词必须是字典中的单词。
 * <p>
 * 说明:
 * <p>
 * 如果不存在这样的转换序列，返回 0。<br>
 * 所有单词具有相同的长度。<br>
 * 所有单词只由小写字母组成。<br>
 * 字典中不存在重复的单词。<br>
 * 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
 * <p>
 * 示例 1:
 * <p>
 * 输入:<br>
 * beginWord = "hit",<br>
 * endWord = "cog",<br>
 * wordList = ["hot","dot","dog","lot","log","cog"]
 * <p>
 * 输出: 5
 * <p>
 * 解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",<br>
 * 返回它的长度 5。
 * <p>
 * 示例 2:
 * <p>
 * 输入: beginWord = "hit" <br>
 * endWord = "cog" <br>
 * wordList = ["hot","dot","dog","lot","log"]
 * <p>
 * 输出: 0
 * <p>
 * 解释: endWord "cog" 不在字典中，所以无法进行转换。
 * 
 * @author zm
 *
 */
public class LEET126 {
	public static void main(String[] args) {
		LEET126 l126 = new LEET126();
		System.out.println(JSON.toJSONString(
				l126.new Solution().findLadders("a", "c", CommonFunctions.stringToStringList("[\"a\",\"b\",\"c\"]"))));
		System.out.println(JSON.toJSONString(l126.new Solution().findLadders("hit", "cog",
				CommonFunctions.stringToStringList("[\"hot\",\"dot\",\"dog\",\"lot\",\"log\",\"cog\"]"))));
		System.out.println(JSON.toJSONString(l126.new Solution().findLadders("hit", "cog",
				CommonFunctions.stringToStringList("[\"hot\",\"dot\",\"dog\",\"lot\",\"log\"]"))));
		System.out.println(JSON.toJSONString(l126.new Solution().findLadders("hit", "dot",
				CommonFunctions.stringToStringList("[\"hot\",\"dot\",\"dog\",\"lot\",\"log\"]"))));
	}

	/**
	 * 参考127，双向bfs
	 * @author zm
	 *
	 */
	class Solution {
		public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
			// 结果集
			List<List<String>> res = new ArrayList<>();
			Set<String> words = new HashSet<>(wordList);
			// 字典中不包含目标单词
			if (!words.contains(endWord)) {
				return res;
			}
			// 存放关系：每个单词可达的下层单词
			Map<String, List<String>> mapTree = new HashMap<>();
			Set<String> begin = new HashSet<>(), end = new HashSet<>();
			begin.add(beginWord);
			end.add(endWord);
			if (buildTree(words, begin, end, mapTree, true)) {
				dfs(res, mapTree, beginWord, endWord, new LinkedList<>());
			}
			return res;
		}

		// 双向BFS，构建每个单词的层级对应关系
		private boolean buildTree(Set<String> words, Set<String> begin, Set<String> end,
				Map<String, List<String>> mapTree, boolean isFront) {
			if (begin.isEmpty()) {
				return false;
			}
			// 始终以少的进行探索
			if (begin.size() > end.size()) {
				return buildTree(words, end, begin, mapTree, !isFront);
			}
			// 在已访问的单词集合中去除
			words.removeAll(begin);
			// 标记本层是否已到达目标单词
			boolean isMeet = false;
			// 记录本层所访问的单词
			Set<String> nextLevel = new HashSet<>();
			for (String word : begin) {
				char[] chars = word.toCharArray();
				for (int i = 0; i < chars.length; i++) {
					char temp = chars[i];
					for (char ch = 'a'; ch <= 'z'; ch++) {
						chars[i] = ch;
						String str = String.valueOf(chars);
						if (words.contains(str)) {
							nextLevel.add(str);
							// 根据访问顺序，添加层级对应关系：始终保持从上层到下层的存储存储关系
							// true: 从上往下探索：word -> str
							// false: 从下往上探索：str -> word（查找到的 str 是 word 上层的单词）
							String key = isFront ? word : str;
							String nextWord = isFront ? str : word;
							// 判断是否遇见目标单词
							if (end.contains(str)) {
								isMeet = true;
							}
							if (!mapTree.containsKey(key)) {
								mapTree.put(key, new ArrayList<>());
							}
							mapTree.get(key).add(nextWord);
						}
					}
					chars[i] = temp;
				}
			}
			if (isMeet) {
				return true;
			}
			return buildTree(words, nextLevel, end, mapTree, isFront);
		}

		// DFS: 组合路径
		private void dfs(List<List<String>> res, Map<String, List<String>> mapTree, String beginWord, String endWord,
				LinkedList<String> list) {
			list.add(beginWord);
			if (beginWord.equals(endWord)) {
				res.add(new ArrayList<>(list));
				list.removeLast();
				return;
			}
			if (mapTree.containsKey(beginWord)) {
				for (String word : mapTree.get(beginWord)) {
					dfs(res, mapTree, word, endWord, list);
				}
			}
			list.removeLast();
		}
	}
}
