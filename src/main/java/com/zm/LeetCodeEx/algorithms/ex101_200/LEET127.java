package com.zm.LeetCodeEx.algorithms.ex101_200;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import com.zm.LeetCodeEx.CommonFunctions;

import javafx.util.Pair;

/**
 * 127. 单词接龙 给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord
 * 的最短转换序列的长度。转换需遵循如下规则：
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
public class LEET127 {
	public static void main(String[] args) {
		LEET127 l127 = new LEET127();
		System.out.println(
				l127.new Solution4().ladderLength("a", "c", CommonFunctions.stringToStringList("[\"a\",\"b\",\"c\"]")));
		System.out.println(l127.new Solution4().ladderLength("hit", "cog",
				CommonFunctions.stringToStringList("[\"hot\",\"dot\",\"dog\",\"lot\",\"log\",\"cog\"]")));
		System.out.println(l127.new Solution4().ladderLength("hit", "cog",
				CommonFunctions.stringToStringList("[\"hot\",\"dot\",\"dog\",\"lot\",\"log\"]")));
		System.out.println(l127.new Solution4().ladderLength("hit", "dot",
				CommonFunctions.stringToStringList("[\"hot\",\"dot\",\"dog\",\"lot\",\"log\"]")));
	}

	/**
	 * 
	 * 直接BFS
	 * 
	 * @author zm
	 *
	 */
	class Solution {
		public int ladderLength(String beginWord, String endWord, List<String> wordList) {
			if (!wordList.contains(endWord) || beginWord.equals(endWord)) {
				return 0;
			}
			Queue<String> queue = new LinkedList<String>();
			queue.add(beginWord);
			int ret = 1;
			while (!queue.isEmpty()) {
				ret++;
				int size = queue.size();
				for (int i = 0; i < size; i++) {
					String temp = queue.poll();
					Iterator<String> it = wordList.listIterator();
					while (it.hasNext()) {
						String ele = it.next();
						if (ele.equals(beginWord)) {
							it.remove();
						} else if (check(temp, ele)) {
							if (ele.equals(endWord)) {
								return ret;
							}
							queue.add(ele);
							it.remove();
						}
					}
				}
			}
			return 0;
		}

		private boolean check(String s1, String s2) {
			char[] c1 = s1.toCharArray();
			char[] c2 = s2.toCharArray();
			int l = c1.length;
			int cnt = 0;
			for (int i = 0; i < l; i++) {
				if (c1[i] != c2[i]) {
					cnt++;
					if (cnt == 2) {
						return false;
					}
				}
			}
			return true;
		}
	}

	/**
	 * 
	 * 先预处理list，每个word只改变一个字母变成*，然后把这些字符组成map，然后BFS 如hot:{*ot,h*t,ho*}
	 * 
	 * @author zm
	 *
	 */
	class Solution2 {
		public int ladderLength(String beginWord, String endWord, List<String> wordList) {
			// Since all words are of same length.
			int L = beginWord.length();

			// Dictionary to hold combination of words that can be formed,
			// from any given word. By changing one letter at a time.
			Map<String, List<String>> allComboDict = new HashMap<>();

			wordList.forEach(word -> {
				for (int i = 0; i < L; i++) {
					// Key is the generic word
					// Value is a list of words which have the same intermediate generic word.
					String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);
					List<String> transformations = allComboDict.getOrDefault(newWord, new ArrayList<>());
					transformations.add(word);
					allComboDict.put(newWord, transformations);
				}
			});

			// Queue for BFS
			Queue<Pair<String, Integer>> Q = new LinkedList<>();
			Q.add(new Pair<String, Integer>(beginWord, 1));

			// Visited to make sure we don't repeat processing same word.
			Map<String, Boolean> visited = new HashMap<>();
			visited.put(beginWord, true);

			while (!Q.isEmpty()) {
				Pair<String, Integer> node = Q.remove();
				String word = node.getKey();
				int level = node.getValue();
				for (int i = 0; i < L; i++) {

					// Intermediate words for current word
					String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);

					// Next states are all the words which share the same intermediate state.
					for (String adjacentWord : allComboDict.getOrDefault(newWord, new ArrayList<>())) {
						// If at any point if we find what we are looking for
						// i.e. the end word - we can return with the answer.
						if (adjacentWord.equals(endWord)) {
							return level + 1;
						}
						// Otherwise, add it to the BFS Queue. Also mark it visited
						if (!visited.containsKey(adjacentWord)) {
							visited.put(adjacentWord, true);
							Q.add(new Pair<String, Integer>(adjacentWord, level + 1));
						}
					}
				}
			}

			return 0;
		}
	}

	/**
	 * 官方题解 双向BFS，因为从startWord开始和从endWord开始是一样的，所以可以同时从两边开始
	 * 
	 * @author zm
	 *
	 */
	class Solution3 {
		private int L;
		private Map<String, List<String>> allComboDict = new HashMap<>();

		public int ladderLength(String beginWord, String endWord, List<String> wordList) {
			if (!wordList.contains(endWord)) {
				return 0;
			}
			// Since all words are of same length.
			this.L = beginWord.length();
			wordList.forEach(word -> {
				for (int i = 0; i < L; i++) {
					// Key is the generic word
					// Value is a list of words which have the same intermediate generic word.
					String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);
					List<String> transformations = this.allComboDict.getOrDefault(newWord, new ArrayList<>());
					transformations.add(word);
					this.allComboDict.put(newWord, transformations);
				}
			});

			// Queues for birdirectional BFS
			// BFS starting from beginWord
			Queue<Pair<String, Integer>> Q_begin = new LinkedList<>();
			// BFS starting from endWord
			Queue<Pair<String, Integer>> Q_end = new LinkedList<>();
			Q_begin.add(new Pair<String, Integer>(beginWord, 1));
			Q_end.add(new Pair<String, Integer>(endWord, 1));

			// Visited to make sure we don't repeat processing same word.
			Map<String, Integer> visitedBegin = new HashMap<>();
			Map<String, Integer> visitedEnd = new HashMap<>();
			visitedBegin.put(beginWord, 1);
			visitedEnd.put(endWord, 1);

			while (!Q_begin.isEmpty() && !Q_end.isEmpty()) {

				// One hop from begin word
				int ans = visitWordNode(Q_begin, visitedBegin, visitedEnd);
				if (ans > -1) {
					return ans;
				}

				// One hop from end word
				ans = visitWordNode(Q_end, visitedEnd, visitedBegin);
				if (ans > -1) {
					return ans;
				}
			}
			return 0;
		}

		private int visitWordNode(Queue<Pair<String, Integer>> Q, Map<String, Integer> visited,
				Map<String, Integer> othersVisited) {
			Pair<String, Integer> node = Q.remove();
			String word = node.getKey();
			int level = node.getValue();

			for (int i = 0; i < this.L; i++) {

				// Intermediate words for current word
				String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);

				// Next states are all the words which share the same intermediate state.
				for (String adjacentWord : this.allComboDict.getOrDefault(newWord, new ArrayList<>())) {
					// If at any point if we find what we are looking for
					// i.e. the end word - we can return with the answer.
					if (othersVisited.containsKey(adjacentWord)) {
						return level + othersVisited.get(adjacentWord);
					}

					if (!visited.containsKey(adjacentWord)) {

						// Save the level as the value of the dictionary, to save number of hops.
						visited.put(adjacentWord, level + 1);
						Q.add(new Pair<String, Integer>(adjacentWord, level + 1));
					}
				}
			}
			return -1;
		}
	}

	/**
	 * 使用递归来处理，并且每次只执行两个方向的队列中元素比较小的那个队列，最优化解法。
	 * 
	 * @author zm
	 *
	 */
	class Solution4 {
		// 递归
		public int ladderLength(String beginWord, String endWord, List<String> wordList) {
			if (wordList == null || wordList.isEmpty()) {
				return 0;
			}
			// hashset的好处：去重也完成了
			// 开始端
			HashSet<String> start = new HashSet<>();
			// 结束端
			HashSet<String> end = new HashSet<>();
			// 所有字符串的字典
			HashSet<String> dic = new HashSet<>(wordList);
			start.add(beginWord);
			end.add(endWord);
			if (!dic.contains(endWord)) {
				return 0;
			}
			// 经历过上面的一系列判定，到这里的时候，若是有路径，则最小是2，所以以2开始
			return bfs(start, end, dic, 2);
		}

		public int bfs(HashSet<String> st, HashSet<String> ed, HashSet<String> dic, int l) {
			// 双端查找的时候，若是有任意一段出现了“断裂”，也就是说明不存在能够连上的路径，则直接返回0
			if (st.isEmpty()) {
				return 0;
			}
			if (st.size() > ed.size()) {// 双端查找，为了优化时间，永远用少的去找多的，比如开始的时候塞进了1000个，而结尾只有3个，则肯定是从少的那一端开始走比较好
				return bfs(ed, st, dic, l);
			}
			// BFS的标记行为，即使用过的不重复使用
			dic.removeAll(st);
			// 收集下一层临近点
			HashSet<String> next = new HashSet<>();
			for (String s : st) {
				char[] arr = s.toCharArray();
				for (int i = 0; i < arr.length; i++) {
					char tmp = arr[i];// tmp去记录当前的字符方便还原
					// 变化
					for (char c = 'a'; c <= 'z'; c++) {
						if (tmp == c) {
							continue;
						}
						arr[i] = c;
						String nstr = new String(arr);
						if (dic.contains(nstr)) {
							if (ed.contains(nstr)) {
								return l;
							} else {
								next.add(nstr);
							}
						}
					}
					// 复原
					arr[i] = tmp;
				}
			}
			return bfs(next, ed, dic, l + 1);
		}
	}
}
