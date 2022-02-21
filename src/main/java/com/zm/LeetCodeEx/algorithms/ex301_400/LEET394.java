package com.zm.LeetCodeEx.algorithms.ex301_400;

/**
 * 394. 字符串解码
 * <p>
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 * <p>
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 * <p>
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 * <p>
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 * <p>
 * 示例:
 * <p>
 * s = "3[a]2[bc]", 返回 "aaabcbc".
 * s = "3[a2[c]]", 返回 "accaccacc".
 * s = "2[abc]3[cd]ef", 返回 "abcabccdcdcdef".
 *
 * @author zm
 */
public class LEET394 {
    public static void main(String[] args) {
        LEET394 l394 = new LEET394();
        System.out.println((l394.new Solution().decodeString("3[a]2[bc]")));
        System.out.println((l394.new Solution().decodeString("3[a2[c]]")));
        System.out.println((l394.new Solution().decodeString("2[abc]3[cd]ef")));
        System.out.println((l394.new Solution().decodeString("10[a]")));
        System.out.println((l394.new Solution().decodeString("3[a]2[b4[F]c]")));
    }

    /**
     * 直接递归实现
     */
    class Solution {
        private int l = 0;
        private char[] cs;

        public String decodeString(String s) {
            cs = s.toCharArray();
            l = cs.length;
            StringBuilder ret = new StringBuilder();
            for (int i = 0; i < l; i++) {
                char cur = cs[i];
                if ((cur >= 'a' && cur <= 'z') || (cur >= 'A' && cur <= 'Z')) {
                    ret.append(cur);
                } else if (cur >= '0' && cur <= '9') {
                    StringBuilder roundStr = new StringBuilder();
                    roundStr.append(cur);
                    while (cs[i + 1] >= '0' && cs[i + 1] <= '9') {
                        roundStr.append(cs[i + 1]);
                        i++;
                    }
                    int round = Integer.parseInt(roundStr.toString());
                    String[] str = decode(i + 2);
                    for (int j = 0; j < round; j++) {
                        ret.append(str[0]);
                        i = Integer.valueOf(str[1]);
                    }
                }
            }
            return ret.toString();
        }

        private String[] decode(int start) {
            if (start == l) {
                return new String[]{"", String.valueOf(start)};
            }
            StringBuilder ret = new StringBuilder();
            int i = start;
            for (; i < l; i++) {
                char cur = cs[i];
                if ((cur >= 'a' && cur <= 'z') || (cur >= 'A' && cur <= 'Z')) {
                    ret.append(cur);
                } else if (cur >= '0' && cur <= '9') {
                    StringBuilder roundStr = new StringBuilder();
                    roundStr.append(cur);
                    while (cs[i + 1] >= '0' && cs[i + 1] <= '9') {
                        roundStr.append(cs[i + 1]);
                        i++;
                    }
                    int round = Integer.parseInt(roundStr.toString());
                    String[] str = decode(i + 2);
                    for (int j = 0; j < round; j++) {
                        ret.append(str[0]);
                        i = Integer.valueOf(str[1]);
                    }
                } else if (cur == ']') {
                    break;
                }
            }
            return new String[]{ret.toString(), String.valueOf(i)};
        }


    }
}
