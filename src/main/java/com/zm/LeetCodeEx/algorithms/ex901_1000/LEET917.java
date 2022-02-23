package com.zm.LeetCodeEx.algorithms.ex901_1000;

/**
 * 917. 仅仅反转字母
 * 给你一个字符串 s ，根据下述规则反转字符串：
 * <p>
 * 所有非英文字母保留在原有位置。
 * 所有英文字母（小写或大写）位置反转。
 * 返回反转后的 s 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "ab-cd"
 * 输出："dc-ba"
 * 示例 2：
 * <p>
 * 输入：s = "a-bC-dEf-ghIj"
 * 输出："j-Ih-gfE-dCba"
 * 示例 3：
 * <p>
 * 输入：s = "Test1ng-Leet=code-Q!"
 * 输出："Qedo1ct-eeLg=ntse-T!"
 * <p>
 * <p>
 * 提示
 * <p>
 * 1 <= s.length <= 100
 * s 仅由 ASCII 值在范围 [33, 122] 的字符组成
 * s 不含 '\"' 或 '\\'
 *
 * @author zm
 */
public class LEET917 {
    public static void main(String[] args) {
        System.out.println(new Solution().reverseOnlyLetters("ab-cd"));
        System.out.println(new Solution().reverseOnlyLetters("a-bC-dEf-ghIj"));
        System.out.println(new Solution().reverseOnlyLetters("Test1ng-Leet=code-Q!"));
    }

    /**
     * 双指针交换，遇到不是字母的跳过
     */
    static class Solution {
        public String reverseOnlyLetters(String s) {
            char[] cs = s.toCharArray();
            int l = 0;
            int r = cs.length - 1;
            while (l < r) {
                if (Character.isLetter(cs[r])) {
                    while (!Character.isLetter(cs[l])) {
                        l++;
                    }
                    char temp = cs[r];
                    cs[r] = cs[l];
                    cs[l] = temp;
                    l++;
                }
                r--;
            }
            return new String(cs);
        }
    }
}
