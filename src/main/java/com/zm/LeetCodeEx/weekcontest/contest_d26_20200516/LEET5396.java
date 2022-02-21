package com.zm.LeetCodeEx.weekcontest.contest_d26_20200516;

import com.alibaba.fastjson.JSON;

/**
 * 双周赛 2020年5月16日
 * <p>
 * 5396. 连续字符
 * <p>
 * 给你一个字符串 s ，字符串的「能量」定义为：只包含一种字符的最长非空子字符串的长度。
 * <p>
 * 请你返回字符串的能量。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "leetcode"
 * 输出：2
 * 解释：子字符串 "ee" 长度为 2 ，只包含字符 'e' 。
 * 示例 2：
 * <p>
 * 输入：s = "abbcccddddeeeeedcba"
 * 输出：5
 * 解释：子字符串 "eeeee" 长度为 5 ，只包含字符 'e' 。
 * 示例 3：
 * <p>
 * 输入：s = "triplepillooooow"
 * 输出：5
 * 示例 4：
 * <p>
 * 输入：s = "hooraaaaaaaaaaay"
 * 输出：11
 * 示例 5：
 * <p>
 * 输入：s = "tourist"
 * 输出：1
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 500
 * s 只包含小写英文字母。
 *
 * @author zm
 */
public class LEET5396 {
    public static void main(String[] args) {
        LEET5396 l5396 = new LEET5396();
        System.out.println(JSON.toJSONString(l5396.new Solution().maxPower("leetcode")));
    }

    class Solution {
        public int maxPower(String s) {
            if (s.length() == 0) {
                return 0;
            }
            char[] cs = s.toCharArray();
            int ret = 1;
            char cur = cs[0];
            int curLength = 1;
            for (int i = 1; i < cs.length; i++) {
                if (cs[i] == cur) {
                    curLength++;
                    ret = Math.max(ret, curLength);
                } else {
                    curLength = 1;
                    cur = cs[i];
                }
            }
            return ret;
        }
    }
}


