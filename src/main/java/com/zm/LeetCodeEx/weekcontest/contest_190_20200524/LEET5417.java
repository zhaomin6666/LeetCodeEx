package com.zm.LeetCodeEx.weekcontest.contest_190_20200524;

import java.util.HashSet;

/**
 * 周赛 2020年5月24日
 * <p>
 * 5417. 定长子串中元音的最大数目
 * <p>
 * 给你字符串 s 和整数 k 。
 * <p>
 * 请返回字符串 s 中长度为 k 的单个子字符串中可能包含的最大元音字母数。
 * <p>
 * 英文中的 元音字母 为（a, e, i, o, u）。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "abciiidef", k = 3
 * 输出：3
 * 解释：子字符串 "iii" 包含 3 个元音字母。
 * 示例 2：
 * <p>
 * 输入：s = "aeiou", k = 2
 * 输出：2
 * 解释：任意长度为 2 的子字符串都包含 2 个元音字母。
 * 示例 3：
 * <p>
 * 输入：s = "leetcode", k = 3
 * 输出：2
 * 解释："lee"、"eet" 和 "ode" 都包含 2 个元音字母。
 * 示例 4：
 * <p>
 * 输入：s = "rhythms", k = 4
 * 输出：0
 * 解释：字符串 s 中不含任何元音字母。
 * 示例 5：
 * <p>
 * 输入：s = "tryhard", k = 4
 * 输出：1
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 10^5
 * s 由小写英文字母组成
 * 1 <= k <= s.length
 *
 * @author zm
 */
public class LEET5417 {
    public static void main(String[] args) {
        LEET5417 l5417 = new LEET5417();
        System.out.println(l5417.new Solution().maxVowels("abciiidef", 3));
    }

    class Solution {
        public int maxVowels(String s, int k) {
            HashSet<Character> vset = new HashSet<>();
            vset.add('a');
            vset.add('e');
            vset.add('i');
            vset.add('o');
            vset.add('u');
            char[] cs = s.toCharArray();
            int len = cs.length;
            int l = 0;
            int r = 0;
            int curlen = 1;
            int curvcnt = vset.contains(cs[0]) ? 1 : 0;
            int ret = curvcnt;
            while (r < len - 1) {
                if (curlen == k) {
                    curvcnt += vset.contains(cs[++r]) ? 1 : 0;
                    curvcnt -= vset.contains(cs[l++]) ? 1 : 0;
                } else {
                    curvcnt += vset.contains(cs[++r]) ? 1 : 0;
                    curlen++;
                }
                ret = Math.max(curvcnt, ret);
            }
            return ret;
        }
    }
}
