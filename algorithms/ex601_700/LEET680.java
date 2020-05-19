package com.zm.LeetCodeEx.algorithms.ex601_700;

/**
 * 680. 验证回文字符串 Ⅱ
 * <p>
 * 给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "aba"
 * 输出: True
 * 示例 2:
 * <p>
 * 输入: "abca"
 * 输出: True
 * 解释: 你可以删除c字符。
 * 注意:
 * <p>
 * 字符串只包含从 a-z 的小写字母。字符串的最大长度是50000。
 *
 * @author zm
 */
public class LEET680 {
    public static void main(String[] args) {
        LEET680 l680 = new LEET680();
        System.out.println(l680.new Solution().validPalindrome("aba"));
        System.out.println(l680.new Solution().validPalindrome("abca"));
        System.out.println(l680.new Solution().validPalindrome("abbca"));
        System.out.println(l680.new Solution().validPalindrome("abbdca"));
        System.out.println(l680.new Solution().validPalindrome("bcc"));
        System.out.println(l680.new Solution().validPalindrome(
                "aguokepatgbnvfqmgmlcupuufxoohdfpgjdmysgvhmvffcnqxj" +
                        "jxqncffvmhvgsymdjgpfdhooxfuupuculmgmqfvnbgtapekouga"));
        // aguokepatgbnvfqmgml cupuufxoohdfpgjdmysgvhmvffcnqxj
        // aguokepatgbnvfqmgmlucupuufxoohdfpgjdmysgvhmvffcnqxj
    }

    class Solution {
        private char[] cs;
        private int l;

        public boolean validPalindrome(String s) {
            l = s.length();
            if (l <= 2) {
                return true;
            }
            cs = s.toCharArray();
            int del = 0;
            int left = 0;
            int right = l - 1;
            return check(left, right, del);
        }

        private boolean check(int left, int right, int del) {
            if (left > l >> 1) {
                return true;
            }
            if (cs[left] != cs[right]) {
                if (del == 1) {
                    return false;
                }
                if (cs[left + 1] == cs[right]) {
                    boolean check1 = check(left + 1, right, del + 1);
                    if (check1) {
                        return true;
                    }
                }
                if (cs[left] == cs[right - 1]) {
                    return check(left, right - 1, del + 1);
                }
                return false;
            } else {
                return check(left + 1, right - 1, del);
            }
        }
    }

    class Solution2 {
        public boolean validPalindrome(String s) {
            int l = s.length();
            if (l <= 2) {
                return true;
            }
            char[] cs = s.toCharArray();
            int del = 0;
            int left = 0;
            int right = l - 1;
            while (left < right) {
                char cl = cs[left];
                char cr = cs[right];
                if (cl == cr) {
                    left++;
                    right--;
                } else {
                    // 判断left+1
                    boolean check1 = true;
                    int templ = left + 1;
                    int tempr = right;
                    while (templ < tempr) {
                        if (cs[templ] != cs[tempr]) {
                            check1 = false;
                            break;
                        } else {
                            templ++;
                            tempr--;
                        }
                    }
                    if (check1) {
                        return true;
                    }
                    // 判断right-1
                    templ = left;
                    tempr = right - 1;
                    while (templ < tempr) {
                        if (cs[templ] != cs[tempr]) {
                            return false;
                        } else {
                            templ++;
                            tempr--;
                        }
                    }
                    return true;
                }
            }
            return true;
        }
    }
}
