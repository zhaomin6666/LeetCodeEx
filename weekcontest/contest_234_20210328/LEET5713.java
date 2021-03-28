package com.zm.LeetCodeEx.weekcontest.contest_234_20210328;

import java.util.HashSet;

/**
 * 5713. 字符串中不同整数的数目
 * 给你一个字符串 word ，该字符串由数字和小写英文字母组成。
 * <p>
 * 请你用空格替换每个不是数字的字符。例如，"a123bc34d8ef34" 将会变成 " 123  34 8  34" 。注意，剩下的这些整数间至少要用一个空格隔开："123"、"34"、"8" 和 "34" 。
 * <p>
 * 返回对 word 完成替换后形成的 不同 整数的数目。
 * <p>
 * 如果两个整数的 不含前导零 的十进制表示不同，则认为这两个整数也不同。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：word = "a123bc34d8ef34"
 * 输出：3
 * 解释：不同的整数有 "123"、"34" 和 "8" 。注意，"34" 只计数一次。
 * 示例 2：
 * <p>
 * 输入：word = "leet1234code234"
 * 输出：2
 * 示例 3：
 * <p>
 * 输入：word = "a1b01c001"
 * 输出：1
 * 解释："1"、"01" 和 "001" 视为同一个整数的十进制表示，因为在比较十进制值时会忽略前导零的存在。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= word.length <= 1000
 * word 由数字和小写英文字母组成
 */
public class LEET5713 {
    public static void main(String[] args) {
        LEET5713 leet5710 = new LEET5713();
        System.out.println(leet5710.new Solution2().numDifferentIntegers("a123bc34d8ef34"));
    }


    class Solution {
        public int numDifferentIntegers(String word) {
            HashSet<String> set = new HashSet<>();
            StringBuilder cur = new StringBuilder();
            char[] cs = word.toCharArray();
            int n = cs.length;
            for (int i = 0; i < n; i++) {
                if (cs[i] < '0' || cs[i] > '9') {
                    if (cur.length() > 0) {
                        if (cur.length() > 1) {
                            int needDelete = 0;
                            loop1:
                            for (int j = 0; j < cur.length(); j++) {
                                if (cur.charAt(j) == '0') {
                                    needDelete++;
                                } else {
                                    cur.delete(0, needDelete);
                                    break loop1;
                                }
                            }
                        }
                        set.add(cur.toString());
                        cur = new StringBuilder();
                    }
                } else {
                    cur.append(cs[i]);
                }
            }
            if (cur.length() > 0) {
                if (cur.length() > 1) {
                    int needDelete = 0;
                    loop1:
                    for (int j = 0; j < cur.length(); j++) {
                        if (cur.charAt(j) == '0') {
                            needDelete++;
                        } else {
                            cur.delete(0, needDelete);
                            break loop1;
                        }
                    }
                }
                set.add(cur.toString());
            }
            return set.size();
        }
    }

    class Solution2 {
        public int numDifferentIntegers(String word) {
            HashSet<String> set = new HashSet<>();
            StringBuilder cur = new StringBuilder();
            char[] cs = word.toCharArray();
            int n = cs.length;
            boolean hasNot0 = false;
            boolean has0 = false;
            for (int i = 0; i < n; i++) {
                if (cs[i] < '0' || cs[i] > '9') {
                    if (cur.length() > 0) {
                        set.add(cur.toString());
                        cur = new StringBuilder();
                    } else if (!hasNot0 && has0) {
                        set.add("0");
                    }
                    has0 = false;
                    hasNot0 = false;
                } else {
                    if (cs[i] == '0') {
                        if (hasNot0) {
                            cur.append(cs[i]);
                        }
                        has0 = true;
                    } else {
                        hasNot0 = true;
                        cur.append(cs[i]);
                    }
                }
            }
            if (cur.length() > 0) {
                set.add(cur.toString());
            }
            return set.size();
        }
    }
}
