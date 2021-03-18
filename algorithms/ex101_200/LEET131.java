package com.zm.LeetCodeEx.algorithms.ex101_200;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 131. 分割回文串
 * <p>
 * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
 * <p>
 * 回文串 是正着读和反着读都一样的字符串。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "aab"
 * 输出：[["a","a","b"],["aa","b"]]
 * 示例 2：
 * <p>
 * 输入：s = "a"
 * 输出：[["a"]]
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 16
 * s 仅由小写英文字母组成
 *
 * @author zm
 */
public class LEET131 {
    public static void main(String[] args) {
        LEET131 l131 = new LEET131();
        System.out.println(JSON.toJSONString(l131.new Solution().partition("aab")));
        System.out.println(JSON.toJSONString(l131.new Solution().partition("a")));
        System.out.println(JSON.toJSONString(l131.new Solution().partition("aabc")));
        System.out.println(JSON.toJSONString(l131.new Solution().partition("aabccba")));
        System.out.println(JSON.toJSONString(l131.new Solution().partition("aabccbaa")));
        System.out.println(JSON.toJSONString(l131.new Solution().partition("aabccbaab")));
    }

    class Solution {
        private int len;
        private List<String>[] palindromesByIndex;

        public List<List<String>> partition(String s) {
            len = s.length();
            // 记录以某个字符开头的回文字符串
            palindromesByIndex = new ArrayList[len];
            for (int i = 0; i < len; i++) {
                palindromesByIndex[i] = new ArrayList<>();
            }
            // 遍历每个字符，中心扩散法获取回文字符串
            for (int i = 0; i < len; i++) {
                List<String> oddStrs = centerSpread(s, i, i);
                List<String> evenStrs = centerSpread(s, i, i + 1);
                dealStrs(palindromesByIndex, i, oddStrs, evenStrs);
            }
            // System.out.println(JSON.toJSONString(palindromesByIndex));
            List<List<String>> result = new ArrayList<>();
            combine(0, new ArrayList<String>(), result);
            return result;

        }

        private void combine(int start, ArrayList<String> strings, List<List<String>> result) {
            if (start == len) {
                ArrayList<String> resultStr = new ArrayList<>(strings);
                result.add(resultStr);
                return;
            } else if (start > len) {
                return;
            }
            for (String pStr : palindromesByIndex[start]) {
                strings.add(pStr);
                combine(start + pStr.length(), strings, result);
                strings.remove(strings.size() - 1);
            }
        }

        private void dealStrs(List<String>[] palindromesByIndex, int i, List<String>... strLists) {
            for (List<String> strList : strLists) {
                for (String str : strList) {
                    int offset = (str.length() + 1) / 2 - 1;
                    palindromesByIndex[i - offset].add(str);
                }
            }
        }

        /**
         * 中心扩散
         *
         * @param s     字符串
         * @param left  开始index
         * @param right 结束index
         * @return String 回文字符串
         * @author 参考liweiwei@leetcode
         */
        private List<String> centerSpread(String s, int left, int right) {
            List<String> pList = new ArrayList<>();
            // left = right 的时候，此时回文中心是一个字符，回文串的长度是奇数
            // right = left + 1 的时候，此时回文中心是一个空隙，回文串的长度是偶数
            int len = s.length();
            int i = left;
            int j = right;
            while (i >= 0 && j < len) {
                if (s.charAt(i) == s.charAt(j)) {
                    pList.add(s.substring(i, j + 1));
                    i--;
                    j++;
                } else {
                    break;
                }
            }
            return pList;
        }
    }

    /**
     * 官方题解 dp预处理
     */
    class Solution2 {
        boolean[][] f;
        List<List<String>> ret = new ArrayList<List<String>>();
        List<String> ans = new ArrayList<String>();
        int n;

        public List<List<String>> partition(String s) {
            n = s.length();
            f = new boolean[n][n];
            for (int i = 0; i < n; ++i) {
                Arrays.fill(f[i], true);
            }

            for (int i = n - 1; i >= 0; --i) {
                for (int j = i + 1; j < n; ++j) {
                    f[i][j] = (s.charAt(i) == s.charAt(j)) && f[i + 1][j - 1];
                }
            }

            dfs(s, 0);
            return ret;
        }

        public void dfs(String s, int i) {
            if (i == n) {
                ret.add(new ArrayList<String>(ans));
                return;
            }
            for (int j = i; j < n; ++j) {
                if (f[i][j]) {
                    ans.add(s.substring(i, j + 1));
                    dfs(s, j + 1);
                    ans.remove(ans.size() - 1);
                }
            }
        }
    }

}
