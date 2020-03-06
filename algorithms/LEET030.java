package com.zm.LeetCodeEx.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 给定一个字符串 s 和一些长度相同的单词 words。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。 注意子串要与 words
 * 中的单词完全匹配，中间不能有其他字符，但不需要考虑 words 中单词串联的顺序。
 * 
 * @author zm
 *
 */
public class LEET030 {
	public static void main(String[] args) {
		LEET030 l030 = new LEET030();
		String s = "barfoothefoobarman";
		String[] words = { "bar", "foo" };
		System.out.println(l030.findSubstring4(s, words));

		String s2 = "wordgoodgoodgoodbestword";
		String[] words2 = { "word", "good", "best", "good" };
		System.out.println(l030.findSubstring4(s2, words2));
	}

	/**
	 * s遍历然后里面遍历words，如果s为words中某一个开头则stemp去掉头部再遍历words 超时了。。。
	 * 
	 * @param s
	 * @param words
	 * @return
	 */
	public List<Integer> findSubstring(String s, String[] words) {
		ArrayList<Integer> ret = new ArrayList<Integer>();
		if (words.length == 0) {
			return ret;
		}
		for (int i = 0; i < s.length(); i++) {
			String stemp = s.substring(i);
			ArrayList<String> wordstemp = new ArrayList<>();
			wordstemp.addAll(Arrays.asList(words));
			while (wordstemp.size() > 0) {
				boolean flag1 = false;
				Iterator<String> it = wordstemp.iterator();
				while (it.hasNext()) {
					String str = it.next();
					if (stemp.startsWith(str)) {
						stemp = stemp.substring(str.length());
						it.remove();
						flag1 = true;
					}
				}
				if (!flag1) {
					break;
				}
			}
			if (wordstemp.size() == 0) {
				ret.add(i);
			}
		}
		return ret;
	}

	/**
	 * 把words写成map，把固定长度的窗口转换成map，判断两个map是否相同
	 * 
	 * @param s
	 * @param words
	 * @return
	 */
	public List<Integer> findSubstring2(String s, String[] words) {
		ArrayList<Integer> ret = new ArrayList<Integer>();
		if (words.length == 0) {
			return ret;
		}
		HashMap<String, Integer> wordsmap = convertListToMap(words);
		int length = words.length * words[0].length();
		for (int i = 0; i < s.length() - length + 1; i++) {
			String stemp = s.substring(i, i + length);
			String[] stemplist = new String[words.length];
			for (int j = 0; j < words.length; j++) {
				stemplist[j] = stemp.substring(j * words[0].length(), (j + 1) * words[0].length());
			}
			HashMap<String, Integer> stempmap = convertListToMap(stemplist);

			// 判断两个map的符合程度
			boolean flag = true;
			for (String string : stempmap.keySet()) {
				if (wordsmap.get(string) == null || wordsmap.get(string) != stempmap.get(string)) {
					flag = false;
					break;
				}
			}
			if (flag) {
				ret.add(i);
			}
		}
		return ret;
	}

	public HashMap<String, Integer> convertListToMap(String[] words) {
		// 把words写成map key = 某个单词 ; value = 出现次数
		HashMap<String, Integer> map = new HashMap<>();
		for (int i = 0; i < words.length; i++) {
			if (map.get(words[i]) == null) {
				map.put(words[i], 1);
			} else {
				map.put(words[i], map.get(words[i]) + 1);
			}
		}
		return map;
	}

	/**
	 * 尝试优化上面的
	 * 
	 * @param s
	 * @param words
	 * @return
	 */
	public List<Integer> findSubstring3(String s, String[] words) {
		ArrayList<Integer> ret = new ArrayList<Integer>();
		if (words.length == 0 || s.length() == 0) {
			return ret;
		}
		int onewordlen = words[0].length();
		int length = words.length * onewordlen;
		for (int i = 0; i < s.length() - length + 1; i++) {
			HashMap<String, Integer> wordsmaptemp = convertListToMap(words);
			String stemp = s.substring(i, i + length);
			boolean flag = true;
			for (int j = 0; j < words.length; j++) {
				String oneword = stemp.substring(j * onewordlen, j * onewordlen + onewordlen);
				Integer onewordcnt = wordsmaptemp.get(oneword);
				if (onewordcnt == null || onewordcnt == 0) {
					flag = false;
					break;
				} else {
					wordsmaptemp.put(oneword, wordsmaptemp.get(oneword) - 1);
				}
			}
			if (flag) {
				ret.add(i);
			}
		}
		return ret;
	}

	/**
	 * 窗口大小不固定，使用双指针，i,j=0 例如第一个例子 barfoothefoobarman ["bar","foo"]
	 * 如果j往后数3，窗口内是bar，bar在words中，且数量就是words中的数量，j+3 3=words[0].length()
	 * j=3，j往后数3，窗口内是bar,foo，新增的foo在words中，且数量就是words中的数量，j+3 此时j-i=3*2
	 * 2=words.length 满足要求记录下i=0 <br>
	 * <b>1.上述例子</b> j=6，j往后数3，窗口内是bar,foo,the，新增的foo不在words中，i,j=j+3 <br>
	 * <b>2.若，例子为 barfoobarthe，那么：</b>
	 * j=6，j往后数3，窗口内是bar,foo,bar，新增的bar在words中，循环(若bar在窗口中的数量大于words中的数量){把窗口内最左边的去掉，i=i+3}
	 * ==> i=i+3,j=j+3 此时j-i=3*2 满足要求记录下i=3 <br>
	 * <b>3.若，例子为 barfoofoothe，那么：</b>
	 * j=6，j往后数3，窗口内是bar,foo,foo，新增的foo在words中，循环(若foo在窗口中的数量大于words中的数量){把窗口内最左边的去掉，i=i+3}
	 * ==> i=i+6,j=j+3 此时j-i=3 不满足要求<br>
	 * 
	 * @param s
	 * @param words
	 * @return
	 */
	public List<Integer> findSubstring4(String s, String[] words) {
		List<Integer> result = new ArrayList<>();
		if (s == null || s.length() == 0 || words == null || words.length == 0) {
			return result;
		}
		Map<String, Integer> wordsCount = generateCount(words);// 将所有字符加入数组Hash表
		int length = words[0].length();
		for (int i = 0; i < length; ++i) {// 错位循环，保证每种情况都遍历到
			Map<String, Integer> window = new HashMap<>();
			int left = i;
			int right = i;
			while (right <= s.length() - length && left <= s.length() - length * words.length) {
				String sub = s.substring(right, right + length);
				incr(window, sub);// 取一个字符加入窗口Hash表
				if (!wordsCount.containsKey(sub)) {// 如果这个字符在数组Hash表中不存在，就清理窗口并重置left和right
					window.clear();
					right += length;
					left = right;
					continue;
				}
				while (window.get(sub) > wordsCount.get(sub)) {// 当窗口Hash中字符次数多于数组Hash字符次数时，left+l,交从窗口中移除最左边的字符
					decr(window, s.substring(left, left + length));
					left += length;
				}
				right += length;
				if (right - left == length * words.length) {// 当窗口长度正好等于数组字符总长度时，表示匹配成功，加入结果中
					result.add(left);
				}
			}
		}
		return result;
	}

	private Map<String, Integer> generateCount(String[] words) {
		Map<String, Integer> wordsCount = new HashMap<>();
		for (String word : words) {
			incr(wordsCount, word);
		}
		return wordsCount;
	}

	private void incr(Map<String, Integer> map, String key) {
		if (map.containsKey(key)) {
			map.put(key, map.get(key) + 1);
		} else {
			map.put(key, 1);
		}
	}

	private void decr(Map<String, Integer> map, String key) {
		if (map.containsKey(key)) {
			Integer value = map.get(key);
			if (value <= 1) {
				map.remove(key);
			} else {
				map.put(key, value - 1);
			}
		}
	}
}
