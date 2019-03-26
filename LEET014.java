package LeetCode;

import java.util.HashMap;
import java.util.HashSet;

import javax.xml.soap.Node;

public class LEET014 {
	public String longestCommonPrefix(String[] strs) {
		if (strs == null || strs.length == 0) {
			return "";
		}
		int minlen = strs[0].length();
		int index = 0;
		for (int i = 0; i < strs.length; i++) {
			if (minlen > strs[i].length()) {
				minlen = strs[i].length();
				index = i;
			}
		}
		if (minlen == 0) {
			return "";
		}

		char[] strchars = strs[index].toCharArray();
		String ans = "";
		for (int i = 0; i < strchars.length; i++) {
			boolean flag = true;
			for (String c : strs) {
				if (!c.substring(i, i + 1)
						.equals(String.valueOf(strchars[i]))) {
					flag = false;
					break;
				}
			}
			if (flag) {
				ans += strchars[i];
			} else {
				break;
			}
		}
		return ans;
	}


	public Trie test(String[] strs) {
		Trie trie = new Trie();
		for (int i = 0; i < strs.length; i++) {
			trie.insert(strs[i]);
		}
		return trie;
	}

	public String longestCommonPrefix2(String[] strs) {
		if (strs == null || strs.length == 0) {
			return "";
		}
		String ans = "";
		int index = 0;
		loop1 : while (true) {
			HashSet<Character> item = new HashSet<>();
			for (String string : strs) {
				if (string.length() <= index) {
					break loop1;
				}
				item.add(string.toCharArray()[index]);
			}
			if (item.size() > 1) {
				break loop1;
			} else {
				ans = ans + strs[0].substring(index, index + 1);
				index++;
			}
		}
		return ans;
	}

	public String longestCommonPrefix3(String[] strs) {
		if (strs == null || strs.length == 0) {
			return "";
		}
		String ans = strs[0];
		for (int i = 0; i < strs.length; i++) {
			while (strs[i].indexOf(ans) != 0) {
				ans = ans.substring(0, ans.length() - 1);
			}
		}
		return ans;
	}

	public String longestCommonPrefix4(String[] strs) {
		if (strs == null || strs.length == 0)
			return "";
		for (int i = 0; i < strs[0].length(); i++) {
			char c = strs[0].charAt(i);
			for (int j = 1; j < strs.length; j++) {
				if (i == strs[j].length() || strs[j].charAt(i) != c)
					return strs[0].substring(0, i);
			}
		}
		return strs[0];
	}

	public String longestCommonPrefix5(String[] strs) {
		if (strs == null || strs.length == 0)
			return "";
		return longestCommonPrefix5(strs, 0, strs.length - 1);
	}
	private String longestCommonPrefix5(String[] strs, int l, int r) {
		if (l == r) {
			return strs[l];
		} else {
			int mid = (l + r) / 2;
			String lcpLeft = longestCommonPrefix5(strs, l, mid);
			String lcpRight = longestCommonPrefix5(strs, mid + 1, r);
			return commonPrefix(lcpLeft, lcpRight);
		}
	}
	String commonPrefix(String left, String right) {
		int min = Math.min(left.length(), right.length());
		for (int i = 0; i < min; i++) {
			if (left.charAt(i) != right.charAt(i))
				return left.substring(0, i);
		}
		return left.substring(0, min);
	}

	public static void main(String[] args) {
		String[] strs = {"aa", "aa", "aaa"};
		LEET014 L014 = new LEET014();
		System.out.println(L014.longestCommonPrefix6(strs));
	}
	
	// 字典树
	public String longestCommonPrefix6(String[] strs) {
		if (strs == null || strs.length == 0)
			return "";
		if (strs.length == 1)
			return strs[0];
		Trie trie = new Trie();

		for (int i = 0; i < strs.length; i++) {
			trie.insert(strs[i]);
		}
		return trie.searchLongestPrefix();
	}

	class TrieNode {

		// 子节点的链接数组
		private TrieNode[] links;

		private final int R = 26;

		private boolean isEnd;

		public TrieNode() {
			links = new TrieNode[R];
		}

		// 非空子节点的数量
		private int size;
		public void put(char ch, TrieNode node) {
			links[ch - 'a'] = node;
			size++;
		}

		public int getLinks() {
			return size;
		}
		// 假设方法 containsKey、isEnd、get、put 都已经实现了
		// 可以参考文章：https://leetcode.com/articles/implement-trie-prefix-tree/

		public boolean containsKey(char ch) {
			return links[ch - 'a'] != null;
		}

		public TrieNode get(char ch) {
			return links[ch - 'a'];
		}

		public boolean isEnd() {
			return isEnd;
		}

		public void setEnd() {
			isEnd = true;
		}
	}

	public class Trie {

		private TrieNode root;

		public Trie() {
			root = new TrieNode();
		}

		// 假设方法 insert、search、searchPrefix 都已经实现了
		// 可以参考文章：https://leetcode.com/articles/implement-trie-prefix-tree/
		private String searchLongestPrefix() {
			TrieNode node = root;
			StringBuilder prefix = new StringBuilder();
			while (true) {
				if (node.getLinks() == 1 && node.isEnd == false) {
					for (int i = 0; i < node.links.length; i++) {
						if (node.links[i] != null) {
							prefix.append((char) (i + 'a'));
							node = node.links[i];
							break;
						}
					}
				}
				else {
					break;
				}
			}
			return prefix.toString();
		}

		public void insert(String str) {
			TrieNode curr = root;
			for (int i = 0; i < str.length(); i++) {
				char currentChar = str.charAt(i);
				if (!curr.containsKey(currentChar)) {
					curr.put(currentChar, new TrieNode());
				}
				curr = curr.get(currentChar);
			}
			curr.setEnd();
		}
	}

}
