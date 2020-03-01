package com.zm.LeetCodeEx;

/**
 * 周赛 2020年1月5日  5303. 解码字母到整数映射
 * <p>
 * 给你一个字符串 s，它由数字（'0' - '9'）和 '#' 组成。我们希望按下述规则将 s 映射为一些小写英文字符：
 * <p>
 * 字符（'a' - 'i'）分别用（'1' - '9'）表示。
 * 字符（'j' - 'z'）分别用（'10#' - '26#'）表示。
 * 返回映射之后形成的新字符串。
 * <p>
 * 题目数据保证映射始终唯一。
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "10#11#12"
 * 输出："jkab"
 * 解释："j" -> "10#" , "k" -> "11#" , "a" -> "1" , "b" -> "2".
 * 示例 2：
 * <p>
 * 输入：s = "1326#"
 * 输出："acz"
 * 示例 3：
 * <p>
 * 输入：s = "25#"
 * 输出："y"
 * 示例 4：
 * <p>
 * 输入：s = "12345678910#11#12#13#14#15#16#17#18#19#20#21#22#23#24#25#26#"
 * 输出："abcdefghijklmnopqrstuvwxyz"
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 1000
 * s[i] 只包含数字（'0'-'9'）和 '#' 字符。
 * s 是映射始终存在的有效字符串。
 *
 * @author zm
 */
public class LEET5303 {
    public static void main(String[] args) {
        LEET5303 l5303 = new LEET5303();
        System.out.println(l5303.freqAlphabets("10#11#12"));
        System.out.println(l5303.freqAlphabets("1326#"));
        System.out.println(l5303.freqAlphabets("25#"));
        System.out.println(l5303.freqAlphabets("12345678910#11#12#13#14#15#16#17#18#19#20#21#22#23#24#25#26#"));

    }

    public String freqAlphabets(String s) {
        StringBuilder ret = new StringBuilder();
        int i = 0;
        int j = 2;
        while (j < s.length()) {
            if (s.charAt(j) != '#') {
                ret.append((char) (s.charAt(i++) + 48));
                j++;
            } else {
                ret.append((char) ('0' + Integer.valueOf(s.substring(i, i + 2)) + 48));
                i = j + 1;
                j = i + 2;
            }
        }
        if (i < s.length()) {
            ret.append((char) (s.charAt(i++) + 48));
            if (i < s.length()) {
                ret.append((char) (s.charAt(i) + 48));
            }
        }
        return ret.toString();
    }
}
