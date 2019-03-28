package LeetCode;

import java.util.HashSet;
/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 
 * 如果不存在公共前缀，返回空字符串 ""。 <br>
 * <b>重点关注字典树结构！</b>
 * 
 * @author zm
 *
 */
public class LEET014 {
	public static void main(String[] args) {
		String[] strs = {"aa", "aa", "aaa"};
		LEET014 L014 = new LEET014();
		System.out.println(L014.longestCommonPrefix6(strs));
	}

	/**
	 * 找出最短字符串，遍历它
	 * 
	 * @param strs
	 * @return
	 */
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

	/**
	 * 把每一个字符串的第一个字符存到set中如果set超过1，则说明有不同的
	 * 
	 * @param strs
	 * @return
	 */
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

	/**
	 * 以第一个字符为基准
	 * 
	 * @param strs
	 * @return
	 */
	public String longestCommonPrefix3(String[] strs) {
		if (strs == null || strs.length == 0)
			return "";
		String ans = strs[0];
		for (int i = 0; i < strs.length; i++) {
			while (strs[i].indexOf(ans) != 0) {
				ans = ans.substring(0, ans.length() - 1);
			}
		}
		return ans;
	}

	/**
	 * 暴力
	 * 
	 * @param strs
	 * @return
	 */
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

	/**
	 * 分治: abcdfg acbd abc ab abcd ab ab
	 * 
	 * @param strs
	 * @return
	 */
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

	/**
	 * 二分法算法进行的过程中一共会出现两种可能情况：
	 * 
	 * S[1...mid] 不是所有串的公共前缀。 这表明对于所有的 j > i S[1..j] 也不是公共前缀，于是我们就可以丢弃后半个查找区间。
	 * 
	 * S[1...mid] 是所有串的公共前缀。 这表示对于所有的 i < j S[1..i]
	 * 都是可行的公共前缀，因为我们要找最长的公共前缀，所以我们可以把前半个查找区间丢弃 先找出最短长度 abcdef3 abceawer1
	 * abcaewfdsa minLen=7 low=1 high=7 mid=4 遍历所有字符串判断前4位是否是公共子串
	 * 发现不是，那么high=mid-1=3 mid=(1+3)/2=2 遍历所有字符串判断前2位是否是公共子串 发现是，那么low=min+1=3
	 * mid=(3+3)/2=3 遍历所有字符串判断前2位是否是公共子串 ok...
	 * 
	 * @param strs
	 * @return
	 */
	public String longestCommonPrefix6(String[] strs) {
		if (strs == null || strs.length == 0)
			return "";
		int minLen = Integer.MAX_VALUE;
		for (String str : strs)
			minLen = Math.min(minLen, str.length());
		int low = 1;
		int high = minLen;
		while (low <= high) {
			int middle = (low + high) / 2;
			if (isCommonPrefix(strs, middle))
				low = middle + 1;
			else
				high = middle - 1;
		}
		return strs[0].substring(0, (low + high) / 2);
	}

	private boolean isCommonPrefix(String[] strs, int len) {
		String str1 = strs[0].substring(0, len);
		for (int i = 1; i < strs.length; i++)
			if (!strs[i].startsWith(str1))
				return false;
		return true;
	}

	/**
	 * 字典树
	 * 
	 * @param strs
	 * @return
	 */
	public String longestCommonPrefix7(String[] strs) {
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

	/**
	 * 字典树
	 * 
	 * @author zm
	 *
	 */
	public class Trie {
		private TrieNode root;// 根节点

		public Trie() {
			root = new TrieNode(); // 构造方法生成根节点
		}

		// 假设方法 insert、search、searchPrefix 都已经实现了
		// 可以参考文章：https://leetcode.com/articles/implement-trie-prefix-tree/
		private String searchLongestPrefix() {
			TrieNode node = root;
			StringBuilder prefix = new StringBuilder();
			while (true) {
				if (node.getLinks() == 1 && node.isEnd == false) {
					// 有下一节点而且不是某个字符串的最后一位
					// 如：abc和ab
					// b的下面虽然有c但是b是ab的最后一位
					// 这在insert方法中会对b节点设置isend=True
					for (int i = 0; i < node.links.length; i++) {
						if (node.links[i] != null) {
							prefix.append((char) (i + 'a'));
							node = node.links[i];
							break;
						}
					}
				} else {
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
			curr.setEnd();// 对节点设置isend=True
		}
	}

	/**
	 * 测试方法
	 * 
	 * @param strs
	 * @return
	 */
	public Trie test(String[] strs) {
		Trie trie = new Trie();
		for (int i = 0; i < strs.length; i++) {
			trie.insert(strs[i]);
		}
		return trie;
	}

}
