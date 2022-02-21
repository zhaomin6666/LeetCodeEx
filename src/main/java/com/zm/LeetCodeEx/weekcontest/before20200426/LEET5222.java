package com.zm.LeetCodeEx.weekcontest.before20200426;

import java.util.Stack;

/**
 * 周赛 2019年10月13日  5222. 分割平衡字符串
 * <p>
 * 在一个「平衡字符串」中，'L' 和 'R' 字符的数量是相同的。
 * <p>
 * 给出一个平衡字符串 s，请你将它分割成尽可能多的平衡字符串。
 * <p>
 * 返回可以通过分割得到的平衡字符串的最大数量。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "RLRRLLRLRL"
 * 输出：4
 * 解释：s 可以分割为 "RL", "RRLL", "RL", "RL", 每个子字符串中都包含相同数量的 'L' 和 'R'。
 * 示例 2：
 * <p>
 * 输入：s = "RLLLLRRRLR"
 * 输出：3
 * 解释：s 可以分割为 "RL", "LLLRRR", "LR", 每个子字符串中都包含相同数量的 'L' 和 'R'。
 * 示例 3：
 * <p>
 * 输入：s = "LLLLRRRR"
 * 输出：1
 * 解释：s 只能保持原样 "LLLLRRRR".
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 1000
 * s[i] = 'L' 或 'R'
 *
 * @author zm
 */
public class LEET5222 {
    public static void main(String[] args) {
        LEET5222 l5222 = new LEET5222();
        String s = "L";
        System.out.println(l5222.balancedStringSplit(s));
    }

    public int balancedStringSplit(String s) {
        if (s.length() <= 2) {
            return 1;
        }
        Stack<Character> stack = new Stack<>();
        int ret = 0;
        for (int i = 0; i < s.length(); i++) {
            if (stack.isEmpty()) {
                stack.push(s.charAt(i));
            } else {
                if (stack.peek() != s.charAt(i)) {
                    stack.pop();
                    if (stack.isEmpty()) {
                        ret++;
                    }
                } else {
                    stack.push(s.charAt(i));
                }
            }
        }
        return ret == 0 ? 1 : ret;
    }
}
