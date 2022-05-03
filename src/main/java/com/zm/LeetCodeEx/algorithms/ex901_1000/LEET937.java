package com.zm.LeetCodeEx.algorithms.ex901_1000;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 937. 重新排列日志文件
 * 给你一个日志数组 logs。每条日志都是以空格分隔的字串，其第一个字为字母与数字混合的 标识符 。
 * <p>
 * 有两种不同类型的日志：
 * <p>
 * 字母日志：除标识符之外，所有字均由小写字母组成
 * 数字日志：除标识符之外，所有字均由数字组成
 * 请按下述规则将日志重新排序：
 * <p>
 * 所有 字母日志 都排在 数字日志 之前。
 * 字母日志 在内容不同时，忽略标识符后，按内容字母顺序排序；在内容相同时，按标识符排序。
 * 数字日志 应该保留原来的相对顺序。
 * 返回日志的最终顺序。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：logs = ["dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"]
 * 输出：["let1 art can","let3 art zero","let2 own kit dig","dig1 8 1 5 1","dig2 3 6"]
 * 解释：
 * 字母日志的内容都不同，所以顺序为 "art can", "art zero", "own kit dig" 。
 * 数字日志保留原来的相对顺序 "dig1 8 1 5 1", "dig2 3 6" 。
 * 示例 2：
 * <p>
 * 输入：logs = ["a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo"]
 * 输出：["g1 act car","a8 act zoo","ab1 off key dog","a1 9 2 3 1","zo4 4 7"]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= logs.length <= 100
 * 3 <= logs[i].length <= 100
 * logs[i] 中，字与字之间都用 单个 空格分隔
 * 题目数据保证 logs[i] 都有一个标识符，并且在标识符之后至少存在一个字
 *
 * @author zm
 */
public class LEET937 {
	public static void main(String[] args) {
		System.out
				.println(Arrays.toString(new Solution().reorderLogFiles(new String[]{"dig1 8 1 5 1", "let1 art can",
						"dig2 3 6",
						"let2 own kit dig", "let3 art zero"})));
		System.out
				.println(Arrays.toString(new Solution2().reorderLogFiles(new String[]{"dig1 8 1 5 1", "let1 art can",
						"dig2 3 6",
						"let2 own kit dig", "let3 art zero"})));
	}

	/**
	 * 数字和文字分开
	 */
	static class Solution {
		public String[] reorderLogFiles(String[] logs) {
			int n = logs.length;
			boolean[] isDigit = new boolean[n];
			List<String[]> letter = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				String log = logs[i];
				String[] logSplit = log.split(" ");
				if (Character.isDigit(logSplit[1].charAt(0))) {
					isDigit[i] = true;
				}
				else {
					letter.add(new String[]{log.substring(logSplit[0].length() + 1), log});
				}
			}
			letter.sort(Comparator.comparing(v -> v, (o1, o2) -> {
				if (o1[0].equals(o2[0])) {
					return o1[1].compareTo(o2[1]);
				}
				return o1[0].compareTo(o2[0]);
			}));
			String[] result = new String[n];
			int i = 0;
			for (String[] log : letter) {
				result[i++] = log[1];
			}
			for (int j = 0; j < n; j++) {
				if (isDigit[j]) {
					result[i++] = logs[j];
				}
			}
			return result;
		}
	}

	/**
	 * 整体排序
	 */
	static class Solution2 {
		public String[] reorderLogFiles(String[] logs) {
			int n = logs.length;
			List<Pair> pairs = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				String log = logs[i];
				String[] logSplit = log.split(" ");
				if (Character.isDigit(logSplit[1].charAt(0))) {
					pairs.add(new Pair(true, log, logSplit[0], null, i));
				}
				else {
					pairs.add(new Pair(false, log, logSplit[0], log.substring(logSplit[0].length() + 1), i));
				}
			}
			pairs.sort(Comparator.comparing(v -> v, (o1, o2) -> {
				if (o1.isDigit && o2.isDigit) {
					return o1.index - o2.index;
				}
				else if (o1.isDigit) {
					return 1;
				}
				else if (o2.isDigit) {
					return -1;
				}
				else if (o1.content.equals(o2.content)) {
					return o1.tag.compareTo(o2.tag);
				}
				return o1.content.compareTo(o2.content);
			}));
			String[] result = new String[n];
			int i = 0;
			for (Pair pair : pairs) {
				result[i++] = pair.log;
			}
			return result;
		}

		static class Pair {
			boolean isDigit;
			String log;
			String tag;
			String content;
			int index;

			public Pair(boolean isDigit, String log, String tag, String content, int index) {
				this.isDigit = isDigit;
				this.log = log;
				this.tag = tag;
				this.content = content;
				this.index = index;
			}
		}
	}
}
