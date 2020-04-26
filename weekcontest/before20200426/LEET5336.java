package com.zm.LeetCodeEx.weekcontest.before20200426;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * 双周赛 2020年3月7日 5336. 上升下降字符串
 * <p>
 * 给你一个字符串 s ，请你根据下面的算法重新构造字符串：
 * <p>
 * 从 s 中选出 最小 的字符，将它 接在 结果字符串的后面。
 * 从 s 剩余字符中选出 最小 的字符，且该字符比上一个添加的字符大，将它 接在 结果字符串后面。
 * 重复步骤 2 ，直到你没法从 s 中选择字符。
 * 从 s 中选出 最大 的字符，将它 接在 结果字符串的后面。
 * 从 s 剩余字符中选出 最大 的字符，且该字符比上一个添加的字符小，将它 接在 结果字符串后面。
 * 重复步骤 5 ，直到你没法从 s 中选择字符。
 * 重复步骤 1 到 6 ，直到 s 中所有字符都已经被选过。
 * 在任何一步中，如果最小或者最大字符不止一个 ，你可以选择其中任意一个，并将其添加到结果字符串。
 * <p>
 * 请你返回将 s 中字符重新排序后的 结果字符串 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "aaaabbbbcccc"
 * 输出："abccbaabccba"
 * 解释：第一轮的步骤 1，2，3 后，结果字符串为 result = "abc"
 * 第一轮的步骤 4，5，6 后，结果字符串为 result = "abccba"
 * 第一轮结束，现在 s = "aabbcc" ，我们再次回到步骤 1
 * 第二轮的步骤 1，2，3 后，结果字符串为 result = "abccbaabc"
 * 第二轮的步骤 4，5，6 后，结果字符串为 result = "abccbaabccba"
 * 示例 2：
 * <p>
 * 输入：s = "rat"
 * 输出："art"
 * 解释：单词 "rat" 在上述算法重排序以后变成 "art"
 * 示例 3：
 * <p>
 * 输入：s = "leetcode"
 * 输出："cdelotee"
 * 示例 4：
 * <p>
 * 输入：s = "ggggggg"
 * 输出："ggggggg"
 * 示例 5：
 * <p>
 * 输入：s = "spo"
 * 输出："ops"
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 500
 * s 只包含小写英文字母。
 *
 * @author zm
 */
public class LEET5336 {
    public static void main(String[] args) {
        LEET5336 l5336 = new LEET5336();
        System.out.println(l5336.new Solution().sortString("aaaabbbbcccc"));
        System.out.println(l5336.new Solution().sortString("rat"));
        System.out.println(l5336.new Solution().sortString("leetcode"));
        System.out.println(l5336.new Solution().sortString("ggggggg"));
        System.out.println(l5336.new Solution().sortString("spo"));
    }

    class Solution {
        public String sortString(String s) {
            char[] chars = s.toCharArray();
            TreeMap<Character, Integer> treeMap = new TreeMap<>();
            TreeMap<Character, Integer> treeMapReverse = new TreeMap<Character, Integer>(new Comparator<Character>() {
                @Override
                public int compare(Character o1, Character o2) {
                    if (o1 > o2) {
                        return -1;
                    } else if (o1 < o2) {
                        return 1;
                    }

                    return 0;
                }
            });
            for (int i = 0; i < s.length(); i++) {
                treeMap.put(chars[i], treeMap.getOrDefault(chars[i], 0) + 1);
            }
            for (Map.Entry<Character, Integer> entry : treeMap.entrySet()) {
                treeMapReverse.put(entry.getKey(), entry.getValue());
            }
            boolean di = true;
            StringBuilder sb = new StringBuilder();
            while (!treeMap.isEmpty()) {
                TreeMap<Character, Integer> temp = treeMapReverse;
                TreeMap<Character, Integer> temp2 = treeMap;
                if (di) {
                    temp = treeMap;
                    temp2 = treeMapReverse;
                }
                Iterator<Map.Entry<Character, Integer>> iter = temp.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry<Character, Integer> entry = iter.next();
                    sb.append(entry.getKey());
                    entry.setValue(entry.getValue() - 1);
                    temp2.put(entry.getKey(), entry.getValue());
                    if (entry.getValue() == 0) {
                        char c = entry.getKey();
                        iter.remove();
                        temp2.remove(c);
                    }
                }

                di = !di;
            }
            return sb.toString();
        }
    }
}
