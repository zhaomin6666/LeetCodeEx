package com.zm.LeetCodeEx.weekcontest.contest_d27_20200530;

import java.util.HashSet;

/**
 * 双周赛 2020年5月30日
 * <p>
 * 5409.1461. 检查一个字符串是否包含所有长度为 K 的二进制子串
 * <p>
 * 给你一个二进制字符串 s 和一个整数 k 。
 * <p>
 * 如果所有长度为 k 的二进制字符串都是 s 的子串，请返回 True ，否则请返回 False 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "00110110", k = 2
 * 输出：true
 * 解释：长度为 2 的二进制串包括 "00"，"01"，"10" 和 "11"。它们分别是 s 中下标为 0，1，3，2 开始的长度为 2 的子串。
 * 示例 2：
 * <p>
 * 输入：s = "00110", k = 2
 * 输出：true
 * 示例 3：
 * <p>
 * 输入：s = "0110", k = 1
 * 输出：true
 * 解释：长度为 1 的二进制串包括 "0" 和 "1"，显然它们都是 s 的子串。
 * 示例 4：
 * <p>
 * 输入：s = "0110", k = 2
 * 输出：false
 * 解释：长度为 2 的二进制串 "00" 没有出现在 s 中。
 * 示例 5：
 * <p>
 * 输入：s = "0000000001011100", k = 4
 * 输出：false
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 5 * 10^5
 * s 中只含 0 和 1 。
 * 1 <= k <= 20
 *
 * @author zm
 */
public class LEET5409 {
    public static void main(String[] args) {
        LEET5409 l5409 = new LEET5409();
        System.out.println(l5409.new Solution().hasAllCodes("00110110", 2));
    }

    class Solution {
        public boolean hasAllCodes(String s, int k) {
            int len = s.length();
            if (len < k) {
                return false;
            }
            HashSet<String> set = new HashSet<>();
            StringBuilder sb = new StringBuilder();
            char[] cs = s.toCharArray();

            int r = 0;
            for (; r < k; r++) {
                sb.append(cs[r]);
            }
            set.add(sb.toString());
            while (r < len) {
                sb.deleteCharAt(0);
                sb.append(cs[r++]);
                set.add(sb.toString());
            }
            return 1 << k == set.size();
        }
    }

    class Solution2 {
        public boolean hasAllCodes(String s, int k) {
            int len = s.length();
            if (len < k) {
                return false;
            }
            HashSet<String> set = new HashSet<>();
            for (int i = 0; i <= s.length() - k; i++) {
                set.add(s.substring(i, i + k));
            }
            return 1 << k == set.size();
        }
    }
}
