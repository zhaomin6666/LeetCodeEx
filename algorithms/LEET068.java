package com.zm.LeetCodeEx.algorithms;

import java.util.ArrayList;
import java.util.List;

/**
 * 68. 文本左右对齐
 * <p>
 * 给定一个单词数组和一个长度 maxWidth，重新排版单词，使其成为每行恰好有 maxWidth 个字符，且左右两端对齐的文本。
 * <p>
 * 你应该使用“贪心算法”来放置给定的单词；也就是说，尽可能多地往每行中放置单词。 必要时可用空格 ' ' 填充，使得每行恰好有 maxWidth 个字符。
 * <p>
 * 要求尽可能均匀分配单词间的空格数量。如果某一行单词间的空格不能均匀分配，则左侧放置的空格数要多于右侧的空格数。
 * <p>
 * 文本的最后一行应为左对齐，且单词之间不插入额外的空格。
 * <p>
 * 说明:
 * <p>
 * 单词是指由非空格字符组成的字符序列。 每个单词的长度大于 0，小于等于 maxWidth。 输入单词数组 words 至少包含一个单词。
 * <p>
 * 示例:
 * <p>
 * 输入: words = ["This", "is", "an", "example", "of", "text", "justification."]
 * maxWidth = 16
 * <p>
 * 输出: [<br>
 * "This    is    an",<br>
 * "example  of text",<br>
 * "justification.  " ]
 * <p>
 * 示例 2: 输入: words = ["What","must","be","acknowledgment","shall","be"] maxWidth
 * = 16
 * <p>
 * 输出: [<br>
 * "What   must   be",<br>
 * "acknowledgment  ",<br>
 * "shall be        " ]
 * <p>
 * 解释: 注意最后一行的格式应为 "shall be " 而不是 "shall be",   因为最后一行应为左对齐，而不是左右两端对齐。
 * 第二行同样为左对齐，这是因为这行只包含一个单词。
 * <p>
 * 示例 3:
 * <p>
 * 输入: words =
 * ["Science","is","what","we","understand","well","enough","to","explain",  
 * "to","a","computer.","Art","is","everything","else","we","do"] maxWidth = 20
 * <p>
 * 输出: [<br>
 * "Science  is  what we",<br>
 * "understand      well",<br>
 * "enough to explain to",<br>
 * "a  computer.  Art is",<br>
 * "everything  else  we",<br>
 * "do                  " ]
 * 
 *
 * @author zm
 */
public class LEET068 {
	public static void main(String[] args) {
		LEET068 l068 = new LEET068();
		/*
		 * System.out.println(l067.new Solution() .fullJustify(new String[] { "This",
		 * "is", "an", "example", "of", "text", "justification." }, 16));
		 * System.out.println(l067.new Solution() .fullJustify(new String[] { "What",
		 * "must", "be", "acknowledgment", "shall", "be" }, 16));
		 * System.out.println(l067.new Solution() .fullJustify(new String[] { "Science",
		 * "is", "what", "we", "understand", "well", "enough", "to", "explain", "to",
		 * "a", "computer.", "Art", "is", "everything", "else", "we", "do" }, 20));
		 */System.out.println(l068.new Solution()
				.fullJustify(new String[] { "Listen", "to", "many,", "speak", "to", "a", "few." }, 6));
	}

	private class Solution {
		public List<String> fullJustify(String[] words, int maxWidth) {
			ArrayList<String> ret = new ArrayList<>();
			StringBuilder sb = new StringBuilder();
			ArrayList<Integer> wordSpanIndex = new ArrayList<>();
			int i = 0;
			while (i < words.length) {
				if (sb.length() + words[i].length() + (sb.length() == 0 ? 0 : 1) > maxWidth) {
					int inteval = maxWidth - sb.length();
					if (wordSpanIndex.size() == 1) {
						for (int j = 0; j < inteval; j++) {
							sb.append(" ");
						}
					} else {
						int needAddSpace = inteval / (wordSpanIndex.size() - 1);
						int needAddMoreSpace = inteval % (wordSpanIndex.size() - 1);
						for (int j = wordSpanIndex.size() - 2; j > -1; j--) {
							int curWordNeedAddSpace = needAddSpace
									+ (wordSpanIndex.size() - 1 - needAddMoreSpace++ > 0 ? 0 : 1);
							for (int k = 0; k < curWordNeedAddSpace; k++) {
								sb.insert(wordSpanIndex.get(j), " ");
							}
						}
					}
					ret.add(sb.toString());
					sb = new StringBuilder();
					wordSpanIndex = new ArrayList<>();
				} else {
					if (sb.length() != 0) {
						sb.append(" ");
					}
					sb.append(words[i++]);
					wordSpanIndex.add(sb.length());
					int spaceNeedToAddAfter = maxWidth - sb.length();
					if (i == words.length) {
						for (int j = 0; j < spaceNeedToAddAfter; j++) {
							sb.append(" ");
						}
						ret.add(sb.toString());
					}
				}
			}
			return ret;
		}
	}
}
