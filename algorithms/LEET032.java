package com.zm.LeetCodeEx.algorithms;

import java.util.Stack;

/**
 * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "(()"
 * 输出: 2
 * 解释: 最长有效括号子串为 "()"
 * 示例 2:
 * <p>
 * 输入: ")()())"
 * 输出: 4
 * 解释: 最长有效括号子串为 "()()"
 *
 * @author zm
 */
public class LEET032 {
    public static void main(String[] args) {
        LEET032 l032 = new LEET032();
        String s = "(()(()()";
        System.out.println(l032.longestValidParentheses(s));
    }

    /**
     * 使用栈
     * 对于遇到的每个"(" ，把它的下标放入栈中
     * 对于遇到的每个")" ，栈弹出一个元素，并计算i与弹出后栈顶元素的差值，就最长有效括号的长度
     *
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        int ret = 0;
        Stack<Integer> stack = new Stack<>();
        // 放一个初始值
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    ret = Math.max(ret, i - stack.peek());
                }
            }
        }
        return ret;
    }

    /**
     * 使用双指针
     * 最开始就想到用这个方法，但是没解决解决 ()(()，加上条件right == left才能是正确值，并且需要双向遍历
     *
     * @param s
     * @return
     */
    public int longestValidParentheses2(String s) {
        int l = 0, r = 0, maxlength = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                l++;
            } else {
                r++;
            }
            if (l == r) {
                maxlength = Math.max(maxlength, 2 * r);
            } else if (r >= l) {
                l = r = 0;
            }
        }
        l = r = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                l++;
            } else {
                r++;
            }
            if (l == r) {
                maxlength = Math.max(maxlength, 2 * l);
            } else if (l >= r) {
                l = r = 0;
            }
        }
        return maxlength;
    }

}
