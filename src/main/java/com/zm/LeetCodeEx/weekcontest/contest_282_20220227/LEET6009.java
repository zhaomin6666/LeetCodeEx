package com.zm.LeetCodeEx.weekcontest.contest_282_20220227;

/**
 * 6009. 使两字符串互为字母异位词的最少步骤数
 * 给你两个字符串 s 和 t 。在一步操作中，你可以给 s 或者 t 追加 任一字符 。
 * <p>
 * 返回使 s 和 t 互为 字母异位词 所需的最少步骤数。
 * <p>
 * 字母异位词 指字母相同但是顺序不同（或者相同）的字符串。
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "leetcode", t = "coats"
 * 输出：7
 * 解释：
 * - 执行 2 步操作，将 "as" 追加到 s = "leetcode" 中，得到 s = "leetcodeas" 。
 * - 执行 5 步操作，将 "leede" 追加到 t = "coats" 中，得到 t = "coatsleede" 。
 * "leetcodeas" 和 "coatsleede" 互为字母异位词。
 * 总共用去 2 + 5 = 7 步。
 * 可以证明，无法用少于 7 步操作使这两个字符串互为字母异位词。
 * 示例 2：
 * <p>
 * 输入：s = "night", t = "thing"
 * 输出：0
 * 解释：给出的字符串已经互为字母异位词。因此，不需要任何进一步操作。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length, t.length <= 2 * 105
 * s 和 t 由小写英文字符组成
 */
public class LEET6009 {
    public static void main(String[] args) {
        System.out.println(new Solution().minSteps("leetcode", "coats"));
        System.out.println(new Solution().minSteps("night", "thing"));
    }

    /**
     * 数组保存每个字母出现次数，最后累计差值
     */
    static class Solution {
        public int minSteps(String s, String t) {
            int[] cntS = cnt(s);
            int[] cntT = cnt(t);
            int result = 0;
            for (int i = 0; i < 26; i++) {
                result += Math.abs(cntS[i] - cntT[i]);
            }
            return result;
        }

        private int[] cnt(String str) {
            char[] cs = str.toCharArray();
            int[] cntStr = new int[26];
            for (char c : cs) {
                cntStr[c - 'a']++;
            }
            return cntStr;
        }
    }
}
